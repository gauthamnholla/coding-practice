class Solution {
    public int minimumPairRemoval(int[] nums) {
        List <Integer> list = new ArrayList<>();
        for(int i:nums)
        {
            list.add(i);
        }

        int count = 0;

        // Keep merging until the list becomes sorted
        while(!sorted(list))
        {
            int idx = 0;
            int minSum = Integer.MAX_VALUE;

            // Find adjacent pair with the smallest sum
            for(int i=0;i<list.size()-1;i++)
            {
                int sum = list.get(i)+list.get(i+1);
                if(sum<minSum)
                {
                    minSum = sum;
                    idx = i;
                }
            }

            // Merge the chosen pair
            int merged = list.get(idx)+list.get(idx+1);
            list.set(idx,merged);
            list.remove(idx+1);
            count++;
        }
        return count;
    }

    // Check if list is sorted
    private boolean sorted(List<Integer> list)
    {
        for(int i=1;i<list.size();i++)
        {
            if(list.get(i)<list.get(i-1))
            {
                return false;
            }
        }
        return true;
    }
}