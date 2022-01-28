## Reading 2.5 AList

在本节中，我们将构建一个名为的新类，该类`AList`可用于存储任意长的数据列表，类似于我们的`DLList`. 与`DLList`不同的是，`AList`将使用数组而不是链表来存储数据。

### 链表性能Puzzle

假设我们想为双向链表`DLList`被调用写一个新方法`int get(int i)`。为什么长列表的`get`与`getLast`相比，会更慢？对于哪些输入，它会特别慢？

您可能会发现下图有助于思考您的答案。

![dllist_circular_sentinel_size_2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_circular_sentinel_size_2.png)

```apl
我认为是因为最后一个值是明确位置的，作为双向链表，其可以直接拿到
但是如果是从中间拿（从前从后开始取都要遍历相等长度的位置的话，就会特别慢）
```

### 链表性能Puzzle解决方案

事实证明，如果我们使用 2.3 节中描述的双向链表结构慢，那么无论你多么聪明，该`get`方法通常都会比`getBack`慢。

这是因为，由于我们只有对列表的第一个和最后一个项目的引用，我们总是需要从前面或后面遍历列表才能找到我们要检索的项目。例如，如果我们想在长度为 10,000 的列表中获取项目 #417，我们将必须遍历 417 个前向链接才能到达我们想要的项目。

在最坏的情况下，项目位于最中间，我们需要遍历与列表长度成正比的项目数量（特别是项目数量除以 2）。换句话说，我们最坏情况下的执行时间`get`与整个列表的大小成线性关系。这与 for 的运行时相反`getBack`，无论列表的大小如何，它都是恒定的。在课程的后面，我们将根据大 O 和大 Theta 符号正式定义运行时。现在，我们将坚持非正式的理解。

### 我们的第一次尝试：基于数组的天真列表

在现代计算机上访问数组的第`i `个元素需要恒定的时间。这表明基于数组的列表的`get`比基于链表的解决方案具有更好的性能，因为它可以简单地使用括号表示法来获取感兴趣的项目。

