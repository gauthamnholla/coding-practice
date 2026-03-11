class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        Arrays.sort(hours);
        int n = hours.length;
        int idx = getIdx(hours, target);
        if (idx < 0){
            return 0;
        }
        // number of employees who have at least target hours
        return n - idx;
    }

    // find ceiling of target using binary search
    public int getIdx(int[] arr, int target){
        int n = arr.length;
        int l = 0, r = n - 1;
        
        while (l < r){
            int mid = l + (r-l)/2;
            if (arr[mid] < target){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // the element at l is either the ceiling or there isn't a ceiling for target in arr
        return (arr[l] >= target)? l : -1;
    }
}