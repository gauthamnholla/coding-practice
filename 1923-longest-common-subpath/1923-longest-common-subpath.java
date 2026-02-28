class Solution 
{
    public int longestCommonSubpath(int n, int[][] paths) 
    {
        /*
            Complexity Analysis:
            --------------------
            Let:
                • N = number of paths
                • M = average length of each path
                • L = minimum path length across all paths

            - Preprocessing each path with rolling hash:
                → O(M) per path, O(N * M) total.
            - For each candidate length ℓ (via binary search):
                → Compute all subpath hashes for each path → O(N * M).
                → Intersection of sets across N paths → worst-case O(N * M).
            - Binary search tries O(log L) different lengths.

            Overall Time Complexity:
                → O(N * M * log L).

            Space Complexity:
                - Hash arrays h1, h2, pow1, pow2 for each path → O(M) per path (reallocated each iteration).
                - Temporary hash sets per candidate length → O(M).
                - Overall → O(M).

            -------------------------------------------------------------------------

            Note:
                I always write my solutions in this detailed, story-like style because:
                    1. It forces me to understand *why* each step works, not just *how*.
                    2. It helps future readers (and myself) recall the reasoning process.
                    3. It documents both the dead ends and the final approach,
                       showing the evolution of thought.
                    4. It’s more than just code—it’s a guided explanation, almost like a tutorial.

                    If you find this style useful, please consider giving an upvote ❤️
                    so others can benefit from this explanation too!

            -------------------------------------------------------------------------

            Intuition:
            ----------
            At first glance, the problem looks like a graph traversal problem:
                - Cities (nodes) numbered 0..n-1
                - Paths are sequences of visited cities.

            But notice:
                • We don’t care about graph edges directly.
                • We only care about *subsequences of consecutive cities* (subpaths).
                • This is exactly like finding a "longest common substring" across multiple strings.

            Reframing:
                - Each path is like a string of symbols (city IDs).
                - We want the longest contiguous substring (subpath) that appears in ALL these "strings".

            This reduction is crucial:
                Graph → String problem → Longest Common Substring.

            -------------------------------------------------------------------------

            Key Observations:
            -----------------
            1. If a common subpath of length ℓ exists,
               then all smaller lengths < ℓ also exist.
               → This monotonic property allows **binary search on length ℓ**.

            2. To test a candidate length ℓ efficiently:
               - Extract all subpaths of length ℓ from each path.
               - Use a **rolling hash** (double hash to avoid collisions).
               - Compare via set intersection across all paths.

            3. Why rolling hash?
               - Direct substring comparison is too slow.
               - Hashing compresses each subpath into O(1) integers.
               - Rolling hash updates efficiently while sliding a window.

            -------------------------------------------------------------------------

            Approach:
            ---------
            1. Find the minimum path length among all paths → upper bound for binary search.
            2. Apply binary search on subpath length ℓ ∈ [1, minLength].
            3. For each ℓ:
                • For each path:
                    - Precompute prefix hashes and power tables.
                    - Extract all subpaths of length ℓ via rolling hash.
                    - Store them in a set.
                • Intersect these sets across all paths.
                • If intersection is non-empty → ℓ is feasible.
            4. Binary search narrows down to the largest feasible ℓ.

            -------------------------------------------------------------------------

            Example Dry Run:
            ----------------
            Suppose:
                paths = [
                    [0,1,2,3,4],
                    [2,3,4],
                    [4,0,1,2,3]
                ]

            Step 1: minLength = 3 (second path).
            Step 2: Binary search ℓ ∈ [1,3].

            ℓ = 2 → 
                - Path 1 substrings: { (0,1), (1,2), (2,3), (3,4) }
                - Path 2 substrings: { (2,3), (3,4) }
                - Path 3 substrings: { (4,0), (0,1), (1,2), (2,3) }
                - Intersection: { (2,3) }
                → feasible.

            ℓ = 3 →
                - Path 1: { (0,1,2), (1,2,3), (2,3,4) }
                - Path 2: { (2,3,4) }
                - Path 3: { (4,0,1), (0,1,2), (1,2,3) }
                - Intersection: { }
                → infeasible.

            Final Answer: ℓ = 2.

            -------------------------------------------------------------------------

            Edge Cases:
            -----------
            - All paths of length 1 → result must be 0 or 1.
            - One path much shorter than others → binary search automatically caps at minLength.
            - Identical paths → result = length of that path.
            - Completely disjoint paths → result = 0.

        */

        int minLength = Integer.MAX_VALUE ;
        for(int[] path : paths)
        {
            minLength = Math.min(minLength, path.length) ;
        }

        int res = 0 ;

        int low = 1 ;
        int high = minLength ;

        while(low <= high)
        {
            int mid = low + (high - low) / 2 ;

            if(check(mid, paths))
            {
                res = mid ;
                low = mid + 1 ;
            }
            else
            {
                high = mid - 1 ;
            }
        }

        return res ;
    }

    boolean check(int l, int[][] paths)
    {
        long mod1 = 1_000_000_007L ;
        long mod2 = 1_000_000_009L ;

        long base1 = 911382323L ;   
        long base2 = 972663749L ;

        Set<Pair> overall = null ;

        for(int[] path : paths)
        {
            int m = path.length ;

            long[] h1 = new long[m+1] ;
            long[] h2 = new long[m+1] ;
            long[] pow1 = new long[m+1] ;
            long[] pow2 = new long[m+1] ;

            pow1[0] = 1 ;
            pow2[0] = 1 ;

            for(int i = 0 ; i < m ; i++)
            {
                h1[i+1] = (h1[i] * base1 + path[i]) % mod1 ;
                h2[i+1] = (h2[i] * base2 + path[i]) % mod2 ;
                pow1[i+1] = (pow1[i] * base1) % mod1 ;
                pow2[i+1] = (pow2[i] * base2) % mod2 ;
            }

            Set<Pair> curr = new HashSet<>() ;

            for(int i = 0 ; i + l <= m ; i++)
            {
                long sub1 = (h1[i+l] - h1[i] * pow1[l] % mod1 + mod1) % mod1 ;
                long sub2 = (h2[i+l] - h2[i] * pow2[l] % mod2 + mod2) % mod2 ;
                curr.add(new Pair(sub1, sub2)) ;
            }

            if(overall == null) overall = curr ;
            else
            {
                overall.retainAll(curr) ;
                if(overall.isEmpty()) return false ;
            }
        }

        return !overall.isEmpty() ;
    }

    class Pair
    {
        long hash1 ;
        long hash2 ;

        Pair(long hash1, long hash2)
        {
            this.hash1 = hash1 ;
            this.hash2 = hash2 ;
        }

        @Override
        public boolean equals(Object o)
        {
            if(!(o instanceof Pair)) return false ;
            Pair p = (Pair) o ;
            return hash1 == p.hash1 && hash2 == p.hash2 ;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(hash1, hash2) ;
        }
    }
}