class Solution {
    static class Triplet {
        int a, b, c;

        Triplet(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) 
                return true;
            Triplet t = (Triplet) o;
            return a == t.a && b == t.b && c == t.c;
        }

        @Override
        public int hashCode() {
            int h1 = Integer.hashCode(a);
            int h2 = Integer.hashCode(b);
            int h3 = Integer.hashCode(c);
            return h1 ^ (h2 << 1) ^ (h3 << 2);
        }
    }

    // prime factors powers for 1..6
    private final int[][] factors = {
        {0, 0, 0},  // dummy
        {0, 0, 0},  // 1 = 2^0 * 3^0 * 5^0
        {1, 0, 0},  // 2 = 2^1 * 3^0 * 5^0
        {0, 1, 0},  // 3 = 2^0 * 3^1 * 5^0
        {2, 0, 0},  // 4 = 2^2 * 3^0 * 5^0
        {0, 0, 1},  // 5 = 2^0 * 3^0 * 5^1
        {1, 1, 0},  // 6 = 2^1 * 3^1 * 5^0
    };

    private Triplet fact235(long x) {
        int e2 = 0, e3 = 0, e5 = 0;
        while (x % 2 == 0) { x /= 2; ++e2; }
        while (x % 3 == 0) { x /= 3; ++e3; }
        while (x % 5 == 0) { x /= 5; ++e5; }
        if (x != 1) return null;
        return new Triplet(e2, e3, e5);
    }

    private HashMap<Triplet, Integer> gen(int[] arr) {
        HashMap<Triplet, Integer> dp = new HashMap<>();
        dp.put(new Triplet(0, 0, 0), 1);

        for (int num : arr) {
            int d2 = factors[num][0];
            int d3 = factors[num][1];
            int d5 = factors[num][2];

            HashMap<Triplet, Integer> newdp = new HashMap<>(Math.max(16, dp.size() * 3));
            for (Map.Entry<Triplet, Integer> e : dp.entrySet()) {
                Triplet s = e.getKey();
                int cnt = e.getValue();

                newdp.put(new Triplet(s.a, s.b, s.c),
                        newdp.getOrDefault(new Triplet(s.a, s.b, s.c), 0) + cnt);
                newdp.put(new Triplet(s.a + d2, s.b + d3, s.c + d5),
                        newdp.getOrDefault(new Triplet(s.a + d2, s.b + d3, s.c + d5), 0) + cnt);
                newdp.put(new Triplet(s.a - d2, s.b - d3, s.c - d5),
                        newdp.getOrDefault(new Triplet(s.a - d2, s.b - d3, s.c - d5), 0) + cnt);
            }

            dp = newdp;
        }

        return dp;
    }

    public int countSequences(int[] nums, long k) {
        Triplet target = fact235(k);
        if (target == null)
            return 0;

        HashMap<Triplet, Integer> dp = gen(nums);
        return dp.getOrDefault(target, 0);
    }
}