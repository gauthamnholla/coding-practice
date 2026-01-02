class Solution {
    public boolean isFascinating(int n) {
        int i = 0;
        List<Integer> arrList = new ArrayList<>();

        addToArrayList(arrList, n);
        addToArrayList(arrList, 2*n);
        addToArrayList(arrList, 3*n);
        Collections.sort(arrList);

        if(arrList.size() != 9){
            return false;
        }

        for (int j = 0; j < 9 ; j++) {
            if(arrList.get(j) != j+1){
                return false;
            }
        }
        return true;
    }

    void addToArrayList(List<Integer> arrList, int n){
        while( n > 0){
            arrList.add(n % 10);
            n /= 10;
        }
    }
}