class Solution {
    public int numOfPairs(String[] nums, String target) {
        Map<String,Integer> hm=new HashMap<>();
        for(String s:nums){
            hm.put(s,hm.getOrDefault(s,0)+1);
        }
        int pos=1,count=0;
        int n=target.length();

        while(pos<n){
            String s1=target.substring(0,pos);
            String s2=target.substring(pos,n);
            if(hm.containsKey(s1)&&hm.containsKey(s2)){
                if(s1.equals(s2)){
                    count+=hm.get(s1)*(hm.get(s1)-1);
                }else{
                    count+=hm.get(s1)*hm.get(s2);
                }
                
            }
            pos++;
        }
        return count;
    }
}