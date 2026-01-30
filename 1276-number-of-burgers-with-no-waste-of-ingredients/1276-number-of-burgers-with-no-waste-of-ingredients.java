class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        // If tomatoes are odd, impossible (since burgers need even tomatoes)
        if (tomatoSlices % 2 != 0) 
            return new ArrayList<>();

        // Solve equations
        int x = (tomatoSlices - 2 * cheeseSlices) / 2; // jumbo
        int y = cheeseSlices - x;                      // small

        // Check validity
        if (x < 0 || y < 0) 
            return new ArrayList<>();

        return Arrays.asList(x, y);
    }
}