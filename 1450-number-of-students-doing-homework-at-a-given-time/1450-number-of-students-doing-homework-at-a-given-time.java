class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n=startTime.length;
        int count=0;
        for(int j=0;j<n;j++){
            if(startTime[j]<=queryTime && endTime[j]>=queryTime){
                count++;
            }
        }
        return count;
    }
}