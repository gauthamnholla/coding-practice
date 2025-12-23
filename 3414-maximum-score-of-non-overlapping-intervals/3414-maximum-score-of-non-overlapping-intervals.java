class Solution {
    // Memoization map to store the result of states (i, k)
    HashMap<String, DpAns> map;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size(); // Number of intervals
        map = new HashMap<>();

        // Create a list of Pair objects to store intervals along with their indices
        List<Pair> dintervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dintervals.add(new Pair(i, intervals.get(i)));
        }

        // Sort intervals based on their start points (li)
        Collections.sort(dintervals);

        // Find the best solution with at most 4 intervals
        DpAns result = solve(dintervals, 0, 4);

        // Convert the result list to an array for the final output
        int[] ans = new int[result.list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = result.list.get(i);
        }
        return ans;
    }

    // Recursive function to find the best solution for picking intervals
    public DpAns solve(List<Pair> intervals, int i, int k) {
        // Base case: no intervals left to pick or out of bounds
        if (k == 0 || i >= intervals.size()) {
            return new DpAns(0, new ArrayList<>());
        }

        // Generate a unique key for the current state (i, k)
        String key = i + "*" + k;

        // If this state has already been computed, return the cached result
        if (map.containsKey(key)) {
            return map.get(key);
        }

        // Choice 1: Skip the current interval
        DpAns ans1 = solve(intervals, i + 1, k);

        // Choice 2: Take the current interval
        // Find the next interval that does not overlap with the current one
        int nextIdx = findNextIndex(intervals, intervals.get(i).interval.get(1), i + 1, intervals.size() - 1);

        // Compute the solution for taking the current interval
        DpAns ans2 = solve(intervals, nextIdx, k - 1);

        // Combine current interval's weight and indices with the solution of the rest
        DpAns ans;
        if (intervals.get(i).interval.get(2) + ans2.score > ans1.score) {
            // If taking this interval gives a better score
            long score = intervals.get(i).interval.get(2) + ans2.score;
            List<Integer> list = new ArrayList<>(ans2.list);
            list.add(intervals.get(i).idx); // Add current interval's index
            Collections.sort(list); // Ensure the indices are sorted lexicographically
            ans = new DpAns(score, list);
        } else if (intervals.get(i).interval.get(2) + ans2.score < ans1.score) {
            // If skipping gives a better score
            long score = ans1.score;
            List<Integer> list = new ArrayList<>(ans1.list);
            ans = new DpAns(score, list);
        } else {
            // If scores are tied, pick the lexicographically smaller list
            long score = ans1.score;
            List<Integer> list1 = new ArrayList<>(ans1.list);
            List<Integer> list2 = new ArrayList<>(ans2.list);
            list2.add(intervals.get(i).idx);
            Collections.sort(list2);
            List<Integer> list = lexiSmallerList(list1, list2);
            ans = new DpAns(score, list);
        }

        // Cache the result for the current state
        map.put(key, ans);
        return ans;
    }

    // Binary search to find the next non-overlapping interval
    public int findNextIndex(List<Pair> intervals, int val, int l, int r) {
        int ans = r + 1; // Default to out-of-bounds if no valid interval found
        while (l <= r) {
            int mid = (l + r) / 2;
            int temp = intervals.get(mid).interval.get(0); // Start of the mid interval
            if (temp > val) {
                ans = mid; // Found a valid interval, update the answer
                r = mid - 1; // Search in the left half
            } else {
                l = mid + 1; // Search in the right half
            }
        }
        return ans;
    }

    // Compare two lists lexicographically
    public List<Integer> lexiSmallerList(List<Integer> list1, List<Integer> list2) {
        int n1 = list1.size();
        int n2 = list2.size();
        int minLength = Math.min(n1, n2);

        // Compare element by element
        for (int i = 0; i < minLength; i++) {
            int val1 = list1.get(i);
            int val2 = list2.get(i);
            if (val1 < val2) {
                return list1;
            } else if (val1 > val2) {
                return list2;
            }
        }

        // If all elements are the same, the shorter list is lexicographically smaller
        return n1 <= n2 ? list1 : list2;
    }
}

// Helper class to store intervals with their indices
class Pair implements Comparable<Pair> {
    int idx; // Index of the interval in the input
    List<Integer> interval; // The interval details [li, ri, weighti]

    Pair(int idx, List<Integer> interval) {
        this.idx = idx;
        this.interval = interval;
    }

    // Sort pairs by their start point (li)
    public int compareTo(Pair p) {
        return this.interval.get(0) - p.interval.get(0);
    }
}

// Helper class to store DP results
class DpAns {
    long score; // Maximum score for this state
    List<Integer> list; // Indices of selected intervals

    DpAns(long score, List<Integer> list) {
        this.score = score;
        this.list = list;
    }
}