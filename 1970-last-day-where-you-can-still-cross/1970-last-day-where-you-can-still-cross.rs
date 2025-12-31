impl Solution {
    pub fn latest_day_to_cross(row: i32, col: i32, cells: Vec<Vec<i32>>) -> i32 {
        let row = row as usize;
        let col = col as usize;
        let n = row * col;
        let top = n;
        let bottom = n + 1;

        let mut parent: Vec<usize> = (0..n+2).collect();
        let mut rank = vec![0; n+2];
        let mut grid = vec![false; n];

        fn find(x: usize, parent: &mut Vec<usize>) -> usize {
            if parent[x] != x {
                parent[x] = find(parent[x], parent);
            }
            parent[x]
        }

        fn union(a: usize, b: usize, parent: &mut Vec<usize>, rank: &mut Vec<usize>) {
            let mut x = find(a, parent);
            let mut y = find(b, parent);
            if x == y { return; }
            if rank[x] < rank[y] {
                parent[x] = y;
            } else {
                parent[y] = x;
                if rank[x] == rank[y] {
                    rank[x] += 1;
                }
            }
        }

        let dr = [1, -1, 0, 0];
        let dc = [0, 0, 1, -1];

        for d in (0..n).rev() {
            let r = (cells[d][0] - 1) as usize;
            let c = (cells[d][1] - 1) as usize;
            let id = r * col + c;
            grid[id] = true;

            if r == 0 { union(id, top, &mut parent, &mut rank); }
            if r == row - 1 { union(id, bottom, &mut parent, &mut rank); }

            for k in 0..4 {
                let nr = r as isize + dr[k];
                let nc = c as isize + dc[k];
                if nr >= 0 && nr < row as isize && nc >= 0 && nc < col as isize {
                    let nid = (nr as usize) * col + (nc as usize);
                    if grid[nid] {
                        union(id, nid, &mut parent, &mut rank);
                    }
                }
            }

            if find(top, &mut parent) == find(bottom, &mut parent) {
                return d as i32;
            }
        }
        0
    }
}