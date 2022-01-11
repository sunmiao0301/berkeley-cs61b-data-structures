## Reading 1.2

### 定义和使用类

如果您之前没有 Java 经验，我们建议您在阅读本章之前完成[HW0 中](http://sp19.datastructur.es/materials/hw/hw0/hw0.html)的练习。它将涵盖我们不会在本书中讨论的各种语法问题。

### 静态与非静态方法

#### 静态方法

Java 中的所有代码都必须是类的一部分（或类似于类的东西，我们稍后会了解）。大多数代码都写在方法内部。让我们考虑一个例子：

```java
public class Dog {
    public static void makeNoise() {
        System.out.println("Bark!");
    }
}
```

如果我们尝试运行`Dog`该类，我们只会收到一条错误消息：

```bash
$ java Dog
Error: Main method not found in class Dog, please define the main method as:
       public static void main(String[] args)
```

`Dog`我们定义的类不做任何事情。我们只是简单地定义了`Dog` **可以**做的事情，即制造噪音。要真正运行这个类，我们要么需要给类添加一个 main 方法`Dog`，就像我们在 1.1 章中看到的那样。或者我们可以创建一个单独的[`DogLauncher`](https://www.youtube.com/watch?v=Q-LE-jJQLTM)类来运行该类的方法`Dog`。例如，考虑下面的程序：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog.makeNoise();
    }
}
```

```bash
$ java DogLauncher
Bark!
```

使用另一个类的类有时被称为该类的“客户端”，即`DogLauncher`是一个客户端`Dog`。这两种技术都不是更好：`Dog`在某些情况下添加一个 main 方法可能会更好，而在其他情况下创建一个类似的客户端类`DogLauncher`可能会更好。随着我们在整个课程中获得更多练习，每种方法的相对优势将变得清晰。

***一个类可以调用一个另外一个类，这是我们将问题拆分的基础。想要运行一个类，我们必须有一个主方法main。我们可以让其他类有一个main方法，调用我们其他类中的方法，Head first Java中将这种main主类称为测试驱动类。 —— Josh Hug***

#### 实例变量和对象实例化

**[I can't explain better than him](https://www.youtube.com/watch?v=Hsor-iZ6-a4)**

不是所有的狗都是一样的。有些狗喜欢不停地吠叫，而另一些狗则喜欢大声咆哮。通常，我们编写程序来模仿我们所居住的宇宙的特征，而 Java 的语法经过精心设计，可以轻松实现这种模仿。

允许我们表示 Dogdom 范围的一种方法是为每种类型的 Dog 创建单独的类。

```java
public class TinyDog {
    public static void makeNoise() {
        System.out.println("yip yip yip yip");
    }
}

public class MalamuteDog {
    public static void makeNoise() {
        System.out.println("arooooooooooooooo!");
    }
}
```

但是，

正如您在过去应该看到的，类可以被实例化，而实例可以保存数据。这导致了一种更自然的方法，我们创建`Dog`类的实例并使`Dog`方法的行为取决于特定的属性`Dog`。为了使这一点更具体，请考虑以下类：

```java
public class Dog {
    public int weightInPounds;

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }
    }    
}
```

作为使用这种 Dog 的示例，请考虑：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d;
        d = new Dog();
        d.weightInPounds = 20;
        d.makeNoise();
    }
}
```

运行时，这个程序将创建一个`Dog`重量为 20 的，并且`Dog`很快就会发出一个漂亮的“bark. bark.”。

一些关键的观察和术语：

- Java 中的一个`Object`是任何类的实例。
- **`Dog`类有它自己的变量，也被称为*实例变量*或者*非静态变量*。这些必须在类中声明，这与 Python 或 Matlab 等语言不同，后者可以在运行时添加新变量。**
- **我们在`Dog`类中创建的方法没有`static`关键字。我们称这种方法*的实例方法*或者*非静态方法*。**
- 要调用该`makeNoise`方法，我们必须首先使用关键字*实例化*一个Dog ，然后发出特定的bark。换句话说，我们调用`d.makeNoise()`而不是`Dog.makeNoise()`
- 一旦对象被实例化，就可以将其*分配*给适当类型的已*声明*变量，例如`d = new Dog();`
- 类的变量和方法也称为类的*成员*。
- 使用*点表示法*访问类的成员。

### Java中的构造函数

