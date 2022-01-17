public class TestBody{
    public static void main(String[] args){
        Body a = new Body(1, 1, 1, 1, 1, "bro");
        Body b = new Body(2, 2, 2, 2, 2, "bro");
        System.out.println(a.calcForceExertedBy(b));
    }
}