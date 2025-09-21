class MovieRentingSystem {
    // Helper class to represent a movie copy at a shop
    private static class Node {
        final int shop;
        final int movie;
        final int price;
        Node(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
    }

    // Comparator to sort Nodes by:
    // 1. Price (ascending), 2. Shop ID (ascending), 3. Movie ID (ascending)
    private static final Comparator<Node> CMP =
        (a, b) -> {
            int c = Integer.compare(a.price, b.price);
            if (c != 0) return c;
            c = Integer.compare(a.shop, b.shop);
            if (c != 0) return c;
            return Integer.compare(a.movie, b.movie);
        };

    // Available copies grouped by movie -> sorted by price, shop, movie
    private final Map<Integer, TreeSet<Node>> availableByMovie = new HashMap<>();
    // Set of all rented copies -> sorted by CMP
    private final TreeSet<Node> rentedSet = new TreeSet<>(CMP);
    // Lookup table: (shop, movie) -> Node
    private final Map<Long, Node> byPair = new HashMap<>();

    // Encode (shop, movie) into a unique long key
    private static long key(int shop, int movie) {
        return (((long) shop) << 32) ^ (movie & 0xffffffffL);
    }

    // Constructor: load all entries into available movies
    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            Node node = new Node(shop, movie, price);

            // Store lookup reference
            byPair.put(key(shop, movie), node);

            // Add into available list for this movie
            availableByMovie
                .computeIfAbsent(movie, k -> new TreeSet<>(CMP))
                .add(node);
        }
    }

    // Return up to 5 shops offering the given movie (cheapest first)
    public List<Integer> search(int movie) {
        List<Integer> ans = new ArrayList<>(5);
        TreeSet<Node> set = availableByMovie.get(movie);

        if (set == null || set.isEmpty()) return ans;

        Iterator<Node> it = set.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            ans.add(it.next().shop); // collect shop IDs
        }
        return ans;
    }

    // Rent a movie: move from available -> rented
    public void rent(int shop, int movie) {
        long k = key(shop, movie);
        Node node = byPair.get(k);
        if (node == null) return; // safety check

        // Remove from available
        TreeSet<Node> set = availableByMovie.get(movie);
        if (set != null) set.remove(node);

        // Add into rented set
        rentedSet.add(node);
    }

    // Drop a rented movie: move from rented -> available
    public void drop(int shop, int movie) {
        long k = key(shop, movie);
        Node node = byPair.get(k);
        if (node == null) return; // safety check

        // Remove from rented
        rentedSet.remove(node);

        // Add back to available
        availableByMovie
            .computeIfAbsent(movie, x -> new TreeSet<>(CMP))
            .add(node);
    }

    // Report: return up to 5 rented movies [shop, movie]
    // sorted by price, shop, movie
    public List<List<Integer>> report() {
        List<List<Integer>> ans = new ArrayList<>(5);
        Iterator<Node> it = rentedSet.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            Node n = it.next();
            ans.add(Arrays.asList(n.shop, n.movie));
        }
        return ans;
    }
}

/**
 * Example usage:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop, movie);
 * obj.drop(shop, movie);
 * List<List<Integer>> param_4 = obj.report();
 */
