

## Reading 6.1

## 列表、集合、数组集

在本节中，我们将学习如何使用 Java 的内置`List`和`Set`数据结构以及构建我们自己的`ArraySet`.

在本课程中，我们已经构建了两种列表：`AList`和`SLList`. 我们还构建了一个接口`List61B`来强制执行特定的列表方法`AList`并且`SLList`必须实现。您可以在以下链接中找到代码：

- [List61B](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/master/inheritance2/List61B.java)

  ```java
  public interface List61B<Item>{
      ...
      /** Prints the list. Works for ANY kind of list. */
      default public void print() {
          for (int i = 0; i < size(); i = i + 1) {
              System.out.print(get(i) + " ");
          }
      }
      ...
  }
  ```

  - 关于default关键词，可参见 [cainiao](https://www.runoob.com/java/java8-default-methods.html)

- [AList](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/master/inheritance1/AList.java)

  ```java
  public class AList<Item> implements List61B<Item>
  ```

- [SLList](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/master/inheritance2/SLList.java)

  ```java
  public class SLList<Blorp> implements List61B<Blorp>
  ```

这就是我们可能使用`List61B`类型的方式：

```java
List61B<Integer> L = new AList<>();
L.addLast(5);
L.addLast(10);
L.addLast(15);
L.print();
```

### 真实 Java 代码中的List

我们从头开始构建了一个列表，但 Java 提供了一个内置`List`接口和几个实现，例如`ArrayList`.

**记住，既然`List`是一个接口，我们不能实例化它！也就是不能像下面这样：**

```java
List<Integer> L = new List<>();
```

**我们必须实例化它的一种实现。也就是说，要访问它，我们可以使用类、接口的全名（“规范名称”）：**

```java
java.util.List<Integer> L = new java.util.ArrayList<>();
```

**但是，这有点冗长。与 import 类似`JUnit`，我们可以导入 java 库：**

```java
import java.util.List;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();//之后，就不必再加上包名
        L.add(5);
        L.add(10);
        System.out.println(L);
    }
}
```

## Sets

集合是唯一元素的集合——每个元素只能有一个副本。也没有秩序感。

##### Java

Java 具有`Set`接口和实现，例如`HashSet`. 如果您不想使用全名，请记住导入它们！（不过现在IDEA都提示了）

```java
import java.util.Set;
import java.util.HashSet;
```

示例使用：

```java
Set<String> s = new HashSet<>();
s.add("Tokyo");
s.add("Lagos");
System.out.println(S.contains("Tokyo")); // true
```

##### Python

在 python 中，我们简单地调用`set()`. 要检查`contains`我们不使用方法，而是使用关键字`in`。

```python
s = set()
s.add("Tokyo")
s.add("Lagos")
print("Tokyo" in s) // True
```

### ArraySet

我们的目标是`ArraySet`使用以下方法制作我们自己的集合：

- `add(value)`: 如果不存在，则将值添加到集合中
- `contains(value)`: 检查 ArraySet 是否包含键
- `size()`: 返回值的数量

如果您想自己尝试，请在此处找到“自己动手”的[起始代码](https://github.com/Berkeley-CS61B/lectureCode-sp19/blob/af2325c600010a8894a6ce3a3ccf517547145ec1/exercises/DIY/inheritance4/ArraySet.java)，然后对其进行补全

```java
import org.omg.CORBA.PRIVATE_MEMBER;

public class ArraySet<T> {

    private T[] t;
    private int size;

    public ArraySet() {
        t = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for(int i = 0; i < size; i++){
            if(t[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        /**
         * 关于这里检查null的问题，为什么用 == 而不用 equals 的原因
         * https://stackoverflow.com/questions/4501061/java-null-check-why-use-instead-of-equals
         */
        if(x == null)
            return;
        if(this.contains(x))
            return;
        t[size] = x;
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}

/**
 * 测试结果如下
 * D:\JDK8\jdk1.8.0_311\bin\java.exe 
 * true
 * 3
 */
```



在下面的讲座片段中，Hug 教授提出了解决方案,这是我们现在的代码：

```apl
但是下面这个代码是不能验证add(null)的情况的
```

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

    /* Associates the specified value with the specified key in this map. */
    public void add(T x) {
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
}
```







