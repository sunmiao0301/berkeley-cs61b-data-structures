## Reading-2.2-SLLists

在第 2.1 章中，我们构建了`IntList`类，一个列表数据结构，从技术上讲，它可以完成列表可以做的所有事情。然而，在实践中，`IntList`它的缺点是使用起来相当笨拙，导致代码难以阅读和维护。

从根本上说，问题在于这`IntList`就是我所说的**裸递归**数据结构。为了`IntList`正确使用，程序员必须理解和利用递归，即使是简单的列表相关任务。这限制了它对新手程序员的用处，并可能引入程序员可能遇到的全新类棘手错误，具体取决于`IntList`类提供的帮助方法类型。

受我们经验的启发`IntList`，我们现在将构建一个新类`SLList`，它更类似于程序员在现代语言中使用的列表实现。我们将通过迭代添加一系列改进来做到这一点。

从根本上说，问题在于这`IntList`就是我所说的**裸递归**数据结构。为了`IntList`正确使用，程序员必须理解和利用递归，即使是简单的列表相关任务。这限制了它对新手程序员的用处，并可能引入程序员可能遇到的全新类棘手错误，具体取决于`IntList`类提供的帮助方法类型。

**受我们经验的启发`IntList`，我们现在将构建一个新类`SLList`，它更类似于程序员在现代语言中使用的列表实现。我们将通过迭代添加一系列改进来做到这一点。（IntList在实践中不好用）**

### 改进#1：品牌重塑

我们`IntList`上次的类如下，省略了辅助方法：

```java
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
...
```

我们的**第一步是简单地重命名（符合编程语言习惯的命名）**所有内容并丢弃辅助方法。这可能看起来不像是进步，但相信我，我是专业人士。

```java
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}
```

### 改进#2：官僚主义

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0126SLList.png)

知道这`IntNodes`很难使用**（结构太裸露，构造方法也不够优美）**，我们将创建一个名为`SLList`用户将与之交互的单独类。基本类很简单：

```java
/** An SLList is a list if integers, which hides the terrible truth
  * of the nakedness within */
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }
}
```

我们已经可以模糊地理解为什么 a`SLList`更好了。将`IntList`一个项目的创建与一个项目的创建进行比较`SLList`。

```java
IntList L1 = new IntList(5, null);
SLList L2  = new SLList(5);
```

`SLList`隐藏用户存在空链接的详细信息。这个`SLList`类还不是很有用，所以让我们添加一个`addFirst`and`getFirst`方法作为简单的预热方法。考虑在继续阅读之前尝试自己编写它们。

### addFirst 和 getFirst

首先，我自己编写的如下；

```	java
public void addFirst(SLList L, int n){
    SLList SL = new SLList(n);
    SL.next = L;
    L = SL;
}

public int getFirst(SLList L){
    return L.item;
}
```

以下是原文：

`addFirst`如果您了解第 2.1 章，则相对简单。

有了`IntLists`，我们在前面加上了一行代码`L = new IntList(5, L)`。因此，我们最终得到：**（比我写的更优美）**

```java
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
}
```

`getFirst`更容易。我们简单地返回`first.item`：

```java
/** Retrieves the front item from the list. */
public int getFirst() {
    return first.item;
}
```

生成的`SLList`类更容易使用。比较：

```java
SLList L = new SLList(15);
L.addFirst(10);
L.addFirst(5);
int x = L.getFirst();
```

