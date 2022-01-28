## Reading 3.1 

## 测试和选择排序

中高级程序员可以拥有的最重要的技能之一是能够判断您的代码何时正确。在本章中，我们将讨论如何编写测试来评估代码的正确性。在此过程中，我们还将讨论一种称为选择排序的排序算法。

### 一种新的方式

编写程序时，可能会出现错误。在课堂环境中，您可以通过用户交互、代码分析和自动评分器测试的某种组合来获得对代码正确性的信心，最后一项在许多情况下是最重要的，特别是因为它是您获得积分的方式。

当然，自动分级机并不是魔术。它们是讲师编写的代码，与您编写的代码基本上没有什么不同。在现实世界中，这些测试是由程序员自己编写的，而不是一些仁慈的 Josh-Hug （教师名）之类的第三方。

在本章中，我们将探讨如何编写自己的测试。我们的目标是创建一个名为的类，该类`Sort`提供一种`sort(String[] x)`对数组中的字符串进行破坏性排序的方法`x`。

作为一种全新的思维方式，我们将首先编写`testSort()`，只有在完成测试后，我们才会继续编写实际的排序代码。

### 临时测试

编写测试`Sort.sort`相对简单，尽管很乏味。我们只需要创建一个输入、调用`sort`并检查方法的输出是否正确。如果输出不正确，我们打印出第一个不匹配并终止测试。例如，我们可能会创建一个测试类，如下所示：

```java
public class TestSort {
    /** Tests the sort method of the Sort class. */
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
                break;
            }
        }
    }

    public static void main(String[] args) {
        testSort();
    }
}
```

我们可以通过创建一个空白方法来测试我们的测试，`Sort.sort`如下所示：

```java
public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) {        
    }
}
```

如果我们`testSort()`使用这个空白`Sort.sort`方法运行该方法，我们会得到：

```sh
Mismatch in position 0, expected: an, but got: i.
```

我们收到错误消息的事实是一件好事！这意味着我们的测试正在运行。对此非常有趣的是，我们现在创建了一个小游戏供自己玩，目标是修改代码，`Sort.sort`以便不再出现此错误消息。这有点心理上的把戏，但许多程序员发现为自己创建这些小迷你拼图几乎让人上瘾。

事实上，这很像你有一个自动评分器来上课的情况，你发现自己迷上了让自动评分器给你爱和认可的想法。您现在可以为您的代码创建一个评委，只有正确完成代码才能赢得他的尊重。

**重要提示：您可能会问“为什么要遍历整个数组？为什么不使用 `==`来检查数组是否相等？”。原因是，当我们测试两个对象的相等性时，我们不能简单地使用`==`运算符。运算符比较内存盒中的`==`文字位，例如`input == expected`将测试 和 的地址`input`是否`expected`相同，而不是数组中的值是否相同。相反，我们使用了一个循环 in `testSort`，并打印出第一个不匹配项。您也可以使用内置方法`java.util.Arrays.equals`而不是循环。**

### 关于数组中的 == 和 equals（对于一个整型数组int[] a，a是其地址，a[0]就已经通过地址运算拿到其内的实际整数了

**具体可见如下代码：**

```java
public class ClassNameHere {
   public static void main(String[] args) {
      int[] a = new int[]{1, 2, 3};
      int[] b = new int[]{1, 2, 3};
      //比较整个数组内容
      System.out.println(a == b);//结果是false 因为是比较地址
      System.out.println(java.util.Arrays.equals(a, b));//结果是true 因为是比较数组内的值
       
      //比较数组中单个内容的值
      System.out.println(a[1] == b[1]);//结果是true 因为比较的是数组内数据的值
      System.out.println((a[1]).equals(b[1]));//结果报错（Error: int cannot be dereferenced） 因为a[1]已经不是引用类型了，不能再用针对引用类型设计的equals方法
      
      System.out.println(a[1] == 2);//结果是true 因为比较的是数组内数据的值
      
   }
}
/**
 * p.s. 注意 String也是引用类型 所以比较字符串内容相等应该用 s1.equals(s2)
 */

```

#### 当然，我以上说的是整型数组！在下面这种情况，a[i].equals(b[i])就是正确且唯一正确的了

