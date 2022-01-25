## Lab 2: Unit Testing with JUnit and IntLists

### Pre-lab

- 在你开始之前，你应该有一个`lab2/`文件夹。

### 介绍

在本实验中，您将了解基本的 IntelliJ 功能、破坏性与非破坏性方法以及 IntList。

您完成这项任务的工作是完成调试练习并为`IntList.java`.

### 调试器基础

重复实验 2 设置中的项目设置过程。但是，这一次，您应该向JB导入`lab2`而不是lab2setup`。

导入后，您的 IntelliJ 应如下所示：

![文件夹结构](https://sp19.datastructur.es/materials/lab/lab2/img2/folder_structure.png)

### 断点和步入

我们将从运行 main 方法开始`DebugExercise1`。在 IntelliJ 中打开此文件并单击运行按钮。您应该会看到打印到控制台的三个语句，其中一个应该会让您觉得不正确。如果不确定如何运行`DebugExercise1`，在文件列表中右击，点击`Run DebugExercise1.main`按钮，如下图：![运行按钮](https://sp19.datastructur.es/materials/lab/lab2/img2/run_button.png)

在我们的代码中某处有一个错误，但不要仔细阅读它的代码！虽然您可能能够发现这个特定的错误，但如果不实际尝试运行代码并探测它执行时发生的情况，通常几乎不可能看到错误。

你们中的许多人在使用 print 语句来探测程序运行时的想法方面有很多经验。虽然 print 语句对于调试非常有用，但它们也有一些缺点：它们需要您修改代码（以添加 print 语句）。它们要求您明确说明您想知道的内容（因为您必须准确地说出您想要打印的内容）。他们以难以阅读的格式提供结果，因为它只是执行窗口中的一大块文本。

如果您使用调试器，通常（但并非总是）发现错误需要更少的时间和精力。IntelliJ 调试器允许您在执行过程中暂停代码，逐行执行代码，甚至可视化复杂数据结构（如链表）的组织。

虽然它们很强大，但必须正确使用调试器才能获得任何优势。我们鼓励您进行所谓的“科学调试”，即使用与科学方法非常相似的方法进行调试！

一般来说，您应该制定有关代码段应该如何表现的假设，然后使用调试器来确定这些假设是否正确。随着每一条新的证据，你将完善你的假设，直到最后，你不禁陷入了这个错误。

我们的第一个练习向我们介绍了两个核心工具，the`breakpoint`和 `step over`button。在左侧项目视图中，右键单击（或两指单击） `DebugExercise1`文件，这次选择`Debug`选项而不是`Run`选项。如果 Debug 选项没有出现，那是因为您没有正确导入您的 lab2 项目（参见 lab2setup 的步骤 1-10）。![文件夹结构](https://sp19.datastructur.es/materials/lab/lab2/img2/debug_button.png)

您会看到程序只是再次运行，没有明显区别！那是因为我们没有给调试器做任何有趣的事情。让我们通过“设置断点”来解决这个问题。为此，滚动到显示 的行`int t3 = 3;`，然后左键单击行号右侧。你应该会看到一个红点，有点像停车标志，这意味着我们现在已经设置了一个断点。如果我们再次以调试模式运行程序，它将停在该行。如果您不想通过右键单击再次运行程序，则可以单击屏幕右上角的`Debug`图标。[此链接](https://gfycat.com/ThickBarrenFrogmouth)上提供了展示本段中步骤的动画 gif 。

![断点](https://sp19.datastructur.es/materials/lab/lab2/img2/breakpoint.png)

如果单击调试按钮时没有出现文本控制台（显示“round(10/2)”之类的内容），则可能需要在继续之前执行一个额外的步骤。在底部面板信息窗口的左上角，您应该会看到标有“Debugger”和“Console”（以及“Java Visualizer”）的选项卡。单击并将“控制台”窗口拖动到底部面板的最右侧。这将允许您同时显示调试器和控制台。[此链接](https://gfycat.com/SmugAbleAustraliankelpie)上提供了展示此过程的动画 gif 。

单击调试按钮（必要时使控制台窗口可见）后，您应该会看到程序已在您设置断点的行处暂停，并且您还应该看到所有变量的列表底部，包括`t`、`b`、`result`、`t2`、`b2`和`result2`。我们可以通过单击“step into”按钮使程序前进一步，该按钮是一个向下的箭头，如下一行所示：

![踏入](https://sp19.datastructur.es/materials/lab/lab2/img2/step_into.png)

我们稍后将在本实验中讨论其他按钮。**确保您按的是“步入”而不是“跨过”。**Step-into 指向直下，而 step-over 指向右侧，然后向下指向右侧。

每次单击此按钮，程序将前进一步。**在您每次点击之前，制定一个关于变量应该如何变化的假设。**

**请注意，当前突出显示的行*是即将执行*的行，而不是刚刚执行的行。**

重复此过程，直到找到结果与您的期望或编写代码的人的期望不符的行。试着找出为什么这条线没有达到你的预期。如果您第一次错过了该错误，请单击停止按钮（红色方块），然后单击调试按钮重新开始。或者，您可以在找到该错误后对其进行修复。

### 跨越并走出

正如我们依靠分层抽象来构造和组合程序一样，我们也应该依靠抽象来调试我们的程序。IntelliJ 中的“跳过”按钮使这成为可能。**上一个练习中的“step into”显示了程序的下一步，而“step over”按钮允许我们在不显示正在执行的函数的情况下完成函数调用。**

中的主要方法`DebugExercise2`应该采用两个数组，计算这两个数组的元素最大值，然后对结果最大值求和。例如，假设两个数组是`{2, 0, 10, 14}`和`{-5, 5, 20, 30}`。element-wise max 是`{2, 5, 20, 30}`，例如在第二个位置，“0”和“5”中较大的一个是 5。这个 element-wise max 的总和是 2 + 5 + 20 + 30 = 57。

提供的代码中有两个不同的错误。您在本练习中的工作是修复这两个错误，但有一条特殊规则：**您不应该进入`max`or`add`函数，甚至不应该尝试理解它们。**这些是非常奇怪的函数，它们使用语法（和糟糕的风格）以极其迟钝的方式完成简单的任务。如果您发现自己不小心进入了这两个功能之一，请使用“退出”按钮（向上箭头）来退出。

即使没有进入这些功能，您也应该能够判断它们是否有错误。这就是抽象的荣耀！即使我不知道一条鱼在分子水平上是如何工作的，但在某些情况下我可以清楚地看出一条鱼已经死了。

如果你发现其中一个函数有错误，你应该完全重写它而不是试图修复它。

既然我们已经告诉您“越过”的作用，请尝试探索它的确切工作原理并尝试找出两个错误。**如果您运行（或调试）按钮后，IDEA继续运行的是 DebugExercise1，请右键单击 DebugExercise2 以运行它。**

```java
// 修改完如下所示
/**
 * Exercise to showcase the step over button.
 * Code adapted from https://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java and https://stackoverflow.com/questions/1533131/what-useful-bitwise-operator-code-tricks-should-a-developer-know-about
 */
