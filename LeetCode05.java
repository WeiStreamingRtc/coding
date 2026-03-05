import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
public class LeetCode05 {

    class TreeNode {
        int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }

    public int pathSum(TreeNode root, int targetSum) {

        //Use this to remember, no need to remember the path
        Map<Long, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L, 1); // base case
        return dfs(root, 0, targetSum, prefixMap);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixMap) {
        if (node == null) {
            return 0;
        }

        currentSum += node.val;

        int count = prefixMap.getOrDefault(currentSum - targetSum, 0);

        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);

        count += dfs(node.left, currentSum, targetSum, prefixMap);
        count += dfs(node.right, currentSum, targetSum, prefixMap);

        // backtrack
        prefixMap.put(currentSum, prefixMap.get(currentSum) - 1);

        return count;
    }

    //Following are no recursive version

    /*if not static, then every State object automatically holds a reference to the outer Solution object.
    In code, you need following, if static, no such problem:
        Solution sol = new Solution();
        State s = sol.new State();
     */
    static class State {
        TreeNode node;
        long sum;
        boolean visited;

        State(TreeNode node, long sum, boolean visited) {
            this.node = node;
            this.sum = sum;
            this.visited = visited;
        }
    }

    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) return 0;

        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);

        Stack<State> stack = new Stack<>();
        stack.push(new State(root, 0, false));

        int count = 0;

        while (!stack.isEmpty()) {
            State s = stack.pop();

            if (s.node == null) continue;

            if (!s.visited) {

                long newSum = s.sum + s.node.val;

                count += prefix.getOrDefault(newSum - targetSum, 0);

                prefix.put(newSum, prefix.getOrDefault(newSum, 0) + 1);

                stack.push(new State(s.node, newSum, true));

                stack.push(new State(s.node.right, newSum, false));
                stack.push(new State(s.node.left, newSum, false));

            } else {
                prefix.put(s.sum, prefix.get(s.sum) - 1);
            }
        }

        return count;
    }
}
