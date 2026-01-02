class Solution {
    public int findMin(int[] nums) {
        //return pivot+1 position to get minimum element
        return nums[pivot_with_Duplicates(nums) + 1];
    }

    // Find the pivot
    static int pivot_with_Duplicates(int[] arr)
    {
        int s=0;
        int e=arr.length-1;
        while(s<e)
        {
            int mid=(s+e)/2;
            if(mid <e &&  arr[mid]>arr[mid+1])
                return mid;
            if(mid >s && arr[mid]<arr[mid-1])
                return mid-1;

            //if start, end, middle are equal then skip the duplicates
            if(arr[s]==arr[mid]&&arr[mid]==arr[e])
            {
                //skip the duplicated
                //NOTE: if elements at start and end were the pivot
                //check if start is pivot
                if(arr[s]>arr[s+1])
                {
                    return s;
                }
                s++;

                //check whether end is pivot
                if(arr[e]<arr[e-1]){
                    return e-1;
                }
                e--;
            }
            //left side is sorted,  so pivot should be in right
            else if(arr[s]<arr[mid] ||(arr[s]==arr[mid]&&arr[mid]>arr[e])){
                s=mid+1;
            }
            else {
                e=mid-1;
            }
        }
        return -1;
    }
}