```java
package ArrayDeque;

public class ArrayDeque {

        /** Tests the sort method of the Sort class. */
        public static void testSort() {
            String[] input = {"i", "have", "an", "egg"};
            String[] expected = {"an", "egg", "have", "i"};
            //Sort.sort(input);
            for (int i = 0; i < input.length; i += 1) {
                /**
                 * equals
                 */
                if (!input[i].equals(expected[i])) {
                    System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i] + ".");
                    break;
                }
            }
        }

        public static void main(String[] args) {
            testSort();
        }

}
```

虽然上面的单个测试不是很多工作，但编写一套这样的*临时*测试将非常乏味，因为它需要编写一堆不同的循环和打印语句。在下一节中，我们将看到该`org.junit`库如何为我们节省大量工作。

### JUnit 测试

该`org.junit`库提供了许多有用的方法和有用的功能来简化测试的编写。例如，我们可以将上面的简单*临时*测试替换为：

```java
public static void testSort() {
    String[] input = {"i", "have", "an", "egg"};
    String[] expected = {"an", "egg", "have", "i"};
    Sort.sort(input);
    org.junit.Assert.assertArrayEquals(expected, input);
}
```

**这段代码要简单得多**，并且或多或少做同样的事情，即如果数组不相等，它会告诉我们第一个不匹配。例如，如果我们运行`testSort()`一个`Sort.sort`什么都不做的方法，我们会得到：

```sh
Exception in thread "main" arrays first differed at element [0]; expected:<[an]> but was:<[i]>
    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
    at org.junit.Assert.internalArrayEquals(Assert.java:532)
    ...
```

虽然这个输出比我们的*临时*测试有点难看，但我们将在本章的最后看到如何让它变得更好。

### 选择排序

在我们编写`Sort.sort`方法之前，我们需要一些排序算法。也许最简单的排序算法是“选择排序”。选择排序包括三个步骤：

- 找到最小的项目。
- 把它移到前面。
- 选择对剩余的 N-1 个项目进行排序（不触及前面的项目）。

例如，假设我们有数组`{6, 3, 7, 2, 8, 1}`。这个数组中最小的项是`1`，所以我们将 移到`1`前面。有两种自然的方法可以做到这一点：一种是将 贴`1`在前面并将所有数字滑过，即`{1, 6, 3, 7, 2, 8}`。然而，更有效的方法是简单地将`1`与旧的前端（在这种情况下`6`）交换，产生`{1, 3, 7, 2, 8, 6}`。

我们只需对剩余的数字重复相同的过程，即`... 3, 7, 2, 8, 6}`is中的最小项`2`。换到前面，我们得到`{1, 2, 7, 3, 8, 6}`. 重复直到我们得到一个排序数组，我们会得到`{1, 2, 3, 7, 8, 6}`, then `{1, 2, 3, 6, 8, 7}`, 然后 finally `{1, 2, 3, 6, 7, 8}`。

我们可以通过使用最初在第 2.4 章中介绍的不变量的概念在数学上证明这种排序算法在任何数组上的正确性，尽管我们不会在这本教科书中这样做。在继续之前，请尝试写出自己的数字短数组并对其执行选择排序，这样您就可以确保您明白了。

现在我们知道了选择排序是如何工作的，我们可以在空白`Sort.sort`方法中写一些简短的注释来指导我们的思考：

```java
public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) { 
           // find the smallest item
           // move it to the front
           // selection sort the rest (using recursion?)
    }
}
```

在接下来的部分中，我将尝试完成选择排序的实现。我会以类似于学生处理问题的方式这样做，所以**我会在此过程中犯一些故意的错误**。这些故意的错误是一件好事，因为它们将有助于证明测试的有用性。如果您在阅读时发现任何错误，请不要担心，我们最终会纠正它们。

### P.S. 在看下面之前，我先自己实现了把

```java
import java.util.Arrays;

public class Sort {
    public static void sort(int[] a){
        int len = a.length;
        int minIndex = 0;
        for(int i = 0; i < len; i++){
            int min = Integer.MAX_VALUE;
            for(int j = i; j < len; j++){
                if(a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }
            Sort.swap(i, minIndex, a);
        }
    }
    public static void swap(int a, int b, int[] array){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    public static void main(String[] args){
        int[] a = new int[]{1, 3, 2, 6, 5};
        Sort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

/**
 * 运行结果如下
 * [1, 2, 3, 5, 6]
 */
```

### 找到最小的

