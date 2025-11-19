class Solution {
    public String maskPII(String s) {
        if (s.indexOf('@') >= 0) {
            // Email case
            s = s.toLowerCase();
            int at = s.indexOf('@');
            char first = s.charAt(0);
            char last = s.charAt(at - 1);
            String domain = s.substring(at + 1); // already lowercase
            return first + "*****" + last + "@" + domain;
        } else {
            // Phone case
            StringBuilder digits = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) digits.append(c);
            }
            String all = digits.toString();
            int n = all.length();
            String local = all.substring(n - 4);
            int countryLen = n - 10;
            
            StringBuilder res = new StringBuilder();
            if (countryLen > 0) {
                res.append('+');
                for (int i = 0; i < countryLen; i++) res.append('*');
                res.append('-');
            }
            res.append("***-***-");
            res.append(local);
            return res.toString();
        }
    }
}
