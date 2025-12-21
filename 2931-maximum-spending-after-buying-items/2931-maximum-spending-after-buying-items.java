class Solution {
    public long maxSpending(int[][] values) {
        
        ArrayList<Integer> al = new ArrayList<Integer>();
        for(int i=0;i<values.length;i++){
            for(int j=0; j<values[0].length;j++)
                 al.add(values[i][j]);     
        }
        
        Collections.sort(al);
        long k=1, result=0;
        for(int a: al) result+=a*(k++);
        return result;   
        
    }
}