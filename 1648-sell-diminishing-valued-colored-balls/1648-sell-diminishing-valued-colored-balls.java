class Solution {
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long res = 0;
        int mod = (int)1e9 + 7;
        long max = inventory[inventory.length - 1];
        int i = inventory.length - 1;
        while(i >= 0){
            if(orders == 0){
                break;
            }
            while(i >= 0 && inventory[i] == max){
                i--;
            }
            long min = i >= 0 ? inventory[i] : 0;
            long count = inventory.length - i - 1;
            long diff = max - min;
            long left = Math.min(diff * count, orders);
            long div = left / count;
            long rem = left % count;
            long curr = count * (long)((div * (long)(max + (max - div + 1))) / 2);
            curr += (max - div) * rem;
            orders -= left;
            max -= div;
            res += curr;
            res %= mod;
            // System.out.println(min + " : " + count + " : " + diff + " : " + left + " : " + 
            // div + " : " + rem + " : " + curr);
        }
        // System.out.println(1000000000L * 1000000000L);
        return (int) res;
    }
}