## CS-61B-Data-Structures

### [sp19.datastructur.es](https://sp19.datastructur.es/index.html)

### why this

Fundamental dynamic data structures, including linear lists, queues, trees, and other linked structures; arrays strings, and hash tables. Storage management. Elementary principles of software engineering. Abstract data types. Algorithms for sorting and searching. Introduction to the Java programming language.

### How learn

- Reading — 首先完成的
- Guide — 摘要，可用来回顾
- Discussing — 忽略
- Lab — 按照 Reading 要求，按序完成
- Assignments/Exams — 忽略
- Project  — 按照 Reading 要求，按序完成，且很重要

### Contents

| Reading                  | Key                                                          |
| ------------------------ | ------------------------------------------------------------ |
| 1.1                      | 静态类型（Java特性）<br>所有 Java 代码都是类的一部分（包括测试主方法void main()) |
| 1.2                      | 静态方法与非静态方法（亦称类方法和实例方法）<br/>构造函数是一个**初始化**新创建对象的代码块。构造函数类似于java 中的实例方法，但它不是一个方法，因为它没有返回类型。<br/>public static void main(String[] args)与命令行参数 |
| 2.1                      | 海象之谜<br/>基本数据类型和引用类型<br/>参数传递的本质（仍旧是GRoE） <br/>IntList<br/>迭代和递归 |
| 2.2                      | 将IntList改名为IntNode，然后基于其创建单向链表SSList<br/>public和private<br/>嵌套类（拥有嵌套类对代码性能没有有意义的影响，只是保持代码组织的工具 -->> 将只有某个类使用的类放到使用它的类中成为嵌套类）<br/>哨兵节点 |
| 2.3                      | 双向链表DLList<br/>泛型                                      |
| 2.4                      | 数组<br/>二维数组<br/>System.arraycopy方法<br/>注意数组也能为null，如下（length == 0和null是不同的）<br/>public class ClassNameHere {<br/>   public static void main(String[] args) {<br/>      int[] b;<br/>      int[] a = new int[0];<br/>   }<br/>}<br/>反射 |
| 2.5                      | AList（只支持整型的AList）<br/>Bird[] Alist = (Bird []) new Object[8];（支持通用类型的AList） |
| 关于单向和双向链表的体会 | [link in my repo](https://github.com/sunmiao0301/Berkeley-CS61B-Data-Structures/blob/main/Reading2.5.md#%E5%81%9A%E5%88%B0%E8%BF%99%E9%87%8C%E6%9C%89%E4%B8%AA%E4%BD%93%E6%82%9F%E5%A6%82%E4%B8%8B) |
| 3.1                      |                                                              |
| 4.1                      |                                                              |
| 4.2                      |                                                              |
| 4.3                      |                                                              |
| 6.1                      |                                                              |
| 6.2                      |                                                              |
| 6.3                      |                                                              |
| 6.4                      |                                                              |
| 9.1                      |                                                              |
| 9.2                      |                                                              |
| 9.3                      |                                                              |
| 9.4                      |                                                              |
| 9.5                      |                                                              |
| 8.3                      |                                                              |
| 8.4                      |                                                              |
| 10.1                     |                                                              |
| 10.2                     |                                                              |
| 11.1                     |                                                              |
| 11.2                     |                                                              |
| 11.3                     |                                                              |
| 11.4                     |                                                              |
| 11.5                     |                                                              |
| 12.1                     |                                                              |
| 12.2                     |                                                              |
| 12.3                     |                                                              |
| 12.4                     |                                                              |
| 12.5                     |                                                              |
| 13.1                     |                                                              |
| 13.2                     |                                                              |
| 13.3                     |                                                              |
| 14.1                     |                                                              |
| 15.1                     |                                                              |
| 15.2                     |                                                              |
| 15.3                     |                                                              |
| 16.1                     |                                                              |
| 16.2                     |                                                              |
| 16.3                     |                                                              |
| 17.1                     |                                                              |
| 17.2                     |                                                              |
| 17.3                     |                                                              |
| 17.4                     |                                                              |
| 18.1                     |                                                              |
| 18.2                     |                                                              |
| 19.1                     |                                                              |
| 19.2                     |                                                              |
| 19.3                     |                                                              |
| 20.1                     |                                                              |
| 20.2                     |                                                              |
| 21.1                     |                                                              |
| 21.2                     |                                                              |
| 21.3                     |                                                              |
| 21.4                     |                                                              |
| None Reading but Guide   |                                                              |



### Java — Install Java 8 on Windows and use it in VSCode-x64-1.63.2

#####  check whether Java is already installed on your computer

- Launch the Command Prompt with administrative privileges
- Type in `java -version` to check whether java is installed on your computer or not.

- If the command returns “java is not recognized as an internal or external command, operable program or batch file,” it means that Java is not installed on your system.

##### Install Java on Windows 11

- Open the [Java downloads page for Windows](https://www.oracle.com/java/technologies/downloads/#java8) and click on the download link for **x64 Installer**.

- registered by hfut_sm@163.com (Oracle does not allow you to download older versions of Java such as Java 8 without registering with Oracle. That is you will have to create an account and login to be able to download.)

##### Run the Installer

- To start the installation process, run the installer by double clicking it. You will see the installation wizard.

- Click Next to continue 

##### Custom Setup

- I choose \D\JDK8\jdk1.8.0_311 and click next.
- You will be asked to specify the default installation folder for JRE. I choose \D\JDK8\jre1.8.0_311 and click next.

![java](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110java.png)

![jdkandjre](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110jdkandjre.png)

##### Check the version of Java installed

- Go to windows terminal cmd or powershell and run the command

- `java -version` to check the version of Java installed.

```bash
C:\WINDOWS\system32>java -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, mixed mode)
```

That’s it, now you have Java JDK installed on your system.

##### Install the **Language Support for Java(TM) by Red Hat** and install another version *v0.64.1*

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110anotherversion.png)

##### Install the Extension Pack for Java

##### I am confused about this part and I will try it again to find the best practices

- ~~create a Java project folder and change the setting.json in explorer to this~~

  ```json
  {
      "java.home": "D:\\JDK8\\jdk1.8.0_311",
      //"java.requirements.JDK11Warning": false,
      //"java.semanticHighlighting.enabled": true,
      "java.project.sourcePaths": ["src"],
      "java.project.outputPath": "bin",
  }
  ```

- about the notification below, you can find something you need in this [link](https://vscode.one/exclude-vscode-java-settings/), or look below:

- 这是来自 VS Code 的 Java 扩展的提示：

  https://github.com/redhat-developer/vscode-java/blob/06793b174437fee55985c62917f08da926f37058/src/settings.ts#L73

  我想这是在询问您是否希望那些与 java 相关的项目文件显示在 VS Code 资源管理器侧栏中，或者被隐藏（如果您选择排除，它会将排除项写入您的 VS Code 设置，因此它们被隐藏）。

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110globallyorworkspace.png)

- and allow this to ensure that the Java extension can find jdk

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110extensionfindjdk.png)

