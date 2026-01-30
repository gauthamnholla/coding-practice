class Solution {
    public int clumsy(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(n);
        n--;
        
        int index = 0; // 0:*, 1:/, 2:+, 3:-

        while (n > 0) {

            if (index == 0) {
                stack.push(stack.pop() * n);
            } 
            else if (index == 1) {
                stack.push(stack.pop() / n);
            } 
            else if (index == 2) {
                stack.push(n);
            } 
            else {
                stack.push(-n);
            }

            index = (index + 1) % 4;
            n--;
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}