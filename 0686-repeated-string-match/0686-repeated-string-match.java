class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        // minimal repeats so that total length >= n
        int repeats = (n + m - 1) / m; // ceil(n / m)

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeats; i++) sb.append(a);
        if (sb.indexOf(b) != -1) return repeats;

        // try one more repeat (covers cases where match starts near end of a)
        sb.append(a);
        if (sb.indexOf(b) != -1) return repeats + 1;

        return -1;
    }
}
