import java.util.*;

/*
Give an array of numbers and another target numberm, found all the combinations that add up to the given number. like a coin change problem

Most backtracking questions has to use recursion.

*/
public class BackTrack {
	
	static int deep = 0;
    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        
        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
        ArrayList<Integer> chosen = new ArrayList<>();
        
        if(A.size() == 0) return listOfLists;
        
        // we need sorted seqn and non duplicated sequence 
        Collections.sort(A);
        // removing duplicates
        ArrayList<Integer> res = removeDuplicates(A);
        
        // backtracking function
        combHelper(res,0,B,0,listOfLists,chosen);
        
        return listOfLists;
    }
    
    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list)
    {
        ArrayList<Integer> res = new ArrayList<>(list.size());
        
        res.add(list.get(0));
        
        int p2 = 0;
        
        for(int i = 1 ; i < list.size() ; i++)
        {
            if(res.get(p2) != list.get(i))
            {
                res.add(list.get(i));
                p2++;
            }
        }
        
        return res;
    }
    
    public static void combHelper(ArrayList<Integer> givenList, int curSum , int desiredSum , int start, ArrayList<ArrayList<Integer>> listOfLists, ArrayList<Integer> chosen)
    {			
        if(curSum > desiredSum){
			deep --;
			//System.out.println("bigger deep = " + deep +", start = " + start);
			return;
		} 
		
		

        if(curSum == desiredSum)
        {
            ArrayList<Integer> newList = new ArrayList<>(chosen);
            listOfLists.add(newList);
			deep --;
			//System.out.println("found deep = " + deep +", start = " + start);
			//System.out.println("found: "  + " start = " + start + ", curSum = " + curSum + ", chosen = " + chosen);
        }
        else
        {
            for(int i = start ; i < givenList.size() ; i++)
            {
                /* This is for if each number (coisn) can only use once.
				if (chosen.contains(givenList.get(i))){
					continue;
				}
				*/
				
				chosen.add(givenList.get(i));
				deep ++;
	            
				//System.out.println("deep = " + deep +", start = " + start);
               // System.out.println("Before: " + "i = " + i + " start = " + start + ", curSum = " + curSum + ", chosen = " + chosen);
                
				combHelper(givenList,curSum + givenList.get(i), desiredSum , i ,listOfLists ,chosen);
				
                //System.out.println("After: " + "i = " + i + " start = " + start + ", curSum = " + curSum + ", chosen = " + chosen);
                
				chosen.remove(chosen.size()-1);
				
				//System.out.println("Remove: " + "i = " + i + " start = " + start + ", curSum = " + curSum + ", chosen = " + chosen);
				//System.out.println("-------------------------------");
                
            }
        }
    }
	
	public static void main(String [] arg){
		Integer[] intarr = {2,3,5,6,7};
		
		ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
		
		ArrayList<Integer> coins = new ArrayList<Integer>(Arrays.asList(intarr));
		listOfLists = combinationSum(coins,7);
        System.out.println("returned:  " +  listOfLists);		
		
	}
	
}