相当于`IntList`：

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
int x = L.first;
```

直观地比较这两种数据结构，我们有：（`IntList`是上面的版本，`SLList`是下面的版本）

![IntList_vs_SLList.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/IntList_vs_SLList.png)

本质上，`SLList`该类充当列表用户和裸递归数据结构之间的中间人。正如上面`IntList`版本中所建议的那样，用户有可能不希望`IntList`有指向`IntList`. 正如奥维德所说：[凡人不能不死就看到神](https://en.wikipedia.org/wiki/Semele)，所以也许最好让神`SLList`充当我们的中介。

**练习 2.2.1**：好奇的读者可能会反对并说如果我们简单地编写一个方法，`IntList`那么它同样易于使用。尝试为该`IntList`类编写一个`addFirst`方法。您会发现生成的方法既棘手又低效。

```java
public void addFirst(int n){
    IntList newHead = IntList(n, null);
    newHead.rest = this;
}
```

### 改进#3：公共与私人

不幸的是，我们`SLList`可以绕过我们的裸数据结构（及其所有危险）的原始力量。程序员可以很容易地直接修改列表，而不需要经过孩子测试、妈妈认可的`addFirst`方法，例如：

```java
SLList L = new SLList(15);
L.addFirst(10);
L.first.next.next = L.first.next;
```

![bad_SLList.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/bad_SLList.png)

**如果给用户拿到这个first，那么用户就可能会胡乱修改这个链表结构，最终使得其乱七八糟，根本不符合我们设计这个数据结构的初衷，这会导致具有无限循环的格式错误的列表。**为了解决这个问题，我们可以修改`SLList`类，使`first`变量用`private`关键字声明。

```java
public class SLList {
    private IntNode first;
...
```

私有变量和方法只能由同一`.java`文件中的方法代码访问，例如在这种情况下`SLList.java`。这意味着像`SLLTroubleMaker`下面这样的类将无法编译，而是会报错产生`first has private access in SLList`错误。

```java
public class SLLTroubleMaker {
    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.first.next.next = L.first.next;
    }
}
```

相比之下，`SLList.java`文件中的任何代码都可以访问该`first`变量。

限制访问似乎有点傻。毕竟，`private`关键字所做的唯一一件事就是中断原本可以编译的程序。**然而，在大型软件工程项目中，`private`关键字是一个非常宝贵的信号，表明最终用户应该忽略某些代码（因此不需要理解）。**同样，`public`关键字应该被认为是一个方法可用并且将**永远**像现在一样工作的声明。

作为一个类比，汽车具有某些`public`特征，例如加速器和制动踏板。在引擎盖下，有`private`关于这些操作的详细信息。在汽油驱动的汽车中，油门踏板可能控制某种燃油喷射系统，而在电池驱动的汽车中，它可能会调整输送到电机的电池电量。虽然私人细节可能因汽车而异，但我们希望所有油门踏板的行为都相同。改变这些会引起用户的极大恐慌，很可能会发生可怕的事故。

**当您创建一个`public`成员（即方法或变量）时，请小心，因为您实际上是在承诺永远支持该成员的行为，就像现在一样。**

### 改进#4：嵌套类

目前，我们有两个`.java`文件：`IntNode`和`SLList`. 然而，他`IntNode`真的只是故事中的一个配角`SLList`。

Java 为我们提供了在这种情况下将类声明嵌入到另一个类中的能力。语法简单直观**（在本例情况下，我们完全可以使用嵌套类，因为所谓的IntNode只有SLList在用）**：

```java
public class SLList {
       public class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first; 

       public SLList(int x) {
           first = new IntNode(x, null);
       } 
...
```

拥有嵌套类对代码性能没有有意义的影响，只是保持代码组织的工具。有关嵌套类的更多信息，请参阅[Oracle 的官方文档](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)。

**如果嵌套类不需要使用任何实例方法或变量`SLList`，则可以声明嵌套类`static`，如下所示。将嵌套类声明为`static`意味着静态类中的方法不能访问封闭类的任何成员。在这种情况下，这意味着`IntNode`中的任何方法都无法访问`first`、`addFirst`或`getFirst`。**

```java
public class SLList {
       public static class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first;
...
```

这节省了一点内存，因为每个`IntNode`不再需要跟踪如何访问其封闭的`SLList`.

换句话说，如果您检查上面的代码，您会发现`IntNode`该类从不使用 `SLList`的`first`变量，也不使用任何`SLList`方法。因此，我们可以使用 static 关键字，这意味着`IntNode`该类不会获得对其老板的引用，从而为我们节省了少量内存。

如果这看起来有点技术性且难以理解，请尝试练习 2.2.2。**一个简单的经验法则是，*如果某个嵌套类不使用外部类的任何实例成员，请将嵌套类设置为 static*。**

**练习 2.2.2**`static`尽可能少地删除单词，以便[程序](https://joshhug.gitbooks.io/hug61b/content/chap2/exercises/Government.java)编译（点击链接并确保 url 更改后刷新页面）。在进行练习之前，请务必阅读顶部的评论。

```java
/** A rather contrived exercise to test your understanding of when
    nested classes may be made static. This is NOT an example of good
    class design, even after you fix the bug.

    The challenge with this file is to delete the keyword static the
    minimum number of times so that the code compiles.

    Guess before TRYING to compile, otherwise the compiler will spoil
    the problem.*/
public class Government {
	private int treasury = 5;

	private void spend() {
		treasury -= 1;
	}

	private void tax() {
		treasury += 1;
	}

	public void report() {
		System.out.println(treasury);
	}

    // greatTreasury方法用到了Government的treasury变量，所以不能是static，得删除
	public static Government greaterTreasury(Government a, Government b) {
		if (a.treasury > b.treasury) {
			return a;
		}
		return b;
	}
	
    //没有用到任何变量，应该用static，所以不用删除static
	public static class Peasant {
		public void doStuff() {
			System.out.println("hello");			
		}
	}
	
    //spend方法需要访问treasury，所以不能是static，需要删除static
	public static class King {
		public void doStuff() {
			spend();			
		}
	}
	
