class Solution {
    public String convertDateToBinary(String date) {
        String[] str = date.split("-",0);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<str.length; i++){
            sb.append(Integer.toBinaryString(Integer.valueOf(str[i])));
            if(i != str.length - 1){
                sb.append("-");
            }
        }

        return sb.toString();
    }
}