class Solution {
    public int brokenCalc(int startValue, int target) {
        int c = 0;
        while(target > startValue){
            if(target % 2 == 0){
                target /= 2;
            }
            else{
                target += 1;
            }
            c += 1;
        }
        // agar target equal hogya start ke to no prblm but if chota hogya  to hume extra step chaiye startvalue ko target ke equal karne ko 
        return c+(startValue-target);
    }
}