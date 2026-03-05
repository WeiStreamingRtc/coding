/*
Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
Each element is either an integer or a list whose elements may also be integers or other lists.

 Example 1:

Input: s = "324"
Output: 324
Explanation: You should return a NestedInteger object which contains a single integer 324.
Example 2:

Input: s = "[123,[456,[789]]]"
Output: [123,[456,[789]]]
Explanation: Return a NestedInteger object containing a nested list with 2 elements:
1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789
 */

import java.util.Collections;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 class NestedInteger {
     // Constructor initializes an empty nested list.
      public NestedInteger() {

      }


      // Constructor initializes a single integer.
      public NestedInteger(int value) {

      }
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger() {
          return true;
      }
      // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
      public Integer getInteger() { return 1;}
     // Set this NestedInteger to hold a single integer.
     public void setInteger(int value) {}

      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
      public void add(NestedInteger ni) {}
  // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return empty list if this NestedInteger holds a single integer
      public List<NestedInteger> getList() { return Collections.emptyList(); }
  }


class LeetCode03 {
    public NestedInteger deserialize(String s) {

        if(!isNested(s)){
            return decodeSimpleString(s);
        }
        //nested, need recursive
        else {
            NestedInteger result  = new NestedInteger();

            int len = s.length();
            //"[456,[789]]"
            if(s.charAt(0) == '['){
                String temp = s.substring(1, len -1);
                len = temp.length();
                int index = 0;
                //temp maybe: 789, or 456,[789]
                while(temp.charAt(index) != '['){
                    index ++;
                    if(index == len -1) break;;
                }
                if(index < len - 1){
                    //First part
                    result.add(decodeSimpleString(temp.substring(0, index)));
                    result.add(deserialize(temp.substring(index)));
                }
                else {
                    result.add(decodeSimpleString(temp));
                }

            }
            else {
                result.add(decodeSimpleString(s));
            }

            return result;
        }
    }

    public NestedInteger decodeSimpleString(String s) {

        if(s.charAt(0) == '['){
            NestedInteger result = new NestedInteger();

            String[] numbs = s.replace("[","").replace("]","").split(",");

            for(String num: numbs){
                result.add(new NestedInteger(Integer.parseInt(num)));
            }

            return result;

        }
        else {
            return new NestedInteger(Integer.parseInt(s.replace(",","")));
        }
    }

    public boolean isNested(String s) {
        int counter = 0;

        int len = s.length();
        for(int i = 0; i < len; i ++){
            if(s.charAt(i) == '['){
                counter ++;
            }
            if(counter > 0) return true;
        }

        return false;
    }
    public static void main(String [] arg){

        LeetCode03 solution = new LeetCode03();
        solution.deserialize("[123,[456,[789]]]");
    }

}