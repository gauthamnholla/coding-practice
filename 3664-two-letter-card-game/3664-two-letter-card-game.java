class Solution {
    public int score(String[] cards, char x) {

        int ans = 0;
        int f = 0;
        int tf = 0;
        int s = 0;
        int ts = 0;
        int xx = 0;
        int used = 0;
        int[] dict1 = new int[26];
        int[] dict2 = new int[26];
        
        for (int i = 0; i < cards.length; ++i) {

            char c1 = cards[i].charAt(0);
            char c2 = cards[i].charAt(1);

            if (c1 == x && c2 == x)
                xx++;
            else if (c1 == x) {

                dict1[c2 - 'a']++;
                f = Math.max(dict1[c2 - 'a'], f);
                tf++;
                
            } else if (c2 == x) {

                dict2[c1 - 'a']++;
                s = Math.max(dict2[c1 - 'a'], s);
                ts++;
            }
        
        }

        if (f > (tf - f)) {

            ans += (tf - f);
            tf = f - (tf - f);
            
        } else {

            ans += tf / 2;
            tf = tf % 2;
        }

        
        if (s > (ts - s)) {

            ans += (ts - s);
            ts = s - (ts - s);
            
        } else {

            ans += ts / 2;
            ts = ts % 2;
        }

        used += Math.min(tf, xx);
        xx -= Math.min(xx, tf);
            
        used += Math.min(ts, xx);
        xx -= Math.min(xx, ts);            

        ans = Math.min(ans * 2, ans + xx / 2);
        ans += used;
        
        return ans;
    }
}