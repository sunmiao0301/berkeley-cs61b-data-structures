## HW3：Hashing 散列

## 获取骨架文件

像往常一样，运行`git pull skeleton master`以获取骨架文件。如果您使用 IntelliJ，则必须在外部“hw3”目录级别（而不是“hw3”目录中的“hw3”目录）导入项目。否则，您将收到软件包错误！

## 介绍

在这个轻量级硬件中，我们将努力更好地理解哈希表。我们试图让这个作业非常简短和重点。

## 简单的 Oomage

这部分作业的目标是为类`TestSimpleOomage`编写`equals`和 `hashCode`方法，以及对该类中的`hashCode`方法进行测试 。

要开始这项作业，请打开课程`SimpleOomage`并快速浏览一下。一个`SimpleOomage`具有三个属性：`red`、`green`和 `blue`，并且每个属性都可以是 0 到 255 之间 5 的倍数的任何值。例如，`SimpleOomage`带有`red=35`、`green=90`和`blue=215`将是有效的，因为所有这些都是 0 到 255 之间的 5 的倍数.**尝试运行 `SimpleOomage`**，您会看到四个随机的 Oomages 绘制到屏幕上。

#### equals()

**从运行`TestSimpleOomage`开始。**你会看到你没有通过 `testEquals`测试。问题是两个`SimpleOomage`对象不被认为是相等的，即使它们具有相同`red`的`green`、 和`blue`值。这是因为`SimpleOomage`使用的是默认`equals`方法(来自Object类)，该方法只是检查 the`ooA`和`ooA2`引用是否指向相同的内存位置。

这个作业的第一个任务是编写一个`equals`方法。

