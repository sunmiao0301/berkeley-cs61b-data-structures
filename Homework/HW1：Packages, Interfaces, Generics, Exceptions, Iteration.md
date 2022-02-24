## HW1：Packages, Interfaces, Generics, Exceptions, Iteration

包、接口、泛型、异常、迭代

## 获取骨架文件

像往常一样`git pull skeleton master`获取起始文件。

请注意，对于本作业，与早期的项目不同，我们将提供大量入门代码供您填写。

## 介绍

在这个项目中，我们将创建一个用于生成合成乐器的**包。**在本作业中，您将学习如何编写和使用包，以及对接口进行一些动手实践。我们还将有机会实现简单的数据结构以及使用该数据结构易于实现的算法。最后，我们将为我们的数据结构添加对迭代和异常的支持。

### Package

包是 Java 类的集合，它们为了某个共同的目标一起工作。我们已经在不知不觉中看到了 61B 中的包。例如， `org.junit`是一个包，其中包含对测试有用的各种类，包括我们熟悉的`Assert`类，它包含有用的静态方法，如 `assertEquals`. 换句话说，当我们看到 `org.junit.Assert.assertEquals` 时， `org.junit`是包名，`Assert`是类名， `assertEquals`是方法名。我们称`org.junit.Assert.assertEquals`方法的“规范名称”，我们称方法`assertEquals`的“简单名称”。

创建包时，我们通过使用 package 关键字在文件顶部指定包名称来指定代码是包的一部分。例如，如果我们想声明一个文件是`es.datastructur.synthesizer` 包的一部分，我们将以下行添加到文件的顶部。

```java
package es.datastructur.synthesizer;
```

如果程序员想要使用我们 `es.datastructur.synthesizer`包中的类或方法，他们将不得不使用完整的规范名称，例如`es.datastructur.GuitarString`，或者选择使用`import es.datastructur.GuitarString`，此时他们可以只使用简单名称`GuitarString`。

通常，包名称是编写代码的实体的互联网地址，但反过来。例如，JUnit 库托管在`junit.org`，因此该包被称为`org.junit`。

为什么包有用？这一切都归结为“规范”这个词。只要没有两个程序员对他们的包使用相同的包名，我们就可以在几个不同的上下文中自由地使用相同的类名。例如，可能存在一个名为 的类`com.hrblock.TaxCalculator`，它不同于 `com.turbotax.TaxCalculator`。鉴于要求使用完整的规范名称或使用导入，这意味着当我们打算使用另一个类时，我们永远不会意外使用一个类。

从概念上讲，您可以将包视为类似于计算机上的不同文件夹。当您构建一个大型系统时，最好将其组织成不同的包。

**从现在开始，我们在 61B 中的大部分代码都将成为包的一部分。**

### 合成器包

该`es.datastructur.synthesizer`软件包具有三个主要组件：

