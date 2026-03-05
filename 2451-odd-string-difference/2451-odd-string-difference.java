class Solution {
    public static String find(String str){
        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<str.length()-1;i++){
            char ch1=str.charAt(i);
            char ch2=str.charAt(i+1);
            sb.append(ch2-ch1);
            sb.append(",");
        }
        return sb.toString();
    }
    public String oddString(String[] words) {
        String first=words[0];
        String second=words[1];
        String f=find(first);
        String s=find(second);
        boolean flag=false;
        String ans="";
        for(int i=2;i<words.length;i++){
            String w=words[i];
            String t=find(w);
            if(f.equals(s) && !s.equals(t)){
                flag=true;
                ans=words[i];
            }
            else if(t.equals(f) && !t.equals(s)){
                flag=true;
                ans=words[i-1];
            }
            else if(t.equals(s) && !t.equals(f)){
                flag=true;
                ans=words[i-2];
            }
            else{
                f=s;
                s=t;
            }
            if(flag) break;
        }
        return ans;
    }
}