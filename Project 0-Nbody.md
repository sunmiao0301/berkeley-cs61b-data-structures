## Project 0

**[https://sp19.datastructur.es/materials/proj/proj0/proj0](https://sp19.datastructur.es/materials/proj/proj0/proj0)**

### 介绍

你的这个项目的目标是编写一个程序来模拟`N` 物体在平面上的运动，考虑到相互影响每个物体的引力，如艾萨克·牛顿爵士的万有引力[定律所示](http://en.wikipedia.org/wiki/Newton's_law_of_universal_gravitation)。

最终，您将创建一个程序`NBody.java`，该程序绘制一个动画，显示在空间中漂浮的物体利用重力相互拉扯。

### 获取骨架文件

git

### Body 类及其构造函数

**以下在skeleton-sp19/PROJ0中进行**

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
}
```

测试运行结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestBodyConstructor.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestBodyConstructor
Checking first Body constructor...
PASS: xxPos: Expected 1.0 and you gave 1.0
PASS: yyPos: Expected 2.0 and you gave 2.0
PASS: xxVel: Expected 3.0 and you gave 3.0
PASS: yyVel: Expected 4.0 and you gave 4.0
PASS: mass: Expected 5.0 and you gave 5.0
PASS: path to image: Expected jupiter.gif and you gave jupiter.gif
Checking second Body constructor...
PASS: xxPos: Expected 1.0 and you gave 1.0
PASS: yyPos: Expected 2.0 and you gave 2.0
PASS: xxVel: Expected 3.0 and you gave 3.0
PASS: yyVel: Expected 4.0 and you gave 4.0
PASS: mass: Expected 5.0 and you gave 5.0
PASS: path to image: Expected jupiter.gif and you gave jupiter.gif
```

### 完善Body类

![pairwise](https://sp19.datastructur.es/materials/proj/proj0/pairwiseforce.png)

**计算绝对距离**

Start by adding a method called `calcDistance` that calculates the distance between two `Body`s. This method will take in a single Body and should return a double equal to the distance between the supplied body and the body that is doing the calculation.

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }
}
```

测试运行结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestCalcDistance.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestCalcDistance
Checking calcDistance...
PASS: calcDistance(): Expected 1.0 and you gave 1.0
PASS: calcDistance(): Expected 5.0 and you gave 5.0
```



**计算相互作用力**

The next method that you will implement is `calcForceExertedBy`. The `calcForceExertedBy` method takes in a `Body`, and returns a double describing the force exerted on this body by the given body. You should be calling the `calcDistance` method inside this method. 

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //计算两个body之间的距离 实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }

    //计算两个body之间的力
    public double calcForceExertedBy(Body b){
        double G = 6.67 * Math.pow(10, -11);
        return G * (b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }
}
```

测试运行结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestCalcForceExertedBy.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestCalcForceExertedBy
Checking calcForceExertedBy...
PASS: calcForceExertedBy(): Expected 133.4 and you gave 133.4
PASS: calcForceExertedBy(): Expected 6.67E-11 and you gave 6.67E-11
```



**计算x和y方向上的力，注意方向**

