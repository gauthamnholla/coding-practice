class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {

        // Create an index of <word, bit index> 
        Map<String, Integer> index = new HashMap<>();
        int count = 0;

        for (List<String> fav : favoriteCompanies) {
            for (String company : fav) {
                if (!index.containsKey(company)) {
                    index.put(company, count++);
                }
            }
        }

        // Convert all the arrays to a bitset
        List<BitSet> bits = new ArrayList<>();
        for (List<String> fav : favoriteCompanies) {
            BitSet bit = new BitSet();

            for (String company : fav) {
                bit.set(index.get(company));
            }

            bits.add(bit);
        }


        List<Integer> ans = new ArrayList<>();

        // O(n^2), AND a bitset with all others, and see if the cardinality (number of set bits)
        // is still the same. If so, we found a superset.
        for (int i = 0; i < bits.size(); i++) {
            boolean foundSuperset = false;

            for (int j = 0; j < bits.size(); j++) {
                if (i == j) continue;

                BitSet and1 = (BitSet) bits.get(i).clone();
                and1.and(bits.get(j));

                if (and1.equals(bits.get(i))) {
                    foundSuperset = true;
                    break;
                }
            }

            if (!foundSuperset) {
                ans.add(i);
            }
        }

        return ans;

    }
}