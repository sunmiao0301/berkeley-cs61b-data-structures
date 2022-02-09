## Reading 6.3

### [Video about Iterator](https://www.youtube.com/watch?v=Gv6LjusNBU0)

**p.s. 在HW0中，我们简单学习过增强的For循环，如下：**

```java
//增强的 For 循环
//Java 还支持使用“增强的 for 循环”对数组进行迭代。基本思想是在很多情况下我们实际上根本不关心索引。在这种情况下，我们避免使用涉及冒号的特殊语法创建索引变量。

//例如，在下面的代码中，我们执行与BreakDemo上面完全相同的操作。但是，在这种情况下，我们不创建索引i。取而代之的是String s，从 a[0] 开始，一直到a[a.length - 1]

public class EnhancedForBreakDemo {
    public static void main(String[] args) {
        String[] a = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};

        for (String s : a) {
            for (int j = 0; j < 3; j += 1) {
                System.out.println(s);
                if (s.contains("horse")) {
                    break;
                }                
            }
        }
    }
}
```

### 增强的for循环 

我们可以使用带有 Java 的干净的、增强的 for 循环`HashSet`

```java
Set<String> s = new HashSet<>();
s.add("Tokyo");
s.add("Lagos");
for (String city : s) {//String city : s
    System.out.println(city);
}
```

但是，如果我们尝试对我们的 `ArraySet` 做同样的事情，我们会得到一个错误如下。

```java
    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
        for(String tmp : s){
            System.out.println(tmp);
        }
    }
    
	//报错如下
	//for- each not applicable to type 'ArraySet<java.lang.String>
```

#### 那么我们将如何启用此功能？



### HashSet能使用增强的 For 循环，而我们的ArraySet不行 — 实质上是因为HashSet有内置的迭代器

让我们首先了解当我们使用增强的 for 循环时会发生什么。我们可以将增强的 for 循环“翻译”成丑陋的手动方法。

```java
Set<String> s = new HashSet<>();
...
for (String city : s) {
    ...
}
```

上面的代码转换为：

```java
Set<String> s = new HashSet<>();
...
Iterator<String> seer = s.iterator();
while (seer.hasNext()) {
    String city = seer.next();
    ...
}
```

这里的关键是一个称为**迭代器iterator**的对象。

此代码的行为与上面的 for-each 增强for循环版本相同。



### 实现迭代器

让我们首先考虑一下编译器需要知道什么才能成功编译以下迭代器示例：

```java
List<Integer> list = new ArrayList<Integer>();
Iterator<Integer> seer = list.iterator();

while(seer.hasNext()) {
    System.out.println(seer.next());
}
```

我们可以查看调用相关方法的每个对象的静态类型。

`list`是一个列表，在其上`iterator()`被调用，所以我们必须问：

- List 接口是否有 iterator() 方法？

`seer`是一个迭代器对象，在其上调用`hasNext()`和`next()`，所以我们必须问：

- Iterator 接口是否有 next() 和 hasNext() 方法？

那么我们如何实现这些要求呢？

