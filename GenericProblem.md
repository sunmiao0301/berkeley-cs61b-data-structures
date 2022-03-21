## 关于泛型的一个问题

如果我这样写：

```java
public class GenericTest<T> {

    private T[] arr = (T[]) new Object[5];//泛型不能直接创建，但是可以这样转型

    public static void func(GenericTest<String> gt){
        for(int i = 0; i < gt.arr.length; i++){
            if(gt.arr[i] == null)
                System.out.print(i);
        }
    }

    public static void main(String[] args){
        GenericTest<String> gt = new GenericTest<>();
        //gt.func();
        func(gt);
    }
}
```

就会报错：

```bash
[Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
```



但是如果我这样写：

```java
public class GenericTest<T> {

    private T[] arr = (T[]) new Object[5];//泛型不能直接创建，但是可以这样转型

    public void func(){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == null)
                System.out.print(i);
        }
    }

    public static void main(String[] args){
        GenericTest<String> gt = new GenericTest<>();
        gt.func();
        //func(gt);
    }
}
```

就能得到我想要的结果如下：

```bash
01234
Process finished with exit code 0
```

