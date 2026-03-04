import java.util.ArrayList;
import java.util.List;
/*
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]
 */
class LeetCode02 {

    class Node {
        int r;
        int c;
        Node(int row, int column){
            r = row;
            c = column;
        }
    }

    public void solve(char[][] board) {
        if(board.length < 3) return;

        List<List<Node>> regList = new ArrayList<List<Node>>();

        int rows = board.length;
        int columns = board[0].length;


        for(int i = 1; i < rows - 1; i ++) {
            for(int j = 1; j < columns -1; j ++){

                if(board[i][j] == 'O') {
                    addNode(board, regList, new Node(i, j));
                }
            }
        }

        capture(board, regList);

    }

    public void addNode(char[][]board, List<List<Node>> regList, Node node){

        boolean added = false;

        List<Node> picked = null;

        for(List<Node> nodes: regList){

            for(Node n : nodes){
                if((n.r == node.r && Math.abs(n.c - node.c) == 1) ||
                        (n.c == node.c && Math.abs(n.r - node.r) == 1)){
                    picked = nodes;
                    added = true;
                    break;
                }
            }

            if(added) break;
        }

        if(picked != null) picked.add(node);

        if(!added) {
            List<Node> temp = new ArrayList<>();
            temp.add(node);
            regList.add(temp);
        }
    }

    public boolean isRegion(char[][]board, List<Node> candi){

        int rows = board.length - 1;
        int columns = board[0].length -1;

        boolean result = true;

        for(Node n : candi){
            if((n.r == 0 || n.r == rows) || (n.c == 0 || n.c == columns)){
                result = false;
                break;
            }
        }

        return result;

    }

    public void capture(char[][]board, List<List<Node>> regList) {

        for(List<Node> nodes: regList){

            if(!isRegion(board, nodes)) continue;

            for(Node n : nodes){
                board[n.r][n.c] = 'X';
            }
        }
    }


    public static void main(String [] arg){

        LeetCode02 solution = new LeetCode02();

        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solution.solve(board);
    }
}