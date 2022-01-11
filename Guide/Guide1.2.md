## Guide 1.2

## 概述

**客户端程序和主要方法。**没有 main 方法的 Java 程序不能直接使用该`java`命令运行。但是，它的方法仍然可以使用`main`另一个类的方法来调用。

**类声明。**Java 类可以包含方法和/或变量。我们说这样的方法和变量是类的“成员”。成员可以是*实例*成员或*静态*成员。静态成员用`static`关键字声明 。实例成员是没有`static`关键字的任何成员。

**类实例化。**实例化一个类几乎总是使用`new`关键字来完成 ，例如`Dog d = new Dog()`. Java 中类的实例也称为“对象”。

**点符号。**我们使用点符号访问类的成员，例如 `d.bark()`. 可以从同一类或其他类中访问类成员。

**构造函数。**构造函数告诉Java 当程序试图创建一个类的实例时该做什么，例如它在执行时应该做什么 `Dog d = new Dog()`。

**数组实例化。**数组也使用`new`关键字实例化。例如，`int[] arr = new int[10]` 如果我们有一个对象数组，例如`Dog[] dogarray`，那么数组的每个元素也必须单独实例化。

**静态与实例方法。**静态方法和实例方法之间的区别非常重要。实例方法是只能由类的实例（即特定对象）执行的操作，而静态方法是由类本身执行的。使用对特定实例的引用来调用实例方法，例如`d.bark()`，而静态方法应该使用类名来调用，例如`Math.sqrt()`。知道何时使用每个。

**静态变量。**变量也可以是静态的。静态变量应该使用类名来访问，例如`Dog.binomen`，而不是`d.binomen`. 从技术上讲，Java 允许您使用特定实例进行访问，但我们强烈建议您不要这样做以避免混淆。

**无效的方法。**一个不返回任何东西的方法应该被赋予一个 void 返回类型。

**该`this`关键字。**在方法内部，我们可以使用`this`关键字来引用当前实例。这相当于`self`在 Python 中。

**公共静态无效主（字符串 [] 参数）。**我们现在知道这些东西的含义：

- public：到目前为止，我们所有的方法都以 this 关键字开头。
- static：它是一个静态方法，不与任何特定实例相关联。
- void：它没有返回类型。
- main：这是方法的名称。
- String[] args：这是传递给 main 方法的参数。

**命令行参数。**参数可以由操作系统提供给您的程序为“命令行参数”，并且可以使用访问`args` 参数`main`。例如，如果我们像这样从命令行调用我们的程序`java ArgsDemo these are command line arguments`，那么 的`main` 方法`ArgsDemo`将有一个包含字符串“这些”、“是”、“命令”、“行”和“参数”的数组。

**使用库。**2017 年没有必要从头开始自己构建一切。在我们的课程中，您可以并且强烈建议您使用 Java 的内置库以及我们提供的库，例如普林斯顿标准库。您不应使用 Java 提供或内置的库以外的库，因为它可能会使某些作业变得毫无意义，而且我们的自动评分器将无法访问这些库并且您的代码将无法工作。

**从 Internet 获取帮助。**欢迎您在线寻求帮助。但是，您应该始终引用您的来源，并且您不应该就特定的家庭作业问题或项目寻求帮助。例如，谷歌搜索“how convert String Java”或“how read file Java”都可以，但你不应该搜索“project 2 61b java berkeley”。

## 练习

### C级

1. 完成[在线教科书中](https://joshhug.gitbooks.io/hug61b/content/chap1/chap12.html)的练习。

   如第 1 课学习指南中所述，我们强烈建议您完成可选的 HW0，它涵盖了一堆基本的 Java 语法。

2. 在下面的代码中，空白变量名称必须是什么才能编译代码

   ```java
    public class Human{
      int _____eyes_____;
      public Human(int ____e____){
        eyes = ______e_____;
      }
     }
   ```

### B级

1. 下面是 Dog 类。

   ```java
    public class Dog{
      public void bark(){
        System.out.println("Moo");
      }
      public static void runFast(){
        System.out.println("Ruff Run");
      }
    }
   ```

   以下哪一行（如果有）会导致错误。

   ```java
   // 测试如下
    public class DogTestStatic{
      public void bark(){
        System.out.println("Moo");
      }
      public static void runFast(){
        System.out.println("Ruff Run");
      }
    }
   
   public class DogTestStaticMain{
       public static void main(String[] args){
           DogTestStatic poppa = new DogTestStatic();//对
           poppa.bark();//对，实例调用非静态方法（实例方法）
           
           DogTestStatic.bark();//错，类调用非静态方法（实例方法）
           
           poppa.runFast();//对的，但不好，实例调用静态方法（类方法）
           //Java中可以通过类实例调用静态方法，当然不推荐这么做，避免出现意想不到的问题
           
           DogTestStatic.runFast();//对，类调用静态方法（类方法）
       }
   }
   
   [msun@ceph57 ~/CS61B]$ javac DogTestStaticMain.java
   DogTestStaticMain.java:5: error: non-static method bark() cannot be referenced from a static context
           DogTestStatic.bark();//错，类调用非静态方法（实例方法）
                        ^
   1 error
   ```