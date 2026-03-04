/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
*/

class LeetCode01 {
    public int[] searchRange(int[] nums, int target) {

        int start, end;

        int index = nums.length /2;

        if(nums.length == 0){
            return new int[] {-1, -1};
        }
        if(nums[index] == target){
            return findRange(nums, target, index);
        }
        else if(nums[index] > target){
            index = search(nums, target, 0, index);
        }
        else{
            index = search(nums, target, index, nums.length);
        }

        if(index == -1)  return new int[] {-1, -1};
        else return findRange(nums, target, index);
    }


    public int search(int[] nums, int target,int start, int end) {

        if(start >= end){
            return -1;
        }

        int index = (start + end) / 2;

        if(nums[index] == target){
            return index;
        }
        else if(nums[index] > target){
            index = search(nums, target, start, index);
        }
        else{
            index = search(nums, target, index, end -1);
        }

        return index;
    }

    public int[] findRange(int[] nums, int target, int index){
        int temp = index;
        int start, end;

        while(nums[temp] == target){
            temp--;
        }

        start = temp + 1;

        temp = index;
        while(nums[temp] == target){
            temp++;
        }

        end = temp - 1;

        return new int[] {start, end};
    }
}
