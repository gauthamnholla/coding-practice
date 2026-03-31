class Solution {
    public int prefixConnected(String[] words, int k) {

        java.util.HashMap<String, Integer> mpp = new java.util.HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= k) {
                String pref = words[i].substring(0, k);
                mpp.put(pref, mpp.getOrDefault(pref, 0) + 1);
            }
        }

        int cnt = 0;
        for (int y : mpp.values()) {
            if (y >= 2) {
                cnt++;
            }
        }
        return cnt;
    }
}