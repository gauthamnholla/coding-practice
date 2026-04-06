class Solution {
    public int minSwaps(int[] a, int[] f) {
        int cnt = 0, n = a.length, maxi = 0;
        java.util.Map<Integer,Integer> a_map = new java.util.HashMap<>(), f_map = new java.util.HashMap<>(), matching = new java.util.HashMap<>();

        for (int i = 0; i < n; i++) {
            if (a[i] == f[i]) {
                cnt++;
                matching.put(a[i], matching.getOrDefault(a[i], 0) + 1);
                maxi = Math.max(maxi, matching.get(a[i]));
            }
            a_map.put(a[i], a_map.getOrDefault(a[i], 0) + 1);
            f_map.put(f[i], f_map.getOrDefault(f[i], 0) + 1);
        }

        for (java.util.Map.Entry<Integer,Integer> p : a_map.entrySet()) {
            if (p.getValue() > n - f_map.getOrDefault(p.getKey(), 0)) return -1;
        }

        return Math.max(maxi, (cnt + 1) / 2);
    }
}