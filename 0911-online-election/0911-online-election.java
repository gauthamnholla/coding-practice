class TopVotedCandidate {
    int[] persons;
    int[] times;
    int[] prefix;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        prefix = new int[times.length];
        Map<Integer, Integer> count = new HashMap<>();
        int leader = -1;
        int maxVotes = 0;

        for (int i = 0; i < persons.length; i++) {
            int p = persons[i];
            count.put(p, count.getOrDefault(p, 0) + 1);

            if (count.get(p) >= maxVotes) {
                leader = p;
                maxVotes = count.get(p);
            }

            prefix[i] = leader;
        }
    }

    public int q(int t) {
        int s = 0, e = persons.length - 1;

        while (s < e) {
            int mid = (s + e + 1) / 2;
            if (times[mid] > t) {
                e = mid - 1;
            } else {
                s = mid;
            }
        }
        return prefix[s];
    }
}
