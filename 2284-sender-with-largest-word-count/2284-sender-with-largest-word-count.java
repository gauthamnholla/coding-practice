class Solution {
    private int totalWords(String str){
        int count=0;
        for(int i=0;i<str.length();i++){
            char cc=str.charAt(i);
            if(cc==' '){
                count++;
            }
        }
        return count+1;
    }
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String,Integer>map=new HashMap<>();
        int idx=0;
        int maxi=Integer.MIN_VALUE;
        String ans="";
        for(String sender:senders){
            int wordsCount=totalWords(messages[idx++]);
            if(map.containsKey(sender)){
                map.put(sender,map.get(sender)+wordsCount);  
            }else{
                map.put(sender,wordsCount); 
            }
            if(map.get(sender)>=maxi){
                if(map.get(sender)>maxi){
                maxi=map.get(sender);
                ans=sender;
             }else{
                if(ans.compareTo(sender)<0){
                    ans=sender;
                    }
                 }
            }
        }

    return ans;
    }
}