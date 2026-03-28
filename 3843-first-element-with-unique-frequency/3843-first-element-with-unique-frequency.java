class Solution {
        public int firstUniqueFreq(int[] A) {
        Map<Integer, Integer> c1 = new HashMap<>();
        for (int a : A)
            c1.put(a, c1.getOrDefault(a, 0) + 1);
        Map<Integer, Integer> c2 = new HashMap<>();
        for (int f : c1.values())
            c2.put(f, c2.getOrDefault(f, 0) + 1);
        for (int a : A)
            if (c2.get(c1.get(a)) == 1)
                return a;
        return -1;
    }
}