## HW 2: Percolation 渗透

## 获取骨架文件

像往常一样，运行`git pull skeleton master`以获取骨架文件。如果您使用 IntelliJ，则**必须在外部 'hw2' 目录级别（而不是 'hw2' 目录中的 'hw2' 目录）导入项目**。否则，您将收到软件包错误！

## 介绍

[在这个程序中，我们将编写一个程序来通过蒙特卡罗模拟](https://en.wikipedia.org/wiki/Monte_Carlo_method)来估计渗透阈值。

**介绍视频。**可以在[此链接](https://www.youtube.com/watch?v=kIYKCsvG6UI&list=PLNSdoiHk6ujjZs46s6XVXEbZUuF1MIO7g)中找到此硬件的介绍视频。它分为三个部分：介绍、实施剧透和优化剧透。请随意忽略这些剧透，以迎接更艰巨的挑战。如果您想观看我在普林斯顿时制作的四年前的视频，请查看[此链接](https://www.youtube.com/watch?v=o60oHXesOuA)。

**HW2 幻灯片。**可以在[此处](https://docs.google.com/presentation/d/1AV5v-gTSIi5xUwtm-FtkReUmuTA3Mqry1eGjje7OgQo/edit?usp=sharing)找到此硬件的幻灯片。因为这是一个硬件而不是一个项目，所以我对如何处理硬件进行了剧透。如果您想要更大的挑战，您可以忽略它们。

**渗滤。**给定一个由随机分布的绝缘材料和金属材料组成的复合系统：需要有多少部分材料是金属的，这样复合系统才能成为电导体？给定地表有水（或下面有油）的多孔景观，在什么条件下水能够流到底部（或油涌到地表）？科学家们已经定义了一种称为渗透的抽象过程来模拟这种情况。

**该模型。**我们使用 N×N 站点网格对渗透系统进行建模。每个站点要么是打开的，要么是被阻止的。完整站点是一个开放站点，可以通过一系列相邻（左、右、上、下）开放站点连接到顶行中的开放站点。我们说如果底行有一个完整的站点，系统就会渗透。换句话说，如果我们填充连接到顶行的所有开放站点，并且该过程填充底行的一些开放站点，系统就会渗透。（对于绝缘/金属材料示例，开放位置对应于金属材料，因此渗透系统具有从顶部到底部的金属路径，全位置导电。对于多孔物质示例，开放位置对应于空白空间水可能流过的地方，这样一个渗透系统就可以让水充满开放的地方，从上到下流动。）

![渗出物](https://sp19.datastructur.es/materials/hw/hw2/images/percolates.png)

**问题。**在一个著名的科学问题中，研究人员对以下问题感兴趣：如果站点被独立设置为以概率 p 开放（因此以概率 1 - p 阻塞），系统渗透的概率是多少？当 p 等于 0 时，系统不渗透；当 p 等于 1 时，系统渗透。下图显示了 20×20 随机网格（顶部）和 100×100 随机网格（底部）的站点空置概率 p 与渗透概率的关系。

![阈值20](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-threshold20.png)![阈值100](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-threshold100.png)

什么时候ññ足够大，有一个阈值p*p*这样当p <p*p<p*一个随机的 N×N 网格几乎从不渗透，当p >p*p>p*，一个随机的 N×N 网格几乎总是渗透。没有确定渗透阈值的数学解决方案p*p*尚未派生。你的任务是编写一个计算机程序来估计p*p*.

## `Percolation.java`

**渗透数据类型。**`Percolation`要对渗透系统建模，请在使用以下 API命名的 hw2 包中创建数据类型：

```
public class Percolation {
   public Percolation(int N)                // create N-by-N grid, with all sites initially blocked
   public void open(int row, int col)       // open the site (row, col) if it is not open already
   public boolean isOpen(int row, int col)  // is the site (row, col) open?
   public boolean isFull(int row, int col)  // is the site (row, col) full?
   public int numberOfOpenSites()           // number of open sites
   public boolean percolates()              // does the system percolate?
   public static void main(String[] args)   // use for unit testing (not required, but keep this here for the autograder)
}
```

**角落案例。** 按照惯例，行和列索引是 0 和`N`- 1 之间的整数，其中 (0, 0) 是左上角位置：如果、或的`java.lang.IndexOutOfBoundsException`任何参数超出其规定范围，则抛出 a。构造函数应该抛出一个if ≤ 0。`open()``isOpen()``isFull()``java.lang.IllegalArgumentException``N`

**性能要求。** 构造函数所花费的时间应与ñ2ñ2; 所有方法都应该花费恒定的时间加上对联合查找方法`union()`、`find()`、`connected()`和的恒定数量的调用`count()`。满足这些要求有点棘手！您可能会考虑创建一个简单有效的解决方案，然后再想办法让它更快。有关满足速度要求的提示，请参阅本规范开头的视频。您的`numberOfOpenSites()`方法必须花费恒定的时间。**您的代码必须使用`WeightedQuickUnionUF`该类！**不要重新实现 Union Find ADT。这项作业的部分目标是学习如何根据已经解决的问题（不相交集，又名联合查找）来转换一个问题（渗透）。

*注意：如果您使用 IntelliJ，您可以在 Run -> Edit Configurations 中选择要运行的类（即选择要运行的主要方法）。有关更多信息，请查看常见问题解答*

## `PercolationStats.java`

**蒙特卡罗模拟。**要估计渗透阈值，请考虑以下计算实验：

- 初始化所有要阻止的站点。
- 重复以下操作，直到系统渗透：
  - 在所有被屏蔽的站点中随机选择一个站点。
  - 打开网站。
  - 系统渗透时打开的站点比例提供了对渗透阈值的估计。

例如，如果根据下面的快照以 20×20 的网格打开站点，那么我们对渗透阈值的估计为 204/400 = 0.51，因为系统会在第 204 个站点打开时进行渗透。这些图像分别对应于打开的 50、100、150 和 204 个站点。

![渗透50](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-50.png)![渗透100](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-100.png)![渗透150](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-150.png)![渗透204](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-204.png)

通过重复这个计算实验吨吨时间和平均结果，我们获得了对渗透阈值的更准确估计。让X吨X吨是计算实验中开放站点的分数吨吨. 样本均值μμ提供渗透阈值的估计；样本标准差σσ测量阈值的锐度。

μ =X1+X2+ … +X吨吨μ=X1+X2+…+X吨吨,σ2=(X1- μ)2+ (X2- μ)2+ … + (X吨- μ)2吨− 1σ2=(X1-μ)2+(X2-μ)2+…+(X吨-μ)2吨-1

假设吨吨足够大（例如，至少 30），以下为渗透阈值提供了 95% 的置信区间：

[ μ -1.96 σ吨√, μ +1.96 σ吨√][μ-1.96σ吨,μ+1.96σ吨]

要执行一系列计算实验，请在`PercolationStats`使用以下 API 命名的 hw2 包中创建一个数据类型：

```
public class PercolationStats {
   public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
   public double mean()                                           // sample mean of percolation threshold
   public double stddev()                                         // sample standard deviation of percolation threshold
   public double confidenceLow()                                  // low endpoint of 95% confidence interval
   public double confidenceHigh()                                 // high endpoint of 95% confidence interval
}
```

`java.lang.IllegalArgumentException`如果`N`≤ 0 或≤ 0 ，构造函数应该抛出 a `T`。

构造函数应采用三个参数`N`、`T`和，并在逐个网格上`pf`执行`T`独立的计算实验（如上所述） 。使用这个实验数据，它应该计算渗透阈值的平均值、标准偏差和 95% 置信区间。执行计算时，不要忘记整数除法会向下舍入为整数！`N``N`

对于这部分硬件分配，您必须使用该`PercolationFactory`对象`pf`来创建新`Percolation`对象。**你不应该`new Percolation(N)`直接调用`PercolationStats`，因为这会弄乱自动评分器测试。**使用该`PercolationFactory`对象将允许我们将您的`PercolationStats`实现与解决方案`Percolation`实现一起使用，以避免级联错误。

此外，您必须使用[`edu.princeton.cs.introcs.StdRandom`](http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdRandom.html)来生成随机数，因为我们的测试套件依赖于这个库。您应该使用[`edu.princeton.cs.introcs.StdStats`](http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdStats.html)来计算样本均值和标准差，但如果您愿意，欢迎您编写自己的函数来计算统计数据。

## 运行时分析（未分级）

这部分硬件不会评分，但强烈建议您至少阅读并考虑以下几点：

- `Percolation`使用 QuickFindUF 中的快速查找算法实现数据类型。使用[秒表](http://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html)测量和`PercolationStats`的各种值的总运行时间。加倍如何影响总运行时间？加倍如何影响总运行时间？给出计算机上总运行时间（以秒为单位）的公式（使用波浪符号）作为 和 的单个函数。`N``T``N``T``N``T`
- 现在，`Percolation`使用 WeightedQuickUnionUF 中的加权快速联合算法实现数据类型。回答上一个项目符号中的相同问题。

## 提供的文件

我们提供了两个客户端作为大规模的视觉跟踪。我们强烈建议使用它们来测试和调试您的`Percolation`实现。

**交互式可视化客户端。** `InteractivePercolationVisualizer.java`使用鼠标作为输入，对在渗透系统中打开站点的结果进行动画处理。`N`它需要一个指定网格大小的命令行整数。作为奖励，它会以与`PercolationVisualizer`（如下所述）使用的相同格式打印出打开的站点序列，因此您可以使用它来准备有趣的文件以进行测试。如果您设计了一个有趣的数据文件，请随时在讨论论坛中分享。

**基于文件的可视化客户端。** `PercolationVisualizer.java`与第一个测试客户端类似，只是输入来自文件（而不是鼠标单击）。它通过执行以下步骤进行可视化：

- 从文件中读取网格大小 N。
- 创建一个 N×N 的站点网格（最初全部被阻止）。
- 读入一系列站点（第 i 行，第 j 列）以从文件中打开。打开每个站点后，使用标准绘图将完整站点绘制为浅蓝色，将打开的站点（未完整）绘制为白色，并将阻止的站点绘制为黑色，站点 (0, 0) 位于左上角。

对于输入文件 ，`input20.txt`程序应产生如下图所示的输出。这些图像分别对应于打开的 50、100、150、204 和 250 个站点。

![渗透50](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-50.png)![渗透100](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-100.png)![渗透150](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-150.png)![渗透204](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-204.png)![渗透250](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-250.png)

您可以通过将正确的参数传递到 IntelliJ 程序的`Edit Configurations...`选项卡（参数：）来在此输入上运行可视化器`input20.txt`。

**样本数据文件。**该`inputFiles`目录包含一些用于可视化客户端的示例文件，包括`input20.txt`我们在上面看到的。与每个输入`.txt`文件相关联的是一个输出`.png`文件，该文件在可视化结束时包含所需的图形输出。

## 提交

您应该按照通常的方式提交，即推送到 GitHub，然后在 Gradescope 上提交。

## 常问问题

#### `stddev()`如果 T 等于 1 ，应该返回什么？

样本标准偏差未定义。我们建议返回 Double.NaN 但我们不会测试这种情况。

#### 系统渗透后，`PercolationVisualizer`所有连接到底部打开站点的站点（除了连接到顶部打开站点的站点）都以浅蓝色显示。这种“反洗”可以接受吗？

虽然允许反洗并不严格符合 Percolation API，但它需要相当多的聪明才智来修复，如果不这样做只会导致小额扣除。

![渗滤](https://sp19.datastructur.es/materials/hw/hw2/images/percolation-backwash.png)

#### 如何在所有被阻止的站点中随机生成一个站点以供使用`PercolationStats`？

随机选择一个站点（通过使用`StdRandom`或其他一些库来生成 0（包括）和 N（不包括）之间的两个整数，如果它被阻止，则使用该站点；如果没有，请重复。

#### 当 N = 200 时，我没有得到可靠的计时信息`PercolationStats`。我该怎么办？

增加 N 的大小（比如增加到 400、800 和 1600），直到平均运行时间超过其标准偏差。

#### 我没有通过卡方测试，但通过了其他所有测试。

问题是您对多个模拟使用相同的随机种子，而统计测试发现它们是相同的。

如果您查看 的代码`StdRandom`，您会发现它只设置了一次种子（使用第一次`StdRandom`），从而防止了种子重置问题。简而言之，不要自己播种。

或者，确保您没有生成有偏差的随机数。您应该使用`StdRandom`生成整数而不是双精度数的方法。

#### 它告诉我我的代码报告“错误”，`percolates()`但是当我运行可视化工具时，我得到了真实的结果！

可视化器执行非常特定的`isOpen()`//调用序列`isFull()`。`percolates()`尝试创建自己的测试，只打开站点然后调用`percolates()`. 或者，禁用可视化工具中的所有`isOpen()`和/或`isFull()`调用，以便您可以专注于`percolates()`行为。或者，密切注意标有 的测试`Random Operation Order`。

#### 我的代码正在我的计算机上编译，但不在自动评分器上。

您的代码必须完全遵守 API。您不得向`Percolation`该类添加额外的公共方法或变量。当我们测试您的`PercolationStats`时，我们使用 Percolation 的参考版本而不是您的版本以避免级联错误——这意味着您不能假设任何其他公共方法可用。

#### 如何选择要运行的文件？如何在 IntelliJ 中传递命令行/程序参数？

首先，导航到运行 -> 编辑配置。您可以在这里设置不同的调试客户端来运行不同的类作为您的“主类”（这意味着程序将以主类内部的“main”函数开始和结束）。您可以使用左上角的小 + 号设置一个新客户端 - 就本课程而言，您只需要设置一个应用程序。然后，您可以随意命名您的配置，为该配置选择所需的主类，以及在程序参数字段中设置任何命令行/程序参数。就像在终端中一样将程序参数输入到该字段中 - 用空格分隔参数的单行。

## 学分

该作业最初由普林斯顿大学的 Kevin Wayne 和 Bob Sedgewick 开发，由 Josh Hug 为普林斯顿算法课程构建自动评分器。

