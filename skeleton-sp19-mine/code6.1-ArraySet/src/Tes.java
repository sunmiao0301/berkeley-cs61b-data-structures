public class Tes {
    public static void main(String[] args){
        int[] a = new int[]{1};
        int[] b = new int[]{2};

        int i = 1;
        double d = 2.0;

        System.out.println(add(d, d));
        //System.out.println(add(i, d));
    }
    public static double add(int i, double d){
        return i + d;
    }
    
}
