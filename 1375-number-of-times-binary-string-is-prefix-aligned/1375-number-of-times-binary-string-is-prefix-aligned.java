class Solution {
    public int numTimesAllBlue(int[] flips) {
        int n = flips.length;
        char s [] = new char[n];
        for(int j = 0; j<s.length; j++){
            s[j] = '0';
        }
        int count = 0;
        for(int j = 0; j<flips.length ;j++){
            s[flips[j]-1] = '1';
            if(isAligned(s, j)) count++;
        }
        return count;
    }
    public boolean isAligned(char[] s, int i){
        for(int j = 0 ; j<s.length ; j++){
            if((j>=0 && j<=i)&&s[j]!='1') return false;
            else if((j>i && j<s.length)&&(s[j]!='0')) return false;
        }
        return true;
    }
}