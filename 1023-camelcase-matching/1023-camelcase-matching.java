class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> arr = new ArrayList<>();
        int n = queries.length, m = pattern.length();
        for (String query : queries) {
            int l = query.length();
            if (l < m) {
                arr.add(false);
                continue;
            } else if (l == m) {
                if (query.equals(pattern))
                    arr.add(true);
                else
                    arr.add(false);
                continue;
            }
            int temp = 0, isValid = 1;
            for (char c : query.toCharArray()) {
                if (temp == m) {
                    if (c > 'Z')
                        continue;
                    else {
                        isValid = 0;
                        break;
                    }
                } else if (c == pattern.charAt(temp))
                    temp++;
                else if (c > 'Z')
                    continue;
                else {
                    isValid = 0;
                    break;
                }
            }
            if (temp != m || isValid == 0)
                arr.add(false);
            else
                arr.add(true);
        }
        return arr;
    }
}