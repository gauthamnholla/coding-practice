class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        long score = 0;
        boolean[] indices = new boolean[values.length];
        for (long i = 0; i < values.length; ) {
            if (i < 0 || i >= values.length || indices[(int) i]) break;
            indices[(int) i] = true;
            if (instructions[(int) i].equals("add")) {
                score += values[(int) i];
                i++;
            } else {
                i += values[(int) i];
            }
        }
        return score;
    }
}