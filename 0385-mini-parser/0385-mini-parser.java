class Solution {
    public NestedInteger deserialize(String s) {
        if(isInteger(s)) {
            return s.isEmpty() ? new NestedInteger() : new NestedInteger(getIntegerValue(s));
        }
        s = removeBrackets(s);
        NestedInteger list = new NestedInteger();
        for (String child: delimitedStr(s)) {
            if(child.isEmpty()) continue;
            list.add(deserialize(child));
        }
        return list;
    }

    private boolean isInteger(String str) {
        return !str.contains(",") && !str.startsWith("[") && !str.endsWith("]");
    }

    private int getIntegerValue(String s) {
        if(s.startsWith("-")) return - Integer.parseInt(s.substring(1));
        return Integer.parseInt(s);
    }

    private String removeBrackets(String s) {
        return s.substring(1, s.length()-1);
    }

    private List<String> delimitedStr(String str) {
        if(!str.contains(",")) return List.of(str);
        List<String> delimited = new ArrayList<>();
        int numBrackets = 0;
        StringBuilder sb = new StringBuilder();
        for (Character c: str.toCharArray()) {
            if(!sb.isEmpty() && numBrackets == 0 && c == ',') {
                delimited.add(sb.toString());
                sb = new StringBuilder();
            } else if(!sb.isEmpty() && c == ']' && numBrackets-1 == 0) {
                sb.append(c);
                numBrackets = 0;
                delimited.add(sb.toString());
                sb = new StringBuilder();
            } else {
                if (sb.isEmpty() && c == ',') continue; 
                sb.append(c);
                if(c == '[') numBrackets++;
                if(c == ']') numBrackets--;
            }
        }
        if(!sb.isEmpty()) delimited.add(sb.toString());
        return delimited;
    }
}