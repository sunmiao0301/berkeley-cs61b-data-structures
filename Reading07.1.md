## Reading 7.1

### 包和 JAR 文件

很有可能使用这个世界上的所有代码，您将创建与来自不同项目的那些共享名称的类。然后，您如何组织这些类，以便在您尝试访问或使用它们时减少歧义？你的程序如何知道你的意思是使用你的 Dog.class，而不是 Josh Hug 的 Dog.class？

**包**——一个组织类和接口的命名空间。通常，在创建包时，您应该遵循以下命名约定：包名以网站地址开头，向后。

例如，如果 Josh Hug 试图分发他的 Animal 包，其中包含各种不同类型的动物类，他会将他的包命名如下：

```java
ug.joshh.animal; // note: his website is joshh.ug
io.github.sunmiao0301.javasde//note: my website is sunmiao0301.github.io
```

但是，在 CS61B 中，您不必遵循此约定，因为您的代码并非用于分发。

## 使用包

**如果您从同一个包中访问该类，则只需使用其简单名称：**

```java
Dog d = new Dog(...);
```

**如果您从包外部访问类，请使用其完整的规范名称：**

```java
ug.joshh.animal.Dog d = new ug.joshh.animal.Dog(...);
```

**为方便起见，您可以导入包，并改用简单的名称！**

```java
import ug.joshh.animal.Dog;
...
Dog d = new Dog(...);
```

## 创建包

创建包需要以下两个步骤：

1.) 将包名放在此包中每个文件的顶部

```java
package ug.joshh.animal;

public class Dog {
    private String name;
    private String breed;
    …
}
```

2.) 将文件存储在具有适当文件夹名称的文件夹中。该文件夹的名称应与您的包匹配：

即`ug.joshh.animal`**包**在 ug/joshh/animal 文件夹中

**在 IntelliJ 中创建包**

1.）文件→新包

2.) 选择包名（即“ug.joshh.animal”）



1.) File -> New package

2.) Choose package name（i.e. “ug.joshh.animal”）

**在 IntelliJ 中将（新）Java 文件添加到包中**

1.) 右键单击包名称

2.) 选择新建 → Java 类

3.) 为你的类命名，IntelliJ 会自动将它放在正确的文件夹中 + 为你添加“package ug.joshh.animal”声明。



1.) Right-click package name

2.) Select New → Java Class

3.) Name your class, and IntelliJ will automatically put it in the correct folder + add the “package ug.joshh.animal” declaration for you.

**在 IntelliJ 中将（旧）Java 文件添加到包中**

1.) 将“package [packagename]”添加到文件顶部。

2.) 将 .java 文件移动到相应的文件夹中。



1.) Add “package [packagename]” to the top of the file.

2.) Move the .java file into the corresponding folder.

## 默认包

任何在文件顶部没有显式包名的 Java 类都被自动认为是“默认”包的一部分。但是，在编写实际程序时，您应该避免将文件留在默认包中（除非它是一个非常小的示例程序）。这是因为无法导入默认包中的代码，并且可能会意外地在默认包下创建同名的类。

例如，如果我要在默认包中创建一个“DogLauncher.java”类，我将无法在默认包之外的任何其他地方访问这个 DogLauncher 类。

```java
DogLauncher.launch(); // won’t work
default.DogLauncher.launch(); // doesn’t exist
```

因此，您的 Java 文件通常应该以显式的包声明开头。



## JAR 文件

**通常，程序将包含多个 .class 文件。如果您想共享此程序，而不是共享特殊目录中的所有 .class 文件，您可以通过创建 JAR 文件将所有文件“压缩”在一起。这个单一的 .jar 文件将包含您的所有 .class 文件，以及一些其他附加信息。**

需要注意的是，JAR 文件就像 zip 文件一样。完全可以将文件解压缩并转换回 .java 文件。JAR 文件不能保证您的代码安全，因此您不应该与其他学生共享您的项目的 .jar 文件。

**创建 JAR 文件 (IntelliJ)**

1.) 转到文件 → 项目结构 → 工件 → JAR → “来自具有依赖关系的模块”

2.) 单击确定几次

3.) 点击 Build → Build Artifacts（这将在名为“Artifacts”的文件夹中创建一个 JAR 文件）

4.) 将此 JAR 文件分发给其他 Java 程序员，他们现在可以将其导入 IntelliJ（或其他方式）

1.) Go to File → Project Structure → Artifacts → JAR → “From modules with dependencies”

2.) Click OK a couple of times

3.) Click Build → Build Artifacts (this will create a JAR file in a folder called “Artifacts”)

4.) Distribute this JAR file to other Java programmers, who can now import it into IntelliJ (or otherwise)

**构建系统**

与其每次我们想创建一个项目时都导入一个库列表或诸如此类的东西，我们可以简单地将文件放在适当的位置，并使用“构建系统”来自动化设置项目的过程。构建系统的优势在更大的团队和项目中尤其明显，在这些项目中自动化设置项目结构的过程在很大程度上是有益的。

**尽管在 61B 中构建系统的优势相当小，但我们确实在项目 3（BearMaps，2017 年春季）中使用了 Maven，它是许多流行的构建系统（包括 Ant 和 Gradle）之一。**