正如你希望见过的，我们通常构建使用面向对象的语言对象*的构造函数*：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(20);
        d.makeNoise();
    }
}
```

在这里，实例化是参数化的，为我们节省了手动输入可能许多实例变量赋值的时间和麻烦。要启用这样的语法，我们只需要在 Dog 类中添加一个“构造函数”，如下所示：

```java
public class Dog {
    public int weightInPounds;

    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }    
    }
}
```

`public Dog(int w)`只要我们尝试`Dog`使用`new`关键字和单个整数参数创建一个，就会调用带有签名的构造函数。对于那些来自 Python 的人来说，构造函数与`__init__`方法非常相似。

### 术语摘要

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110Javaclass.png)

```java
// 对于上面的图
// new Dog(20)
// 很快就会被垃圾收集器清除
```

### 数组实例化，对象数组

正如我们在 HW0 中看到的，数组也在 Java 中使用 new 关键字进行实例化。例如：

```java
public class ArrayDemo {
    public static void main(String[] args) {
        /* Create an array of five integers. */
        int[] someArray = new int[5];
        someArray[0] = 3;
        someArray[1] = 4;
    }
}
```

类似地，我们可以在 Java 中创建实例化对象的数组，例如

```java
public class DogArrayDemo {
    public static void main(String[] args) {
        /* Create an array of two dogs. */
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(8);
        dogs[1] = new Dog(20);

        /* Yipping will result, since dogs[0] has weight 8. */
        dogs[0].makeNoise();
    }
}
```

注意 new 以两种不同的方式使用：一次是创建一个可以容纳两个`Dog`对象的数组，两次是创建每个实际的`Dog`.

### 类方法与实例方法（static）

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0111staticandnonstatic.png)

**Java 允许我们定义两种类型的方法：**

- **类方法，又名静态方法。（用类名就直接能调用的方法 --> Dog.MakeNoise()**
- **实例方法，又称非静态方法。(只有实例能调用的方法) --> Teddy.makeNoise()**

**实例方法是只能由类的特定实例执行的操作。静态方法是类本身采取的行动。两者在不同的情况下都有用。作为静态方法的一个例子，`Math`该类提供了一个`sqrt`方法。因为它是静态的，所以我们可以这样使用它：**

```java
x = Math.sqrt(100);
```

**如果`sqrt`它是一个实例方法，我们将使用下面的笨拙语法。幸运的`sqrt`是，它是一个静态方法，所以我们不必在实际程序中这样做。**

```java
Math m = new Math();
x = m.sqrt(100);
```

因此，拥有一个同时具有实例方法和静态方法的类是有意义的。例如，假设想要比较两只狗的重量。一种方法是添加一个静态方法来比较 Dogs。

```java
/** Dog是该类方法的返回值 maxDog是方法名*/
public static Dog maxDog(Dog d1, Dog d2) {
    if (d1.weightInPounds > d2.weightInPounds) {
        return d1;
    }
    return d2;
}
```

例如，可以通过以下方式调用此方法：

```java
Dog d1 = new Dog(15);
Dog d2 = new Dog(100);
Dog.maxDog(d1, d2);
```

注意我们使用类名调用，因为这个方法是一个静态方法。

我们也可以实现`maxDog`为非静态方法，例如

```java
/** Dog是该类方法的返回值 maxDog是方法名*/
public Dog maxDog(Dog d2) {
    if (this.weightInPounds > d2.weightInPounds) {
        return this;
    }
    return d2;
}
```

**在这种情况下，我们使用关键字`this`来引用当前对象（因为是non-static，我们需要一个实例来调用这个方法，this就是用来获得这个实例本身的，如下面的d1）**。例如，可以通过以下方式调用此方法：

```java
Dog d1 = new Dog(15);
Dog d2 = new Dog(100);
d1.maxDog(d2);
```

在这里，我们使用特定的实例变量调用该方法。



**练习 1.2.1**：下面的方法会做什么？如果您不确定，请尝试一下。

```java
public static Dog maxDog(Dog d1, Dog d2) {
    if (weightInPounds > d2.weightInPounds) {
        return this;
    }
    return d2;
}

/**
很奇怪哦，我猜测默认第一个参数为this？
所以等效于？
    if (d1.weightInPounds > d2.weightInPounds) {
        return d1;
    }
    return d2;
}
*/

// 测试一下，首先写一个Dog.java
class Dog{
    public int weightInPounds;
    public Dog(int w){
        weightInPounds = w;
    }
    public static Dog maxDog(Dog d1, Dog d2){
        if(weightInPounds > d2.weightInPounds){
            return this;
        }
        return d2;
    }
}

// 然后是DogLaunch.java
public class Doglauncher{
    public static void main(String[] args){
        Dog d1 = new Dog(20);
        Dog d2 = new Dog(30);
        Dog.maxDog(d1, d2);
    }
}

// 然后直接编译解释DogLauncher.java即可
// 因为DogLauncher.java会聪明的找到Dog.java并且编译它。

