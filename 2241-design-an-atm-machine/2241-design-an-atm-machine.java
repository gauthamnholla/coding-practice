class ATM {

    int notes[];
    int bank[];
    public ATM() {
        notes = new int[5];
        bank = new int[5];
        bank[0] = 20;
        bank[1] = 50;
        bank[2] = 100;
        bank[3] = 200;
        bank[4] = 500;        
    }
    
    public void deposit(int[] banknotesCount) {
        for(int i=0;i<5;i++) notes[i] += banknotesCount[i];
    }
    
    public int[] withdraw(int amount) {
        int changes[] = new int[5];
        for(int i=4;i>= 0 ;i--){
            int note = bank[i];
            int need = amount / note;
            int pay = Math.min(need,notes[i]);
            amount -= (pay * note);
            changes[i] = pay;
        }

        if(amount != 0) return new int[]{-1};
        for(int j=0;j<5;j++) notes[j] -= changes[j];
        return changes;
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */