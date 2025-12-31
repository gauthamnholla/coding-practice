class Trie {

    class Node {
        Node[] next;
        int size;
        int count;

        Node() {
            next = new Node[2];
        }
    }

    private Node root;

    Trie() {
        this.root = new Node();
    }

    void insert(int n) {
        Node curr = root;
        for (int b = 31; b >= 0; b--) {
            curr.size++;
            int thisBit = ((n & (1 << b)) == 0 ? 0 : 1);
            if (curr.next[thisBit] == null) {
                curr.next[thisBit] = new Node();
            }
            curr = curr.next[thisBit];
        }
        curr.count++;
        curr.size++;
    }

    int size() {
        return root.size;
    }

    int get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new IllegalArgumentException("Invalid index");
        }
        int result = 0;
        Node curr = root;
        for (int b = 31; b >= 0; b--) {
            int lSize = (curr.next[0] != null ? curr.next[0].size : 0);
            int rSize = (curr.next[1] != null ? curr.next[1].size : 0);
            result <<= 1;
            if (idx < lSize) {
                curr = curr.next[0];
            } else {
                curr = curr.next[1];
                idx -= lSize;
                result |= 1;
            }
        }
        return result;
    }

    boolean contains(int n) {
        Node curr = root;
        for (int b = 31; b >= 0; b--) {
            int thisBit = ((n & (1 << b)) == 0 ? 0 : 1);
            if (curr.next[thisBit] == null) {
                return false;
            }
            curr = curr.next[thisBit];
        }
        return (curr.count >= 0);
    }
}

class Solution {
    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = vals.length;
        var graph = new ArrayList<List<Integer>>();
        for (int node = 0; node < n; node++) {
            graph.add(new ArrayList<>());
        }

        for (int node = 1; node < n; node++) {
            graph.get(par[node]).add(node);
        }

        int[] xorVal = new int[n];
        calcXorVal(graph, 0, vals, 0, xorVal);

        int m = queries.length;
        List<List<Pair<Integer,Integer>>> formattedQueries = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            formattedQueries.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            formattedQueries.get(queries[i][0]).add(new Pair<>(queries[i][1], i));
        }

        int[] result = new int[m];
        answerQueries(graph, 0, xorVal, formattedQueries, result);
        return result;
    }

    private void calcXorVal(List<List<Integer>> graph, int node, int[] vals, 
        int xorUptoParent, int[] xorVal) {
        xorVal[node] = (xorUptoParent ^ vals[node]);
        for (int child: graph.get(node)) {
            calcXorVal(graph, child, vals, xorVal[node], xorVal);
        }
    }

    private Trie answerQueries(List<List<Integer>> graph, int node, int[] xorVal,
        List<List<Pair<Integer,Integer>>> formattedQueries, int[] result) {
        Trie retVal = new Trie();
        retVal.insert(xorVal[node]);
        for (int child : graph.get(node)) {
            var otherTrie = answerQueries(graph, child, xorVal, formattedQueries, result);
            retVal = merge(retVal, otherTrie);
        }
        for (Pair<Integer,Integer> query : formattedQueries.get(node)) {
            int idx = query.getKey() - 1;
            if (idx < retVal.size()) {
                result[query.getValue()] = retVal.get(idx);
            } else {
                result[query.getValue()] = -1;
            }
        }
        return retVal;
    }

    private Trie merge(Trie t1, Trie t2) {
        Trie result;
        Trie other;
        if (t1.size() < t2.size()) {
            result = t2;
            other = t1;
        } else {
            result = t1;
            other = t2;
        }
        int count = other.size();
        for (int i = 0; i < count; i++) {
            int k = other.get(i);
            if (!result.contains(k)) {
                result.insert(k);
            }
        }
        return result;
    }
}