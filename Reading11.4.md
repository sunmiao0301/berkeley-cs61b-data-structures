## Reading 11.4

# 旋转树

尽管 B 树非常平衡，但实际上很难实现。我们需要跟踪不同的节点，拆分过程非常复杂。作为欣赏好的代码和好的挑战的计算机科学家，让我们找到另一种方法来创建平衡树。

## BST结构

对于任何 BST，有多种方法可以构建它，以便您维护 BST 不变量。在第 11.1 章中，我们讨论了如何以不同的顺序**插入**元素将导致不同的 BST。下面的 BST 都由元素 1、2 和 3 组成，但都具有不同的结构。

```apl
  2    2 -> 1 -> 3 || 2 -> 3 -> 1
 / \
1   3

    3    3 -> 2 -> 1
   / 
  2
 /
1 

  1    1 -> 2 -> 3
   \
     2
       \
         3
         
    3   3 -> 1 -> 2
   / 
  1  
   \
    2
    
   1    1 -> 3 -> 2
    \
      3
     /
    2
```



然而，插入并不是为同一 BST 产生不同结构的唯一方法。我们可以做的一件事是通过一个叫做**旋转**的过程来改变已经存在节点的树。

## 树旋转

旋转的正式定义是：

```
rotateLeft(G): Let x be the right child of G. Make G the new left child of x.
rotateRight(G): Let x be the left child of G. Make G the new right child of x.
```

在接下来的几段中，我们将慢慢揭开这个过程的神秘面纱。下面是在节点 G 上左旋转时发生的情况的图形描述。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/Screen%20Shot%202019-03-06%20at%2010.25.18%20PM.png)

G 的右孩子 P 与 G 合并，并带上它的孩子。然后 P 将其左孩子传递给 G 并且 G 向下移动到左侧成为 P 的左孩子。您可以看到树的结构以及层数都发生了变化。我们也可以在非根节点上旋转。我们只是暂时断开节点与父节点的连接，在节点处旋转子树，然后重新连接新的根节点。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/Screen%20Shot%202019-03-06%20at%2010.37.17%20PM.png)

以下是[princeton docs]( [https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html\](https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html/)`rotateRight` )对于`rotateLeft`和`rotateRight`的实现，为简单起见省略了一些行。

```java
private Node rotateRight(Node h) {
    // assert (h != null) && isRed(h.left);
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    return x;
}

// make a right-leaning link lean to the left
private Node rotateLeft(Node h) {
    // assert (h != null) && isRed(h.right);
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    return x;
}
```

让我们再举几个例子来练习一下。

**练习 11.4.2**：给出一个旋转序列（`rotateRight(G)`, `rotateLeft(G)`），它将左边的树转换为右边的树。![img](https://joshhug.gitbooks.io/hug61b/content/assets/Screen%20Shot%202019-03-06%20at%2010.30.30%20PM.png)

**解决方案**：`rotateRight(3)`，`rotateLeft(1)`

通过旋转，我们实际上可以完全平衡一棵树。请参阅这些幻灯片中的演示：

# https://docs.google.com/presentation/d/1pfkQENfIBwiThGGFVO5xvlVp7XAUONI2BwBqYxib0A4/edit#slide=id.g465b5392c_00\

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0215rotate.png)

在下一章中，我们将学习通过使用旋转保持平衡的特定树数据结构。