## Project 1A: Data Structures

这个项目分为两个部分。第 1A 部分将于 2/8 到期，第 1B 部分将于 2/15 到期。

我们不会模拟我们宇宙中光荣的引力物理学，而是构建数据结构。这不是最迷人的事情，但这将为您提供大量机会，在期中考试开始之前练习我们在讲座中所做的一切。

## 介绍

在这个项目 1A 中，我们将使用列表和数组构建“双端队列”的实现。在项目 1B（下周）中，我们将学习如何为这些数据结构编写自己的测试，并将使用双端队列来解决一些现实世界的小问题。

在项目的这一部分中，您将创建两个 Java 文件： `LinkedListDeque.java`和`ArrayDeque.java`，以及下面列出的公共方法。

与项目 0 不同，我们将提供相对较少的脚手架。换句话说，我们会说你应该做什么，而不是怎么做。

对于这个项目，你必须单独工作！请仔细阅读[关于合作和作弊的政策，](http://sp19.datastructur.es/about.html#project-collaboration-policy)以了解其确切含义。

**我们强烈建议您为此项目切换到 IntelliJ。**虽然这不是绝对必需的，但您将有更好的时间。可视化调试代码的能力非常有用，拥有一个在您键入时捕获语法错误的开发环境也很不错，并且它避免了键入`javac`和`java`（或按箭头键）数十亿次的需要。如果您需要重新了解如何导入项目，可以遵循[Intellij 设置指南](https://sp19.datastructur.es/materials/lab/lab2setup/lab2setup#project-setup)

此外，**我们将强制执行 style**。您必须遵循[样式指南](https://sp19.datastructur.es/materials/guides/style-guide.html)，否则您将在自动评分器上失分。

## 获取骨架文件

与项目 0 一样，您应该从下载骨架文件开始。按照下面的指引进行操作。

为此，请前往包含您的存储库副本的文件夹。例如，如果您的登录名是“s101”，则前往“sp19-s101”文件夹（或任何子目录）。

现在我们将确保您拥有最新的骨架文件副本，使用
`git pull skeleton master`. 如果您使用的是较新版本的 git，则可能需要执行
`git pull skeleton master -allow-unrelated-histories`.

如果您发现自己面临一个奇怪的文本编辑器或合并冲突，请参阅[项目 0](http://sp19.datastructur.es/materials/proj/proj0/proj0)说明以了解如何继续。

成功合并后，您应该会看到一个 proj1a 目录，其中包含与[骨架存储库匹配的](https://github.com/Berkeley-CS61B/skeleton-sp19/tree/master/proj1a)文件。

如果您遇到某种错误，请停止并通过仔细阅读 git 指南来解决问题，或者在 OH 或 Piazza 寻求帮助。与使用 git 命令进行猜测和检查相比，您可能会省去很多麻烦。如果您发现自己尝试使用 Google 推荐的命令，例如
`force push`， [不要](https://twitter.com/heathercmiller/status/526770571728531456)。**不要使用强制推送，即使您在 Stack Overflow 上找到的帖子说要这样做！**

骨架中唯一提供的文件是`LinkedListDequeTest.java`. 该文件提供了如何编写测试以验证代码正确性的示例。我们强烈建议您尝试给定的测试，并至少编写一个您自己的测试。

你可能会发现编写测试很烦人。然而，在这个项目的 B 部分中，我们将使用一个名为 JUnit 的库，这将使编写测试变得更加容易和有条理。我们将在后面的部分中更多地讨论测试。

## 双端队列 API

双端队列与我们在课堂上讨论过的 SLList 和 AList 类非常相似。这是来自 [cplusplus.com](http://www.cplusplus.com/reference/deque/deque/)的定义。

> Deque（通常发音为“deck”）是双端队列的不规则首字母缩写词。双端队列是具有动态大小的序列容器，可以在两端（前端或后端）扩展或收缩。

具体来说，任何双端队列实现都必须具有以下操作：

- `public void addFirst(T item)``T`：在双端队列的前面添加一个类型的项目。
- `public void addLast(T item)``T`：在双端队列的后面添加一个类型的项目。
- `public boolean isEmpty()`: 如果 deque 为空，则返回 true，否则返回 false。
- `public int size()`：返回双端队列中的项目数。
- `public void printDeque()`：从头到尾打印双端队列中的项目，用空格分隔。打印完所有项目后，打印出一个新行。
- `public T removeFirst()`：删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。
- `public T removeLast()`：删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。
- `public T get(int index)`：获取给定索引处的项目，其中 0 是前面，1 是下一个项目，依此类推。如果不存在这样的项目，则返回 null。不能改变双端队列！

- 你的类应该接受任何泛型类型（不仅仅是整数）。有关创建和使用通用数据结构的信息，请参阅[第 5 讲](https://docs.google.com/presentation/d/1nRGXdApMS7yVqs04MRGZ62dZ9SoZLzrxqvX462G2UbA/edit?usp=sharing)。请务必密切注意最后一张幻灯片上关于泛型的经验法则。



## 项目任务

### 1.链表双端队列

*注意：我们在第 4 课和第 5 课（1/30 和 2/1）中涵盖了完成这部分所需的所有内容。*

在您的项目目录中创建一个名为`LinkedListDeque.java`的文件。

作为您的第一个双端队列实现，您将构建`LinkedListDeque`基于链表的类。

您的操作须遵守以下规则：

- `add`和`remove`操作不得涉及任何循环或递归。单个这样的操作必须花费“恒定时间”，即执行时间不应取决于双端队列的大小。
- `get` 必须使用迭代，而不是递归。
- `size`必须花费恒定的时间。
- 您的程序在任何给定时间使用的内存量必须与项目数成正比。例如，如果您向双端队列添加 10,000 个项目，然后删除 9,999 个项目，则生成的大小应该更像是一个包含 1 个项目而不是 10,000 个项目的双端队列。不要维护对不再在双端队列中的项目的引用。

实现上面“Deque API”部分中列出的所有方法。

此外，还需要实现：

- `public LinkedListDeque()`: 创建一个空的链表双端队列。
- `public LinkedListDeque(LinkedListDeque other)`创建`other`
  - 创建深层副本意味着您创建一个全新的`LinkedListDeque`，具有完全相同的项目`other`。但是，它们应该是不同的对象，即如果您更改了，您创建`other`的新对象也不应该更改。`LinkedListDeque`（编辑 2/6/2018：为该复制构造函数提供解决方案的演练可在https://www.youtube.com/watch?v=JNroRiEG7U4获得）
- `public T getRecursive(int index)`: 与 get 相同，但使用递归。

`LinkedListDeque.java`如果您认为有必要，您可以添加任何私有帮助程序类或方法。

虽然这听起来很简单，但有许多设计问题需要考虑，并且您可能会发现实现比您预期的更具挑战性。一定要参考关于双向链表的讲座，特别是关于哨兵节点的幻灯片：[两个哨兵拓扑](https://docs.google.com/presentation/d/1suIeJ1SIGxoNDT8enLwsSrMxcw4JTvJBsMcdARpqQCk/pub?start=false&loop=false&delayms=3000&slide=id.g829fe3f43_0_291)和[循环哨兵拓扑](https://docs.google.com/presentation/d/1suIeJ1SIGxoNDT8enLwsSrMxcw4JTvJBsMcdARpqQCk/pub?start=false&loop=false&delayms=3000&slide=id.g829fe3f43_0_376)。我更喜欢循环方法。**不允许在实现中使用 Java 内置的 LinkedList 数据结构（或任何来自 的数据结构`java.util.\*`）**。

```java
public class LinkedListDeque {
    /**
     * add和remove操作不得涉及任何循环或递归。单个这样的操作必须花费“恒定时间”，即执行时间不应取决于双端队列的大小。
     * 这个要求我有一点疑问，作为不使用数组辅助的双端链表，怎么可能做到不适用循环或递归且add（任何位置）都是恒定时间呢？
     */

    public 
}

```



### 2. 数组双端队列

*注意：我们将在 2 月 4 日（第 6 讲）之前涵盖在讲座中完成这部分所需的所有内容。*

在您的项目目录中创建一个名为`ArrayDeque.java`的文件。

作为您的第二个双端队列实现，您将构建`ArrayDeque`该类。这个双端队列必须使用数组作为核心数据结构。

对于此实施，您的操作受以下规则的约束：

- `add`和`remove`必须花费恒定的时间，除非在调整大小操作期间。
- `get`并且`size`必须花费恒定的时间。
- 数组的起始大小应为 8。
- 您的程序在任何给定时间使用的内存量必须与项目数成正比。例如，如果您向双端队列添加 10,000 个项目，然后删除 9,999 个项目，那么您不应该仍然使用长度为 10,000 的数组。对于长度为 16 或更长的数组，您的使用系数应始终至少为 25%。对于较小的阵列，您的使用系数可以任意低。

实现上面“Deque API”部分中列出的所有方法。

此外，还需要实现：

- `public ArrayDeque()`: 创建一个空数组双端队列。
- `public ArrayDeque(ArrayDeque other)`: 创建`other`
  - 创建深层副本意味着您创建一个全新的`ArrayDeque`，具有完全相同的项目`other`。但是，它们应该是不同的对象，即如果您更改了`ArrayDeque`，您创建`other`的新对象也不应该更改。

如果您认为有必要，您可以向`ArrayDeque.java`添加任何私有帮助程序类或方法。

我们*强烈建议*您在本练习中将阵列视为圆形。换句话说，如果你的前端指针位于零位置，并且你 `addFirst`，前端指针应该循环回到数组的末尾（因此双端队列中的新前端项将是基础数组中的最后一项）。与非循环方法相比，这将导致更少的头痛。有关详细信息，请参阅[项目 1 演示幻灯片](https://docs.google.com/presentation/d/1XBJOht0xWz1tEvLuvOL4lOIaY0NSfArXAvqgkrx0zpc/edit#slide=id.g1094ff4355_0_450) 。

正确调整数组的大小**非常棘手**，需要深思熟虑。尝试手工绘制各种方法。您可能需要相当长的时间才能提出正确的方法，我们鼓励您与您的同学或助教讨论重要的想法。确保您的实际实施**是由您一个人**完成的。

## 测试

测试是工业和学术界代码编写的重要组成部分。这是一项基本技能，可以防止行业中的金钱损失和危险错误，或者在您的情况下，失去积分。学习如何编写好的、全面的单元测试，并养成在发布前始终测试代码的好习惯是 CS 61B 的一些核心目标。

我们将在本项目的下一部分（项目 1B）中更多地关注测试。目前，我们为您提供了一个非常简单的健全性检查， `LinkedListDequeTest.java`. 要使用示例测试文件，您必须取消注释示例测试中的行。只有在您实现了该测试使用的所有方法后才取消注释该测试（否则它将无法编译）。执行 main 方法来运行测试。在测试您的项目时，**请记住您可以使用 IntelliJ 内部的可视化工具！**

你不会提交`LinkedListDequeTest.java`。在提交之前为 LinkedListDeque 和 ArrayDeque 编写更全面的测试对您有利。请注意，通过给定的测试`LinkedListDequeTest.java`并不 *一定*意味着您将通过所有自动评分器测试或获得满分。

## 可交付成果

- `LinkedListDeque.java`
- `ArrayDeque.java`

## Tips

- 查看[项目 1 的幻灯片](https://docs.google.com/presentation/d/1XBJOht0xWz1tEvLuvOL4lOIaY0NSfArXAvqgkrx0zpc/edit#slide=id.g1094ff4355_0_450) ，了解一些额外的视觉提示。
- 如果你被卡住了，甚至不知道从哪里开始：一个很好的第一步是实现 SLList 和/或 AList。SList 和 AList 的起始代码（链接即将推出）。为了最大限度地提高效率，请与一两个或三个朋友一起工作。解决方案也可以在 github 上找到。
- 一次拿一点。一次编写大量代码会导致痛苦，而且只会带来痛苦。如果你写了太多东西并且感到不知所措，请将不必要的内容注释掉。
- 如果您的第一次尝试失败，请不要害怕废弃您的代码并重新开始。每个类的代码量实际上并不多（我的解决方案是每个 .java 文件大约 130 行，包括所有注释和空格）。
- `LinkedListDeque(LinkedListDeque other)`和`ArrayDeque(ArrayDeque other)`特别棘手。我们建议最后做。如果您遇到困难，我们在https://www.youtube.com/watch?v=JNroRiEG7U4提供了解决问题的演练。提示：您可能会发现使用您的一些方法来实现这些构造函数很有帮助。可以使用看起来几乎与我的演练一模一样的代码，只需确保使用 @source 标记引用帮助即可。
- 对于 ArrayDeque，在您知道您的代码在没有它的情况下可以正常工作之前，请考虑根本不调整大小。调整大小是一种性能优化（并且是满分所必需的）。
- 在你尝试在代码中实现它们之前，弄清楚你的数据结构在纸上的样子！如果你能找到一个愿意的朋友，让他们发出命令，同时你试图把所有东西都拿出来。尝试提出可能揭示您的实施问题的操作。
- 确保您仔细考虑如果数据结构从空到某个非零大小（例如 4 个项目）又回到零，然后又回到某个非零大小时会发生什么。这是一个普遍的疏忽。
- 一旦你了解了哨兵节点，它们就会让生活**变得更轻松。**
- 循环数据结构使这两种实现（尤其是 ArrayDeque）的生活更轻松。
- 考虑一个辅助函数来完成一些小任务，比如计算数组索引。例如，在我`ArrayDeque`的`int minusOne(int index)`.
- 考虑使用 Java Visualizer（您在 lab2setup 中安装）在您逐步使用调试器时可视化您的 Deque。可视化工具是一个带有眼睛的蓝色咖啡杯图标，是调试器面板中“控制台”选项卡旁边的选项卡）。 如果您不知道如何让可视化器显示，请参阅[CS 61B 插件指南。](https://sp19.datastructur.es/materials/guides/plugin.html#using-the-plugin)可视化器将如下所示：![java_visualizer](https://sp19.datastructur.es/materials/proj/proj1a/java_visualizer.png)

## 经常问的问题

#### 问：当我不知道它们的类型时，我应该如何打印我的双端队列中的项目？

A: 使用将要打印的默认字符串很好（这个字符串来自一个对象的实现`toString()`，我们将在本学期稍后讨论）。例如，如果你在你的类中调用了泛型类型`Jumanji`，要打印`Jumanji j`，你可以调用`System.out.print(j)`.

#### 问：我无法让 Java 创建泛型对象数组！

A：使用我们在 1 月 30 日讲座中看到的奇怪语法，
即`T[] a = (T[]) new Object[1000];`. 这里，`T`是一个泛型类型，它是其他对象类型的占位符，例如“字符串”或“整数”。

#### 问：我试过了，但我收到了编译器警告？

A：抱歉，这是 Java 的设计者在将泛型引入 Java 时搞砸的。没有什么好办法。享受您的编译器警告。我们将在几周内详细讨论这个问题。

#### 问：如何让我的箭头指向数据结构的特定字段？

在您的讲座图表中，箭头看起来能够指向数组的中间或节点的特定字段。

答：每当我在类中画一个指向对象的箭头时，指针指向的是整个对象，而不是对象的特定字段。事实上，引用不可能指向 Java 中对象的字段。

#### 问：为什么LinkedListDeque的第二个构造函数的参数是`LinkedListDeque`而不是`LinkedListDeque<T>`？

答：虽然构造函数的输入具有 type 更有意义`LinkedListDeque<T>`，但简短的回答是您不需要在代码中使用 <T> 。换句话说，如果参数是`LinkedListDeque<T>`，那将阻止您使用自己的选择，如 <Yolo>、<Blorp> 或任何泛型类型参数。

不幸的缺点是您必须将对象从`other`类型转换为 <T> （或 <Yolo> 或您使用的任何东西）。

