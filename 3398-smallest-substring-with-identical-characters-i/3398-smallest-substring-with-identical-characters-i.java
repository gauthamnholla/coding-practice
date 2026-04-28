class Solution {
    boolean check(String s, int numOps, int mid, char startChar){
    for(int i = 0; i < s.length(); ++i){
        if(startChar == s.charAt(i)) numOps--;
        startChar = (startChar == '0')?'1':'0';
    }
    return (numOps >= 0);
}

boolean isValid(String s, int numOps, int mid){
    if(mid == 1) return check(s, numOps, mid, '1') || check(s, numOps, mid, '0');
    int count = 0;
    for(int i = 0, last = -1; i < s.length(); ++i){
        if(s.charAt(i) == last) count++; 
        else{
            numOps -= count/(mid+1);
            last = s.charAt(i);
            count = 1;
        }
    }
    if(count > mid) numOps -= count/(mid+1);
    return (numOps >= 0);
}
    
int minLength(String s, int numOps) {
    int start = 1, end = s.length(), mid, ans = s.length();
    while(start <= end){
        mid = start + (end - start)/2;
        if(isValid(s, numOps, mid)){
            ans = mid;
            end = mid - 1;
        }else start = mid + 1;
    }
    return ans;
}
}