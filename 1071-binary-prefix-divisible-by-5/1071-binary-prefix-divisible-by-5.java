class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> list = new ArrayList<>();
        int temp = 0;
        for (int i : nums) {
            temp = ((temp << 1) + i) % 5;
            list.add(temp == 0);
        }
        return list;
    }
}