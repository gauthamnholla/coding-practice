class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ng = new HashMap<>(); // Store next greater for each number
        Deque<Integer> st = new ArrayDeque<>();     // Monotonic stack

        // Step 1: Find next greater element for each number in nums2
        for (int num : nums2) {
            while (!st.isEmpty() && st.peek() < num) {
                ng.put(st.pop(), num); // Current num is next greater for stack top
            }
            st.push(num);
        }

        // Step 2: Build result for nums1 using the map
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = ng.getOrDefault(nums1[i], -1); // Default to -1 if no greater element
        }

        return res;
    }
}
