# Reading 10.2 二叉搜索树

现在我们将学习可能是有史以来最重要的数据结构。

链接列表很棒，但是即使列表已排序，搜索项目也需要很长时间！如果该项目位于列表的末尾怎么办？那将需要线性时间！

我们知道，对于一个数组，我们可以使用二分查找来更快地找到一个元素。具体来说，log(n) 时间。有关二分搜索的简短说明，请查看此[链接](https://www.geeksforgeeks.org/binary-search/)。

TL;DR：在二分搜索中，我们知道列表已排序，因此我们可以使用此信息来缩小搜索范围。首先，我们看中间元素。如果它比我们要搜索的元素大，我们看它的左边。如果它小于我们正在搜索的元素，我们向右看。然后我们查看各个部分的中间元素并重复该过程，直到找到我们正在寻找的元素（或者因为列表不包含它而没有找到它）。

但是我们如何对链表进行二分搜索呢？我们必须一直遍历到中间才能检查那里的元素，这本身就需要线性时间！

我们可以实现的一种优化是引用中间节点。这样，我们可以在恒定时间内到达中间。

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0215addmidpointer.png)

然后，如果我们翻转节点的指针（形成双指针），允许我们遍历左右两半，我们可以将运行时间减少一半！

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0215turnbacksome.png)

但是，我们可以做得更好。我们可以通过向每个**递归半部分**的中间添加指针来进一步优化，就像这样。

现在，如果你垂直拉伸这个结构，你会看到一棵树！

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0215recursiondofindmidpointer.png)

这种特定的树被称为**二叉树**，因为每个节点都分裂为 2。

### 树的属性

让我们更加形式化树数据结构。

树由以下部分组成：

- 节点
- 连接这些节点的边。
  - **约束**：任意两个节点之间只有一条路径。

在某些树中，我们选择一个没有父节点的节点作为我们的根节点**。**

树也有**叶子**，它们是没有子节点的节点。

**练习 10.2.1：**你能举出一个无效树的例子吗？

将其与我们之前提出的原始树结构相关联，我们现在可以将新的约束引入到已经存在的约束中。这会创建更多特定类型的树，两个示例是二叉树和二叉搜索树。

- **二叉树**：除上述要求外，还持有二叉属性约束。也就是说，每个节点有 0、1 或 2 个子节点。
- **二叉搜索树**：除了上述所有要求外，还具有对于树中的每个节点 X 的属性：
  - 左子树中的每个键都小于 X 的键。
  - 右子树中的每个键都大于 X 的键。**记住这个属性！！在本模块和 61B 的整个过程中，我们将大量引用它。

这是我们将在此模块中使用的 BST 类：

```java
private class BST<Key> {
    private Key key;
    private BST left;
    private BST right;

    public BST(Key key, BST left, BST Right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BST(Key key) {
        this.key = key;
    }
}
```

## 二叉搜索树操作

### 搜索

为了搜索某些东西，我们使用了二分搜索，由于上一节中描述的 BST 属性，这很容易！

我们知道 BST 的结构使得节点右侧的所有元素都更大，而左侧的所有元素都更小。知道了这一点，我们可以从根节点开始，将它与我们正在寻找的元素 X 进行比较。如果 X 大于根，我们继续到根的右孩子。如果它更小，我们继续到根的左孩子。我们递归地重复这个过程，**直到我们找到对于该元素值的树节点，或者我们到达一个叶子（然后因为叶子节点的左右节点都是空值，所以在这种情况下，树不包含该项目。）**

**练习 10.2.2：**试着自己写这个方法。这是方法头：`static BST find(BST T, Key key)`

```java
static BST find(BST T, Key key){
    if(T == null)
        return null;
    /*
    这一步没注意考虑泛化一下，因为key不是int，得像标准答案一样，用equals进行。
    */
    else if(T.key == key)
        return T;
    else if(T.key > key)
        return find(T.left, key);
    else
        return find(T.right);
}
```

下面是标准答案：

```java
static BST find(BST T, Key sk) {
   if (T == null)
      return null;
   if (sk.equals(T.key))
      return T;
   else if (sk ≺ T.key)
      return find(T.left, sk);
   else
      return find(T.right, sk);
}
```

如果我们构造的树比较“浓密”，find 操作会运行在log (n)，因为树的高度是 logn，这非常快！

### 插入

**对于二叉搜索树，我们插入的总是叶子节点！**

首先，我们在树中搜索节点。如果我们找到它，那么我们什么也不做。如果我们没有找到它，我们将已经在一个叶节点处。此时，我们可以将新元素添加到叶子的左侧或右侧，保留 BST 属性。

