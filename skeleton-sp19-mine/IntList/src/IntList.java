import java.awt.*;

public class IntList {
    public int val;
    public IntList next;

    public IntList(int v, IntList I){
        val = v;//when you use this to create an node, this is can't be null anymore.
        next = I;
    }

    public static int recursiveSize(IntList I){
        if(I == null)
            return 0;
        return 1 + recursiveSize(I.next);
    }

    public int recursiveSize(){// instance method without parameter
        /**
         * this大部分情况下可以省略
         * java允许同一个对象的方法直接调用该对象的属性或者方法，所以this可以省略。
         * 但是用来区分局部变量和实例变量的时候，不能省略
         * 所以在下面的代码中，this是可以省略的
         *
         * 但是这种写法 不允许调用该方法的实例是null！我不喜欢这种hug的这种写法，我喜欢我上面的写法.
         */
        if(next == null)
            return 1;
        return 1 + this.next.recursiveSize();
    }

    public int iterativeSize(){//instance method
        int res = 0;
        IntList p = this;
        while(p != null) {
            p = p.next;
            res++;
        }
        return res;
    }

    public static void main(String[] args){
        IntList I = new IntList(0, null);
        I.next = new IntList(1, new IntList(2, null));

        System.out.println(recursiveSize(I));//本类的静态方法在本类调用，可以直接调用
        System.out.println(I.recursiveSize());
        System.out.println(I.iterativeSize());
    }
}
