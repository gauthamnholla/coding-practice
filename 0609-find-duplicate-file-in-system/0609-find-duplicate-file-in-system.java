class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>(); // Map content → list of file paths

        // Step 1: Parse each directory info
        for (String path : paths) {
            String[] piece = path.split(" "); // First part = folder, rest = files
            for (int i = 1; i < piece.length; i++) {
                String[] inf = piece[i].split("\\("); // Split filename and content
                String fullPath = piece[0] + "/" + inf[0]; // Build full file path
                String content = inf[1].substring(0, inf[1].length() - 1); // Remove ')'

                // Step 2: Store file under its content
                map.computeIfAbsent(content, x -> new ArrayList<>()).add(fullPath);
            }
        }

        // Step 3: Add only duplicates to result
        for (List<String> pathsList : map.values()) {
            if (pathsList.size() > 1) res.add(pathsList);
        }

        return res; // Return list of duplicate file groups
    }
}
