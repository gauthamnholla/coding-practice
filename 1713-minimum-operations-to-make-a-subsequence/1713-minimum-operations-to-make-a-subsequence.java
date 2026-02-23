import java.util.*;

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        
        List<Integer> sequence = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                sequence.add(map.get(num));
            }
        }
        
        int maxSubsequence = lis(sequence);
        return target.length - maxSubsequence;
    }
    
    private int lis(List<Integer> list) {
        List<Integer> max = new ArrayList<>();
        for (int num : list) {
            if (max.isEmpty() || num > max.get(max.size() - 1)) {
                max.add(num);
            } else {
                int pos = Collections.binarySearch(max, num);
                if (pos >= 0) {
                    max.set(pos, num);
                } else {
                    max.set(-(pos + 1), num);
                }
            }
        }
        return max.size();
    }
}