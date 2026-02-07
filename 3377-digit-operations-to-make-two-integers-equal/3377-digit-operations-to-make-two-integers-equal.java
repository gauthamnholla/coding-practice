class Solution {
    public int minOperations(int n, int m) {
        // Precompute primes using Sieve of Eratosthenes
        boolean[] isPrime = sieve();
        
        // Dijkstra's: dist[i] = min total cost to reach i
        int[] dist = new int[10001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = n;  // Starting cost is n itself
        
        // Min-heap: [current_number, current_total_cost]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{n, n});
        
        HashSet<Integer> visited = new HashSet<>();
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int num = cur[0];
            int cost = cur[1];
            
            // Skip if already visited with lower cost or if it's prime
            if (visited.contains(num) || isPrime[num]) continue;
            
            // Found target m
            if (num == m) return cost;
            
            visited.add(num);
            
            // Generate neighbors by changing each digit ±1
            StringBuilder sb = new StringBuilder(Integer.toString(num));
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                
                // Increase digit if not '9'
                if (c != '9') {
                    sb.setCharAt(i, (char)(c + 1));
                    int nextNum = Integer.parseInt(sb.toString());
                    int newCost = cost + nextNum;
                    if (!isPrime[nextNum] && dist[nextNum] > newCost) {
                        dist[nextNum] = newCost;
                        pq.add(new int[]{nextNum, newCost});
                    }
                    sb.setCharAt(i, c);
                }
                
                // Decrease digit if not '0'
                if (c != '0') {
                    sb.setCharAt(i, (char)(c - 1));
                    int nextNum = Integer.parseInt(sb.toString());
                    int newCost = cost + nextNum;
                    if (!isPrime[nextNum] && dist[nextNum] > newCost) {
                        dist[nextNum] = newCost;
                        pq.add(new int[]{nextNum, newCost});
                    }
                    sb.setCharAt(i, c);
                }
            }
        }
        
        return -1;  // No path found
    }
    
    // Sieve of Eratosthenes up to 10000 (safe limit for 4-digit numbers)
    private boolean[] sieve() {
        int MAX = 10001;
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}