如果您想知道**为什么**数组具有恒定时间访问权限，请查看这篇[Quora 帖子](https://www.quora.com/Why-does-accessing-an-array-element-take-constant-time)，或者如下

The easiest way to see is how C evaluates an array.

Let's say you have

```c
int x, my_array[1000], *array_p = &my_array[0]; 
 
my_array[500] = 1234; 
```

You can access the 500th element by doing

```c
x = my_array[500];
```

or you can access it by doing

```c
x = *(array_p + 500);
```

The second access, using C "pointer arithmetic", shows that finding the i'th element of an array is just a small bit of calculation.

Yes, this fact is rooted in the principle of "random access memory"; strictly speaking, your entire memory space is just a massive array, with formatting imposed by your programming language memory structures. Everything from C unsigned char arrays to the most elaborate Java object model is ultimately just formatting imposed on flat "arrays" of memory.

Note that [Locality of reference](https://en.wikipedia.org/wiki/Locality_of_reference) issues may affect the time to access entries in larger arrays, but even there the lookup time is still "constant".

**可选练习 2.5.1：**尝试构建一个支持`addLast`、`getLast`、`get`和`size`操作的 AList 类。您的 AList 应该适用于最多 100 个大小的数组。有关入门代码，请参阅https://github.com/Berkeley-CS61B/lectureCode/tree/master/lists4/DIY。

```java
import javax.xml.bind.NotIdentifiableEvent;

public class AList {
    /**
     * inner class and don't use any method in class AList
     * so I used static
     */
    public static class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    /**
     * this is the varieties in AList
     */
    public int size;
    public Node last;
    public Node[] Array;

    /**
     * @param n
     * constructor (func
     */
    public AList(int n){
        size = 1;
        last = new Node(n);
        Array = new Node[100];
        Array[0] = last;
    }

    public void addLast(int n){//the int n is from 0 to 100
        last.next = new Node(n);
        last = last.next;
        size++;
        Array[size - 1] = last;
    }

    public int getLast(){
        return  last.val;
    }

    public int get(int n){
        return Array[n].val;
    }

    public int size(){
        return size;
    }

    // Test
    public static void main(String[] args){
        AList a = new AList(1);
        a.addLast(2);
        a.addLast(3);
        System.out.println("the value of the first node in AList a is " + a.get(0));
        System.out.println("the value of the second node in AList a is " + a.get(1));
        System.out.println("the value of the last node in AList a is " + a.getLast());
        System.out.println("the size of AList a is " + a.size());
    }
}
```

测试结果如下：

```bash
D:\JDK8\jdk1.8.0_311\bin\java.exe 
the value of the first node in AList a is 1
the value of the second node in AList a is 2
the value of the last node in AList a is 3
the size of AList a is 3

Process finished with exit code 0
```

教师的方案如下：（与我的方案不同在于，他这个已经不是List了，纯纯是以一个数组，加上一个size变量（size变量起一个指针的作用）

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0127AList.png)

[教师的解决方案](https://github.com/Berkeley-CS61B/lectureCode/tree/master/lists4/naive)有以下方便的不变量。

- 要插入（使用`addLast`）的下一个项目的位置始终是`size`。
- AList 中的项目数始终为`size`。
- 列表中最后一项的位置始终为`size - 1`。

其他解决方案可能略有不同。

#### 做到这里有个体悟如下

```Apl
/**
做到后面的时候，我才发现，教师的做法其实才是最合适的，因为：
1）要么就直接用单向，双向链表，别用数组，此时是支持addAtIndex和addFirst的
2）要么就全部用数组实现，但是这样是不支持addAtIndex和addFirst的
像我这样用数组 还用链表之间的next，实在是有些蠢了
3）其次，当你构造的是以链表而不是数组为基础的时候，最好加上哨兵节点，这会让你后面的代码很好看。
*/
```

**所以模仿教师的，实现如下：**

```java
public class AList {
    /** Creates an empty list. */
    int[] array;
    int last;//use int as a pointer

    public AList() {
        array = new int[100];
        last = -1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        array[last + 1] = x;
        last++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return array[last];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return array[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return last + 1;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int res = array[last];
        last--;
        return res;
    }

    //Test
    public static void main(String[] args){
        AList a = new AList();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        System.out.println("the value of first node is " + a.get(0));
        System.out.println("the value of last node is " + a.getLast());
        System.out.println("the size of this AList is " + a.size());
        System.out.println(a.removeLast() + " was deleted");
        System.out.println("now the value of last node is " + a.getLast());
    }
}
```

### removeLast

我们需要支持的最后一个操作是`removeLast`. 在开始之前，我们进行以下关键观察：对列表的任何更改都必须反映在我们实现中一个或多个内存盒的更改中。

这可能看起来很明显，但它有一些深刻的意义。该列表是一个抽象概念，而`size`、`items`和`items[i]`记忆框是该概念的具体表示。用户尝试使用我们提供的抽象（`addLast`, `removeLast`）对列表进行的任何更改都必须以符合用户期望的方式反映在对这些内存盒的一些更改中。我们的不变量为我们提供了这些变化应该是什么样子的指南。

**可选练习 2.5.2：**尝试编写`removeLast`. 在开始之前，决定`size`、`items`和中的哪一个`items[i]`需要更改，以便在操作后保留我们的不变量，即，以便将来对我们方法的调用为列表类的用户提供他们期望的行为。

```java
    public int removeLast() {
        int res = array[last];
        last--;
        return res;
    }
```

### 天真的 Resizing Array

**可选练习 2.5.3：**假设我们有一个处于下图所示状态的 AList。如果我们`addLast(11)`会发生什么？对于这个问题我们应该怎么做？

![dllist_circular_sentinel_size_2.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig25/full_naive_alist.png)

在 Java 中，答案是我们只需构建一个大到足以容纳新数据的新数组。例如，我们可以想象如下添加新项目：

```java
int[] a = new int[size + 1];
System.arraycopy(items, 0, a, 0, size);
a[size] = 11;
items = a;
size = size + 1;
```

创建新数组和复制项目的过程通常称为“调整大小”。这有点用词不当，因为数组实际上并没有改变大小，我们只是在制作一个更大的**新数组。**

**练习 2.5.4：**尝试实现`addLast(int i)`调整数组大小的方法。

**做到这里，由于我有一定的数据结构基础，所以就不踩坑了，直接翻倍调整大小**

```java
    public void addLast(int x) {
        if(last + 1 == array.length){
            int[] a = new int[2 * array.length];
            System.arraycopy(array, 0, a ,0, last);
            array = a;
        }
        array[last + 1] = x;
        last++;
    }
```

### 分析天真的 Resizing Array

我们在上一节中尝试的方法性能很差。通过运行一个简单的计算实验，我们调用了`addLast`100,000 次，我们看到`SLList`完成如此之快，以至于我们甚至无法计时。相比之下，我们基于数组的列表需要几秒钟。

要了解原因，请考虑以下练习：

**练习 2.5.5：**假设我们有一个大小为 100 的数组。如果我们调用 insertBack 两次，在整个过程中我们需要创建和填充多少个框？假设在对数组的最后一个引用丢失后立即进行垃圾收集，我们在任何时候总共将拥有多少个盒子？

```apl
//按照教师的
第一次insertBack的时候，
我们需要创建一个大小为101的数组框（每个数组框占用32位），和一个64位的地址框

然后第二次insertBack的时候，
我们又需要创建一个大小为102的数组框，和一个64位的地址框

所以我们一共用了 101 + 102 = 203 个数组框

假设在对数组的最后一个引用丢失后立即进行垃圾收集，我们在任何时候总共将拥有盒子的个数就是我们现在正在使用的数组的大小
```

**练习 2.5.6：**`addLast`从一个大小为 100 的数组开始，如果我们调用1000 次，大约会创建和填充多少个内存盒？

```apl

```

创建所有这些内存盒并重新复制它们的内容需要时间。在下图中，我们在顶部绘制了 SLList 的总时间与操作数的关系，底部绘制了基于简单数组的列表。SLList 显示一条直线，这意味着对于每个`add`操作，列表需要相同的额外时间。这意味着每次操作都需要固定的时间！你也可以这样想：图形是线性的，表示每个操作都需要常数时间，因为常数的积分是一条线。

**相比之下，天真数组列表显示的是抛物线，表明每个操作都需要线性时间，因为线的积分是抛物线。这对现实世界具有重要意义。对于插入 100,000 个项目，我们可以通过计算 N^2/N 的比率来粗略计算多长时间。**

**将 100,000 个项目插入我们基于数组的列表需要 (100,000^2)/100,000（也就是相较于链表而言，100,000） 倍的时间。这显然是不可接受的。**

![fig25/insert_experiment.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig25/insert_experiment.png)

### 几何调整大小

我们可以通过将数组的大小增加一个乘法量而不是相加量来解决我们的性能问题。也就是说，而不是**添加**等于某个调整大小因子的内存盒数量`RFACTOR`：

```java
public void insertBack(int x) {
    if (size == items.length) {
           resize(size + RFACTOR);
    }
    items[size] = x;
    size += 1;
}
```

我们改为通过将框数**乘以**`RFACTOR`来调整大小。

```java
public void insertBack(int x) {
    if (size == items.length) {
           resize(size * RFACTOR);
    }
    items[size] = x;
    size += 1;
}
```

重复我们之前的计算实验，我们看到我们的新`AList`算法在很短的时间内完成了 100,000 次插入，我们甚至都没有注意到。我们将推迟对为什么会发生这种情况的全面分析，直到本书的最后一章。

### 内存性能

**我们`AList`几乎完成了，但我们有一个主要问题。假设我们插入 1,000,000,000 个项目，然后删除 990,000,000 个项目。在这种情况下，我们将只使用 10,000,000 个内存盒，剩下 99% 完全未使用。**

为了解决这个问题，我们还可以在数组开始看起来为空时缩小数组的大小。具体来说，我们定义了一个“使用率”R，它等于列表的大小除以`items`数组的长度。例如下图中，使用率是0.04。

![fig25/usage_ratio.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig25/usage_ratio.png)

在一个典型的实现中，当 R 低于 0.25 时，我们将数组的大小减半。

```apl
但是做到这里，我产生了一个疑问，当我们在数组中内容较少（比如刚刚开始创建的时候）的时候，
我们的每一次removeLast都会触发 “数组大小减半”
所以我觉得我还应该加一个 当数组没有经历过第一次数组扩容之前  我们不进行removLast
```

### 通用 AList

**就像我们之前所做的那样，我们可以修改我们的`AList`，使其可以保存任何数据类型，而不仅仅是整数。为此，我们再次在我们的类中使用特殊的尖括号符号，并在适当的地方用我们的任意类型参数替换整数。例如，下面，我们使用`Glorp`作为我们的类型参数。**

**有一个显着的语法差异：Java 不允许我们创建泛型对象数组，因为泛型的实现方式存在一个模糊的问题。也就是说，我们不能这样做：**

```java
Glorp[] array = new Glorp[8];
```

**相反，我们必须使用如下所示的笨拙语法（这在Java中称为类型转换，将Object转换成Glorp）：**

```java
Glorp[] array = (Glorp []) new Object[8];
```

**这将产生一个编译警告，但这只是我们必须忍受的。我们将在后面的章节中更详细地讨论这一点。**



**此外，这里提到了关于对象的清除（垃圾收集器），细说如下**

``` java
public Bird deleteBack(){
    Bird returnItem = getBack();
    item[size - 1] = null;
    size -= 1;
    return returnItem;
}
/**
此时，我们删除的是一个内容是Bird类的数组中，对某个对象的地址，那么这个对象的实例就丢失了，我们再也无法通过地址找到它，此时Java就会默认你不需要它了，继而通过垃圾收集器收集掉他。
*/
```

我们所做的另一个更改是我们将“删除”的所有项目都清空。以前，我们没有理由将被删除的元素归零，对于通用对象，我们确实希望对我们正在存储的对象的引用归零。这是为了避免“游荡”。

**注意一下，Java 仅在最后一个引用丢失时才销毁对象。如果我们未能清空引用，那么 Java 将不会垃圾收集已添加到列表中的对象。**

```java
/**
可以在Java Visualizer中执行下面code
*/
public class ClassNameHere {
   public static void main(String[] args) {
      int[] array = new int[1];//1
      int[] p = array;//2
      array = null;//3
      p = null;//4
   }
}
/**
当执行
语句1的时候，会创建一个64位地址array，然后指向数组
语句2的时候，会创建一个64位地址p指向和array一样的数组
语句3的时候，会将array指向空 —— 但是注意 此时还有一个p指向数组 所以数组对象并不是没有引用的，所以此时垃圾收集器并不会销毁对象
语句4的时候，数组的所有引用都清空了，Java的垃圾收集器将销毁数组对象。
*/
```

这是一个微妙的性能错误，除非您正在寻找它，否则您不太可能观察到它，但在某些情况下可能会导致大量内存浪费。

## 下一步是什么：

- [讨论3](http://sp19.datastructur.es/materials/discussion/disc03.pdf)
- [实验室 2](http://sp19.datastructur.es/materials/lab/lab2/lab2)
- [项目 1A](http://sp19.datastructur.es/materials/proj/proj1a/proj1a)
