public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        //return null;
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        int l = 0;
        int r = word.length() - 1;
        while(l <= r){
            if(word.charAt(l) != word.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args){
        Palindrome p = new Palindrome();
        System.out.print(p.isPalindrome("cattac") + " ");
        System.out.print(p.isPalindrome("cat") + " ");
        System.out.print(p.isPalindrome("c") + " ");
        System.out.print(p.isPalindrome("") + " ");
    }
}
