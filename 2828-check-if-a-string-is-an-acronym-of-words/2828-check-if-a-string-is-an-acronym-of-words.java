class Solution {
    public boolean isAcronym(List<String> words, String s) {
        if(s.length() != words.size())
            return false;

        int index = 0;
        for(int i=0; i<words.size(); i++){
            String temp = words.get(i);
            if(temp.charAt(0) != s.charAt(index))
                return false;
            index++;
        }
        return true;
        
    }
}