## Reading 2.1

### 列表

在项目 0 中，我们使用数组来跟踪 N 个对象在空间中的位置。我们无法轻易做到的一件事是在模拟开始后更改对象的数量。这是因为数组在 Java 中具有永远不会改变的固定大小。

另一种方法是使用列表类型。毫无疑问，您在过去的某个时候使用过列表数据结构。例如，在 Python 中：

```python
L = [3, 5, 6]
L.append(7)
```

**虽然 Java 确实有一个内置的 List 类型，但我们现在要避免使用它。在本章中，我们将从头开始构建自己的列表，同时学习 Java 的一些关键特性。**

### 海象之谜

在开始我们的旅程之前，我们将首先思考海象的深刻奥秘。

尝试预测当我们运行下面的代码时会发生什么。对 b 的更改会影响 a 吗？

```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;
System.out.println(a);
System.out.println(b);
// weight:5, tusk size:8.30
// weight:5, tusk size:8.30
```

现在尝试预测当我们运行下面的代码时会发生什么。对 x 的更改会影响 y 吗？

```java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);
System.out.println("y is: " + y);
// x is:2
// y is:5
```

答案可以在[这里](http://cscircles.cemc.uwaterloo.ca/java_visualize/#code=public+class+PollQuestions+{ +++public+static+void+main(String[]+args)+{ ++++++Walrus+a+%3D+new+Walrus(1000,+8.3)%3B ++++++Walrus+b%3B ++++++b+%3D+a%3B ++++++b.weight+%3D+5%3B ++++++System.out.println(a)%3B ++++++System.out.println(b)%3B++++++ ++++++int+x+%3D+5%3B ++++++int+y%3B ++++++y+%3D+x%3B ++++++x+%3D+2%3B ++++++System.out.println("x+is%3A+"+%2B+x)%3B ++++++System.out.println("y+is%3A+"+%2B+y)%3B++++++ +++} +++ +++public+static+class+Walrus+{ ++++++public+int+weight%3B ++++++public+double+tuskSize%3B ++++++ ++++++public+Walrus(int+w,+double+ts)+{ +++++++++weight+%3D+w%3B +++++++++tuskSize+%3D+ts%3B ++++++} ++++++public+String+toString()+{ +++++++++return+String.format("weight%3A+%d,+tusk+size%3A+%.2f",+weight,+tuskSize)%3B ++++++} +++} }&mode=edit)找到。我们在VSCode和java visualizer里面运行如下代码：

```java
public class PollQuestions {
    public static void main(String[] args) {
        Walrus a = new Walrus(1000, 8.3);       
        Walrus b;       
        b = a;       
        b.weight = 5;       
        System.out.println(a);       
        System.out.println(b);             
        int x = 5;       
        int y;       
        y = x;       
        x = 2;       
        System.out.println("x is: " + x);       
        System.out.println("y is: " + y);          
    }        
    public static class Walrus {       
        public int weight;       
        public double tuskSize;              
        public Walrus(int w, double ts) {          
            weight = w;          
            tuskSize = ts;       
        }       
        public String toString() {          
            return String.format("weight: %d, tusk size: %.2f", weight, tuskSize);
       }    
    } 
}
```

可以得到运行结果如下：

```bash
PS D:\repo\Berkeley-CS61B-Data-Structures\skeleton-sp19-mine\proj0>  & 'D:\JDK8\jdk1.8.0_311\bin\java.exe' '-cp' 'C:\Users\hfut_\AppData\Roaming\Code\User\workspaceStorage\dbf3fabab1e10ef5aba0a0cc9a52ca6b\redhat.java\jdt_ws\jdt.ls-java-project\bin' 'PollQuestions'
weight: 5, tusk size: 8.30
weight: 5, tusk size: 8.30
x is: 2
y is: 5
```

可以得到如下的可视化结果：

![image-20220118133430312](C:\Users\hfut_\AppData\Roaming\Typora\typora-user-images\image-20220118133430312.png)

可以看到对象赋值是指向同一个，基本数据类型赋值是产生一个新的。

**我认为这个涉及到Java中的数据类型分为基本数据类型，和引用数据类型。**

**虽然微妙，但作为海象之谜基础的关键思想对于我们将在本课程中实现的数据结构的效率非常重要，并且对这个问题的深刻理解也将导致更安全，更可靠的代码。**

### 位

计算机中的所有信息都以 1 和 0 的序列的形式存储在*内存*中。一些例子：

- 72 通常存储为 01001000
- 205.75 通常存储为 01000011 01001101 11000000 000000000
- 字母 H 通常存储为 01001000（与 72 相同）
- 真实值通常存储为00000001

在本课程中，我们不会花太多时间讨论特定的二进制表示，例如，为什么205.75被存储为上面看似随机的32位字符串。理解具体表示是[CS61C](http://www-inst.eecs.berkeley.edu/~cs61c/)的一个主题，这是61B的后续课程。

虽然我们不会学习二进制语言，但很高兴知道这就是引擎下发生的事情。

**一个有趣的观察结果是，72 和 H 都存储为 01001000。这就提出了一个问题：一段Java代码如何知道如何解释01001000？**

**答案是通过类型！例如，请考虑下面的代码：**

```java
char c = 'H';
int x = c;
System.out.println(c);
System.out.println(x);
```

如果我们运行此代码，我们得到：

```
H
72
```

在这种情况下，x 和 c 变量都包含相同的位（嗯，几乎...），但 Java 解释器在打印时会以不同的方式处理它们。

在 Java 中，有 8 种基元类型：字节byte、短short、整数int、长long、浮点float、双精度double、布尔值boolean和字符char。每个都有不同的属性，我们将在整个课程中讨论这些属性，除了 short 和 float，你可能永远不会使用它们。

### 声明变量（简化）

您可以将计算机视为包含大量用于存储信息的内存位，每个内存位都有一个唯一的地址。现代计算机可以使用数十亿个这样的位。

当你声明某种类型的变量时，Java会找到一个连续的块，该块具有恰好足够的位来保存该类型的事物。例如，如果声明一个 int，则会得到一个 32 位的块。如果声明一个字节，则会得到一个 8 位的块。Java 中的每种数据类型都包含不同数量的位。在这个课程中，确切的数字对我们来说并不是特别重要。

为了方便的比喻，我们将其中一个块称为位的"盒子"。

除了留出内存之外，Java 解释器还在内部表中创建一个条目，该条目将每个变量名称映射到框中第一位的位置。

例如，如果您声明了`int x` 和`double y` ，则 Java 可能会决定使用计算机内存的 352 到 384 位来存储 x，并使用位 20800 到 20864 来存储 y。然后，解释器将记录 int x 从位 352 开始，y 从位 20800 开始。例如，在执行如下代码后：

```java
int x;
double y;
```

我们最终会得到大小分别为 32 和 64 的盒子，如下图所示：

![x_and_y_empty_bitwise](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/x_and_y_empty_bitwise.png)

Java语言无法让您知道盒子的位置，例如，您无法以某种方式发现x位于位置352。换句话说，确切的内存地址低于我们在Java中可以访问的抽象级别。这与像C这样的语言不同，在C语言中，您可以要求语言提供一段数据的确切地址。因此，我省略了上图中的地址。

**Java的这个特性是一种权衡！对程序员隐藏内存位置会减少您的控制，从而阻止您进行某些[类型的优化](http://www.informit.com/articles/article.aspx?p=2246428&seqNum=5)。但是，它也避免了一[大类非常棘手的编程错误 — 使用指针直接访问和操作内存地址的优势和危害](http://www.informit.com/articles/article.aspx?p=2246428&seqNum=1)。在非常低成本计算的现代时代，这种权衡通常是值得的。正如睿智的唐纳德·高德纳（Donald Knuth）曾经说过的那样："我们应该忘记小效率，大约97%的时间：过早的优化是所有邪恶的根源"。**

```apl
为了澄清这些区别，请查看"JavaDude"Scott Stanchfield的指针和引用的这些定义：

- 指针的值通常是您感兴趣的数据的内存地址。某些语言允许您操作该地址;其他人则没有。
- "引用"是另一个变量的别名。对引用变量执行的任何操作都会直接更改原始变量。

指针和引用之间差异的涵盖范围超出了本文的讨论范围,上面的链接重点介绍指针算法 — C++允许，而 Java 和 .NET 不允许。
```

**打个比方，你不能直接控制你的心跳。虽然这限制了您在某些情况下进行优化的能力，但它也避免了发生愚蠢错误的可能性，例如意外将其关闭。**

声明变量时，Java 不会在保留框中写入任何内容。换句话说，没有默认值。因此，Java 编译器会阻止您在使用运算符填充框之前使用变量。直到使用`=`运算符填充了位框之后。出于这个原因，我避免在上图中的框中显示任何位。

为内存盒赋值时，它将填充您指定的位。例如，如果我们执行以下行：

```java
x = -1431195969;
y = 567213.112;
```

然后，上面的内存盒被填充，如下所示，我称之为**盒符号**。

![x_and_y_empty_filled.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/x_and_y_empty_filled.png)

顶部位表示 -1431195969，底部位表示 567213.112。为什么这些特定的位序列表示这两个数字并不重要，这是CS61C中涵盖的主题。但是，如果您很好奇，请参阅维基百科上[的整数表示](https://en.wikipedia.org/wiki/Two's_complement)和[双重表示](https://en.wikipedia.org/wiki/IEEE_floating_point)。

注意：内存分配实际上比此处描述的要复杂一些，并且是 CS 61C 的主题。但是，对于我们在61B中的目的，这个模型已经足够接近现实了。

### 简化框符号

虽然我们在上一节中使用的框符号非常有助于大致了解幕后发生的事情，但它对于实际目的没有用，因为我们不知道如何解释二进制位。

因此，我们将用人类可读的符号来代替以二进制形式编写内存盒内容。我们将在接下来的课程中这样做。例如，执行后：

```java
int x;
double y;
x = -1431195969;
y = 567213.112;
```

我们可以使用我所说的**简化框表示法**来表示程序环境，如下所示：

![x_and_y_simplified_box_notation.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/x_and_y_simplified_box_notation.png)



### 平等的黄金法则（GRoE）

现在有了简化的方框符号，我们终于可以开始解开海象之谜了。

事实证明，我们的 Mystery 有一个简单的解决方案：**当您编写 时`y = x`，您是在告诉 Java 解释器将位从 x 复制到 y。**在理解我们的海象之谜时，这条平等的黄金法则 (GRoE) 是所有真理的根源。

```java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);
System.out.println("y is: " + y);
```

这种复制位的简单想法对于`=`在 Java 中使用的任何赋值都是正确的。要查看实际情况，请单击[此链接](http://cscircles.cemc.uwaterloo.ca/java_visualize/#code=public+class+PollQuestions+{ +++public+static+void+main(String[]+args)+{ ++++++int+x+%3D+5%3B ++++++int+y%3B ++++++y+%3D+x%3B ++++++x+%3D+2%3B ++++++System.out.println("x+is%3A+"+%2B+x)%3B ++++++System.out.println("y+is%3A+"+%2B+y)%3B++++++ +++} }&mode=display&curInstr=0)。

### 参考类型

**上面，我们说有 8 种基本类型：byte、short、int、long、float、double、boolean、char。其他一切，包括数组，都不是原始类型，而是`reference type`.**

**正如我上面所说，海象之谜涉及到Java中的数据类型分为基本数据类型，和引用数据类型。**

### 对象实例化

当我们使用（例如 Dog、Walrus、Planet）*实例化*一个对象时`new`，Java 首先为类的每个实例变量分配一个框，并用默认值填充它们。然后构造函数通常（但不总是）用其他值填充每个框。

例如，如果我们的海象类是：

```java
public static class Walrus {
    public int weight;
    public double tuskSize;

    public Walrus(int w, double ts) {
          weight = w;
          tuskSize = ts;
    }
}
```

我们使用 来创建一个海象`new Walrus(1000, 8.3);`，然后我们最终得到一个海象，它分别由两个 32 位和 64 位的框组成：

![匿名海象.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/anonymous_walrus.png)

在 Java 编程语言的实际实现中，任何对象实际上都存在一些额外的开销，因此 Walrus 占用的数据量略高于 96 位。但是，出于我们的目的，我们将忽略此类开销，因为我们永远不会直接与之交互。

**我们创建的海象是匿名的，因为它已经被创建`new Walrus(1000, 8.3)`，但它没有存储在任何变量中。现在让我们转向存储对象的变量。**

### 引用变量声明

**当我们*声明*任何引用类型（Walrus、Dog、Planet、数组等）的变量时，Java 会分配一个 64 位的盒子，无论是什么类型的对象。**

**乍一看，这似乎导致了海象悖论。上一节中的海象需要超过 64 位来存储。此外，无论对象的类型如何，我们都只能用 64 位来存储它，这似乎很奇怪。**

**但是，通过以下信息可以轻松解决此问题：64 位框不包含有关海象的数据，而是包含海象在内存中的地址。**

例如，假设我们调用：

```java
Walrus someWalrus;// 创建一个64位盒子 用来存储海象在内存中的地址
someWalrus = new Walrus(1000, 8.3);// 创建一个32+64=96位盒子，用来存储海象的数据，并且把地址传给上面的64位地址盒子。
```

第一行创建一个 64 位的盒子。第二行创建一个新的海象，地址由`new`操作员返回。`someWalrus`然后根据 GRoE将这些位复制到框中。

如果我们想象我们的海象重量从内存位`5051956592385990207`开始存储，而 tuskSize 从位开始`5051956592385990239`，我们可能会存储`5051956592385990207`在海象变量中。在二进制中，`5051956592385990207`用 64 位表示`0100011000011100001001111100000100011101110111000001111000111111`，用方框表示法表示：

![someWalrus_bit_notation.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/someWalrus_bit_notation.png)

我们还可以将特殊值分配`null`给一个引用变量，对应于全零。

![someWalrus_bit_notation_null.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/someWalrus_bit_notation_null.png)

### 方框和指针表示法

和以前一样，很难解释引用变量中的一堆位，因此我们将为引用变量创建一个简化的框表示法，如下所示：

- 如果地址全为零，我们将用 null 表示它。
- 非零地址将由指向对象实例的**箭头表示。**

这有时也称为“框和指针”表示法。

对于上一节中的示例，我们将有：

![someWalrus_simplified_bit_notation.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/someWalrus_simplified_bit_notation.png)

![someWalrus_simplified_bit_notation_null.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/someWalrus_simplified_bit_notation_null.png)

### 解开海象之谜

我们现在终于准备好完全彻底地解开海象之谜了。

```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
```

第一行执行后，我们有：

![神秘的_of_the_walrus_resolved_step1.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/mystery_of_the_walrus_resolved_step1.png)

执行第二行后，我们有：

![神秘的_of_the_walrus_resolved_step2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/mystery_of_the_walrus_resolved_step2.png)

**请注意，上面的 b 是未定义的，而不是 null。**

**根据 GRoE，最后一行`b=a`只是将`a`框中的位复制到`b`框中。**或者就我们的视觉隐喻而言，这意味着 b 将精确复制 a 中的箭头，现在显示一个指向同一对象的箭头。

![神秘的_of_the_walrus_resolved_step3.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/mystery_of_the_walrus_resolved_step3.png)

就是这样。没有比这更复杂的了。

### 参数传递

**当您将参数传递给函数时，您也只是在复制位。换句话说，GRoE 也适用于参数传递。**复制位通常称为“按值传递”。在 Java 中，我们**总是**按值传递。

假设我们调用这个函数，如下所示：

```java
public static void main(String[] args) {
    double x = 5.5;
    double y = 10.5;
    double avg = average(x, y);
}
public static double average(double a, double b) {
    return (a + b) / 2;
}
```

执行此函数的前两行后，main 方法将有两个框标记`x`并`y`包含如下所示的值：

![main_x_y.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/main_x_y.png)

当函数被调用时，`average`函数有自己的作用域，也就是创建了两个新盒子用来装`a`和`b`，位被简单地*复制*进来。这种位的复制就是我们所说的“按值传递”时所指的内容，如下，是两个新盒子。

![average_a_b.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig21/average_a_b.png)

如果`average`函数要更改`a`，那么在main函数中的`x`将保持不变，因为 GRoE 告诉我们，我们只需修改average创建的新的盒子`a`即可。

### 测试你的理解

**练习 2.1.1**：假设我们有以下代码：

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```

`doStuff`对 海象 和 x 有影响吗？提示：我们只需要知道 GRoE 就可以解决这个问题。

```bash
# 根据上面课程的介绍，我觉得：
# 对walrus重量是有影响的，因为参数新建的还是等于原来的海象的地址，所以本质上是指向同一只海象，修改W和修改walrus是一样的。
# 对x没有影响，参数本质上是新建了一个x。
```

详细情况见[visualizer](http://goo.gl/ngsxkq)

### 数组的实例化

**如上所述，存储数组的变量和其他变量一样是引用变量。(int[] array = new int[5];)**

例如，考虑以下声明：

```java
int[] x;
Planet[] planets;
```

这两个声明都创建了 64 位的内存盒。`x`只能保存`int`数组的地址，`planets`也只能保存`Planet`数组的地址。因为是引用类型。

实例化数组与实例化对象非常相似。例如，如果我们创建一个大小为 5 的整数数组，如下所示：

```
x = new int[]{0, 1, 2, 95, 4};
```

**然后`new`关键字创建 5 个 32 位的盒子，并返回整个对象的地址以分配给 x。**

如果丢失与地址对应的位，则可能会丢失对象。例如，如果某个特定海象的地址的唯一副本存储在 中`x`，那么`x = null`将导致您永久丢失该海象。这不一定是件坏事，因为您经常会认为您已经完成了一个对象，因此简单地丢弃引用是安全的。我们将在本章后面构建列表时看到这一点。

### 破蒲团法则

您可能会问自己，为什么我们要花费如此多的时间和篇幅来讨论看似微不足道的事情。如果您以前有 Java 经验，这可能尤其如此。**原因是学生很容易对这个问题有一个半吊子的理解，允许他们编写代码，但对正在发生的事情没有真正的理解。**

虽然这在短期内可能很好，但从长远来看，在没有完全理解的情况下做问题可能会注定你以后会失败。有一篇关于这个所谓[的破蒲团法则的](https://mathwithbaddrawings.com/2015/04/08/the-math-ceiling-wheres-your-cognitive-breaking-point/)博客文章，你可能会觉得有趣。

*这段话说的太对了！！！事实上，当你深入一门语言的时候，根基不牢导致你没法深入。*

### 整数列表

现在我们已经真正了解了海象之谜，我们准备构建自己的列表类。

事实证明，一个非常基本的列表很容易实现，如下所示：

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}
```

您可能还记得 61a 中的类似内容，称为“链接列表”。

这样的列表很难使用。例如，如果我们想要列出数字 5、10 和 15，我们可以这样做：

```java
IntList L = new IntList(5, null);
L.rest = new IntList(10, null);
L.rest.rest = new IntList(15, null);
```

或者，我们可以向后构建我们的列表，产生更好但更难理解的代码：

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
```

虽然您原则上可以使用 IntList 来存储任何整数列表，但生成的代码会相当难看并且容易出错。我们将采用通常的面向对象编程策略，在我们的类中添加辅助方法来执行基本任务。

### 大小和迭代大小

我们想在类`IntList`中添加一个方法`size`，这样如果你调用`L.size()`，就可以返回其长度`L`.

在阅读本章的其余部分之前，请考虑编写`size`和`iterativeSize`方法。前者应该使用递归，后者则不应该。在看到我如何做之前，您可能会通过自己尝试了解更多信息。

```java
// 我写的版本如下
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
    public static int size(IntList r, int L){
		//递归法
        //参数为整数链表的头节点r(rest_head)和整数0(L = 0)
        if(r == null)
            return L;
       	else
            return size(r.rest, L + 1);
    }
    public static int iterativeSize(IntList r){
        //迭代法
        //参数为整数链表的头节点，返回一个整数值为其长度
        int L = 0;
        while(r != null){
            L++;
            r = r.rest;
        }
        return L;
    }
}
```

老师给出的答案是：**(老师用的是类方法)**

关于递归代码要记住的关键是您需要一个基本案例。在这种情况下，最合理的基本情况是 rest 是null，这会导致大小为 1 的列表。

练习：你可能想知道为什么我们不做类似if (this == null) return 0;. 为什么这行不通？

答案：想想当你调用 size 时会发生什么。您在一个对象上调用它，例如 L.size()。如果 L 为空，那么您将收到 NullPointer 错误！

```java
/** Return the size of the list using... recursion! */
public int size() {
    if (rest == null) {
        return 1;
    }
    return 1 + this.rest.size();
}
```

我的iterativeSize方法如下图。我建议在编写迭代数据结构代码时使用该名称p来提醒自己该变量持有一个指针。您需要该指针，因为您无法在 Java 中重新分配“this”。[这篇 Stack Overflow Post](https://stackoverflow.com/questions/23021377/reassign-this-in-java-class)中的后续内容简要解释了原因。

```java
/** Return the size of the list using no recursion! */
public int iterativeSize() {
    IntList p = this;
    int totalSize = 0;
    while (p != null) {
        totalSize += 1;
        p = p.rest;
    }
    return totalSize;
}
```

### get

虽然该`size`方法可以让我们获取列表的大小，但我们没有简单的方法来获取列表的第 i 个元素。

练习：编写一个`get(int i)`返回列表第 i 项的方法。例如，如果`L`是 5 -> 10 -> 15，那么`L.get(0)`应该返回 5，`L.get(1)`应该返回 10，`L.get(2)`应该返回 15。不管你的代码对于 invalid 的行为如何`i`，无论是太大还是太小。

有关解决方案，请参阅上面的讲座视频或 LectureCode 存储库。

请注意，我们编写的方法需要线性时间！也就是说，如果您有一个包含 1,000,000 个项目的列表，那么获取最后一个项目将花费比我们有一个小列表时更长的时间。我们将在以后的讲座中看到另一种实现列表的方法来避免这个问题。

```java
public int get(int i){
    IntList p = this;
    int index = 0;
    while(index != i){
        p = p.rest;
    }
    return p.first;
}
```

老师的解法是：（递归，看起来老师对迭代法有很深的偏见，不知为何）

```java
/** Returns the ith item of this IntList. */
public int get(int i) {
	if (i == 0) {
		return first;
	}
	return rest.get(i - 1);
}
```

#### What Next

- [Lab setup](http://sp19.datastructur.es/materials/lab/lab2setup/lab2setup)
- [Lab 2](http://sp19.datastructur.es/materials/lab/lab2/lab2)









































