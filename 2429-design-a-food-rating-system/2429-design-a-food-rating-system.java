class FoodRatings {
    // Inner class to store food name and rating, and compare them
    class Pair implements Comparable<Pair> {
        String foodName;
        int rating;

        // Constructor for Pair
        Pair(String name, int rate) {
            foodName = name;
            rating = rate;
        }

        // Comparison rule: higher rating first; if equal, lexicographically smaller name first
        public int compareTo(Pair obj) {
            if (this.rating != obj.rating)
                return obj.rating - this.rating;
            return this.foodName.compareTo(obj.foodName);
        }
    }

    // Map cuisine -> priority queue of foods with ratings
    Map<String, PriorityQueue<Pair>> cuisineToRatingsMap;
    // Map food name -> index in the array
    Map<String, Integer> foodNameToIndex;
    // Map food name -> its cuisine
    Map<String, String> foodToCuisine;
    // Array to store all food rating pairs
    Pair[] foodRatingsArray;

    // Constructor to initialize the data structures
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;

        cuisineToRatingsMap = new HashMap<>();
        foodNameToIndex = new HashMap<>();
        foodToCuisine = new HashMap<>();
        foodRatingsArray = new Pair[n];

        // Populate maps and priority queues
        for (int i = 0; i < n; i++) {
            // Create a Pair for each food and rating
            foodRatingsArray[i] = new Pair(foods[i], ratings[i]);

            // Map food name to its index in the array
            foodNameToIndex.put(foods[i], i);
            // Map food name to its cuisine
            foodToCuisine.put(foods[i], cuisines[i]);

            // If the cuisine is not present yet, create a new priority queue for it
            if (!cuisineToRatingsMap.containsKey(cuisines[i]))
                cuisineToRatingsMap.put(cuisines[i], new PriorityQueue<>());

            // Insert the food into the cuisine's priority queue
            PriorityQueue<Pair> temp = cuisineToRatingsMap.get(cuisines[i]);
            temp.offer(foodRatingsArray[i]);
            cuisineToRatingsMap.put(cuisines[i], temp);
        }
    }

    // Method to change the rating of a given food
    public void changeRating(String food, int newRating) {
        // Find the index of the food in the array
        int position = foodNameToIndex.get(food);

        // Remove the old rating from the cuisine's priority queue
        cuisineToRatingsMap.get(foodToCuisine.get(food)).remove(foodRatingsArray[position]);
        // Update the rating in the array
        foodRatingsArray[position].rating = newRating;
        // Insert the updated Pair back into the priority queue
        cuisineToRatingsMap.get(foodToCuisine.get(food)).offer(foodRatingsArray[position]);
    }

    // Method to get the highest rated food in a cuisine
    public String highestRated(String cuisine) {
        // Return the food name at the top of the priority queue
        return cuisineToRatingsMap.get(cuisine).peek().foodName;
    }
}


/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */