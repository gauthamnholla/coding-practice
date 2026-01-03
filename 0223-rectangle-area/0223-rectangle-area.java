class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        //we are to find the intersection of the two rectangles 
        //so first we find the axes of each line 
        //for rect1 => x1->x2 && y1->y2
        //for rect2 => x3->x4 && y3->y4
        //now intersection (Math.min(x2,x4)-Math.max(x1,x3))*(Math.min(y2,y4)-Math.max(y1,y3)
        //we know that for (a union b)= a+b-(a intersection b)

        int intersection= Math.max(0,(Math.min(ax2,bx2)-Math.max(ax1,bx1)))*Math.max(0,(Math.min(ay2,by2)-Math.max(ay1,by1)));
        //max 0 if there is no overlap 
        int A1=(ax2-ax1)*(ay2-ay1);
        int A2=(bx2-bx1)*(by2-by1);

        return A1+A2-intersection;
    }
}