最自然的起点是编写一个查找列表中最小项目的方法。与 一样`Sort.sort`，我们将在完成方法之前先编写一个测试。首先，我们将创建一个`findSmallest`简单地返回一些任意值的虚拟方法：

```java
public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) { 
           // find the smallest item
           // move it to the front
           // selection sort the rest (using recursion?)
    }

    /** Returns the smallest string in x. */
    public static String findSmallest(String[] x) {
        return x[2];
    }
}
```

显然这不是一个正确的实现，但我们选择推迟真正思考如何`findSmallest`工作，直到我们编写了一个测试。使用该`org.junit`库，在我们的类中添加这样的测试`TestSort`非常容易，如下所示：

```java
public class TestSort {
    ...
    public static void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        String actual = Sort.findSmallest(input);
        org.junit.Assert.assertEquals(expected, actual);        
    }

    public static void main(String[] args) {
        testFindSmallest(); // note: we changed this from testSort!
    }
}
```

与 一样`TestSort.testsort`，然后我们运行我们的`TestSort.testFindSmallest`方法以确保它失败。当我们运行这个测试时，我们会看到它实际上通过了，即没有消息出现。这是因为我们只是碰巧硬编码了正确的返回值`x[2]`。让我们修改我们的`findSmallest`方法，让它返回一些绝对不正确的东西：

```java
/** Returns the smallest string in x. */
public static String findSmallest(String[] x) {
    return x[3];
}
```

进行此更改后，当我们运行时`TestSort.testFindSmallest`，我们会得到一个错误，这是一件好事：

```sh
Exception in thread "main" java.lang.AssertionError: expected:<[an]> but was:<[null]>
    at org.junit.Assert.failNotEquals(Assert.juava:834)
    at TestSort.testFindSmallest(TestSort.java:9)
    at TestSort.main(TestSort.java:24)
```

和以前一样，我们为自己设置了一个小游戏来玩，我们现在的目标是修改代码，`Sort.findSmallest`以便不再出现此错误。这是一个比`Sort.sort`上班更小的目标，这可能更容易上瘾。

旁注：我只是碰巧返回了正确的值，这似乎有点做作`x[2]`。然而，当我录制这个讲座视频时，我确实在无意中犯了这个错误！

接下来我们转向实际写作`findSmallest`。这似乎应该相对简单。如果您是 Java 新手，您最终可能会编写如下所示的代码：

```java
/**  Returns the smallest string in x. */
public static String findSmallest(String[] x) {
    String smallest = x[0];
    for (int i = 0; i < x.length; i += 1) {
        if (x[i] < smallest) {
            smallest = x[i];
        }
    }
    return smallest;
}
```

但是，这会产生编译错误“< cannot be applied to 'java.lang.String'”。问题是 Java 不允许使用 < 运算符在字符串之间进行比较。

