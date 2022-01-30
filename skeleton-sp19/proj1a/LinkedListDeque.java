import sun.misc.OSEnvironment;

import java.util.List;

public class LinkedListDeque {
    /**
     * 这是一个基于链表的类
     * add和remove操作不得涉及任何循环或递归。单个这样的操作必须花费“恒定时间”，即执行时间不应取决于双端队列的大小。
     */
    public static void main(String[] args){
        LinkedListDeque list = new LinkedListDeque();
        System.out.println("is it empty now?");
        System.out.println(list.isEmpty());
        System.out.println("add a node 2 at first");
        list.addFirst(2);
        System.out.println("is it empty now?");
        System.out.println(list.isEmpty());
        System.out.println("add a node 1 at first");
        list.addFirst(1);
        System.out.println("the size of list is " + list.size());
        System.out.println("the first node of list is " + list.first.val);
        System.out.println("add a node 3 at last");
        list.addLast(3);
        System.out.println("the last of list is " + list.last.val);
        System.out.println("let's print every node");
        list.printDeque();
        System.out.println("now, remove the first node");
        list.removeFirst();
        System.out.println("and remove the last node");
        list.removeLast();
        System.out.println("the only node in list is " + list.get(0).val);
        System.out.println("the only node in list is " + list.getRecursive(0, list.first).val);
    }

    int size;
    Node sentinel;// = new Node(301);
    Node first;// = sentinel;
    Node last;// = sentinel;

    public static class Node{
        //Item<T> item = new Item<>();
        int val;
        Node pre;
        Node next;
        Node(int v){
            val = v;
            pre = null;
            next = null;
        }
        Node(int v, Node p, Node n){
            val = v;
            pre = p;
            next = n;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(301);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        first = sentinel.next;
        last = sentinel.pre;

    }//创建一个空的链表双端队列。

    public LinkedListDeque(LinkedListDeque other){
        //other.sentinel = new Node(301);
        //while()
    }//创建other,创建深层副本意味着您创建一个全新的`LinkedListDeque`，具有完全相同的项目`other`。但是，它们应该是不同的对象，即如果您更改了，您创建`other`的新对象也不应该更改。`LinkedListDeque`（编辑 2/6/2018：为该复制构造函数提供解决方案的演练可在https://www.youtube.com/watch?v=JNroRiEG7U4获得）

    public /** T */ Node getRecursive(int index, Node first){
        if(index == 0)
            return first;
        return getRecursive(index--, first.next);
    }//与 get 相同，但使用递归。

    public void addFirst (int x){//(T item){
        Node p = new Node(x, sentinel, first);
        sentinel.next = p;
        first.pre = p;
        first = p;
        last = sentinel.pre;
        size++;
    }//在双端队列的前面添加一个类型的项目。

    public void addLast(int x){//(T item){
        Node p = new Node(x, last, sentinel);
        sentinel.pre = p;
        last.next = p;
        last = p;
        first = sentinel.next;
        size++;
    }//在双端队列的后面添加一个类型的项目。

    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }//如果 deque 为空，则返回 true，否则返回 false。

    public int size(){
        return size;
    }//返回双端队列中的项目数。

    public void printDeque(){
        Node p = first;
        while(p != sentinel){
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }//从头到尾打印双端队列中的项目，用空格分隔。打印完所有项目后，打印出一个新行。

    public /** T */ Node removeFirst(){
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        first = sentinel.next;
        //first = sentinel.next;
        //first.pre = sentinel;
        size--;
        return first;
    }//删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    public /** T */ Node removeLast() {
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        last = sentinel.pre;
        //last = sentinel.pre;
        //last.next = sentinel;
        size--;
        return last;
    }//删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。

    public /** T */ Node get(int index){
        if(index >= size || index < 0)
            return null;
        Node p = first;
        while (index > 0){
            p = p.next;
            index--;
        }
        return p;
    }//获取给定索引处的项目，其中 0 是前面，1 是下一个项目，依此类推。如果不存在这样的项目，则返回 null。不能改变双端队列！
}
