# Reading 6.4

## 对象方法

所有类都继承自总体 Object 类。继承的方法如下：

- **`String toString()`**
- **`boolean equals(Object obj)`**
- `Class <?> getClass()`
- `int hashCode()`
- `protected Objectclone()`
- `protected void finalize()`
- `void notify()`
- `void notifyAll()`
- `void wait()`
- `void wait(long timeout)`
- `void wait(long timeout, int nanos)`

在本章中，我们将重点关注前两个。我们将利用继承，并覆盖我们类中的这两个方法，以使其按照我们希望的方式运行。

### toString() - 有疑问

该`toString()`方法提供对象的字符串表示。

常见的是，`System.out.println()`函数在传递给它的任何对象上隐式调用此方法`toString()`并打印返回的字符串。当你运行时`System.out.println(dog)`，它实际上是这样做的：

```java
String s = dog.toString()
System.out.println(s)
```

默认`Object`类的`toString()`方法打印对象在内存中的位置。这是一个十六进制字符串。像 Arraylist 和 java 数组这样的类有自己的重写版本的`toString()`方法。这就是为什么当您为 Arraylist 工作和编写测试时，总是会以像这样 (1, 2, 3, 4) 这样的良好格式返回列表，而不是返回内存位置。

对于我们自己编写的类，例如`ArrayDeque`，`LinkedListDeque`等，如果我们希望能够看到以可读格式打印的对象，我们需要提供自己的方法`toString()`。

让我们尝试为一个`ArraySet`类编写`toString()`方法。阅读下面的`ArraySet`类，确保您了解各种方法的作用。随意将代码插入 java 可视化工具以获得更好的理解！

```java
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** returns an iterator (a.k.a. seer) into ME */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        /* hmmm */
    }


    @Override
    public boolean equals(Object other) {
        /* hmmm */
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }

        //toString
        System.out.println(aset);

        //equals
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
        System.out.println(aset.equals(aset));
}
```

但是是Override，我们该怎么从一个无参函数中得到参数呢？我的实现如下：

```java
    @Override
    public String toString() {
        /* hmmm */
        
    }
    @Override
    public String toString(ArraySet<Integer> aset) {
        /* hmmm */
        //对象方法怎么得到参数？

        Iterator<Integer> iterator = aset.iterator();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            sb.append(iterator.next() + " ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        /* hmmm */
        if(this.toString().equals(other.toString()))
            return true;
        return false;
    }
    }
```

您可以在[此处找到解决方案 (ArraySet.java)](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/af2325c600010a8894a6ce3a3ccf517547145ec1/inheritance4/ArraySet.java)

### 由于我们是在类中实现重写，所以直接拿数据结构就行了？？？

```java
//由于我们是在类中实现，所以直接拿数据结构就行了
	@Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }
```





**练习 6.4.1：**编写 toString() 方法，这样当我们打印 ArraySet 时，它会打印花括号内用逗号分隔的元素。即{1、2、3、4}。请记住，toString() 方法应该返回一个字符串。

**解决方案**

```java
public String toString() {
    String returnString = "{";
    for (int i = 0; i < size; i += 1) {
        returnString += keys[i];
        returnString += ", ";
    }
    returnString += "}";
    return returnString;
}
```

这种解决方案虽然看似简单优雅，但实际上非常幼稚。这是因为当您像这样在 Java 中使用字符串连接时：`returnString += keys[i];`您实际上不仅仅是附加到`returnString`，您正在创建一个全新的字符串。这是非常低效的，因为创建一个新的字符串对象也需要时间！具体来说，与字符串的长度呈线性关系。

**奖励问题：**假设将一个字符连接到一个字符串需要 1 秒。如果我们有一个大小为 5: 的 ArraySet，`{1, 2, 3, 4, 5}`运行该幼稚的`toString()`方法需要多长时间？

**答：**我们设置`returnString`为左括号，这需要一秒钟，因为这涉及添加`{`到空字符串`""`。添加第一个元素将涉及创建一个全新的字符串，添加 } 和 1，这需要 2 秒。添加第二个元素需要 3 秒，因为我们需要添加`{`, `1`, `2`。此过程继续进行，因此对于整个数组集，总时间为`1 + 2 + 3 + 4 + 5 + 6 + 7.`

**为了解决这个幼稚方法引出的问题，Java 有一个特殊的类，叫做`StringBuilder`. 它创建了一个可变的字符串对象，因此您可以继续附加到同一个字符串对象，而不是每次都创建一个新对象。**