当您在编程时遇到这样一个很容易描述的问题时，最好求助于搜索引擎。例如，我们可以用 Google 搜索“小于字符串 Java”。这样的搜索可能会产生类似[这样](https://stackoverflow.com/questions/5153496/how-can-i-compare-two-strings-in-java-and-define-which-of-them-is-smaller-than-t)的 Stack Overflow 帖子。

这篇文章的一个流行答案解释说，如果 ，该`str1.compareTo(str2)`方法将返回一个负数`str1 < str2`，如果它们相等，则返回 0，如果 ，则返回一个正数`str1 > str2`。

将其合并到我们的代码中，我们最终可能会得到：

```java
/** Returns the smallest string in x. 
  * @source Got help with string compares from https://goo.gl/a7yBU5. */
public static String findSmallest(String[] x) {
    String smallest = x[0];
    for (int i = 0; i < x.length; i += 1) {
        int cmp = x[i].compareTo(smallest);
        if (cmp < 0) {
            smallest = x[i];
        }
    }
    return smallest;
}
```

请注意，我们使用`@source`标签来引用我们的来源。我正在为那些将 61B 作为正式课程的人举例说明这一点。这不是典型的现实世界实践。

由于我们使用的语法特性对我们来说是全新的，我们可能对我们`findSmallest`方法的正确性缺乏信心。幸运的是，我们刚刚编写了该测试。如果我们尝试运行它，我们将看到没有打印任何内容，这意味着我们的代码可能是正确的。

我们可以通过添加更多测试用例来增强我们的测试以增加我们的信心。例如，我们可能会更改`testFindSmallest`为如下所示：

```java
public static void testFindSmallest() {
    String[] input = {"i", "have", "an", "egg"};
    String expected = "an";

    String actual = Sort.findSmallest(input);
    org.junit.Assert.assertEquals(expected, actual);        

    String[] input2 = {"there", "are", "many", "pigs"};
    String expected2 = "are";

    String actual2 = Sort.findSmallest(input2);
    org.junit.Assert.assertEquals(expected2, actual2);
}
```

重新运行测试，我们看到它仍然通过。我们不能绝对确定它是否有效，但我们更确定我们会在没有任何测试的情况下进行。

### 交换

看看我们`sort`下面的方法，我们需要编写的下一个辅助方法是将项目移动到前面的东西，我们将调用它`swap`。

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
       // find the smallest item
       // move it to the front
       // selection sort the rest (using recursion?)
}
```

编写一个`swap`方法非常简单，而且您以前可能已经这样做过。正确的实现可能如下所示：

```java
public static void swap(String[] x, int a, int b) {
    String temp = x[a];
    x[a] = x[b];
    x[b] = temp;
}
```

但是，目前，让我们引入一个故意的错误，以便我们可以展示测试的实用性。一个更天真的程序员可能会做类似的事情：

```java
public static void swap(String[] x, int a, int b) {    
    x[a] = x[b];
    x[b] = x[a];
}
```

在 JUnit 的帮助下，为这种方法编写测试非常容易。下面显示了一个示例测试。请注意，我们还编辑了 main 方法，以便它调用`testSwap`而不是`testFindSmallest`or `testSort`。

```java
public class TestSort {
    ...    

    /** Test the Sort.swap method. */
    public static void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void main(String[] args) {
        testSwap();
    }
}
```

`swap`正如我们所料，在我们的越野车上运行这个测试会产生一个错误。

```sh
Exception in thread "main" arrays first differed in element [2]; expected:<[i]> but was:<[an]>
    at TestSort.testSwap(TestSort.java:36)
```

值得注意的是，重要的是我们只调用`testSwap`而不是调用`testSort`。例如，如果我们的`main`方法如下，整个`main`方法一旦`testSort`失败就会终止执行，并且`testSwap`永远不会运行：

```java
public static void main(String[] args) {
    testSort();
    testFindSmallest();
    testSwap();
}
```

我们将在本章末尾学习一种更优雅的方式来处理多个测试，这将避免手动指定要运行的测试的需要。

现在我们有一个失败的测试，我们可以用它来帮助我们调试。一种方法是在`swap`方法内设置断点并使用 IntelliJ 中的可视化调试功能。如果您想了解有关调试的更多信息和练习，请查看[Lab3](https://sp19.datastructur.es/materials/lab/lab3/lab3)。逐行浏览代码可以立即清楚哪里出了问题（参见视频或自己尝试），我们可以通过更新代码来修复它，以包含本节开头的临时变量：

```java
public static void swap(String[] x, int a, int b) {
    String temp = x[a];
    x[a] = x[b];
    x[b] = temp;
}
```

重新运行测试，我们看到它现在通过了。

### 修改 findSmallest

现在我们已经完成了方法的多个部分，我们可以开始尝试将它们连接在一起以创建一个`Sort`方法。

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
       // find the smallest item
       // move it to the front
       // selection sort the rest (using recursion?)
}
```

很清楚如何使用我们的`findSmallest`and`swap`方法，但是当我们这样做时，我们立即意识到有一点不匹配：`findSmallest`返回 a `String`，并`swap`期望两个索引。

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
       // find the smallest item
       String smallest = findSmallest(x);

       // move it to the front
       swap(x, 0, smallest);

       // selection sort the rest (using recursion?)
}
```

换句话说，`findSmallest`应该返回的是最小字符串的索引，而不是字符串本身。犯这种愚蠢的错误是正常的，而且很容易做到，所以如果你发现自己在做类似的事情，不要担心。迭代设计是编写代码过程的一部分。

幸运的是，这种新设计可以轻松更改。我们只需要调整`findSmallest`返回一个`int`，如下所示：

```java
public static int findSmallest(String[] x) {
    int smallestIndex = 0;
    for (int i = 0; i < x.length; i += 1) {
        int cmp = x[i].compareTo(x[smallestIndex]);
        if (cmp < 0) {
            smallestIndex = i;
        }
    }
    return smallestIndex;
}
```

由于这是一个重要的更改，我们还应该更新`testFindSmallest`并确保它`findSmallest`仍然有效。

```java
public static void testFindSmallest() {
    String[] input = {"i", "have", "an", "egg"};
    int expected = 2;

    int actual = Sort.findSmallest(input);
    org.junit.Assert.assertEquals(expected, actual);        

    String[] input2 = {"there", "are", "many", "pigs"};
    int expected2 = 1;

    int actual2 = Sort.findSmallest(input);
    org.junit.Assert.assertEquals(expected2, actual2);
}
```

在修改`TestSort`以便运行此测试并运行`TestSort.main`后，我们看到我们的代码通过了测试。现在，修改排序，我们可以填写排序算法的前两个步骤。

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   // find the smallest item
   // move it to the front
   // selection sort the rest (using recursion?)
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
}
```

