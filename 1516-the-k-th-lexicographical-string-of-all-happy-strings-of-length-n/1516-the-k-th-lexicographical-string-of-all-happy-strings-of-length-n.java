class Solution {
    public String getHappyString(int n, int k) {
        // Precompute powers of 2 up to n
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) pow2[i] = pow2[i - 1] * 2;

        int total = 3 * (n == 0 ? 0 : pow2[n - 1]); // total happy strings of length n
        if (k > total) return "";

        StringBuilder sb = new StringBuilder();
        char[] letters = new char[]{'a', 'b', 'c'};

        // choose first character
        for (int i = 0; i < 3; i++) {
            int block = pow2[n - 1]; // number of strings if we fix the first char
            if (k > block) {
                k -= block;
            } else {
                sb.append(letters[i]);
                break;
            }
        }

        // choose subsequent characters
        for (int pos = 2; pos <= n; pos++) {
            char prev = sb.charAt(sb.length() - 1);
            int remaining = n - pos; // positions left after choosing this one
            // try choices in lexicographic order excluding prev
            for (char c : letters) {
                if (c == prev) continue;
                int block = pow2[remaining]; // count for picking c here
                if (k > block) {
                    k -= block;
                } else {
                    sb.append(c);
                    break;
                }
            }
        }

        return sb.toString();
    }
}
