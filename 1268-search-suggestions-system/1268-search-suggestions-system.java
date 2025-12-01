import java.util.*;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList<>();
        int n = products.length;

        StringBuilder prefix = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            prefix.append(c);
            String p = prefix.toString();
            int idx = lowerBound(products, p);
            List<String> list = new ArrayList<>();
            for (int j = idx; j < n && j < idx + 3; j++) {
                if (products[j].startsWith(p)) list.add(products[j]);
                else break;
            }
            ans.add(list);
        }
        return ans;
    }

    // first index in arr where arr[idx] >= target (lexicographically)
    private int lowerBound(String[] arr, String target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid].compareTo(target) < 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
