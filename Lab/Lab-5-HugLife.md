## Lab 5：HugLife

## 介绍

在本实验中，您将创建一个名为`creature`的包，该包将实现两个生物（或更多，如果您愿意），它们将居住在 `huglife`包模拟的世界中。（有关什么是包的介绍，请参阅教科书的第 7 章。）今天的实验旨在为您在期中后轻松而有趣地介绍地Map。

#### 拥抱生活

首先，拉取 Lab 5 的启动文件：

```
git pull skeleton master
```

通过启动 HugLife 模拟器启动实验室。去做这个：

- 将项目导入 IntelliJ
- 在 IntelliJ 的侧边栏中，查看`huglife`包/文件夹并打开 `HugLife.java`文件。
- 单击第 10 行旁边的绿色播放按钮运行文件。它将打印出`Usage: java huglife.HugLife [worldname]`.
- 用法消息告诉我们，我们需要传入命令行参数才能运行文件（还记得 Lab 1 中的 LeapYear 吗？）。我们可以通过单击 IntelliJ 顶部的下拉菜单（在右上角`HugLife`的另一个运行和调试按钮旁边）来在 IntelliJ 中执行此操作。从该菜单中，选择`Edit Configurations...`并参数`samplesolo`添加到`Program Arguments:` 。再次按下`OK`并运行该文件。这一次，你应该看到我们的世界打开了，一个孤独的红色方块随机游荡。

在本作业中，您将创建以下两个将进入`creatures/`目录的生物文件：

- `Plip.java`（提供骨架）
- `Clorus.java`（你需要创建这个）

这些类将扩展`huglife.Creature`类，它提供了一个所有生物都应该遵循的模板。

