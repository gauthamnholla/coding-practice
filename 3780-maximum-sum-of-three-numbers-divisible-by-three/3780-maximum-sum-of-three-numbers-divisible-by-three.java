class Solution {
    public int maximumSum(int[] nums) {
        int n = nums.length;
        int[] r0 = new int[3]; Arrays.fill(r0, -1);
        int[] r1 = new int[3]; Arrays.fill(r1, -1);
        int[] r2 = new int[3]; Arrays.fill(r2, -1);

        for (int i = 0; i < n; i++) {
            if (nums[i] % 3 == 0) {
                r0 = assertNum(r0, nums[i]);
            } else if (nums[i] % 3 == 1) {
                r1 = assertNum(r1, nums[i]);
            } else if (nums[i] % 3 == 2) {
                r2 = assertNum(r2, nums[i]);
            }
        }

        int total_0 = Arrays.stream(r0).sum();
        total_0 = isValid(r0) ? total_0 : -1;
        
        int total_1 = Arrays.stream(r1).sum();
        total_1 = total_1 % 3 == 0 ? total_1 : -1;
        total_1 = isValid(r1) ? total_1 : -1;
            
        int total_2 = Arrays.stream(r2).sum();
        total_2 = total_2 % 3 == 0 ? total_2 : -1;
        total_2 = isValid(r2) ? total_2 : -1;
        
        int total_012 = r0[0] + r1[0] + r2[0];
        total_012 = total_012 % 3 == 0 ? total_012 : -1;
        total_012 = 
            (r0[0] != -1 && r1[0] != -1 && r2[0] != -1) 
            ? total_012 : -1;

        int max = Math.max(Math.max(total_0, total_1),
                        Math.max(total_2, total_012));
        return max != -1 ? max : max+1;
    }

    private int[] assertNum(int[] arrOld, int num) {
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = arrOld[i];
        }
        
        if (arr[0] < num) {
            arr[2] = arr[1];
            arr[1] = arr[0];
            arr[0] = num;
        } else if (arr[1] < num) {
            arr[2] = arr[1];
            arr[1] = num;
        } else if (arr[2] < num) {
            arr[2] = num;
        }
        return arr;
    }

    private boolean isValid(int[] arr) {
        for (int num : arr) {
            if (num == -1) return false;
        }
        return true;
    }
}