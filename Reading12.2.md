## Reading 12.2

## 解决单词插入问题

我们`DataIndexedIntegerSet`只允许整数，但现在我们要插入一个`String` `"cat"`。我们将调用可以插入字符串的数据结构`DataIntexedEnglishWordSet`这是一个疯狂的想法：让我们给每个字符串一个代表的数字。也许“猫”可以`1`，“狗”可以`2`，“乌龟”可以`3`。

（这样的工作方式是——如果有人想在我们的数据结构中添加一个“猫”，我们会“找出”“猫”的数字是 1，然后设置`present[1]`为`true`。如果有人想问如果“cat”在我们的数据结构中，我们将“找出”“cat”为 1，并检查是否`present[1]`为真。）

**但是如果有人试图插入“potatocactus”这个词，我们将不知道该怎么做。我们需要制定一个通用策略，以便给定一个字符串，我们可以计算出它的数字表示。**

### 策略1：使用第一个字母。

一个简单的想法是只使用任何给定字符串的第一个字符将其转换为数字表示。所以 "cat" -> "c" -> 3. "Dog" -> "d" -> 4. 而且，"drum" -> "d" -> 4。

如果有人想在我们的 中插入“Dog”和“Drum”`DataIntexedEnglishWordSet`怎么办？我们不知道该怎么做。

请注意，当两个不同的输入（“dog”和“drum”）映射到同一个整数时，我们称之为**碰撞**。我们还不知道如何处理碰撞，所以让我们想办法避免它们。（这是我们对大多数问题的处理方式哈哈……）

### 策略2：避免碰撞

为了激发这一部分，让我们了解我们的数字系统是如何工作的。

一个四位数字，比如 5149，可以写成

5 × 10^3 + 1 × 10^2 + 4 × 10^1 + 9 × 10^0

实际上，**任何**4 位数字都可以用这种形式**唯一地写入。**这意味着给出 4 位数字，A B C D；我们可以写a × 10^3 + b × 10^2 + c × 10^1 + d × 10^0，这给了我们一个唯一的 4 位数字：a b c d

**请注意，选择10来乘法在这里很重要。如果我们选择了一个错误的数字，比如 2，那也是不正确的。让我们我们看看如果我们选择2作为乘数，会发生什么？**

a, b, c, d = 1, 1, 1, 1

1 × 2^3 + 1 × 2^2 + 1 × 2^1 + 1 × 2^0 = 15

a, b, c, d = 0, 3, 1, 1

0 × 2^3 + 3 × 2^2 + 1 × 2^1 + 1 × 2^0 = 15

输入 (1, 1, 1, 1) 和 (0, 3, 1, 1) 发生冲突！

那么为什么是10重要的？这是因为我们的十进制系统中有 10 个唯一数字：0、1、2、3、4、5、6、7、8、9

同样，有26英文小写字母中的唯一字符。为什么不给每个人一个数字：a=1, b=2, ... , *z* = 26. **现在，我们可以在base 26**中编写任何唯一的小写字符串。（请注意，**基数 26**仅表示我们将使用**26**作为乘数，就像我们在上面的示例中使用**10**和**2一样。）**

- “猫” = "cat" = "c" 26^2 *+ 'a'* 26^1 + 't' 26^0 = 3 × 26^2 + 1 × 26^1 + 2 × 26^0 = 2074

**快速检查**

- 你如何代表“狗”？
- “dog” = 4 × 26^2 + 7 × 26^1 + 15 × 26^0 = ...

**这种表示为每个包含小写字母的英文单词提供了一个唯一的整数，就像使用 base 10 为每个数字提供一个唯一的表示一样。我们保证不会发生碰撞。**

### 我们的数据结构`DataIndexedEnglishWordSet`

```java
public class DataIndexedEnglishWordSet {
    private boolean[] present;

    public DataIndexedEnglishWordSet() {
        present = new boolean[2000000000];
    }

    public void add(String s) {
        present[englishToInt(s)] = true;
    }

    public boolean contains(int i) {
        resent present[englishToInt(s)];
    }
}
```

使用辅助方法

```java
public static int letterNum(String s, int i) {
    /** Converts ith character of String to a letter number.
    * e.g. 'a' -> 1, 'b' -> 2, 'z' -> 26 */
    int ithChar = s.charAt(i)
    if ((ithChar < 'a') || (ithChar > 'z')) {
        throw new IllegalArgumentException();
    }

    return ithChar - 'a' + 1;
}

public static int englishToInt(String s) {
    int intRep = 0;
    for (int i = 0; i < s.length(); i += 1) {
        intRep = intRep * 26;
        intRep += letterNum(s, i);
    }

    return intRep;
}
```

### 我们在哪？

回想一下，我们想要：

(a) 优于 Θ(log N) 我们现在已经为整数和单个英文单词做了这个。

(b) 允许加入不可比的元素（而不像二叉树只能加入可以比较的元素）。We haven't touched this yet, although we are getting there. So far, we've only learnt how to add integers and english words, both of which *are* comparable, **but**, have we ever **used** the fact that they are comparable? I.e., have we ever tried to compare them (like we did in BSTs)? No. So we're getting there, but we haven't actually inserted anything non-comparable yet.

(c) 我们有插入整数和英文单词的数据结构。接下来我们将尝试插入任意`String`对象，包括空格等等。甚至可能插入其他语言和表情符号！

(d) 进一步回想一下，我们的方法仍然非常浪费内存。我们还没有解决这个问题！