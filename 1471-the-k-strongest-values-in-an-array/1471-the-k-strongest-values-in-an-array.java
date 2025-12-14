class Solution {
    public int[] getStrongest(int[] arr, int k) {

			if (arr.length == k) {
				return arr;
			}
			Arrays.sort(arr);

			int m = arr[(arr.length - 1) / 2];

			PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {

					return Math.abs(o1 - m) < Math.abs(o2 - m) || Math.abs(o1 - m) == Math.abs(o2 - m) && o1 < o2 ? 1
							: -1;

				};

			});

			for (int i = 0; i < arr.length; i++) {
				heap.add(arr[i]);
			}

			int[] result = new int[k];
			int i = 0;
			while (k-- > 0 && !heap.isEmpty()) {
				result[i++] = heap.poll();
			}

			return result;
	}        
    
}




