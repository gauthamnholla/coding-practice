class Solution {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        String letter = "zabcdefgh";
        boolean a = (letter.indexOf(coordinate1.charAt(0)) & 1) == 0;
        boolean b = (letter.indexOf(coordinate2.charAt(0)) & 1) ==0;
        boolean c = ((coordinate1.charAt(1) - '0') & 1) == 0;
        boolean d = ((coordinate2.charAt(1) - '0') & 1) == 0;
        // if(a && b && c && d){
        //     return true;
        // }
        // return false;

        return (a ^ c) == (b ^ d);
    }
}