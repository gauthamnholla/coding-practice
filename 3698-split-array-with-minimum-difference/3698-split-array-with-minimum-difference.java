
class Solution {
    public long splitArray(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        long lsum = 0, rsum = 0;
      // strictly increasing from left
        while (l < n - 1 && arr[l] < arr[l + 1]) {
            lsum += arr[l];
            l++;
        }
      // strictly decreasing from right
        while (r > 0 && arr[r - 1] > arr[r]) {
            rsum += arr[r];
            r--;
        }
      // single peak element
        if (l==r){
        long option1 = Math.abs((lsum + arr[l]) - rsum);
        long option2 = Math.abs(lsum - (rsum + arr[l]));

        return Math.min(option1, option2);
        }
      // flat peak with two equal middle elements
        else if(arr[l]==arr[r]&&r-l==1){
              long option1 = Math.abs((lsum  - rsum));
      return option1;
        }
     // invalid mountain
        else  return -1;
    }
}