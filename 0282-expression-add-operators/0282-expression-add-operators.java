class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", res);

        return res;
    }

    private void dfs(String num, int target, int start, long curr_val, long last_operand, String expression, List<String> res) {
        if(start == num.length()) {
            if(curr_val == target) {
                res.add(expression);
            }
            return;
        }

        for(int i=start; i<num.length(); i++) {
            if(i>start && num.charAt(start) == '0') return;

            String curr_num = num.substring(start, i+1);
            long curr_num_val = Long.parseLong(curr_num);

            // If we are at the first number, just start the expression
            if(start == 0) {
                dfs(num, target, i+1, curr_num_val, curr_num_val, curr_num, res);
            } else {
                // Add the current number with '+'
                dfs(num, target, i+1, curr_val+curr_num_val, curr_num_val, expression+"+"+curr_num, res);

                // Add the current number with '-'
                dfs(num, target, i+1, curr_val-curr_num_val, -(curr_num_val), expression+"-"+curr_num, res);

                // Add the current number with '*'
                dfs(num, target, i+1, curr_val-last_operand+last_operand*curr_num_val, last_operand*curr_num_val, expression+"*"+curr_num, res);
            }
        }
    }
} 