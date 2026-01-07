/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] words, Master master) {

        List<String> lt = new ArrayList<>(Arrays.asList(words));

        for (int attempt = 0; attempt <= 30 && !lt.isEmpty(); attempt++) {
            HashMap<String,Integer> mp = new HashMap<>();

            for (String a : lt) {
                mp.put(a, count(a, lt));
            }

            lt.sort((w1, w2) -> {
                int c1 = mp.get(w1);
                int c2 = mp.get(w2);
                if (c1 != c2) {
                    return Integer.compare(c2, c1);
                }
                return w1.compareTo(w2);
            });

            String word = lt.get(0);
            int cnt = master.guess(word);

            if (cnt == 6)
                return;

            List<String> newlt = new ArrayList<>();
            for (String s : lt) {
                if (compare(word, s, cnt)) {
                    newlt.add(s);
                }
            }

            lt = newlt;
        }
    }

    boolean compare(String s1, String s2, int cnt) {
        int matches = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                matches++;
            }
        }
        return matches == cnt;
    }

    int count(String s, List<String> ls) {
        int c = 0;
        for (String st : ls) {
            if (st.equals(s)) continue;
            for (int i = 0; i < st.length(); i++) {
                if (st.charAt(i) == s.charAt(i))
                    c++;
            }
        }
        return c;
    }
}