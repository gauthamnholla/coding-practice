class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        LinkedList<Integer> res = new LinkedList<>();
        int i = 1, carry = 0;
        while( i <= arr1.length || i <= arr2.length || carry != 0){
            int a = arr1.length - i > -1 ? arr1[arr1.length - i] : 0;
            int b = arr2.length - i > -1 ? arr2[arr2.length - i] : 0;
            if( a + b - carry >= 0){
                res.addFirst((a  + b - carry) % 2);
                carry = ( a  + b - carry)  / 2;
            }else{
                res.addFirst(1);
                carry = -1;
            }
            i++;
        }
        int[] arr =  res.stream().dropWhile(x->x == 0).mapToInt(x->x).toArray();
        return arr.length > 0 ? arr : new int[]{0};
    }
}