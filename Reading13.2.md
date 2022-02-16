## Reading 13.2 Heap

# 堆结构

我们之前看到，对于 PQ 操作具有最佳运行时间的已知数据结构是*二叉搜索树*。修改其结构和约束，我们可以进一步提高这些操作的运行时间和效率。

我们将定义我们的二元最小堆为**完整**且遵守**最小堆**属性：

- 最小堆：每个节点都小于或等于它的两个子节点
- 完成：如果缺少元素，都应该发生在最底层，其次，所有节点都尽可能靠着左边。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/heap-13.2.1.png)

正如我们在上图中看到的，绿色的堆是有效的，而红色的堆不是。最后两个不是因为它们违反了我们上面定义的至少一个属性。

```apl
第一个红色的是因为所有节点没有尽可能靠近左边
第二个红色的是因为没有保证“所有节点都小于等于其的子节点”
```

现在让我们考虑一下这种结构如何适用于我们在前一章中描述的抽象数据类型。我们将通过分析我们想要的操作来做到这一点。

**练习 13.2.1。**在给定这个堆结构的情况下，确定我们的 Priority Queue 接口的每个方法将如何实现。不要写实际代码，只写伪代码！

```java
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

public class MinHeap<Item> implements MinPQ<Item>{
    
    public void add(Item x){
        
    }
    
    public Item getSmallest(){
        
    }
    
    public Item removeSmallest(){
        
    }
    
    public int size(){
        return size;
    }
}
```

## 堆操作

我们关心 PriorityQueue ADT 的三个方法是`add`、`getSmallest`和`removeSmallest`。我们将从概念上描述在给定堆模式的情况下如何实现这些方法开始。

- `add`: 暂时添加到堆的末尾，沿着层次结构向上swim到合适的位置
  - 如果子节点 < 父节点，那么swim涉及到交换节点。
- `getSmallest`：返回堆的根（这由我们的*min-heap*属性保证是最小值）
- `removeSmallest`：将堆中的最后一项交换到根中。将层次结构下沉到适当的位置。
  - 如果父节点 > 子节点，下沉涉及交换节点。与最小的孩子交换以保留*最小堆*属性。

Great！我们已经确定了如何以有效的方式处理 PriorityQueue 接口指定的操作。但是我们如何实际编码呢？

**练习 13.2.2。**为上面指定的每个方法提供运行时。最坏情况和最好情况。

```apl
对于add操作，最好情况是θ(1)，插入的节点是很大的值
对于add操作，最坏情况是

对于getSmallest操作，无论好坏，情况都是θ(1)

对于removeSmallest，最好情况是
```

## 树表示

我们可以采用许多方法来表示树。

### 方法 1a、1b 和 1c

让我们考虑最直观并且之前学习过的树表示。我们将在节点及其子节点之间创建映射。有几种方法可以做到这一点，我们现在将探索。

- 在方法**Tree1A**中，我们考虑创建指向子节点的指针并将值存储在节点对象中。这些是为我们提供固定宽度节点的硬连线链接。我们可以观察代码：

```java
public class Tree1A<Key> {
  Key k;
  Tree1A left;
  Tree1A middle;
  Tree1A right;
  ...
}
```

这种结构的可视化如下所示。

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0216tree1a.png)

- 或者，在**Tree1B**中，我们探索使用数组来表示子节点和节点之间的映射。这会给我们提供可变宽度的节点，但也会尴尬的遍历和性能会更差。

```java
public class Tree1B<Key> {
  Key k;
  Tree1B[] children;
  ...
}
```

这种结构的可视化如下所示。

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0216tree1b.png)

- 最后，我们可以使用**Tree1C**的方法。这将与我们看到的通常方法略有不同。我们说节点不仅可以表示节点的子节点，还可以维护对其兄弟节点的引用。

```java
public class Tree1C<Key> {
  Key k;
  Tree1C favoredChild;
  Tree1C sibling;
  ...
}
```

这种结构的可视化如下所示。

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0216tree1c.png)

在所有这些方法中，我们都存储了对在我们下面的人的明确引用。这些显式引用采用指向实际 Tree 对象的指针的形式，这些对象是我们的子对象。让我们考虑一些不存储对子项的显式引用的更奇特的方法。

### 方法二

回忆一下不相交集 ADT。我们表示这种加权快速联合结构的方式是通过数组。

**为了表示一棵树，我们可以存储*键*数组以及*父*数组。keys 数组表示哪个索引映射到哪个键，而 parents 数组表示哪个键是另一个键的子键。(这也是我们在leetcode中常遇到的数组作为参数的方法时，参数的传递形式)**

```java
public class Tree2<Key> {
  Key[] keys;
  int[] parents;
  ...
}
```

![img](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0216treeway2.png)

花一些时间确保左边的树对应于右边数组中的表示。

是时候做一个非常重要的观察了！基于树的结构以及数组表示和树的图之间的关系，我们可以看到：

1. 树是**完整**的。这是我们之前定义的属性。
2. 父数组有一种冗余模式，其中元素只是加倍。
3. 读取树的级别顺序，我们看到它与*键*数组中键的顺序完全匹配。

**这是什么意思呢？我们观察到，在我们假设树是完整的情况下，那么parents数组是多余的，所以我们可以忽略它，并且我们知道一棵树可以用数组中的级别顺序表示。**

### 方法 3

在这种方法中，我们假设我们的树是完整的。这是为了确保我们的数组表示中没有“间隙”。因此，我们将采用树的这种复杂的 2D 结构并将其展平为一个数组。

```java
public class TreeC<Key> {
  Key[] keys;
  ...
}
```

使用上面的续图： ![img](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0216treeway3.png)

### 这将类似于本书中实现堆的方案，这是优先队列 ADT 的底层实现。

### swim

鉴于此实现，我们为堆操作部分中描述的“游泳”定义以下代码。

```java
public void swim(int k) {
    if (keys[parent(k)] ≻ keys[k]) {
       swap(k, parent(k));
       swim(parent(k));              
    }
}
```

父方法有什么作用？它使用方法 3 中的表示返回给定 k 的父代。

**练习 13.2.3。**编写父方法。对于额外的挑战，尝试编写查找给定项目的左孩子和右孩子的方法。

```java
//很容易找到规律并写出parent方法
public int parent(int k){
    return (k - 1) / 2;
} 

//这里需要注意，(0 - 1) / 2 = - 0.5 = 0
//我已经测试过了
public class Test {
    public static void main(String[] args){
        System.out.println((0-1)/2);
    }
}
//输出如下
0
Process finished with exit code 0

```













