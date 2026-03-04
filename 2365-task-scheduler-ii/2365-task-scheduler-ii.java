class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        long time = 0;

        HashMap<Integer, Long> map = new HashMap<>();
        
        for(int i : tasks){
            time++;

            if(map.containsKey(i)){
                time = Math.max(time, map.get(i));
            }
            
            map.put(i, time + space + 1);
        }
        return time;
    }
}