**练习 6.4.2：**使用 StringBuilder 重写 toString() 方法。

**解决方案**

```java
public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }
```

现在您已经成功地覆盖了该`toString()`方法！尝试打印 ArraySet 以查看您的工作成果。

接下来我们将覆盖另一个重要的对象方法：`equals()`

## equals()

`equals()`并且`==`在 Java 中有不同的行为。`==`检查两个对象是否实际上是内存中的同一个对象。请记住，按值传递！`==`检查两个盒子是否包含相同的东西。

**对于原语，这意味着检查值是否相等。**

**对于对象，这意味着检查地址/指针是否相等。**

**更需要注意的是，对于null，我们必须使用 == ，否则会得到一个空指针错误。**

假设我们有这个`Doge`类：

```java
public class Doge {

   public int age;
   public String name;

   public Doge(int age, String name){
      this.age = age;
      this.name = name;
   }
   public static void main(String[] args) {

      int x = 5;
      int y = 5;
      int z = 6;

      Doge fido = new Doge(5, "Fido");
      Doge doggo = new Doge(6, "Doggo");
      Doge fidoTwin = new Doge(5, "Fido");
      Doge fidoRealTwin = fido;
   }
}
```

如果我们将此代码插入 java 可视化器，我们将看到如下所示的指针图中的框。

![img](https://joshhug.gitbooks.io/hug61b/content/assets/Doge.png)

练习 6.4.2：如果我们运行以下命令，java 会返回什么？

- `x == y`
- `x == z`
- `fido == doggo`
- `fido == fidoTwin`
- `fido = fidoRealTwin`

**答案**

- `True`
- `False`
- `False`
- `False`
- `True`

`fido`并且`fidoTwin`不被考虑`==`，因为它们指向不同的对象。但是，这很愚蠢，因为它们的所有属性都是相同的。您可以看到如何`==`在 Java 测试中引起一些问题。当我们为我们的 ArrayList 编写测试并想要检查 expected 是否与我们的函数返回的相同时，我们创建一个新的 arraylist。如果我们`==`在测试中使用它，它总是会返回 false。这是`equals(Object o)`为了什么。

### `equals(Object o)`

`equals(Object o)`是 Object 中的一个方法，默认情况下，它的行为类似于 ==，因为它检查 this 的内存地址是否与 o 相同。但是，我们可以覆盖它以任何我们希望的方式定义相等！例如，要使两个 Arraylist 被视为相等，它们只需要以相同的顺序具有相同的元素。

**练习 6.4.3：**让我们为 ArraySet 类编写一个 equals 方法。请记住，集合是唯一元素的无序集合。因此，要使两个集合被视为相等，您只需要检查它们是否具有相同的元素。

**我的方案**：

```java
    @Override
    public boolean equals(Object other) {
        /* hmmm */
        /**
         *         this can't be null!!!!!!
         *     
         *         if(this == null && other == null)
         *             return true;
         *         else if(this == null || other == null)
         *             return false;
         */
        if(other == null)
            return false;
        else if(this.toString().equals(other.toString()))
            return true;
        else
            return false;
    }
```

**教师的解决方案**：

```java
public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }
```

我们在方法的开头添加了一些检查，以确保我们的 equals 可以处理空值和不同类的对象。如果 == 方法返回 true，我们还通过立即返回 true 来优化函数。这样，我们避免了遍历集合的额外工作。

**Java 中的等于规则：**当覆盖一个`.equals()`方法时，它有时可能比看起来更棘手。在实施您的方法时要遵守的一些规则`.equals()`如下：

1.)`equals`必须是等价关系

- **反身**的：`x.equals(x)`是真的
- **对称**：`x.equals(y)`当且仅当`y.equals(x)`
- **传递**的：`x.equals(y)`并`y.equals(z)`暗示`x.equals(z)`

2.) 它必须接受一个 Object 参数，以便Override原始`.equals()`方法

3.) 必须一致， if `x.equals(y)`, 那么 只要`x`和`y`保持不变：`x`必须继续等于`y`

4.) null无法调用方法，所以 `x.equals(null)`必须是假的

### 奖励视频 — ignore

创建一个更好的`toString`方法和`ArraySet.of`：

### [Video](https://www.youtube.com/watch?v=tjLpeVD0KWc)

链接到[奖金代码](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/af2325c600010a8894a6ce3a3ccf517547145ec1/inheritance4/ArraySet.java)