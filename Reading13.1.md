## Reading 13.1 堆和优先队列 Heaps and Priority Queues 

## 优先队列接口

我们学到的最后一个**ADT是二叉搜索树，它允许我们在 logN 内有效地搜索**。这是因为我们可以在搜索的每一步中消除一半的元素。

But，如果我们更关心快速找到*最小*或*最大*元素而不是快速搜索呢？

现在我们来看看**优先队列**的抽象数据类型。要理解此 ADT，请考虑一袋物品。您可以将物品添加到此包中，可以从此包中删除物品等。但是需要注意的是，您只能与此包中最小的物品进行交互。

```java
/** (Min) Priority Queue: Allowing tracking and removal of 
  * the smallest item in a priority queue. */
public interface MinPQ<Item> {
    /** Adds the item to the priority queue. */
    public void add(Item x);
    /** Returns the smallest item in the priority queue. */
    public Item getSmallest();
    /** Removes the smallest item from the priority queue. */
    public Item removeSmallest();
    /** Returns the size of the priority queue. */
    public int size();
}
```

## 优先队列思路

我们将在哪里实际使用或需要它？

- 考虑我们正在监视公民之间的短信并希望跟踪不和谐对话的场景。
- 每天，你准备一份报告，报告中包含最不和谐的 M 条消息，并且这个报告使用方法`HarmoniousnessComparator`收集

让我们采用这种方法：将我们全天收到的所有消息收集到一个列表中。排序这个列表并返回前 M 个消息。

```java
public List<String> unharmoniousTexts(Sniffer sniffer, int M) {
    ArrayList<String>allMessages = new ArrayList<String>();
    for (Timer timer = new Timer(); timer.hours() < 24; ) {
        allMessages.add(sniffer.getNextMessage());
    }

    Comparator<String> cmptr = new HarmoniousnessComparator();
    Collections.sort(allMessages, cmptr, Collections.reverseOrder());

    return allMessages.sublist(0, M);
```

潜在的缺点？这种方法将使用 Θ ( *N* ) 的空间，但是实际我们只需要使用 Θ (*M*) 空间。

p.s. 因为我们的功能就是返回M个消息，其余的消息我们不关心。

**练习 13.1.1. **重新完成上面列出的方法`unharmoniousTexts`，并使其具有Θ (*M*)空间。

最终，教师的实现**思路**是：

```java
//之前的问题在于，我们只需要最不和谐的M个消息即可，但是我们却维护了一个包含所有N个消息的数组链表。所以我们需要改良的部分在于不要其余的 N - M 个消息

//也就是，往一个最大存储内存为M+1的数据结构中放东西，但是实际上+1是不存在的，因为会被排出，比如：
//当目前是M个消息在结构中，然后此时需要放入一个新消息，那么就放进去
//然后此时是M+1个消息在其中，但是这个状态无法保持，需要拿出一个来保证结构中只有M个。
//于是就removeSmallest() （我们只能与包中最小的物体进行交互）
public List<String> unharmoniousTexts(Sniffer sniffer, int M){
    Comparator<String> cmptr = new HarmoniousnessComparator();
    MinPQ<String> unharmoniousTexts = new HeapMinPQ<Transaction>(cmptr);
    for(Timer timer = new Timer(); timer.hours() < 24;){
        unharmoniousTexts.add(sniffer.getNextMessage());
        if(unharmoniousTexts.size()) > M)
        	{unharmoniousTexts.removeSmallest();}
    }
    ArrayList<String> textlist = new ArrayList<String>();
    while(unharmoniousTexts.size() > 0){
        textlist.add(unharmoniousTexts.removeSmallest());
    }
    return textlist;
}
```

## 优先队列实现

我们使用 Priority Queue ADT 解决了同样的问题，使内存更高效。我们可以观察到代码稍微复杂一些，但情况并非总是如此。

请记住，ADT 是类似的接口，但是还未实现。

接下来，让我们考虑使用我们已经知道的数据结构实现的可能实现，分析我们所需操作的**最坏情况**运行时：

- **有序**数组
  - `add`：Θ ( *N* ) --- 之所以是N，是因为可能触发数组大小resize()
  - `getSmallest`：θ ( 1 )
  - `removeSmallest`：Θ ( *N* ) --- 之所以是N，是因为可能触发数组大小resize()
- 浓密的 BST --- 但是使用二叉树存在一个问题，那就是不允许重复，而我们想要实现的优先队列，应该是需要允许重复现象存在的。
  - `add`：Θ (log N)
  - `getSmallest`：Θ (log N)
  - `removeSmallest`：Θ (log N)
- 哈希表 --- 毫无疑问，哈希表无法让我们快速拿到最小值，直接Θ ( *N* )了...
  - `add`：θ ( 1 )
  - `getSmallest`：Θ ( *N* )
  - `removeSmallest`：Θ ( *N* )

**练习 13.1.2。**解释上面的每个运行时。每种数据结构的缺点是什么？描述一种修改这些数据结构以提高性能的方法。我们能比这些建议的数据结构做得更好吗？

```apl
对于有序数组，我想的不是太明白
对于BST实现方案，我觉得可以对最左下角的树节点维护一个指针，以此来快速得到最小值。但是删除导致的树的结构修改有点麻烦。
对于哈希表，就是纯纯牛马思路了
```

## 概括

- Priority Queue 是一种抽象数据类型，可优化处理最小或最大元素。
- 使用这种专门的数据结构可以带来空间/内存优势。
- 我们目前知道的 ADT 的实现并没有为我们提供 PQ 操作的有效运行时。
  - 其他结构中的二叉搜索树是最有效的















