## Reading 4.1

### Problem  — 为了引出（丑陋的重载 和 优雅的接口）

回想一下我们上周创建的两个列表类：SLList 和 AList。如果您查看他们的文档，您会发现它们非常相似。其实他们所有的配套方法都是一样的！

假设我们要编写一个类`WordUtils`，其中包含可以在单词列表上运行的函数，包括计算 SLList 中最长字符串的方法。

**练习 4.1.1。** 尝试自己编写此方法。该方法应接收字符串的 SLList 并返回列表中最长的字符串。

这是我们想出的方法。

```java
public static String longest(SLList<String> list) {
    int maxDex = 0;
    for (int i = 0; i < list.size(); i += 1) {
        String longestString = list.get(maxDex);
        String thisString = list.get(i);
        if (thisString.length() > longestString.length()) {
            maxDex = i;
        }
    }
    return list.get(maxDex);
}
```

我们如何使这种方法也适用于 AList？

我们真正需要做的就是改变方法的签名：参数

```java
SLList<String> list
```

应该改为

```java
AList<String> list
```

现在我们的`WordUtils`类中有两个方法名称完全相同。

```java
public static String longest(SLList<String> list)
```

和

```java
public static String longest(AList<String> list)
```

**这实际上在 Java 中是允许的！这就是所谓的方法重载。**当您调用 WordUtils.longest 时，Java 根据您提供的参数类型知道要运行哪一个。如果为它提供 AList，它将调用 AList 方法。与 SLList 相同。

很高兴 Java 足够聪明，知道如何为不同类型处理两个相同的方法，但是重载有几个缺点：

- 这是超级重复和丑陋的，因为你现在有两个几乎相同的代码块。
- 它需要维护更多的代码，这意味着如果您想对`longest`方法进行小的更改（例如更正错误），则需要在方法中针对每种类型的列表进行更改。
- 如果我们想创建更多的列表类型，我们将不得不为每个新的列表类复制方法。

##### 正是因为重载丑陋，所以针对重载解决的这个问题，还有接口的解决方案如下：

## 上位词、下位词和接口继承

在英语语言和一般生活中，单词和对象存在逻辑层次结构。

**狗是所谓的贵宾犬，雪橇犬，哈士奇等的上位词。反过来，贵宾犬，雪橇犬和哈士奇是狗的下位词。**

这些词形成了“is-a”关系的层次结构：

- 贵宾犬“是”狗
- 狗“是”犬
- 犬类“是”食肉动物
- 食肉动物“是”动物

