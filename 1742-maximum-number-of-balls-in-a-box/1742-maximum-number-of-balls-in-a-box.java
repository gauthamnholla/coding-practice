class Solution {
    static int digitCount(int num){
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    public int countBalls(int lowLimit, int highLimit) {
        int[] arr = new int[46];
        int max = Integer.MIN_VALUE;
        for(int i = lowLimit; i<= highLimit; i++){
            int val = digitCount(i);
            arr[val]++;
        }
        for(int i = 0; i< arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}