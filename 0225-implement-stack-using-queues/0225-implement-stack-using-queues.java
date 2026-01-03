class MyStack {
    private Deque<Integer> q;

    public MyStack() {
        q = new ArrayDeque<>();
    }
    
    public void push(int x) {
        q.offer(x);
    }
    
    public int pop() {
        return q.pollLast();
    }
    
    public int top() {
        return q.peekLast();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}