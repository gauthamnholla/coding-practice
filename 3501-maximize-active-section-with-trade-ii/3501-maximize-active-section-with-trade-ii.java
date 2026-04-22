class Solution {
    static class pair{
        int a,b,c,d,e,f;
        public pair(int a,int b){
            this.a=a;
            this.b=b;
        }
        public pair(int a,int b,int c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public pair(int a,int b,int c,int d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
         public pair(int a,int b,int c,int d,int e,int f){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
        }
    }
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] q) {
          int arr[]=new int[s.length()];
        int pr=s.charAt(0)-'0';
        int cnt=1;
        int tot=s.charAt(0)-'0';
        int ind=0;
        ArrayList<pair> ar=new ArrayList<>();
        for(int i=1;i<s.length();i++){
            tot+=(s.charAt(i)-'0');
            if(pr!=(s.charAt(i)-'0')){
                ar.add(new pair(pr,cnt,ind));
                cnt=1;
                ind=i;
                pr=s.charAt(i)-'0';
            }
            else cnt++;
        }
        ar.add(new pair(pr,cnt,ind));
        for(int i=1;i<ar.size()-1;i++){
            if(ar.get(i).a==1){
                for(int j=1;j<=ar.get(i+1).b;j++){
                arr[ar.get(i-1).c+ar.get(i-1).b+ar.get(i).b+j-1]=ar.get(i-1).b+j;
            }

            }
        }
        int next1=s.length();
        int arr2[]=new int[s.length()];
        int indd=ar.size()-1;
        for(int i=s.length()-1;i>=0;i--){
            arr2[i]=next1;
            if(ar.get(indd).c==i){
                if(s.charAt(i)=='1')next1=indd;
                indd--;
            }
        }
        int sp[][]=buildSparseTable(arr);
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<q.length;i++){
            int max=Integer.MIN_VALUE;
            if(s.charAt(q[i][0])=='0' && arr2[q[i][0]]>0&&arr2[q[i][0]]<ar.size()-1 && ar.get(arr2[q[i][0]]+1).c<=q[i][1]){
                max=Math.min(q[i][1]+1,ar.get(arr2[q[i][0]]+1).c+ar.get(arr2[q[i][0]]+1).b)-q[i][0]-ar.get(arr2[q[i][0]]).b;
            }
            if(s.charAt(q[i][0])=='1'){
                if (arr2[q[i][0]]!=s.length() && ar.get(arr2[q[i][0]]).c<=q[i][1]){
                max=Math.max(max,query(ar.get(arr2[q[i][0]]).c,q[i][1],sp));
                 
                }
            }
            else if(arr2[q[i][0]]!=s.length() && arr2[ar.get(arr2[q[i][0]]).c]!=s.length() && ar.get(arr2[ar.get(arr2[q[i][0]]).c]).c<=q[i][1]){
                 max=Math.max(max,query(ar.get(arr2[ar.get(arr2[q[i][0]]).c]).c,q[i][1],sp));
            }
            ans.add(max==Integer.MIN_VALUE?tot:max+tot);
        }
        return ans;

    }
 // Template from GFG
 // Fills lookup array lookup[][] in bottom up manner.
    public static int[][] buildSparseTable(int[] arr) {
        int n = arr.length;

        // create the 2d table
        int[][] lookup = new int[n + 1][(int)(Math.log(n) / Math.log(2)) + 1];

        // Initialize for the intervals with length 1
        for (int i = 0; i < n; i++)
            lookup[i][0] = arr[i];

        // Compute values from smaller to bigger intervals
        for (int j = 1; (1 << j) <= n; j++) {

            // Compute maximum value for all intervals with
            // size 2^j
            for (int i = 0; (i + (1 << j) - 1) < n; i++) {

                if (lookup[i][j - 1] > 
                    lookup[i + (1 << (j - 1))][j - 1])
                    lookup[i][j] = lookup[i][j - 1];
                else
                    lookup[i][j] = 
                    lookup[i + (1 << (j - 1))][j - 1];
            }
        }

        return lookup;
    }

    // Returns maxmimum of arr[L..R]
    public static int query(int L, int R, int[][] lookup) {

        // Find highest power of 2 that is smaller
        // than or equal to count of elements in range
        int j = (int)(Math.log(R - L + 1) / Math.log(2));
        // System.out.println(L+" "+R+" "+j);

        // Compute maximum of last 2^j elements with first
        // 2^j elements in range.
        if (lookup[L][j] >= lookup[R - (1 << j) + 1][j])
            return lookup[L][j];
        else
            return lookup[R - (1 << j) + 1][j];
    }
}