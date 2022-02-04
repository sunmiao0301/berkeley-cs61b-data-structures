## Reading 4.2

## Extends

现在您已经了解了我们如何使用`implements`关键字来定义与接口的层次关系。如果我们想定义类之间的层次关系怎么办？

假设我们要构建一个 RotatingSLList，它与 SLList 具有相同的功能，例如`addFirst`、`size`等，但需要额外的`rotateRight`操作来将最后一项带到列表的前面。

您可以这样做的一种方法是从 SLList 复制和粘贴所有方法并`rotateRight`在其之上写入 - 但这样我们就不会利用继承的力量！请记住，继承允许子类*重用*已定义类的代码。所以让我们定义我们的 RotatingSLList 类来继承 SLList。

我们可以在类头中设置这种继承关系，使用如下`extends`关键字：

```java
public class RotatingSLList<Item> extends SLList<Item>
```

与 AList 与 List61B 共享“is-a”关系的方式相同，RotatingSLList 共享“is-a”关系 SLList。该**`extends`关键字让我们保留 SLList 的原始功能，同时使我们能够进行修改和添加额外的功能。**

![img](https://joshhug.gitbooks.io/hug61b/content/assets/list_subclasses.png)

现在我们已经将 RotatingSLList 定义为从 SLList 扩展，让我们赋予它独特的旋转能力。

**练习 4.2.1。** 定义该`rotateRight`方法，该方法接受现有列表，并将每个元素向右旋转一个位置，将最后一项移动到列表的前面。

例如，调用`rotateRight`[5, 9, 15, 22] 应该返回 [22, 5, 9, 15]。

*提示：是否有任何继承的方法可能有助于执行此操作？*

```java
//有，将最后一项删除，并将删除操作的返回值作为addFirst的参数操作。具体操作如下：

public class RotatingSLList<Item> extends SLList<Item>{
    public void rotateRight(){
        Item x = removeLast();
        addFirst(x);
    }
}
```

您可能已经注意到我们能够使用在 RotatingSLList 之外定义的方法，因为我们使用`extends`关键字从 SLList 继承它们。这就产生了一个问题：我们到底继承了什么？

通过使用`extends`关键字，子类继承父类的所有**成员**。“成员”包括：

- 所有实例和静态变量
- 所有方法
- 所有嵌套类
- **注意构造函数是不会被继承的，因为子类不能直接访问父类的私有成员。**

### 复仇的SLList

请注意，当有人调用`removeLast`SLList 时，它会丢弃该值 - 再也不会出现。但是，如果那些被移除的价值观离开并开始大规模反抗我们怎么办？在这种情况下，我们需要记住那些被删除（或者更确切地说是有缺陷的 >:() 值是什么，以便我们可以追捕它们并在以后终止它们。

我们创建了一个新类 VengefulSLList，它会记住所有被`removeLast`.

和之前一样，我们在 VengefulSLList 的类头中指定它应该从 SLList 继承。

```java
public class VengefulSLList<Item> extends SLList<Item>
```

现在，让我们给 VengefulSLList 一个方法来打印出所有通过调用该`removeLast`方法删除的项目，`printLostItems()`. 我们可以通过添加一个可以跟踪所有已删除项目的实例变量来做到这一点。如果我们使用 SLList 来跟踪我们的项目，那么我们可以简单地调用该`print()`方法来打印出所有项目。

到目前为止，这就是我们所拥有的：

```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public void printLostItems() {
        deletedItems.print();
    }
}
```

VengefulSLList`removeLast`应该做的事情和 SLList 做的完全一样，除了一个额外的操作 - 将删除的项目添加到`deletedItems`列表中。为了*重用*代码，我们可以**重写**`removeLast`方法以修改它以满足我们的需要，并使用关键字`super`调用`removeLast`父类 SLList 中定义的方法。

**练习 4.2.2。** 覆盖`removeLast`删除最后一项的方法，将该项添加到`deletedItems`列表中，然后将其返回。

```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList(){
        deletedItems = new SLList<Item>();
    }
    
    @Override
    public void removeLast(){
        Item x = super.removeLast();//调用父类的removeLast()为子类所用（因为此时子类已经在重写removeLast()函数了，所以不能像上个例子RotatingSLList那样直接使用了，而需要super关键词。
        deletedItems.addLast(x);
        return x;
    }
    
    public void printLostItems() {
        deletedItems.print();
    }
}
```

**注意这里的：** 

**`Item x = super.removeLast();`//调用父类的removeLast()为子类所用（因为此时子类已经在重写removeLast()函数了，所以不能像上个例子RotatingSLList那样直接使用了，而需要super关键词。**



### 构造函数不被继承

正如我们前面提到的，子类继承了父类的所有成员，其中包括实例和静态变量、方法和嵌套类，但不*包括*构造函数。

虽然构造函数不是继承的，但 Java 要求所有构造函数都必须以调用其超类的构造函数之一开始。

要了解为什么会这样，请记住`extends`关键字定义了子类和父类之间的“is-a”关系。如果 VengefulSLList “是一个” SLList，那么每个 VengefulSLList 都必须像 SLList 一样设置。

这里有更深入的解释。假设我们有两个类：

```java
public class Human {...}
public class TA extends Human {...}
```

TA 扩展 Human 是合乎逻辑的，因为所有 TA 都是 Human。因此，我们希望 TA 继承人类的属性和行为。

如果我们运行下面的代码：

```java
TA Christine = new TA();
```

然后首先，必须创建一个人类。然后，可以赋予该人类 TA 的品质。如果不先创建 Human 就构建 TA 是没有意义的。

**[关于这部分，我们可以看此视频，说的很好，通过Debug和Java Visualizer解释发生了什么](https://youtu.be/Cb8F2qreFR8)**

**因此，我们可以使用`super`关键字显式调用超类的构造函数：**

```java
public VengefulSLList() {
    super();
    deletedItems = new SLList<Item>();
}
```

**或者，如果我们选择不这样做，Java 会自动为我们调用超类的*无参数*构造函数。**

```java
public VengefulSLList(){
    //在这里会自动调用super(),也就是父类的无参构造函数
    deletedItems = new SLList<Item>();
}
```

**在这种情况下，添加`super()`与我们之前编写的构造函数没有区别。它只是明确了 Java 之前隐式所做的事情。但是，如果我们要在 VengefulSLList 中定义另一个构造函数，Java 的隐式调用可能不是我们想要调用的。**

**假设我们有一个接受一个项目的单参数构造函数。如果我们依赖于对超类的*无参数*构造函数的隐式调用，`super()`那么作为参数传入的项目将不会放置在任何地方！**

```java
public VengefulSLList(Item x) {
    //如果不通过super调用父类的含参构造函数，那么就会依旧调用默认的无参构造函数，也就是错误的。
    deletedItems = new SLList<Item>();
}
```

**因此，我们必须通过将项目作为参数传递给 super 来显式调用正确的构造函数，如下：**

```java
public VengefulSLList(Item x) {
    super(x);
    deletedItems = new SLList<Item>();
}
```

### 对象类 Object

Java 中的每个类都是 Object 类或 Object 类的后代`extends`。即使类中没有显式`extends`Object类，但是仍然隐式extends了 Object 类。

例如，

- VengefulSLList `extends`SLList 在其类声明中（显式的）
- SLList`extends`Object implicitly（隐式的）

这意味着由于 SLList 继承了 Object 的所有成员，VengefulSLList 继承了 SLList*和*Object 的所有成员，可传递。那么，要从 Object 继承什么？

**正如在 Object 类的[文档中](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)看到的那样，Object 类提供了每个 Object 都应该能够执行的操作，例如`.equals(Object obj)`、`.hashCode()`和`toString()`。**

### Is-a vs. Has-a

*重要提示：*关键字`extends`定义“is-a”或上位关系。一个常见的错误是将其用于“has-a”或meronymic 关系。

在扩展课程时，明智的做法是问问自己“is-a”关系是否有意义。

- `Shower`是一个`Bathroom`？不！
- `VengefulSLList`是一个`SLList`？是的！

## 封装

封装是面向对象编程的基本原则之一，也是我们作为程序员用来抵抗我们最大敌人：*复杂性*的方法之一。管理复杂性是我们在编写大型程序时必须面对的主要挑战之一。

我们可以用来对抗复杂性的一些工具包括分层抽象（抽象障碍！）和一个被称为“为改变而设计”的概念。这围绕着这样一个想法，即程序应该被构建成模块化的、可互换的部分，可以在不破坏系统的情况下进行交换。此外，**隐藏**其他人不需要的信息是管理大型系统时的另一种基本方法。

封装的根源在于向外部隐藏信息的概念。一种看待它的方法是查看封装如何类似于人类细胞。细胞的内部结构可能非常复杂，由染色体、线粒体、核糖体等组成，但它被完全封装在一个*模块*中——抽象掉了内部的复杂性。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/cell_encapsulated.png)

在计算机科学术语中，模块可以定义为一组方法，它们作为一个整体协同工作以执行一项任务或一组相关任务。这可能类似于表示列表的类。现在，如果模块的实现细节在内部保持隐藏，并且与之交互的唯一方法是通过文档化的接口，那么该模块被称为封装。

**以`ArrayDeque`课堂为例。外部世界能够`ArrayDeque`通过其定义的方法（如`addLast`和）使用并与之交互`removeLast`。但是，他们不需要了解如何实现数据结构的复杂细节，以便能够有效地使用它。**这便是抽象。

p.s. oop三大特性：

- 抽象
- 封装
- 继承

### 抽象障碍

理想情况下，用户不应该能够观察到他们正在使用的数据结构的内部运作。幸运的是，Java 使实施抽象屏障变得容易。在 Java 中使用`private`关键字，几乎不可能查看对象内部 - 确保底层复杂性不会暴露给外部世界。

### 继承如何打破封装

假设我们在 Dog 类中有以下两个方法。我们可以这样实现`bark`和`barkMany`：

这是第一种实现。

```java
public void bark() {
    System.out.println("bark");
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

或者，我们也可以像这样实现它：

这是第二种实现。

```java
public void bark() {
    barkMany(1);
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        System.out.println("bark");
    }
}
```

从用户的角度来看，这些实现中的任何一个的功能都是完全相同的。但是，如果我们要定义一个被`Dog`调用的子类`VerboseDog`，并像这样覆盖它的`barkMany`方法，请观察效果：

```java
@Override
public void barkMany(int N) {
    System.out.println("As a dog, I say: ");
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

**练习 4.2.3。** 给定一个 VerboseDog `vd`，`vd.barkMany(3)`在一个实现下会输出什么？第二个实现会输出什么？

- a：As a dog, I say: bark bark bark
- b: bark bark bark
- c: something else

```java
//当我们在第一种实现下 
//根据我们在4.1所说的，调用重写方法的时候，是“动态方法选择”，所以会调用VerboseDog重写的barkMany(3)，也就是
@Override
public void barkMany(int N) {
    System.out.println("As a dog, I say: ");
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
//可以看到，最终会调用bark(),由于VerboseDog中没有重写bark()方法，所以会运行父类的bark()，对于第一种实现，是这样的
public void bark() {
    System.out.println("bark");
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
//所以就会运行得到bark bark bark


//当我们在第二种实现下时，我们还是“动态方法选择”，所以会调用VerboseDog重写的barkMany(3)，也就是父类的bark()
public void bark() {
    barkMany(1);
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        System.out.println("bark");
    }
}
//然而此时的bark()却是返回来运行子类重写的barkMany()

//如您所见，使用第一个实现，输出为 A，而使用第二个实现，程序陷入无限循环。调用bark()将调用barkMany(1)，它调用bark()，无限次重复该过程。最终陷入死循环。
```

## 类型检查和铸造

在我们进入类型和强制转换之前，让我们回顾一下动态方法选择。回想一下，动态方法查找是根据对象的动态类型确定在运行时执行的方法的过程。具体来说，如果 SLList 中的方法被 VengefulSLList 类覆盖，则在运行时调用的方法由该变量的运行时类型或动态类型确定。

**练习 4.2.4。** 对于下面的每一行代码，请确定以下内容：

- 该行会导致编译错误？
- 哪种方法使用动态选择？

```apl
明显
vsl静态类型是VengefulSLList，动态类型是VengefulSLList
sl静态类型是SLList，动态类型是VengefulSLList

倒数第二行肯定是错误，sl的静态类型是SLList，而SLList里面根本就没printLostItem()方法，即使sl的动态类型VerboseSLList里面有printLostItem()方法也没用。

最后一行肯定会错误

至于动态选择，就看是否是：子类重写了父类的方法，并且子类对象又调用了这个方法。
```

![img](https://joshhug.gitbooks.io/hug61b/content/assets/dynamic_selection.png)

让我们逐行浏览这个程序。

```java
VengefulSLList<Integer> vsl = new VengefulSLList<Integer>(9);
SLList<Integer> sl = vsl;
```

**上面这两行编译得很好。由于`VengefulSLList`“is-an” SLList，将`VengefulSLList`类的实例放入`SLList`“容器”中是有效的。**

```java
sl.addLast(50);
sl.removeLast();
```

上面的这些行也可以编译。调用`addLast`是明确的，因为 VengefulSLList 没有覆盖或实现它，所以执行的方法在 SLList 中。然而，该`removeLast`方法被 VengefulSLList 覆盖，所以我们来看看`sl`. 它的动态类型是 VengefulSLList，因此动态方法选择会选择 VengefulSLList 类中被覆盖的方法。

```java
sl.printLostItems();
```

**上面的这一行会导致编译时错误。请记住，编译器根据对象的静态类型确定某事是否有效。由于`sl`是静态类型 SLList，并且`printLostItems`方法没有在 SLList 类中定义，因此即使`sl`的动态类型是 VengefulSLList ，也不允许运行代码。** 

```java
VengefulSLList<Integer> vsl2 = sl;
```

**出于类似的原因，上面的这一行也会导致编译时错误。一般来说，编译器只允许基于编译时类型的方法调用和赋值。由于编译器只看到`sl`是SLList 的静态类型，因此它不会允许 VengefulSLList“容器”来保存它。**

### 表达式

与上面看到的变量一样，使用`new`关键字的表达式也具有编译时类型。

```java
SLList<Integer> sl = new VengefulSLList<Integer>();
```

上面，表达式右侧的编译时类型是 VengefulSLList。编译器检查以确保 VengefulSLList "is-a" SLList，并允许此分配，

```java
VengefulSLList<Integer> vsl = new SLList<Integer>();
```

**上面，表达式右侧的编译时类型是 SLList。编译器检查 SLList 是否为“is-a” VengefulSLList，并非在所有情况下都如此，因此会导致编译错误。**

此外，方法调用的编译时类型等于它们声明的类型。假设我们有这个方法：

```java
public static Dog maxDog(Dog d1, Dog d2) { ... }
```

由于返回类型`maxDog`是 Dog，因此任何调用都`maxDog`将具有编译时类型 Dog。

```java
Poodle frank = new Poodle("Frank", 5);
Poodle frankJr = new Poodle("Frank Jr.", 15);

Dog largerDog = maxDog(frank, frankJr);

Poodle largerPoodle = maxDog(frank, frankJr); //does not compile! RHS has compile-time type Dog
```

**将 Dog 对象分配给 Poodle 变量，就像 SLList 的情况一样，会导致编译错误。贵宾犬“是”狗，但更一般的 Dog 对象可能并不总是贵宾犬，即使它显然是你和我的（我们知道`frank`并且`frankJr`都是贵宾犬！）。**

```java
//上面的代码很容易理解为什么错误和正确：
//首先是
Dog largerDog = maxDog(frank, frankJr);
//maxDog函数需要的参数是Dog，我们给的frank是贵宾犬，是Dog的子类，肯定没问题。

Poodle largerPoodle = maxDog(frank, frankJr); 
//maxDog(@parameter)返回值是Dog,我们却用一个“贵宾犬盒子”来装“狗类对象”，难道所有的狗都是贵宾犬嘛，很显然不是，所以这里出错。
```

**当我们确定该分配会起作用时，有什么办法可以解决这个问题？**

### Casting —— 转型  —— 强制转换

Java 有一种特殊的语法，您可以在其中告诉编译器特定表达式具有特定的编译时类型。这称为“强制转换”。通过强制转换，我们可以告诉编译器将表达式视为不同的编译时类型。

回顾上面失败的代码，因为我们知道`frank`并且`frankJr`都是贵宾犬，我们可以转换：

```java
Poodle largerPoodle = (Poodle) maxDog(frank, frankJr); // compiles! Right hand side has compile-time type Poodle after casting
```

*注意*：强制转换是一种强大但危险的工具。从本质上讲，强制转换是告诉编译器不要执行其类型检查职责——告诉它信任你并按照你想要的方式行事。这是一个可能出现的问题：

```java
Poodle frank = new Poodle("Frank", 5);
Malamute frankSr = new Malamute("Frank Sr.", 100);

Poodle largerPoodle = (Poodle) maxDog(frank, frankSr); // runtime exception!
//maxDog是比较两种狗的大小，正常情况下，返回值是Dog类的对象，但是当你使用(poodle)进行强制转型的时候，返回值就变成了poodle，但是此时，返回的可能是Malamute(而Malamute也是一种狗，与poodle一样，同属于狗类，此时如果强制转型Matamute为poodle就会出错 - ClassCastException。)
```

在这种情况下，我们比较贵宾犬和雪橇犬。如果没有强制转换，编译器通常不允许调用`maxDog`编译，因为右侧的编译时类型是 Dog，而不是 Poodle。但是，强制转换允许此代码通过，并且当`maxDog`在运行时返回雪橇犬时，我们尝试将雪橇犬强制转换为贵宾犬，**我们会遇到运行时异常 - a `ClassCastException`。**

## 高阶函数

稍微绕道，我们将介绍高阶函数。高阶函数是将其他函数视为数据的函数。例如，以这个 Python 程序为例，`do_twice`它接受另一个函数作为输入，并将其应用于输入`x`两次。

```python
def tenX(x):
    return 10*x

def do_twice(f, x):
    return f(f(x))
```

调用 to`print(do_twice(tenX, 2))`会将 tenX 应用于 2，并将 tenX 再次应用于其结果 20，结果为 200。我们如何在 Java 中做这样的事情？

在老式 Java（Java 7 和更早版本）中，内存盒（变量）不能包含指向函数的指针。这意味着我们不能编写具有“Function”类型的函数，因为根本没有函数的类型。

**p.s. 关于Java 8的新特性，见[此处](https://www.runoob.com/java/java8-new-features.html)**

Java8 新增了非常多的特性，我们主要讨论以下几个：

- **Lambda 表达式** − Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。
- **方法引用** − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
- **默认方法** − 默认方法就是一个在接口里面有了一个实现的方法。
- **新工具** − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。
- **Stream API** −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
- **Date Time API** − 加强对日期与时间的处理。
- **Optional 类** − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
- **Nashorn, JavaScript 引擎** − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。

让我们回到原先的问题上来。

为了解决这个问题，我们可以利用接口继承。让我们编写一个接口来定义任何接受整数并返回整数的函数 - an `IntUnaryFunction`。

```java
public interface IntUnaryFunction {
    int apply(int x);
}
```

现在我们可以编写一个类`implements IntUnaryFunction`来表示一个具体的函数。让我们创建一个函数，它接收一个整数并返回该整数的 10 倍。

```java
public class TenX implements IntUnaryFunction {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}
```

至此，我们已经用 Java 编写了`tenX`函数的 Python 等效项。我们现在写吧`do_twice`。

```java
public static int do_twice(IntUnaryFunction f, int x) {
    return f.apply(f.apply(x));
}
```

在 Java 中的调用`print(do_twice(tenX, 2))`如下所示：

```java
System.out.println(do_twice(new TenX(), 2));
```

### 继承备忘单

`VengefulSLList extends SLList`表示 VengefulSLList “is-an” SLList，并继承了 SLList 的所有成员：

- 变量、方法嵌套类
- 不包含构造函数，子类构造函数必须首先调用超类构造函数。**该`super`关键字可用于调用被重写的方法对应的原超类方法和构造函数。**

**重写方法的调用遵循下面几个简单的规则：**

- **编译器很安全，只允许我们根据静态类型做事。**
- **对于被覆盖的方法（*不是重载的方法*），实际调用的方法是基于调用表达式的动态类型**
- **可以使用强制转换来否决编译器类型检查。**