// 报错
[msun@ceph57 ~/CS61B]$ javac DogLauncher.java
./Dog.java:7: error: non-static variable weightInPounds cannot be referenced from a static context
        if(weightInPounds > d2.weightInPounds){
           ^
./Dog.java:8: error: non-static variable this cannot be referenced from a static context
            return this;
                   ^
2 errors
                       
// 我想到的解决办法是将Dog.java 修改
//   将 public static Dog maxDog(Dog d1, Dog d2)
// 改为 public Dog maxDog(Dog d1, Dog d2)
// 这样，weightInPounds就可以被识别为调用这个非静态方法的实例的weight值
// 在这种情况下，d1在这里实际上是被忽略的
class Dog{
    public int weightInPounds;
    public Dog(int w){
        weightInPounds = w;
    }
    public Dog maxDog(Dog d1, Dog d2){
        if(weightInPounds > d2.weightInPounds){
            return this;//this拿到实例，而不是拿到参数d1或者d2
        }
        return d2;
    }
}          
```

#### 静态变量

类具有静态变量有时很有用。这些是类本身而不是实例固有的属性。例如，我们可以记录 Dogs 的学名（或 binomen）是“Canis familiaris”：

```java
public class Dog {
    public int weightInPounds;
    public static String binomen = "Canis familiaris";
    ...
}
```

静态变量应该使用类的名称而不是特定的实例来访问，例如你应该使用`Dog.binomen`，而不是`d.binomen`。

虽然 Java 在技术上允许您使用实例名称访问静态变量，但它的风格很差，令人困惑，并且在我看来是 Java 设计人员的错误。

**练习 1.2.2**：完成这个练习：

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0111exercise122.png)

- 视频：[链接](https://youtu.be/8Gq-8mVbyFU)
- 幻灯片：[链接](https://docs.google.com/presentation/d/10BFLHH8VaoYy7XaazwjaoTtLw3zvasX4HCssDruqw84/edit#slide=id.g6caa9a6fe_057)
- 解决方案视频：[链接](https://youtu.be/Osuy8UEH03M)

```bash
# 很明显是
# manyDogs[3]没东西 NullPointerException
```

**发现好东西了。Won’t go over in live lecture. Use the visualizer to see the solution [at this link](http://goo.gl/HLzN6s).Or if you’re watching this video and can’t find the slides, the link is: http://goo.gl/HLzN6s**

### public static void main(String[] args)

到目前为止，我们已经了解到，是时候揭开我们一直用于 main 方法的声明的神秘面纱了。把它分解成碎片，我们有：

- `public`: 到目前为止，我们所有的方法都是以 this 关键字开头的。
- `static`：它是一个静态方法，不与任何特定实例相关联。
- `void`: 它没有返回类型。
- `main`: 这是方法的名称。
- `String[] args`: 这是传递给 main 方法的参数。

#### 命令行参数

**[I can't explain better than him](https://www.youtube.com/watch?v=1ViQUHlRoyM)**

由于 main 由 Java 解释器本身而不是另一个 Java 类调用，因此提供这些参数是解释器的工作。它们通常指的是命令行参数。例如，考虑`ArgsDemo`下面的程序：

```java
public class ArgsDemo {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

该程序打印出第 0 个命令行参数，例如

```
$ java ArgsDemo these are command line arguments
these
```

在上面的示例中，`args`将是一个字符串数组，其中条目是 {"these", "are", "command", "line", "arguments"}。

#### 求和命令行参数

**练习 1.2.3**：尝试编写一个summing命令行参数的程序，假设它们是数字。有关解决方案，请参阅webcast或 GitHub 上提供的代码。

```java
public class SumArgs{
    public static void main(String[] args){
        int len = args.length;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += Integer.parseInt(args[i]);
        }
        return sum;
    }
}

[msun@ceph57 ~/CS61B]$ javac SumArgs.java
[msun@ceph57 ~/CS61B]$ java SumArgs 1 2 4 
Error: Main method must return a value of type void in class SumArgs, please 
define the main method as:
   public static void main(String[] args)

//修改一下
public class SumArgs{
    public static void main(String[] args){
        int len = args.length;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += Integer.parseInt(args[i]);
        }
        System.out.println(sum);
    }
}

[msun@ceph57 ~/CS61B]$ javac SumArgs.java
[msun@ceph57 ~/CS61B]$ java SumArgs 1 2 4
7
```

### 使用库

作为程序员最重要的技能之一是知道如何查找和使用现有的库。在辉煌的现代时代，通过网络寻求帮助通常可以为自己节省大量的工作和调试。

在本课程中，欢迎您这样做，但需要注意以下几点：

- 不要使用我们不提供的库。
- 引用你的消息来源。
- 不要为特定的家庭作业或项目问题寻找解决方案。

例如，搜索“convert String integer Java”就可以了。但是，搜索“nbody project berkeley”是不行的。

有关协作和学术诚信政策的更多信息，请参阅课程大纲。

#### 接下来是什么

- [项目 0](http://sp19.datastructur.es/materials/proj/proj0/proj0)
- [讨论2](http://sp19.datastructur.es/materials/discussion/disc02.pdf)







