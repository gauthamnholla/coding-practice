class Solution {
    static HashSet<String> set;
    public boolean canMeasureWater(int x, int y, int target) {
        set=new HashSet<>();
        // if sum of capacity of both jugs is less than target it is impossible so we return false immediately
        if(x+y<target){
            return false;
        }
        return helper(x,y,target,0,0);
    }
    public static boolean helper(int x,int y,int target,int cur_x,int cur_y){

        // if at any point capacity of either of the jug or sum of capacity of jug become equal to target we immediately return true.

        if(cur_x==target || cur_y==target || cur_x+cur_y==target){
            return true;
        }

        // to avoid repetition we can check if current x and current y existed in set or not
        // it will detect cycle and avoid infinte recursion!!
        if(set.contains(cur_x+","+cur_y)){ 
            return false;
        }

        set.add(cur_x+","+cur_y); // we will add current capacities of x and y in the set.


        // case 1 >> we can fill any of the jug
        boolean fill_x=helper(x,y,target,x,cur_y); // filling x completely
        boolean fill_y=helper(x,y,target,cur_x,y); // filling y completely

        // case 2 >> we can empty any of the jug
        boolean emt_x=helper(x,y,target,0,cur_y);  // empty x
        boolean emt_y=helper(x,y,target,cur_x,0);  // empty y

        // case 3 >> we can fill from one jug to another till other completely fills

        int x_req=Math.min(cur_y,x-cur_x); // first check if y has enough to completely fill x
        boolean tfr_x=helper(x,y,target,cur_x+x_req,cur_y-x_req); // transfer from y till x is filled

        int y_req=Math.min(cur_x,y-cur_y); // similarly check if x has enough to completely fill y
        boolean tfr_y=helper(x,y,target,cur_x-y_req,cur_y+y_req); // transfer from x till y is filled

        return ( fill_x || fill_y || emt_x || emt_y || tfr_x || tfr_y); // if any of the condition holds true we will return true;
    }
}