剩下的就是以某种方式对剩余的项目进行选择排序，也许使用递归。我们将在下一节中解决这个问题。

回顾我们所取得的成就，值得注意的是我们如何首先创建测试，并在我们尝试将它们用于任何事情之前使用它们来建立对实际方法有效的信心。这是一个非常重要的想法，如果您决定采用它，它将为您提供很好的服务。

### 递归辅助方法

要开始本节，请考虑如何进行完成所需的递归调用`sort`：

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
   // recursive call??
}
```

对于那些习惯于 Python 之类的语言的人来说，尝试使用切片表示法之类的东西可能很诱人，例如

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
   sort(x[1:])
}
```

但是，Java 中没有对子数组的引用，也就是说，我们不能只传递数组中下一项的地址。

这个只需要考虑较大数组的子集的问题很常见。一个典型的解决方案是创建一个私有辅助方法，该方法具有一个附加参数（或多个参数），用于描述要考虑的数组的哪一部分。例如，我们可能会编写一个私有辅助方法，也称为`sort`只考虑以 item 开头的项目`start`。

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
    // TODO
}
```

与我们的公共排序方法不同，使用递归相对简单，因为我们有额外的参数`start`，如下所示。我们将在下一节中测试此方法。

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
   int smallestIndex = findSmallest(x);
   swap(x, start, smallestIndex);
   sort(x, start + 1);
}
```

现在我们有了一个辅助方法，我们需要设置正确的原始调用。如果我们将 start 设置为 0，我们可以有效地对整个数组进行排序。

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   sort(x, 0);
}
```

这种方法在尝试对本质上不是递归的数据结构（例如数组）使用递归时非常常见。

### 调试和完成排序

运行我们的`testSort`方法，我们立即遇到了一个问题：

```sh
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4
    at Sort.swap(Sort.java:16)
```

使用 Java 调试器，我们发现问题在于不知何故`start`达到了值 4。仔细检查代码（参见上面的视频），我们发现问题在于我们忘记在递归`sort`方法中包含基本情况。解决这个问题很简单：

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
   if (start == x.length) {
       return;
   }
   int smallestIndex = findSmallest(x);
   swap(x, start, smallestIndex);
   sort(x, start + 1);
}
```

再次重新运行此测试，我们得到另一个错误：

```sh
Exception in thread "main" arrays first differed at element [0]; 
   expected<[an]> bit was:<[have]>
```

同样，通过明智地使用 IntelliJ 调试器（参见视频），我们可以识别出结果与我们的预期不符的代码行。值得注意的是，我在比您可能拥有的更高的抽象级别上调试了代码，这是我通过使用`Step Over`more than来实现的`Step Into`。正如实验 3 中所讨论的，在更高的抽象级别进行调试可以让您将整个函数调用的结果与您的期望进行比较，从而为您节省大量时间和精力。

具体来说，我们发现在对最后 3 个（共 4 个）项目进行排序时，该`findSmallest`方法在对 input 调用时作为第 0 个项目（ `"an"`）而不是第 3 个项目（ ）给出。仔细查看 的定义，这种行为并不奇怪，因为查看的是整个数组，而不仅仅是从 position 开始的项目。这种设计缺陷很常见，编写测试和使用调试器是修复它们的好方法。`"egg"``{"an", "have", "i", "egg"}``findSmallest``findSmallest``start`

