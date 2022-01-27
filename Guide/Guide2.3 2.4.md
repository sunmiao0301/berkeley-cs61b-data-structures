## Guide2.3/2.4

## 概述

**SLList 缺点** `addLast()`很慢！我们不能添加到列表的中间。另外，如果我们的列表真的很大，我们必须从前面开始，一直循环到列表的后面，然后再添加我们的元素。

**一个简单的解决方案**回想一下，我们将列表的大小缓存为`SLList`. 如果我们也将`last`元素缓存在列表中怎么办？一下子，`addLast()`又快了；我们立即访问最后一个元素，然后添加我们的元素。但`removeLast()`仍然很慢。在 `removeLast()`中，我们必须知道倒数第二个元素是什么，因此我们可以将缓存`last`变量指向它。然后我们可以缓存一个`second-to-last` 变量，但是现在如果我想删除倒数第二个元素，我需要知道倒数第三个元素在哪里。如何解决这个问题呢？

**DLList**的解决方法是给每`IntNode`一个`prev`指针，指向上一项。这将创建一个双向链表，或`DLList`. 通过这种修改，从列表的前面和后面添加和删除变得很快（尽管从中间添加/删除仍然很慢）。

**结合 Sentinel** Recall，我们将一个哨兵节点添加到我们的 `SLList`. 对于`DLList`，我们可以有两个哨兵（一个在前面，一个在后面），或者我们可以使用一个圆形哨兵。`DLList`使用循环哨兵的 A有一个哨兵。哨兵用 指向列表的第一个元素，用 指向列表`next`的最后一个元素`prev`。此外，列表的最后一个元素`next`指向哨兵，列表的第一个元素`prev`指向哨兵。对于空列表，哨兵在两个方向都指向自身。

**Generic DLList**我们如何修改我们的`DLList`，使其成为我们选择的任何对象的列表？回想一下，我们的类定义如下所示：

```
public class DLList { ... }
```

我们将其更改为

```
public class DLList<T> { ... }
```

where`T`是占位符对象类型。注意尖括号语法。另请注意，我们不必使用`T`; 任何变量名都可以。在 our`DLList`中，我们的 item 现在是 type `T`，我们的方法现在将`T`实例作为参数。为了准确起见，我们还可以重命名我们的`IntNode`类。`TNode`

**使用 Generic DLList**回想一下创建一个包含整型数据类型的`DLList`，我们输入：

```
DLList list = new DLList(10);
```

如果我们现在要创建一个`DLList`持有`String`对象，那么我们必须说：

```
DLList<String> list = new DLList<>("bone");
```

**在创建列表时，编译器将所有实例替换`T`为`String`! 我们将在后面的讲座中更详细地介绍泛型类型。**

**数组** 回想一下，变量只是位框。例如，`int x;` 给我们一个 32 位的内存盒。数组是一个特殊的对象，它由一系列编号的内存盒组成！要获取数组的第 i 项`A`，请使用 `A[i]`. 数组的长度不能改变，数组的所有元素必须是同一类型（这与 Python 列表不同）。这些框是零索引的，这意味着对于具有 N 个元素的列表，第一个元素是 at `A[0]`，最后一个元素是 at `A[N - 1]`。与常规类不同，**数组没有方法！**数组确实有一个`length`变量。

**实例化数组**创建数组有三种有效的表示法。第一种方式指定数组的大小，并用默认值填充数组：

```
int[] y = new int[3];
```

第二种和第三种方式用特定值填充数组，而不必提前指定数组长度。

```
int[] x = new int[]{1, 2, 3, 4, 5};
int[] w = {1, 2, 3, 4, 5};
```

我们可以使用数组索引在数组中设置一个值。例如，我们可以说 `A[3] = 4;`。这将访问数组的**第四个**`A`元素并将该框的值设置为 4。

