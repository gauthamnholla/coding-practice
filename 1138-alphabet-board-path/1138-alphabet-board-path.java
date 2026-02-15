class Solution {
    public String alphabetBoardPath(String target) {

        StringBuilder sb = new StringBuilder();

        int sr = 0;
        int sc = 0;

        for (char ch : target.toCharArray()) {

            int cr = (ch - 'a') / 5;
            int cc = (ch - 'a') % 5;

            sb.append("U".repeat(Math.max(0, sr - cr)));
            sb.append("L".repeat(Math.max(0, sc - cc)));
            sb.append("D".repeat(Math.max(0, cr - sr)));
            sb.append("R".repeat(Math.max(0, cc - sc)));

            sb.append("!");

            sr = cr;
            sc = cc;

        }

        return sb.toString();

    }
}