为了修复我们的代码，我们进行了修改`findSmallest`，使其采用第二个参数`start`，即`findSmallest(String[] x, int start)`。通过这种方式，我们确保我们只在最后一个项目中找到最小的项目，但许多项目仍未排序。改版如下图：

```java
public static int findSmallest(String[] x, int start) {
    int smallestIndex = start;
    for (int i = start; i < x.length; i += 1) {
        int cmp = x[i].compareTo(x[smallestIndex]);
        if (cmp < 0) {
            smallestIndex = i;
        }
    }
    return smallestIndex;
}
```

鉴于我们已经对我们的构建块之一进行了重大更改，即`findSmallest`，我们应该确保我们的更改是正确的。

我们首先进行修改`testFindSmallest`，使其使用我们的新参数，如下所示：

```java
public static void testFindSmallest() {
    String[] input = {"i", "have", "an", "egg"};
    int expected = 2;

    int actual = Sort.findSmallest(input, 0);
    org.junit.Assert.assertEquals(expected, actual);        

    String[] input2 = {"there", "are", "many", "pigs"};
    int expected2 = 2;

    int actual2 = Sort.findSmallest(input2, 2);
    org.junit.Assert.assertEquals(expected2, actual2);
}
```

然后我们修改`TestSort.main`使其运行`testFindSmallest`。该测试通过，强烈表明我们的修改`findSmallest`是正确的。

