## Project 1B 应用和测试数据结构 1.0 版

## 介绍

在您之前的大多数编程任务中，您都依赖于一些外部事实来源来告诉您您的代码是正确的。

在这个项目中，您将使用项目 1A 中的一个双端队列来解决现实世界的问题。在此过程中，您还将编写应用程序测试，以说服自己一切正常。

与项目 1A 不同，该项目将更加高度地搭建以减少预期中期的工作量。

警告：此项目的自动评分器输出将是简洁且无用的。这是因为我们希望您使用测试来建立对代码的信心。您将无法依靠自动评分器来确保正确性。

## 获取所需文件

#### 骨架文件

和以前一样，使用命令拉出骨架`git pull skeleton master`。

在骨架中，我们提供了以下文件：

- `PalindromeFinder.java`：帮助识别英语中的广义回文的类。
- `CharacterComparator.java`：用于比较字符的界面。
- `TestPalindrome.java`: 一个包含 JUnit 测试的类`Palindrome`。
- `TestOffByOne.java`: 一个包含 JUnit 测试的类`OffByOne`。

此外，您将创建以下文件：

- `Palindrome.java`: 回文运算类。
- `OffByOne.java`: off-by-1 比较器的类。
- `OffByN.java`：N 比较器的类。

您还需要 cd 进入您的`library-sp19`文件夹。一旦你在那里，使用`git pull origin master`. 如果一切正常，您应该`words.txt`会在`library-sp19/data`文件夹中看到一个名为的文件。

总之，这些应该是要使用的 shell 命令：

```
$ git pull skeleton master
$ cd library-sp19
$ git pull origin master
```

如果这不起作用，`git submodule update --recursive --remote`请从您的个人`sp19-s???`文件夹中尝试。

注意：我们已经设置了您的 git 存储库，以便它`words.txt`默认拒绝。对于那些知道这意味着的人：请不要使用该`-f`命令将这个大文件强制到您的存储库中。它会减慢自动分级机的速度。

#### 附加可选手册下载

