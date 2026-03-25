class Solution {
    public int[] rotateElements(int[] nums, int k) {
        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                positions.add(i);
                values.add(nums[i]);
            }
        }

        if (positions.size() == 0) 
            return nums;

        k %= positions.size();

        ArrayList<Integer> rotated = new ArrayList<>(values.size());
        for (int i = k; i < values.size(); i++) 
            rotated.add(values.get(i));
        for (int i = 0; i < k; i++) 
            rotated.add(values.get(i));

        for (int t = 0; t < positions.size(); t++) {
            nums[positions.get(t)] = rotated.get(t);
        }

        return nums;
    }
}