**Arraycopy**为了复制一个数组，我们可以使用 `System.arraycopy`. 它需要5个参数；语法很难记住，因此我们建议使用各种在线参考，例如 [this](https://www.tutorialspoint.com/java/lang/system_arraycopy.htm)。

**二维数组**我们可以声明多维数组。对于二维整数数组，我们使用以下语法：

```java
int[][] array = new int[4][];
```

这将创建一个包含整数数组的数组。请注意，我们必须手动创建内部数组，如下所示：

```java
array[0] = new int[]{0, 1, 2, 3};
```

Java 还可以使用自动创建的内部数组创建多维数组。为此，请使用以下语法：

```java
int[][] array = new int[4][4];
```

我们也可以使用符号：

```java
int[][] array = new int[][]{{1}, {1, 2}, {1, 2, 3}}
```

获取具有特定值的数组。

**数组与类**

- 两者都用于组织一堆内存。
- 两者都有固定数量的“盒子”。
- 数组通过方括号表示法访问。类是通过点符号访问的。
- 数组中的元素必须都是同一类型。一个类中的元素可以是不同的类型。
- 数组索引在运行时计算。我们无法计算类成员变量名称。

## 练习

### C级

1. [在此处](https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html)和 [此处](https://joshhug.gitbooks.io/hug61b/content/chap2/chap24.html)完成在线教科书中的练习 。
2. 你能创建一个不同类型的二维数组吗？例如，一个子数组将由所有字符串组成，而另一个子数组将仅由整数组成。

```java
//新建一个泛型<T>的数组类吗？如下 但我觉得肯定不对

int[][] arr = new int[][];

<T>[][] arr;
arr = new <>[][]();

//stackoverflow上的解答是:

public class Book
{
    public int number;
    public String title;
    public String language;
    public int price;

    // Add constructor, get, set, as needed.
}

//然后将您的数组声明为：
Book[] books = new Book[3];
```

### B级

1. 执行下面的每一步

   ```java
    public class Deck{
        public static int[] cards;//自闭
        Deck(){
            cards = [1, 3, 4, 10];
        }
    }
   ```

   记下dingie的阵列卡的内容。

   ```java
      Deck dingie = new Deck();
      dingie.cards[3] = 3;
   	
   	//1, 3, 4, 3
   ```

   写出普拉提数组和丁吉数组的内容。

   ```java
      Deck pilates = new Deck();
      pilates.cards[1] = 2;
   
   	//1, 2, 4, 10
   ```

   写出普拉提数组和丁吉数组的内容。

   ```java
      int[] newArrWhoDis = {2, 2, 4, 1, 3};
      dingie.cards = pilates.cards;
   	//dingie = 1, 2, 4, 10
   	//pilates = 1, 2, 4, 10
   	//newArrWhoDis = 2, 2, 4, 1, 3
      pilates.cards = newArrWhoDis;
   	//dingie = 1, 2, 4, 10
   	//pilates = 2, 2, 4, 1, 3
   	//newArrWhoDis = 2, 2, 4, 1, 3
      newArrWhoDis = null;
   	//dingie = 1, 2, 4, 10
   	//pilates = 2, 2, 4, 1, 3
   	//newArrWhoDis = null;
   ```

   #### 拿Java Visualizer验证如下，发现我以上写的是错的，我的推断符合的是下面这种表达

   #### public int[] cards = {1, 3, 4, 10};//与带static的大不同

   #### 对static在类中的使用还是没理解透

   ```java
   // 在Java Visualizer中运行即可知道区别
   public class Deck{
       public static int[] cards = {1, 3, 4, 10};//自闭
       /**
       public int[] cards = {1, 3, 4, 10};//与带static的大不同
       */
       public static void main(String[] args) {
           Deck dingie = new Deck();
           dingie.cards[3] = 3;
           
           Deck pilates = new Deck();
           pilates.cards[1] = 2;
           
           int[] newArrWhoDis = {2, 2, 4, 1, 3};
           dingie.cards = pilates.cards;
           //dingie = 1, 2, 4, 10
           //pilates = 1, 2, 4, 10
           //newArrWhoDis = 2, 2, 4, 1, 3
           pilates.cards = newArrWhoDis;
           //dingie = 1, 2, 4, 10
           //pilates = 2, 2, 4, 1, 3
           //newArrWhoDis = 2, 2, 4, 1, 3
           newArrWhoDis = null;
           //dingie = 1, 2, 4, 10
           //pilates = 2, 2, 4, 1, 3
           //newArrWhoDis = null;
       }
   }
   ```

2. 假设我们有一个二维 DList。我们希望这个 2-D DList 尽可能均匀。为此，我们将尝试尽可能均匀地填充行——这意味着没有行的大小将比任何其他行大超过 1 个元素。编写一个接收子 DList 的方法，如果它满足约束条件，则添加给定元素。如果未满足约束条件，则将尝试将项目放入您最初尝试插入的项目下方的 DList 中，依此类推，直到到达最底部的 DList，在这种情况下移动到顶部的 DList。

### A级

1. [完成 Kartik教科书](http://www.kartikkapur.com/documents/mt1.pdf#page=10)练习中期 1 中的第 10 题
2. [在这里](https://tbp.berkeley.edu/exams/4695/download/#page=9)完成 2015 年春季中期 1 中的第 7 题