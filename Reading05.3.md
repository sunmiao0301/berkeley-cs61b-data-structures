## Reading 5.3

### 创建另一个泛型类 — Map

现在我们已经创建了通用列表，例如`DLLists`和`ALists`，**让我们继续使用不同的数据类型：Maps 映射。**

**Maps 让您可以将键与值关联起来，例如，“Josh 在考试中的分数是 0”的语句可以存储在将学生与其考试分数相关联的 Map 中。Java中的Map是 Python 中字典的等价物。**

我们将创建`ArrayMap`类，来实现`Map61B`接口，接口是 Java 内置接口的受限版本`Map`。`ArrayMap`将有以下方法：

```java
 put(key, value): Associate key with value.
 containsKey(key): Checks if map contains the key.
 get(key): Returns value, assuming key exists.
 keys(): Returns a list of all keys.
 size(): Returns number of keys.
```

对于本练习，我们将忽略调整大小。**关于`Map61B`接口（以及一般的 Java`Map`接口）需要注意的一点是，每个键一次只能有一个值。**如果 Josh 映射到 0，然后我们说“哦等等，有一个错误！Josh 实际上在考试中得了 100”，我们删除 Josh 映射到的值 0 并将其替换为 100。

随意尝试自己构建一个`ArrayMap`

#### 自己随意构建的ArrayMap如下：

```java
import java.util.LinkedList;
import java.util.List;

public class ArrayMap<K, V> {

    private K[] key;
    private V[] value;
    private int size;

    public ArrayMap(int size){
        key = (K[]) new Object[size];//泛化数组的方案
        value = (V[]) new Object[size];
        size = 0;
    }

    public boolean containsKey(K k){
        for(int i = 0; i < size; i++){
            if(key[i].equals(k)){// == or equals?
                return true;
            }
        }
        return false;
    }//: Checks if map contains the key.

    public void put(K k, V v){
            for(int i = 0; i < size; i++){
                if(key[i].equals(k)){// == or equals?
                    value[i] = v;
                    return;
                }
            }
            key[size] = k;
            value[size] = v;
            size++;
    }//: Associate key with value.

    public V get(K k){
        int i = 0;
        while(i < size){
            if(key[i].equals(k)){// == or equals?
                return value[i];
            }
            i++;
        }
        return value[i - 1];//这一句是废的 get之前必须exist
    }//: Returns value, assuming key exists.

    public List keys(){
        List<K> list = new LinkedList<>();
        for(int i = 0; i < size; i++){
            list.add(key[i]);
        }
        return list;
    }//: Returns a list of all keys.

    public int size(){
        return size;
    }//: Returns number of keys.

    public static void main(String[] args){
        ArrayMap<String, Integer> map = new ArrayMap<>(100);
        map.put("simon", 1);
        System.out.println(map.containsKey("simon"));
        System.out.println(map.get("simon"));
        System.out.println(map.keys());
        System.out.println(map.size());
    }
}

/** 运行结果如下：
 * D:\JDK8\jdk1.8.0_311\bin\java.exe 
 *         true
 *         1
 *         [simon]
 *         1
 */
```

作为参考，教师完整的实现如下。

**p.s. 教师的方案，通过一个KeyIndex(K key)函数大大优雅了每个函数的实现。**

```java
package Map61B;

import java.util.List;
import java.util.ArrayList;

/***
 * An array-based implementation of Map61B.
 ***/
public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
    * Returns the index of the key, if it exists. Otherwise returns -1.
    **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
            return i;
        }
        return -1;
    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }
}
```

`K`注意：命名泛型的决定`V`是任意的（但要直观，比如 K - Key，V - Value）。我们也可以用`Potato`and`Sauce`或任何其他名称替换这些泛型。**但是，在本课程和其他课程中，Java 中的泛型表示为单个大写字母是很常见的。**

这里有一些有趣的事情；查看代码的顶部，我们声明`package Map61B;`. 我们稍后会讨论这个，但现在只知道这意味着我们将我们的`ArrayMap`类放在一个名为 Map61B 的文件夹中。此外，`List`我们`ArrayList`从`java.utils`.

