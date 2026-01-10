class TrieNode {
    TrieNode[] branch = new TrieNode[26];
    Boolean isEmpty = true;
}

class Solution {
    public int minimumLengthEncoding(String[] W) {
        TrieNode trie = new TrieNode();
        trie.branch = new TrieNode[26];
        int ans = 1;
        for (String word : W) {
            TrieNode curr = trie;
            Boolean newWord = false;
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (curr.isEmpty && !newWord) ans -= word.length() - i;
                if (curr.branch[c] == null) {
                    curr.branch[c] = new TrieNode();
                    newWord = true;
                    curr.isEmpty = false;
                }
                curr = curr.branch[c];
            }
            if (newWord) ans += word.length() + 1;
        }
        return ans;
    }
}