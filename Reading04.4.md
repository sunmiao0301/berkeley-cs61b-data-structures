## Reading 4.4

## 抽象数据类型 (ADTS - Abstract Data Type)

尽管没有明确地谈论它们，我们实际上已经在课堂上看到了一些抽象数据类型！

两个例子是 List61B 和 Deque。让我们磨练一下 Deque。

![双端队列](https://joshhug.gitbooks.io/hug61b/content/assets/deque.png)

我们有这个ArrayDeque 和 LinkedListDeque 都实现的接口`deque`。Deque 和它的实现类是什么关系？好吧，deque 只是提供了一个方法列表（行为）：

```java
public void addFirst(T item);
public void addLast(T item);
public boolean isEmpty();
public int size();
public void printDeque();
public T removeFirst();
public T removeLast();
public T get(int index);
```

这些方法实际上是由 ArrayDeque 和 LinkedListDeque **实现的。**

在 Java 中，Deque 被称为接口。从概念上讲，我们称 deque 为**Abstract Data Type 抽象数据类型。Deque只带有行为，没有任何具体的方式来展示这些行为。这样，它是抽象的。**

## Java 库

Java 具有某些您可以使用的内置抽象数据类型。这些都打包在 Java 库中。

java.util 库中包含三个最重要的 ADT：

- List：项目的有序集合

  - 一个流行的实现是[ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)

- Set：严格唯一项的无序集合（无重复）

  - 一个流行的实现是[HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)

- Map：键/值对的集合。您可以通过键访问该值。

  - 一个流行的实现是[HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)

使用上述三个 **ADT - Abstract Data Type** 完成以下练习。阅读上面链接的文档将有很大帮助。

**练习 4.4.1** 编写一个方法`getWords`，接收参数是一个`String inputFileName`，并将输入文件中的每个单词放入一个列表中。回想一下我们在proj0 中如何从文件中读取单词。*提示：使用`In`*

```java
public void getWords(String inputFileName){
    List<String> res = new LinkedList<>();
    In in = new In();
 	while(!in.isEmpty()){
        res.add(in.readString());
    }   
    return res;
}
```

**练习 4.4.2** 编写一个方法`countUniqueWords`，接收一个`List<String>`并计算文件中有多少个**唯一**单词。

```java
public static int countUniqueWords(List<String> words) {
    Set<String> ss = new HashSet<>();
    for (String s : words) {
           ss.add(s);        
    }
    return ss.size();
}

// my editor
public static int countUniqueWords(List<String> words) {
    Set<String> set = new HashSet<>();
    int ret = 0;
    for(int i = 0; i < words.size(); i++){
        String tmp = words.get(i);
        if(!set.contains(tmp){
            set.add(tmp);
            ret++;
        }
    }
    return ret;
}
```

**练习 4.4.3** 编写一个方法`collectWordCount`，接受 `List<String> targets`和 `List<String> words`并找出每个**目标单词**在单词列表中出现的次数。

```java
public static Map<String, Integer> collectWordCount(List<String> words) {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String t: target) {
        counts.put(s, 0);
    }
    for (String s: words) {
        if (counts.containsKey(s)) {
            counts.put(word, counts.get(s)+1);
        }
    }
    return counts;
}
```

我们使用地图是因为它在两件事之间建立了关联。在我们的例子中，我们需要单词和数字之间的关联。

这三个 ADT 都是从 Collection Interface 扩展而来的。Collection接口的定义很模糊。Java 说Collection“代表一组对象，称为它的元素”。

![H](https://joshhug.gitbooks.io/hug61b/content/assets/collection_hierarchy.png)

在上图中，白框是接口。蓝色框是具体的类。

## Java 与 Python

**Java非常冗长。下面的java代码看起来比对应的python代码要麻烦很多。**

![你好](https://joshhug.gitbooks.io/hug61b/content/assets/java.png)

![海白](https://joshhug.gitbooks.io/hug61b/content/assets/python.png)

但是，Java 也有它的优点！它给你很多选择和自由。例如，python 只有一种字典类型，它使用大括号 {} 声明。在 Java 中，如果你想使用像 Map 这样的 ADT，你可以选择你想要的 Map 类型：Hashmap？Treemap？等等。

我们喜欢 61B 中的 Java！以下是一些原因：

- 可以说，由于以下特性，编写程序需要更少的时间：
  - 静态类型（提供类型检查并帮助指导程序员）。
  - **偏向接口继承导致更清晰的子类型多态性。**
  - 访问控制修饰符使抽象壁垒更加牢固。
- 由于以下功能，代码效率更高：
  - 能够更好地控制工程权衡。
  - 单值数组带来更好的性能。

- **基本数据结构更类似于底层硬件：**
  - 在 Python 中做 ArrayDeque 会很奇怪，因为不需要调整数组大小。但是，在硬件中（参见 61C），不存在可变长度数组。



## 抽象类

**我们已经看到了可以做很多很酷的事情的接口！它们允许您利用接口继承和实现继承。作为复习，这些是接口的特性：**

- **所有方法都必须是公开的。**
- **所有变量都必须是 public static final。**
- **无法实例化**
- **默认情况下，所有方法都是抽象的，除非指定为`default`**
- **每个类可以实现多个接口**

**我们现在将介绍一个介于接口和具体类之间的新类：抽象类。下面是抽象类的特征：**

- **方法可以是公共的或私有的**
- **可以有任何类型的变量**
- **无法实例化**
- **默认情况下，方法是具体的，除非指定为`abstract`**
- **每个类只能实现一个**

**基本上，抽象类可以做接口可以做的所有事情，甚至更多。**

**如有疑问，请尝试使用接口**以降低复杂性。

## 包

包名给**所有东西一个规范的名字**。规范意味着事物的*唯一表示*。

为什么？好吧，在 Java 中，我们可以有多个具有相同名称的类。我们需要一种方法来区分这些不同的类。在行业中，这种区分是通过将类附加到网站地址（向后）来实现的，如下所示：

~~~ug.joshh.animal```
But... this means we have to type out that entire name every time we want to instantiate something of that class.

```ug.joshh.animal.Dog d = new ug.joshh.animal.Dog()
~~~

这很烦人。我们可以通过导入包来解决这个问题。

```
import ug.joshh.animal
```

现在我们可以随心所欲地使用狗了。

这只是软件包的简要预览。我们将在课程的后几周进一步了解这一点。