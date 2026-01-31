import java.util.ArrayList;
import java.util.List;

class Fancy {
    private static final int MOD = 1000000007;
    private List<Long> sequence;
    private long add;
    private long mult;

    public Fancy() {
        sequence = new ArrayList<>();
        add = 0;
        mult = 1;
    }

    public void append(int val) {
        // Apply the inverse of the current add and mult to keep the value as its original form
        long adjustedVal = ((val - add) * modInverse(mult, MOD)) % MOD;
        if (adjustedVal < 0) adjustedVal += MOD;
        sequence.add(adjustedVal);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        add = (add * m) % MOD;
        mult = (mult * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= sequence.size()) return -1;
        long result = (sequence.get(idx) * mult + add) % MOD;
        return (int) result;
    }

    // Function to compute the modular inverse using Fermat's Little Theorem
    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    // Function to compute (x^y) % mod
    private long power(long x, int y, int mod) {
        if (y == 0) return 1;
        long p = power(x, y / 2, mod) % mod;
        p = (p * p) % mod;
        return (y % 2 == 0) ? p : (x * p) % mod;
    }

    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2);   
        fancy.addAll(3);   
        fancy.append(7);   
        fancy.multAll(2);  
        System.out.println(fancy.getIndex(0));
        fancy.addAll(3);   
        fancy.append(10);  
        fancy.multAll(2);  
        System.out.println(fancy.getIndex(0)); 
        System.out.println(fancy.getIndex(1)); 
        System.out.println(fancy.getIndex(2)); 
    }
}