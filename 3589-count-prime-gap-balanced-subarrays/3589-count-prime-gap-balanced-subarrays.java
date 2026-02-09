class Solution {
    public static boolean[] prime;
    static{
        // seive will run automatically on loading class due to static block
        prime = seiveOfErasthonesis(50001);
    }
    public static boolean[] seiveOfErasthonesis(int n){
        boolean[] arr = new boolean[n+1];
        arr[0]=arr[1]=true;
        for(int i=2 ; i*i<=n ; i++){
            if(arr[i]) continue;
            for(int j=2*i ; j<=n ; j+=i){
                arr[j] = true;
            }
        }
        return arr;
    }
    public int primeSubarray(int[] nums, int k) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Deque<Integer> primes = new ArrayDeque<>();
        int st=0;
        int ans=0;
        for(int i=0 ; i<nums.length ; i++){
            if(!prime[nums[i]]){
                map.put(nums[i],map.getOrDefault(nums[i],0)+1);

                // queue is created inorder to maintain indexes of primes
                primes.add(i);
            }
            while(map.size()>0 && map.lastKey()-map.firstKey()>k){
                if(!prime[nums[st]]){
                    // character which we are deleting is a prime number 
                    // so we need to remove nums[st] from map ans qu as well
                    primes.poll();
                    
                    map.put(nums[st],map.get(nums[st])-1);

                    // if freq becomes 0 completely remove those
                    if(map.get(nums[st])==0) map.remove(nums[st]);
                }
                st++;
            }

            // now update the answer
            if(primes.size()>=2){
                int temp = primes.pollLast();

                // need atleast 2 prime in array so removed one to get another prime's index
                int secondLast = primes.peekLast();
                ans += (secondLast-st+1);
                primes.add(temp);
            }
        }
        return ans;
    }
}