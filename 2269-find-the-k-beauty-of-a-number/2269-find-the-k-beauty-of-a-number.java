class Solution {
    public int divisorSubstrings(int num, int k) {
        String str = String.valueOf(num);

        int kbeauty = 0;
        int left = 0;
        for(int right = k-1; right<str.length(); right++) {
            if(right - left + 1 > k) {
                left++;
            }
            String temp = str.substring(left, right + 1);
            int subnum = Integer.valueOf(temp);
            if(subnum == 0) continue;
            if(num % subnum == 0) kbeauty++;
        }
        return kbeauty;
    }
}