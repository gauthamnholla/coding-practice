import java.util.*;

/**
 * LFU Cache with O(1) average get and put.
 */
public class LFUCache {
    private final int capacity;
    private int size;
    private int minFreq;

    // key -> node
    private final Map<Integer, Node> keyNode;
    // freq -> list of nodes with that freq (most recent at head)
    private final Map<Integer, DoublyLinkedList> freqListMap;

    // Node representing a cache entry
    private static class Node {
        int key;
        int val;
        int freq;
        Node prev;
        Node next;
        Node(int k, int v) { key = k; val = v; freq = 1; }
    }

    // Doubly linked list with dummy head/tail for nodes of the same frequency
    private static class DoublyLinkedList {
        Node head;
        Node tail;
        int listSize;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            listSize = 0;
        }

        // add node right after head (most recent)
        void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            listSize++;
        }

        // remove any node
        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
            listSize--;
        }

        // remove and return least recent node (tail.prev), or null if empty
        Node removeTail() {
            if (listSize == 0) return null;
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

        boolean isEmpty() {
            return listSize == 0;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        this.keyNode = new HashMap<>();
        this.freqListMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyNode.get(key);
        if (node == null) return -1;
        // Increase frequency
        increaseFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNode.containsKey(key)) {
            Node node = keyNode.get(key);
            node.val = value;
            increaseFreq(node);
            return;
        }

        // If at capacity, evict
        if (size == capacity) {
            DoublyLinkedList minList = freqListMap.get(minFreq);
            Node toRemove = minList.removeTail(); // least recently used at minFreq
            if (toRemove != null) {
                keyNode.remove(toRemove.key);
                size--;
            }
            if (minList.isEmpty()) freqListMap.remove(minFreq);
        }

        // Insert new node with freq = 1
        Node newNode = new Node(key, value);
        keyNode.put(key, newNode);
        DoublyLinkedList list = freqListMap.computeIfAbsent(1, k -> new DoublyLinkedList());
        list.addToHead(newNode);
        size++;
        minFreq = 1; // new node has freq 1, so minFreq becomes 1
    }

    // helper to increase node's frequency by 1
    private void increaseFreq(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqListMap.get(oldFreq);
        oldList.removeNode(node);
        if (oldList.isEmpty()) {
            freqListMap.remove(oldFreq);
            if (minFreq == oldFreq) minFreq++; // update minFreq
        }

        node.freq++;
        DoublyLinkedList newList = freqListMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
        newList.addToHead(node);
    }

    // Optional: quick debugging helper (not required)
    private void debugState() {
        System.out.println("size=" + size + " minFreq=" + minFreq);
        for (Map.Entry<Integer, DoublyLinkedList> e : freqListMap.entrySet()) {
            System.out.print("freq " + e.getKey() + ": ");
            DoublyLinkedList dl = e.getValue();
            Node cur = dl.head.next;
            while (cur != dl.tail) {
                System.out.print("[" + cur.key + ":" + cur.val + "] ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */