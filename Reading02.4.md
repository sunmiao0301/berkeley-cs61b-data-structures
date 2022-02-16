## Reading 2.4 数组

到目前为止，我们已经了解了如何利用递归类定义来创建可扩展的列表类，包括`IntList`、`SLList`和`DLList`. 在本书接下来的两节中，我们将讨论如何使用数组构建列表类。

本书的这一部分假定您已经使用过数组，并不打算成为其语法的全面指南。

## 数组基础

为了最终构建一个可以保存信息的列表，我们需要一些方法来获取记忆框。之前，我们看到了如何获得带有变量声明和类实例化的内存盒。例如：

- `int x;`给了我们一个 32 位的存储 int 的内存盒。
- `Walrus w1;` 此时，只声明不赋值，那么Java会给我们一个 64 位的内存盒来存储海象的引用（location）。
- `Walrus w2 = new Walrus(30, 5.6);`给我们总共 3 个内存盒。一个存储 Walrus 引用的 64 位框，一个存储海象的 int 大小的 32 位框，以及一个存储海象的双 tuskSize 的 64 位框。

数组是一种特殊类型的对象，由一系列编号的内存盒组成。这与具有命名内存盒的类实例不同。要获取数组的第 i 个元素，我们使用在 HW0 和项目 0 中看到的括号表示法，例如`A[i]`获取`i`A 的第 th 个元素。

数组包括：

- 一个固定的整数长度，N
- 一个由 N 个记忆框组成的序列（N = 长度），**其中所有的框都属于同一类型**，编号从 0 到 N - 1。

与类不同，数组没有方法。

### 数组创建

