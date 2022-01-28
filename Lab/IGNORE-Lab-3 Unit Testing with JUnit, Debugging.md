## Lab 3: Unit Testing with JUnit, Debugging

## 预实验室

- [安装和使用 IntelliJ 插件](https://sp19.datastructur.es/materials/guides/plugin.html#using-the-plugin)：确保您有机会安装和使用 CS61B 和 Java Visualizer IntelliJ 插件。 **确保您知道如何使用插件检查样式。**
- **拉出骨架后，将您的 IntList.java 从 lab2 复制到 lab3/IntList 文件夹中。**
- 确保您已观看有关测试的讲座。本实验假设您已经看过本讲座。
- 确保您的 CS61B 插件版本至少为**2.0.0**并且您的 Java Visualizer 版本至少为**2.0.1**。为此，请转到配置**>** 插件**>**已安装。您应该会看到一个插件列表，包括这两个插件。单击每个，版本号应显示在名称的右侧。

## 介绍

在本实验中，您将了解单元测试、JUnit、61B 样式检查器，我们还将获得更多调试经验。

#### 什么是 JUnit？

[JUnit](https://junit.org/junit4/)是 Java 的单元测试框架。

#### 什么是单元测试？

单元测试是严格测试代码的每种方法并最终确保您有一个工作项目的好方法。

单元测试的“单元”部分源于您可以将程序分解为单元或应用程序的最小可测试部分的想法。因此，单元测试强制执行良好的代码结构（每个方法应该只做“一件事”），并允许您考虑每个方法的所有边缘情况并单独测试它们。

在本课程中，您将使用 JUnit 对您的代码创建和运行测试以确保其正确性。当 JUnit 测试失败时，您将有一个很好的调试起点。此外，如果您有一些难以修复的可怕错误，您可以使用 git 恢复到您的代码根据 JUnit 测试正常工作时的状态。

#### JUnit 语法

JUnit 测试是用 Java 编写的，类似于`LinkedListDequeTest`项目 1A。然而，JUnit 库实现了所有无聊的东西，比如打印错误消息，使测试编写变得更加简单。

要查看示例 JUnit 测试，请导航到 Arithmetic 目录并 `ArithmeticTest.java`在您最喜欢的文本编辑器中打开（暂时不要打开 IntelliJ）。

您会注意到的第一件事是顶部的导入（IntelliJ 有时会将它们缩短为`import ...`；只需单击`...`展开它并查看导入的确切内容）。这些导入使您可以轻松访问运行 JUnit 测试所需的 JUnit 方法和功能。有关详细信息，请参阅测试讲座视频。

接下来，您将看到`ArithmeticTest.java`: `testProduct`和`testSum`. 这些方法遵循以下格式：

```
@Test
public void testMethod() {
    assertEquals(<expected>, <actual>);
}
```

`assertEquals`是 JUnit 测试中常用的方法。它测试变量的实际值是否等于其预期值。

当你创建 JUnit 测试文件时，你应该在每个测试方法前加上一个 `@Test`注解，并且可以有一个或多个`assertEquals`or`assertTrue` 方法（由 JUnit 库提供）。**所有测试都必须是非静态的。** 这可能看起来很奇怪，因为您的测试不使用实例变量并且您可能不会实例化该类。然而，这就是 JUnit 的设计者决定应该如何编写测试的方式，所以我们将继续使用它。

从 61B 的这一点开始，我们将正式在 IntelliJ 中工作。如果您想从终端运行代码，请参阅[此补充指南](https://sp19.datastructur.es/materials/lab/lab3supplement/lab3supplement)。虽然欢迎您这样做，但工作人员不会为命令行编译和执行或其他文本编辑器/IDE 提供官方支持。

## 在 IntelliJ（或其他 IDE）中运行 JUnit 测试

打开 IntelliJ 并**导入**（不要只是打开！）您从骨架中提取的 lab 3 文件夹。重复实验 2 设置、[项目设置](https://sp19.datastructur.es/materials/lab/lab2setup/lab2setup#project-setup)的步骤， 不要忘记导入 javalib 库！

在 IntelliJ 中打开 lab3/Arithmetic/ArithmeticTest.java。将光标移动到 `main`方法`ArithmeticTest`并单击 IntelliJ 顶部菜单`Run...`下的选项。`Run`

![运行选项](https://sp19.datastructur.es/materials/lab/lab3/img/lab3_run.png)

单击“运行...”后，您应该会看到至少两个类似于下面列表的选项。您列表中的项目数量可能会有所不同。

![运行选项](https://sp19.datastructur.es/materials/lab/lab3/img/lab3_run_menu.png)

列表中最重要的两个选项将是一个在红色和绿色箭头旁边仅显示“ArithmeticTest”的选项（在上图中的 2. 旁边），另一个在白色箭头旁边仅显示“ArithmeticTest”和蓝色框（上图中的 1. 旁边）。

如果您使用带有红色/绿色箭头的版本，那么 IntelliJ 将呈现测试结果。如果您使用带有白色/蓝色框的版本，则 Josh 放在一起的渲染器将运行。您更喜欢哪个是个人喜好问题。在本课程的其余部分，我们将它们分别称为“默认渲染器”和“jh61b 渲染器”。

**确保已将您为 lab2 创建的 IntList.java 复制到** **lab3/IntList 文件夹中。**

现在，运行默认渲染器，您应该会看到如下内容：

![运行选项](https://sp19.datastructur.es/materials/lab/lab3/img/default_renderer.png)

这就是说 ArithmeticTest.java 第 25 行的测试失败了。测试预计 5 + 6 为 11，但`Arithmetic`该类声称 5 + 6 为 30。您会看到，即使`testSum`包含许多`assert`语句，也只显示一个失败。

这是因为 JUnit 测试是短路的——只要方法中的一个断言失败，它就会输出失败并继续进行下一个测试。

尝试单击`ArithmeticTest.java:25`屏幕底部窗口中的 ，IntelliJ 将直接带您到导致测试失败的行。在以后的项目中运行您自己的测试时，这可以派上用场。

接下来，尝试使用 jh61b 渲染器运行代码。它看起来不如默认渲染器好，并且不允许您直接单击以进行编码。在本课程的早期版本中，我们让学生从命令行运行测试，这需要 Josh 编写我们自己的自定义渲染器。在我们使用 IntelliJ 的新网络未来中，不再需要此渲染器。但是，您可能会发现自己更喜欢它。

![运行选项](https://sp19.datastructur.es/materials/lab/lab3/img/jh61b_renderer.png)

对于那些喜欢 jh61b 渲染器的人，您可以修改您的 JUnit 测试文件，以便它只显示失败测试的结果（而不是所有测试）。为此，只需将主方法中的模式参数从“all”更改为“failed”。

如果您只想使用您已经选择的渲染器，您可以通过以下四种方式中的任何一种绕过必须在两个渲染器之间进行选择：

1. 右键单击，然后选择“运行”。
2. 使用 IntelliJ 屏幕顶部“运行”菜单中的顶部项目。
3. 单击右上角的绿色箭头（或调试符号）。
4. 使用适当的键盘快捷键（Windows：Shift + F10；Mac：Ctrl + R）。

这四个都是等价的。如果您决定要切换渲染器，则需要使用“运行...”选项。

现在修复错误，方法是检查`Arithmetic.java`并找到错误，或者使用 IntelliJ 调试器单步执行代码，直到找到错误。

修复错误后，重新运行测试，如果您使用的是默认渲染器，您应该会得到一个漂亮的绿色条。享受匆忙。

## 清单

现在一个真正的 JUnit 测试的 CS61B 应用程序：IntLists。

与上周的实验一样，我们将利用 IntList 类的“of”方法，这使得创建 IntList（和编写 IntList 测试）变得更加容易。如果您上周没有机会仔细研究此方法，那么现在是这样做的好时机。例如，考虑：

```
IntList myList = IntList.of(0, 1, 2, 3);
```

这将创建 IntList `0 -> 1 -> 2 -> 3 -> null`。

#### 测试反向方法

**确保已将您为 lab2 创建的 IntList.java 复制到** **lab3/IntList 文件夹中。**在本节中，我们的目标是编写 reverse 方法。这与本周的讨论工作表类似，因此请确保您已经看过它。

我们将在本练习中展示“测试驱动开发”的概念，甚至在编写新方法之前就编写单元测试。

向 IntListTest.java 添加一个测试该`.reverse()`方法的新测试，您可以假设它具有以下定义：

```
/**
 * Returns the reverse of the given IntList.
 * This method is destructive. If given null
 * as an input, returns null.
 */
public static IntList reverse(IntList A)
```

**不要`reverse`向 IntList 添加方法。**我们将在编写之前编写一个测试`reverse`。

您的测试应至少测试以下三种情况：

- 该函数返回一个反向列表。
- 该函数是破坏性的，即当它运行完成时，A指向的列表被篡改。您可以使用`assertNotEquals`. 这是一个愚蠢的测试。
- 该方法正确处理空输入。

您会注意到，当您输入代码时，该代码`IntList.reverse`以红色突出显示，并且鼠标悬停应显示“无法解析方法反向”或类似内容。这是因为我们还没有添加 reverse 方法。我们将在下一节中执行此操作。**还不写`reverse`！**

完成测试后，将您的结果与实验室中的邻居进行比较并进行讨论。确保你们至少都为上述所有三种情况编写了测试。

如果您使用的是命令行而不是 IntelliJ（不推荐）：尝试编译 IntListTest.java，您应该会收到如下编译器错误：

```
IntListTest.java:72: error: cannot find symbol

  symbol:   method reverse
```

这个错误是一件好事！这意味着编译器实际上正在寻找我们的测试。

#### 编写反向方法

现在在 IntList.java 中创建一个 reverse 方法的虚拟版本，它只返回`null`. 您在这里的唯一目标是让 IntList.java 进行编译。**不要填写实际的 reverse 方法的代码，只需让它返回 null 即可。**

如果您从命令行运行 IntListTest，则需要在继续之前添加一个 main 方法。有关示例，请参见 ArithmeticTest.java。由于您应该使用 IntelliJ，除非您想使用 jh61b 渲染器而不是默认渲染器，否则这不是必需的。

尝试运行测试，它测试应该失败。这很棒！我们现在已经到了课堂上描述的测试驱动开发（TDD）周期的“红色”阶段。

编写一个反向方法，然后重新运行测试，直到它通过。**如果您遇到困难（这是一个非常聪明的解决方案的棘手问题），您可以在调试时尝试查看 Java Visualizer，它应该可以帮助您准确了解您的方法在做什么**。如果这没有帮助，请查看第 3 周的讨论解决方案。请注意，本周实验室的完整学分不需要正确的反向（这是 AG 中的未评分测试），因此如果您真的卡住并需要在项目 1A 上工作，请先这样做，然后再回来完成这个练习是在你完成项目 1A 之后进行的。

Protip：如果你想让你的测试在一段时间后超时（以防止无限循环），你可以像这样声明你的测试：

```
@Test(timeout = 1000)
```

给定参数以毫秒为单位指定最长时间。在课程的后期，您可能会遇到在调试过程中似乎“放弃”您的测试，而没有遇到错误。这通常是由于您正在运行的测试指定了超时这一事实造成的，因此您始终可以在调试时删除超时，然后在再次运行测试时将其添加回来。

有些人发现 TDD 的热潮令人上瘾；你基本上是在为自己设置一个小游戏来解决。其他人讨厌它，因此您的里程可能会有所不同。无论您个人是否喜欢 TDD 流程，编写测试都将是您在伯克利学习的最重要的技能之一，而“[感染测试](http://c2.com/cgi/wiki?TestInfected)”将为您和您未来的同事节省大量时间和痛苦。

## 调试之谜

另一个需要学习的重要技能是如何彻底调试。如果做得好，调试应该可以让你快速缩小 bug 所在的位置，即使你在调试你不完全理解的代码。考虑以下场景：

您的公司 Flik Enterprises 发布了一个名为 Flik.java 的优秀软件库，它能够确定两个整数是否相同。

您收到一封来自名为“Horrible Steve”的电子邮件，他描述了他们在使用您的图书馆时遇到的问题：

```
"Dear Flik Enterprises,

Your library is very bad. See the attached code. It should print out 500
but actually it's printing out 128.

(attachment: HorribleSteve.java)"
```

使用以下技术的任意组合，确定错误是在 Horrible Steve 的代码中还是在 Flik 企业的库中：

- 为 Flik 库编写 JUnit 测试。
- **使用 IntelliJ 调试器，尤其是[条件断点](https://www.jetbrains.com/help/idea/2016.3/configuring-breakpoints.html)或[中断异常](https://www.jetbrains.com/help/idea/using-breakpoints.html#exception-breakpoints)（试试这个：运行 -> 查看断点 -> 选中“Java Exceptions Breakpoints” -> 取消选中“Caught Exceptions”）。**
- 使用打印语句。
- 重构可怕的史蒂夫的代码。重构意味着在不改变功能的情况下改变语法。这可能很难做到，因为 HS 的代码使用了很多奇怪的东西。

HorribleSteve.java 和 Flik.java 都使用了我们在课堂上没有涉及的语法。 **我们不希望您修复该错误，甚至不希望您在发现它后了解它发生的原因。**相反，您的工作只是找到错误。

提示：JUnit 提供了一些可能对您有所帮助的`assertTrue(boolean)`方法。`assertTrue(String, boolean)`

尝试对错误提出简短的解释！检查你的助教，看看你的答案是否正确（不是为了成绩）。

## 运行 61B 样式检查器

我们将使用 CS 61B IntelliJ 插件来检查样式（右键单击文件，然后选择“检查样式”）。`IntList.java`在您的 IntList 文件夹中试一试。您应该看到至少有两个样式错误（我们输入的两个，加上您可能介绍的任何内容）。解决这些错误。如果您在样式问题上遇到困难，请查阅 [官方 61B 样式指南](https://sp19.datastructur.es/materials/guides/style-guide)。

通过样式检查后，输出应如下所示：

```
Running style checker on 1 file(s)...
Style checker completed with 0 errors
```

## 双端队列单元测试

在项目 1B（将于 2/9 发布）中，您需要为 Deque 类编写 JUnit 测试。`LinkedListDeque`如果您在实验室有额外的时间，请开始编写一些测试 `ArrayDeque`作为热身。

## 提交

和以前一样，将您的代码推送到 GitHub 并提交到 Gradescope 以测试您的代码。

## 回顾

在这个实验室中，我们回顾了：

- 单元测试（大图）
- JUnit 语法和细节
- 编写 JUnit 测试
- 使用 JUnit 进行调试
- 运行样式检查器