public class DebugExercise2 {
    /** Returns the max of a and b. Do not step into this function. 
      * This function may have a bug, but if it does, you should find it
      * by stepping over, not into. */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }


    /** Returns the sum of a and b. Do not step into this function. 
      * This function may have a bug, but if it does, you should find it
      * by stepping over, not into. */    
    public static int add(int a, int b) {
        return a + b;
    }

    /** Returns a new array where entry i is the max of
     * a[i] and b[i]. For example, if a = {1, -10, 3}
     * and b = {0, 20, 5}, this function will return {1, 20, 5}.
     * */
    public static int[] arrayMax(int[] a, int[] b) {
        if (a.length != b.length) {
            System.out.println("ERROR! Arrays don't match");
            return null;
        }
        int[] returnArray = new int[a.length];
        for (int i = 0; i < a.length; i += 1) {
            int biggerValue = max(a[i], b[i]);
            returnArray[i] = biggerValue;
        }

        return returnArray;
    }

    /** Returns the sum of all elements in x. */
    public static int arraySum(int[] x) {
        int i = 0;
        int sum = 0;
        while (i < x.length) {
            sum = add(sum, x[i]);
            i = i + 1;
        }
        return sum;
    }

    /** Returns the sum of the element-wise max of a and b.
     *  For example if a = {2, 0, 10, 14} and b = {-5, 5, 20, 30},
     *  the result should be 57.
     * */
    public static int sumOfElementwiseMaxes(int[] a, int[] b) {
        int[] maxes = arrayMax(a, b);
        int sumofMaxes = arraySum(maxes);
        return sumofMaxes;
    }


    public static void main(String[] args) {
        int[] a = {1, 11, -1, -11};
        int[] b = {3, -3, 2, -1};

        int sumOfElementwiseMaxes = sumOfElementwiseMaxes(a, b);
        System.out.println(sumOfElementwiseMaxes);
    }
}
```

如果您遇到困难或只是想要更多指导，请阅读以下说明。

#### 进一步指导（对于那些想要它的人）

首先，尝试运行该程序。该`main`方法将计算并向控制台打印答案。尝试手动计算答案，您会发现打印的答案不正确。如果您不知道如何手动计算答案，请重新阅读上面对函数应该做什么的描述，或阅读提供的代码中的注释。

`main`接下来，在调用的行中设置断点`sumOfElementwiseMaxes`。然后使用调试按钮，然后使用 step-into 功能到达`sumOfElementWiseMaxes`. 然后使用呼叫线路上的“跳过”按钮`arrayMax`。输出有什么问题（如果有的话），即它如何不符合您的期望？请注意，要查看数组的内容，您可能需要单击底部面板中调试器窗口的变量选项卡中变量名称旁边的向右三角形。

如果你觉得有错误，请进入`arrayMax`（而不是越过它）并尝试找到错误。提醒：请勿踏入`max`。您应该能够`max`使用 step over 来判断是否有错误。如果`max`有错误，请完全更换。

`arraySum`用和重复相同的过程`add`。修复两个错误后，请仔细检查该`sumOfElementwiseMaxes`方法是否适用于提供的输入。注意：这并不能证明`sumOfElementwiseMaxes`是正确的，但没有必要编写任何额外的测试来帮助验证这一事实（这将在下周推出）。

### 条件断点和恢复

有时，能够设置断点并一遍又一遍地返回它会很方便。在最后的调试练习中，我们将了解如何执行此操作以及它为何有用。

尝试运行`DebugExercise3`，它会尝试计算附近所有杂货店提供的萝卜数量。它通过读入`foods.csv`提供有关可用食品的信息来做到这一点，其中文件的每一行对应于单个商店中可用的单个产品。随意打开文件看看它的样子。奇怪的是，萝卜的数量似乎是负数。

在发生的行上设置一个断点`totalTurnips = newTotal`，你会看到如果你“越过”，萝卜的总数会按照你的预期增加。一种调试方法是反复单击“跳过”，直到最终出现问题。但是，这太慢了。我们可以加快速度的一种方法是单击“恢复”按钮（就在跨步按钮的下方和左侧），它看起来像一个指向右侧的绿色三角形。重复此操作，您会看到萝卜计数不断增加，直到最终出现问题。

一种更快的方法是使我们的断点有条件。为此，请右键（或两指）单击红色断点。在这里，您可以设置何时要停止的条件。在条件框中，输入“newTotal < 0”，停止程序，然后再次单击“调试”。你会看到你降落在你想去的地方。

看看你是否能找出问题所在（不要担心对代码进行任何更改；只需找出问题所在）。如果您无法弄清楚，请与您的邻居或助教或实验室助理交谈。

```java
// 发现foods.csv里面有一个错误数据是：-387128732
// 加个正负值判断即可
/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
         while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip") && numAvailable > 0) {
                int newTotal = totalTurnips + numAvailable;
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
    }
}