正如课堂上所提到的，编写一个合适的`equals`方法比乍看起来要复杂一些。根据[Java 语言规范](https://docs.oracle.com/javase/9/docs/api/java/lang/Object.html#equals-java.lang.Object-)，您的`equals`方法应具有以下属性以符合要求：

- Reflexive:`x.equals(x)`对于任何非 null的`x` 都必须为真。
- 对称：对于非 null的 `x`和`y`。`x.equals(y)`必须与`y.equals(x)`有相同结果。
- 传递性：对于任何非空`x`、`y`和`z`。如果满足`x.equals(y)`和`y.equals(z)`，则有`x.equals(z)`。
- 一致：`x.equals(y)`如果多次调用必须返回相同的结果，只要引用的对象`x`不`y`改变。
- Not-equal-to-null:`x.equals(null)`对于任何非 null 的`x`都应该为 false 。

**一个特别令人烦恼的问题是传递给 equals 方法的参数是 type `Object`，而不是 type `SimpleOomage`，因此您需要进行强制转换。但是，在不验证 Object 是否为一个 `SimpleOomage`  的情况下进行强制转换是行不通的，因为如果有人使用不是`SimpleOomage` 的参数对`.equals` 进行调用，会导致代码崩溃。因此，我们需要使用 Object 类的一个新方法，称为`getClass`. 有关正确实现的示例`equals`，请参阅 http://algs4.cs.princeton.edu/12oop/Date.java.html。**

覆盖该`equals`方法，使其正常工作。确保 `equals`通过再次运行测试来测试您的方法。修复 equals 后，您的代码现在应该可以通过测试了。

```java
//完成后的代码如下：

    @Override
    public boolean equals(Object o) {
        // TODO: Write this method.
        //return false;
        if(this != null && this == o)//this != null is always true
            return true;
        if(o == null)
            return false;
        if(o.getClass() != this.getClass())
            return false;
        SimpleOomage casted = (SimpleOomage)o;
        return (this.red == casted.red && this.green == casted.green && this.blue == casted.blue);
    }

//然后运行TestSimpleOomega结果如下，也就是通过测试。

Time: 0.007
Ran 3 tests. All passed.

Process finished with exit code 0
```



#### A simple hashCode

**在 Java 中，如果你覆盖`equals`，那么也应覆盖`hashCode`。**取消注释`TestSimpleOomage`中的`testHashCodeAndEqualsConsistency` 方法。运行它，你会发现它失败了。原因是我们已经覆盖`equals`但没有覆盖`hashCode`。

要更深入地了解为什么会发生此故障，请考虑下面显示的代码。

阅读这段代码时要思考的两个问题：

- 每个打印语句*应该*输出什么？

- 每个打印语句*会*输出什么？

  ```java
   public void testHashCodeAndEqualsConsistency() {
       SimpleOomage ooA = new SimpleOomage(5, 10, 20);
       SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
  
       System.out.println(ooA.equals(ooA2));
  
       HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
       hashSet.add(ooA);
       System.out.println(hashSet.contains(ooA2));
   }
  
  //第一个打印 应该 输出 true;
  //第二个打印 应该 输出 true;
  
  //但是在未重写的情况下
  //第一个打印会输出false
  //第二个打印会输出false
  ```

答案：

- 根据我们在赋值的前一部分中创建的`equals`定义，第一个 print 语句*应该*并且*将*输出 true 。
- 最后的 print 语句*应该*输出 true。HashSet 确实包含 `SimpleOomage`r/g/b 值为 5/10/20 的 a！

- 最后的打印语句*将* 打印错误。当 HashSet 检查是否`ooA2`存在时，它会首先计算`ooA2.hashCode`，对于我们的代码，这将是默认值`hashCode()`，即内存地址。由于 `ooA`和`ooA2`具有不同的地址，它们的 hashCode 将不同，**因此 HashSet 将无法`Oomage`在该桶中找到 r/g/b 值为 5/10/20 的值。(所以contain也是false结果）**

Java 规范也提到了`equals`的这种危险：

“请注意，当`equals`方法被重写时，通常需要重写方法`hashCode` ，以维护该方法`hashCode`的作用：即相等的对象必须具有相等的哈希码。”

**取消注释`SimpleOomage`中给定的`hashCode`方法**，这将返回一个等于 `red + green + blue` 的 hashCode 。请注意，这`hashCode`现在与 equals 一致，因此您现在应该通过所有`TestSimpleOomage` 测试。

#### testHashCodePerfect

虽然给定的`hashCode`方法是可以的，但从某种意义上说，它并不是真正的通过，因为并不是当两个SimpleOomage完全相同的时候才equals，并且它只使用了哈希码可能空间的一小部分，这意味着它会有许多不必要的冲突。

我们的下一个目标`SimpleOomage`是编写一个*完美* `hashCode`的函数。**完美，我们的意思是两个`SimpleOomage`s 可能只有当它们具有完全相同的红色、绿色和蓝色值时才具有相同的 hashCode。**

在我们编写它之前，填充`TestSimpleOomage`的`testHashCodePerfect`函数，以此来试试看`hashCode`是否完美。

提示：尝试红色、绿色和蓝色值的所有可能组合，并确保您不会多次看到相同的值。

随意使用您想要的任何数据结构。运行这个测试，它应该会失败，因为提供的`hashCode`方法并不完美。

#### 完美的哈希码

要做到`hashCode`完美，**请在您的 hashCode() 方法**中将变量设置`USE_PERFECT_HASH`为 true， 并将`SimpleOomage`中`return 0`的替换为新的完美哈希码计算方法。

```java
//我写的perfect hashCode方法如下：
    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            // TODO: Write a perfect hash function for Simple Oomages.
            return 255 * red + 255 * 255 * green + 255 * 255 * 255 * blue;
            // return 0;
        }
    }
```

最后，运行`TestSimpleOomage`并验证您的完美`hashCode`方法是否通过了测试。您的`TestSimpleOomage` 测试可能需要几秒钟才能完成执行。

#### 在 JUnit 中评估完美的 hashCode

在方法`OomageTestUtility`中填写`haveNiceHashCodeSpread(List<Oomage> oomages, int M)`方法，所以如果给定的 Oomages 使用它们的 hashCodes 扔到 M 个存储桶中会有很好的spread，则它返回 true。我们将一个不错的spread定义为：

- 没有任何一个桶的 oomages 少于 N / 50。
- 没有任何一个桶的 oomages 超过 N / 2.5, 其中 N 是 oomages 的总数量。

换句话说，每个桶的 oomages 数量必须在 (N / 50, N / 2.5) 范围内。

为了将 Oomage 的 hashCode 转换为存储桶编号，您应该计算`bucketNum = (o.hashCode() & 0x7FFFFFFF) % M`，其中`o`是 Oomage。你不应该使用`Math.abs`or `Math.floorMod`。请参阅常见问题解答了解原因。

```java
//最终代码如下：
public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        // map = new HashMap<>();
        int[] arr = new int[M];
        for(Oomage o : oomages){
            arr[(o.hashCode() & 0x7FFFFFFF) % M]++;
        }
        for(int i = 0; i < M; i++){
            if(arr[i] > oomages.size() / 2.5 || arr[i] < oomages.size() / 50)
                return false;
        }
        return true;
    }
```

编写完此方法后，尝试运行`TestJankyOomage`它应该会失败。

```bash
# 确实是失败了
```

尝试运行`TestNiceSpreadOomage`，它应该通过。

```bash
# 确实是成功了
```

现在取消注释`TestSimpleOomage`中的`testRandomOomagesHashCodeSpread`，并确保你的`SimpleOomage`类中`USE_PERFECT_HASH`是true的。**即使它是完美的，您的哈希码也很可能无法通过此测试。为了理解为什么，让我们转向可视化。**如果您的哈希码偶然通过，那很好，您仍应使用可视化工具在下一节中查看您的哈希码在视觉上的表现。

```java
//这里我测试了很多次都不能通过，说明我写的hashCode方法还不够好。

//于是我重新思考，并写了一份新的hashCode方法：
    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            // TODO: Write a perfect hash function for Simple Oomages.
            return red / 5 + green / 5 * 52 + blue / 5 * 52 * 52;
        }
    }

//于是通过了

Tests passed
```



#### 直观地评估完美的 hashCode

为了更好地理解哈希表的工作原理，我们将使用哈希表可视化工具。具体来说，我们提供了一种带有签名的方法，该方法 `visualize(List<Oomage> oomages, int M, double scale)`根据其哈希码显示给定 oomages 在 M 个桶中的分布（并且`scale`是控制绘图某些方面大小的特殊参数）。

要试用它，请尝试运行`HashTableVisualizer`提供的。您的可视化可能看起来像这样（如果您的哈希码未通过 `testRandomOomagesHashCodeSpread`上一节的测试）：

![可视化器](https://sp19.datastructur.es/materials/hw/hw3/images/visualizer2.png)

或者，如果您的哈希码已经足够好，一切都很好地展开，它看起来像这样：

![可视化器](https://sp19.datastructur.es/materials/hw/hw3/images/visualizer.png)

如果你得到一个与第一个相似的结果，所有东西都在两个桶中，原因如下：即使你的 hashCode 是完美的，它总是返回 5 的倍数。尝试将`HashTableVisualizer`文件中的 M 更改为 9，你应该会看到一个很好的分布。了解为什么会发生这种情况是一个好主意。

如果你想让你的 hashCodes 对任意数量的桶都有效，***你应该确保它不总是任何数字的倍数。一种方法是在计算哈希码之前将红色、绿色和蓝色值除以 5。***

#### 使用展示台进行实验（可选）

一旦你有一个很好的传播，尝试使用`scale = 0.5, N = 2000, M = 100`。

```apl
结果如下：
```

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0224hashresult.png)

尝试弄乱 N 和 M 并查看可视化器在您的 `SimpleOomage`班级中的表现。**如果没有足够的空间容纳屏幕上的所有内容，请尝试将缩放因子重置为小于 1 的数字。**比较完美与不完美与默认哈希码的项目分布。您所看到的是否符合您的预期？

## 复杂的 Ooage

这个`ComplexOomage`类是一个更复杂的野兽。每个变量`ComplexOomage`都有一个完整的整数列表，来代替我们在SimpleOomage里面的`red`、`green`和`blue`值的三个实例变量，范围在 0 到 255 之间（不一定是 5 的倍数）。该列表可以是任意长度。

这一次，你根本不会改变`ComplexOomage`班级。相反，您的工作将是编写测试以找出`hashCode`函数中的缺陷。

#### 评估 ComplexOomage hashCode

提供`hashCode`的内容是有效的，但它在散列表中分配项目方面可能做得不好。

首先使用可视化器可视化随机`ComplexOomage`对象的分布。使用`randomComplexOomage`方法生成随机 `ComplexOomage`s。您应该会发现，这个视觉测试显示`randomComplexOomages`。

也可以尝试取消注释`testRandomOomagesHashCodeSpread`。`TestComplexOomage`您会看到它通过`testRandomOomagesHashCodeSpread`得很好，加强了您刚刚在可视化器中看到的内容。对于完全随机 `ComplexOomages`的，一切都很好。

#### testWithDeadlyParams 和二进制表示

在这种情况下，我们需要查看`hashCode`以找出问题所在。填写测试`testWithDeadlyParams`，使得提供的`hashCode`功能由于`ComplexOomage`对象分布不佳而失败。

鉴于我们目前在 61B 中学到的知识，这是一个非常棘手的问题！考虑 Java 如何用二进制表示整数（参见[第 23 课](https://docs.google.com/presentation/d/1hRUkaONWvWP7IZbINLP-G6uOyyulDqury5kop7638co/edit) 的回顾）。有关提示，请参阅 Hint.java。

您的测试不应因 IllegalArgumentException 而失败。

一旦你编写了这个测试并`ComplexOomage`失败了，你就完成了 HW3！

#### 修复 hashCode（可选）

考虑如何更改`ComplexOomage`的`hashCode`方法以便 `testWithDeadlyParams`通过。是否还有其他可能影响您的`hashCode`方法的致命参数？

## 提交

为了在您想要关注的问题上为您提供一些小的灵活性，我们设置了 AG，只要您通过除一项测试之外的所有测试，我们就会给予您满分。因此，如果您在硬件的任何特定部分遇到问题，请随意跳过它，不会受到任何惩罚。

## 常问问题

#### 我没有通过 HashTableVisualizer 测试！

您必须使用 将 hashCode 转换为存储桶编号`(hashCode & 0x7FFFFFFF) % M`。你不应该使用`Math.abs(hashCode) % M`.

#### 为什么我不能只使用 Math.abs 或 Math.floorMod？

**`Math.abs`不好，因为如果您执行 Math.abs(-2147483648) 会发生什么。试试看。**`Math.floorMod`很好，但我们想给你一个机会看看可选算法教科书是如何做的，并给你一个按位运算的例子（0x7FFFFFFF），我们将在课程的最后部分讨论这些内容.

#### k & 0x7FFFFFFF 是做什么的？

**它返回一个等于 k 的整数，但最高位设置为零。我们将在课程结束时更多地讨论这个问题。**

#### 我收到错误，例如文件在自动评分器中不包含类 hw3.hash.HashTableVisualizer。

您的代码必须是 hw3.hash 包的一部分，并在文件顶部带有适当的声明。