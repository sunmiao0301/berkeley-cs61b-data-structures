## Reading 4.3

## 子类 多态性

我们已经看到继承如何让我们重用超类中的现有代码，同时通过覆盖超类的方法或在子类中编写全新的方法来实现小的修改。继承还使得使用*多态性*设计通用数据结构和方法成为可能。

多态性的核心是“多种形式”。在 Java 中，多态性是指对象如何具有多种形式或类型。在面向对象编程中，多态性与如何将对象视为其自身类的实例、其超类的实例、其超类的超类的实例等有关。

考虑一个静态类型 Deque 的对象`deque`。调用`deque.addFirst()`将在执行时确定，具体取决于调用`addFirst()`时`deque`的动态类型。正如我们在上一章中看到的，Java 使用动态方法选择来选择调用哪个重写方法`addFirst()`。

假设我们要编写一个 python 程序，打印两个对象中较大的一个的字符串表示。对此有两种方法。

1. 显式 HoF 方法

```python
def print_larger(x, y, compare, stringify):
    if compare(x, y):
        return stringify(x)
    return stringify(y)
```

1. 子类多态性方法

```python
def print_larger(x, y):
    if x.largerThan(y):
        return x.str()
    return y.str()
```

使用显式高阶函数方法，您有一种常用方法来打印出两个对象中较大的一个。相反，在子类型多态方法中，对象*本身*做出选择。所`largerFunction`调用的取决于 x 和 y 实际上是什么。

### 最大功能

假设我们要编写一个`max`函数，该函数接受任何数组 - 无论类型如何 - 并返回数组中的最大项。

**练习 4.3.1。** 您的任务是确定下面的代码中有多少编译错误。

```java
public static Object max(Object[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        if (items[i] > items[maxDex]) {
            maxDex = i;
        }
    }
    return items[maxDex];
}

public static void main(String[] args) {
    Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
    Dog maxDog = (Dog) max(dogs);
    maxDog.bark();
}
//我的猜想是：items[i]是可以直接进行比较大小的吗？
//我觉得不能，所以应该是itmes[i].weight?
```



在上面的代码中，只有 1 个错误，在这一行发现：

```java
if (items[i] > items[maxDex]) {
```

这会导致编译错误的原因是因为这一行假设`>`操作符可以使用任意 Object 类型，而实际上并非如此。

相反，我们可以做的一件事是`maxDog`在 Dog 类中定义一个函数，并放弃编写一个可以接收任意类型数组的“一个真正的最大函数”。我们可以这样定义：

```java
public static Dog maxDog(Dog[] dogs) {
    if (dogs == null || dogs.length == 0) {
        return null;
    }
    Dog maxDog = dogs[0];
    for (Dog d : dogs) {
        if (d.size > maxDog.size) {
            maxDog = d;
        }
    }
    return maxDog;
}
```

**虽然这暂时可行，但如果我们放弃创建泛化`max`函数的梦想并让 Dog 类定义自己的`max`函数，那么我们必须对以后定义的任何类做同样的事情。我们需要编写一个`maxCat`函数，一个`maxPenguin`函数，一个`maxWhale`函数等等，导致不必要的重复工作和大量冗余代码。**

导致这种情况的根本问题是 Objects 不能与`>`. 这是有道理的，因为 Java 怎么知道它是否应该使用对象的字符串表示、大小或其他度量来进行比较？在 Python 或 C++ 中，`>`运算符的工作方式可以重新定义，以便在应用于不同类型时以不同的方式工作。不幸的是，Java 没有这种能力。**相反，我们求助于接口继承来帮助我们。**

**我们可以创建一个接口来保证任何实现类，比如 Dog，都包含一个比较方法，我们称之为`compareTo`. —— 这就是Java工程师一年写几百个接口的原因吗？**

