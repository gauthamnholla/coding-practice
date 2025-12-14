import java.util.*;

class Solution {
    public String arrangeWords(String text) {
        // Convert first character to lowercase
        text = text.substring(0, 1).toLowerCase() + text.substring(1);

        String[] words = text.split(" ");
        int n = words.length;

        // Store each word with its original index
        List<Pair> wordList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            wordList.add(new Pair(words[i], i));
        }

        // Sort by length, then by original index
        Collections.sort(wordList, (a, b) -> {
            if (a.word.length() != b.word.length())
                return a.word.length() - b.word.length();
            return a.index - b.index;
        });

        // Build the result sentence
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordList.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(wordList.get(i).word);
        }

        // Capitalize first character
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    // Helper class to store word with its index
    static class Pair {
        String word;
        int index;

        Pair(String w, int i) {
            word = w;
            index = i;
        }
    }
}