**练习 5.2.1：**在我们当前的 ArrayMap 实现中，有一个 bug。你能弄清楚它是什么吗？

**答：**在该`keys`方法中，for 循环应该迭代 until `i == size`，而不是`keys.length`。

```apl
会空指针错误。
```

## ArrayMap 和自动装箱的puzzle

如果我们写一个如下所示的测试：

```java
@Test
public void test() { 
    ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
    am.put(2, 5);
    int expected = 5;
    //exceptd是int，am.get(2)是Integer
    assertEquals(expected, am.get(2));
}
```

你会发现我们得到一个编译时错误！

```bash
$ javac ArrayMapTest.java
ArrayMapTest.java:11: error: reference to assertEquals is ambiguous
    assertEquals(expected, am.get(2));
    ^
    both method assertEquals(long, long) in Assert and method assertEquals(Object, Object) in Assert match
```

我们得到这个错误是因为 JUnit 的`assertEquals`方法被重载了，例如。`assertEquals(int expected, int actual)`,`assertEquals(Object expected, Object actual)`等。因此，Java 不确定调用哪个方法`assertEquals(expected, am.get(2))`，这需要一个参数自动装箱/拆箱。

```apl
这一部分的几个练习还是不是很熟悉
```

**练习 5.2.2** 我们需要做什么才能调用`assertEquals(long, long)`？

A.)加宽`expected`到`long` 

B.) 自动装箱`expected`到`Long` 

C.)`am.get(2)` 拆箱 

D.) 将拆箱加宽`am.get(2)`到long

**答案**A、C 和 D 都有效。

**练习 5.2.3** 我们如何使`assertEquals(Object, Object)`工作？

**回答**Autobox来使`expected`到`Integer`因为`Integers`are `Objects`。

**练习 5.2.4** 我们如何通过强制转换使代码编译？

**回答**Cast`expected`到`Integer`。

## 通用方法

下一节的目标是创建一个有两个方法的类`MapHelper`：

- `get(Map61B, key)`: 如果存在则返回map中给定键对应的值，否则返回null。
  - 这很有用，因为`ArrayMap`当前有一个错误，如果我们尝试获取不存在于`ArrayMap`.

- `maxKey(Map61B)`: 返回给定的所有键的最大值`ArrayMap`。仅在可以比较键时才有效。

### 实现获取

`get`是一个静态方法，它接受一个 Map61B 实例和一个键，如果存在则返回与该键对应的值，否则返回 null。

**练习 5.2.5** 试着自己写这个方法！

```java
public static V get(Map61B<K, V> map, K key){
    int i = 0;
    while(i < Map61B.size){
        if(K[i].equals(key)){
            return V[i];
        }
    }
    return null;
}
//上面是我自己写的，但是实际上这种方法标头会产生编译错误
```



正如您在视频中看到的，我们可以通过将参数声明为 String 和 Integer 来编写一个非常有限的方法，如下所示：

```java
public static Integer get(Map61B<String, Integer> map, String key) {
    ...
}
```

我们将这种方法限制为只接受`Map61B<String, Integer>`，这不是我们想要的！`Map61B`无论泛型的实际类型是什么，我们都希望它采用任何类型。但是，以下方法标头会产生编译错误：

```java
public static V get(Map61B<K, V> map, K key) {
    ...
}
```

**这是因为在类头中定义了泛型，Java 等待用户实例化该类的一个对象，以便知道每个泛型将是什么实际类型。**

**但是，在这里我们想要一个特定于此方法的泛型。此外，我们并不关心我们的论点中的实际类型`K`和`V`采用什么`Map61B`——重要的部分是无论是什么，都会返回`V`一个类型的对象。**

**因此，我们看到了对泛型方法的需求。要将方法声明为泛型，必须在返回类型之前指定形式类型参数：**

```java
//如下所示
public static <K,V> V get(Map61B<K,V> map, K key) {
    if map.containsKey(key) {
        return map.get(key);
    }
    return null;
}
```

