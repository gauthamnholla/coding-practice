import java.util.*;

class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int q = queries.length;

        // Prepare (nums1, nums2, sum)
        int[][] pts = new int[n][3];
        for (int i = 0; i < n; i++) {
            pts[i][0] = nums1[i];
            pts[i][1] = nums2[i];
            pts[i][2] = nums1[i] + nums2[i];
        }

        // Sort points by nums1 descending
        Arrays.sort(pts, (a, b) -> Integer.compare(b[0], a[0]));

        // Prepare queries, store original index
        int[][] qs = new int[q][3];
        for (int i = 0; i < q; i++) {
            qs[i][0] = queries[i][0]; // x
            qs[i][1] = queries[i][1]; // y
            qs[i][2] = i;            // original index
        }

        // Sort queries by x descending
        Arrays.sort(qs, (a, b) -> Integer.compare(b[0], a[0]));

        // Coordinate compress nums2
        TreeSet<Integer> ts = new TreeSet<>();
        for (int v : nums2) ts.add(v);
        for (int i = 0; i < q; i++) ts.add(qs[i][1]);

        List<Integer> uniq = new ArrayList<>(ts);
        int m = uniq.size();

        // Fenwick tree / segment tree storing max values
        int[] seg = new int[4 * m];
        Arrays.fill(seg, -1);

        // segment tree update
        final class ST {
            void update(int idx, int val, int node, int l, int r) {
                if (l == r) {
                    seg[node] = Math.max(seg[node], val);
                    return;
                }
                int mid = (l + r) >> 1;
                if (idx <= mid) update(idx, val, node * 2, l, mid);
                else update(idx, val, node * 2 + 1, mid + 1, r);
                seg[node] = Math.max(seg[node * 2], seg[node * 2 + 1]);
            }

            int query(int L, int R, int node, int l, int r) {
                if (L > r || R < l) return -1;
                if (L <= l && r <= R) return seg[node];
                int mid = (l + r) >> 1;
                return Math.max(
                        query(L, R, node * 2, l, mid),
                        query(L, R, node * 2 + 1, mid + 1, r)
                );
            }
        }
        ST st = new ST();

        // processing
        int[] ans = new int[q];
        int ptr = 0;

        for (int[] query : qs) {
            int x = query[0], y = query[1], qi = query[2];

            // Add all points with nums1 >= x
            while (ptr < n && pts[ptr][0] >= x) {
                int idx = Collections.binarySearch(uniq, pts[ptr][1]);
                st.update(idx, pts[ptr][2], 1, 0, m - 1);
                ptr++;
            }

            // Query max over nums2 >= y
            int yIdx = lowerBound(uniq, y);
            if (yIdx >= m) ans[qi] = -1;
            else ans[qi] = st.query(yIdx, m - 1, 1, 0, m - 1);
        }

        return ans;
    }

    private int lowerBound(List<Integer> list, int x) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) >> 1;
            if (list.get(m) < x) l = m + 1;
            else r = m;
        }
        return l;
    }
}
