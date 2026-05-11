class Solution {
    public int finalPositionOfSnake(int n, List<String> commands) {
        int i = 0, j = 0;
        for (int k = 0; k < commands.size(); k++){
            String c = commands.get(k);
            if (c.equals("DOWN")){
                i++;
            }
            else if (c.equals("RIGHT")){
                j++;
            }
            else if (c.equals("UP")){
                i--;
            }
            else if (c.equals("LEFT")){
                j--;
            }
        }
        return (i * n) + j;
    }
}