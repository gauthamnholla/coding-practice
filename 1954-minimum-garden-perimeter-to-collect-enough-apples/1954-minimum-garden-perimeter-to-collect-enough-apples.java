class Solution {
    public long minimumPerimeter(long neededApples) {
    long n = 0;
    while (2 * n * (n + 1) * (1 + 2 * n) < neededApples)
        ++n;
    return  n * 8;
}
}