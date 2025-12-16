import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/*
    Find the gain between each point, if the gain changes, we need to add a line
    Time: O(n)
*/
class Solution {
    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length == 1) { // case for single data point only, no line needed
            return 0;
        }
        Arrays.sort(stockPrices, (a, b) -> Integer.compare(a[0], b[0])); // sort by day (chronologically)
        List<BigDecimal> diff = new ArrayList<>();
        // calculate the gains
        for (int i = 1; i < stockPrices.length; i++) {
            BigDecimal price = new BigDecimal(stockPrices[i][1]-stockPrices[i-1][1]);
            BigDecimal time = new BigDecimal(stockPrices[i][0]-stockPrices[i-1][0]);
            BigDecimal gain = price.divide(time, MathContext.DECIMAL128);
            diff.add(gain);   
            // System.out.println(gain);
        }
 
        int lines = 1; // need at least 1 line to plot a diff 
        // if gain changes, add a line
        for (int i = 1; i < diff.size(); i++) {
            if (diff.get(i).compareTo(diff.get(i-1)) == 0) {
                continue;
            } else {
                lines++;
            }
        }
        
        return lines;
    }
}