//D:\JDK8\jdk1.8.0_311\bin\java.exe ...
56554405

Process finished with exit code 0
```

### 回顾：调试

至此，您应该了解以下工具：

- 断点

- 跨过 - 不执行函数

- 走进 - “走进”函数内部

- 退出（尽管您可能没有在本实验中实际使用过此功能）

- 条件断点 - 当满足条件的时候停止

- **恢复 - 在`totalTurnips = newTotal`行上设置一个断点，然后重复resume操作（您会看到萝卜计数不断增加，直到最终出现问题）**

  **resume在这种循环中很好用呀，相当于快速执行循环，不断观察程序在断点位置发生的事情。**

然而，这只是触及调试器功能的皮毛！随意尝试。你看到“手表”标签了吗？为什么不阅读它的作用？还是“评估表达式”按钮（进入/结束/退出按钮行上的最后一个按钮 - 它看起来像一个计算器）？在实验 3 中，我们将尝试其中的几个功能。

## 应用：IntLists

### IntLists 的介绍/回顾

正如周一的讲座中所讨论的，an`IntList`是我们针对整数的裸递归链表的 CS61B 实现。每个`IntList`都有一个`first`和`rest`变量。`first`是节点包含的元素`int`，而`rest`是列表中的下一个链（另一个`IntList`！）。

在本实验的 IntList 目录中，我们提供了一个`IntList.java`比我们在课堂上创建的大得多的目录。它有五个重要的新静态方法，您将填写其中两个：

- `void dSquareList(IntList L)`: 修改列表，使其所有元素都是平方的。
- `IntList squareListIterative(IntList L)`：使用迭代返回所有元素平方的列表版本。该列表未修改。
- `IntList squareListRecursive(IntList L)`：使用递归返回所有元素平方的列表版本。该列表未修改。
- `dcatenate(IntList A, IntList B)`：返回一个由 A 的所有元素组成的列表，后跟 B 的所有元素。可以修改 A。由您完成。

```java
    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        //TODO:  fill in method
        if(A == null)
            return B;
        IntList ptr = A;
        while(ptr.rest != null){
            ptr = ptr.rest;
        }
        ptr.rest = B;
        return A;
    }
