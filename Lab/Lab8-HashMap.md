## Lab8：HashMap

## 介绍

在本实验中，您将创建**MyHashMap**，它是 Map61B 接口的一个实现，它表示一个哈希映射。这将与实验 7 非常相似，只是这次我们构建的是 HashMap 而不是 TreeMap。

完成实现后，您将把实现的性能与基于列表的 Map 实现`ULLMap`，以及Java内置的`HashMap`类（也使用哈希表）进行比较。

## MyHashMap

创建一个实现**Map61B**接口的**MyHashMap类。**您必须在名为`MyHashMap.java`文件中实现， 您的实现需要实现**Map61B**中给出的所有方法，除了`remove`。对于此方法，您应该抛出一个`UnsupportedOperationException`。请注意，这一次您应该实现`iterator`和 `keySet`，并且`iterator`返回一个迭代存储键的迭代器。**对于这些方法，我们建议您简单地创建一个包含所有Key的实例变量`HashSet`。**

此外，您应该实现以下构造函数：

```java
public MyHashMap();
public MyHashMap(int initialSize);
public MyHashMap(int initialSize, double loadFactor);
```

您的 MyHashMap 最初应该有等于 initialSize 的存储桶。当负载因子超过 loadFactor 时，您应该增加 MyHashMap 的大小。如果没有给出`initialSize`和`loadFactor`，你应该设置默认值`initialSize = 16`以及`loadFactor = 0.75` （就像 Java 的[内置](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html#())[**HashMap**](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html#())所做的那样）。调整大小时，请确保以乘法增加大小，而不是加法（例如乘以 2，不要加 100 或其他值）。**您不需要缩小尺寸。**您的 MyHashMap 操作都应该是恒定的摊销时间，假设插入的任何对象的 hashCode 都很好地 **spread**（提示：Java 中的每个 Object 都有自己的 hashCode 方法）。

**如果多次插入同一个键，则每次都应更新该键对应的值。（允许值相同，但不允许键相同，键相同会导致覆盖。）**

您可以假设`null`Key 永远不会被插入。

您应该使用单独的链接处理冲突。您不得导入除`ArrayList`、`LinkedList`、`HashSet`、`iterator`和`Set`以及之外的任何库。

您可以使用`TestMyHashMap.java`。

您可能会发现以下资源很有用：

- 来自我们的课程参考页面的[Data Structures Into Java](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf)第 136 和 137 页的 HashMap 代码。
- 我们可选的算法教科书的[第 3.4 章。](https://algs4.cs.princeton.edu/34hash)
- 来自我们可选教科书的[HashTable 代码代码。](http://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html)
- `ULLMap.java`（提供），一个基于**Map61B**实现的工作无序列表。**（p.s. 注意ULLMap是没有hash的，相当于把LinkedList里面的每个链表节点修改为了Entry）**
- 第 19 讲[幻灯片](https://docs.google.com/presentation/d/1QevjelsyVO8Ea375VRhIf-o--MIMDYB83OxBbXnbQZU/edit?usp=sharing)。

```java
//做了很久，这题还是很有意义的，主要就在于hashMap的实现，实际上就是Entry的组合。

```



## 那么……它有多快（Redux）？

`InsertRandomSpeedTest.java`和中提供了两个交互式速度测试`InsertInOrderSpeedTest.java`。**在完成MyHashMap**之前不要尝试运行这些测试。准备就绪后，您可以在 IntelliJ 中运行测试。

该类对您的**MyHashMap**、**ULLMap**（已提供）和 Java 的内置**HashMap**`InsertRandomSpeedTest`的元素插入速度进行测试。它通过询问用户输入大小来工作，然后生成长度为 10 的字符串并将它们作为 <String, Integer> 对插入到地图中。`N``N`

试一试，看看你的数据结构与`N`幼稚和工业强度的实现相比如何扩展。将您的结果记录在一个名为`speedTestResults.txt`. 您的结果不需要标准格式，也不需要数据点的数量。

现在尝试运行`InsertInOrderSpeedTest`，它的行为类似于`InsertRandomSpeedTest`，除了这次`<String, Integer>`键值对中的字符串按[字典顺序](http://en.wikipedia.org/wiki/Lexicographical_order)插入。请注意，与lab7 不同，您的代码应该在Java 内置解决方案的大致范围内——比如说在10 倍左右。这告诉我们，与最先进的 TreeMap 相比，最先进的 HashMap 相对容易实现。什么时候使用 BSTMap/TreeMap 而不是 HashMap 会更好？与您周围的人讨论这个问题，并将您的答案添加到`speedTestResults.txt`.

## 可选练习

这不会被评分，但您仍然可以收到有关自动评分器的反馈。

在您的**MyHashMap**类中实现方法`remove(K key)`和。对于一个额外的挑战，实现并且不使用第二个实例变量来存储密钥集。`remove(K key, V value)``keySet()``iterator`

对于，如果参数键在**MyHashMap**`remove`中不存在，则应返回 null 。否则，删除键值对（key, value）并返回值。

## 实验室汇报和提交

在实验结束时，您的助教会检查参考溶液。如果您还没有完成实验室，这将很有帮助，因为我们不希望您在实验室之外在实验室工作太多。（这也是你去实验室的动力！）

确保提交完成的`MyHashMap.java`and `speedTestResults.txt`，并像往常一样通过 git 和 Gradescope 提交。