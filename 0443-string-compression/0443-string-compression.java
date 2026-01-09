class Solution {
    public int compress(char[] chars) {
        int l = 0;
        int r = 1;
        int cnt = 1;
        char prev = chars[0];

        while (r < chars.length) {
            if (chars[r] == prev) {
                cnt++;
            } else {
                chars[l++] = prev;

                if (cnt > 1) {
                    String str = Integer.toString(cnt);
                    for (int i = 0; i < str.length(); i++) {
                        chars[l++] = str.charAt(i);
                    }
                }

                prev = chars[r];
                cnt = 1;
            }
            r++;
        }

        // handle last group
        chars[l++] = prev;
        if (cnt > 1) {
            String str = Integer.toString(cnt);
            for (int i = 0; i < str.length(); i++) {
                chars[l++] = str.charAt(i);
            }
        }

        return l;
    }
}