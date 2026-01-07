class Solution {
    public int rotatedDigits(int n) {
        final Map<Integer, Character> ROTATIONS = getRotationsMap();

        int res = 0;
        for (int i = 1; i <= n; i++) {
            final int originalNum = i;

            String rotatedNum = "";
            while (i > 0) {
                final int digit = i % 10;
                final Character rotatedDigit = ROTATIONS.get(digit);

                if (rotatedDigit == null) {
                    rotatedNum = String.valueOf(originalNum);
                    break;
                }

                rotatedNum = rotatedDigit + rotatedNum;
                
                i /= 10;
            }

            i = originalNum;

            if (Integer.parseInt(rotatedNum) != i) res++;
        }

        return res;
    }


    private Map<Integer, Character> getRotationsMap() {
        final Map<Integer, Character> map = new HashMap<>();
        map.put(0, '0');
        map.put(1, '1');
        map.put(2, '5');
        map.put(3, null);
        map.put(4, null);
        map.put(5, '2');
        map.put(6, '9');
        map.put(7, null);
        map.put(8, '8');
        map.put(9, '6');

        return map;
    }
}