![img](https://joshhug.gitbooks.io/hug61b/content/assets/dog_comparable.png)

让我们编写。我们将指定一种方法`compareTo`。

```java
public interface OurComparable {
    public int compareTo(Object o);
}
```

我们将像这样定义它的行为：

- `this`如果< o，则返回 -1 。
- `this`如果等于 o，则返回 0 。
- `this`如果> o，则返回 1 。

现在我们已经创建了`OurComparable`接口，我们可以要求我们的 Dog 类实现该`compareTo`方法。首先，我们将 Dog 的类头更改为 include `implements OurComparable`，然后`compareTo`根据上面定义的行为编写方法。

**练习 4.3.2。** 实现Dog 类的`compareTo`的方法。

```java
public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
}
```

**请注意，由于`compareTo`接受任意 Object o，因此我们必须\*将\*输入转换为 Dog 以使用`size`实例变量进行比较。**

现在我们可以将我们在练习 4.3.1 中定义的函数推广`max`到，而不是接受任何任意的对象数组，而是接受`OurComparable`对象——我们肯定知道所有的`compareTo`方法都实现了。

```java
public static OurComparable max(OurComparable[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        int cmp = items[i].compareTo(items[maxDex]);
        if (cmp > 0) {
            maxDex = i;
        }
    }
    return items[maxDex];
}
```

伟大的！现在我们的`max`函数可以接收任何`OurComparable`类型的对象数组并返回数组中的最大对象。`compareTo`现在，这段代码确实很长，所以我们可以通过修改我们方法的行为使它更简洁：

- `this`如果< o，则返回负数。
- `this`如果等于 o，则返回 0 。
- `this`如果> o，则返回正数。

现在，我们可以返回大小之间的差异。如果我的大小是 2，而 uddaDog 的大小是 5，`compareTo`将返回 -3，一个负数表示我更小。

```java
public int compareTo(Object o) {
    Dog uddaDog = (Dog) o;
    return this.size - uddaDog.size;
}
```

使用继承，我们能够概括我们的max函数。这种方法有什么好处？

- 每个类都不需要最大化代码（即不需要`Dog.maxDog(Dog[])`函数
- 我们有可以优雅地（大部分）在多种类型上运行的代码

### 接口测验

**练习 4.3.3。** 给定`Dog`类、`DogLauncher`类、`OurComparable`接口和`Maximizer`类，如果我们从 Dog 类中省略 compareTo() 方法，哪个文件会编译失败？

```java
public class DogLauncher {
    public static void main(String[] args) {
        ...
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));
    }
}

public class Dog implements OurComparable {
    ...
    //将被省略
    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
    ...
}

public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        ...
        int cmp = items[i].compareTo(items[maxDex]);
        ...
    }
}
```



在这种情况下，`Dog`类无法编译。通过声明它`implements OurComparable`，Dog 类声明它“是”OurComparable。结果，编译器检查此声明是否真的正确，但发现 Dog 没有实现`compareTo`.

如果我们`implements OurComparable`从 Dog 类标题中省略怎么办？由于这一行，这将导致 DogLauncher 中的编译错误：

```java
System.out.println(Maximizer.max(dogs));
```

如果 Dog 没有实现 OurComparable 接口，那么尝试将 Dogs 数组传递给 Maximizer 的`max`函数将不会被编译器批准。`max`只接受 OurComparable 对象的数组。

## 可比物

我们刚刚构建的`OurComparable`界面可以工作，但并不完美。以下是它的一些问题：

- 尴尬的对象转换
- 我们弥补了。
  - 没有现有的类实现 OurComparable（例如 String 等）
  - 没有现有的类使用 OurComparable（例如，没有使用 OurComparable 的内置 max 函数）

**解决方案？我们将利用一个已经存在的名为`Comparable`的接口，`Comparable`已经由 Java 定义并被无数的库使用。**

**`Comparable`看起来与我们制作的 OurComparable 接口非常相似，但有一个主要区别。你能发现吗？**

**![img](https://joshhug.gitbooks.io/hug61b/content/assets/comparable_interface.png)**

**请注意，这`Comparable<T>`意味着它采用泛型类型。这将帮助我们避免将对象强制转换为特定类型！现在，我们将重写 Dog 类以实现 Comparable 接口，确保将泛型类型更新`T`为 Dog：**

```java
public class Dog implements Comparable<Dog> {
    ...
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }
}
```

现在剩下的就是将 Maximizer 类中的每个 OurComparable 实例更改为 Comparable。然后准备看着最大的狗吠叫：

`OurComparable`是Java用户现在使用的真实的内置界面，而不是使用我们个人创建的界面`Comparable`。因此，我们可以利用所有已经存在和使用的库`Comparable`。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/comparable.png)

## 比较器

我们刚刚了解了可比较接口，它为每只 Dog 嵌入了将自己与另一只 Dog 进行比较的能力。现在，我们将介绍一个看起来非常相似的新界面，称为`Comparator`.

让我们从定义一些术语开始。

- 自然顺序 - 用于指代`compareTo`特定类的方法中隐含的顺序。

举个例子，如前所述，Dogs 的自然排序是根据 size 的值定义的。如果我们想以不同于自然顺序的方式对 Dogs 进行排序，例如按名称的字母顺序怎么办？

Java 的做法是使用`Comparator`'s. 由于比较器是一个对象，我们将使用的方式 是在 Dog 中编写一个实现接口`Comparator`的嵌套类。`Comparator`

但首先，这个界面里面有什么？

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

这表明`Comparator`接口要求任何实现类都实现该`compare`方法。的规则`compare`就像`compareTo`：

- 如果 o1 < o2，则返回负数。
- 如果 o1 等于 o2，则返回 0。
- 如果 o1 > o2，则返回正数。

让我们给 Dog 一个 NameComparator。为此，我们可以简单地遵循`String`'s 已经定义的`compareTo`方法。

```java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    ...
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
```

请注意，我们已将 NameComparator 声明为静态类。一个微小的区别，但我们这样做是因为我们不需要实例化 Dog 来获得 NameComparator。让我们看看这个 Comparator 是如何工作的。

如您所见，我们可以像这样检索我们的 NameComparator：

```java
Comparator<Dog> nc = Dog.getNameComparator();
```

总而言之，我们有一个 Dog 类，它有一个私有 NameComparator 类和一个返回 NameComparator 的方法，我们可以使用它按名称按字母顺序比较狗。

让我们看看继承层次结构中的一切是如何工作的——我们有一个 Java 内置的 Comparator 接口，我们可以实现它来在 Dog 中定义我们自己的比较器（`NameComparator`、`SizeComparator`等）。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/comparator.png)

**总而言之，Java 中的接口为我们提供了进行回调的能力。有时，一个函数需要另一个可能尚未编写的函数的帮助（例如`max`需要`compareTo`）。回调函数是帮助函数（在场景中，`compareTo`）。在某些语言中，这是使用显式函数传递来完成的；在 Java 中，我们将所需的函数包装在一个接口中。**

A Comparable 说：“我想将自己与另一个对象进行比较”。它嵌入在对象本身中，并定义了类型的**自然顺序**。另一方面，比较器更像是一个第三方机器，将两个对象相互比较。由于只有一种`compareTo`方法的空间，如果我们想要多种方法进行比较，我们必须求助于 Comparator。