class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        String sb = String.valueOf(nums1[0]) + String.valueOf(nums2[0]);

        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1 > len2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        Set<Integer> set = new HashSet<>();
        for(int element : nums1) {
            set.add(element);
        }

        ArrayList<Integer> dups = new ArrayList<>();
        for(int element : nums2) {
            if(set.contains(element)) {
                return element;
            }
        }

        return sb.charAt(0) > sb.charAt(1) ? helperMethod_swap(sb.charAt(0), sb.charAt(1)) :
               Integer.parseInt(sb);
    }

    public int helperMethod_swap(char c1, char c2) {
        String temp = String.valueOf(c2) + String.valueOf(c1);
        return Integer.parseInt(temp);
    }

}