class Solution {
    int N = 1_00_001;
    int seg[];
    void update(int idx , int x , int low , int high , int i){
        if(low == high){
            seg[idx] = x;
            return;
        }
        int mid = low + (high - low) / 2;
        if(i <= mid){
            update(2 * idx + 1 , x , low , mid , i);
        }
        else{
            update(2 * idx + 2 , x , mid + 1 , high , i);
        }
        seg[idx] = Math.max(seg[2 * idx + 1], seg[2 * idx + 2]);
    }
    int query(int l , int r , int low , int high , int idx){ // max query
        if(l > high || r < low){
            return Integer.MIN_VALUE;
        }
        if(low >= l && high <= r){
            return seg[idx];
        }
        int mid = low + (high - low) / 2;
        int left = query(l , r , low , mid , 2 * idx + 1);
        int right = query(l , r , mid + 1 , high , 2 * idx + 2);
        return Math.max(left , right);
    }
    public int lengthOfLIS(int[] a, int k) {
        int n = a.length;
        int max = 0;
        seg = new int[4 * N];
        for(int i = 0; i < n; i++){
            int l = Math.max(0 , a[i] - k);
            int r = a[i] - 1;
            int res = query(l , r , 0 , N - 1 , 0) + 1; // search in all the possible previous elements ([l , r]) and add '1' to the max                                                                length with this previous
            max = Math.max(max , res); // update max
            update(0 , res , 0 , N - 1 , a[i]); // update segment tree's a[i]th index with res
        }
        return max;
    }
}