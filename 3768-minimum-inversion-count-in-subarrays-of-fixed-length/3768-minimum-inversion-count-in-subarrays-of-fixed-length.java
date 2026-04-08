class Solution {
    private class FenwickTree {
        private long[] tree;
        private int n;

        FenwickTree(int n) {
            this.n = n;
            this.tree = new long[n + 1];
        }

        void update(int index, int value) {
            while (index <= n) {
                tree[index] += value;
                index += (index & -index);
            }
        }

        long getSmaller(int index) {
            index--;
            long count = 0L;

            while (index > 0) {
                count += tree[index];
                index -= (index & -index);
            }

            return count;
        }

        long getLarger(int index) {
            return getSmaller(n + 1) - getSmaller(index + 1);
        }
    }

    public long minInversionCount(int[] nums, int k) {
        int n = nums.length;

        // Coordinate Compression
        Map<Integer, Integer> rank = new HashMap<>();
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int idx = 1;

        for (int num : copy)
            if (!rank.containsKey(num))
                rank.put(num, idx++);

        FenwickTree ft = new FenwickTree(idx - 1);

        long count = 0L, min = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            
            // remove an element and then count its contribution
            if (i >= k) {
                int removeId = rank.get(nums[i - k]);

                ft.update(removeId, -1);

                count -= ft.getSmaller(removeId);
            }

            // count its contribution and then add the element
            int id = rank.get(nums[i]);

            count += ft.getLarger(id);

            ft.update(id, 1);

            if (i >= k - 1)
                min = Math.min(min, count);
        }

        return min;
    }
}