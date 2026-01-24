class Solution {
    public int[] prevPermOpt1(int[] A) {
        int i = A.length - 2, max_ = -1;
        while(i >= 0 && A[i] <= A[i+1])
            --i;
        if(i >= 0){
			max_ = i + 1;
			for(int j=max_ + 1; j < A.length; ++j)
				if(A[max_] < A[j] && A[j] < A[i])
					max_ = j;
			int temp = A[max_];
			A[max_] = A[i];
			A[i] = temp;
		}
        return A;
    }
}