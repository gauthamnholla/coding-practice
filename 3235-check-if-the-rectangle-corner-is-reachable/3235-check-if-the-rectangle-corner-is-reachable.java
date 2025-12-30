import java.math.BigDecimal;
class Solution {
    public boolean canReachCorner(int X, int Y, int[][] A) {
        int n = A.length;
        int[] f = new int[n + 2];
        
        for (int i = 0; i < n + 2; i++) {
            f[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int x = A[i][0];
            int y = A[i][1];
            int r = A[i][2];

            if (x - r <= 0 || ( y + r >= Y && x < X)) {
                f[find(f, n)] = find(f, i);
                 // System.out.println("merge1");
            }
            if(helper(x,X,y,Y, r, 0)) {
                // System.out.println("return" + x + " " + y + " " + r);
                //  System.out.println("return" + ((x - X) * (x - X) + (y - Y) * (y - Y)));
                return false; 
            }
            if(x > X && y > Y) continue; 

            if ((x + r >= X && y < Y) || y - r <= 0) {
                f[find(f, n + 1)] = find(f, i);
                 // System.out.println("merge2");
            }

            for (int j = 0; j < i; j++) {
                int x2 = A[j][0];
                int y2 = A[j][1];
                int r2 = A[j][2];

                if(x2 > X && y2 > Y) continue; 

                // if 1 out of range and 2 out of Range continue; 
                if((x + x2) / 2.0 > (double)X && (y + y2) / 2.0 > (double)Y){
                    // System.out.println("skip");
                    continue; 
                }
            

                if (helper(x,x2,y,y2, r, r2)) {
                    // System.out.println("merge3");
                    f[find(f, i)] = find(f, j);
                }
            }
        }
        // System.out.println("find(f, n): " + find(f, n));
        // System.out.println("find(f, n + 1): " + find(f, n + 1));

        return find(f, n) != find(f, n + 1);
    }

    private boolean helper(int x, int x2, int y, int y2, int r, int r2){
        BigDecimal xDiff = BigDecimal.valueOf(x).subtract(BigDecimal.valueOf(x2));
        BigDecimal yDiff = BigDecimal.valueOf(y).subtract(BigDecimal.valueOf(y2));
        BigDecimal radiusSum = BigDecimal.valueOf(r).add(BigDecimal.valueOf(r2));

        BigDecimal xDiffSquared = xDiff.multiply(xDiff);
        BigDecimal yDiffSquared = yDiff.multiply(yDiff);
        BigDecimal radiusSumSquared = radiusSum.multiply(radiusSum);

        BigDecimal distanceSquared = xDiffSquared.add(yDiffSquared);
        return distanceSquared.compareTo(radiusSumSquared) <= 0;
    }

    private int find(int[] f, int i) {
        if (f[i] != i) {
            f[i] = find(f, f[i]);
        }
        return f[i];
    }
}