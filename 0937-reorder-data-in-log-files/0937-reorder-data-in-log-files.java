class Solution {
    public String[] reorderLogFiles(String[] logs) {
        PriorityQueue<Pair<String, String>> pq = new PriorityQueue<>((a,b)->a.getValue().equals(b.getValue())?a.getKey().compareTo(b.getKey()):a.getValue().compareTo(b.getValue()));
        List<String> digits = new ArrayList<>();
        // seperate first 
        for(String d : logs) {
            String spl[] = d.split(" ");
            if(isDigit(spl[1])) {
                digits.add(d);
            } else {
                pq.add(new Pair<>(d, makeString(spl)));
            }
        }
        String ans[] = new String[pq.size()+digits.size()];
        int i = 0;
        while(!pq.isEmpty()) {
            ans[i++] = pq.poll().getKey();
        }
        int j = 0;
        while(j<digits.size()) {
            ans[i++] = digits.get(j++);
        }
        return ans;

    }
    boolean isDigit(String d) {
       return Character.isDigit(d.charAt(0));
    }
    String makeString(String spl[]) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<spl.length; i++) {
            sb.append(spl[i]);
            sb.append(" ");
        }
        return sb.toString().trim(); 
    }

}