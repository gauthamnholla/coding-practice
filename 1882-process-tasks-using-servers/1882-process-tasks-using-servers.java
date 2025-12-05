class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        //weight,index
        PriorityQueue<int []> available =  new PriorityQueue<>((a,b)->
            a[0]==b[0] ? a[1]-b[1]:a[0]-b[0]
        );
        //freetime, weight,index
        PriorityQueue<int []> used =  new PriorityQueue<>((a,b)->
            a[0]==b[0] ? (a[1]==b[1]?a[2]-b[2]:a[1]-b[1]):a[0]-b[0]
        );
        int [] ans= new int[tasks.length];
        //add all servers initially they are available
        for(int i=0;i<servers.length;i++){
            available.add(new int[]{servers[i],i});
        }

        int time =0;
        for(int i=0;i<tasks.length;i++){
            time = Math.max(time,i);
            //release the servers that are free by this time 
            while(!used.isEmpty()&&used.peek()[0]<=time){
                int []cur = used.poll();
                available.add(new int[] {cur[1],cur[2]});
            }
            //if no free servers go to that time of earliest availble time
            if(available.isEmpty()){
                int []cur = used.poll();
                time = cur[0];
                available.add(new int [] {cur[1],cur[2]});
                while(!used.isEmpty()&&used.peek()[0]<=time){
                    int [] next = used.poll();
                    available.add(new int[] {next[1],next[2]});
                }
            }
            int []res = available.poll();
            ans[i]=res[1];
            used.add(new int[] {time+tasks[i],res[0],res[1]});
        }
        return ans;
    }
}