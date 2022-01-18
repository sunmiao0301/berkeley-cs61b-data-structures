## Lists1 Study Guide

## 概述

**位**计算机将信息存储为内存，并使用位序列（0 或 1）表示此信息。

**基元**基元是信息的表示。Java 中有 8 种基本类型：byte、short、int、long、float、double、boolean 和 char。每个原语由一定数量的位表示。例如，整数是 32 位原语，而字节是 8 位原语。

**声明基元**当我们将一个变量声明为基元（即 `int x;`）时，我们留出足够的内存空间来保存这些位（在本例中为 32）。我们可以把它想象成一个装有比特的盒子。Java 然后将变量名映射到这个框。假设我们有一行之前定义的`int y = x;`代码`x` 。Java 会将框内的位复制到`x`框内的位`y`中。

**创建对象**当我们使用关键字创建类的实例时`new` ，Java 会为每个字段创建位框，其中每个框的大小由每个字段的类型定义。例如，如果一个 Walrus 对象有一个 `int`变量和一个`double`变量，那么 Java 将分配两个总计 96 位 (32 + 64) 的框来保存这两个变量。这些将被设置为默认值，如 0。然后构造函数进入并将这些位填充为适当的值。构造函数的返回值将返回盒子在内存中的位置，通常是一个 64 位的地址。然后可以将该地址存储在具有“引用类型”的变量中。

**引用类型**如果变量不是原始类型，那么它就是引用类型。当我们声明对象变量时，我们使用引用类型变量来存储对象所在的内存中的位置。请记住，这是构造函数返回的内容。引用类型始终是大小为 64 位的框。请注意，该变量并不存储整个对象本身！

**相等的黄金法则**

**对于基元，行将盒子`int y = x`内的位复制`x`到`y`盒子中。对于引用类型，我们做同样的事情。在行`Walrus newWalrus = oldWalrus;`中，我们将框中的 64 位地址复制`oldWalrus`到`newWalrus`框中。**所以我们可以把等号的黄金法则（GroE）想象成：当我们用等号分配一个值时，我们只是将位从一个内存盒复制到另一个！

**参数传递**假设我们有一个方法`average(double a, double b)`。此方法采用两个双精度数作为参数。参数传递也遵循 GRoE，即当我们调用此方法并传入两个双精度时，我们将这些变量中的位复制到参数变量中。

**数组实例化**

**数组也是对象**，也使用`new`关键字进行实例化。这意味着声明一个数组变量（即`int[] x;`）将创建一个 64 位引用类型变量，该变量将保存该数组的位置。当然，现在这个框包含值 null，因为我们还没有创建数组。数组的`new`关键字将创建数组并返回该数组在内存中的位置。因此`int[] x = new int[]{0, 1, 2, 3, 4};`，我们将这个新创建的数组的位置设置为变量 x。请注意，数组的大小是在创建数组时指定的，不能更改！

**整数列表。**使用引用，我们递归地定义了`IntList`类。 `IntLists`是可以改变大小的整数列表（与数组不同），并存储任意数量的整数。`size`可以通过递归或迭代来编写辅助方法。

## 练习

### C级

1. 完成[在线教科书](https://joshhug.gitbooks.io/hug61b/content/chap2/chap21.html)中的练习。

```finished```

1. 如果双打比整数更通用，为什么我们不总是使用它们呢？这样做有什么缺点吗？

```apl
Why don't we just use double instead of int
isn't double better for storing any kind of numerical data, so why do we use int?

Basil Amazing :
double uses more space, which probably isn't a huge concern nowadays. Still, sometimes it's useful to make it clear that the intended value is an integer and will not have a decimal part

Dan Walker :
Also, apart from memory allocation size, whole number arithmetic operations are faster than floating points arithmetic operations. Plus, there are places where floating points usage doesn't make sense, for example, when we refer to an element's  index in an array, or when we iterate in loops, using floating point types for loop iterator will cause problem, because iterator value isn't precise.
```

1. 存储 32 个条目的 int 数组和 300 个条目的 int 数组的地址之间的内存成本有多少不同？

### B级

1. [使用IntList 类的起始代码重写 Lecture 中](https://github.com/Berkeley-CS61B/lectureCode-sp18/blob/master/exercises/lists1/IntList.java)的`size`、`iterativeSize`和`get`方法。
2. 编写方法`incrList`并`dincrList`如 [Lists1Exercises](https://github.com/Berkeley-CS61B/lectureCode-sp18/blob/master/exercises/lists1/Lists1Exercises.java)中所述。如果您的解决方案使用`size`、`iterativeSize`或`get`，您需要先完成上一个练习。