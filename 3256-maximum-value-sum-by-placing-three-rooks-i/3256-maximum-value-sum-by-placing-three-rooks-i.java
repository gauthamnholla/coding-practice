class Solution {
    private static final long MIN = (long) -3e9 - 1;
    private static final int MIN_CELL = (int) -1e9 - 1;
    private static final int K = 3; //no. of rooks
    private static final int LIMIT = 2 * K * K - 3 * K + 2; //for K = 3, LIMIT = 11
                                        //in general, limit = 2k^2 - 3k + 2 = (k - 1) (2k - 1) + 1 => (k - 1) first rooks block (2k - 1) cells each in the 
                                        //worst case scenario (when there's no overlaps). we need just one more, hence + 1. that's the minimum number of cells we
                                        //must consider to handle all the cases, including the one where there's no overlap between rooks.

    private long seed = 7;

    public long maximumValueSum(int[][] board) {
        return bruteForce(board);
    }

    public long bruteForce(int[][] board) { //that could be also implemented with e.g. backtracking

        int m = board.length;
        int n = board[0].length;

        Cell[] candidates = topValues(board);
        int size = candidates.length;

        long res = MIN;

        for (int i = 0; i < size; i++) {
            final Cell rook1 = candidates[i];
            final int row1 = rook1.row;
            final int col1 = rook1.col;
            final int val1 = rook1.val;

            for (int j = i + 1; j < size; j++) {
                final Cell rook2 = candidates[j];
                final int row2 = rook2.row;
                final int col2 = rook2.col;

                if (row1 == row2 || col1 == col2) {
                    continue;
                }

                final int sum = val1 + rook2.val;

                for (int k = j + 1; k < size; k++) {
                    final Cell rook3 = candidates[k];
                    final int row3 = rook3.row;
                    final int col3 = rook3.col;

                    if (row1 == row3 || col1 == col3 || row2 == row3
                            || col2 == col3) {
                        continue;
                    }

                    long candidate = (long) sum + rook3.val;
                    if (candidate > res) {
                        res = candidate;
                    }
                }
            }
        }

        return res;
    }

    private Cell[] topValues(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        Cell[] cells = new Cell[m * n];
        int idx = 0;

        boolean[][] topRow = topRowValues(board);
        boolean[][] topCol = topColumnValues(board);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!topRow[r][c] || !topCol[r][c]) {
                    continue;
                }

                cells[idx++] = new Cell(r, c, board[r][c]);
            }
        }

        cells = Arrays.copyOf(cells, idx); //limit to actual cells only

        sort(cells);

        return Arrays.copyOf(cells, Math.min(LIMIT, idx)); //limit to our previously calculated limit (or not, if there's less cells)
    }

    private boolean[][] topRowValues(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] topValues = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            int[] indices = sort(board[row]);

            for (int i = 0; i < K; i++) { //taking 'no. of rooks' top values
                topValues[row][indices[i]] = true;
            }
        }

        return topValues;
    }

    private boolean[][] topColumnValues(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] topValues = new boolean[m][n];
        int[] colValues = new int[m];

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                colValues[row] = board[row][col];
            }

            int[] indices = sort(colValues);

            for (int i = 0; i < K; i++) { 
                topValues[indices[i]][col] = true;
            }
        }

        return topValues;
    }

    private void sort(Cell[] source) { //in-place quickselect
        int left = 0;
        int right = source.length - 1;

        int target = Math.min(source.length, LIMIT) - 1;

        while (true) {
            int pivotIdx = partition(source, left, right);

            if (pivotIdx == target) {
                return;
            }

            if (pivotIdx > target) {
                right = pivotIdx - 1;
            } else {
                left = pivotIdx + 1;
            }
        }
    }

    private int[] sort(int[] source) { //non-destructive, returns a sorted array of indices
        int n = source.length;

        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        int left = 0;
        int right = source.length - 1;

        int target = K - 1;

        while (true) {
            int pivotIdx = partition(source, indices, left, right);

            if (pivotIdx == target) {
                return indices;
            }

            if (pivotIdx > target) {
                right = pivotIdx - 1;
            } else {
                left = pivotIdx + 1;
            }
        }
    }

    private int partition(Cell[] source, int left, int right) {
        int pivotIdx = left + randomNext(right - left + 1);
        int pivot = source[pivotIdx].val;

        swap(source, pivotIdx, right);

        int i = left;

        for (int j = left; j < right; j++) {
            if (source[j].val > pivot) {
                swap(source, i++, j);
            }
        }

        swap(source, i, right);

        return i;
    }

    private int partition(int[] source, int[] indices, int left, int right) {
        int pivotIdx = left + randomNext(right - left + 1);
        int pivot = source[indices[pivotIdx]];

        swap(indices, pivotIdx, right);

        int i = left;

        for (int j = left; j < right; j++) {
            if (source[indices[j]] > pivot) {
                swap(indices, i, j);
                i++;
            }
        }

        swap(indices, i, right);

        return i;
    }

    private void swap(Cell[] source, int i, int j) {
        Cell tmp = source[i];
        source[i] = source[j];
        source[j] = tmp;
    }

    private void swap(int[] source, int i, int j) {
        int tmp = source[i];
        source[i] = source[j];
        source[j] = tmp;
    }

    private int randomNext(int bound) { //slightly faster than synchronized Random::nextInt
        seed ^= (seed << 13);
        seed ^= (seed >>> 17);
        seed ^= (seed << 5);
        return (int) (seed & 0x7fffffff) % bound; //0x7fffffff = Integer.MAX_VALUE in hex, to get rid of possible negatives
    }

    record Cell(int row, int col, int val){};
}