对于这个任务，你还需要一个正确的双端队列实现。欢迎您使用您的`LinkedListDeque`或您`ArrayDeque`的 Part1A。如果您不确定您对项目 1a 的解决方案是否正确，或者您只是没有完成，您可以`LinkedListDeque`通过左键单击 [此链接](http://joshh.ug/LinkedListDeque.html)下载正确的实现，然后手动复制并粘贴显示的代码。

#### 通过 Intellij 设置

您可以`proj1b`像往常一样将文件夹导入 Intellij。但是，我们已经看到学生的 Intellij 无法将代码识别为 Java 的一些问题，因为其中大部分都被注释掉了。这个问题有几个修复：

1. 将源目录 ( ) 标记`proj1b`为“Sources Root”。您可以右键单击`proj1b`Intellij 中的目录 -> 标记为 -> 源根目录。
2. 取消注释`TestPalindrome.java`和`TestOffByOne.java`，然后再次尝试重新导入。

## 任务 1：双端队列接口

第一项任务会有点乏味，但不会花很长时间。

在一个名为的新文件中创建一个接口，该文件`Deque.java`包含出现在 和 中的*所有* `ArrayDeque`方法`LinkedListDeque`。有关简明列表，请参阅[项目 1a 规范](https://sp19.datastructur.es/materials/proj/proj1a/proj1a)。在 IntelliJ 中，使用“New->Java Class”。IntelliJ 将假定您需要一个类，因此请确保将`class`关键字替换为`interface`.

创建此接口并添加所有方法。对于该`isEmpty()`方法，给它一个`default`实现，`true`如果`size()`is 则返回`0`。请注意，由于您`LinkedListDeque`和`ArrayDeque`可能都使用这样的实现，现在我们有了这个默认实现，我们可以删除上述两个具体类中的重复实现（如果您愿意，可以继续删除它们）。

修改您的`LinkedListDeque`和/或`ArrayDeque`，以便它们`Deque` 通过添加`implements Deque<Item>`到声明类存在的行来实现接口。如果您使用的不是`Item`泛型类型参数，请改用它。将`@Override`标签添加到覆盖方法的每个`Deque`方法。

注意：如果你使用提供的解决方案`LinkedListDeque`，它依赖于一些继承黑魔法，你的类定义应该看起来像
`public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item>`.

## 任务 2：wordToDeque

创建一个名为 的新文件`Palindrome.java`，并添加一个具有如下签名的方法：

- `public Deque<Character> wordToDeque(String word)`

**不要为此方法编写任何代码。**现在，只需让它返回`null` 以便`Palindrome.java`可以编译。

给定 a `String`，`wordToDeque`应该返回 a `Deque`，其中字符出现的顺序与字符串中的顺序相同。例如，如果单词是“persiflage”，那么返回的`Deque`应该在前面有“p”，然后是“e”，以此类推。 **暂时不要实现 wordToDeque！**

取消注释中的代码`TestPalindrome`并运行文件中包含的测试（例如，通过右键单击它并选择`Run TestPalindrome`）。您应该未通过提供的测试。您的目标是现在通过正确实施 `wordToDeque`. 一旦你通过了测试，就可以继续这个作业的下一部分。确保不要删除奇怪的行`static Palindrome palindrome = new Palindrome();`。这对作业的这项任务没有用处，但以后有必要。

```java
//我对文件实施如下：
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        //return null;
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
}
```

运行TestPalindrome测试结果如下：

```bash
Test passed: 1

Process finished with exit code 0
```



*提示：搜索网络以查看如何获取字符串中的第 i 个字符。*

*提示：将字符插入 `Deque<Character>`就像将整数插入 a 一样`LinkedListDeque<Integer>`。*

*注意：细心的读者`testWordToDeque`可能想知道为什么我们不只是创建一个正确的`Deque`然后调用`assertEquals`. 原因是我们的 `Deque`类没有提供`equals`方法，因此它不会按您期望的方式工作。我们很快就会在课堂上讨论这个问题。*

**提示：如果您自己的测试失败并且无法弄清楚原因，请记住您有一个调试器。用它！不要只盯着你的代码寻找错误。这太慢了，太乏味了。**

## 任务 3：isPalindrome

#### 任务 3A：isPalindrome 测试

现在您已经通过了测试并开始享受成功栏的漂亮绿色光芒，让我们再次打破常规。

修改您的`Palindrome.java`，使其现在具有带有以下签名的第二种方法。

- `public boolean isPalindrome(String word)`

现在，让它返回一个虚拟值。虚拟值只是您选择的一些任意事物，即真或假。在我们编写方法本身之前，我们将编写一个测试。让我们从讨论`isPalindrome`应该做什么开始。

如果给定的单词是回文，则该`isPalindrome`方法应返回`true`，否则返回`false`。回文被定义为一个单词，无论它是向前读还是向后读都是一样的。例如“a”、“racecar”和“noon”都是回文。“horse”、“rancor”和“aaaaab”不是回文。*任何长度为 1 或 0 的单词都是回文。*

'A' 和 'a' 不应被视为相等；您无需为大写字母进行任何特殊操作即可正常工作。也就是说，如果你忘记了大写字母的存在，你的代码就可以正常工作。

```java
//测试结果是：
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        //return null;
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        int l = 0;
        int r = word.length() - 1;
        while(l <= r){
            if(word.charAt(l) != word.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args){
        Palindrome p = new Palindrome();
        System.out.print(p.isPalindrome("cattac") + " ");
        System.out.print(p.isPalindrome("cat") + " ");
        System.out.print(p.isPalindrome("c") + " ");
        System.out.print(p.isPalindrome("") + " ");
    }
}
```



至少添加一个测试来`TestPalindrome`测试该`isPalindrome`方法。您可能会发现`assertTrue`和`assertFalse`方法很有用。也欢迎您使用 [JUnit 文档](http://junit.sourceforge.net/javadoc/org/junit/Assert.html)中的任何其他方法。理想情况下，您应该编写多个测试，而不仅仅是一个，这取决于您。在一个测试中有多个断言是可以的，但不要太疯狂。**确保使用 `@Test` 注释您的测试，否则 JUnit 将不会运行您的测试。**

当您`TestPalindrome`再次运行时，您的代码应该无法通过（希望是多个）测试。

*提示：例如，`assertFalse(palindrome.isPalindrome("cat"));`测试以确保“猫”不被视为回文。*

*提示：如果您正在寻找更有趣的东西来测试，请仔细阅读本节以了解任何有趣的极端情况，并确保您的测试检查这些极端情况。*

#### 任务 3B：isPalindrome

现在您的测试失败了，请实施该`isPalindrome`方法。用你的 `wordToDeque`方法给自己一个更轻松的时间。虽然您在技术上根本不能使用 a `Deque`，但我们强烈建议您这样做。这是一个很好的练习，可以帮助您了解您选择的数据结构（在本例中为`Deque`）将如何对您编写代码的方式产生深远影响。

一旦你通过了自己的测试，你就可以继续前进了。请记住，我们的自动评分器将非常安静，因此您需要确保您的测试是彻底的，以便您对自己的代码感觉良好。至少，您应该至少有一个测试来检查某个单词是否是回文，以及一个检查某个单词是否不是回文，以及两个有趣的极端情况。

*提示：考虑递归。有一个使用递归的非常漂亮的解决方案。您需要创建一个私有帮助器方法才能使其工作。*

*提示：不要使用Deque的 `get` 的方法。那只会使事情变得不必要地复杂。*

*只是为了好玩：取消注释提供的`PalindromeFinder.java` 类中的代码，您将获得所有长度为 4 或更多英文回文的列表（假设您还下载了提供的单词文件）。*

```java
//我的执行结果如下：
anna
civic
deed
deified
kayak
level
madam
minim
noon
peep
poop
radar
redder
refer
repaper
reviver
rotator
rotor
sagas
sees
sexes
shahs
tenet
toot

Process finished with exit code 0
```



**提示：如果您自己的测试失败并且无法弄清楚原因，请记住您有一个调试器。用它！不要只盯着你的代码寻找错误。这太慢了，太乏味了。**

## 任务 4：广义回文和 OffByOne

对于此任务，您将做六件事。我们建议的顺序如下：

- 创建一个名为`OffByOne`的类并且`implements`了`CharacterComparator`。
- `TestOffByOne`为类中的`equalChars`方法添加测试`OffByOne`。
- 完成该`equalChars`方法并验证它是否有效。
- 添加一个[重载](https://www.geeksforgeeks.org/overloading-in-java/) `isPalindrome`的新方法。
- 添加测试以`TestPalindrome`测试您的新方法`isPalindrome`。可以`new OffByOne()`用于这些测试。
- 完成新方法`isPalindrome`并验证它是否有效。

但是，欢迎您按照您选择的任何顺序进行操作。对于这个任务，我们不会像上面的任务那样仔细列举你应该做的所有事情，我们将简单地给出你的目标的一般描述。

`Palindrome`在此任务中，您的最终目标是使用以下签名向您的类添加第三个公共方法：

- `public boolean isPalindrome(String word, CharacterComparator cc)`

根据作为参数传入的字符比较测试，如果该单词是回文，该方法将返回`true`。字符比较器定义如下：`CharacterComparator``cc`

```
/** This interface defines a method for determining equality of characters. */
public interface CharacterComparator {

    /** Returns true if characters are equal by the rules of the implementing class. */
    public boolean equalChars(char x, char y);
}
```

对于此任务，您还将创建一个名为 `OffByOne.java` 的类，该类应实现返回完全不同的字符。例如下面的调用应该返回。请注意，Java 中的字符是用单引号来描述的，而字符串则使用双引号。`CharacterComparator``equalChars``true``obo``true`

```
OffByOne obo = new OffByOne();
obo.equalChars('a', 'b');  // true
obo.equalChars('r', 'q');  // true
```

但是，下面的三个调用应该返回`false`：

```
obo.equalChars('a', 'e');  // false
obo.equalChars('z', 'a');  // false
obo.equalChars('a', 'a');  // false
```

*注意：Java 中的字符包括非字母字符。例如 '%' 和 '&' 减一。这可能看起来很奇怪（特别是因为它们在键盘上甚至不相邻），但 Java 中的 char 值实际上只是整数。比如'%'其实只是37的另一种写法，'&'是38的另一种写法。也就是说，下面的代码是有效的，第一个打印语句将打印38。后两个打印语句完全等价, 他们都简单地打印 1。*

```
int x = '&';
System.out.println(x);         // prints 38
System.out.println(38 - 37);   // prints 1
System.out.println('&' - '%'); // prints 1
```

*因此，下面的方法调用应该返回 true：*

```
obo.equalChars('&', '%');
```

*[同样，'a' 和 'B' 不会相差一个，因为 'a' - 'B' 是 31。如果您对许多熟悉字符的具体值感到好奇，请参阅这篇维基百科文章](https://en.wikipedia.org/wiki/ASCII)底部的表格.*

**为了允许奇数长度的回文，我们不检查中间字符是否与自身相等。**所以“flake”是一个off-by-1回文，即使“a”不是一个独立的字符。

与我们之前的`isPalindrome`方法一样，任何零或 1 个字符的单词都被认为是回文。

*提示：确保`@Override`在实施时包括`equalChars`. 虽然它对您的程序的功能没有影响，但由于[讲座](https://docs.google.com/presentation/d/1b-Ue_mWWMI2CeHfaU_GO6PWhIpAr_SyZge9JQJQFpXc/edit#slide=id.g1c5d63f7bc_0_0)中详述的原因，这是一个好习惯。*

*提示：要计算两个字符之间的差异，只需在 java 中计算它们的差异。例如`int diff = 'd' - 'a';`将返回`diff`为`-3`.*

*提示：即使 Palindrome 和 OffByOne 的一个好的解决方案不应该明确担心非字母字符或大写字母，您的测试可能会在一个糟糕的解决方案上运行，因此在这种情况下应该尝试导致仅适用于非字母字符的错误- 字母字符。*

*只是为了好玩：尝试通过修改 `PalindromeFinder.java`. 例如，“flake”是一个off-by-1回文，因为“f”和“e”是一个字母，“k”和“l”是一个字母。*

**提示：如果您自己的测试失败并且无法弄清楚原因，请记住您有一个调试器。用它！不要只盯着你的代码寻找错误。这太慢了，太乏味了。**

## 任务 5：OffByN

在最后一部分中，您将实现一个`OffByN`应该实现`CharacterComparator`接口的类，以及一个采用整数的单参数构造函数。换句话说，可调用的方法和构造函数将是：

- `OffByN(int N)`
- `equalChars(char x, char y)`

构造`OffByN`函数应创建对象，其`equalChars`方法返回`true`的字符为`N`. 例如，下面对相等字符的调用应该返回`true`，因为“a”和“f”相差 5 个字母，但第二个调用将返回`false`，因为“f”和“h”相差 4 个字母。

```
OffByN offBy5 = new OffByN(5);
offBy5.equalChars('a', 'f');  // true
offBy5.equalChars('f', 'a');  // true
offBy5.equalChars('f', 'h');  // false
```

对于此任务，如果您选择编写测试，则需要制作自己的测试文件。确保包含适当的`import`语句，您可以从我们提供的两个测试文件中复制和粘贴这些语句。与其他测试文件不同，欢迎`new` 您在测试中使用。由于技术限制，`TestOffByN.java`如果您创建一个文件，我们将不会测试您的文件，但您仍然可能会发现创建此类测试很有用。

*只是为了好玩：尝试修改`PalindromeFinder.java`，以便它输出一个 offByN 回文列表供`N`您选择。*

*Just-for-more-fun：`N`英语中最多的回文是什么？最长的 offByN 回文数是`N`多少？*

## 经常问的问题

#### 我无法编译提供的 LinkedListDeque 解决方案。

确保您的类定义是
`public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item>`.

#### 我的 LinkedListDeque 或 ArrayDeque 实现无法编译。

确保您的类定义以 结尾`implements Deque<Item>`，或者如果您的代码不使用`Item`，请将其替换为您使用的任何通用参数类型变量。

#### 我的代码通过了我所有的测试，但没有通过一些自动评分器测试。

试着想想你可能没有涵盖的极端情况。仔细阅读规范的相应部分以识别它们。然后编写您自己的测试来测试这些极端情况，并确保您的代码通过它们。

#### Autograder 不喜欢我的测试。

确保您没有`words.txt`在测试中的任何地方使用。自动评分器无权访问此文件。

#### 只有我的测试失败了。我错过了哪些测试？

可能是极端情况。

#### 我没有通过 TestPalindrome 或 TestOffByOne 测试，但我觉得我的测试很好。

确保你`TestPalindrome`没有说`new Palindrome`任何地方。同样，确保你`TestOffByOne`没有说`new OffByOne`任何地方。

#### 奇怪的静态回文 palindrome = new Palindrome(); 是怎么回事？东西？

为了为您的 JUnit 测试创建 JUnit 测试，我们不得不求助于一些巧妙的技巧，而最简单的方法涉及到这些奇怪的东西。对不起。幸运的是，`TestOffByN`您不必担心。

## 可交付成果

- `Deque.java`（由您创建）
- `Palindrome.java`（由您创建）
- `OffByOne.java`（由您创建）
- `OffByN.java`（由您创建）
- `TestPalindrome.java`
- `TestOffByOne.java`