- ~~then the setting.json become~~

```json
{
    "java.home": "D:\\JDK8\\jdk1.8.0_311",
    //"java.requirements.JDK11Warning": false,
    //"java.semanticHighlighting.enabled": true,
    //"java.project.sourcePaths": ["src"],
    //"java.project.outputPath": "bin",
    "files.exclude": {
        "**/.classpath": true,
        "**/.project": true,
        "**/.settings": true,
        "**/.factorypath": true
    }
}
```

##### Done, Happy Coding ./

### How to Create a Java Project for Visual Studio Code

Visual Studio Code doesn´t have this orientation of creating a project because it´s file oriented. It´s on of the diferences with Visual Studio.

So, the solution is: Hit Ctrl+Shift+P to show all commands, then type "Java" and select "Java: Create Java Project"



### Java — Install Java 8 on Windows and use it in IntelliJ IDEA 2021.3.1

##### Download IntelliJ IDEA

- You’ll need to install the Community Edition of IntelliJ from the [JetBrains](https://www.jetbrains.com/idea/download/) website.

- After selecting the appropriate version for your OS (Mac, Windows, or Linux), click download and wait a few minutes for the file to finish downloading.

##### Install IntelliJ IDEA

- After the download is complete, open the installer file click “**Next”** select the location, and click on “**Next**“.(Dont ask me why your dir's name is JB, bcz the questionnaire from JB)

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118Jbdir.png)

- Now it will open an installation options window, select all the required options that are shown on the image below, and click “**Next**”.

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118step2.png)

- Select “**JetBrains**” and click on “**Install**“.

![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118step3.png)

- reboot
- **Setup IntelliJ IDEA for Java Development with CS61B**

- 确保您已经拿到skeleton-sp19，并且您在与文件夹`library-sp19`和`lab1`相同的目录中有一个目录`lab2setup`。

