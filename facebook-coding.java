import java.util.*;

class Solution {

  /*
    System.out.println(isPalindrome("racecar"));        // true
    System.out.println(isPalindrome("hello"));          // false
    System.out.println(isPalindrome("A man a plan a canal Panama")); // true
    System.out.println(isPalindrome(""));               // true
    System.out.println(isPalindrome(null));             // true or false (depends on requirement)
    System.out.println(isPalindrome("12321"));          // true
  */

  public boolean isPalindrome(String input) {
    int right = input.length();
    int left = 0;

    while (left <= right) {

      char leftChar = input.charAt(left);
      char rightChar = input.charAt(right);

      if (!isChar(leftChar)) {
        left++;
        continue;
      }
      if (!isChar(rightChar)) {
        right--;
        continue;
      }

      if (isChar(leftChar) && isChar(rightChar)) {
        char lowLeft = toLower(leftChar);
        char lowRight = toLower(rightChar);

        if (lowLeft == lowRight) {
          left++;
          right--;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isChar(char c) {
    // Check if the character is between 'A' and 'Z' OR between 'a' and 'z'
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

  public char toLower(char c) {
    // Check if the character is an uppercase letter (A-Z)
    if (c >= 'A' && c <= 'Z') {
      // Convert to lowercase by adding the ASCII offset
      // 'A' (65) to 'a' (97) is a difference of 32
      return (char) (c + 32);
    }

    // Return the character unchanged if it's not uppercase
    return c;
  }

}