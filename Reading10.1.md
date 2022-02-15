## Reading 10.1  ADT Abstract Data type

抽象数据类型 (ADT) 仅由其操作定义，而不由其实现定义。例如在 proj1a 中，我们开发了具有相同方法的 ArrayDeque 和 LinkedListDeque，但这些方法的编写方式却大不相同。在这种情况下，我们说 ArrayDeque 和 LinkedListDeque 是Deque ADT 的*实现*。从这个描述中，我们看到 ADT 和接口有些相关。从概念上讲，Deque 是一个接口，ArrayDeque 和 LinkedListDeque 是它的实现。在代码中，为了表达这种关系，我们让 ArrayDeque 和 LinkedListDeque 类继承自 Deque 接口。

一些常用的 ADT 是：

- 堆栈：支持元素后进先出检索的结构
  - `push(int x)`: 将 x 放在栈顶
  - `int pop()`: 获取栈顶元素
- **列表**：一组有序的元素
  - `add(int i)`: 添加一个元素
  - `int get(int i)`: 获取索引 i 处的元素
- **Sets**：一组无序的唯一元素（无重复）
  - `add(int i)`: 添加一个元素
  - `contains(int i)`：返回集合是否包含值的布尔值
- **Maps**：一组键/值对
  - `put(K key, V value)`：将键值对放入映射中
  - `V get(K key)`: 获取key对应的值

加粗的 ADT 是更大的总体接口的子接口，称为`Collections`

下面我们展示接口和类之间的关系。接口是白色的，类是蓝色的。



ADT 允许我们以一种高效而优雅的方式使用面向对象的编程。您在 proj1b 中看到了我们如何交换`OffByOne`和`OffByN`比较器，因为它们都实现了相同的接口！同样，您可以互换使用 ArrayDeque 或 LinkedListArrayDeque，因为它们都是 Deque ADT 的一部分。

在接下来的章节中，我们将致力于定义更多的 ADT 并列举它们的不同实现。