    //同King方法的道理，需要删除static。
	public static class Mayor {
		public void doStuff() {
			tax();			
		}
	}

    //同King方法道理，需要删除static
	public static class Accountant {
		public void doStuff() {
			report();			
		}
	}

    //需要对treasury进行修改，需要删除static
	public static class Thief {
		public void doStuff() {
			treasury = 0;			
		}
	}

    //该嵌套类用到了外部类的其他方法（greaterTreasury）所以不能有static，需要删除static
	public static class Explorer {
		public void doStuff(Government a, Government b) {
			Government favorite = Government.greaterTreasury(a, b);
			System.out.println("The best government has treasury " + favorite.treasury);			
		}
	}
}
```

### addLast() 和 size()

为了激发我们剩余的改进并展示数据结构实现中的一些常见模式，我们将添加`addLast(int x)`和`size()`方法。我们鼓励您在继续阅读之前获取[入门代码](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lists2/DIY/addLastAndSize/SLList.java)并自己尝试。我特别鼓励您尝试编写`size`的递归实现，这将产生一个有趣的挑战。

```java
public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        // 实例方法
        // 不需要用this，直接就能拿到first
        // 那么this的作用是什么？
        // 不需要 IntNode p = this.first;
        // 直接 IntNode p = first即可
    }

    /** Returns the number of items in the list using recursion. */
    public int size(IntNode p) {
        /* Your Code Here! */
        if(p.next == null)
            return 1;
        else
	        return size(p.next) + 1;
    }
    
    // 使用重载（参数不同）
    public int size(){
        return size(first);
    }
}
```

我将`addLast`迭代地实现该方法，尽管您也可以递归地实现它。这个想法相当简单，我们创建一个指针变量`p`并让它遍历列表直到结束。

```java
/** Adds an item to the end of the list. */
public void addLast(int x) {
    IntNode p = first;

    /* Advance p to the end of the list. */
    while (p.next != null) {
        p = p.next;
    }
    p.next = new IntNode(x, null);
}
```

相比之下，我将`size`递归实现。这个方法有点类似于`size`我们在[2.1](https://joshhug.gitbooks.io/hug61b/content/chap2/chap21.html)节中为`IntList`.

`size`对in的递归调用`IntList`很简单：`return 1 + this.rest.size()`. 对于 a `SLList`，这种方法没有意义。A`SLList`没有`rest`变量。相反，我们将使用与中间人类一起使用的通用模式`SLList`——我们将创建一个与底层裸递归数据结构交互的私有辅助方法。

这会产生如下方法：

```java
/** Returns the size of the list starting at IntNode p. */
private static int size(IntNode p) {
    if (p.next == null) {
        return 1;
    }

    return 1 + size(p.next);
}
```

使用这种方法，我们可以很容易地计算出整个列表的大小：

```java
public int size() {
    return size(first);
}
```

在这里，我们有两个方法，都命名为`size`. 这在 Java 中是允许的，因为它们具有不同的参数。我们说两个同名但签名不同的方法被**重载**了。有关重载方法的更多信息，请参阅 Java 的[官方文档](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)。

`IntNode`另一种方法是在类本身中创建一个非静态辅助方法。`IntNode`任何一种方法都很好，尽管我个人更喜欢在类中没有任何方法。

#### // 在这一节中 我产生了一个问题 this的作用是什么？

```java
// this的作用是得到调用
public class Employee{
    public String name;
    public int age;
    public Employee(String name, int age){
        this.name = name;
        this.age = age;
    }
}
 Employee e;
 e = new Employee("s", 1);
 // 上面的两句实质上还是隐藏了对象调用构造函数的过程e.Employee

 //当使用的参数和类中定义的变量使用相同名时，就需要this显示的指明隐式参数
