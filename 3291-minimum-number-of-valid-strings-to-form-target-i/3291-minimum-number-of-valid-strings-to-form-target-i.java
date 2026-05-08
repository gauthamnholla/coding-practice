class Trie{
    List<List<Integer>> list;
    public Trie(){
        list = new ArrayList<>();
        
        list.add(new ArrayList<>());
        for(int i=0; i<26; i++){
            list.get(0).add(-1);
        }
    }
    
    public void insert(String word){
        int curr = 0;
        for(char ch:word.toCharArray()){
            int idx = ch-'a';
            if(list.get(curr).get(idx)==-1){
                list.get(curr).set(idx, list.size());
                
                List<Integer> newl = new ArrayList<>();
                for(int i=0; i<26; i++){
                    newl.add(-1);
                }
                list.add(newl);
            }
            curr = list.get(curr).get(idx);
        }
    }
    
    public int findPrefix(String target, int start, int[] dp){
        int current = 0;
        for (int i = start; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            if (list.get(current).get(index) == -1) {
                return -1;
            }
            current = list.get(current).get(index);
            dp[i + 1] = Math.min(dp[i + 1], dp[start] + 1);
        }
        return dp[target.length()];
    }
}


class Solution{
    public int minValidStrings(String[] words, String target) {
        Trie trie = new Trie();
        for(String word:words){
            trie.insert(word);
        }
        
        int[] dp = new int[target.length()+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0]=0;
        for(int i=0; i<target.length(); i++){
            if(dp[i]!=Integer.MAX_VALUE){
                trie.findPrefix(target, i, dp);
            }
        }
        return dp[target.length()]==Integer.MAX_VALUE?-1:dp[target.length()];
    }
}