**练习 10.2.3：**试着自己写这个方法。这是方法头：`static BST insert(BST T, Key ik)`. 它应该**返回完整的 BST，并将新节点插入正确的位置。**

```java
static BST insert(BST T, Key ik){
    if(ik.equals(T.key))
        return null;
    /*
    下面 8 行代码可以优化为：
	if (T == null)
    	return new BST(ik);
    if (ik ≺ T.key)
    	T.left = insert(T.left, ik);
    else if (ik ≻ T.key)
    	T.right = insert(T.right, ik);
    */
    if(ik > T.key && T.right != null)
        return insert(T.right, ik);
    else if(ik > T.key && T.right == null)
        T.right = new BST(ik);
    
    if(ik < T.key && T.left != null)
        return insert(T.left, ik);
    else if(ik < T.key && T.left == null)
        T.left = new BST(ik);
    //但是我这个写法最终没法返回完整的BST，所以还是得像标准答案一样，或者我得加一个void helper()函数，让这个函数insert来调用helper，执行完毕后返回root。
}
```

下面是标准答案：

```java
static BST insert(BST T, Key ik) {
  if (T == null)
    return new BST(ik);
  if (ik ≺ T.key)
    T.left = insert(T.left, ik);
  else if (ik ≻ T.key)
    T.right = insert(T.right, ik);
  return T;
}
```

**练习 10.2.4：**想一想会导致不同高度的树的插入顺序。尝试找出树高的两种极端情况。提示：您的第一次插入将决定后续插入的大部分行为。

```apl
一直需要插入比当前树中最大节点还大的，会导致极端树高。
```

### 删除

从二叉树中删除有点复杂，因为每当我们删除时，我们需要确保我们重建树并仍然保持它的 BST 属性。

让我们将这个问题分为三类：

- 我们试图删除的节点没有子节点
- 有1个孩子
- 有2个孩子

##### 没有孩子

如果节点没有子节点，它就是叶子，我们可以删除它的父指针，最终节点会被[垃圾收集器](https://stackoverflow.com/questions/3798424/what-is-the-garbage-collector-in-java)扫走。

##### 一个孩子

如果节点只有一个子节点，我们知道子节点与节点的父节点保持 BST 属性，因为该属性递归到左右子树。因此，我们只需将父节点的子指针重新分配给节点的子节点，该节点最终将被垃圾回收。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/Screen%20Shot%202019-02-28%20at%2010.35.56%20AM.png)

##### 两个孩子

**如果节点有两个孩子，这个过程会变得有点复杂，因为我们不能只分配一个孩子作为新的根。这可能会破坏 BST 属性。**

相反，我们选择一个新节点来替换已删除的节点。

我们知道新节点必须：

- 大于左子树中的所有内容。
- 比一切都正确的子树。

在下面的树中，我们展示了哪些节点将满足这些要求，因为我们正在尝试删除`dog`节点。

要找到这些节点，您可以只取左子树中最右边的节点或右子树中最左边的节点。

然后，我们用or替换`dog`节点，然后删除旧的or节点。`cat``elf``cat``elf`

这称为**Hibbard 删除**，它在删除过程中出色地维护了 BST 属性。



## 作为集合和映射的 BST

我们可以使用 BST 来实现`Set`ADT！但它甚至更好，因为在 ArraySet 中，运行`contains`命令，最坏的情况是 o ( *n* )运行时，因为我们需要搜索整个集合。但是，如果我们使用 BST，我们可以将此运行时间减少到 log (n) 因为 BST 属性使我们能够使二分搜索！

`(key,value)`我们还可以通过让每个 BST 节点保存对而不是单个值来将二叉树制作成映射。我们将比较每个元素的键，以确定将其放置在树中的哪个位置。

## 概括

抽象数据类型 (ADT) 是根据操作而不是实现来定义的。

几个有用的 ADT：

- Disjoint Sets, Map, Set, List.
- Java 提供了 Map、Set、List 接口以及几个实现。

我们已经看到了两种实现 Set（或 Map）的方法：

- ArraySet：Θ ( *N* )最坏情况下的操作。
- BST：如果树是平衡的，则操作时是Θ (log*N* )。

BST 实现：

- 搜索和插入很简单（但插入有点棘手）。
- 删除更具挑战性。典型的方法是“Hibbard 删除”。

#### 接下来是什么

- [实验室 7](https://sp19.datastructur.es/materials/lab/lab7/lab7)
- [讨论 7](https://sp19.datastructur.es/materials/discussion/disc07.pdf)