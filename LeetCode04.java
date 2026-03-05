import java.util.ArrayList;
import java.util.List;
/*
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
Return true if there is a 132 pattern in nums, otherwise, return false.

Example 1:

Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:

Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
class LeetCode04 {

    //Helper class, very useful
    class Node {
        public int first = Integer.MIN_VALUE;
        public int second = Integer.MIN_VALUE;
        public int third = Integer.MIN_VALUE;

        public boolean isDone(){
            return first != Integer.MIN_VALUE && second != Integer.MIN_VALUE && third != Integer.MIN_VALUE;
        }
        public boolean add (int num) {
            if(first == Integer.MIN_VALUE){
                first = num;
                return false;
            }
            else if(second == Integer.MIN_VALUE && num > first) {
                second = num;
                return false;
            }
            else if(third == Integer.MIN_VALUE && num > first && num < second) {
                third = num;
                return true;
            }

            return false;
        }
    }
    public boolean find132pattern(int[] nums) {

        List<Node> result = new ArrayList<Node>();
        int len = nums.length;

        for(int i = 0; i < len -2; i ++){

            Node temp = new Node();

            for(Node nd: result){
                nd.add(nums[i]);
                if(nd.isDone()) return true;
            }
            temp.add(nums[i]);
            result.add(temp);

            temp = new Node();

            for(Node nd: result){
                nd.add(nums[i + 1]);
                if(nd.isDone()) return true;
            }
            temp.add(nums[i + 1]);
            result.add(temp);

            temp = new Node();

            for(Node nd: result){
                nd.add(nums[i + 2]);
                if(nd.isDone()) return true;
            }
            temp.add(nums[i +2]);
            result.add(temp);

        }

        return false;
    }

    public static void main(String [] arg){

        LeetCode04 solution = new LeetCode04();

        int[] array = {3,1,4,2};
        solution.find132pattern(array);
    }
}
