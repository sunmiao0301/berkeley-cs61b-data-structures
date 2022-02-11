## Reading 8.1 高效编程 — 封装、API、ADT

### 高效编程

“工程师会花一毛钱做傻瓜会花一美元做的事”——保罗·希尔芬格

效率有两种形式：

1.) 编程成本。

- 开发您的程序需要多长时间？
- 阅读、修改和维护代码有多容易？

**2.) 执行成本（从下周开始将对此内容进行讲解）。**

- 你的程序执行需要多少时间？
- 你的程序需要多少内存？

今天，我们将关注如何降低编程成本。当然，想要保持较低的编程成本，既可以让我们更快地编写代码，也可以减少沮丧的人，这也将帮助我们更快地编写代码（人们在沮丧时不会很快编写代码）。

61B 中讨论的一些有用的 Java 特性：

- 包 Packages
  - 好：组织，使东西包私有
  - 坏：具体
- 静态类型检查
  - 好：及早检查错误，读起来更像一个故事
  - 坏：不太灵活（转型）
- 继承
  - 好：
    - 代码重用
  - 坏：
    - “is a”
    - 调试的路径变得烦人
    - 无法实例化
    - 实现接口的每个方法

我们将在本章中探索一些新的方法！

## 封装

我们将首先定义几个术语：

- **模块：**一组作为一个整体协同工作以执行某项任务或一组相关任务的方法。
- **封装：**如果一个模块的实现完全隐藏，则称其为封装，并且只能通过文档化的接口访问它。

## API's

ADT 的 API（应用程序编程接口）是构造函数和方法的列表以及每个的简短描述。

API 由句法和语义规范组成。

- 编译器验证语法是否满足要求
  - 也就是说，API 中指定的所有内容都存在。
- 测试有助于验证语义是否正确。
  - 也就是说，一切事物实际上都按应该的方式工作。
  - 语义规范通常用英语写成（可能包括使用示例）。
  - 数学上精确的形式规范在某种程度上是可能的，但并不普遍。

## ADT's

**ADT（抽象数据结构）是由它们的行为定义的高级类型，而不是它们的实现。举个例子，Proj1 中的 Deque 是具有某些行为（addFirst、addLast 等）的 ADT。但是，我们实际用来实现它的数据结构是 ArrayDeque 和 LinkedListDeque.**



一些 ADT 实际上是其他 ADT 的特例。例如，堆栈和队列只是具有更具体行为的列表。

**练习 8.1.1**
使用链表作为底层数据结构编写一个 Stack 类。你只需要实现一个函数：push(Ite​​m x)。确保使用“Item”作为泛型类型来使类泛型！

下面是我写的：

```java
class Stack<Item>{
    private LinkedList<Item> list = new LinkedList<>();
    public void push(Item x){
        list.add(x);
    }
}
```

**您可能已经用几种不同的方式编写了它。让我们看看三个流行的解决方案：**

1）此解决方案使用*扩展名*。它只是从那里借用方法`LinkedList<Item>`并将它们用作自己的方法。

```java
public class ExtensionStack<Item> extends LinkedList<Item> {
    public void push(Item x) {
        add(x);//为什么此处可以直接add(x)?继承还是不是很理解，得做点project
    }
}
```

2）这种方法使用委托。它创建一个链表对象并调用它的方法来完成它的目标。

```java
public class DelegationStack<Item> {
    private LinkedList<Item> L = new LinkedList<Item>();
    public void push(Item x) {
        L.add(x);
    }
}
```

3）这种方法与前一种方法类似，不同之处在于它可以使用任何实现**List**接口的类（LinkedList、ArrayList 等）。

```java
public class StackAdapter<Item> {
    private List L;
    public StackAdapter(List<Item> list) {
        L = list;
    }
    public void push(Item x) {
        L.add(x);
    }
}
```

**警告**：注意“is-a”和“has-a”关系之间的区别。

- 猫有爪子
- 猫是猫

**本节前面定义了委托是通过传入一个类来完成的，而extends被定义为继承（只是因为乍一看可能很难注意到）。**

**委托与扩展 Delegation vs Extension**：现在看来，委托和扩展几乎可以互换；但是，在使用它们时必须记住一些重要的区别。

当您知道父类中发生了什么时，往往会使用扩展。换句话说，您知道这些方法是如何实现的。此外，通过扩展，您基本上是在说您要扩展的类的行为类似于进行扩展的类。另一方面，委托是当您不想将当前类视为您从中提取方法的类的一个版本时。

视图 Views：视图是现有对象的替代表示。视图本质上限制了用户对底层对象的访问。但是，通过视图所做的更改将影响实际对象。

```java
/** Create an ArrayList. */
List<String> L = new ArrayList<>();
/** Add some items. */
L.add(“at”); 
L.add(“ax”); 
…
```

假设您只想要索引 1 和 4 中的列表。然后您可以使用名为 sublist 的方法执行以下操作，您将

```java
/** subList me up fam. */
List<String> SL = l.subList(1, 4);
/** Mutate that thing. */
SL.set(0, “jug”);
```

现在为什么这很有用？假设我们只想反转列表的一部分。例如在下图中，我们希望反转 ax ban bat。

![撤销](https://joshhug.gitbooks.io/hug61b/content/assets/reverse_list1.png)

最直观的方法是创建一个接收列表对象和应该反转的索引的方法。然而，这可能有点痛苦，因为我们添加了一些无关的逻辑。

为了解决这个问题，我们可以创建一个通用的 reverse 函数，它接收一个列表并反转该列表。因为视图改变了它所代表的底层对象，我们可以像之前一样创建一个子列表并反转子列表。最终结果实际上会改变实际列表而不是副本。
![img](https://joshhug.gitbooks.io/hug61b/content/assets/reverse_list2.png)

#### 这一切都很好。但是，它本身就是一个问题。您声称您可以提供一个列表对象，该对象在操作时会影响原始列表对象 - 这有点奇怪。

#### 想一想“你如何返回一个实际的列表，但它仍然影响另一个列表？” 有点混乱。那么答案是访问方法。

首先要注意的是 sublist 方法返回一个列表类型。此外，还有一个名为 Sublist 的定义类，它扩展了 AbstractList。由于 Abstract List 它实现了 List 接口，它和 Sublist 是 List 类型。

```
List<Item> sublist(int start, int end){
    Return new this.Sublist(start,end);
}
```

从上面的代码中首先要注意的是 subList 返回一个 List 类型。

```java
Private class Sublist extends AbstractList<Item>{
    Private int start end;
    Sublist(inst start, int end){...}
}
```

现在 sublist 函数返回 List 的原因是因为 SubList 类扩展了 AbstractList。由于 AbstractList 实现了 List 接口，因此它和 Sublist 都是 List 类型。

```java
public Item get(int k){
    return AbstractList.this.get(start+k);
}
public void add(int l, Item x){
    AbstractList.this.add(start+k, x); end+=1
}
```

应该注意的是，从我们的子列表中获取第 k 个项目与从原始列表中获取第 k 个项目相同，偏移量等于我们的起始索引。

#### 因为我们使用的是外部类（最父类）的 get 方法，所以我们更改了原始列表。

类似地，向我们的子列表添加一个元素与向我们的原始列表添加一个元素相同，其偏移量等于子列表的起始索引。

**外卖：**

- API 很难设计；但是，拥有连贯的设计理念可以使您的代码更加简洁和易于处理。
- 继承很容易被频繁使用，但它有问题并且应该谨慎使用，只有当你确定你的类的属性时（包括那些被扩展的和正在扩展的）。