最终这两种生物也会栖息在这个世界上，它们会以 [一种有趣的方式](http://i.imgur.com/E2Kdowq.gifv)相互作用。

#### 模拟器的工作原理

生物生活在 NxN 网格上，没有围城。每个方格可能是空的、无法通行的，或者包含一个生物。在每个时间步长，只有一个生物采取了一个动作。生物以循环方式选择动作；他们一个接一个地轮流。

世界上所有生物都有一个全球队列，等待轮到他们采取行动。当一个生物排在队列的最前面时，世界模拟器会告诉该生物它的四个邻居是谁，并要求该生物选择行动。更具体地说，世界模拟器调用了生物的`chooseAction()`方法，该方法将所有四个邻居的集合作为参数。根据四个邻居的身份，该生物选择五个动作之一：移动、复制、攻击、停留或死亡。

MOVE、REPLICATE 和 ATTACK 是定向动作，STAY 和 DIE 是静止动作。如果一个生物采取定向动作，它必须指定一个方向或一个位置。例如，如果行动的生物在它的左边看到一个可以吃的生物，它可能会选择向左攻击。

**本实验的主要任务之一是编写做出 Creature 决策的代码。动作作为`Action`类的对象返回，在`huglife/Action.java`.**

在一个生物选择一个 `Action` 后，模拟器对世界网格进行更改。**您将负责编写跟踪每个生物状态的代码。**例如，在行动生物吃掉另一个生物后，行动生物可能会变得更强壮、死亡、变色等。

这将通过对生物的*回调*来完成，该回调需要提供`move()`、`replicate()`、`attack()`和`stay()`方法，这些方法描述了在这些相应的动作中的每一个之后行动生物的状态将如何演变。

例如，如果您的生物选择通过返回  `new Action(Action.ActionType.REPLICATE, Direction.TOP)`来向上复制，则游戏模拟器保证稍后会调用`replicate()`做出此选择的生物的方法。

## 试验样本生物

打开您将在`huglife/`目录中找到的`Occupant.java`、`Creature.java`和`SampleCreature.java`。

- `Occupant`是所有可能存在于 HugLife 宇宙网格中的事物的通用类。您会看到`Occupant`继承了一个名称，该名称由该子类型的所有实例共享`Occupant`。此外，每个人都`Occupant` 必须提供一个返回颜色的方法（稍后会详细介绍）。有两种特殊`Occupant`类型，名称为“empty”和“impassible”。这些分别代表未占用和不可占用的方格。
- `Creature`是可以居住在 HugLife 宇宙网格中的所有生物的通用类。每个`Creature`人都有一个能级，如果那个能级低于零，宇宙将为他们选择 DIE 行动。
  - 每个生物都必须实现四种回调方法：`move()`、 `replicate()`、`attack()`和`stay()`。这些描述了当每个动作发生时生物应该做什么。没有任何`die()`方法，因为该生物只是从世界上完全移除。
  - 生物还必须实现一种`chooseAction()`方法。
- `SampleCreature`是一个样本`Creature`；其实就是我们之前看到的那个孤独的红方块。你为这个实验实现的两个生物看起来就像`SampleCreature`

对示例生物进行一些更改并观察它们如何影响 HugLife 模拟。您可以像在本实验开始时那样运行模拟器。

```java
//r g b 是三色值
//moveProbability 当方块周围是空，方块移动的可能性。
//colorShift
//...
```



## 创建一个Plip

现在是时候为世界添加一种新型生物了。进入 `creatures/`目录，你会看到一个名为`Plip.java`那里的文件，等待填写。

#### 基本的 Plip 功能

`Plip`s 将是懒惰（但能动）的光合作用生物。他们大多只是站着生长和复制，但如果碰巧看到他们的死敌`Clorus`，他们就会逃跑。

打开`TestPlip.java`，它也在`creatures/`目录中。您会看到我们提供了一个测试文件`creatures.TestPlip`，您可以运行该文件来测试您的`Plip`。

试一试，你会发现最初`testBasics`测试失败了。

`Plip`现在根据以下规范修改类，直到所有测试通过。完成后，您就可以拥有一个功能齐全的 Plip！

让我们从我们`Plip`类最终需要的一些属性开始。

- `name()`方法（继承自）`Occupant`应该完全返回`"plip"` ，没有空格或大写。这很重要，因为生物只知道如何根据这个名称字符串相互反应。（你真的需要改变什么来确保这一点吗？）
- Plips 应该`0.15`在 MOVE 动作中损失能量单位，并在动作中获得`0.2`能量单位`STAY`。
- Plips 的能量永远不应大于`2`. 如果某个动作会导致 Plip 的能量大于`2`，则应将其设置为`2`。
- Plips 的能量也不应该低于`0`. 如果某个动作会导致 Plip 的能量小于`0`，则应将其设置为`0`。
- Plips的`color()`方法应返回颜色`red = 99`， `blue = 76`其中`green = 96*e+63` 、 e 是 plip 的能量，这样就可以实现： 如果 plip 的能量为零，则它的`green`值应为`63`。如果它有最大能量，它应该有一个`green`值`255`。

我们可以`Plip`通过在 HugLife 世界网格上粘贴一堆 Plips 来测试我们的课程，并观察他们在狂奔时做了什么。但是，很难确定一切是否正常。相反，让我们直接对`Plip`类执行测试。

```bash
# 执行Testplip 可得到：

Tests passed：1 

Process finished with exit code 0
```

> *测试注意事项：*不一定要测试所有内容！仅当您认为测试可能揭示一些有用的东西时才使用测试，即您有可能会出错。弄清楚要测试什么是一门艺术！

#### Plip 复制方法

现在我们将努力为我们的 Plips 添加正确的复制属性。在开始之前，请阅读以下规范并在方法中编写适当的测试`testReplicate()`。**请务必检查：`Plip` 的方法`replicate()`返回的`plip`与调用方法的`Plip`不同。**

Plips 的复制行为是：

- 当 Plip 复制时，它会保留 50% 的能量。剩下的 50% 属于它的后代。在复制过程中没有能量损失。

在 `Plip.java`中`replicate()`的方法中实现此逻辑。

```java
//我的实现如下：
    public Plip replicate() {
        Plip babyPlip = new Plip(this.energy / 2);
        this.energy = babyPlip.energy;
        return babyPlip;
    }

//测试结果如下：
    @Test
    public void testReplicate() {
        // TODO
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);

        Plip baby = p.replicate();
        assertEquals(0.85, p.energy(), 0.01);

        assertEquals(new Color(99, 144, 76), baby.color());
    }
//并且通过

Tests passed：1 

Process finished with exit code 0
```



#### Plip`chooseAction()`方法

剩下的就是给 Plip 一个大脑。为此，您将填写`chooseAction()`方法。这一次，我们为您提供了一个 JUnit 测试。

Plip 应遵循以下行为规则，按优先顺序排列：

1. 如果没有空格，则 Plip 应该`STAY`.
2. 否则，如果 Plip 的能量大于或等于 1.0，它应该复制到可用空间。
3. 否则，如果它看到一个`name()`等于“clorus”的邻居，它将以 50% 的概率移动到任何可用的空方格。并且应该随机选择空方格。例如，如果它看到`BOTTOM`处的 Clorus，它有 50% 的机会移动（由于对 Cloruses 的恐惧），如果它确实移动了，它会在`LEFT`，`RIGHT` ，`TOP`之间的随机选择一个方向移动。
4. **否则，它会留下来。**

这些规则必须按照这个严格的顺序来遵守！示例：如果它的能量大于 1，它总是会复制，即使有相邻的 Cloruses。

#### 测试`chooseAction()`

这一次，我们为您提供了测试，因此您可以专注于弄清楚地图如何用于实施。在开始之前`chooseAction()`，取消注释中方法的`@Test`注释标记。这将允许 测试运行。在开始之前通读测试，以确保您同意预期的结果。`testChoose()``TestPlip.java``testChoose`

目前，`testChoose()`将只测试前两个选项 (`STAY`和`REPLICATE`) 如果附近有 Clorus，请不要担心测试 50% 规则，因为您还没有创建`Clorus`类。

稍后，一旦您编写了`Clorus`课程，您可能会发现回来尝试编写随机性测试很有趣，因为逃离 clorus 的几率只有 50%。一种可能性是通过多次调用并确保每个调用至少发生一次来简单地测试这两种选择是否可行。对于今天的实验室而言，执行统计测试可能有点过分（尽管如果您想要额外的挑战，欢迎您）。

#### 写作`chooseAction()`

编辑`Plip`类，使其做出正确的选择。您需要仔细查看`SampleCreature`该类作为指南，以了解这些`huglife`功能。您还需要弄清楚如何使用`neighbors`参数，即`Map`. 要了解如何使用它，请查看用于`Map`. 您可能还想在 Google 上了解如何使用增强的 for 循环来遍历地图的键。什么是地图？它就像一个 Python 字典。每个键都映射到一个对应的值，因此在这种情况下，每个键`Direction`都映射到一个`Occupant`（并记住 an `Creature`、 `Empty`和`Impassible`extend `Occupant`）。

## 模拟剪辑

假设您的测试成功，您现在可以看到您的 Plips 在真实的 HugLife 世界中的表现如何。`huglife.Huglife`使用命令行参数运行`sampleplip`。（您可以像以前一样通过再次编辑程序参数的配置来做到这一点。）

您应该会看到您的 Plips 快乐地成长。如果出现问题，可能是因为您的测试不够彻底。如果是这种情况，请考虑 plip 执行了哪些操作，并使用错误消息添加新测试，`TestPlip.java`直到最终出现问题。

## 介绍 Clorus

现在我们将实现 Clorus，它是一种凶猛的蓝色掠食者，只喜欢吃倒霉的 Plips 零食。

首先，创建`TestClorus.java`并`Clorus.java`在`creatures`包中。与以前不同，您将从头开始编写这些类。

Clorus 应该完全遵守以下规则：

- 所有 Cloruses 的名称必须完全等于“clorus”（没有大写或空格）。
- Cloruses 应该`0.03`在一个`MOVE`动作上失去能量单位。
- Cloruses 应该`0.01`在一个`STAY`动作上失去能量单位。
- Cloruses对其最大能量没有限制。
- `color()`Cloruses 的方法应该总是返回颜色`red = 34`, `green = 0`, `blue = 231`。
- 如果 Clorus 攻击另一个生物，它应该获得该生物的能量。这应该发生在`attack()`方法中，而不是`chooseAction()`. 您不必担心确保被攻击的生物会死亡——模拟器会为您完成。
- 像 Plip 一样，当 Clorus 复制时，它会保留 50% 的能量。剩下的 50% 属于它的后代。在复制过程中没有能量损失。
- Cloruses 应该完全遵守以下行为规则：
  1. 如果没有`empty`方格，则 Clorus 会`STAY`（即使附近有 Plips，他们也可以攻击，因为 plip 方格不算作空方格）。
  2. 否则，如果看到任何 Plip，Clorus 将`ATTACK`随机选择其中一个。
  3. 否则，如果 Clorus 的能量大于或等于 1，它将复制到一个随机的空方格。
  4. 否则，Clorus 会`MOVE`随机到一个空方格。

和以前一样，先写一个`TestClorus`类。您可能不需要测试 `move()`、`stay()`或`color()`方法，但欢迎您这样做。您应该为每种类型的操作至少包含一个测试。

编写完*完整*的测试后，编写`Clorus`类本身。

## 开演时间

我们做到了。

现在是时候观看 Cloruses 和 Plips 的战斗了。进入`HugLife.java`并取消注释 中的行`readWorld()`。`huglife.Huglife`然后，通过命令行参数运行，开始一场摩尼教式的斗争，以永恒的振荡或孤独的不朽者永远在荒野中游荡而告终 `strugggz`。（同样，您可以通过编辑配置并将其输入到程序参数中来做到这一点。）

如果你做的一切都正确，它应该看起来很酷。您可以考虑调整 HugLife 模拟器参数，包括世界大小和模拟步骤之间的暂停时间。请注意，大于 50x50 的世界尺寸可能会运行得相当缓慢。

## 提交

这个实验室的自动评分器非常基础。如果你的 HugLife 模拟看起来基本正确——也就是说，如果它类似于介绍中的 [动画](http://i.imgur.com/E2Kdowq.gifv)——那么你可能做的一切都是正确的。