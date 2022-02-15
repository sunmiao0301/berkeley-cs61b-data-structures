## Reading 12.1 Hashing

# 散列

## 到目前为止我们所看到的问题

到目前为止，我们已经研究了一些数据结构，用于有效地搜索数据结构中是否存在项目。我们查看了二叉搜索树，然后使用 2-3 棵树使它们平衡。

但是，这些结构存在一些限制（是的，甚至 2-3 棵树！）

#1. 他们要求项目具有可比性。你如何决定一个新项目在 BST 中的位置？您必须回答“您是小于还是大于根”的问题？对于某些对象，这个问题可能没有意义。

#2. 它们给出了一个复杂度Θ(log N)，这个好吗？当然好，但也许我们可以做得更好。

### 解决上述问题的第一次尝试：`DataIndexedIntegerSet`

让我们从考虑以下方法开始。

目前，我们只打算尝试改进上面的问题 #2（从Θ log N 到 θ(1)

我们不要担心问题#1（可比性）。事实上，我们将只考虑存储和搜索`int`s。

这里有一个想法：让我们创建一个类型是`boolean`，大小为 20 亿的 ArrayList。然后让ArrayList中的一切值为false。

- 该`add(int x)`方法只是将ArrayList 中`x`的位置设置为 true。这需要 θ(1) 时间。
- 该`contains(int x)`方法只返回我们的 ArrayList 中`x`位置是`true`还是`false`。这也只需要 θ(1) 时间！

```java
public class DataIndexedIntegerSet {
    private boolean[] present;

    public DataIndexedIntegerSet() {
        present = new boolean[2000000000];
    }

    public void add(int x) {
        present[i] = true;
    }

    public boolean contains(int x) {
        return present[i];
    }
```

嗯，不是真的。这种方法有哪些潜在**问题**？

- 极其浪费。如果我们假设 一个`boolean`需要 1 个字节来存储，那么上述`DataIndexedIntegerSet`结构就是`2GB`，此外，用户只能插入少量项目......
- 如果有人想插入一个`String`，我们该怎么办？