我们接下来进行修改`Sort.sort`，使其使用新`start`参数`findSmallest`：

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
   if (start == x.length) {
       return;
   }
   int smallestIndex = findSmallest(x, start);
   swap(x, start, smallestIndex);
   sort(x, start + 1);
}
```

然后我们修改`TestSort`它以使其运行`TestSort.sort`，瞧，该方法有效。我们完了！您现在已经看到了从本讲座开始的“新方式”，我们将在本章的其余部分进行反思。

### 对发展过程的思考

在编写和调试程序时，您经常会发现自己在不同的上下文之间切换。一次试图在你的大脑中容纳太多是最坏的灾难，最好的情况是缓慢的进展。

拥有一组自动化测试有助于减少这种认知负担。例如，`sort`当我们意识到`findSmallest`. 我们能够切换上下文以考虑`findSmallest`并使用我们的方法确定它是正确的`testFindSmallest`，然后切换回`sort`. 这与更幼稚的方法形成鲜明对比，在这种方法中，您只是`sort`一遍又一遍地调用并试图弄清楚整个算法的行为是否表明该`findSmallest`方法是正确的。

打个比方，您可以通过上飞机、起飞、跳出，然后拉动拉索并查看降落伞是否出来来测试降落伞的拉索是否起作用。但是，您也可以将其拉到地上，看看会发生什么。所以，也没有必要用来`sort`试试`findSmallest`。

正如本章前面提到的，测试还可以让您对程序的基本部分充满信心，这样如果出现问题，您可以更好地了解从哪里开始寻找。

最后，测试使重构代码变得更容易。假设您决定重写`findSmallest`以使其更快或更易读。我们可以通过进行我们想要的更改并查看测试是否仍然有效来安全地做到这一点。

### 更好的 JUnit

首先，让我们回顾一下我们今天看到的新语法，即`org.junit.Assert.assertEquals(expected, actual)`. 此方法（名称很长）测试`expected`和`actual`是否相等，如果不相等，则以详细的错误消息终止程序。

除了 , JUnit 还有很多这样的方法`assertEquals`，例如`assertFalse`, `assertNotNull`,`fail`等等，它们可以在[JUnit 官方文档](http://junit.org/junit4/javadoc/4.12/org/junit/Assert.html)中找到。JUnit 还有许多其他复杂的特性，我们不会在 61B 中描述或教授，尽管您可以自由使用它们。

虽然 JUnit 确实改进了一些东西，但我们之前的测试代码在某些方面有点笨拙。在本节的其余部分中，我们将讨论您可以进行的两项主要改进，以使您的代码更简洁、更易于使用。从语法的角度来看，这些增强看起来非常神秘，所以只需复制我们现在所做的，我们将在后面的章节中解释其中的一部分（但不是全部）。

第一个增强是使用所谓的“测试注释”。为此，我们：

- 在每个方法`@org.junit.Test`前加上（没有分号）。
- 将每个测试方法更改为非静态的。
- 从类中删除我们的`main`方法`TestSort`。

一旦我们完成了这三件事，如果我们在 JUnit 中使用 Run->Run 命令重新运行我们的代码，所有的测试都会执行而无需手动调用。这种基于注释的方法有几个优点：

- 无需手动调用测试。
- 所有测试都会运行，而不仅仅是我们指定的那些。
- 如果一项测试失败，其他测试仍会运行。
- 提供了运行了多少测试以及通过了多少测试的计数。
- 测试失败的错误消息看起来更好看。
- 如果所有测试都通过，我们会收到一条很好的消息并出现一个绿色条，而不是简单地没有输出。

第二个增强功能将让我们为一些非常长的方法名称以及注解名称使用较短的名称。具体来说，我们将使用所谓的“导入语句”。

我们首先将 import 语句添加`import org.junit.Test;`到文件的顶部。完成此操作后，我们可以`@org.junit.Test`用简单的替换所有实例`@Test`。

然后我们添加我们的第二个 import 语句`import static org.junit.Assert.*`。这样做之后，任何我们可以省略的地方都可以`org.junit.Assert.`。例如，我们可以`org.junit.Assert.assertEquals(expected2, actual2);`简单地替换为`assertEquals(expected2, actual2);`

我们将在后面的讲座中准确解释为什么 import 语句。现在，只需使用和享受。

### 测试理念

##### 正确性工具 #1：自动评分器

让我们回到零地。自动评分器可能是您接触到的第一个正确性工具。我们的自动评分器实际上是基于 JUnit 加上一些额外的自定义库。

自动分级机有一些很大的好处。也许最重要的是，它为您验证正确性，使您免于编写所有自己的测试的繁琐且无指导的任务。它还通过提供多汁的分数作为实现正确性的激励来使评估过程游戏化。如果学生花费过多的时间来追求不会真正影响他们的成绩或学习的最终分数，这也会适得其反。

然而，自动评分器在现实世界中并不存在，依赖自动评分器会养成坏习惯。偶尔上传代码并等待自动评分器运行会阻碍一个人的工作流程。*Autograder Driven Development*是一个极端版本，学生编写所有代码，修复编译器错误，然后提交给 autograder。收到错误后，学生可以尝试进行一些更改，在打印语句中撒上，然后再次提交。并重复。最终，如果您依赖自动评分器，您将无法控制您的工作流程或代码。

##### 正确性工具 #2：JUnit 测试

正如我们所见，JUnit 测试为您打开了一个新世界。您不必依赖其他人编写的自动评分器，而是为程序的每个部分编写测试。我们将这些部分中的每一个称为一个单元。这使您可以对代码的每个单元充满信心-您可以依赖它们。这也有助于减少调试时间，因为您可以一次将注意力集中在一个代码单元上（通常是一个方法）。单元测试还迫使您澄清每个代码单元应该完成的工作。

然而，单元测试也有一些缺点。首先，编写彻底的测试需要时间。编写不完整的单元测试很容易，这会给您的代码带来错误的信心。为依赖于其他单元的单元编写测试也很困难（考虑`addFirst`你的方法`LinkedListDeque`）。

***测试驱动开发 (TDD)\***

TDD 是一个开发过程，在这个过程中，我们在编写代码本身之前编写代码测试。步骤如下：

1. 确定一个新功能。
2. 为该功能编写单元测试。
3. 运行测试。它应该失败。
4. 编写通过测试的代码。耶！
5. 可选：重构代码以使其更快、更简洁等。除了现在我们有对应该通过的测试的引用。

本课程不需要测试驱动开发，并且可能不是您的风格，但通常单元测试绝对是一个好主意。

##### 正确性工具#3：集成测试

单元测试很棒，但我们还应该确保这些单元一起正常工作（[不像这个模因](https://media.giphy.com/media/3o7rbPDRHIHwbmcOBy/giphy.gif)）。集成测试验证组件是否正确交互在一起。JUnit 实际上可以用于此。您可以将单元测试想象成最本质的东西，而集成测试的抽象级别高于此。

集成测试的挑战在于手动执行很乏味，而自动化则具有挑战性。而且在高度抽象的情况下，很容易遗漏细微或罕见的错误。

总而言之，您**绝对应该编写测试，但前提是它们可能有用！**从 TDD 中汲取灵感，在编写代码之前编写测试在某些情况下也很有帮助。

### 下一步是什么？

- [项目 1b](https://sp19.datastructur.es/materials/proj/proj1b/proj1b)