class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int totalSum = 0;
        for(int i=0;i<arr.length;i++){
            totalSum += arr[i];
        }

        if(totalSum%3!=0){
            return false;
        }

        int currentSum = 0;
        int count = 0;

        for(int i=0;i<arr.length;i++){
            currentSum += arr[i];

            if(currentSum==totalSum/3){
                count++;
                currentSum = 0;

                if(count==2 && i<arr.length-1)
                    return true;
            }
            
        }

        return false;
    }
}