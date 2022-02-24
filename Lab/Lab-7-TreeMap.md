## Lab 7：TreeMap 树状图

## 介绍

在本实验中，您将创建**BSTMap**，这是 Map61B 接口的基于 BST 的实现，它表示基于树的图。

完成实现后，您将把实现的性能与基于列表的 Map 实现`ULLMap`以及内置 Java`TreeMap`类（也使用 BST）进行比较。

## BST Map

创建一个使用 BST（二叉搜索树）作为其核心数据结构，实现Map61B接口的BSTMap类。您必须在名为`BSTMap.java` 文件中完成，您的实现需要实现Map61B中给出的所有方法，除了`remove`，`iterator`和`keyset`。对于这些方法，您应该抛出一个``UnsupportedOperationException`。

在您的实现中，您应该假设泛型 K，`BSTMap<K,V>`并且extends [Comparable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html)。

以下资源可能有用：

- 来自我们的课程资源页面的[Data Structures Into Java](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf)第 109 和 111 页的 BST 代码。

- [我们可选教科书](https://algs4.cs.princeton.edu/32bst/BST.java.html)中的BST 代码。

- `ULLMap.java`（提供），一个基于**Map61B**实现的工作无序列表。

  ```apl
  ULLMap.java就是一个链表，但是链表节点存储的不是简单的整型数，而是一个个Entry<K, V>
  ```

  

- 第16讲[幻灯片](https://docs.google.com/presentation/d/1NkqojSVwLwdauC_SC5A2DplVx6AswzeXgn9LFi95APU/edit#slide=id.g50738e5fde_0_0)。

您的 BSTMap 还应该添加一个附加方法`printInOrder()`（未在**Map61B**界面中给出）以 Key 增长的顺序打印出您的**BSTMap 。**您会发现这对测试您的实现很有帮助！

您可以使用`TestBSTMap.java`来测试你的`BSTMap.java`.

## 那么……它有多快？

`InsertRandomSpeedTest.java`和中提供了两个交互式速度测试`InsertInOrderSpeedTest.java`。**在完成BSTMap**之前不要尝试运行这些测试。准备就绪后，您可以在 IntelliJ 中运行测试。

该类对**BSTMap**、**ULLMap**（已提供）、Java 的内置**TreeMap**和 Java 的内置**HashMap**（您将在下一个实验室中进一步探索）`InsertRandomSpeedTest`的元素插入速度进行测试。它通过向用户询问要插入的每个字符串的所需长度以及输入大小（要执行的插入次数）来工作。然后它生成指定长度的许多字符串，并将它们作为 <String,Integer> 对插入到映射中。

试一试，看看你的数据结构如何随着插入次数而扩展，与幼稚和工业强度的实现相比。将您的结果记录在一个名为`speedTestResults.txt`. 您的结果不需要标准格式，也不需要数据点的数量。

现在尝试运行`InsertInOrderSpeedTest`，它的行为类似于`InsertRandomSpeedTest`，除了这次`<String, Integer>`键值对中的字符串按[字典顺序](http://en.wikipedia.org/wiki/Lexicographical_order)插入。如果您观察到任何有趣的事情（希望您做到了），您应该与您的同学和/或助教讨论。

## 可选练习

这不会被评分，但您仍然可以收到有关自动评分器的反馈。

在您的**BSTMap**类中实现方法`iterator()`、`keySet()`、`remove(K key)`和。实施相当具有挑战性。对于一个额外的挑战，实现并且不使用第二个实例变量来存储密钥集。`remove(K key, V value)``remove()``keySet()``iterator`

对于，如果参数键在**BSTMap**`remove`中不存在，则应返回 null 。否则，删除键值对（key, value）并返回值。

## 实验室汇报和提交

在实验结束时，您的助教会检查参考溶液。如果您还没有完成实验室，这将很有帮助，因为我们不希望您在实验室之外在实验室工作太多。（这也是你去实验室的动力！）

确保提交完成的`BSTMap.java`and `speedTestResults.txt`，并像往常一样通过 git 和 Gradescope 提交。

## 可选渐近问题

给定`B`一个带有键值对的**BSTMMap**和一个随机键值对，回答以下问题。`N``(K, V)`

除非另有说明，否则“big-Oh”界限（例如`O(N)`）和“big-Theta”界限（例如 Θ( `N`)）是指给定方法调用**中的比较次数。**

对于问题 1-7，说明该陈述是对还是错。对于问题 8，给出运行时界限。

1. `B.put(K, V)`∈ O(log(· `N`))。

2. `B.put(K, V)`∈ Θ(log(· `N`))。

3. `B.put(K, V)`∈ Θ( `N`)。

4. `B.put(K, V)`∈ O( `N`)。

5. `B.put(K, V)`∈ O( `N`2 )。

6. 平均而言，完成 N 次随机调用的比较总数，`B.put(K, V)`后跟`B.containsKey(K)`∈ ~2(ln( `N`))

   ```
    Note: We write g(N)~f(N) to represent that ~g(N)/f(N) -> 1 as N gets large.
   ```

7. 对于键`C`!= `K`，同时运行`B.containsKey(K)`和`B.containsKey(C)`∈ Ω(log( `N`))。

8. 让**BSTMap** `b`由一个`root`节点（键、值对）和两个称为和的**BSTMap**子树组成。此外，假设该方法返回 BSTMap 的根节点数**，**并且运行时间为 Θ ，其中是**BSTMap**中的节点数。某个正整数的运行时间（大 O 表示法）是多少？给出你可以假设有节点的最严格的界限。您的答案不应包含任何不必要的乘法常数或加法因子。`left``right``numberOfNodes(BSTMap b)``b.root``(n)``n``b``mystery(b, z)``z``b``N`

   ```
    public Key mystery(BSTMap b, int z) {
        if (z > numberOfNodes(b) || z <= 0)
            return null;
        if (numberOfNodes(b.left) == z-1)
            return b.root.key;
        else if (numberOfNodes(b.left) > z)
            return mystery(b.left, z);
        else
            return mystery(b.right, z-numberOfNodes(b.left) - 1);
    }
   ```