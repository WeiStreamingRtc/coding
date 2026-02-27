import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.*;
/*
Note: Things to know about HashMap
1. The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls. To synchronize it: use 
	Map m = Collections.synchronizedMap(new HashMap(...));
2. This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will rcemain constant over time.
3. This implementation provides constant-time performance for the basic operations
4. 

TreeMap (Implemented SortedMap): 
   1) A Red-Black tree based NavigableMap implementation. The map is sorted according to the natural ordering of its keys, or by a Comparator 
		  provided at map creation time, depending on which constructor is used.  
	2)  Note that this implementation is not synchronized. If multiple threads access a map concurrently, and at least one of the threads modifies
	the map structurally, it must be synchronized externally. (A structural modification is any operation that adds or deletes one or more mappings; 
	merely changing the value associated with an existing key is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the map. If no such object exists, the map should be "wrapped" using the Collections.synchronizedSortedMap method. 
	This is best done at creation time, to prevent accidental unsynchronized access to the map:
   SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));
		  
*/

public class HashTable{
	
	public static HashMap <Integer, String> buildMap (String[] strs){
		
		HashMap<Integer, String> map = new HashMap<Integer,String>();
		for(String s: strs){
			map.put(calIntFromString(s),s);			
		}
		return map;
	}
	
	public static Integer calIntFromString(String str){
		
		int i = 0;
		System.out.println("String is: " + str);
		for(char ch: str.toCharArray()){
			
			i+=ch;
			//System.out.println(" ch is: " + ch + ",  i is: " + i);
			
		}
		return i;
		
	}
	public  static void main(String arg[]){
		
		String [] sample = {"Hank Wei","Emily Zhang","Wei James","Raftsman Cove","Sky view", " misissagua"};
		HashMap <Integer, String >  map = buildMap(sample);
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		
		while (it.hasNext()){
			Map.Entry en = (Map.Entry)it.next();
			System.out.println("Key is " + en.getKey() +":  value = " + en.getValue());
			
		}
		
		String en =  map.get(711);
		System.out.println ("key is: 711 " + "val is: " + en);
		
		System.out.println("***********************************Sorting **********************************");
		System.out.println(map);
		TreeMap tree = new TreeMap(map);
		System.out.println(tree);
		
		System.out.println("***********************************Header  **********************************");
		
		SortedMap sort = tree.headMap(900);
		System.out.println(sort);
			
		System.out.println("***********************************Tail  **********************************");
		
		SortedMap tail = tree.tailMap(900);
		System.out.println(tail);
		
	}
}