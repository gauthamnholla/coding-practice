class Solution {
    public int kIncreasing(int[] arr, int k) {
    int longest = 0;
    for (int i = 0; i < k; ++i) {
        List<Integer> mono = new ArrayList<>();
        for (int j = i; j < arr.length; j += k)
            if (mono.isEmpty() || mono.get(mono.size() - 1) <= arr[j])
                mono.add(arr[j]);
            else
                mono.set(upperBound(mono, arr[j]), arr[j]);
        longest += mono.size();
    }
    return arr.length - longest;
}
private int upperBound(List<Integer> mono, int val) {
    int l = 0, r = mono.size() - 1;
    while (l < r) {
        int m = (l + r) / 2;
        if (mono.get(m) <= val)
            l = m + 1;
        else
            r = m;
    }
    return l;
}
}