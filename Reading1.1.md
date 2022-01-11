## Reading 1.1

### Hello World

Author: Josh Hug

让我们看看我们的第一个 Java 程序。运行时，下面的程序会打印“Hello world！” 到屏幕。

```java
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("hello world");
	}
}

/*
1 该程序由一个类声明组成，它使用关键字声明public class。在 Java 中，所有代码都存在于类中。
2 运行的代码位于一个名为 的方法中main，该方法声明为public static void main(String[] args)。
3 我们使用花括号{和}来表示一段代码的开始和结束。
4 语句必须以分号结尾。
*/
```

*这不是一本 Java 教科书，因此我们不会详细讨论 Java 语法。如果您需要参考，请考虑 Paul Hilfinger 的免费电子书[A Java Reference](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book1/java.pdf)，或者如果您需要更传统的书籍，请考虑 Kathy Sierra 和 Bert Bates 的[Head First Java](http://www.headfirstlabs.com/books/hfjava/)。*

### 运行Java程序

执行 Java 程序的最常见方法是通过两个程序的序列来运行它

第一个是 Java 编译器`javac`. 

第二个是 Java 解释器`java`.

![](https://joshhug.gitbooks.io/hug61b/content/assets/compilation_figure.svg)

**练习 1.1.1。**在您的计算机上创建一个名为 HelloWorld.java 的文件，然后从上面复制并粘贴确切的程序。试试这个`javac HelloWorld.java`命令。看起来就像什么都没发生一样。

但是，如果您查看目录，您会看到创建了一个名为 HelloWorld.class 的新文件。我们稍后会讨论这是什么。

现在尝试输入命令`java HelloWorld`。您应该会看到“Hello World！” 打印在您的终端中。

*纯娱乐。*尝试使用 Notepad、TextEdit、Sublime、vim 或任何你喜欢的文本编辑器打开 HelloWorld.class。你会看到很多只有 Java 解释器才会喜欢的疯狂垃圾。这是[Java 字节码](https://en.wikipedia.org/wiki/Java_bytecode)，我们不会在课程中讨论。

```bash
[msun@ceph57 ~/CS61B]$ ls
HelloWorld.java
[msun@ceph57 ~/CS61B]$ javac HelloWorld.java
[msun@ceph57 ~/CS61B]$ ls
HelloWorld.class  HelloWorld.java
[msun@ceph57 ~/CS61B]$ java HelloWorld
Hello World!

# 您可能会注意到我们在编译时包含“.java”，但在解释时我们不包含“.class”。这就是它的方式（TIJTWII）。

# 纯娱乐。尝试使用 Notepad、TextEdit、Sublime、vim 或任何你喜欢的文本编辑器打开 HelloWorld.class。你会看到很多只有 Java 解释器才会喜欢的疯狂垃圾。这是Java 字节码，我们不会在课程中讨论。

# 尝试在vscode打开 提示
The file is not displayed in the editor because it is either binary or uses an unsupported text encodin. Do you want to open it anyway?
```

### 变量和循环

```java
public class HelloNumbers{
    public static void main(String[] args){
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
    }
}
```

当我们运行这个程序时，我们看到：

```bash
[msun@ceph57 ~/CS61B]$ javac HelloNumbers.java
[msun@ceph57 ~/CS61B]$ java HelloNumbers
0 1 2 3 4 5 6 7 8 9 

# 我们的变量 x 必须在使用前声明，并且必须给它一个类型！
```

**练习 1.1.2。**修改`HelloNumbers`它以打印出从 0 到 9 的整数的累积和。例如，您的输出应以 0 1 3 6 10... 开头并以 45 结尾。

此外，如果您有审美问题，请修改程序，使其在末尾打印出一个新行。

```java
public class HelloNumbers{
    public static void main(String[] args){
        int x = 0;
        int sum = 0;
        while (x < 10) {
            System.out.print(sum + " ");
            x = x + 1;
            sum += x;
        }
        System.out.println();
    }
}
```

```bash
[msun@ceph57 ~/CS61B]$ javac HelloNumbers.java
[msun@ceph57 ~/CS61B]$ java HelloNumbers
0 1 3 6 10 15 21 28 36 45 
[msun@ceph57 ~/CS61B]$
```

### 等级范围

本课程的作业使用名为[gradescope](http://www.gradescope.com/)的网站进行[评分](http://www.gradescope.com/)。如果您正在参加本课程附带的加州大学课程，您将使用它来提交您的作业以获得成绩。如果您只是为了好玩，欢迎您使用 Gradescope 来检查您的工作。要注册，请使用入口代码 93PK75。有关 Gradescope 以及如何提交作品的更多信息，请参阅[Gradescope 指南（稍后提供链接）](https://joshhug.gitbooks.io/hug61b/content/chap1/TBA)。

### 静态打字

Java 最重要的特性之一是所有变量和表达式都有一个所谓的`static type`. Java 变量可以包含该类型的值，并且只能包含该类型。此外，变量的类型永远不会改变。

Java 编译器的关键特性之一是它执行静态类型检查。例如，假设我们有以下程序：

```java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
        x = "horse";
    }
}
```

编译这个程序，我们看到：

```bash
# 注意，在javac阶段就直接报错了
$ javac HelloNumbers.java 
HelloNumbers.java:9: error: incompatible types: String cannot be converted to int
        x = "horse";
                ^
1 error
```

编译器甚至在它运行之前就拒绝了这个程序。这很重要，因为这意味着世界上运行这个程序的人不可能遇到类型错误！

这与 Python 等动态类型语言形成鲜明对比，后者在执行过程中用户可能会遇到类型错误！

除了提供额外的错误检查之外，**静态类型还让程序员确切地知道他或她正在使用哪种对象。我们将在接下来的几周内看到这有多么重要。这是我个人最喜欢的 Java 特性之一。**

总而言之，静态类型有以下优点：

- 编译器确保所有类型都兼容，使程序员更容易调试他们的代码。
- 由于代码保证没有类型错误，因此编译程序的用户将永远不会遇到类型错误。**例如，Android 应用程序是用 Java 编写的，并且通常仅作为 .class 文件分发，即以编译格式。因此，此类应用程序永远不会因为类型错误而崩溃，因为它们已经被编译器检查过了。(有类型错误的.java文件根本就无法生成.class文件)**
- 每个变量、参数和函数都有一个声明的类型，使程序员更容易理解和推理代码。

然而，我们会看到静态类型有一些缺点，将在后面的章节中讨论。

**额外的思考练习**

在 Java 中，我们可以说`System.out.println(5 + " ");`. 但是在 Python 中，我们不能说`print(5 + "horse")`，就像我们在上面看到的那样。为什么呢？

考虑以下两个 Java 语句：

```java
String h = 5 + "horse";
```

和

```java
int h = 5 + "horse";
```

第一个将成功；

第二个将给出编译器错误。

由于Java是强类型的，如果你告诉它`h`是一个字符串，它可以连接元素并给你一个字符串。但是当`h`是`int`时，它不能连接一个数字和一个字符串并给你一个数字。

Python 不限制类型，也无法假设你想要什么类型。被`x = 5 + "horse"`认为是多少？一个字符串？Python不知道。所以它会出错。

在这种情况下，`System.out.println(5 + "horse");`Java 将参数解释为字符串连接，并打印出“5horse”作为结果。或者，更有用的是，`System.out.println(5 + " ");`将在“5”之后打印一个空格。

那么`System.out.println(5 + "10");`打印？510，还是15？怎么样`System.out.println(5 + 10);`？

```bash
# 我认为是510
# 我认为是15
```

```java
public class HelloNumbers{
    public static void main(String[] args){
        System.out.println(5 + "10");
        System.out.println(5 + 10);
    }
}
```

```bash
[msun@ceph57 ~/CS61B]$ javac HelloNumbers.java
[msun@ceph57 ~/CS61B]$ java HelloNumbers
510
15
```

### 在 Java 中定义函数

在像 Python 这样的语言中，函数可以在任何地方声明，甚至可以在函数之外。例如，下面的代码声明了一个函数，它返回两个参数中较大的一个，然后使用这个函数计算并打印数字 8 和 10 中的较大者：

```python
def larger(x, y):
    if x > y:
        return x
    return y

print(larger(8, 10))
```

**由于所有 Java 代码都是类的一部分，因此我们必须定义函数以便它们属于某个类。**作为类的一部分的函数通常称为“方法”。我们将在整个课程中交替使用这些术语。与上述代码等效的 Java 程序如下：

```java
public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```

这里的新语法是我们使用关键字声明我们的方法`public static`，这是 Python`def`关键字的一个非常粗略的模拟。我们将在下一章看到声明方法的替代方法。

这里给出的 Java 代码当然看起来要冗长得多！您可能会认为这种编程语言会减慢您的速度，而且在短期内确实会。将所有这些东西视为我们还不了解的安全设备。当我们构建小程序时，这一切似乎都是多余的。然而，当我们开始构建大型程序时，我们会逐渐意识到所有增加的复杂性。

打个比方，Python 编程有点像[Dan Osman 的自由独奏 Lover's Leap](https://www.youtube.com/watch?v=NCByLWtM7y4)。它可能非常快，但也很危险。相比之下，Java 更像是在[这个视频中](https://www.youtube.com/watch?v=tr6UIfPEuI0)使用绳索、头盔等。

### 代码风格、注释、Javadoc

代码可以在很多方面都很漂亮。它可以很简洁。它可以很聪明。它可以是有效的。新手最不喜欢的代码方面之一是代码风格。当您作为新手编程时，您通常一心一意地想让它工作，而不考虑再次查看它或必须长时间维护它。、

在本课程中，我们将努力保持代码的可读性。良好编码风格的一些最重要的特征是：

- 一致的样式（间距、变量命名、大括号样式等）
- 大小（不太宽的行，不太长的源文件）
- 描述性命名（变量、函数、类），例如名称类似于`year`和`getUserName`而不是变量`x` 和函数`f`。
- 避免重复代码：您几乎不应该有两个重要的代码块，除了一些更改之外几乎相同。
- 在适当的地方发表评论。Java 中的行注释使用`//`分隔符。块（又名多行注释）注释使用 `/*`和`*/`.

黄金法则是：编写代码，让陌生人容易理解。

这是课程的官方[风格指南](https://sp19.datastructur.es/materials/guides/style-guide.html)。值得一看！

通常，我们愿意承担轻微的性能损失，只是为了让我们的代码更易于[grok](https://en.wikipedia.org/wiki/Grok)。我们将在后面的章节中重点介绍示例。

#### 评论

我们鼓励您编写自记录的代码，即通过选择变量名称和函数名称，以便更容易准确地了解正在发生的事情。然而，这并不总是足够的。例如，如果您正在实现一个复杂的算法，您可能需要添加注释来描述您的代码。您对评论的使用应该是明智的。通过体验和接触别人的代码，你会感觉到什么时候注释最合适。

需要特别注意的是，您的所有方法和几乎所有类都应使用所谓的[Javadoc](https://en.wikipedia.org/wiki/Javadoc)格式在注释中进行描述。在 Javadoc 注释中，块注释以额外的星号开头，例如`/**`，并且注释通常（但不总是）包含描述性标记。我们不会在本教科书中讨论这些标签，但请参阅上面的链接了解它们的工作原理。

作为没有标签的例子：

```java
public class LargerDemo {
    /** Returns the larger of x and y. */           
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```

广泛使用的[javadoc 工具](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)可用于生成代码的 HTML 描述。我们将在后面的章节中看到示例。

```java
当你编写一个函数的时候，最好在其上放上一些评论
/** comment  */
或是
/** comment
	@precondition */
```

*但是最好是写出通俗易懂、甚至不需要大段注释的代码，这对你自己是莫大的好处，因为往往需要维护代码的，是你自己 —— Josh Hug*

### 接下来是什么

在每一章的最后，会有一些链接让你知道你可以用到目前为止所涵盖的材料完成哪些练习（如果有的话），按照你应该完成的顺序列出。

- [作业 0](http://sp18.datastructur.es/materials/hw/hw0/hw0.html)
- [实验室 1b](http://sp18.datastructur.es/materials/lab/lab1setup/lab1setup)
- [实验室 1](http://sp18.datastructur.es/materials/lab/lab1/lab1.html)
- [讨论1](http://sp18.datastructur.es/materials/discussion/disc01.pdf)

