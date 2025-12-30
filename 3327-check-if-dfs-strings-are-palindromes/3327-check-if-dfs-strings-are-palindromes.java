class Solution {

    ArrayList<Integer> graph[];
    int size[];
    char postval[];
    int maparr[];
    int idx;

    long mod1=1000000007;
    long mod2=1000000033;

    int pow26[];
    int pow27[];

    int hash1[];
    int hash2[];

    int revhash1[];
    int revhash2[];

    public boolean[] findAnswer(int[] par, String s) {
        int n=par.length;

        graph=new ArrayList[n];
        size=new int[n];
        maparr=new int[n];
        postval=new char[n];

        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=1;i<n;i++){
            graph[par[i]].add(i);
        }

        helper(s,0);

        pow26=new int[n];
        pow27=new int[n];

        hash1=new int[n];
        hash2=new int[n];

        revhash1=new int[n];
        revhash2=new int[n];

        initPow(pow26,26l,mod1);
        initPow(pow27,27l,mod2);

        initHash(hash1,pow26,mod1,n);
        initHash(hash2,pow27,mod2,n);

        initHashRev(revhash1,pow26,mod1,n);
        initHashRev(revhash2,pow27,mod2,n);

        boolean ans[]=new boolean[n];
        for(int src=0;src<n;src++){
            ans[src]=isPalind(maparr[src]-size[src]+1,maparr[src],n);
        }
        return ans;
    }
    public int helper(String s,int src){
        size[src]++;
        for(int neigh:graph[src]){
            size[src]=size[src]+helper(s,neigh);
        }

        postval[idx]=s.charAt(src);
        maparr[src]=idx++;

        return size[src];
    }
    public void initPow(int pow[],long base,long mod){
        pow[0]=1;
        for(int i=1;i<pow.length;i++){
            pow[i]=(int)((mod+pow[i-1]*base*1l)%mod);
        }
    }
    public void initHash(int hash[],int pow[],long mod,int n){
        hash[0]=postval[0]-'a'+1;

        long p;
        for(int i=1;i<n;i++){
            hash[i]=(int)((mod+hash[i-1]*1l+(postval[i]-'a'+1)*1l*pow[i]%mod)%mod);
            
        }    
    }
    public void initHashRev(int revhash[],int pow[],long mod,int n){
        revhash[n-1]=postval[n-1]-'a'+1;

        long p;
        for(int i=n-2;i>=0;i--){
            revhash[i]=(int)((mod+revhash[i+1]*1l+(postval[i]-'a'+1)*1l*pow[n-i-1]%mod)%mod);
        }
    }
    public boolean isPalind(int left,int right,int n){
        if(left==right)
        return true;

        int h1=(int)(((mod1+hash1[right]*1l%mod1)-(left-1>=0?hash1[left-1]*1l%mod1:mod1))%mod1);
        int h2=(int)(((mod2+hash2[right]*1l%mod2)-(left-1>=0?hash2[left-1]*1l%mod2:mod2))%mod2);

        h1=(int)((h1*1l*pow26[n-1-left])%mod1);
        h2=(int)((h2*1l*pow27[n-1-left])%mod2);

        
        int revh1=(int)(((mod1+revhash1[left]*1l%mod1)-(right+1<n?revhash1[right+1]*1l%mod1:mod1))%mod1);
        int revh2=(int)(((mod2+revhash2[left]*1l%mod2)-(right+1<n?revhash2[right+1]*1l%mod2:mod2))%mod2);

        revh1=(int)((revh1*1l*pow26[right])%mod1);
        revh2=(int)((revh2*1l*pow27[right])%mod2);

        return h1==revh1&&h2==revh2;
    }
    
}