List 接口扩展了 Iterable 接口，继承了抽象的 iterator() 方法。（实际上，List 扩展了 Collection，Collection扩展了 Iterable，但用这种方式开始编码更容易。）

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
public interface List<T> extends Iterable<T>{
    ...
}
```

接下来，编译器检查迭代器是否具有`hasNext()`和`next()`。Iterator 接口明确指定了这些抽象方法：

```java
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
```

###### 如果有人在`hasNext`返回 false 时调用`next`怎么办？

```
This behavior is undefined. However, a common convention is to throw a `NoSuchElementException`. See [Discussion 5](https://sp19.datastructur.es/materials/discussion/disc05sol.pdf) for examples.
```

###### `hasNext`会一直在`next`之前被调用吗？

```
Not necessarily. This is sometimes the case when someone using the iterator knows exactly how many elements are in the sequence. Thus, we can't rely on the user calling `hasNext` before `next`. However, you can always call `hasNext` from within your `next` function.
```

 

### 下面我们将讨论如何使我们上一节的ArraySet类支持迭代。

**类比上面List的Iterator，我们的 ArraySet 迭代器类 ArraySetIterator 应该中有三个关键方法：**

**1、由于我们使用了`Iterator<String> seer = s.iterator();`，所以我们会定义一个返回迭代器对象的方法`iterator()`。**

```java
public Iterator<E> iterator();
```

**2、使用 while 循环遍历列表。我们检查`seer.hasNext()`是否还有剩余的项目，如果还有未见过的项目，它将返回 true，如果所有项目都已处理，则返回 false。**

**3、`seer.next()`一次做两件事：**

- **它返回列表的当前元素，在这里我们将其打印出来。**

- **它还将迭代器推进到下一个。这样，迭代器将只检查每个项目一次。**



**特定的类将为接口方法实现它们自己的迭代行为。让我们看一个例子。（注意：如果您想从一开始就构建它，请按照视频中的实时编码进行操作。）**

**我们将通过键向 ArrayMap 类添加迭代。首先，我们编写一个名为 ArraySetIterator 的新类，作为嵌套类嵌套在 ArraySet 中：**

```java
private class ArraySetIterator{
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

//然后给ArraySet添加一个新方法
public Iterator<T> iterator(){
    return new ArraySetIterator();
}
```

这个 ArraySetIterator 实现了一个`hasNext()`方法，一个`next()`方法，使用一个`wizPos`位置作为索引来跟踪它在数组中的位置。对于不同的数据结构，我们可能会以不同的方式实现这两种方法。

**思考练习：**你会如何设计一个链表的`hasNext()`和`next()`方法？

```java

```

现在我们有了合适的方法，我们可以使用 ArraySetIterator 来遍历 ArrayMap：

```java
ArraySet<Integer> aset = new ArraySet<>();
aset.add(5);
aset.add(23);
aset.add(42);

Iterator<Integer> iter = aset.iterator();

while(iter.hasNext()) {
    System.out.println(iter.next());
}
```



**不过，我们仍然希望ArraySet能够支持增强的 for 循环，以使我们的调用更简洁。所以，我们需要让 ArrayMap 实现 Iterable 接口。**

**Iterable 接口的基本方法是`iterator()`，它为该类返回一个 Iterator 对象。我们之前已经添加了这个方法：**

```java
public Iterator<T> iterator() {
    return new ArraySetIterator();
}
```

**但是其实它真正的作用在于它实现了iterator接口。我们只需要对：**

```java
public class ArraySet<T> implements Iterable<T>{
    ...
}
```



**现在我们可以对ArraySet使用增强的 for 循环了`ArrraySet`！**

```java
ArraySet<Integer> aset = new ArraySet<>();
...
for (int i : aset) {
    System.out.println(i);
}
```

在这里，我们看到了**Iterable**，它是使类能够被迭代的接口，并且需要`iterator()`返回 Iterator 对象的方法。我们已经看到了**Iterator**，该接口定义了具有实际执行迭代的方法的对象。您可以将 Iterator 视为一台机器，您可以将它放在促进迭代的可迭代对象上。任何可迭代对象都是迭代器正在执行的对象。

使用这两个组件，您可以为您的类制作精美的 for 循环！



![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0209Iterator.png)



### 简单的，为一个类制作增强for循环的经验规律就是：

- **ArraySet应该implements Iterable<T>；**

  ```java
  public class ArraySet<T> implements Iterable<T> {
      ...
  }
  ```

- **为ArraySet增加一个iterator()方法，并且这个方法返回一个Iterator<T>类的对象**

  ```java
      public Iterator<T> iterator() {
          return new ArraySetIterator();
      }
  ```

- **为ArraySet新增一个实现Iterator<T>接口的嵌套类ArraySetIterator类，并且应该具有一个hasNext()和next()方法**

  ```java
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
  
  ```

- 所以最终的使用方法是：

  ```java
  ArraySet<Integer> aset = new ArraySet<>();
  aset.add(5);
  aset.add(23);
  
  Iterator<Integer> iter = aset.iterator();//返回一个new ArraySetIterator()
  
  while(iter.hasNext()) {//使用ArraySetIterator的hasNext()和next()方法
      System.out.println(iter.next());
  }
  ```

  

`ArraySet`具有迭代支持的完整代码如下：

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

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }
    }

}
```