![等级制度](https://joshhug.gitbooks.io/hug61b/content/assets/hierarchy.png)



SLLists 和 ALists 的层次结构相同！SLList 和 AList 都是更通用列表List的下义词。

我们将在 Java 中形式化这种关系：如果 SLList 是 List61B 的下义词，那么 SLList 类是 List61B 类的**子**类，而 List61B 类是 SLList 类的**超**类。

**图 4.1.1** ![子类](https://joshhug.gitbooks.io/hug61b/content/assets/subclass.png)

在 Java 中，为了*表达*这种层次结构，我们需要做**两件事**：

- 第 1 步：定义通用列表上位词的类型——我们将选择名称 List61B。
- 第 2 步：指定 SLList 和 AList 是该类型的下义词。

**新的 List61B 是 Java 所称的接口**。它本质上是一个指定列表必须能够做什么的合同，但它没有为这些行为提供任何实现。你能想到为什么吗？

这是我们的 List61B 接口interface。至此，我们已经满足了建立关系层次的第一步：创建上位词。

```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void addLast(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
}
```

现在，要完成第 2 步，我们需要指定 AList 和 SLList 是 List61B 类的下义词。在 Java 中，我们在类定义中定义了这种关系。

我们将添加到

```java
public class AList<Item> {...}
```

一个关系定义词：实现。

```java
public class AList<Item> implements List61B<Item>{...}
```

`implements List61B<Item>`本质上是一个承诺。AList 说“我保证我将拥有并定义 List61B 接口中指定的所有属性和行为”

现在我们可以编辑我们的`longest`方法`WordUtils`以获取 List61B. 因为 AList 和 SLList 共享“is-a”关系。

**此时，如果我们还想要一个方法，既可以对AList，也可以对SLList进行操作，就可以不再借助重载来实现了（重载的话，需要写两个函数块，它们除参数不一样，其他都一样）。我们可以直接借助接口：**

```java
//首先是声明一个接口
public interface List61B<Item> {
	...
} 
//然后分别用AList和SLList实现这个接口
public class AList<Item> implements List61B<Item>{
    ...
}
public class SLList<Item> implements List61B<Item>{
    ...
}

//此时，再针对于我们的方法，就不再需要写两次了，而是直接：
public static String longest(List61B<Item> list) {
    int maxDex = 0;
    for (int i = 0; i < list.size(); i += 1) {
        String longestString = list.get(maxDex);
        String thisString = list.get(i);
        if (thisString.length() > longestString.length()) {
            maxDex = i;
        }
    }
    return list.get(maxDex);
}
//此时这个方法，SLList和AList都可以调用 如下：
public static void main(String[] args){
    SLList<String> list1 = new SLList<>();
    AList<String> list2 = new AList<>();
    System.out.println(longest(list1) + longest(list2));
}
```

## Override vs Overload（重写和重载）

我们承诺我们将在 AList 和 SLList 类中实现 List61B 中指定的方法，所以让我们继续这样做。

在子类中实现所需的功能时，将`@Override`标记包含在方法签名的顶部是有用的（并且实际上在 61B 中是必需的）。在这里，我们只用一种方法做到了这一点。

```java
@Override
public void addFirst(Item x) {
    insert(x, 0);
}
```

**值得注意的是，即使您不包含此标记，您*仍然会*覆盖该方法。*所以从技术上讲，你*不必包括它。但是，包含标记可以作为程序员的一种保护措施，它会提醒编译器您打算覆盖此方法。你问这为什么会有帮助？嗯，这有点像有一个校对员！如果过程中出现问题，编译器会告诉您。**

**假设您要覆盖该`addLast`方法。如果你打错字不小心写了`addLsat`怎么办？如果您不包含 @Override 标记，那么您可能不会发现错误，这可能会使调试变得更加困难和痛苦。而如果您包含@Override，编译器将停止并提示您在程序运行之前修复您的错误。**

### 重写 Override

- Override是重写，函数签名（参数）不变，函数内容改变
  - 类与接口之间: 只有实现关系,一个类可以实现多个接口

```java
public interface father{
	public void shout(){
    }
}

public class son implements father{
	@Override // 重写
	public void shout(){
    	System.out.println("ahhhh!");
	}
}
```

### 重载 Overload

- Overload是重载，函数签名（参数）改变，函数内容不变。

```java
public class Math{
    public int abs(int a)
    public double abd(double a)
}
```

## 接口继承 — 注意与实现继承区分

### p.s. 接口继承是接口继承接口，但最终还是没实现  

### p.s. 实现继承是类实现接口

接口继承是指子类继承父类的所有方法/行为的关系。与我们在 Hyponyms 和 Hypernyms**部分**中定义的 List61B 类一样，**接口包括所有方法签名，但不包括实现。实际提供这些实现取决于子类。**

这种继承也是多代的。这意味着如果我们有很长的超类/子类关系，**如图 4.1.1 所示**，AList 不仅继承了 List61B 的方法，而且它上面的所有其他类一直到最高的超类 AKA AList 从 Collection 继承。

**图 4.1.1**

 ![子类](https://joshhug.gitbooks.io/hug61b/content/assets/subclass.png)

# 格罗伊

回想一下我们在第一章中介绍的相等的黄金法则。这意味着每当我们进行赋值时 `a = b` ，我们将 b 中的位复制到 a 中，并要求 b 与 a 的类型相同。您不能分配`Dog b = 1` ，或者`Dog b = new Cat()` 因为 1 不是 Dog，Cat 也不是。

让我们尝试将这个规则应用到`longest`我们在本章之前编写的方法中。

`public static String longest(List61B<String> list)`接受一个 List61B。我们说这也可以包含 AList 和 SLList，但是由于 AList 和 List61B 是不同的类，这怎么可能呢？好吧，回想一下 AList 与 List61B 共享一个“is-a”关系，这意味着 AList 应该能够适合 List61B 框！

**练习 4.1.2** 你认为下面的代码会编译吗？如果是这样，它运行时会发生什么？

```java
public static void main(String[] args) {
    List61B<String> someList = new SLList<String>();
    someList.addFirst("elk");
}
```

以下是可能的答案：

- 不会编译。
- 将编译，但会导致**新**行出错
- 当它运行时，会创建一个 SLList 并将其地址存储在 someList 变量中，但它在 someList.addFirst() 上崩溃，因为 List61B 类没有实现 addFirst；
- **当它运行时，会创建 SLList 并将其地址存储在 someList 变量中。然后将字符串“elk”插入到 addFirst 引用的 SLList 中。**

答案是最后一个

```java
//就如同：
List<Integer> list = new LinkedList<>();
list.addFirst()
```



## 实现继承

**以前，我们有一个接口 List61B，它只有方法头来标识List61B 应该做什么，而没有对其进行具体实现。但是，现在我们将看到我们可以在 List61B 中编写已经完成实现的方法。这些方法确定List61B 的上位词应该如何表现。为此，您必须在方法签名中包含关键字`default`。**

如果我们在 List61B 中定义这个方法

```java
default public void print() {
    for (int i = 0; i < size(); i += 1) {
        System.out.print(get(i) + " ");
    }
    System.out.println();
}
```

那么实现 List61B 类的所有东西都可以使用该方法！

但是，这种方法有一个小的低效率。你能抓住它吗？

```java
/**
 * 低效率在于这个print()方法是需要被用于SLList和AList的
 * 虽然AList中的get(i)方法很快
 * 但是SLList中的get(i)是需要遍历的，速度很慢
 * 如何解决呢？
 */
```

对于 SLList，该`get`方法在每次调用期间需要遍历整个列表，很不好。

我们希望 SLList 以与其接口中指定的方式不同的方式打印。为此，我们需要override重写它。在SLList中，我们重新实现了这个方法；

```java
@Override
public void print() {
    for (Node p = sentinel.next; p != null; p = p.next) {
        System.out.print(p.item + " ");
    }
}
//这样实现就能节省下一大笔时间
```

现在，每当我们在 SLList 上调用 print() 时，它将调用此方法而不是 List61B 中的方法。

**您可能想知道，Java 是如何知道调用哪个 print() 的？好问题。Java 能够做到这一点是由于一种叫做动态方法选择的东西。**

**[关于这部分知识，可以见此视频](https://youtu.be/eNtItRCIkBg)**

**我们知道java中的变量是有类型的。**

```java
List61B<String> lst = new SLList<String>();
```

**在上述声明和实例化中，lst 的类型为“List61B”。这称为“静态类型”**

但是，对象本身也有类型。lst 指向的对象是 SLList 类型的。虽然这个对象本质上是一个 SLList（因为它是这样声明的），但它也是一个 List61B，因为我们之前探讨了“is-a”关系。**但是，因为对象本身是使用 SLList 构造函数实例化的，所以我们称其为“动态类型”。**

**顺便说一句：“动态类型”这个名字实际上在它的起源上是非常语义化的！如果将 lst 重新分配为指向另一种类型的对象，例如 AList 对象，lst 的动态类型现在将是 AList 而不是 SLList！它是动态的，因为它会根据当前所指对象的类型而变化。**

**当 Java 运行一个被 重写 的方法时，它会在其动态类型中搜索适当的方法签名并运行它。**

**重要提示：这不适用于重载方法！**

```apl
以上这些话说的很好，值得揣摩
```

## 重载

假设同一个类中有两个方法

```java
public static void peek(List61B<String> list) {
    System.out.println(list.getLast());
}
public static void peek(SLList<String> list) {
    System.out.println(list.getFirst());
}
```

然后你运行这段代码

```java
SLList<String> SP = new SLList<String>();
List61B<String> LP = SP;
SP.addLast("elk");
SP.addLast("are");
SP.addLast("cool");
peek(SP);
peek(LP);
```

第一次调用 peek() 将使用接收 SLList 的第二个 peek 方法。

第二次调用 peek() 将使用第一个 peek 方法。该方法采用 List61B。这是因为两个重载方法之间的唯一区别是参数的类型。**当 Java 检查要调用哪个重载方法时，它会检查静态类型并调用具有相同类型参数的方法。**

### 也就是说，重写检查动态类型，重载检查静态类型，以此来决定调用哪个方法。

## 接口继承与实现继承

我们如何区分“接口继承”和“实现继承”？好吧，您可以使用这个简单的区别：

- 接口继承（什么）：简单地告诉子类应该能够做什么。
  - EX）所有列表都应该能够自己打印，他们如何做到这一点取决于他们。
- 实现继承（如何）：告诉子类它们应该如何表现。
  - EX）列表应该以这种方式打印自己：按顺序排列每个元素，然后打印它们。

在创建这些层次结构时，请记住子类和超类之间的关系应该是“is-a”关系。AKA Cat 应该只实现 Animal Cat **is an** Animal。您不应该使用“has-a”关系来定义它们。Cat **has-a** Claw，但 Cat 绝对不应该实施 Claw。

最后，实现继承可能听起来不错，但也有一些缺点：

- 我们是容易犯错的人，我们无法跟踪所有内容，因此您可能会覆盖方法但忘记了。

- 如果两个接口给出冲突的默认方法，可能很难解决冲突。

- 它鼓励过于复杂的代码

  ### 下一步是什么？

- [讨论4 继承](https://sp19.datastructur.es/materials/discussion/disc04.pdf)

Given the Animal class, fill in the definition of the Cat class so that when greet() is called, “Cat says: Meow!” is printed (instead of “Animal says: Huh?”). Cats less than the ages of 5 can say “MEOW!” instead of “Meow!”

```java
public class Animal {
	protected String name, noise;
	protected int age;

public Animal(String name, int age) {
	this.name = name;
	this.age = age;
	this.noise = "Huh?";
}

public String makeNoise() {
	if (age < 5) {
        return noise.toUpperCase();
    } else {
        return noise;
    }
}

public void greet() {
    System.out.println("Animal " + name + " says: " + makeNoise());
}
}

public class Cat extends Animal {
    
}
```

### Raining Cats and Dogs
Assume that Animal and Cat are defined as above. What would Java print on each of the indicated lines?

```java
public class TestAnimals {
        public static void main(String[] args) {
                Animal a = new Animal("Pluto", 10);
                Cat c = new Cat("Garfield", 6);
                Dog d = new Dog("Fido", 4);
                a.greet(); // (A) ______________________
                c.greet(); // (B) ______________________
                d.greet(); // (C) ______________________
                a = c;
                ((Cat) a).greet(); // (D) ______________________
                a.greet(); // (E) ______________________
        }
}

public class Dog extends Animal {
        public Dog(String name, int age) {
                super(name, age);
                noise = "Woof!";
        }

        @Override
        public void greet() {
                System.out.println("Dog " + name + " says: " + makeNoise());
        }

        public void playFetch() {
                System.out.println("Fetch, " + name + "!");
        }
}

```

Consider what would happen if we added the following to the bottom of main under：

```java
    a = new Dog("Spot", 10);
    d = a;
```

Why would this code produce a compiler error? How could we fix this error?

```apl
我猜测问题应该出在这行代码：d = a;
a的静态类型是Animal,动态类型是Dog
d的静态类型是Dog,动态类型想要转变为a的静态类型Animal肯定是不行的（上转型？
```





