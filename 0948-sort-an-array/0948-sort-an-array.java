class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move max element (root) to end
            swap(nums, 0, i);
            // Restore heap property for reduced heap
            heapify(nums, i, 0);
        }

        return nums;
    }

    private void heapify(int[] nums, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // Check left child
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }

        // Check right child
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }

        // If root not largest, swap and continue heapifying
        if (largest != root) {
            swap(nums, root, largest);
            heapify(nums, heapSize, largest);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