```

- `catenate(IntList A, IntList B)`：返回一个由 A 的所有元素组成的列表，后跟 B 的所有元素。不能修改 A。由您完成。

```java
    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        //TODO:  fill in method
        if(A == null)
            return  B;
        IntList newA = new IntList(A.first, null);
        IntList ret = newA;
        while(A.rest != null){
            newA.rest = new IntList(A.rest.first, null);
            A = A.rest;
            newA = newA.rest;
        }
        newA.rest = B;
        return ret;
    }
```

该类还包括您不应该阅读或理解的其他方法。在本实验中省略了它们的描述。

### 破坏性与非破坏性

对于给定的所需功能，通常有多种方法可以编写相同的功能。例如，考虑对数字列表中的每个项目进行平方的任务。在提供的`IntList`java.lang.

让我们考虑一种方法`dSquareList`，它将“破坏性”地平方列表中的每个项目（类似于第 2 周讨论的额外问题。

```java
IntList origL = IntList.of(1, 2, 3)
dSquareList(origL);
// origL is now (1, 4, 9)
```

破坏性是指原始列表发生变化。有时也使用“突变”一词（如第 2 周的讨论）。相比之下，非破坏性方法`squareListIterative`不会影响原始列表，例如

```java
IntList origL = IntList.of(1, 2, 3)
IntList squaredList = squareListIterative(origL);
// origL is still (1, 2, 3)
// squaredList is (1, 4, 9)
```

#### dSquareList 实现

这是一种可能的实现`dSquareList()`，以及对 的调用`dSquareList`：

```java
public static void dSquareList(IntList L) {
    while (L != null) {
        L.first = L.first * L.first;
        L = L.rest;
    }
}

IntList origL = IntList.of(1, 2, 3)
dSquareList(origL);
// origL is now (1, 4, 9)
```

具有破坏性的原因`dSquareList`是因为我们更改了**原始输入`IntList`**的值。随着我们的进行，我们对每个值进行平方，并且更改内部数据的操作仍然存在。

观察框`origL`中的位不改变也很重要，即变量在完成时仍然指向内存中完全相同的对象`dSquareList`。

为了确保这些想法都有意义，请在*调试*模式下设置断点`dSquareList`并运行`IntListTest`该类。**使用 Java Visualizer**（您在 lab2setup 中安装）在**您使用调试器逐步完成时可视化 IntList**。可视化工具是一个带有眼睛的蓝色咖啡杯图标，是调试器面板中“控制台”选项卡旁边的选项卡）。如果您不知道如何让可视化器显示，请参阅[CS 61B 插件指南。](https://sp19.datastructur.es/materials/guides/plugin.html#using-the-plugin)

![可视化器](https://sp19.datastructur.es/materials/lab/lab2/img2/visualizer.png)

如果您不了解该`dSquareList`方法的工作原理，请向助教或学术实习生寻求帮助。他们在这里为您提供帮助！指针和 IntList 起初可能看起来令人困惑，但了解这些概念很重要！

注意：选择返回 void 而不是指向的指针`L`是一个任意决定。不同的语言和库使用不同的约定（人们对哪个是“正确”的约定非常不满）。

#### 无损平方

提供的`squareListIterative()`和`squareListRecursive()`方法都是*非破坏性的*。也就是说，`IntList`传递给方法的底层**不会**被修改，而是一个新的副本被修改并返回。

看`squareListIterative`

```java
    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }
```

和`squareListRecursive`。理想情况下，您应该花一些时间尝试真正理解它们，包括可能使用可视化工具。但是，如果您没有时间，这个迭代版本会更加混乱。

```java
    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     * what a beautiful code.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }
```

非破坏性方法的迭代版本通常（但不总是）比递归版本更混乱，因为它需要一些小心的指针操作来创建一个新的`IntList`、构建它并返回它。

#### 测试代码

（可选）查看 中的测试方法`testDSquareList`。`IntListTest.java`这让您对本课程今后将如何编写测试有所了解，我们将要求您从下周的实验室开始编写测试。下周编写项目 1A 时，您可能还会发现它们很方便。

测试所依赖的一个主要区别是，我们在`IntList`类中添加了一个名为的方法，该方法`of`可以更轻松地创建 IntList。例如，要创建一个`IntList`包含数字 0、1、2 和 3 的数字，我们可以使用如下方法：

```java
IntList myList = IntList.of(0, 1, 2, 3);
// Creates the IntList 0 -> 1 -> 2 -> 3 -> null
```

- `myList.first` 返回 0
- `myList.rest` 返回 1 -> 2 -> 3 -> null
- `myList.rest.rest.rest` 返回 3 -> null
- `myList.rest.rest.rest.rest` 返回空
- 流行测验：会发生什么`myList.rest.rest.rest.rest.rest`？（提示：它没有成功返回一些东西）

`IntList.of()`请注意，与蛮力方法相比，该方法更容易创建 IntLists。

```java
IntList myList = new IntList(0, null);
myList.rest = new IntList(1, null);
myList.rest.rest = new IntList(2, null);
myList.rest.rest.rest = new IntList(3, null);
// One line of using IntList.of() can do the job of four lines!
```

### 实施破坏性与非破坏性方法

要完成实验，您应该编写方法`dcatenate`，`catenate`如下所述。在编写代码时，您可能会发现上面的平方方法很有用。

这两种方法都接受两个 IntList，并将它们连接在一起。所以 `catenate(IntList A, IntList B)`和`dcatenate(IntList A, IntList B)`两者都会导致一个`IntList`包含元素的元素，`A`然后是元素`B`。

这两种方法之间的唯一区别是`dcatenate`修改原始文件`IntList A`（即它是破坏性的）而`catenate`不是。

完成实验：

- 填写`dcatenate()`和`catenate()`，然后针对我们的测试运行它们。修改您的代码，直到它通过我们的测试。

首先测试对A进行破坏性改写的。

```java
    @Test
    public void testDcatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.dcatenate(A, B));
        assertEquals(IntList.of(1, 2, 3, 4, 5, 6), A);
    }
```

testDcatenate() 测试结果如下：

```java
A = (1, 2, 3, 4, 5, 6)
B = (4, 5, 6)
exp = (1, 2, 3, 4, 5, 6)
// 故通过测试
```

下面测试非破坏性的。

```java
    @Test
    public void testCatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);

        IntList tmp = IntList.catenate(A, B);

        assertEquals(exp, IntList.catenate(A, B));
        assertEquals(IntList.of(1, 2, 3), A);
    }
```

testCatenate() 测试结果如下：

```java
tmp = "(1, 2, 3, 4, 5, 6)"

// 并且执行完成之后，A 的值仍旧为
A = "(1, 2, 3)"
    
// 所以没问题
```

- 重复您尚未完成的方法。（我们建议您先做一个并在开始下一个之前完成它，因为这样您就可以利用类似的逻辑）。

`IntList`问题可能很难考虑，总有几种方法可以奏效。不要害怕拿出笔和纸或走到白板上做一些例子！如果你被卡住了，画出指针可能会刺激你回到进步的道路上。而且，与往常一样，调试器（和可视化器）是一个不错的选择！

随意使用递归或迭代。如需额外练习，请尝试两者！

**首先考虑基本情况（例如 when `A`is `null`）通常也很有用 - 这对于构建递归解决方案特别有效。换句话说，编写一个适用于基本案例的解决方案，然后停下来思考如何将此解决方案扩展为适用于其他更大案例的解决方案。**

### 完成并提交实验 2

要完成本实验，您将提交 IntList.java。按照Lab 1 底部的[提交说明进行操作。](https://sp19.datastructur.es/materials/lab/lab1/lab1.html#g-submitting-lab-1)

## 全面回顾

在这个实验室中，我们回顾了：

- 在 IntelliJ 调试器中步入、结束和退出（这对于项目来说很方便！）
- 非破坏性与破坏性方法
- IntLists 和指针
- `IntList`以破坏性、非破坏性、递归和迭代方式编写方法

## 常见问题解答和常见问题

### 像 String 或 String.equals() 这样的东西是红色的！

这是一个 JDK 问题，请转到文件 > 项目结构 > 项目 > 项目 SDK 进行故障排除。如果您的 Java 版本是 11.0，那么您应该具有 11.0 SDK 和 11 级语言级别。

### @Test 之类的东西是红色的！

您忘记添加库。**每次** 开始新项目时都必须添加库！

### 控制台按钮未显示！

那是因为你没有编译成功。通常，这是因为您没有添加库。

### Java 文件有一个红色圆圈，圆圈内有一个 J，在文件图标旁边

您是否确保*导入*文件夹而`lab2`不是*打开*`lab2`文件夹？这通常是此类问题的原因。

但是，如果您正确*导入*，请尝试右键单击包含该 Java 文件的文件夹，然后单击 Mark As → Sources Root。