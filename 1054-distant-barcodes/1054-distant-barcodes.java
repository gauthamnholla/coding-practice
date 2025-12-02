class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length; // How many items total? Let's remember that.

        // 🧮 Step 1: Count those barcodes! (Like counting how many of each candy you have)
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int barcode : barcodes) {
            // For each barcode, add 1 to its count. If it's new, start counting from 0.
            freqMap.put(barcode, freqMap.getOrDefault(barcode, 0) + 1);
        }

        // 🥇 Step 2: Create a MAX HEAP (a special queue that always gives the most frequent item first)
        // We store pairs: [count, barcode_number]. The comparator (a, b) -> b[0] - a[0] sorts by count descending.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Put each barcode and its count into our max heap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new int[]{entry.getValue(), entry.getKey()}); // [count, barcode]
        }

        // 🏗️ Step 3: Build the result array (our new, nicely arranged conveyor belt)
        int[] res = new int[n];
        int index = 0; // Start at the beginning of the new conveyor belt
        int[] prev = null; // This will remember the last pile we took from (so we don't use it twice in a row)

        // Keep going until we've placed all items
        while (!maxHeap.isEmpty()) {
            // Grab the pile with the most items currently
            int[] curr = maxHeap.poll();

            // Take one item from this pile and put it on the conveyor belt
            res[index++] = curr[1];
            curr[0]--; // One less item in this pile now

            // If we used a pile previously and it still has items left...
            if (prev != null && prev[0] > 0) {
                maxHeap.offer(prev); // ...put it back in the heap so we can use it later
            }
            
            // Remember this current pile as the "previous" one for the next iteration
            prev = curr;
        }

        return res; // 🎉 Here's your perfectly arranged conveyor belt!
    }
}