### Reading 2.3 The DLList

在第 2.2 章中，我们构建了`SLList`类，它比我们之前的裸递归`IntList`数据结构更好。**在本节中，我们将结束对链表的讨论，并开始学习数组的基础知识，我们将其称为基于数组的列表`AList`。**一路走来，我们还将揭示为什么我们`SLList`在前一章中使用了这个尴尬的名字的秘密。

### addLast

考虑`addLast(int x)`上一章的方法。

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

这种方法的问题是它很慢。对于一个长列表，该`addLast`方法必须遍历整个列表，就像我们`size`在第 2.2 章中看到的方法一样。同样，我们可以尝试通过添加`last`变量来加快速度，以加速我们的代码，如下所示：

```java
public class SLList {
    private IntNode sentinel;
    private IntNode last;
    private int size;    

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }
    ...
}
```

**练习 2.3.1：**考虑上述的`SLList`实现的方框和指针图（也就是包括最后指针的那款）。假设我们想要支持`addLast`、`getLast`和`removeLast`操作。该结构是否支持快速`addLast`、`getLast`和`removeLast`操作？如果不是，哪些操作很慢？

![sllist_last_pointer.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/sllist_last_pointer.png)

**答案 2.3.1：** `addLast`和`getLast`会更快，但`removeLast`会很慢。那是因为在删除最后一个节点`last`后，我们没有简单的方法来获取倒数第二个节点来更新指针。

### SecondToLast

练习 2.3.1 中结构的问题在于，删除列表中最后一项的方法本身就会很慢。这是因为我们需要首先找到倒数第二个项目，然后将其下一个指针设置为空。添加`secondToLast`指针也无济于事，因为那样我们需要在列表中找到倒数第三项，以确保在删除最后一项`secondToLast`后`last`遵守适当的不变量。

**练习 2.3.2：**尝试设计一个加速`removeLast`操作的方案，使其始终以恒定时间运行，无论列表有多长。不要担心实际编写一个解决方案，我们将把它留给项目 1。只要想出一个关于如何修改列表结构（即实例变量）的想法。

我们将在改进#7 中描述解决方案。

```apl
根据“因为我们需要首先找到倒数第二个项目，然后将其下一个指针设置为空。添加`secondToLast`指针也无济于事，因为那样我们需要在列表中找到倒数第三项，以确保在删除最后一项`secondToLast`后`last`遵守适当的不变量。”

我们不难知道，想要删除最后一项很快，只有几种可能的思路
	1）双向链表。
	2）用数组维护每个节点，这样就能快速得到链表最后一个节点的信息。
	3）...
```

### 改进#7：Looking Back

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0127doublylinkedlist.png)

解决这个问题的最自然的方法是为每个添加一个先前的指针`IntNode`，即

```java
public class IntNode {
    public IntNode prev;
    public int item;
    public IntNode next;
}
```

换句话说，我们的列表现在每个节点都有两个链接。此类列表的一个常用术语是“双向链表”，我们将`Doubly Linked List` 简称为`DLList` 。这与第 2.2 章中的`single linked list`（`SLList`取名方式一样）

添加这些额外的指针将导致额外的代码复杂性。**您将在项目 1 中自己构建一个双向链表**，而不是引导您完成它。下面的框和指针图更准确地显示了双向链表分别对于大小为 0 和大小为 2 的列表的样子。

![dllist_basic_size_0.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_basic_size_0.png)

![dllist_basic_size_2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_basic_size_2.png)

### 改进#8：哨兵升级

**反向指针允许列表支持在恒定时间内添加、获取和删除列表的前面和后面。这种设计有一个微妙的问题，`last`指针有时指向哨兵节点，有时指向真实节点。就像 的非哨兵版本一样`SLList`，这导致具有特殊情况的代码比我们在第 8 次也是最后一次改进后得到的要丑陋得多。**（你能想到哪些`DLList`中的方法会有这些特殊情况吗？）

一种解决方法是将第二个哨兵节点添加到列表的后面。这导致下面显示为框和指针图的拓扑。

