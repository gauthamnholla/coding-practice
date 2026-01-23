class Solution {
    private static final int MAX_INTEGER_BITS = Integer.SIZE;

    public int[] maximizeXor(int[] nums, int[][] queries) {

        /******-MY LOYAL GUARDS-**********************/
        if (queries == null || queries.length == 0)
            return new int[0];
        if (nums == null || nums.length == 0) {
            int[] ans = new int[queries.length];
            Arrays.fill(ans, -1);
            return ans;
        }

        //Left most significant bit may exist in nums or queries so make sure that we check for both
        int leftMostBitIndex = 0;
        for (int num : nums)
            leftMostBitIndex = Math.max(leftMostBitIndex, getLeftMostBitIndex(num));

        //We need the left most significant bit in the query as well
        for (int[] query : queries)
            leftMostBitIndex = Math.max(leftMostBitIndex, getLeftMostBitIndex(query[0]));

        /**-MY MINI GUARD-**/
        if (leftMostBitIndex < 0) {
            int[] ans = new int[queries.length];
            Arrays.fill(ans, 0);
            return ans;
        }

        //We will be using a wrapper class for this
        //Because after sorting the array we will miss out the original array position
        QueryWrapper[] queryWrappers = new QueryWrapper[queries.length];
        for (int i = 0; i < queries.length; i++)
            queryWrappers[i] = new QueryWrapper(queries[i][0], queries[i][1], i);

        //After we create that lets sort the queries and the nums
        //We are sorting because when we are performing the query we will make sure that we are inserting all the numbers <=m1
        Arrays.sort(nums);
        Arrays.sort(queryWrappers, Comparator.comparingInt(QueryWrapper::maxAllowed));

        //Now that we have the left most bit, we can create the trie
        Trie trie = new Trie(leftMostBitIndex);

        /**
        Now perform the main logic,
        We are traversing, for each element in queries we make sure that the trie consists of all the elements from num
        lesser that or equal to m int eh current query
        This is possible and will give the correct answer as we have sorted the queries and the nums
         */
        int[] ans = new int[queryWrappers.length];
        for (int i = 0, j = 0; i < queryWrappers.length; i++) {
            //Make sure to insert num, lesser that equal to m
            while (j < nums.length && nums[j] <= queryWrappers[i].maxAllowed()) {
                trie.insert(nums[j]);
                j++;
            }

            //Nums length will be alwaye greater as we have a guard in the top
            ans[queryWrappers[i].originalPosition()] = j == 0 ? -1 : trie.getMaxXOR(queryWrappers[i].xorWith());
        }

        return ans;
    }

    public int getLeftMostBitIndex(int num) {
        /**-MY MINI GUARD-***/
        if (num == 0)
            return -1;

        for (int bitIndex = MAX_INTEGER_BITS - 1; bitIndex >= 0; bitIndex--)
            if ((num & (1 << bitIndex)) > 0)
                return bitIndex;

        return -1;
    }
}

//We are strictly following encapsulation for all the classes below
record QueryWrapper(int xorWith, int maxAllowed, int originalPosition) {
};

class Trie {
    private final TrieNode root = new TrieNode();
    private final int leftMostBit;

    public Trie(int leftMostBit) {
        this.leftMostBit = leftMostBit;
    }

    public void insert(int num) {
        TrieNode current = root;
        for (int bitIndex = leftMostBit; bitIndex >= 0; bitIndex--) {
            int bit = ((1 << bitIndex) & num) > 0 ? 1 : 0;
            current = current.put(bit);
        }
    }

    public int getMaxXOR(int num) {
        TrieNode current = root;
        int maxXor = 0;
        for (int bitIndex = leftMostBit; bitIndex >= 0; bitIndex--) {
            int mask = 1 << bitIndex;
            int bit = (mask & num) > 0 ? 1 : 0;
            if (current.contains(bit ^ 1)) {
                current = current.get(bit ^ 1);
                maxXor |= mask;

            } else
                current = current.get(bit);
        }
        return maxXor;
    }
}

class TrieNode {
    private final TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[2];
    }

    public TrieNode put(int bit) {
        if (children[bit] == null)
            children[bit] = new TrieNode();

        return children[bit];
    }

    public TrieNode get(int bit) {
        return children[bit];
    }

    public boolean contains(int bit) {
        return children[bit] != null;
    }
}