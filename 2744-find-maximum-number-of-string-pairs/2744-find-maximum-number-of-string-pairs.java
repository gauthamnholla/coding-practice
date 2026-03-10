class Solution {
    public int maximumNumberOfStringPairs(String[] w) {
        int c=0;
        for(int i=0;i<w.length;i++){

            for(int j=i+1;j<w.length;j++){
               String h=w[i]+w[j];
               if(rev(h)){
                 c++;
                 break;
               }
            }
           
        }
         return c;
    }
    public boolean rev(String str){
        StringBuilder s=new StringBuilder(str);
        if(str.equals(s.reverse().toString())){
            return true;
        }
        return false;
    }
}