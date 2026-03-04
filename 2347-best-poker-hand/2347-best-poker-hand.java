
import java.util.*;

class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        int[] ran = new int[14];
        
        int i = 1;
        int n = suits.length;
        for (; i < suits.length; i++) {
            if (suits[i - 1] != suits[i]) break;
        }

        if (i == n) {
            return "Flush";
        }

        for (int j = 0; j < ranks.length; j++) {
            ran[ranks[j]]++;
        }

        int res = 0;
        for (int j = 0; j < ran.length; j++) {
            res = Math.max(res, ran[j]);
        }

        if (res >= 3) {
            return "Three of a Kind";
        } else if (res == 2) {
            return "Pair";
        }  
        return "High Card";
    }
}