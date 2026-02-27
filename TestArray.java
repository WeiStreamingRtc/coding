import java.util.*;
/*
Note: Things to know about ArrayList
1. The ArrayList  it is unsynchronized and permits nulls. To synchronize it: use 
	List list = Collections.synchronizedList(new ArryList(...));
	 
2. ArrayList temp to order differently if it has members that are equal to each other. like if a list: 1 2 3 4, and 1 2 3 4 1, the order will be completely different, it will always put the equal members together.

4. 
*/

public class TestArray{

	public  static void main(String arg[]){
		
		String [] sample = {"Hank Wei","Emily Zhang","Wei James","Raftsman Cove","Sky view", " misissagua"};
		ArrayList<String> list = new ArrayList<String>();
		
		for(String str: sample){
			list.add(str);
		}
		
		System.out.println("***********************************Array list  **********************************");
		System.out.println(list);
		
		System.out.println("***********************************After sorted  **********************************");
		Collections.sort(list);
		
		System.out.println(list);
		
		
		System.out.println("***********************************After sorted 1 : reverse  **********************************");
		list.sort(new Comparator<String> (){
			@Override
			public int compare(String s1,String s2){
				return s2.compareTo(s1);
			}		
			
		});
		
		System.out.println(list);
		
		System.out.println("***********************************After sorted 2 : not reverse **********************************");
		
		list.sort(new Comparator<String> (){
			@Override
			public int compare(String s1,String s2){
				return s1.compareTo(s2);
			}		
			
		});
		
		System.out.println(list);
		
	}
}