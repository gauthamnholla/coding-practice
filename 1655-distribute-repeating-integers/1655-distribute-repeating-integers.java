class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        HashMap<Integer,Integer>map = new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        int arr[] = new int[map.size()];
        int j=0;
        for(int i : map.values()){
            arr[j++] = i;
        }

        Arrays.sort(quantity);
        reverse(quantity);

        return helper(0,arr,quantity);

    }

    public boolean helper(int j,int arr[], int quantity[]){
        if(j>=quantity.length){
            return true;
        }

        HashSet<Integer>set = new HashSet<>();
        
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=quantity[j] && set.add(arr[i])){
                arr[i]-=quantity[j];
                if(helper(j+1,arr,quantity)){
                    return true;
                }
                arr[i]+=quantity[j];
            }
        }
        return false;
    }
    private void reverse(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int t = a[l];
            a[l++] = a[r];
            a[r--] = t;
        }
    }
}