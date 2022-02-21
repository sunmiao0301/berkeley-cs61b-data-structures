public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        //return false;
        if(x == y)
            return true;
        return false;
    }
}
