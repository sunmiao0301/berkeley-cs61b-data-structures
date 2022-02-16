## Reading 6.2

## 抛出异常 - Throwing Exceptions

我们`ArraySet`上一节的实现有一个小错误。当我们添加`null`到我们的 ArraySet 时，我们得到一个 NullPointerException。

问题在于`contains`我们检查的方法`items[i].equals(x)`。如果 at 的`items[i]`值为 null，那么我们正在调用`null.equals(x)`-> NullPointerException。

异常会导致正常的控制流程停止。事实上，我们可以选择抛出我们自己的异常。在 python 中，您可能已经使用`raise`关键字看到了这一点。在 Java 中，异常是对象，我们使用以下格式抛出异常：

```
throw new ExceptionObject(parameter1, ...)
```

**当用户尝试将 null 添加到我们的`ArraySet`. 我们将抛出一个`IllegalArgumentException`(该对象接受一个参数（一条`String`消息），如下。**

```apl
throw new IllegalArgumentException("can't add null");
```

我们更新的`add`方法：

```java
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
```

**无论哪种方式，我们都会得到一个Exceptation - 为什么这更好？**

1. **我们可以控制我们的代码：我们有意识地决定在什么时候停止我们的程序流**
2. **对于使用我们代码的人来说，更有用的异常类型和有用的错误消息**

**但是，如果程序根本不崩溃会更好。在这种情况下，我们可以做不同的事情。以下是一些：**

**方法 1：如果将其传递给数组，则不要添加`null`到数组中`add`** 

**方法 2：更改`contains`方法以解决 if 的情况`items[i] == null`。**

**无论您做出什么决定，重要的是让用户知道会发生什么。这就是为什么文档（例如关于您的方法的评论）非常重要的原因。**

### 捕捉异常

正如我们所见，有时程序运行时会出现问题。Java 通过抛出异常来处理这些“异常事件”。在本节中，我们将了解在抛出此类异常时我们还能做些什么。

考虑以下错误情况：

- 您尝试使用 383,124 GB 的内存。
- 您尝试将 Object 强制转换为 Dog，但动态类型不是 Dog。
- 您尝试使用等于 null 的引用变量调用方法。
- 您尝试访问数组的索引 -1。

到目前为止，我们已经看到 Java 崩溃并打印带有隐式异常的错误消息：

```java
Object o = "mulchor";
Dog x = (Dog) o;
```

导致：

```
Exception in thread "main" java.lang.ClassCastException: 
java.lang.String cannot be cast to Planet
```

在上面，我们看到了如何通过使用显式异常来提供更多信息错误：

```java
public static void main(String[] args) {
    System.out.println("ayyy lmao");
    throw new RuntimeException("For no reason.");
}
```

这会产生错误：

```
$ java Alien
ayyy lmao
Exception in thread "main" java.lang.RuntimeException: For no reason.
at Alien.main(Alien.java:4)
```

**在此示例中，请注意一个熟悉的结构：`new RuntimeException("For no reason.")`. 这看起来很像实例化一个类——因为这正是它的本质。RuntimeException 只是一个 Java 对象，就像任何其他对象一样。**

![异常类](https://joshhug.gitbooks.io/hug61b/content/assets/exceptions.png)

到目前为止，抛出的异常会导致代码崩溃。但是我们可以“捕获”异常，防止程序崩溃。

**关键字*try*和*catch*打破了程序的正常流程，保护它免受Exceptions的影响。**

考虑以下示例：

```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try {
    d.receivePat();
} catch (Exception e) {
    System.out.println("Tried to pat: " + e);
}
System.out.println(d);
```

此代码的输出可能是：

```
$ java ExceptionDemo
Tried to pat: java.lang.RuntimeException: grrr... snarl snarl
Lucy is a displeased Retriever weighing 80.0 standard lb units.
```

在这里，我们看到当狗生气时我们尝试拍拍狗时，它会抛出 RuntimeException，并带有有用的错误消息“grrr...snarl snarl”。**但是，它会继续，并在最后一行打印出狗的状态！这是因为我们捕捉到了异常。**

这似乎还不是特别有用。但我们也可以使用 catch 语句来采取纠正措施。

```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try {
    d.receivePat();
} catch (Exception e) {
    System.out.println(
    "Tried to pat: " + e);
    d.eatTreat("banana");
} 
d.receivePat();
System.out.println(d);
```

在我们的代码的这个版本中，我们用零食来抚慰狗。现在，当我们再次尝试轻拍它时，该方法会执行而不会失败。

```
$ java ExceptionDemo
Tried to pat: java.lang.RuntimeException: grrr... snarl snarl
Lucy munches the banana

Lucy enjoys the pat.

Lucy is a happy Retriever weighing 80.0 standard lb units.
```

在现实世界中，当期望天线就绪的操作引发异常时，此纠正措施可能是在机器人上扩展天线。或者也许我们只是想将错误写入日志文件以供以后分析。 

### Exceptions 的哲学

异常不是进行错误处理的唯一方法。但它们确实有一些优势。最重要的是，它们在概念上将错误处理与程序的其余部分分开。

让我们考虑一些从文件读取的程序的伪代码：

```java
func readFile: {
    open the file;
    determine its size;
    allocate that much memory;
    read the file into memory;
    close the file;
}
```

这里可能会出现很多问题：可能文件不存在，可能内存不足，或者读取失败。

如果没有例外，我们可能会像这样处理错误：

```java
func readFile: {
    open the file;
    if (theFileIsOpen) {
        determine its size;
        if (gotTheFileLength) {
            allocate that much memory;
        } else {
            return error("fileLengthError");
        }
            if (gotEnoughMemory) {
                read the file into memory;
            if (readFailed) {
                return error("readError");
            }
        ...
        } else {
            return error("memoryError");
        }
    } else {
        return error("fileOpenError")
    } 
}
```

但是这个超级乱！读起来非常令人沮丧。

但是如果有Exception的话，我们可以将其重写为：

```java
func readFile: {
    try {
        open the file;
        determine its size;
        allocate that much memory;
        read the file into memory;
        close the file;
    } catch (fileOpenFailed) {
        doSomething;
    } catch (sizeDeterminationFailed) {
        doSomething;
    } catch (memoryAllocationFailed) {
        doSomething;
    } catch (readFailed) {
        doSomething;    
    } catch (fileCloseFailed) {
        doSomething;
    }
}
```

在这里，我们首先完成与读取文件相关的所有事情，并将它们包装在 try 语句中。然后，如果在该操作序列的任何地方发生错误，它将被适当的 catch 语句捕获。我们可以为每种类型的异常提供不同的行为。

#### 与上面的幼稚版本相比，异常版本的主要好处是代码以清晰的叙述方式流动。首先，尝试执行所需的操作。然后，捕获任何错误。好的代码就像一个故事；它的结构具有一定的美感。这种清晰性使得随着时间的推移更容易编写和维护。

### 未捕获的异常

当抛出异常时，它会下降调用堆栈。

![调用栈](https://joshhug.gitbooks.io/hug61b/content/assets/callstack.png)

如果`peek()`方法没有显式捕获异常，异常将传播到调用函数，`sample()`. 我们可以认为这是将当前方法从堆栈中弹出，并移动到它下面的下一个方法。如果`sample()`也未能捕获异常，则移至`main()`.

如果异常到达堆栈底部而未被捕获，则程序崩溃，Java 为用户提供一条消息，打印出*堆栈跟踪*。理想情况下，用户是有能力做某事的程序员。

```
java.lang.RuntimeException in thread “main”: 
at ArrayRingBuffer.peek:63 
at GuitarString.sample:48 
at GuitarHeroLite.java:110
```

我们可以通过查看发生错误的堆栈跟踪来看到：

在第 63 行`ArrayRingBuffer.peek()`

在被第 48 行调用`GuitarString.sample()`之后

在被第 110 行的 main 方法调用`GuitarHeroLite.java`之后。

但这并不是很有帮助，除非用户也碰巧是一个有能力对错误做点什么的程序员。