**尤其是注意函数返回值怎么写，这里之所以不同于我们在ArrayMap中可以直接返回V的原因是，这个get()方法属于mapHelper类，并且是一个类方法，也就是说，我们想直接通过mapHelper.get(isMap, 5)的形式来调用，而不实例化一个mapHelper对象，在这种情况下，我们需要将函数的返回值写为<K, V> V**

```apl
public static <K,V> V get(Map61B<K,V> map, K key)
```

这是一个如何调用它的示例：

```java
ArrayMap<Integer, String> isMap = new ArrayMap<Integer, String>();
System.out.println(mapHelper.get(isMap, 5));
```

您不需要任何明确声明要插入的类型。Java 可以推断 isMap 是一个从 `Integers`到`Strings`的`ArrayMap`。

## 实现 maxKey

**练习 5.2.6** 试着自己写这个方法！

```java
public static <K, V> K maxKey(Map61B<K, V> map){
    //K max = (K) new Object;
    //K max = map.key[0];//数组是私有的
    //那么问题来了，没法得到一个K值啊
    while(int i = 0; i < map.size, i++){
        if(){
            max = ;
        }
    }
    return max;
}
```

下面是一些看起来不错，但并不完全正确的东西：

```java
public static <K, V> K maxKey(Map61B<K, V> map) {
    List<K> keylist = map.keys();
    K largest = map.get(0);
    for (K k: keylist) {
        if (k > largest) {
            largest = k;
        }
    }
    return largest;
}
```

**习题 5.2.7** 你能看出这个方法有什么问题吗？

**答：**运算`>`符不能用于比较`K`对象。这仅适用于基元，`map`可能不包含基元\

我们将这样重写这个方法：

```java
public static <K, V> K maxKey(Map61B<K, V> map) {
    List<K> keylist = map.keys();
    K largest = map.get(0);
    for (K k: keylist) {
        if (k.compareTo(largest)) {
            largest = k;
        }
    }
    return largest;
}
```

**习题5.2.8** 这也是错的，为什么？

**回答**并非所有对象都有`compareTo`方法！

#### 正确方案

正确的方案是：

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0208complex.png)

我们将在函数头中为泛型方法引入更多语法。

```java
public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {...}
```

方法键必须实现可比较的`K extends Comparable<K>`接口，并且可以与其他 K 进行比较。我们需要包含`<K>`after`Comparable`因为`Comparable`它本身是一个通用接口！因此，我们必须指定我们想要什么样的可比性。在这种情况下，我们希望将 K's 与 K's 进行比较。

## 键入上限

您可能想知道，为什么它“扩展”可比较而不是“实施”？Comparable毕竟是一个接口。

好吧，事实证明，这种情况下的“扩展”与多态性上下文中的含义不同。

当我们说 Dog 类扩展了 Animal 类时，我们是在说 Dogs 可以做任何动物可以做的事情，甚至更多！我们正在**赋予**Dog 动物的能力。当我们说 K 扩展 Comparable，我们只是在陈述一个事实。我们并没有**赋予**K Comparable 的能力，我们只是说 K**必须是**Comparable 的。这种不同的用法`extends`称为类型上限。令人困惑？没关系，这*很*混乱。请记住，在继承的上下文中，`extends`关键字在赋予子类超类的能力方面是积极的。你可以把它想象成一个仙女教母：她看到了你的需求，并用她的一些仙术帮助你。另一方面，在泛型的上下文中，`extends`只是陈述了一个事实：你必须是你所扩展的任何东西的子类。**当与泛型一起使用时（如在泛型方法头中），`extends`施加约束而不是授予新功能。**它类似于算命先生，他只是告诉你一些事情而不做太多事情。

## 概括

我们已经看到了 Java 的四个使泛型更强大的新特性：

- 原始包装类型的自动装箱和自动拆箱。
- 原始类型之间的提升/扩展。
- **方法的泛型类型规范（在返回类型之前）。**
- **在泛型方法中键入上限（例如`K extends Comparable<K>`）。**

# 这最后两点没有理解