![dllist_double_sentinel_size_0.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_double_sentinel_size_0.png)

![dllist_double_sentinel_size_2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_double_sentinel_size_2.png)

另一种方法是实现列表，使其是循环的，前后指针共享同一个哨兵节点。

![dllist_circular_sentinel_size_0.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_circular_sentinel_size_0.png)

![dllist_circular_sentinel_size_2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_circular_sentinel_size_2.png)

双哨兵和循环哨兵方法都可以工作，并导致代码没有丑陋的特殊情况，尽管我个人认为循环方法更清洁，更美观。我们不会讨论这些实现的细节，因为您将有机会在项目 1 中探索一个或两个。

### 通用 DLLists

们的 DLLists 有一个主要限制：它们只能保存整数值。例如，假设我们想要创建一个字符串列表：

```java
DLList d2 = new DLList("hello");
d2.addLast("world");
```

上面的代码会崩溃，因为我们的`DLList`构造函数和`addLast`方法只接受一个整数参数。

**幸运的是，在 2004 年，Java 的创建者将泛型添加到该语言中，这将允许您创建包含任何引用类型（p.s. String是引用类型）的数据结构等。**

一开始，语法有点奇怪。基本思想是，在类声明中的类名之后，您可以在尖括号内使用任意占位符：`<>`. 然后，在您想使用任意类型的任何地方，您都可以使用该占位符。

比如我们`DLList`之前的声明是：

```java
public class DLList {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;
        ...
    }
    ...
}
```

可以容纳任何类型的泛型`DLList`如下所示：

```java
public class DLList<BleepBlorp> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public BleepBlorp item;
        public IntNode next;
        ...
    }
    ...
}
```

在这里，`BleepBlorp`这只是我编的一个名称，您可以使用您可能愿意使用的大多数其他名称，例如`GloopGlop`，`Horse`，`TelbudorphMulticulus`或其他任何名称

现在我们已经定义了`DLList`类的通用版本，我们还必须使用特殊的语法来实例化这个类。为此，我们在声明期间将所需类型放在尖括号内，并在实例化期间使用空尖括号。例如：

```java
DLList<String> d2 = new DLList<>("hello");
d2.addLast("world");
```

**由于泛型仅适用于引用类型，因此我们不能将原语`int`或是`double`放在尖括号中，例如`<int>`. 相反，我们应该使用原语int的的引用版本Integer，又例如**

```java
DLList<Integer> d1 = new DLList<>(5);
d1.insertFront(10);
```

使用泛型类型还有一些细微差别，但我们会将它们推迟到本书后面的章节，当你有更多机会自己尝试它们时。现在，请记住以下经验法则：

- **在实现**数据结构的 .java 文件中，仅在文件最顶部的类名之后指定一次泛型类型名称。

- **在使用您的数据结构的其他 .java 文件中，在声明期间指定特定的所需类型，并在实例化期间使用空菱形运算符。**

  ```java
  DLList<Integer> d1 = new DLList<>(5);
  // or
  DLList<Integer> d1;
  d1 = new DLList<>(5);
  ```

- 如果您需要在原始类型上实例化泛型，请使用`Integer`, `Double`, `Character`, `Boolean`, `Long`, `Short`, `Byte`, 或`Float`代替它们的原始等价物。

**次要细节：您也可以在实例化时在尖括号内声明类型，但这不是必需的，只要您还在同一行上声明变量即可。换句话说，以下代码行是完全有效的，但是`Integer`右侧的 是多余的。**

```java
DLList<Integer> d1 = new DLList<Integer>(5);
```

至此，您了解了`LinkedListDeque`在项目 1 上实施该项目所需了解的一切，您将在其中精炼您在第 2.1、2.2 和 2.3 章中获得的所有知识。

#### 接下来是什么

- [项目 1](https://sp19.datastructur.es/materials/proj/proj1a/proj1a)的第一部分，您在其中实施`LinkedListDeque.java`。