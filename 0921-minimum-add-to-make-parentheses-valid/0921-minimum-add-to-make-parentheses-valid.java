class Solution {
    public int minAddToMakeValid(String s) {
        int opening = 0;
        int closing = 0;

        for( int i = 0; i < s.length(); i++ ){
            char ch = s.charAt(i);
            if( ch == '(' ){
                opening++;
            }else if( ch == ')' && opening > 0 ){
                opening--;
            }else{
                closing++;
            }
        }
        return opening + closing;
    }
}