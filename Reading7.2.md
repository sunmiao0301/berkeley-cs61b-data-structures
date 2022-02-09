## Reading 7.2

## 访问控制

```apl
public
private
protected
...
```

我们现在遇到了公共和私有成员在包和子类中如何表现的问题。现在想一想：当从父类继承时，我们可以访问该父类中的私有成员吗？或者，同一个包中的两个类可以访问对方的私有成员吗？

```java
```



如果您不立即知道答案，可以继续阅读以找出答案！

**Private** 只有给定类的代码才能访问**私有**成员。它是真正*私有*的，因为子类、包和其他外部类无法访问私有成员。  *TL;DR：只有类需要这段代码*

**pack** 如果没有写入显式修饰符，这是授予 Java 成员的默认访问权限。包私有意味着属于同一包的类可以访问，但不能访问子类！为什么这很有用？通常，包由同一（组）人处理和修改。人们扩展他们最初没有编写的类也很常见。被扩展的类的原始所有者可能不希望某些特性或成员被篡改，如果人们选择扩展它——因此，package-private 允许那些熟悉程序内部工作的人访问和修改某些成员，而它阻止那些子类化的人做同样的事情。

*TL;DR：只有位于同一个包中的类才能访问*

**Protected** 保护成员受到保护，不受“外部”世界的影响，因此同一个包和子类中的类可以访问这些成员，但世界的其他部分（例如，包外部的类或非子类）不能！ *TL;DR：*

*子类型可能需要它，但子类型客户端不需要*

**Public** 这个关键字向所有人开放了访问权限！这通常是包的客户端可以依赖使用的，一旦部署，公共成员的签名不应该改变。这就像对使用此公共代码的人的承诺和合同，他们将始终可以访问它。通常，如果开发人员想要“摆脱”一些公开的东西，而不是删除它，他们会称之为“已弃用 - deprecated”。

*TL;DR：向世界开放并承诺*

**练习 7.1.1** 看看你能不能自己从记忆中画出访问表。

| Modifier        | Class | Package | Subclass | World |
| --------------- | ----- | ------- | -------- | ----- |
| public          | √     | √       | √        | √     |
| protected       | √     | √       | √        | ×     |
| package-private | √     | √       | ×        | ×     |
| private         | √     | ×       | ×        | ×     |

列标题如下：Modifier、Class、Package、Subclass、World，行如下：public、protected、package-private、private。

指示每个行/访问类型是否可以访问该特定列的“类型”。

![使用权](https://joshhug.gitbooks.io/hug61b/content/assets/access_modifiers.png)

## 访问控制细节

没有包声明的**默认包代码自动成为默认包的一部分。**如果这些类的成员没有访问修饰符（即包私有），那么因为所有内容都是同一个（未命名）默认包的一部分，这些成员仍然可以在这些“默认”包类之间访问。

**访问仅基于静态类型**

需要注意的是，**对于接口，其方法的默认访问实际上是公共的，而不是包私有的**。此外，就像这个小标题所示，访问仅取决于静态类型。

**练习 7.1.2**

给定以下代码，demoAccess 方法中的哪些行（如果有）在编译时会出错？

```java
package universe;
public interface BlackHole {
    void add(Object x); // this method is public, not package-private!
}

...........................................................................

package universe;
public class CreationUtils {
    public static BlackHole hirsute() {
         return new HasHair();
    }
}

...........................................................................
    
package universe;
class HasHair implements BlackHole {
    Object[] items;
    public void add(Object o) { ... }
    public Object get(int k) { ... }
}

...........................................................................

import static CreationUtils.hirsute;
class Client {
   void demoAccess() {
      BlackHole b = hirsute();//b is a HasHair(implements BlackHole) and is package-private
      b.add("horse");//ok
      b.get(0);//
      HasHair hb = (HasHair) b;//
   }
}
```

**回答**

- **b.get(0); 这行错误是因为`b`是静态类型`BlackHole`，但是`BlackHole`接口没有定义`get`方法！即使您和我都知道它`b`是动态的 a `HasHair`，因此具有该`get`方法，但编译器仍会根据静态类型进行检查。**
- **HasHair hb = (HasHair) b; 这很棘手，但请注意`HasHair`该类不是公共类 - 它是包私有的。这意味着， 包`Client`外的一个类`universe`，看不到`HasHair`该类的存在。**