class Solution {
    public int closestToTarget(int[] arr, int target) {
    int res = Integer.MAX_VALUE;
    Set<Integer> set = new HashSet<>();
    for(int a: arr) {
        Set<Integer> next = new HashSet<>();
        for(int p: set) {
            next.add(a & p);
        }
		next.add(a);
        for(int p: next) {
            res = Math.min(res, Math.abs(p - target));
        }
        set = next;
    }
    return res;
}
}