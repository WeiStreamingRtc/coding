class Interleaving
{
	/* Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters in individual strings is preserved.
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
    }
}