- `BoundedQueue`，一个接口，它声明了必须由任何实现`BoundedQueue`的类实现的所有方法。
- `ArrayRingBuffer`, 一个实现`BoundedQueue`的类，并且是使用数组实现的`BoundedQueue`.
- `GuitarString`，一个使用`ArrayRingBuffer<Double>`实现 [Karplus-Strong 算法](http://en.wikipedia.org/wiki/Karplus–Strong_string_synthesis) 来合成吉他弦声音的类。

我们为您提供了 和 的框架代码`ArrayRingBuffer`，`GuitarString`，但您需要从头开始实现接口`BoundedQueue`。在这个硬件中，我们将按照从最抽象到最具体的层次结构进行工作。

## 任务一：有界队列

#### 回顾：什么是接口？你为什么要一个？

正如课堂上所讨论的，接口是类与外部世界之间的正式契约。如果您的类声称实现了一个接口，那么该接口定义的所有方法都必须出现在您的类中**（或您的超类中的某个位置）**，然后该类才能成功编译。这是一种强制执行承诺行为的方式。**您在接口中声明或定义的所有方法都是自动公开的和抽象的（即使您省略了`public`关键字）。**

#### 你的任务

我们将从定义 BoundedQueue 接口开始。BoundedQueue 类似于我们在项目 1 中的 Deque，但 API 更有限。具体来说，项目只能在队列的后面入队，并且只能从队列的前面出队。与我们的 Deque 不同，BoundedQueue 具有固定的容量，如果队列已满，则不允许任何内容入队。

在文件夹`es.datastructur.synthesizer`中创建一个文件`BoundedQueue.java`。您可以在 IntelliJ 中通过右键单击 `es.datastructur.synthesizer`项目结构侧边栏中的文件夹并单击 New -> Java Class 轻松完成此操作。请务必将“种类”设置为“接口”。

您的 BoundedQueue 接口应包含以下方法：

```java
int capacity();     // return size of the buffer
int fillCount();    // return number of items currently in the buffer
void enqueue(T x);  // add item x to the end
T dequeue();        // delete and return item from the front
T peek();           // return (but do not delete) item from the front
```

您还应该创建默认方法`isEmpty()`和`isFull()`，并在 BoundedQueue 为空或已满时返回适当的答案。

```java
default boolean isEmpty()       // is the buffer empty (fillCount equals zero)?
default boolean isFull()        // is the buffer full (fillCount is same as capacity)?
```

例如，给定一个`BoundedQueue<Double>`容量为 4 的空队列，每次操作后队列的状态如下所示：

```java
isEmpty()       //                       (returns true)
enqueue(9.3)    // 9.3
enqueue(15.1)   // 9.3  15.1
enqueue(31.2)   // 9.3  15.1  31.2
isFull()        // 9.3  15.1  31.2       (returns false)
enqueue(-3.1)   // 9.3  15.1  31.2  -3.1
isFull()        // 9.3  15.1  31.2  -3.1 (returns true)
dequeue()       // 15.1 31.2  -3.1       (returns 9.3)
peek()          // 15.1 31.2  -3.1       (returns 15.1)
```

当然，您的`BoundedQueue.java`文件实际上不会做任何事情（因为它是一个接口），但它将定义任何人`BoundedQueue`必须遵循的合同。

确保将此接口声明为`es.datastructur.synthesizer` 包的一部分。如上所述，声明自己是包的一部分的语法是`package <packagename>;`。例如，如果您是 `animal`包的一部分，则文件的顶部应该有`package animal;`一行。你的包名应该说`es.datastructur.synthesizer`，没有别的。（如果您使用 IntelliJ 创建了文件，这应该已经为您完成了。）

在继续之前，请确保`BoundedQueue`中没有编译错误。

如果您遇到困难，请参阅[List61B 接口](https://github.com/Berkeley-CS61B/lectureCode/blob/7a8b1af0bd5472d732fe9fbb95685830c00295ca/inheritance1/DIY/List61B.java) 以获取具有泛型的接口声明示例。

```java
//我的代码如下：
package es.datastructur.synthesizer;//this interface is a part of this package

public interface BoundedQueue<T> {
    int capacity();//return the size of buffer
    int fillCount();//return number of items currently in the buffer
    void enqueue(T x);//add item x to the end
    T dequeue();//delete and return item from the front
    T peek();//return (but not delete) item from the front
    default boolean isEmpty(){
        return (fillCount() == 0);
    }
    default boolean isFull() {
        return (fillCount() == capacity());
    }
}
```



## 任务 2：ArrayRingBuffer

该类`ArrayRingBuffer`将通过实现`BoundedQueue`来完成所有实际工作 。这意味着我们可以愉快地继承`isEmpty()`，`isFull()` 而不必重写这些，但我们需要重写所有抽象方法。在这部分，您将填写`ArrayRingBuffer.java`。

BoundedQueue 的简单数组实现会将最新项目存储在位置 0，第二个最新项目存储在位置 1，依此类推。这是一种低效的方法，正如我们在下面的示例中所见，注释分别显示数组的条目 0、1、2 和 3。我们假设数组最初都是空值。

```java
BoundedQueue x = new NaiveArrayBoundedQueue(4);
x.enqueue(33.1) // 33.1 null null  null
x.enqueue(44.8) // 33.1 44.8 null  null
x.enqueue(62.3) // 33.1 44.8 62.3  null
x.enqueue(-3.4) // 33.1 44.8 62.3 -3.4
x.dequeue()     // 44.8 62.3 -3.4  null (returns 33.1)
```

请注意，在此设置中，调用`dequeue`速度非常慢，因为它需要将每个项目都向左移动。对于较大的阵列，这将导致无法接受的性能。

**ArrayRingBuffer 将通过使用“环形缓冲区”数据结构来显着改善此运行时间，类似于项目 1A 中的循环数组。**环形缓冲区首先是空的，并且具有一些预定义的长度。例如，这是一个 7 元素缓冲区：

![空缓冲区](http://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Circular_buffer_-_empty.svg/500px-Circular_buffer_-_empty.svg.png)

假设将 1 写入缓冲区的中间（确切的起始位置在环形缓冲区中无关紧要）：

![一项](http://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Circular_buffer_-_XX1XXXX.svg/500px-Circular_buffer_-_XX1XXXX.svg.png)

然后假设添加了另外两个元素——2 和 3——它们被附加在 1 之后。在这里，重要的是 2 和 3 按所示的确切顺序和位置放置：

![三项](http://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Circular_buffer_-_XX123XX.svg/500px-Circular_buffer_-_XX123XX.svg.png)

如果随后从缓冲区中删除两个元素，则删除缓冲区内最旧的两个值。在这种情况下删除的两个元素是 1 和 2，缓冲区只剩下 3：

![又是一项](http://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Circular_buffer_-_XXXX3XX.svg/500px-Circular_buffer_-_XXXX3XX.svg.png)

如果我们然后将 4、5、6、7、8 和 9 入队，则环形缓冲区现在如下所示：

![满的](http://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Circular_buffer_-_6789345.svg/500px-Circular_buffer_-_6789345.svg.png)

请注意，6 被排在数组的最左边的条目中（即缓冲区环绕，像一个环）。**此时，环形缓冲区已满，如果再次执行 enqueue()，则会发生 Exception。您将手动抛出此异常。**[有关更多信息，请参阅](https://sp19.datastructur.es/materials/hw/hw1/hw1#task-4-iteration-exceptions-and-equals)标记为迭代、异常和等于的部分。

**我们建议您维护一个整数实例变量`first`来存储最旧插入的项目的索引；维护第二个整数实例变量`last`，存储在最近插入的项目之后1个的索引位置。要插入一个项目，请将其放在 index`last`处，然后增长一下`last`。要删除一个项目，请删除 index`first`处的元素，然后增长`first`索引。当任一索引等于容量时，通过将索引更改为 0 使其环绕。我们的骨架文件提供了这些行的起始代码。**

```java
//我的实现如下：
package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        rb[last++] = x;
        if(last == capacity())
            last = 0;
        fillCount++;
        //return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        T tmp = rb[first];//这里有点疑问了，要是数组存储的是基本数据类型还好，要是引用类型怎么办
        //解决办法难道就是不null这个索引处的值？ rb[first] == null;
        first++;
        if(first == capacity())
            first = 0;
        fillCount--;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
```

如果您愿意，欢迎您做其他事情，因为这些变量是私有的，因此我们的测试人员无论如何都无法看到它们。

在本作业的最后一部分，如果客户端尝试`enqueue()`进入一个满的的缓冲区`ArrayRingBuffer`或调用`dequeue()`或`peek()`在一个空的缓冲区`ArrayRingBuffer`上，我们将实现我们抛出一个运行时异常。

一旦您充实了 TODO，请确保在继续之前进行编译`ArrayRingBuffer`。或者，您可以向`TestArrayRingBuffer`类添加测试（在您的 write 之前或之后`ArrayRingBuffer`）。 `TestArrayRingBuffer.java`不会被评分。

对于家庭作业和实验室（但不是项目），欢迎您分享测试代码。随时在 Piazza 上分享您对本作业的测试。

## 任务 3：吉他弦

最后，我们要充实`GuitarString`，它使用 `ArrayRingBuffer`来复制弹拨的声音。我们将使用 Karplus-Strong 算法，该算法很容易通过 BoundedQueue 实现。

Karplus-Algorithm 只需以下三个步骤：

1. 用随机噪声（-0.5 和 0.5 之间的双精度值）替换 BoundedQueue 中的每个项目。

2. 移除 BoundedQueue 中的前双倍，并与 BoundedQueue 中的下一个双倍平均（提示：使用`dequeue()`和`peek()`）乘以能量衰减因子 0.996（我们称其为整个量 `newDouble`）。然后，添加`newDouble`到有界队列。

3. 播放`newDouble`您在第 2 步中出列的双 ( )。返回第 2 步（并一直重复）。

   ```java
   //我的代码如下：
   package es.datastructur.synthesizer;
   
   import edu.princeton.cs.algs4.StdAudio;
   
   //Note: This file will not compile until you complete task 1 (BoundedQueue).
   public class GuitarString {
       /** Constants. Do not change. In case you're curious, the keyword final
        * means the values cannot be changed at runtime. */
       private static final int SR = 44100;      // Sampling Rate
       private static final double DECAY = .996; // energy decay factor
   
       /* Buffer for storing sound data. */
       private BoundedQueue<Double> buffer;
   
       /* Create a guitar string of the given frequency.  */
       public GuitarString(double frequency) {
           // TODO: Create a buffer with capacity = SR / frequency. You'll need to
           //       cast the result of this division operation into an int. For
           //       better accuracy, use the Math.round() function before casting.
           //       Your buffer should be initially filled with zeros.
           buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
           while(!buffer.isFull())
               buffer.enqueue(0.0);
       }
   
   
       /* Pluck the guitar string by replacing the buffer with white noise. */
       public void pluck() {
           // TODO: Dequeue everything in buffer, and replace with random numbers
           //       between -0.5 and 0.5. You can get such a number by using:
           //       double r = Math.random() - 0.5;
           //
           //       Make sure that your random numbers are different from each
           //       other.
           for(int i = 0; i < buffer.capacity(); i++){
               buffer.enqueue(Math.random() - 0.5);
           }
       }
   
       /* Advance the simulation one time step by performing one iteration of
        * the Karplus-Strong algorithm.
        */
       public void tic() {
           // TODO: Dequeue the front sample and enqueue a new sample that is
           //       the average of the two multiplied by the DECAY factor.
           //       Do not call StdAudio.play().
   
           //StdAudio.play(sample());
   
           buffer.enqueue((buffer.dequeue() + buffer.peek()) / 2 * DECAY);
   
       }
   
       /* Return the double at the front of the buffer. */
       public double sample() {
           // TODO: Return the correct thing.
           return buffer.peek();
       }
   }
       // TODO: Remove all comments that say TODO when you're done.
   
   ```

   

或者在视觉上，如果 BoundedQueue 如上图所示，我们会将 0.2 出列，将其与 0.4 组合形成 0.2988，将 0.2988 入队，然后播放 0.2。

![karplus-strong](https://sp19.datastructur.es/materials/hw/hw1/karplus-strong.png)

您可以使用该方法播放双精度值`StdAudio.play`。例如 `StdAudio.play(0.333)`，会告诉扬声器的振膜将自身延伸到其总范围的 1/3，`StdAudio.play(-0.9)`会告诉它将它的小心脏向后伸展到几乎可以到达的地方。扬声器振膜的运动会置换空气，如果您以良好的模式置换空气，由于数十亿年的进化，您的意识会将这些干扰解释为令人愉悦的。有关更多信息，请参阅[此页面](http://electronics.howstuffworks.com/speaker6.htm)。如果您只是这样做`StdAudio.play(0.9)`并且不再播放任何内容，则图像中显示的隔膜将仅在前进的 9/10 处静止不动。

完成`GuitarString.java`以实现 Karplus-Strong 算法的步骤 1 和 2。第 3 步将由 `GuitarString`班级的客户完成。

*确保像往常一样将 javalib 导入您的项目，否则 IntelliJ 将无法找到`StdAudio`*.

例如，提供的类提供了一个尝试在吉他弦上弹奏 A 音符`TestGuitarString`的示例测试 。`testPluckTheAString`运行此测试时，您应该会听到一个 A 音符。如果你不这样做，你应该尝试该 `testTic`方法并从那里调试。考虑添加一个`print`or`toString` 方法，`GuitarString.java`这将帮助您了解抽动之间发生的情况。

#### 吉他HeroLite

您现在应该也可以使用`GuitarHeroLite`该类了。运行它将提供一个界面，允许用户（你！）使用`es.datastructur.synthesizer`包的`GuitarString`类交互式地播放声音。

考虑创建一个`GuitarHero`类似于的程序`GuitarHeroLite`，但在 110Hz 到 880Hz 的半音阶上总共支持 37 个音符。使用以下 37 个键来表示键盘，从最低音符到最高音符：

```
String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
```

这种键盘排列模仿钢琴键盘：“白键”位于键盘的 qwerty 和 zxcv 行上，“黑键”位于键盘的 12345 和 asdf 行上。

字符串键盘的第 i 个字符对应的频率为440 ⋅2( i - 24 ) / 12440⋅2(一世-24)/12，所以字符'q'是110Hz，'i'是220Hz，'v'是440Hz，''是880Hz。甚至不要考虑包含 37 个单独的 GuitarString 变量或 37 路 if 语句！相反，创建一个包含 37 个 GuitarString 对象的数组，并使用 keyboard.indexOf(key) 来确定键入了哪个键。如果按下的键与您的 37 个音符之一不对应，请确保您的程序不会崩溃。

这部分作业没有评分。

## 只是为了好玩：TTFAF

一旦您对 GuitarString 应该可以正常工作感到相对舒适，请尝试运行`TTFAF`. *确保您的声音已打开！*

您可以阅读`GuitarPlayer`和`TTFAF`类来了解它们是如何工作的。 `TTFAF`特别包括（作为注释掉的代码）如何以另一种方式使用它的示例。

## 更有趣

- Harp 字符串：在 es.datastructur.synthesizer 包中创建一个 Harp 类。在将新值放入 tic() 之前翻转新值的符号会将声音从类似吉他的声音变为类似竖琴的声音。您可能希望使用衰减因子来提高真实感，并将缓冲区大小调整为两倍，因为 tic() 更改将自然共振频率减半。
- Drums：在 es.datastructur.synthesizer 包中创建一个 Drum 类。在将新值加入 tic() 之前以 0.5 的概率翻转新值的符号将产生鼓声。衰减因子 1.0（无衰减）会产生更好的声音，您需要调整使用的频率集。
- 吉他在 6 根物理琴弦中的一根上演奏每个音符。为了模拟这一点，您可以将您的 GuitarString 实例分成 6 组，当弹拨一个字符串时，将该组中的所有其他字符串归零。
- 钢琴带有一个制音踏板，可用于使琴弦静止。您可以通过在按住某个键（例如 Shift）的迭代中更改衰减因子来实现这一点。
- 虽然我们使用了相等的律，但当音程跟随公正语调系统中的小分数时，耳朵会觉得更悦耳。例如，当音乐家使用铜管乐器演奏纯五度泛音时，频率比是 3/2 = 1.5 而不是 27/12 ∼ 1.498。编写一个程序，其中每对连续的音符都有刚刚的语调。

这部分作业没有评分。

## 为什么有效

使 Karplus-Strong 算法工作的两个主要组件是环形缓冲区反馈机制和平均操作。

- 环形缓冲区反馈机制。环形缓冲器模拟能量在其中来回传播的介质（两端系住的绳子）。环形缓冲区的长度决定了最终声音的基频。从声音上讲，反馈机制仅增强基频及其谐波（频率为基频的整数倍）。能量衰减因子（在这种情况下为 0.996）模拟了当波在弦中往返时能量的轻微耗散。
- 平均操作。平均操作用作温和的低通滤波器（它去除较高的频率，同时允许较低的频率通过，因此得名）。因为它在反馈的路径中，所以它具有逐渐衰减高次谐波同时保留低次谐波的效果，这与弹拨吉他弦的声音密切相关。

## 任务 4：迭代、异常和等于

既然您已经希望获得了很多乐趣，那么让我们做一些简单的练习来使您的数据结构更具工业实力。

```java
//这一部分完成的不好，因为没有自动评分器，不知道完成的如何。

//特别是equals()方法如何实现，m
```



在最后一个任务中，我们将添加遍历 BoundedQueue 的能力，改变它的行为以便在给定无效输入时抛出异常，最后创建一个 equals 方法来测试两个 ArrayRingBuffers 的相等性。

#### 有界队列

首先，修改您的`BoundedQueue<T>`接口，以便`extends Iterable<T>` 将所需的抽象方法添加到接口中。您需要导入 `java.util.Iterator`.

#### 数组环缓冲区

现在将所需的`iterator()`方法添加到`ArrayRingBuffer`. 您需要定义一个实现`Iterator`接口的私有类。 [示例见第 11 课。](https://docs.google.com/presentation/d/1uItKUU8BDI8qSh_T8EO_0DWO34rKJtiO9nuoIj_VduE).

#### Exception 

现在进行修改`ArrayRingBuffer`，以便`RuntimeException`在用户尝试入队时抛出带有字符串“Ring Buffer overflow”的 a full `ArrayRingBuffer`，并在用户尝试调用 dequeue 或 peek on an empty 时抛出“Ring Buffer underflow” `ArrayRingBuffer`。

#### equals()

现在修改`ArrayRingBuffer`，使其覆盖`equals(Object o)`. 仅当另一个对象是`ArrayRingBuffer`具有完全相同的值时，此方法才应返回 true。**这种方法应该是无损的。**

**注意：一旦实现`equals`并运行样式检查器，您将收到一条消息，说明您必须定义一个`hashCode()`函数。暂时忽略这个；我们将在本学期晚些时候讨论这种方法。**

## 提交

您应该按照通常的方式提交，即推送到 GitHub，然后在 Gradescope 上提交。

## 经常问的问题

#### 我收到“类文件包含错误的类”错误。

确保所有 Java 文件的顶部都有正确的包声明。还要确保`es.datastructur.synthesizer` 包中的任何内容都位于名为 synthesizer 的文件夹中，该文件夹位于名为 datastructur 的文件夹中，该文件夹位于名为 es 的文件夹中。

#### 我收到一条消息，说我没有覆盖抽象方法，但我是！

很有可能你有一个错字。在覆盖方法时，您应该始终使用 @Override 标记，以便编译器能够找到任何此类拼写错误。

#### 我得到......在 ArrayRIngBuffer 和......在 BoundedQueue 具有相同的擦除，但都没有覆盖另一个。

确保您的类被定义为`ArrayRingBuffer<T> implements BoundedQueue<T>`（或您使用的任何类型参数而不是 T）。

#### 当我尝试运行提供的测试时，我得到“没有可运行的方法”。

确保您已取消注释测试，包括`@Test`注释。

#### 我没有通过嵌套迭代测试。这是什么意思？

考虑运行以下命令时会发生什么：

```
int[] someInts = new int[]{1, 2, 3};
for (int x : someInts) {
    for (int y: someInts) {
        System.out.println("x: " + x +  ", y:" + y);
    }
}
```

并考虑您的代码如何没有执行上面列出的操作。

#### 当我尝试编译我的代码时，它说类型 K#1 与类型 K#2 或类似的东西不兼容。

如果您正在定义一个内部类，请确保它没有重新声明一个新的泛型类型参数，例如第一个`<Z>`给出的`private class MapWizard<Z> implements Iterator<Z>{`不应该在那里！

#### 我收到一个奇怪的自动分级错误！

虽然`GuitarString`是吉他弦模拟器，但它不应该涉及播放任何声音。播放应由`GuitarString`客户完成。

致谢：来自 [维基百科](http://en.wikipedia.org/wiki/Circular_buffer)的 RingBuffer 数据。该作业改编自[Kevin Wayne 的吉他女主角](http://nifty.stanford.edu/2012/wayne-guitar-heroine/)作业。

### 额外的想法

为简单起见，我们做的一些事情在真正的包中是不好的编程实践。一个重要的一点是我们将所有 3 个类都公开。在现实世界的场景中，最好只制作 GuitarString 类`public`（因为使用合成器的人并不真正关心 ArrayRingBuffers 或 BoundedQueues），并限制其他两个类，以便它们只能在包装自己。为此，我们只需从 ArrayRingBuffer 和 BoundedQueue 定义中省略“public”。但是，为了方便测试，我们将为此硬件公开所有课程。