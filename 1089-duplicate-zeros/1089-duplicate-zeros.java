class Solution {
    public void duplicateZeros(int[] arr) {
        //Find the zero count in the arr
        int numOfZeros = 0;
        for(int i : arr) {
            if(i == 0) {
                numOfZeros++;
            }
        }
        //Get the index (i) of the last element that will be included in the result
        int i = arr.length - 1;
        while(numOfZeros > 0) {
            if(arr[i] == 0) {
                numOfZeros = numOfZeros - 2;
            }
            else {
                numOfZeros--;
            }
            i--;
        }
        int j;
        if(numOfZeros == -1) { //Check the scenario of the last element is zero and its duplicate will not be included in result
            arr[arr.length - 1] = 0;
            j = arr.length - 2;
        }
        else {
            j = arr.length - 1;
        }
        //Modify the arr starting from end with the right values
        while(i >= 0) { 
            if(arr[i] == 0) {
                arr[j--] = arr[i];
                arr[j--] = arr[i];
            }
            else {
                arr[j--] = arr[i];
            }
            i--;
        }
    }
}