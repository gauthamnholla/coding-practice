class Solution {
    static ArrayList<Integer> list = new ArrayList<>();
    public int diagonalPrime(int[][] nums) {
        list.add(2);
        int j= nums.length-1; int max =0; int k=0;
        for(int i =0; i<nums.length; i++){
            boolean rtoLeft = check_prime(nums[i][j]);            
            boolean ltoRight = check_prime(nums[i][i]);
            if(ltoRight) {
                if(nums[i][i]>max) max = nums[i][i];
            }
            if(rtoLeft) {
                if(nums[i][j]>max) max = nums[i][j];
            }
            j--;
        }
        return max;        
    }
    boolean check_prime(int n ){
        if(n<2) return false;
        if(list.get(list.size()-1)>=n){
            int start = 0; int end = list.size()-1;
            while(start<=end){
                int mid = start +(end - start)/2;
                if(list.get(mid)==n) return true;
                else if(list.get(mid)>n) end = mid-1;
                else start = mid+1;
            }
            return false;
        }else{
            for(int i=2; i<=Math.sqrt(n);i++){
                if(n%i==0) return false;
            }
            list.add(n);
        }
        return true;
    }
}