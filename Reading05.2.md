## Reading 5.2

### 不变性

不变性的概念是你可能永远不知道存在的东西之一，但是一旦你意识到它是一件事，它可以大大简化你的生活（有点像你成年后意识到没有人*真正*知道他们在做什么，至少当他们第一次开始做新的事情时）。

不可变数据类型是其实例在实例化后无法以任何可观察方式更改的数据类型。

**例如，Java 中的`String`对象是不可变的。无论如何，如果你有一个`String`的实例，你可以调用它的任何花里胡哨的方法，但这个String实例还是会完全保持不变。**

**这意味着当`String`对象被concat()，split()时，原始字符串都不会被修改——而是会返回一个全新的`String`对象。**

可变数据类型包括`ArrayDeque`和`Planet`之类的对象。我们可以从`ArrayDeque`中添加或删除项目，这是可观察到的变化。类似地，`Planet`的速度和位置可能会随着时间而改变。

**任何具有非私有变量的数据类型都是可变的，除非声明了这些变量`final`（这不是可变性的唯一条件——还有许多其他方法可以定义数据类型以使其可变）。**这是因为外部方法可以更改非私有变量的值，从而导致可观察到的变化。

**`final`关键字是用于防止变量在第一次赋值后被更改的变量关键字。例如，考虑`Date`下面的类：**

```java
public class Date {
    public final int month;
    public final int day;
    public final int year;
    private boolean contrived = true;
    public Date(int m, int d, int y) {
        month = m; day = d; year = y;
    }
}
```

**这个类是不可变的。实例化 a 后`Date`，无法更改其任何属性的值。**

**不可变数据类型的优点：**

- **防止错误并使调试更容易，因为属性永远不会改变**
- **您可以指望对象具有某种行为/特征**

**缺点：**

- **您需要创建一个新对象才能更改属性**



### 注意事项

- **将引用声明为final不会使引用指向的对象不可变！例如，考虑以下代码片段：**

  ```java
  public final ArrayDeque<String>() deque = new ArrayDeque<String>();
  ```

  **final的是`deque`，也就意味着deque永远不能重新分配**

  ```java
  public final ArrayDeque<String>() deque = new ArrayDeque<String>();
  ArrayDeque<String>() fakeDeque = new ArrayDeque<String>();
  deque = fakeDeque;//这是不行的
  ```

  **但它指向的数组双端队列对象可以改变！你可以对这个对象进行addFirst()，addLast()或是其他方法**

  ```java
  public final ArrayDeque<String>() deque = new ArrayDeque<String>();
  //下面这些操作都是可以的
  deque.addFirst(1);
  deque.addLast(2);
  ```

- **使用反射 API，甚至可以对私有变量进行更改！我们的不变性概念假设我们没有使用这个库的任何特殊功能。**