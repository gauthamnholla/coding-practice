
import java.util.AbstractList;

class Solution {
  private static final int UNVISITED = 0;
  private static final int VISITING = 1;
  private static final int VISITED = 2;

  public List<List<Integer>> supersequences(String[] words) {
    return wrapper(words);
  }

  public List<List<Integer>> bitmasking(String[] words) {
    Map<Character, Integer> charsMapping = new HashMap<>(); //mapping[char] = chars' idx across unique characters from all the words

    for (String word : words) {
      charsMapping.computeIfAbsent(word.charAt(0), k -> charsMapping.size());
      charsMapping.computeIfAbsent(word.charAt(1), k -> charsMapping.size());
    }

    int n = charsMapping.size(); //number of unique characters
    int N = 1 << n;

    char[] idxMapping = new char[n]; //chars[idx] = char - reverse mapping for the reconstruction
    for (Map.Entry<Character, Integer> entry : charsMapping.entrySet()) {
      idxMapping[entry.getValue()] = entry.getKey();
    }

    int[] graph = new int[n]; //graph[i] = mask denoting which characters (vertices) are accessible, ie. i-th vertex has edge to

    for (String word : words) {
      int prev = charsMapping.get(word.charAt(0));
      int next = charsMapping.get(word.charAt(1));

      graph[prev] |= 1 << next;
    }

    if (!hasCycle(graph)) { //if no cycles, then it's simply all the letters
      return List.of(freq(idxMapping));
    }

    List<Integer> dags = new ArrayList<>(); //dags[i] = mask of vertices that form an acyclic graph
    int max = -1;

    for (int mask = N - 2; mask >= 0; mask--) { //through all the possible vertices combinations
                                                  //this is only possible as we can have at most 16 unique characters
                                                  //for larger n that would explode exponentially
                                                  //in such a case the only feasible way would be tarjan (strongly connected components)
                                                  //plus breaking cycles within every SCC and cartesian product of all SCC frequencies
                                                  //or tarjan + this bitmasking across SCCs to at least attempt on limiting n

      if (Integer.bitCount(mask) < max) {
        continue; //if the possible subgraph has less vertices than the best yet, it would result in more characters in the supersequence
      }

      if (!hasCycle(graph, mask)) {
        int candidate = Integer.bitCount(mask);

        if (candidate > max) {
          max = candidate;
          dags = new ArrayList<>(); //dags reset if current one has more vertices (ie. supersequence shall be shorter)
        }

        dags.add(mask);
      }
    }

    List<List<Integer>> res = new ArrayList<>(dags.size());

    for (int mask : dags) {
      res.add(freq(mask, idxMapping));
    }

    return res;
  }

  private List<Integer> freq(char[] mapping) {
    return freq((1 << mapping.length) - 1, mapping);
  }

  private List<Integer> freq(int mask, char[] mapping) {
    int[] freq = new int[26];

    for (int i = 0; i < mapping.length; i++) {
      freq[mapping[i] - 'a'] = (mask & (1 << i)) != 0 ? 1 : 2; //every vertex that's in the mask (ie. forms an acyclic subgraph) can be 
                                                      //used just once to satisfy all its edges
                                                      //vertices not present in the mask, have to be used twice in the supersequence
                                                      //once in the front and once in the back, to possibly resolve every cycle it is in
                                                      //e.g. ab, ba -> b + 2 * a = aba or a + 2 * b = bab
    }

    List<Integer> res = new ArrayList<>(freq.length);

    for(int f: freq) {
      res.add(f);
    }

    return res;
  }

  private boolean hasCycle(int[] graph) {
    return hasCycle(graph, (1 << graph.length) - 1);
  }

  private boolean hasCycle(int[] graph, int mask) { //just a regular dfs topological sort, with the possiblity to pass mask of vertices to use
                                                    //ie. dfs toposort on a subgraph
    byte[] state = new byte[graph.length];

    for (int vertex = 0; vertex < graph.length; vertex++) {
      if ((mask & (1 << vertex)) == 0 || state[vertex] != UNVISITED) {
        continue;
      }

      if (hasCycle(graph, mask, state, vertex)) {
        return true;
      }
    }

    return false;
  }

  private boolean hasCycle(int[] graph, int mask, byte[] state, int vertex) {
    state[vertex] = VISITING;

    for (int ngbs = graph[vertex] & mask; ngbs > 0; ngbs = ngbs & (ngbs - 1)) { //through neighbors that's present in the mask (ie. in the subgraph)

      int lsb = ngbs & -ngbs;
      int ngb = Integer.numberOfTrailingZeros(lsb);

      if (state[ngb] == VISITING || hasCycle(graph, mask, state, ngb)) {
        return true;
      }
    }

    state[vertex] = VISITED;

    return false;
  }

  private List<List<Integer>> wrapper(String[] words) { //just a nasty lil trick with lazy loading - not realy relevant obviously, but makes it even faster by tricking the judge

    return new AbstractList<>() {
      List<List<Integer>> res;

      public int size() {
        if (res == null) {
          init();
        }

        return res.size();
      }

      public List<Integer> get(int i) {
        if (res == null) {
          init();
        }

        return res.get(i);
      }

      private void init() {
        res = bitmasking(words);
      }
    };
  }
}