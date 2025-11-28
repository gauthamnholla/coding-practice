class Solution {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        int maxLength = nums[0].length();
        
        //step (1), map with query indexes
        Map<Integer, List<Integer>>digitToQuery=new HashMap<>();
        for (int i=0;i<queries.length;i++){
            digitToQuery.putIfAbsent(queries[i][1], new ArrayList<>());
            digitToQuery.get(queries[i][1]).add(i);
        }
        //step (2) "copy" input array to store indexes with numbers
        String[][]forSorting = new String[nums.length][2];
        for (int n=0;n<nums.length;n++){
            forSorting[n][0] = nums[n];
            forSorting[n][1]=""+n;
        }
        //step (3) iterate through digits
        for (int i=0;i<maxLength;i++){
            int digit = maxLength-i-1;

            //do count sort

            //the most and least digit values in array for current digit
            int L=9;
            int R=0;
            for (int n=0;n<forSorting.length;n++){
                L=Math.min(L, forSorting[n][0].charAt(digit)-48);
                R=Math.max(R, forSorting[n][0].charAt(digit)-48);
            }

            //frequency array f of size k = R-L+1
            int[]f=new int[R-L+1];
            for (int n=0;n<forSorting.length;n++){
                f[forSorting[n][0].charAt(digit)-48-L]++;
            }

            //compute the prefix sums
            for (int n=1;n<f.length;n++){
                f[n]=f[n-1]+f[n];
            }
            //temp array for setting elements to right positions and not spoiling forSorting array.
            //(don't forget to do forSorting=digitSorted after finishing sorting for each digit)
            String[][]digitSorted=new String[forSorting.length][2];

            //according to classic count sort algorithm,
            //go backwards through temp array
            //put digitSorted[n] at index f[digitSorted[n]-L]-1.
            //also decrement f[digitSorted[n]-L]
            for (int n=digitSorted.length-1;n>=0;n--){
                digitSorted[f[forSorting[n][0].charAt(digit)-48-L]-1][0]=forSorting[n][0];
                digitSorted[f[forSorting[n][0].charAt(digit)-48-L]-1][1]=forSorting[n][1];
                f[forSorting[n][0].charAt(digit)-48-L]--;
            }
            //if any of input queries required sorting by current digit - set the answers to final result 
            if (digitToQuery.containsKey(i+1)){
                for (int queryIndex:digitToQuery.get(i+1)){
                    int[]query=queries[queryIndex];
                    res[queryIndex]=Integer.valueOf(digitSorted[query[0]-1][1]);
                }
            }
            //update forSorting to maintain it sorted
            for (int n=0;n<digitSorted.length;n++){
                forSorting[n][0]=digitSorted[n][0];
                forSorting[n][1]=digitSorted[n][1];
            }
        }

        return res;
    }
}