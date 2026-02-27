class Interleaving
{
	/* Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters in individual strings is preserved.
	*/
	/*This version is very slow for following reasons:
	  1. Because it is using recurse, same spot (x, y) are computted again and again, which is true for all recurse, because it is not memorizing. so time complexity is O(2^(m+n))
	  2. calling "substring()" will create a new string everytime, so even worse
       	  
	*/
    public static boolean interleaved(String X, String Y, String S)
    {
        // return true if we have reached end of all Strings
        if (X.length() == 0 && Y.length() == 0 && S.length() == 0) {
            return true;
        }

        // return false if we have reached the end of String S
        // but String X or Y are not empty
        if (S.length() == 0) {
            return false;
        }

        // if String X is not empty and its first character matches with
        // first character of S, recurse for remaining subString
		boolean x = false;
        if (X.length() != 0 && S.charAt(0) == X.charAt(0)) {
            x = interleaved(X.substring(1), Y, S.substring(1));
        }

        // if String Y is not empty and its first character matches with
        // first character of S, recurse for remaining subString
		boolean y = false;
        if (Y.length() != 0 && S.charAt(0) == Y.charAt(0)) {
            y = interleaved(X, Y.substring(1), S.substring(1));
        }

        return x || y;
    }

	//This is a much better version using index rather than subString()
	public static boolean interleavedUsingIndex(String X, String Y, String S) {

		if (X.length() + Y.length() != S.length()) {
			return false;
		}

		return helper(X, Y, S, 0, 0);
	}

	private static boolean helper(String X, String Y, String S,
								  int i, int j) {

		if (i == X.length() && j == Y.length()) {
			return true;
		}

		int k = i + j;

		boolean x = false;
		boolean y = false;

		if (i < X.length() && X.charAt(i) == S.charAt(k)) {
			x = helper(X, Y, S, i + 1, j);
		}

		if (j < Y.length() && Y.charAt(j) == S.charAt(k)) {
			y = helper(X, Y, S, i, j + 1);
		}

		return x || y;
	}


	//This is a version using recursive but with momo 
    public static boolean isInterleavingWithMemo(String A, String B, String C) {

        if (A.length() + B.length() != C.length()) {
            return false;
        }

        Boolean[][] memo = new Boolean[A.length()][B.length()];
        return helper(A, B, C, 0, 0, memo);
    }

    private static boolean helper(String A, String B, String C,
                                  int i, int j, Boolean[][] memo) {

        if (i == A.length() && j == B.length()) {
            return true;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int k = i + j;

        boolean result = false;

        if (i < A.length() && A.charAt(i) == C.charAt(k)) {
            result = helper(A, B, C, i + 1, j, memo);
        }

        if (!result && j < B.length() && B.charAt(j) == C.charAt(k)) {
            result = helper(A, B, C, i, j + 1, memo);
        }

        memo[i][j] = result;
        return result;
    }

	//this is dp version
    public static boolean isInterleaving(String A, String B, String C) {
        int m = A.length();
        int n = B.length();

        // Length check
        if (C.length() != m + n) {
            return false;
        }

		//using the array to memorize 
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case
        dp[0][0] = true;

        // First column (only A contributes)
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && 
                       A.charAt(i - 1) == C.charAt(i - 1);
        }

        // First row (only B contributes)
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && 
                       B.charAt(j - 1) == C.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char current = C.charAt(i + j - 1);

                // Take from A
                if (A.charAt(i - 1) == current) {
                    dp[i][j] |= dp[i - 1][j];
                }

                // Take from B
                if (B.charAt(j - 1) == current) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args)
    {
        String X = "ABC";
        String Y = "ACD";
        String S = "ACDABC";

        if (interleaved(X, Y, S)) {
            System.out.print("Given String is interleaving of X and Y");
        } else {
            System.out.print("Given String is not an interleaving of X and Y");
        }
		
		String A = "abc";
        String B = "def";
        String C = "adbcef";

        System.out.println(isInterleaving(A, B, C)); // true
    }
}
