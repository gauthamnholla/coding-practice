class MyCircularDeque {
    int cap;
    int size;
    int[] arr;
    int front, rear;

    public MyCircularDeque(int k) {
        this.cap = k;  // Deque ka capacity set kar rahe hain
        arr = new int[k];  // Ek array banate hain jo elements ko store karega
        front = rear = -1;  // Pehle front aur rear ko -1 se initialize karte hain
        size = 0;  // Size ko 0 set karte hain
    }

    public boolean insertFront(int value) {
        if(size == cap) return false;  // Agar deque full ho, to value insert nahi kar sakte
        if(front == -1) {  // Agar pehla element insert kar rahe hain
            front = rear = 0;  // Front aur rear dono ko 0 set karte hain
        } else {
            front = (front - 1 + cap) % cap;  // Circular behavior ke liye front ko adjust karte hain
        }
        arr[front] = value;  // Value ko front pe insert karte hain
        size++;  // Size ko increase karte hain
        return true;
    }

    public boolean insertLast(int value) {
        if(size == cap) return false;  // Agar deque full ho, to value insert nahi kar sakte
        if(front == -1) {  // Agar pehla element insert kar rahe hain
            front = rear = 0;  // Front aur rear dono ko 0 set karte hain
        } else {
            rear = (rear + 1) % cap;  // Circular behavior ke liye rear ko adjust karte hain
        }
        arr[rear] = value;  // Value ko rear pe insert karte hain
        size++;  // Size ko increase karte hain
        return true;
    }

    public boolean deleteFront() {
        if(size == 0) return false;  // Agar deque empty ho, to element delete nahi kar sakte
        if(size == 1) {  // Agar ek hi element hai, to deque ko reset karte hain
            front = rear = -1;
        } else {
            front = (front + 1) % cap;  // Circular behavior ke liye front ko adjust karte hain
        }
        size--;  // Size ko decrease karte hain
        return true;
    }

    public boolean deleteLast() {
        if(size == 0) return false;  // Agar deque empty ho, to element delete nahi kar sakte
        if(size == 1) {  // Agar ek hi element hai, to deque ko reset karte hain
            front = rear = -1;
        } else {
            rear = (rear - 1 + cap) % cap;  // Circular behavior ke liye rear ko adjust karte hain
        }
        size--;  // Size ko decrease karte hain
        return true;
    }

    public int getFront() {
        return size == 0 ? -1 : arr[front];  // Agar deque empty ho, to -1 return karte hain
    }

    public int getRear() {
        return size == 0 ? -1 : arr[rear];  // Agar deque empty ho, to -1 return karte hain
    }

    public boolean isEmpty() {
        return size == 0;  // Agar size 0 ho, to deque empty hai
    }

    public boolean isFull() {
        return size == cap;  // Agar size capacity ke barabar ho, to deque full hai
    }
}