import java.security.PublicKey;
import java.util.Arrays;

/**
public class WordUtils {
    public int getMax(int[] cards, int cnt){
        Arrays.sort(cards);
        int Oindex = 0;
        int Jindex = 0;
        int sum = 0;
        int len = cards.length;
        for(int i = len - 1; i >= len - cnt; i--){
            if(cards[i] % 2 == 0){
                Oindex = i;
            }
            else{
                Jindex = i;
            }
            sum += cards[i];
        }
        //index = len - cnt
        int[] oj = new int[]{-1, -1};
        if(sum % 2 == 0)
            return sum;
        else{

            for(int i = len - cnt - 1; i >= 0; i--){
                if(oj[0] != -1 && oj[1] != -1)
                    break;
                if(cards[i] % 2 == 1 && oj[0] == -1){
                    oj[0] = cards[i];
                }
                if(cards[i] % 2 == 0 && oj[1] == -1){
                    oj[1] = cards[i];
                }
            }
        }
        if(cards[Oindex] + oj[1] > oj[0] + cards[Jindex]){
            return sum - cards[Jindex] + oj[0];
        }
    }
}
 */

    //如果是偶数 那么是
    //偶数 = 任意个 * 偶数 + 偶数个 * 奇数
    //cnt = 奇数 --> 奇数个偶数 + 偶数个奇数
    //cnt = 偶数 --> 偶数个偶数 + 偶数个奇数

    //排序
    //9 8 7 6 5 4 3 2 1
    //cnt == 奇数
    //