```

### 改进#5：缓存

考虑`size`我们上面写的方法。假设`size`在大小为 1,000 的列表上花费 2 秒。我们预计，对于大小为 1,000,000 的列表，该`size`方法将花费 2,000 秒，因为计算机必须遍历列表中 1,000 倍的项目才能到达末尾。对于大型列表，使用非常慢的`size`方法是不可接受的，因为我们可以做得更好。

无论列表有多大，都可以重写`size`，使其花费相同的时间。

为此，我们可以简单地将一个`size`变量添加到`SLList`跟踪当前大小的类中，产生下面的代码。这种保存重要数据以加快检索速度的做法有时称为**缓存**。

```java
public class SLList {
    ... /* IntNode declaration omitted. */
    private IntNode first;
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int size() {
        return size;
    }
    ...
}
```

这种修改使我们的`size`方法非常快，无论列表有多大。当然，它也会减慢我们的`addFirst`和`addLast`方法，也会增加我们类的使用内存，但只是微不足道的。**在这种情况下，权衡显然是有利于为大小创建缓存。**

### 改进#6：空列表

与第 2.1 章中的简单方法相比，我们`SLList`有许多好处：`IntList`

- 一个`SLList`类的用户永远不会看到`IntList`。
  - 使用更简单。
  - 更有效`addFirst`的方法（练习 2.2.1）。
  - 避免`IntList`用户的错误或渎职。
- 比使用`IntList`拥有更快的`size`.

另一个自然优势是我们将能够轻松实现创建空列表的构造函数。如果列表为空，最自然的方法是设置`first`为`null`这将产生以下构造函数：

```java
public SLList() {
    first = null;
    size = 0;
}
```

不幸的是，`addLast`如果我们插入一个空列表，这会导致我们的方法崩溃。由于`first`is `null`，尝试`p.next`在`while (p.next != null)`下面访问会导致空指针异常。

```java
public void addLast(int x) {
    size += 1;
    IntNode p = first;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

**练习 2.2.3**修正`addLast`方法。起始代码[在这里](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lists2/DIY/fixAddLast/SLList.java)。

```java
public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first; 
    private int size;

    public SLList() {
        first = null;
        size = 0;
    }

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        IntNode p = first;

        // my way to solve this problem
        if(p == null)
        	return;
            
        /* Advance p to the end of the list. */
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Crashes when you call addLast on an empty SLList. Fix it. */
    public static void main(String[] args) {
        SLList x = new SLList();
        x.addLast(5);
    }
}
```

### //做到这里，我想对SLList类进行分析

```java
首先是当你创建SLList的时候（并且用的是含参构造函数
    你就已经创建了一个对象
    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }
    这个对象包含一个节点IntNode
    和一个维护的值size
    其中
    IntNode又可以分割
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
	其结构如下
    所以创建的SLList对象实质上是：
        1）first:是一个IntNode
            IntNode内部包含
            1.1)item:是一个Int值，是当前first的本身值
            1.2)next:是一个指向下一个IntNode的指针
        2) size：维护链表长度
                
// 如此一看，之前的代码就很正常了。
```

### 改进#6b：哨兵节点

一种解决方案`addLast`是为空列表创建一个特殊情况，如下所示：

```java
public void addLast(int x) {
    size += 1;

    if (first == null) {
        first = new IntNode(x, null);
        return;
    }

    IntNode p = first;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

此解决方案有效，但必要时应避免使用如上所示的特殊情况代码。人类只有这么多的工作记忆，因此我们希望尽可能控制复杂性。对于像 一样的简单数据结构`SLList`，特殊情况的数量很少。像树这样更复杂的数据结构会变得更加丑陋。

**一个更清洁的，虽然不太明显的解决方案是让所有`SLLists`的都是“相同的”，即使它们是空的。我们可以通过创建一个始终存在的特殊节点来做到这一点，我们将其称为哨兵节点。哨兵节点将保存一个我们不会关心的值。**

例如，创建的空列表`SLList L = new SLList()`如下所示：

![empty_sentinelized_SLList.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/empty_sentinelized_SLList.png)

带有`SLList`项目 5、10 和 15 的 a 看起来像：

![three_item_sentenlized_SLList.png](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/three_item_sentenlized_SLList.png)

在上图中，淡紫色的？？表示我们不在乎那里有什么值。由于 Java 不允许我们用问号填充整数，我们只需要选择一些任意值，例如 -518273 或 63 或其他任何值。

由于`SLList`没有哨兵的 a 没有特殊情况，我们可以简单地从我们的`addLast`方法中删除特殊情况，产生：

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

如您所见，这段代码要干净得多！

##### 实际上。哨兵节点是为了使得一个链表里面所有需要的节点都是一致的数据结构，从而减少了对于一些特殊情况的考虑，具体实现就是在head节点之前加一个sentinel哨兵节点，这样从head开始的所有节点都是一样的结构：可以为null，有一个前节点....

### 不变量

不变量是关于保证为真的数据结构的事实（假设您的代码中没有错误）。

具有哨兵节点的 A`SLList`至少具有以下不变量：

- `sentinel`引用总是指向一个哨兵节点。
- 最前面的项目（如果存在）总是在`sentinel.next.item`.
- 变量始终是已添加的`size`项目总数。

不变量使推理代码变得更容易，并且还为您提供了确保代码正常工作的具体目标。

真正了解哨兵的便利程度需要您真正深入研究并自己进行一些实施。您将在project 1 中获得大量练习。但是，我建议您等到完成本书的下一部分后再开始project 1。

#### 接下来是什么

本章没有任何内容。但是，如果您正在学习伯克利课程，欢迎您现在开始Lab 2。