class Solution {
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        int  max = 0;
        for(int miles : milestones) {
            sum += miles;
            max = Math.max(max , miles);
        } 
        if(max > (sum - max)) {
            return 2 * (sum - max) + 1;
        }
        return sum;
    }
}