import sun.misc.OSEnvironment;

import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    /**
     * 这是一个基于链表的类
     * add和remove操作不得涉及任何循环或递归。单个这样的操作必须花费“恒定时间”，即执行时间不应取决于双端队列的大小。
     */

    int size;
    Node sentinel;// = new Node(301);

    public class Node{
        //Item<T> item = new Item<>();
        T item;
        Node pre;
        Node next;
        Node(T item){
            this.item = item;
            pre = null;
            next = null;
        }
        Node(T item, Node p, Node n){
            this.item = item;
            pre = p;
            next = n;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);//T is null when you create the sentinel node
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }//创建一个空的链表双端队列。

    public LinkedListDeque(LinkedListDeque other){
        //other.sentinel = new Node(301);
        //while()
    }//创建other,创建深层副本意味着您创建一个全新的`LinkedListDeque`，具有完全相同的项目`other`。但是，它们应该是不同的对象，即如果您更改了，您创建`other`的新对象也不应该更改。`LinkedListDeque`（编辑 2/6/2018：为该复制构造函数提供解决方案的演练可在https://www.youtube.com/watch?v=JNroRiEG7U4获得）

    public T getRecursive(int index, Node sentinel){
        if(index == 0)
            return sentinel.next.item;
        return getRecursive(index--, sentinel.next);
    }//与 get 相同，但使用递归。

    @Override
    public void addFirst (T item){//(T item){
        Node p = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = p;
        sentinel.next = p;
        size++;
    }//在双端队列的前面添加一个类型的项目。

    @Override
    public void addLast(T item){//(T item){
        Node p = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = p;
        sentinel.pre = p;
        size++;
    }//在双端队列的后面添加一个类型的项目。

    /**
    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }//如果 deque 为空，则返回 true，否则返回 false。
     */

    @Override
    public int size(){
        return size;
    }//返回双端队列中的项目数。

    @Override
    public void printDeque(){
        Node p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }//从头到尾打印双端队列中的项目，用空格分隔。打印完所有项目后，打印出一个新行。

    @Override
    public T removeFirst(){
        Node res = sentinel.next;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res.item;
    }//删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T removeLast() {
        Node res = sentinel.pre;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size--;
        return res.item;
    }//删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T get(int index){
        if(index >= size || index < 0)
            return null;
        Node p = sentinel.next;
        while (index > 0){
            p = p.next;
            index--;
        }
        return p.item;
    }//获取给定索引处的项目，其中 0 是前面，1 是下一个项目，依此类推。如果不存在这样的项目，则返回 null。不能改变双端队列！
}
