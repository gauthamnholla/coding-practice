class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = image.length;
        int m = image[0].length;
        for(int i=0; i<m*n; i++){
            map.put(i, new int[]{0, 0});

        }

        for(int i=0; i<(n-2);i++){
            for(int j=0; j<(m-2); j++){

                boolean chk = true;
                int sum = 0;

                for(int l=0; l<3; l++){
                    for(int k=0; k<3; k++){
                        int x = i+l;
                        int y = j+k;
                        sum+= image[x][y];
                        if((x+1) < n && l < 2){
                          if(Math.abs(image[x+1][y] - image[x][y]) > threshold){
                            chk = false; 
                          }
                        }
                         if((y+1) < m && k < 2){
                          if(Math.abs(image[x][y+1] - image[x][y]) > threshold){
                            chk = false; 
                          }
                        }
                    }
                }


                if(chk == true){
                      for(int l=0; l<3; l++){
                    for(int k=0; k<3; k++){
                        int x = i+l;
                        int y = j+k;
                        int[] a = map.get(x*m + y);
                        a[0]+= sum/9;
                        a[1]++;
                    }
                }
                }
            }
        }


        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int[] a = map.get(i*m+j);
                if(a[1] > 0){
                    image[i][j] = a[0]/a[1];
                }
            }
        }


        return image;
    }
}