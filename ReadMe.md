# Online reading

https://www.1point3acres.com/bbs/thread-678970-1-1.html
https://www.techinterviewhandbook.org/best-practice-questions/

# Lessons learned

Read the question closely, and always consider edge cases.

First, Come out a high level approach that is working, and communicate it clearly with the interviewer, then start working on the details.

1. Helper function / class are very helpful. Divid and conquer. 
DO NOT try to solve everything in one function. try to come out helper function to do some specific work, even you do not have time to
implement the detail, just define the interface, then move on to the main problem. Come back to the helper function when time allows.

2.Helper data stucture. Some time a helper data structure will help, either to remember the result, or make things easier.
For example the question of longest valid parentheses "(())", use an integer array of 1 and -1 to represent of "(" and ")", then count the longest 0
this helps.
For the question of "132" pattern in an integer array, using a class to contain and test if three integers are "132" pattern is very helpful.

3. Recursive
Identify if a problem has repeating problems, then use recursive solutions.

4. Do things step by step
Some questions are need to be solved by many steps, like the question of reversing a sentence, neeed to do it in 2 steps, first to reverse the whole sentence, then
reverse back word by word 



