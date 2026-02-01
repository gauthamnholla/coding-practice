class Solution {
    public int twoEggDrop(int n) {
        int moves =0;
        int count=0;
        while(count<n){
            moves++;
            count+=moves;
        }
        return moves;
    }
}