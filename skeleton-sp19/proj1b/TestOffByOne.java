import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    public static void main(String[] args){
        System.out.println(offByOne.equalChars('a','b'));
        System.out.println(offByOne.equalChars('a','a'));

    }
} //uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/