class Solution {
    public int[] decimalRepresentation(int n) {
       List<Integer>list=new ArrayList<>();
       int place=1;
       while(n>0){
         int digit=n%10;
          if(digit!=0){
             list.add(place*digit);
           }
           place*=10;
           n/=10;
       }
       Collections.reverse(list);
       int arr[]=new int[list.size()];
       int index=0;
       for(int i:list){
        arr[index++]=i;
       }
       return arr;
    }
}