The next two methods that you should write are `calcForceExertedByX` and `calcForceExertedByY`. Unlike the `calcForceExertedBy` method, which returns the total force, these two methods describe the force exerted in the X and Y directions, respectively. *Remember to check your signs!* Once you’ve finished, you can recompile and run the next unit test.

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //计算两个body之间的距离 实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }

    //计算两个body之间的力
    public double calcForceExertedBy(Body b){
        double G = 6.67 * Math.pow(10, -11);
        return G * (b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }
    public double calcForceExertedByX(Body b){
        return Math.abs(this.calcForceExertedBy(b) * (this.xxPos - b.xxPos) / this.calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        return Math.abs(this.calcForceExertedBy(b) * (this.yyPos - b.yyPos) / this.calcDistance(b));
    }
}
```

测试验证结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestCalcForceExertedByXY.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestCalcForceExertedByXY
Checking calcForceExertedByX and calcForceExertedByY...
PASS: calcForceExertedByX(): Expected 133.4 and you gave 133.4
PASS: calcForceExertedByX(): Expected 4.002E-11 and you gave 4.002E-11
PASS: calcForceExertedByY(): Expected 0.0 and you gave 0.0
PASS: calcForceExertedByY(): Expected 5.336E-11 and you gave 5.336E-11
```



**计算多个物体情况下，某个物体收到的x和y方向上的力（注意方向，注意排除物理数组总的本身，本身不能对自己产生力）**

Write methods `calcNetForceExertedByX` and `calcNetForceExertedByY` that each take in an array of `Body`s and calculates the net X and net Y force exerted by all bodies in that array upon the current Body. For example, consider the code snippet below:

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //计算两个body之间的距离 实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }

    //计算两个body之间的力
    public double calcForceExertedBy(Body b){
        double G = 6.67 * Math.pow(10, -11);
        return G * (b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }
    
    //注意以下两个函数中，不要使用 Math.abs 来修复这些方法的符号问题。这将在以后绘制行星时引起问题。
    public double calcForceExertedByX(Body b){
        return (this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        return (this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByX(allBodys[i]);
        }
        return ret;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByY(allBodys[i]);
        }
        return ret;
    }
}
```

测试验证结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestCalcNetForceExertedByXY.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestCalcNetForceExertedByXY
Checking calcNetForceExertedByXY...
PASS: calcNetForceExertedByX(): Expected 133.4 and you gave 133.4
PASS: calcNetForceExertedByY(): Expected 0.0 and you gave 0.0
Running test again, but with array that contains the target planet.
PASS: calcNetForceExertedByX(): Expected 133.4 and you gave 133.4
PASS: calcNetForceExertedByY(): Expected 0.0 and you gave 0.0
```



**计算力对物体的速度和位置的改变，这里Berkeley提供的公式好像是错的，可能是为了简化？但是就将错就错了。**

Next, you’ll add a method that determines how much the forces exerted on the body will cause that body to accelerate, and the resulting change in the body’s velocity and position in a small period of time dtdt. For example, `samh.update(0.005, 10, 3)` would adjust the velocity and position if an xx-force of 10 Newtons10 Newtons and a yy-force of 3 Newtons3 Newtons were applied for 0.005 seconds0.005 seconds.

```java
class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //计算两个body之间的距离 实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }

    //计算两个body之间的力
    public double calcForceExertedBy(Body b){
        double G = 6.67 * Math.pow(10, -11);
        return G * (b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }
    
    //注意以下两个函数中，不要使用 Math.abs 来修复这些方法的符号问题。这将在以后绘制行星时引起问题。
    public double calcForceExertedByX(Body b){
        return (this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        return (this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByX(allBodys[i]);
        }
        return ret;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByY(allBodys[i]);
        }
        return ret;
    }

    public void update(double seconds, double xForce, double yForce){
        double xAccelerate = xForce / this.mass;
        double yAccelerate = yForce / this.mass;
        this.xxVel += xAccelerate * seconds;
        this.yyVel += yAccelerate * seconds;
        this.xxPos += this.xxVel * seconds;
        this.yyPos += this.yyVel * seconds;
    }
}
```

验证测试结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java TestUpdate.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestUpdate
Checking update...
PASS: xxVel update(): Expected 3.4 and you gave 3.4
PASS: yyVel update(): Expected 3.8 and you gave 3.8
PASS: xxPos update(): Expected 7.8 and you gave 7.8
PASS: yyPos update(): Expected 8.6 and you gave 8.6
```



**Once you’ve done this, you’ve finished implementing the physics. Hoorah! You’re halfway there.**



### （可选）测试您的Body类

随着学期的进行，我们给你的测试会越来越少，你有责任编写自己的测试。编写测试是改善工作流程和提高效率的好方法。

继续尝试为 Body 类编写自己的测试。创建一个 `TestBody.java`文件并编写一个测试，创建两个实体并打印出它们之间的成对力。这是可选的，我们不会对这部分作业进行评分。

```java
public class TestBody{
    public static void main(String[] args){
        Body a = new Body(1, 1, 1, 1, 1, "bro");
        Body b = new Body(2, 2, 2, 2, 2, "bro");
        System.out.println(a.calcForceExertedBy(b));
    }
}
```

测试验证结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac TestBody.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestBody
6.669999999999999E-11
```



### 模拟器入门 (NBody.java)

创建一个名为`NBody.java`. NBody 是一个实际运行模拟的类。此类将没有构造函数。此类的目标是模拟在其中一个数据文件中指定的 Universe。例如，如果我们查看 data/planets.txt 内部（使用命令行`more`命令），我们会看到以下内容：

```
$ more planets.txt
5
2.50e+11
1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif
2.2790e+11  0.0000e+00  0.0000e+00  2.4100e+04  6.4190e+23     mars.gif
5.7900e+10  0.0000e+00  0.0000e+00  4.7900e+04  3.3020e+23  mercury.gif
0.0000e+00  0.0000e+00  0.0000e+00  0.0000e+00  1.9890e+30      sun.gif
1.0820e+11  0.0000e+00  0.0000e+00  3.5000e+04  4.8690e+24    venus.gif
```

输入格式是一个文本文件，其中包含特定 Universe 的信息（以 SI 单位表示）。第一个值是一个整数`N`，表示行星的数量。第二个值是一个实数`R`，代表宇宙的半径，用于确定绘图窗口的缩放比例。最后，有`N`行，每行包含 6 个值。前两个值是初始位置的 x 和 y 坐标；下一对值是初始速度的 x 和 y 分量；第五个值是质量；最后一个值是一个字符串，它是用于显示行星的图像文件的名称。图像文件可以在`images`目录中找到。上面的文件包含我们自己的太阳系（直到火星）的数据。

#### 读取半径

你的第一种方法是`readRadius`。给定一个文件名 a `String`，它应该返回一个对应于该文件中宇宙半径的双精度数，例如 `readRadius("./data/planets.txt")`应该返回 2.50e+11。

为了帮助您理解该课程，我们在框架中给出的文件夹中`In`为您提供了一些示例。`examples`第一个叫做`BasicInDemo.java`。查看代码及其输入文件`BasicInDemo_input_file.txt`. 该程序应输出：`The file contained 5, 9.0, ketchup, brass, and 5.0`.

有一个稍微复杂一点的示例`InDemo.java`，您也可以在示例文件夹中找到它。虽然此演示与您将在此项目中执行的操作不完全匹配，但您需要的每个方法都在此文件中的某个位置。也欢迎您在网上搜索其他示例（尽管可能很难找到，因为类名`In`是一个非常常见的英文单词）。

注意：不要在代码中使用 System.exit(0)，尽管示例使用它。这将破坏自动评分器，您将无法获得分数。

或者，您可以查阅[In 类的完整文档](http://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html)，尽管可能会发现它有点吓人。

```java
class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
}
```

测试验证结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac NBody.java TestReadRadius.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestReadRadius
Checking readRadius...
PASS: readRadius(): Expected 2.5E11 and you gave 2.5E11
```

#### 读体

您的下一个方法是 readBodies。给定一个文件名，它应该返回一个`Body`与文件中的物体相对应的 s 数组，例如 `readBodies("./data/planets.txt")`应该返回一个由五个行星组成的数组。您会发现In 类中的`readInt()`、`readDouble()`和`readString()`方法很有用。

```java
class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public Body[] readBodies(String s){
        In in = new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] ret = new Body[N];
        for(int i = 0; i < N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String s = in.readString();
            ret[i] = new Body(xP, yP, vX, vY, m, s);
        }
        return ret;
    }
    public void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
    }
}
```

验证测试结果如下：

```bash
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ javac Body.java NBody.java TestReadBodies.java
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java TestReadBodies
Checking readBodies...
PASS: readBodies(); Congrats! This was the hardest test!
```



### 绘制初始宇宙状态（主要）

接下来，构建将宇宙绘制到其起始位置的功能。您将分四个步骤执行此操作。因为这部分作业的所有代码都在 main 中，所以这部分作业不会有自动测试来检查每个小块。

#### 收集所有需要的输入

`main`在 NBody 类中创建一个方法。编写代码，以便您的 NBody 类执行以下步骤：

- 将第 0 个和第 1 个命令行参数存储为名为`T`and的双精度数`dt`。提示：参数以字符串形式出现。您必须在 Google 上搜索才能了解如何将字符串转换为双打！
- 将第二个命令行参数存储为名为`filename`.
- `filename`使用本作业前面的方法从描述的文件中读取物体和宇宙半径 。

```java
class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
    }
}
```

#### 绘制背景

在您的 main 方法从文件中读取所有内容之后，就该开始绘图了。首先，设置比例，使其与宇宙的半径相匹配。然后将图像绘制`starfield.jpg`为背景。为此，您需要弄清楚如何使用 StdDraw 库。

有关 StdDraw 的演示，请参见`StdDrawDemo.java`示例文件夹。这个例子，就像`InDemo.java`，与你正在做的不完全匹配。

此外，请务必查看[此迷你教程的 StdDraw 部分](http://introcs.cs.princeton.edu/java/15inout/)，如果您觉得大胆，请[查看完整的 StdDraw 文档](https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html)。这可能需要一些试验和错误。这可能看起来有点令人沮丧，但这是一个很好的做法！

请注意，您可能会注意到将`starfield.jpg`参数作为参数放入 `StdDraw.picture()`会导致空白屏幕。这是因为我们 `starfield.jpg`在`images`文件夹内。因此，**您将需要使用 proj0 目录中的完整相对路径**，即`images/starfield.jpg` 为了获取您的图像。这适用于您将来可能使用的任何其他图像。

```java
class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] ret = new Body[N];
        for(int i = 0; i < N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ret[i] = new Body(xP, yP, vX, vY, m, img);
        }
        return ret;
    }
    public void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        //filename使用本作业前面的方法从描述的文件中读取物体和宇宙半径
        //物体
        //Body.readBodies(filename);
        //宇宙半径
        //Body.readRadius(filename);

        //StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-Body.readRadius(filename), Body.readRadius(filename));
		StdDraw.clear();
		StdDraw.picture(-Body.readRadius(filename), -Body.readRadius(filename), "images/starfield.jpg");
		StdDraw.show();
		StdDraw.pause(2000);
    }
}
```

#### 绘制一个身体

接下来，我们需要一个物体，比如一颗行星，能够将自己绘制在适当的位置。为此，请绕道返回 Body.java 文件。将最后一个方法添加到 Body 类，`draw`它使用上面提到的 StdDraw API 在 Body 的位置绘制 Body 的图像。该 `draw`方法应该不返回任何内容并且不接受任何参数。

```java
public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    StdDraw.show();
}
```

#### 绘制多个实体

返回到 NBody.java 中的 main 方法，并使用您刚刚编写的方法在您创建的 s 数组`draw`中绘制每个主体。`Body`请务必在绘制`starfield.jpg`文件后执行此操作，以免行星被背景覆盖。

```java
class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] ret = new Body[N];
        for(int i = 0; i < N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ret[i] = new Body(xP, yP, vX, vY, m, img);
        }
        return ret;
    }
    public static void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        //filename使用本作业前面的方法从描述的文件中读取物体和宇宙半径
        //物体
        //Body.readBodies(filename);
        //宇宙半径
        //Body.readRadius(filename);

        StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-NBody.readRadius(filename), NBody.readRadius(filename));
        StdDraw.clear();

		StdDraw.picture(-NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
		StdDraw.picture(NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");
        StdDraw.picture(NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
        StdDraw.picture(-NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");
        StdDraw.show();
		StdDraw.pause(2000);

        Body[] bodies = readBodies(filename);
        int N = bodies.length;
        for(int i = 0; i < N; i++){
            bodies[i].draw();
        }
    }
}
```

验证测试如下：

```bash
# 但是此时发生报错如下
[msun@ceph57 ~/CS61B-code/skeleton-sp19/proj0]$ java NBody 157788000.0 25000.0 data/planets.txt
Exception in thread "main" java.lang.ExceptionInInitializerError
        at NBody.main(NBody.java:37)
Caused by: java.awt.HeadlessException: 
No X11 DISPLAY variable was set, but this program performed an operation which requires it.
        at java.awt.GraphicsEnvironment.checkHeadless(GraphicsEnvironment.java:204)
        at java.awt.Window.<init>(Window.java:536)
        at java.awt.Frame.<init>(Frame.java:420)
        at java.awt.Frame.<init>(Frame.java:385)
        at javax.swing.JFrame.<init>(JFrame.java:189)
        at StdDraw.init(StdDraw.java:669)
        at StdDraw.<clinit>(StdDraw.java:632)
        ... 1 more
# 应该是Linux中无法显示GUI，这不属于我们需要研究的问题，所以我放到windows里面运行了。
# 以下是在windows下的运行结果
PS C:\Users\hfut_\Desktop\proj0>  & 'D:\JDK8\jdk1.8.0_311\bin\java.exe' '-cp' 'C:\Users\hfut_\AppData\Roaming\Code\User\workspaceStorage\c271612a792096e7848ebc3e5d6b2be6\redhat.java\jdt_ws\proj0_a8bcfad4\bin' 'NBody' 157788000.0 25000.0 data/planets.txt
Exception in thread "main" java.lang.IllegalArgumentException: image earth.gif not found
        at StdDraw.getImage(StdDraw.java:1276)
        at StdDraw.picture(StdDraw.java:1341)
        at Body.draw(Body.java:76)
        at NBody.main(NBody.java:52)
# 应该是由于earth.gif没有采用相对路径 改为images/earth.gif
# 运行 成功 得到如下结果
PS C:\Users\hfut_\Desktop\proj0>  & 'D:\JDK8\jdk1.8.0_311\bin\java.exe' '-cp' 'C:\Users\hfut_\AppData\Roaming\Code\User\workspaceStorage\c271612a792096e7848ebc3e5d6b2be6\redhat.java\jdt_ws\proj0_a8bcfad4\bin' 'NBody' 157788000.0 25000.0 data/planets.txt
```

![universe](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0117universe.png)

### 创建动画

到目前为止，您所做的一切都是为了这一刻。只需多一点代码，我们就会得到一些非常酷的东西。

为了创建我们的模拟，我们将离散时间（请不要向斯蒂芬霍金提及）。这个想法是，在每个离散间隔，我们都将进行计算，一旦我们完成了该时间步的计算，我们将更新`Body`s 的值，然后重新绘制宇宙。

`main`通过添加以下内容来完成您的方法：

- 通过调用启用双缓冲`enableDoubleBuffering()`。这是一种防止动画闪烁的图形技术。这应该只是一个方法调用，所以你不应该在这里做任何复杂的事情。您可以在`StdDrawDemo.java`中查看示例。这是官方文档，用几句话对其进行了解释。对于 CS61B，您不必了解这一点。只要知道如果你不调用这个函数，任何平滑动画的尝试都会看起来很糟糕和闪烁（删除它，看看会发生什么！）。
  - 当通过调用启用双缓冲时`enableDoubleBuffering()`，所有绘图都发生在屏幕外画布上。不显示离屏画布。只有当您调用时`show()`，您的绘图才会从屏幕外画布复制到屏幕画布，并显示在标准绘图窗口中。您可以将双缓冲视为收集您告诉它绘制的所有线条、点、形状和文本，然后根据请求同时绘制它们。
- 创建一个表示时间的变量。将其设置为 0。设置一个循环来循环，直到这个时间变量到达（并包括）我们上面得到的`T`量。
- 对于每次循环，请执行以下操作：
  - 创建一个`xForces`数组和`yForces`数组。
  - 计算每个 Body 的净 x 和 y 力，将它们分别存储在 `xForces`和`yForces`数组中。
  - 在计算每个 Body 的净力后，调用`update`每个`Body`s。这将更新每个身体的位置、速度和加速度。
  - 绘制背景图像。
  - 画出所有的`Body`s。
  - 显示屏幕外缓冲区（参见`show`StdDraw 的方法）。
  - 暂停动画 10 毫秒（参见`pause`StdDraw 的方法）。您可能需要在计算机上对此进行调整。
  - 将时间变量增加`dt`.

**重要提示**`update`：对于每次通过主循环，在计算所有力并将其安全存储在`xForces` and之前，不要进行任何调用 `yForces`。例如，`bodies[0].update()`在整个`xForces`和`yForces`数组完成之前不要调用！区别是微妙的，但是如果您`bodies[0].update`在计算`xForces[1]`and之前调用，自动评分器会感到confused`yForces[1]`。

**BUT**

在这里出现了问题，星球会转着转着就飞出去了

课程中对于这个问题，有如下解答：

##### 当我运行模拟时，我的行星开始旋转，但随后迅速加速并从屏幕左下方消失。

- 看看你在一个时间步长计算施加在特定行星上的力的方式。确保力不包括在过去时间步中施加的力。
- `Math.abs(...)`确保在计算时 没有使用`calcForceExertedByX(...)`和 `calcForceExertedByY(...)`。还要确保您使用 a `double`来跟踪总合力（不是`int`）！

**最后版本的NBody.java如下**

```java
import java.nio.file.attribute.UserPrincipal;

import javax.activation.UnsupportedDataTypeException;

class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] ret = new Body[N];
        for(int i = 0; i < N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ret[i] = new Body(xP, yP, vX, vY, m, img);
        }
        return ret;
    }
    public static void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        //filename使用本作业前面的方法从描述的文件中读取物体和宇宙半径
        //物体
        //Body.readBodies(filename);
        //宇宙半径
        //Body.readRadius(filename);

            /**
             * 双缓冲最重要的用途是制作计算机动画，通过快速显示静态绘图来创造运动的错觉。
             * 要制作动画，请重复以下四个步骤：
             * 
             * 清除屏幕外画布。
             * 在离屏画布上绘制对象。
             * 将离屏画布复制到屏幕画布。
             * 稍等片刻。
             */
        
        StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-NBody.readRadius(filename), NBody.readRadius(filename));

        Body[] bodies = readBodies(filename);
        int N = bodies.length;

        double time = 0;

        double[] xForces = new double[N];
        double[] yForces = new double[N];

        while(time <= T){

            StdDraw.clear();

            StdDraw.picture(-NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
		    StdDraw.picture(NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");
            StdDraw.picture(NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
            StdDraw.picture(-NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");

            for(int i = 0; i < N; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //注意这里update的第一个参数是dt不是time（否则就飞出去了）
            for(int j = 0; j < N; j++){
                bodies[j].update(dt, xForces[j], yForces[j]);
                bodies[j].draw();
            }
            //show
            StdDraw.show();
		    StdDraw.pause(25);

            time = time + dt;
        }
    }
}
```

但是由于对普林斯顿的这个函数包不太了解，写出来的星球有点闪烁，应该是因为背景刷新，覆盖了星球的显示？

答：尝试删除了背景，星球还是会闪烁。

补充：感觉和pause时间有关，调整StdDraw.pause(25);为StdDraw.pause(50);闪烁的情况会好很多。

视频转化成gif后如下：

![gif](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0117gifofuniverse.gif)

## 致谢

该作业是 Josh Hug、Matthew Chow 和 Daniel Nguyen 对普林斯顿大学的 Robert Sedgewick 和 Kevin Wayne 创建的作业进行的重大修订。
