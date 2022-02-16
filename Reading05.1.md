## Reading 5.1

## 工业强度句法

在本书的前面部分，我们已经讨论了各种数据结构以及 Java 支持它们实现的方式。**在本章中，我们将讨论用于工业级 Java 程序实现的各种补充主题。**

这并不是一本全面的 Java 指南，而是重点介绍了在学习本课程时可能对您有用的特性。

## 自动转换

### 自动装箱和拆箱

正如我们在上一章中看到的，我们可以使用`<>`语法定义具有泛型类型变量的类，例如`LinkedListDeque<Item>`and `ArrayDeque<Item>`。当我们想实例化一个其类使用泛型的对象时，我们必须用一个具体的类来代替泛型，即指定什么类型的项目将进入该类。

回想一下，Java 有 8 种原始类型——所有其他类型都是引用类型。Java 的一个特殊特性是我们不能提供原始类型作为泛型的实际类型参数，例如`ArrayDeque<int>`语法错误。相反，我们使用`ArrayDeque<Integer>`. 对于每种原始类型，我们使用下表所示的相应引用类型。这些引用类型称为“包装类”。

![包装类](https://joshhug.gitbooks.io/hug61b/content/assets/wrapper_classes.png)

```java
//为什么不?
ArrayList<int> L = new ArrayList<int>();

//因为会编译报错如下
required: reference
found: int

//也就是说需要的是一个引用类型
//并且Java中对于八种基本数据类型，都有一个所谓的包装器类型，它是这些基本数据类型的引用版本。
```

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0208referenceof8.png)

如果我们不了解自动转换，当我们面对一个ArrayList<Integer>的时候，将导致在使用通用数据结构时必须在原始类型和引用类型之间手动转换。例如，我们可能会：

```java
public class BasicArrayList{
    public static coid main(String[] args){
        ArrayList<Integer> L = new ArrayList<Integer>();
        L.add(new Integer(5));
        int first = L.get(0).valueOf();
    }
}
```

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0208ifnoautoboxing.png)

但是实际上，幸运的是，Java 可以在原始类型和包装类型之间进行隐式转换（自动拆箱和装箱），因此下面的代码可以正常工作：

```java
public class BasicArrayList{
    public static coid main(String[] args){
        ArrayList<Integer> L = new ArrayList<Integer>();
        L.add(5);
        int first = L.get(0);
    }
}
```

这样做的原因是 Java 会自动在原始类型与其对应的引用类型之间“装箱”和“拆箱”值。也就是说，如果 Java 需要一个包装类型，如 Integer，而您提供一个原始类型，如 int，它将“自动装箱”整数。例如，如果我们有以下功能：

```java
public static void blah(Integer x) {
    System.out.println(x);
}
```

我们称之为：

```java
int x = 20;
blah(x);//此时int值 x将被自动装箱为引用类型Integer
```

然后 Java 隐式创建一个`Integer`值为 20 的 new，导致调用等效于调用`blah(new Integer(20))`。这个过程称为自动装箱。



同样，如果 Java 期望一个原语：

```java
public static void blahPrimitive(int x) {
    System.out.println(x);
}
```

但是你给它一个相应的包装类型的值：

```java
Integer x = new Integer(20);
blahPrimitive(x);//此时Integer值 x将被自动拆箱为基本数据类型int
```

它会自动拆箱整数，相当于调用`Integer`类的`valueOf`方法。

### 注意事项

但是，我们仍需要知道自动装箱和拆箱的存在，因为如果模棱两可，我们偶尔会遭遇一些重大的性能影响。

在进行自动装箱和拆箱时，需要牢记以下几点：

- 数组永远不会自动装箱或自动拆箱，例如，如果您有一个整数数组`int[] x`，并尝试将其地址放入 type 变量中`Integer[]`，编译器将不允许您的程序编译。
- 自动装箱和拆箱也会对性能产生可衡量的影响。也就是说，依赖于自动装箱和拆箱的代码将比避免这种自动转换的代码慢。**（也就是说：ArrayList采用Integer这一事实意味着其性能不会像硬连线以处理int原始类型的ArrayList版本的性能那么好）**
- 此外，包装类型比原始类型使用更多的内存。在大多数现代计算机上，您的代码不仅必须持有对对象的 64 位引用，而且每个对象还需要 64 位开销用于存储对象的动态类型等内容。**(64位的地址引用 + 32位的·对象字段大小 + 64位用于垃圾收集器的神秘内存 = 160位)**
  - 有关内存使用的更多信息，请参阅[此链接](http://www.javamex.com/tutorials/memory/object_memory_usage.shtml)或[此链接](http://blog.kiyanpro.com/2016/10/07/system_design/memory-usage-estimation-in-java/)。



### Widening

与自动装箱/拆箱过程类似，Java 也会在需要时自动 **widen** 原语。具体来说，如果程序需要 T2 类型的原语并被赋予 T1 类型的变量，并且 T2 类型可以采用比 T1 更广泛的值范围，则该变量将隐式转换为 T2 类型。

**例如，Java 中的双精度数比整数宽。如果我们有如下所示的函数：**

```java
public static void blahDouble(double x) {
    System.out.println(“double: “ + x);
}
```

**我们可以使用 int 参数调用它：**

```java
int x = 20;
blahDouble(x);
```

**效果和我们做的一样`blahDouble((double) x)`。谢谢Java！**

**如果要从较宽的类型变为较窄的类型，则必须手动转换。例如，如果您有以下方法：**

```java
public static void blahInt(int x) {
    System.out.println(“int: “ + x);
}
```

**然后，如果我们想使用双精度值调用此方法，则需要使用强制转换，例如**

```java
double x = 20;
blahInt((int) x);
```

**有关扩展的更多详细信息，包括对哪些类型比其他类型更宽的完整描述，请参阅[官方 Java](http://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html)文档。**