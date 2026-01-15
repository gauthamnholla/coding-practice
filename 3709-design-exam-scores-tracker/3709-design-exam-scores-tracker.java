class ExamTracker {

    List<Long> pre = new ArrayList<>(List.of(0L));
    List<Long> times = new ArrayList<>(List.of(0L));

    void record(long time, long score) {
        pre.add(pre.get(pre.size() - 1) + score);
        times.add(time);
    }

    long totalScore(long startTime, long endTime) {
        int i = Collections.binarySearch(times, startTime);
        if (i < 0) i = -i - 1;
        int j = Collections.binarySearch(times, endTime);
        if (j < 0) j = -j - 1;
        else j++;
        return pre.get(j - 1) - pre.get(i - 1);
    }
}