class Solution {
    public List<List<String>> mostPopularCreator(final String[] creators, final String[] ids, final int[] views) {
        final List<List<String>> result = new ArrayList<>();
        final Map<String, Creator> totalViews = new HashMap<>();
        final PriorityQueue<Creator> maxHeap = new PriorityQueue<>((a, b) -> b.totalViews() - a.totalViews());

        final int n = creators.length;

        for(int i = 0; i < n; ++i) {
            totalViews.putIfAbsent(creators[i], new Creator(creators[i], views[i], ids[i]));

            Creator creator = totalViews.get(creators[i]);

            creator.totalViews(creator.totalViews() + views[i]);

            if(views[i] > creator.maxViewsVideo() || (views[i] == creator.maxViewsVideo() && ids[i].compareTo(creator.maxViewsVideoId()) < 0))
                creator.maxViewsVideo(views[i]).maxViewsVideoId(ids[i]);
        }

        for(Creator creator : totalViews.values())
            maxHeap.offer(creator);

        int prev = maxHeap.peek().totalViews();

        while(!maxHeap.isEmpty() && prev == maxHeap.peek().totalViews()) {
            Creator creator = maxHeap.poll();

            prev = creator.totalViews();
            result.add(Arrays.asList(creator.name(), creator.maxViewsVideoId()));
        }

        return result;
    }

    private final class Creator {
        private String name;
        private int totalViews;
        private int maxViewsVideo;
        private String maxViewsVideoId;

        public Creator(final String name, final int maxViewsVideo, final String maxViewsVideoId) {
            this.name = name;
            this.totalViews = 0;
            this.maxViewsVideo = maxViewsVideo;
            this.maxViewsVideoId = maxViewsVideoId;
        }

        public Creator name(final String name) {
            this.name = name;
            return this;
        }

        public String name() {
            return this.name;
        }

        public Creator totalViews(final int totalViews) {
            this.totalViews = totalViews;
            return this;
        }

        public int totalViews() {
            return this.totalViews;
        }

        public Creator maxViewsVideo(final int maxViewsVideo) {
            this.maxViewsVideo = maxViewsVideo;
            return this;
        }

        public int maxViewsVideo() {
            return this.maxViewsVideo;
        }

        public Creator maxViewsVideoId(final String maxViewsVideoId) {
            this.maxViewsVideoId = maxViewsVideoId;
            return this;
        }

        public String maxViewsVideoId() {
            return this.maxViewsVideoId;
        }
    }
}