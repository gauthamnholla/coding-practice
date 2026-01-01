class Solution {
    public boolean search(int[] nums, int target) {
         int pivot=pivot_with_Duplicates(nums);
            if(binary(nums,target,0,pivot)||binary(nums,target,pivot+1,nums.length-1)){
                return true;
            }
            return false;
    }

    //to search element
    static boolean binary(int[] a, int t,int s,int e) {
        while (s <= e) {
            int mid = s +( e - s ) / 2;
            if (a[mid] < t) {
                s=mid+1;
            }
            else if (a[mid] > t) {
                e=mid-1;
            }
            else if (a[mid] == t) {
                return true;
            }
        }
        return false;
    }

    //to find pivot
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