1. 在*欢迎*窗口中，单击窗口右下角的**配置 → 插件**按钮。![配置插件](https://sp19.datastructur.es/materials/lab/lab2setup/img2/plugin_setup1.png)
2. 在出现的窗口中，单击“Marketplace”并在顶部的搜索栏中输入“CS 61B”。CS 61B 插件条目应该会出现。如果您单击自动完成建议，可能会出现与下面显示的窗口略有不同的窗口——这没关系。
3. 单击绿色的**安装**按钮，然后等待插件下载并安装。![搜索 CS 61B](https://sp19.datastructur.es/materials/lab/lab2setup/img2/plugin_setup2.png)
4. 现在，搜索“Java Visualizer”，然后单击绿色的**安装**按钮来安装插件。![搜索 Java 可视化工具](https://sp19.datastructur.es/materials/lab/lab2setup/img2/plugin_setup3.png)
5. **重新启动 IDE**按钮以完成安装。

**有关使用插件的更多信息，请阅读[插件指南](https://sp19.datastructur.es/materials/guides/plugin.html#using-the-plugins)。您现在不必阅读此内容。注意，只有在安装了这两个插件后才能继续。**

## 项目设置

IntelliJ 是一个 IDE（集成开发环境）。它包括一个文本编辑器，以及许多额外的功能，使编写代码更容易。为了在这个特殊的环境中运行您的文件，我们可以使用我们的 IDE 魔法，我们需要将我们的文件导入到项目中，类似于将图像或剪辑导入到 iMovie 或 Windows Movie Maker 等程序的项目中的方式。幸运的是，这是一个相当轻松的过程。

通过启动 IntelliJ 开始设置过程。

1. 打开 IntelliJ 后，导入项目，关于导入项目的详细信息，见[**此处的“从现有资源创建项目﻿”**](https://www.jetbrains.com/help/idea/import-project-or-module-wizard.html#import-project)（2021.3版本移除了欢迎页面上的import project按钮）

- 如果欢迎屏幕打开，请按Ctrl+Shift+A，键入`project from existing sources`，然后单击弹出窗口中的**从现有源中导入项目**操作。

1. 找到并选择您的 lab2setup 目录，然后按 OK 按钮。从现在开始，您应该能够为每个屏幕简单地选择下一个，但为了安全起见，更多的屏幕截图如下。**如果您在不注意的情况下继续单击下一步并看到一条消息说没有指定 SDK，请停止并参考第 8 步！**如果您使用的是 Windows 或 Linux，您可能会看到与您的操作系统相对应的不同窗口（下图来自 macOS）。

   

   ![IntelliJ 选择文件夹](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118import.png)

2. 确保选择“从现有资源创建项目”，然后按下一步。在此步骤中，您不必更改任何内容。![导入项目](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext.png)

3. 保持这些字段不变，然后按下一步。![项目名](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext2.png)

4. 在这里什么都不做，然后按下一步。对于上下文，IntelliJ 会自动检测您的 Java 文件是什么，并自行配置以编辑和运行它们。![进口来源](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext3.png)

5. 您可能实际上看不到弹出下一个窗口。如果是，请单击下一步。如果没有，那很好。![进口来源](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext4.png)

6. 您可能实际上看不到弹出下一个窗口。如果是，请单击下一步。如果没有，那很好。![进口来源](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext5.png)

7. 您可能实际上看不到弹出下一个窗口。如果是这样，并且您在左侧边栏上看到1.8，那么您就很清楚了，只需按下一步，然后在最终屏幕上单击完成，瞧，您的项目已设置完毕，您可以跳过第 8、9 步！如果您没有看到 1.8（或您安装的任何 Java 版本），请继续执行步骤8、9。![选择 SDK](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext9.png)

8. 点击左上角的加号，从下拉菜单中点击JDK![添加 JDK](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext6.png)

   ![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext7.png)

9. 找到保存 JDK 的位置，选择它，然后按 OK 按钮。IntelliJ 很可能已经为您找到了该文件夹（它将被称为`Home`）并将其设置为默认选择。如果是这种情况，只需按“确定”。

   否则，在我的 Mac 上，它位于`/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home`.

   您可以`/usr/libexec/java_home`在终端上运行以找出：

```
$ /usr/libexec/java_home
/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home
```

如果您在 Windows 上，您的路径可能看起来像`C:\Program Files\Java\jdk11.0.1`. 如果您使用的是实验室计算机，它应该位于 `/usr/lib/jvm/java-10-oracle/`. 一旦此窗口关闭并且您的屏幕看起来像第 8 步中的图像，请按下一步，然后完成，您就完成了！

![选择 JDK](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext8.png)

10. 按下完成后，您应该会看到与下图非常相似的内容。`lab2setup` 您可能需要单击左上角旁边的小三角形才能显示源文件（`Dog.java`和`DogTest.java`） `lab2setup`。如果您没有看到侧边栏，请转到**查看 → 工具窗口 → 项目**，或选择左侧工具栏上的 **“项目” 。**![选择 JDK](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118justnext10.png)

### 获取 Java 库

~~还记得那个空`library-sp19`文件夹吗？我们将首先使用该类所需的 Java 库填充该文件夹。~~

1. ~~首先，打开一个终端窗口并`cd`进入您的`s**`存储库。~~

2. ~~跑~~

   ```
   git submodule update --init
   ```

   ~~你应该得到这样的输出：~~

   ```bash
   Submodule 'library-sp19' (https://github.com/Berkeley-CS61B/library-sp19) registered for path './'
   Cloning into '/Users/eli/Downloads/sp19-s1/library-sp19'...
   Submodule path './': checked out '1fbc26d69f044b48346849989aeeff9761b00abf'
   ```

3. ~~达达！你现在有图书馆了！~~

   ```
   $ ls library-sp19/
   data/
   javalib/
   ```

   ~~下图是`library-sp19`. 查看文件夹内部，确保您看到下面列出的七个 .jar 文件。如果您使用的是操作系统的文件资源管理器，则“jar”部分可能不会显示在文件名中，这没关系。~~

   ```
   library-sp19/
   └── javalib
    ├── algs4.jar
    ├── hamcrest-core-1.3.jar
    ├── jh61b.jar
    ├── junit-4.12.jar
    ├── stdlib-package.jar
    ├── stdlib.jar
    └── xchart-3.5.1.jar
   ```

以上这一部分，我直接在skeleton-sp19中下载得到library-sp19文件夹。（我不想用自动评分）

### 导入库和运行代码

仔细检查`DogTest.java`。您应该看到文件中的一些单词是红色的，特别是`Test`和`assertEquals`。如果您将鼠标悬停在它们上面，您会看到一条类似“无法解析符号”的消息。问题是我们没有告诉 IntelliJ 在哪里可以找到我们刚刚提取的 CS61B 库。

点击IntelliJ 左上角的**File → Project Structure 。**应该会弹出一个窗口，您应该单击该窗口左侧面板上的**SDK 。**完成后，它将如下所示：![添加库步骤 1](https://sp19.datastructur.es/materials/lab/lab2setup/img3/add_libs1.png)

单击此窗口底部中间的“加号”按钮，然后导航到 `javalib`文件夹（位于 中`library-sp19`）。选择`*.jar`此文件夹中的所有文件（使用 shift-click 选择多个文件）并按“打开”或类似按钮。![添加库步骤 2](https://sp19.datastructur.es/materials/lab/lab2setup/img3/add_libs2.png)

按 OK 几次，您会发现自己再次查看 DogTest.java。这一次，红色文字应该消失了。

**通过单击Run → Run**尝试运行代码，如下所示。![运行->运行](https://sp19.datastructur.es/materials/lab/lab2setup/img3/run_run.png)

这可能会弹出一个非常小的对话框窗口，如图所示。基本上 IntelliJ 是说它不太确定运行程序的意思，并给您两个选择： 0. 是在运行程序之前编辑配置（我们不会这样做）。2.就是运行DogTest类，就是我们想要的。![运行 -> 运行对话框](https://sp19.datastructur.es/materials/lab/lab2setup/img3/run_dialog.png)

单击 DogTest，应该会出现一个绿色条，并显示消息“测试通过：2 个测试中的 2 个”，如下所示。![测试通过](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0118finished.png)

运行代码后，您会注意到右上角的绿色播放和绿色错误图标现在是绿色的；这是因为当你点击“2.”时，IntelliJ 记住了你为这个项目运行的意思，你现在可以点击这个按钮来运行你的程序。随着我们使用 IntelliJ 的更高级功能，随着时间的推移，您将了解更多相关信息。

### 嵌入式终端（可选）

IntelliJ 有一个很酷的功能，您可以在工作区中拥有一个工作终端，这样您就不必在拥有 IntelliJ 和终端之间不断切换，如果出于任何原因需要这样做的话。

对于 Mac 用户，您应该可以跳过此设置部分。Windows 用户可能需要做一些工作。此设置假定您是 Windows 用户并且您已安装 Git Bash。

首先，转到文件 → 设置（或使用 Ctrl + Alt + S）![设置](https://sp19.datastructur.es/materials/lab/lab2setup/img4/intellij_settings.png)

在搜索栏中输入“终端”。在那里，输入：

```
"D:\Git\bin\sh.exe" --login -i
```

进入“外壳路径”字段。单击确定。

![终端](https://sp19.datastructur.es/materials/lab/lab2setup/img4/terminal_settings_window.png)

要测试您是否已正确设置，请单击 IntelliJ 主窗口底部工具栏上的“终端”按钮。屏幕底部的三分之一现在应该是一个终端，相当于在那里有 Git Bash。**（默认是很丑的powershell，配置好了就变成gitbash的样子了）**

![终端测试](https://sp19.datastructur.es/materials/lab/lab2setup/img4/select_terminal.png)

试着输入一些东西！如果您能够运行类似`ls`的基本命令，`cd` 或者`echo 'Hello world'`您已经完成了！

### Remote SSH

—— by zhp

平常用惯了Windows环境的人可能会很喜欢这个办法。利用 VS Code 的远程开发插件Remote SSH，像编辑本地文件一样地编辑远程文件，并且能充分利用 VS Code 的所有其他插件的功能。

- VS Code 中安装 [Remote SSH](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-ssh) 插件，在插件中使用上述命令添加服务器，即可利用VS Code进行远程开发。

- 生成 RSA 密钥对文件，并将公钥内容复制到堡垒机和服务器上。之后即可用命令 `ssh -J hpzhong@202.38.95.226:2222 root@10.10.8.45` 快速登录服务器。（或者用C:\Users\hfurt_\.ssh\config，VSCode能检测到你Windows上的config文件）

  ![](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/0110sshvscode.png)

```bash
  Host msun57
  User msun
  HostName 10.10.8.57
  IdentityFile ~/.ssh/id_rsa
  ProxyJump msun@202.38.95.226:2222
  DynamicForward 80
```

- Connect to Host in Current Window and Open Folder.
- **p.s. Remember SSH TARGETS --> Configure --> Setting --> clean search bar --> change the Auto Save  to "onFocusChange"**



### Calendar

| Week                                                         | Date                                                         | Reading                                                      | Lecture                                                      | Discussion                                                   | Lab                                                          | Assignments/Exams                                            |                                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1 [survey](https://goo.gl/forms/xH4gmt1qTtuo2KBf2)           | Wed 01/23                                                    | [1.1](https://joshhug.gitbooks.io/hug61b/content/chap1/chap11.html) | 1. Intro, Hello World Java [[vid1](https://www.youtube.com/playlist?list=PL8FaHk7qbOD58T7Zjfeq4m6K_D7eDg4TH)] ‌[[vid2](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5k3bSznbTuZERTwr6lg2d_)] ‌[[slides](https://docs.google.com/presentation/d/10w9dMLSGSlpVdVk0wVbXIBYT1-TEbUcoB40sIPhDPuQ/edit#slide=id.g397e8bf9f_013)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec1/lec1.html)] ‌ | [Intro to Java](https://sp19.datastructur.es/materials/discussion/disc01.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/disc01sol.pdf)] | [Setting Up Your Computer](https://sp19.datastructur.es/materials/lab/lab1setup/lab1setup)[javac, java, git (due 2/1)](https://sp19.datastructur.es/materials/lab/lab1/lab1) | [HW 0: Basic Java Programs (optional)](https://sp19.datastructur.es/materials/hw/hw0/hw0) |                                                              |
| Fri 01/25                                                    | [1.2](https://joshhug.gitbooks.io/hug61b/content/chap1/chap12.html) | 2. Defining and Using Classes [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD6XopUFumnRzFCgYXshKex3)] ‌[[slides](https://docs.google.com/presentation/d/1-wnnFpq2BUiGYeiUrbphXZwOyVScgcXDurTb5P7OCrQ/edit#slide=id.g2fbecbe0d1_1_2)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec2/lec2.html)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 2 [survey](https://goo.gl/forms/yblztp9vqGdYWggC3)           | Mon 01/28                                                    | [2.1](https://joshhug.gitbooks.io/hug61b/content/chap2/chap21.html) | 3. References, Recursion, and Lists [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD7lprwG_xdIMLrwibZDi-Ll)] ‌[[slides](https://docs.google.com/presentation/d/18Uj2jLnjJ30CWTUnf0qgHMzYRriC0is2hzrah9bh5_Q/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec3/lec3.html)] ‌ | [Scope, Pass-by-Value, Static](https://sp19.datastructur.es/materials/discussion/disc02.pdf) [[slides](https://docs.google.com/presentation/d/1aF6qyyeKl8sh5N4zn1PmtPde74ZNKRy1RBrQGykkd0w)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc02sol.pdf)][Scope, Pass-by-Value, Static Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep02.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep02sol.pdf)] | [IntelliJ Home Setup](https://sp19.datastructur.es/materials/lab/lab2setup/lab2setup)[IDEs (due 2/1)](https://sp19.datastructur.es/materials/lab/lab2/lab2) | [Project 0: NBody (due 2/1 @ 11:59PM)](https://sp19.datastructur.es/materials/proj/proj0/proj0) |                                                              |
| Wed 01/30                                                    | [2.2](https://joshhug.gitbooks.io/hug61b/content/chap2/chap22.html) | 4. SLLists, Nested Classes, Sentinel Nodes [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4cp06tWA8i9m20pQLvcgE7)] ‌[[slides](https://docs.google.com/presentation/d/1BUYrC_QFq14FtJu6YnjpPqX-uAZig626urU45uxtDEA/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec4/lec4)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 02/01                                                    | [2.3](https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html), [2.4](https://joshhug.gitbooks.io/hug61b/content/chap2/chap24.html) | 5. DLLists, Arrays [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5Gy1o06RRilCqv0So31lJt)] ‌[[slides](https://docs.google.com/presentation/d/1nRGXdApMS7yVqs04MRGZ62dZ9SoZLzrxqvX462G2UbA/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec5/lec5)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 3 [survey](https://goo.gl/forms/pRsg90CQIGz7pDvy2)           | Mon 02/04                                                    | [2.5](https://joshhug.gitbooks.io/hug61b/content/chap2/chap25.html) | 6. ALists, Resizing, vs. SLists [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4S8NCRyN3yQV2U2TpjWUhy)] ‌[[slides](https://docs.google.com/presentation/d/1LGQeMHb8-HFKdvJi5nGKRIPZt4on18fZe-cIyTJv8_4/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec6/lec6)] ‌ | [Linked Lists, Arrays](https://sp19.datastructur.es/materials/discussion/disc03.pdf) [[slides](https://docs.google.com/presentation/d/18mEaQhfLGgpujzniBZq7Veq19P2zC9gJ6BIeMRHYUm0/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc03sol.pdf)][Linked Lists, Arrays Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep03.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep03sol.pdf)] | [Testing, Debugging (due 2/8)](https://sp19.datastructur.es/materials/lab/lab3/lab3) | [Project 1A: Data Structures (due 2/9 @ 11:59 PM)](https://sp19.datastructur.es/materials/proj/proj1a/proj1a) |                                                              |
| Wed 02/06                                                    | [3.1](https://joshhug.gitbooks.io/hug61b/content/chap3/chap31.html), Optional: [TDD is dead](http://david.heinemeierhansson.com/2014/tdd-is-dead-long-live-testing.html), [Unit Tests Are Waste](http://www.rbcs-us.com/documents/Why-Most-Unit-Testing-is-Waste.pdf), [Response](http://henrikwarne.com/2014/09/04/a-response-to-why-most-unit-testing-is-waste/) | 7. Testing [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4ZfVY8g8lo5dFrLP-ctGmT)] ‌[[slides](https://docs.google.com/presentation/d/1g2RwCFKvbv2x0lkFW9hwK1IcmNTHEqZloOLRgF4clhA/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec7/lec7)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 02/08                                                    | [4.1](https://joshhug.gitbooks.io/hug61b/content/chap4/chap41.html) | 8. Inheritance, Implements [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD6km6LlaHLWgRl9SbhlTHk2)] ‌[[slides](https://docs.google.com/presentation/d/1b-Ue_mWWMI2CeHfaU_GO6PWhIpAr_SyZge9JQJQFpXc/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec8/lec8)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 4 [survey](https://goo.gl/forms/ekrTCdGJvXWH6YMx1)           | Mon 02/11                                                    | [4.2](https://joshhug.gitbooks.io/hug61b/content/chap4/chap42.html) | 9. Extends, Casting, Higher Order Functions [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD6Mi8gDriGGeSnHi68QLuVD)] ‌[[slides](https://docs.google.com/presentation/d/15dNZyGIvXbBwkZX35BMRQx7Qjdu6i7Ul5-uovsxut-c/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec9/lec9)] ‌ | [Inheritance](https://sp19.datastructur.es/materials/discussion/disc04.pdf) [[slides](https://docs.google.com/presentation/d/1uN5LwebE8ntlJHJTsCixhP4squ-r_xVUC2vcq8FfUXM/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc04sol.pdf)][Inheritance Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep04.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep04sol.pdf)] | [Peer Code Review (due 2/15)](https://sp19.datastructur.es/materials/lab/lab4/lab4) | [Project 1B: Testing and HoFs (due 2/16 @ 11:59 PM)](https://sp19.datastructur.es/materials/proj/proj1b/proj1b) | [Project 1 Gold: Autograding (due 2/16 @ 11:59 PM)](https://sp19.datastructur.es/materials/proj/proj1gold/proj1gold) |
| Wed 02/13                                                    | [4.3](https://joshhug.gitbooks.io/hug61b/content/chap4/chap43.html) | 10. Subtype Polymorphism vs. HoFs [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD56r1sGUGifsfC0KRDAsuZ3)] ‌[[slides](https://docs.google.com/presentation/d/1r6tlayoFPJAjt4ADb757yCVHOCZ2DSTW5R7M7O-dT0Q/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec10/lec10)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 02/15                                                    | [6.1](https://joshhug.gitbooks.io/hug61b/content/chap6/chap61.html), [6.2](https://joshhug.gitbooks.io/hug61b/content/chap6/chap62.html), [6.3](https://joshhug.gitbooks.io/hug61b/content/chap6/chap63.html), [6.4](https://joshhug.gitbooks.io/hug61b/content/chap6/chap64.html) | 11. Exceptions, Iterators, Object Methods [[video](https://www.youtube.com/watch?v=DWr8YNXPH6k&list=PL8FaHk7qbOD4vPE_Bd8QagarKi3kPw8rB)] ‌[[slides](https://docs.google.com/presentation/d/1uItKUU8BDI8qSh_T8EO_0DWO34rKJtiO9nuoIj_VduE/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec11/lec11)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 5 [survey](https://goo.gl/forms/f5HSgITAmUHrOd2E2)           | Mon 2/18: Academic Holiday                                   | [Iterators, Iterables](https://sp19.datastructur.es/materials/discussion/disc05.pdf) [[slides](https://docs.google.com/presentation/d/100FPpP8gYJuD5SmaONunFdJcTSVWGDAtV6I_zyS7CE4/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc05sol.pdf)][Exceptions, Iterators, Iterables Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep05.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep05sol.pdf)] | [HugLife (due 2/22)](https://sp19.datastructur.es/materials/lab/lab5/lab5) |                                                              |                                                              |                                                              |                                                              |
| Wed 02/20                                                    | None                                                         | 12. Coding in the Real World, Review [[slides](https://docs.google.com/presentation/d/1QhGvi8FmDzyEyIFU_ONFfJYufNWwaNJUwbKq2Z7PDVI/edit?usp=sharing)] ‌ | Midterm 1 (Date 2/20, 8-10PM) Material up to 2/15            |                                                              |                                                              |                                                              |                                                              |
| Fri 02/22                                                    | [8.1](https://joshhug.gitbooks.io/hug61b/content/chap8/chap81.html), [8.2](https://joshhug.gitbooks.io/hug61b/content/chap8/chap82.html), Algs 170-198 (top paragraph) | 13. Asymptotics I [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4oAdQOZ765z6aeqyKs2593)] ‌[[slides](https://docs.google.com/presentation/d/16IBfr6JGMStfd2WqVaUIg4cWrGyeb_g3otsWpVqZJYw/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec13/lec13)] ‌ | [HW 1: Java Syntax and Sound Synthesis (due 2/27)](https://sp19.datastructur.es/materials/hw/hw1/hw1) |                                                              |                                                              |                                                              |                                                              |
| 6 [survey](https://goo.gl/forms/EAIkIxUIgYwBeHox2)           | Mon 02/25                                                    | [9.1](https://joshhug.gitbooks.io/hug61b/content/chap9/chap91.html), [9.2](https://joshhug.gitbooks.io/hug61b/content/chap9/chap92.html), [9.3](https://joshhug.gitbooks.io/hug61b/content/chap9/chap93.html), [9.4](https://joshhug.gitbooks.io/hug61b/content/chap9/chap94.html), [9.5](https://joshhug.gitbooks.io/hug61b/content/chap9/chap95.html), Algs 216-233 | 14. Disjoint Sets [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD59HbdZE3x52KOhJJS54BlT)] ‌[[slides](https://docs.google.com/presentation/d/1TQVF5vHJQb-Qp4h1RanhR8863QQJO-jO0R7GFTArrYo/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec14/lec14)] ‌ | [Disjoint Sets and Asymptotics](https://sp19.datastructur.es/materials/discussion/disc06.pdf) [[slides](https://docs.google.com/presentation/d/1aHLkumiPhKAo41J2dEBBa3by9SO5RyaDu7OpdeMM6Es/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc06sol.pdf)][Disjoint Sets and Asymptotics Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep06.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep06sol.pdf)] | [Disjoint Sets (due 3/1)](https://sp19.datastructur.es/materials/lab/lab6/lab6)[Challenge Disjoint Sets (due 3/1)](https://sp19.datastructur.es/materials/clab/clab6/clab6) |                                                              |                                                              |
| Wed 02/27                                                    | [8.3](https://joshhug.gitbooks.io/hug61b/content/chap8/chap83.html), [8.4](https://joshhug.gitbooks.io/hug61b/content/chap8/chap84.html) (extra), Algs 170-198 | 15. Asymptotics II [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5Ek10eT39UqAjcwr99xqZP)] ‌[[slides](https://docs.google.com/presentation/d/1_LhI5V5JlcRHYU55_SF7ZHxPemBr9OVlNzj7ScYdg64/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec15/lec15)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 03/01                                                    | [10.1](https://joshhug.gitbooks.io/hug61b/content/chap10/chap101.html), [10.2](https://joshhug.gitbooks.io/hug61b/content/chap10/chap102.html), Algs 396-406 | 16. ADTs, Sets, Maps, BSTs [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5JBcKwLbuUf6BAdotM4-1K)] ‌[[slides](https://docs.google.com/presentation/d/1NkqojSVwLwdauC_SC5A2DplVx6AswzeXgn9LFi95APU/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec16/lec16)] ‌ | [HW2: Percolation (due 3/6)](https://sp19.datastructur.es/materials/hw/hw2/hw2) |                                                              |                                                              |                                                              |                                                              |
| 7 [survey](https://goo.gl/forms/Ycz2D8y0yGL3sFm62)           | Mon 03/04                                                    | [11.1](https://joshhug.gitbooks.io/hug61b/content/chap11/chap111.html), [11.2](https://joshhug.gitbooks.io/hug61b/content/chap11/chap112.html), [11.3](https://joshhug.gitbooks.io/hug61b/content/chap11/chap113.html), Algs 424-431, 432-448 (extra) | 17. B-Trees (2-3, 2-3-4 Trees) [[video](https://www.youtube.com/watch?v=0SCtnf84QrI&index=3&list=PL8FaHk7qbOD41EHkD7CgQuRw1jpH_Fv7-)] ‌[[slides](https://docs.google.com/presentation/d/1zhQDvbcDZ9RJgJl0bmqwFFlHP8ExbDFo36Q9ZWH9EgU/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec17/lec17)] ‌ | [More Asymptotics, Search Trees](https://sp19.datastructur.es/materials/discussion/disc07.pdf) [[slides](https://docs.google.com/presentation/d/1ZdRqUP90WwNmBHlHVckrt6WAfml0wMSrC7tq0Kpqfd4/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc07sol.pdf)][More Asymptotics, Search Trees Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep07.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep07sol.pdf)] | [TreeMap (due 3/8)](https://sp19.datastructur.es/materials/lab/lab7/lab7)[Challenge Binary Search Tree Performance (due 3/8)](https://sp19.datastructur.es/materials/clab/clab7/clab7) |                                                              |                                                              |
| Wed 03/06                                                    | [11.4](https://joshhug.gitbooks.io/hug61b/content/chap11/chap114.html), [11.5](https://joshhug.gitbooks.io/hug61b/content/chap11/chap115.html), Algs 424-431, 432-448 (extra) | 18. Red Black Trees [[video](https://www.youtube.com/watch?v=kkd8d0QhiQ0&list=PL8FaHk7qbOD6aKgTz2W-foDiTeBEaBoS3&index=2&t=0s)] ‌[[slides](https://docs.google.com/presentation/d/1FVENq6nVfWEHohE8j3oQC6uutxWOghacBfJixFV3KQU/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec18/lec18)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 03/08                                                    | [12.1](https://joshhug.gitbooks.io/hug61b/content/chap12/chap121.html), [12.2](https://joshhug.gitbooks.io/hug61b/content/chap12/chap122.html), [12.3](https://joshhug.gitbooks.io/hug61b/content/chap12/chap123.html), [12.4](https://joshhug.gitbooks.io/hug61b/content/chap12/chap124.html), [12.5](https://joshhug.gitbooks.io/hug61b/content/chap12/chap125.html), Algs 458-468, 478-479, 468-475 (extra) | 19. Hashing [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD67rFIKNVkDcucFwNjUq9-d)] ‌[[slides](https://docs.google.com/presentation/d/1QevjelsyVO8Ea375VRhIf-o--MIMDYB83OxBbXnbQZU/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec19/lec19)] ‌ | [HW3: Hashing (due 3/11)](https://sp19.datastructur.es/materials/hw/hw3/hw3) |                                                              |                                                              |                                                              |                                                              |
| 8 [survey](https://goo.gl/forms/WkB5aSPmM0Zn8DS42)           | Mon 03/11                                                    | [13.1](https://joshhug.gitbooks.io/hug61b/content/chap13/chap131.html), [13.2](https://joshhug.gitbooks.io/hug61b/content/chap13/chap132.html), [13.3](https://joshhug.gitbooks.io/hug61b/content/chap13/chap133.html), Algs 308-320 | 20. Heaps and PQs [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD50LnOXTSpYgnVJQTIVFsmI)] ‌[[slides](https://docs.google.com/presentation/d/1XgdL3QTKYRAVa14qQznoVORqrPug5tNdmiDmbqM5FlE/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec20/lec20)] ‌ | [LLRBs, Hashing, Heaps](https://sp19.datastructur.es/materials/discussion/disc08.pdf) [[slides](https://docs.google.com/presentation/d/1VlyZ61tvqaH1TGm_HbxxBSkchWdh_ezu0ybP8a5NcYg/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc08sol.pdf)][LLRBs, Hashing, Heaps Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep08.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep08sol.pdf)] | [HashMap (due 3/15)](https://sp19.datastructur.es/materials/lab/lab8/lab8)[Challenge Heaps and Hashes (due 3/15)](https://sp19.datastructur.es/materials/clab/clab8/clab8) |                                                              |                                                              |
| Wed 03/13                                                    | [14.1](https://joshhug.gitbooks.io/hug61b/content/chap14/chap141.html), [15.1](https://joshhug.gitbooks.io/hug61b/content/chap15/chap151.html), [15.2](https://joshhug.gitbooks.io/hug61b/content/chap15/chap152.html), [15.3](https://joshhug.gitbooks.io/hug61b/content/chap15/chap153.html), Algs 730-752 | 21. Prefix Operations and Tries [[video](https://www.youtube.com/watch?v=F8Q-SHW2hAM&list=PL8FaHk7qbOD7eyAcACitG8neRNL2rsvng)] ‌[[slides](https://docs.google.com/presentation/d/1yK88MIaVgAf3Pj-CMr_a4J_6KXCIQd-Fv4LvHDAghng/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec21/lec21)] ‌ | [Proj2: HeapPQ/KD-Tree HeapPQ (due 3/16) KDTree due (3/23)](https://sp19.datastructur.es/materials/proj/proj2ab/proj2ab) |                                                              |                                                              |                                                              |                                                              |
| Fri 03/15                                                    | [16.1](https://joshhug.gitbooks.io/hug61b/content/chap16/chap161.html), [16.2](https://joshhug.gitbooks.io/hug61b/content/chap16/chap162.html), [16.3](https://joshhug.gitbooks.io/hug61b/content/chap16/chap163.html) | 22. Range Searching and Multi-Dimensional Data [[video](https://www.youtube.com/watch?v=BV9Yi7eAEyY&list=PL8FaHk7qbOD5I3wsXKTC70LwFX6KiA3Yy)] ‌[[slides](https://docs.google.com/presentation/d/1lsbD88IP3XzrPkWMQ_SfueEgfiUbxdpo-90Xu_mih5U/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec22/lec22)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 9 [survey](https://forms.gle/do4vbcYs31imsQw78)              | Mon 03/18                                                    | [17.1](https://joshhug.gitbooks.io/hug61b/content/chap17/chap171.html), [17.2](https://joshhug.gitbooks.io/hug61b/content/chap17/chap172.html), [17.3](https://joshhug.gitbooks.io/hug61b/content/chap17/chap173.html), [17.4](https://joshhug.gitbooks.io/hug61b/content/chap17/chap174.html), Algs 538-542, 566-583 | 23. Tree and Graph Traversals [[video](https://www.youtube.com/watch?v=wkkCVWn7au4&index=1&list=PL8FaHk7qbOD4tIQrwqsx16fNq6uXNhauw)] ‌[[slides](https://docs.google.com/presentation/d/1QJ2H8WKwNn9FXYTM38sZEp8vI_AEkHymQNYlDfohe_M/edit#slide=id.g54c7f70b17_0_322)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec23/lec23)] ‌ | [Tries, K-d Trees, Tree Traversals](https://sp19.datastructur.es/materials/discussion/disc09.pdf) [[slides](https://docs.google.com/presentation/d/1zezrdd0Fri5I-YapvzXUSsgfR5ErHdjW0585QRXVusw/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc09sol.pdf)][Tries, K-d Trees, Tree Traversals Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep09.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep09sol.pdf)] | [Tries (due 3/22)](https://sp19.datastructur.es/materials/lab/lab9/lab9)[Challenge Graphs (due 3/22)](https://sp19.datastructur.es/materials/clab/clab9/clab9) |                                                              |                                                              |
| Wed 03/20                                                    | [18.1](https://joshhug.gitbooks.io/hug61b/content/chap18/chap181.html), [18.2](https://joshhug.gitbooks.io/hug61b/content/chap18/chap182.html), Algs 538-542, 566-583 | 24. Graph Traversals and Implementations [[video](https://youtu.be/0fV6z8BX5U0)] ‌[[slides](https://docs.google.com/presentation/d/143WntPl7CG5Po3utVK0jYSA0Jd6XKppT5h1juWEWhUU/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec24/lec24)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 03/22                                                    | [19.1](https://joshhug.gitbooks.io/hug61b/content/chap19/chap191.html), [19.2](https://joshhug.gitbooks.io/hug61b/content/chap19/chap192.html), [19.3](https://joshhug.gitbooks.io/hug61b/content/chap19/chap193.html), Algs 638-657 | 25. Shortest Paths [[video](https://youtu.be/iMoFtG1md3w)] ‌[[slides](https://docs.google.com/presentation/d/1X_HRo2Wr9FwFrzRcH8Ppq6L5r1bEGrlhLS2L9hY4-Ec/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec25/lec25)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Spring Break (3/25 - 3/29)                                   |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
| 10 [survey](https://forms.gle/tpRy3oDgtQudo9fAA)             | Mon 04/01                                                    | [20.1](https://joshhug.gitbooks.io/hug61b/content/chap20/chap201.html), [20.2](https://joshhug.gitbooks.io/hug61b/content/chap20/chap202.html), Algs 604-630 | 26. Minimum Spanning Trees [[video](https://www.youtube.com/watch?v=vnKK38JS9Ik&list=PL8FaHk7qbOD7SvlWei_-neNmgmXzJG2ad&index=1)] ‌[[slides](https://docs.google.com/presentation/d/18leOHESniaJqqehiTR-YAL4WeEEcHJyRB9aw_S1FLG0/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec26/lec26)] ‌ | [DFS, BFS, Shortest Paths, MSTs](https://sp19.datastructur.es/materials/discussion/disc10.pdf) [[slides](https://docs.google.com/presentation/d/1DZM7GK2KwX6zbf33aMZcG3lWQb38_KQ_FjeA3CJnRB8/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc10sol.pdf)][DFS, BFS, Shortest Paths, MSTs Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep10.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep10sol.pdf)] | Exam Review                                                  |                                                              |                                                              |
| Wed 04/03                                                    | [21.1](https://joshhug.gitbooks.io/hug61b/content/chap21/chap211.html), [21.2](https://joshhug.gitbooks.io/hug61b/content/chap21/chap212.html), [21.3](https://joshhug.gitbooks.io/hug61b/content/chap21/chap213.html), [21.4](https://joshhug.gitbooks.io/hug61b/content/chap21/chap214.html) | 27. Reductions and Decomposition [[video](https://youtu.be/VDXFc0sar80)] ‌[[slides](https://docs.google.com/presentation/d/1srzyUpmXrwSLHaAZDYVCpmPFFsq5q-sTwPwdCJkIQVI/edit?usp=sharing)] ‌[guide] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 04/05                                                    | None                                                         | 28. No Lecture                                               | Midterm 2 (Date 4/5, Time 8-10PM) Material up to 4/1         |                                                              |                                                              |                                                              |                                                              |
| 11 [survey](https://docs.google.com/forms/d/e/1FAIpQLSdsSC3g40gzD4z5ltktPpBY6nC4oK-NMEz0nx-Zuli4EjhZlg/viewform?usp=sf_link) | Mon 04/08                                                    | Algs 244-275, 323-327                                        | 29. Basic Sorts [[video](https://youtu.be/AEAmgbls8TM)] ‌[[slides](https://docs.google.com/presentation/d/1sKQPxOF2gBY1GaE8Nq_H2Zce2pMmkoDpbskukzFTSYM/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec29/lec29)] ‌ | [Graphs](https://sp19.datastructur.es/materials/discussion/disc11.pdf) [[slides](https://docs.google.com/presentation/d/10FrgzofQgVGjUFmO2FVvRu_lbQBtpKHYc9PNoKUGX3Q/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc11sol.pdf)][Graphs Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep11.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep11sol.pdf)] | [Merge and Quicksort (due 4/12)](https://sp19.datastructur.es/materials/lab/lab11/lab11)[Challenge Bears and Beds (due 4/12)](https://sp19.datastructur.es/materials/clab/clab11/clab11) | [HW 4: Puzzle Solver (due 4/10)](https://sp19.datastructur.es/materials/hw/hw4/hw4) |                                                              |
| Wed 04/10                                                    | Algs 288-296, 302                                            | 30. Quick Sort [[video](https://youtu.be/u35dILXioxk)] ‌[[slides](https://docs.google.com/presentation/d/1vN37eB8HyjQiFq5N-x5zc6McdRu-5A3oenxCSNLBgU4/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec30/lec30)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 04/12                                                    | None                                                         | 31. Software Engineering I [[video](https://youtu.be/eS03npKka0c)] ‌[[slides](https://docs.google.com/presentation/d/1-Ap-f0yBKFXvAs9DbjEcSfxeOssBb86htGaEz16q6Os/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec31/lec31)] ‌ | [Proj 2C: Bear Maps (due 4/17)](https://sp19.datastructur.es/materials/proj/proj2c/proj2c) |                                                              |                                                              |                                                              |                                                              |
| 12 [survey](https://forms.gle/b5ruqZDAmsAg1sto7)             | Mon 04/15                                                    | Algs 341-347                                                 | 32. More Quick Sort, Sorting Summary [[video](https://youtu.be/Yecyv1olgiE)] ‌[[slides](https://docs.google.com/presentation/d/1fqG18P08NZSGbFd6goXx_yK_QeHg9GLSNPWr-a7TilY/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec32/lec32)] ‌ | [Sorting, ADTs](https://sp19.datastructur.es/materials/discussion/disc12.pdf) [[slides](https://docs.google.com/presentation/d/1zik_yrqjRKIMCjvmCkrC8hJC8h-TS08T3gyMLCvHJJc/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc12sol.pdf)][Sorting Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep12.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep12sol.pdf)] | [Getting Started on Project 3 (due 4/19)](https://sp19.datastructur.es/materials/lab/lab12/lab12) |                                                              |                                                              |
| Wed 04/17                                                    | Algs 279-282                                                 | 33. Sorting and Algorithmic Bounds [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD7y8-q2qpfhZ-fzphsk3ETf)] ‌[[slides](https://docs.google.com/presentation/d/1z2Av67MPCNEtTbpwAlocmF0ss3EruXdQkgy9jdqQ_hM/edit#slide=id.g42d4f6d39_01303)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec33/lec33)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 04/19                                                    | None                                                         | 34. Software Engineering II [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5evlzuFnuBv1tEM2mmq1vc&disable_polymer=true)] ‌[[slides](https://docs.google.com/presentation/d/1CEJzMF0AeUiW6ZNPcEhrEwZfxX-kB1F8IJNi-t4_ILI/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec34/lec34)] ‌ | [Proj 3A: BYOW Phase 1(due 4/26)](https://sp19.datastructur.es/materials/proj/proj3/proj3) |                                                              |                                                              |                                                              |                                                              |
| 13 [survey](https://forms.gle/KCKf7yfTm7vhmHzg8)             | Mon 04/22                                                    | Algs 702-718                                                 | 35. Radix Sorts [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4vkAp0vFBizBL6IywKFdT5&disable_polymer=true)] ‌[[slides](https://docs.google.com/presentation/d/1YBYV2ymAFiHHbSNcC1DRRi2C56blMxEsTZ7G_QE3q_8/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec35/lec35)] ‌ | [More Sorting](https://sp19.datastructur.es/materials/discussion/disc13.pdf) [[slides](https://docs.google.com/presentation/d/1TfuRBgIdUFcSIkgV0q5awxImHlfxJvekpW93469nNkI/edit?usp=sharing)] ‌[[solution](https://sp19.datastructur.es/materials/discussion/disc13sol.pdf)][Sorting Exam Prep](https://sp19.datastructur.es/materials/discussion/examprep13.pdf) [[solution](https://sp19.datastructur.es/materials/discussion/examprep13sol.pdf)] | [Interactivity in Project 3](https://sp19.datastructur.es/materials/lab/lab13/lab13) |                                                              |                                                              |
| Wed 04/24                                                    | None                                                         | 36. Sorting and Data Structures Conclusion [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD4i4ozOgclL_5HMpZrHYA8c&disable_polymer=true)] ‌[[slides](https://docs.google.com/presentation/d/139hGRXUGwrjjJjLYyy7eWD1JdxFSGQbpGYnwA_DMluA/edit)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec36/lec36)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 04/26                                                    | None                                                         | 37. Software Engineering III [[video](https://youtu.be/7lLGNXbAVzo)] ‌[[slides](https://docs.google.com/presentation/d/1M1PPIKy3fWZBqyB0VS-YfZ1gmtdq9Yc4jIL8Pr0IZiM/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec37/lec37)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 14 [survey](https://forms.gle/FVtDwxHHocDFcurQ7)             | Mon 04/29                                                    | None                                                         | 38. Compression [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD6kGO6F1uWKggr-Ie9TCMUZ&disable_polymer=true)] ‌[[slides](https://docs.google.com/presentation/d/1PChiXMquyWo2RrLaPemaga-ihhcNMrM7JI4oVh37HcA/edit?usp=sharing)] ‌[[guide](https://sp19.datastructur.es/materials/lectures/lec38/lec38)] ‌ | Goodbye, Fun                                                 | BYOW Demos                                                   | [Proj 3B: BYOW Phase 2 (due on 5/01)](https://sp19.datastructur.es/materials/proj/proj3/proj3) |                                                              |
| Wed 05/01                                                    | None                                                         | 39. Compression, Complexity, and P=NP? [[video](https://www.youtube.com/playlist?list=PL8FaHk7qbOD5nfUOOXco_8Sx5Nkt-Wgq8)] ‌[[slides](https://docs.google.com/presentation/d/1HEnJvGENEfQmgOsHLMhgdPhO3_Xhpa6T4pODbowR4Ao/edit?usp=sharing)] ‌[guide] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| Fri 05/03                                                    | None                                                         | 40. Summary, Fun [[slides](https://docs.google.com/presentation/d/1bIEpqvR4Zw7C1E5BoemLSb2bpJSKy6EwAORd6MVqs00/)] ‌ |                                                              |                                                              |                                                              |                                                              |                                                              |
| 15                                                           | RRR Week (May 6-10)                                          |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
|                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
|                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
|                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
|                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |
| Finals Week (May 13-17), Final exam: TBD                     |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |                                                              |