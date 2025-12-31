import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Initialize an adjacency list to represent the graph of method invocations
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Fill the adjacency list with the provided method invocations
        for (int[] invocation : invocations) {
            adjacencyList.get(invocation[0]).add(invocation[1]);
        }

        // 'suspicious' will track whether a method has been invoked either directly or indirectly
        boolean[] suspicious = new boolean[n];

        // Stack to perform DFS starting from the given k
        Deque<Integer> stack = new LinkedList<>();
        stack.push(k);
        suspicious[k] = true;

        // DFS to mark all reachable methods invoked either directly or indirectly starting from 'k'
        while (!stack.isEmpty()) {
            int currentMethod = stack.pop();

            for (int connectedMethod : adjacencyList.get(currentMethod)) {
                if (!suspicious[connectedMethod]) {
                    suspicious[connectedMethod] = true;
                    stack.push(connectedMethod);
                }
            }
        }

        // Check if there are any nodes (methods) that could connect a suspicious node but are not suspicious themselves
        for (int method = 0; method < n; method++) {
            if (!suspicious[method]) {
                for (int connectedMethod : adjacencyList.get(method)) {
                    if (suspicious[connectedMethod]) {
                        // If we find any node connected to a suspicious node, return all nodes/methods
                        List<Integer> allMethods = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            allMethods.add(i);
                        }
                        return allMethods;
                    }
                }
            }
        }

        // If we don't find any non-suspicious method connecting a suspicious method, return the remaining non-suspicious methods
        List<Integer> remainingMethods = new ArrayList<>();
        for (int method = 0; method < n; method++) {
            if (!suspicious[method]) {
                remainingMethods.add(method);
            }
        }

        return remainingMethods;
    }
}