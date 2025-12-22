class Solution {
    public int countPairs(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]) res++;
                else if(func(nums[i],nums[j])) res++;
            }
        }
        return res;
    }

    boolean func(int x,int y){
        int count=0;
        int x1=0,x2=0,y1=0,y2=0;
        while((x>0 || y>0) && count<=2){
            if(x%10 != y%10){
                count++;
                if(count==1){
                    x1=x%10;
                    y1=y%10;
                }else{
                    x2=x%10;
                    y2=y%10;
                }
            }
            x/=10;
            y/=10;
        }

        return ((count==2) && (x2==y1) && (x1==y2));
    }
}