**[I can't explain better than him](https://youtu.be/nToxEh2PO48)**

数组创建有三种有效的表示法。尝试运行下面的代码，看看会发生什么。单击[此处](http://pythontutor.com/iframe-embed.html#code=public class ArrayCreationDemo {  public static void main(String[] args) {    int[] x%3B    int[] y%3B    x %3D new int[3]%3B    y %3D new int[]{1, 2, 3, 4, 5}%3B    int[] z %3D {9, 10, 11, 12, 13}%3B } }&codeDivHeight=400&codeDivWidth=350&cumulative=false&curInstr=0&heapPrimitives=false&origin=opt-frontend.js&py=java&rawInputLstJSON=[]&textReferences=false)查看交互式可视化。

- `x = new int[3];`
- `y = new int[]{1, 2, 3, 4, 5};`
- `int[] z = {9, 10, 11, 12, 13};`

所有三个符号都创建一个数组。

第一个表示法，用于创建`x`，将创建一个指定长度的数组，并用默认值填充每个内存框。在这种情况下，它将创建一个长度为 3 的数组，并用默认`int`值填充 3 个框`0`。

第二个表示法，用于创建`y`，创建一个具有容纳指定起始值所需的确切大小的数组。在这种情况下，它使用这五个特定元素创建一个长度为 5 的数组。

第三种表示法，用于声明**和**创建`z`，与第二种表示法具有相同的行为。唯一的区别是它省略了 `new`的使用，并且只能在与变量声明结合使用时使用。

这些符号中没有一个比其他符号更好，都是一样的。

### 数组访问和修改

以下代码展示了我们将用于处理数组的所有关键语法。尝试单步执行下面的代码，并确保您了解每行执行时会发生什么。为此，请单击[此处](https://goo.gl/bertuh)查看交互式可视化。除了最后一行代码，我们之前已经看过所有这些语法。

```java
int[] z = null;
int[] x, y;

x = new int[]{1, 2, 3, 4, 5};
y = x;
x = new int[]{-1, 2, 5, 4, 99};
y = new int[3];
z = new int[0];
int xL = x.length;

String[] s = new String[6];
s[4] = "ketchup";
s[x[3] - x[1]] = "muffins";

int[] b = {9, 10, 11};
System.arraycopy(b, 0, x, 3, 2);
```

最后一行演示了一种将信息从一个数组复制到另一个数组的方法。`System.arraycopy`接受五个参数：

- 用作源的数组
- 在源数组中从哪里开始
- 用作目标的数组
- 在目标数组中从哪里开始
- 要复制多少项目

对于 Python 的老手，`System.arraycopy(b, 0, x, 3, 2)`来说，相当于在 Python 中使用`x[3:5] = b[0:2]`

复制数组的另一种方法是使用循环。`arraycopy`通常比循环快，并产生更紧凑的代码。唯一的缺点是`arraycopy`（可以说）更难阅读。**请注意，Java 数组仅在运行时执行边界检查。也就是说，下面的代码编译得很好，但会在运行时崩溃。**

```java
int[] x = {9, 10, 11, 12, 13};
int[] y = new int[2];
int i = 0;
while (i < x.length) {
    y[i] = x[i];
    i += 1;
}
```

[尝试在 java 文件或可视化](https://goo.gl/YHufJ6)工具中本地运行此代码。崩溃时遇到的错误的名称是什么？错误的名称是否有意义？

```java
//报错为
java.lang.ArrayIndexOutOfBoundsException: 2
//错误名称有意义，因为确实是数组y在2处超界
```

### Java中的二维数组

在 Java 中人们可能称之为二维数组实际上只是一个数组数组。它们遵循我们已经学习过的对象的相同规则，但让我们回顾一下它们以确保我们了解它们是如何工作的。

数组数组的语法可能有点混乱。考虑代码`int[][] bamboozle = new int[4][]`。这将创建一个名为`bamboozle`的整数数组数组。具体来说，这恰好创建了四个内存盒，每个内存盒都可以指向一个整数数组（长度未指定）。

试着逐行运行下面的代码，看看结果是否符合你的直觉。如需交互式可视化，请单击[此处](http://goo.gl/VS4cOK)。

```java
// 我的直觉如下,不能确定的点主要在于不知道数组具体长度的时候，是null吧?
int[][] pascalsTriangle;
pascalsTriangle = new int[4][];
/**
{{null},
{null}.
{null}.
{null}}
*/
int[] rowZero = pascalsTriangle[0];
// rowZero = {null} 

pascalsTriangle[0] = new int[]{1};
/**
pascalsTriangle[0] = {1}
rowZero = {1}
*/
pascalsTriangle[1] = new int[]{1, 1};
//pascalsTriangle[1] = {1， 1}
pascalsTriangle[2] = new int[]{1, 2, 1};
//pascalsTriangle[2] = {1，2, 1}
pascalsTriangle[3] = new int[]{1, 3, 3, 1};
//pascalsTriangle[3] = {1，3, 3, 1}
int[] rowTwo = pascalsTriangle[2];
//rowZero = {1, 2, 1}
rowTwo[1] = -5;
//rowZero = {1， -5， 1}

int[][] matrix;
matrix = new int[4][];
/**
{{null},
{null},
{null},
{null}}
*/
matrix = new int[4][4];
/**
{{0, 0, 0, 0},
{0, 0, 0, 0},
{0, 0, 0, 0},
{0, 0, 0, 0}}
*/
int[][] pascalAgain = new int[][]{{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}};
/**
{{1},
{1, 1},
{1, 2, 1}, 
{1, 3, 3, 1}}
*/
```

##### 交互可视化得到的真正结果如下，其中需要注意的地方有：

```java
public class ArrayBasics2 {
	public static void main(String[] args) {
		int[][] pascalsTriangle;
		pascalsTriangle = new int[4][];
		int[] rowZero = pascalsTriangle[0];
        /**
	    执行 int[] rowZero = pascalsTriangle[0]; 之后
	    rowZero作为数组（引用类型）并不是指向了oascalsTriangle的第一个数组，而是自己为null
	    我觉得原因就是因为此时pascalsTriangle[0]是null，所以无法复制数组地址而成为一个引用。
	    等同于int[] rowZero;
        */
		
		pascalsTriangle[0] = new int[]{1};
		pascalsTriangle[1] = new int[]{1, 1};
		pascalsTriangle[2] = new int[]{1, 2, 1};
		pascalsTriangle[3] = new int[]{1, 3, 3, 1};
		int[] rowTwo = pascalsTriangle[2];
        /**
        此时，结果和上面的 int[] rowZero = pascalsTriangle[0]; 不一样了
        因为此时 pascalsTriangle[2] 是有值的
        所以可以复制其地址成为一个引用
        */
		rowTwo[1] = -5;//由于上一步，所以这一步通过引用修改了pascalsTriangle[2]的值成为1, -5, 1

		int[][] matrix;
		matrix = new int[4][];
		matrix = new int[4][4]; 

		int[][] pascalAgain = new int[][]{{1}, {1, 1}, 
		                                  {1, 2, 1}, {1, 3, 3, 1}};
	}
} 
```

所以，关键的两步是：

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0127bcznullsocantrefer.png)



![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0127bcznotnullsocanrefer.png)

**练习 2.4.1：**运行下面的代码后，x/[0]/[0] 和 w/[0]/[0] 的值是多少？[单击此处](http://goo.gl/fCZ9Dr)检查您的工作。

```java
int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

int[][] z = new int[3][];
z[0] = x[0];
z[1] = x[1];
z[2] = x[2];
z[0][0] = -z[0][0];

int[][] w = new int[3][3];
System.arraycopy(x[0], 0, w[0], 0, 3);
System.arraycopy(x[1], 0, w[1], 0, 3);
System.arraycopy(x[2], 0, w[2], 0, 3);
w[0][0] = -w[0][0];
/**
我的猜测是：
x[0][0] = -1
w[0][0] = 1
因为z实际上是引用了x的地址，对z的修改实际上就是对x的修改。
但是arraycopy不是，是新建了一个数组w
*/
```

**实际运行的结果也符合我的猜测。**

### 数组与类

数组和类都可以用来组织一堆内存盒。在这两种情况下，内存盒的数量都是固定的，即不能改变数组的长度，就像不能添加或删除类字段一样。

数组和类中内存盒的主要区别：

- 数组框使用`[]`符号进行编号和访问，类框使用点符号命名和访问。
- 数组框必须都是相同的类型。类框可以是不同的类型。

**这些差异的一个特别显着的影响是`[]`符号允许我们在运行时指定我们想要的索引。例如，考虑下面的代码：**

```java
int indexOfInterest = askUserForInteger();
int[] x = {100, 101, 102, 103};
int k = x[indexOfInterest];
System.out.println(k);
```

**如果我们运行这段代码，我们可能会得到如下信息：**

```bash
$ javac arrayDemo
$ java arrayDemo
What index do you want? 2
102
```

**相比之下，在类中指定字段并不是我们在运行时所做的事情。例如，考虑下面的代码：**

```java
class Planet{
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p[fieldOfInterest];
}
```

**如果我们尝试编译它，我们会得到一个语法错误。**

```bash
$ javac classDemo
FieldDemo.java:5: error: array required, but Planet found
        double mass = earth[fieldOfInterest];        
                               ^
```

**如果我们尝试使用点表示法，也会出现同样的问题：**

```java
class Planet{
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p.fieldOfInterest;
}
```

**编译，我们会得到：**

```bash
$ javac classDemo
FieldDemo.java:5: error: cannot find symbol
        double mass = earth.fieldOfInterest;        
                           ^
  symbol:   variable fieldOfInterest
  location: variable earth of type Planet
```

**这不是您经常会遇到的问题，但值得指出因为这让你多学到一个知识点。值得一提的是，有一种方法可以在运行时指定所需的字段，称为*反射*，但对于典型程序来说，它被认为是非常糟糕的编码风格。[您可以在此处](https://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html)阅读有关反射的更多信息。你不应该在任何 61B 程序中使用反射，我们不会在我们的课程中讨论它。**

**通常，编程语言的部分设计目的是限制程序员的选择，以使代码更易于推理。通过将这些特性限制在特殊的反射 API 中，我们使典型的 Java 程序更易于阅读和解释。**

### 附录：Java 数组与其他语言

与其他语言中的数组相比，Java 数组：

- 没有“切片”的特殊语法（例如在 Python 中）。
- 不能收缩或扩展（例如在 Ruby 中）。
- 没有成员方法（例如在 Javascript 中）。
- 必须只包含相同类型的值（与 Python 不同）。