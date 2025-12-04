class Solution {
    public int largestInteger(int num) {
        char[] s = String.valueOf(num).toCharArray();
        List<Character> ev = new ArrayList<>(), od = new ArrayList<>();
        for (char c : s) {
            if (((c - '0') & 1) == 0) ev.add(c);
            else od.add(c);
        }
        ev.sort(Comparator.reverseOrder());
        od.sort(Comparator.reverseOrder());

        int ie = 0, io = 0;
        for (int i = 0; i < s.length; i++) {
            if (((s[i] - '0') & 1) == 0) s[i] = ev.get(ie++);
            else s[i] = od.get(io++);
        }
        return Integer.parseInt(new String(s));
    }
}
