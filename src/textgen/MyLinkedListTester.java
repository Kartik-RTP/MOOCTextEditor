/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 1 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		
		try{
			int temp = list1.remove(LONG_LIST_LENGTH);
			fail("check out of bounds");
			
		}catch(IndexOutOfBoundsException e){
			
		}
		
		try {
			int temp = longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		int c = list1.remove(0);//removing when only single element is there
		assertEquals("Remove: check b is correct ", 21, c);
		//assertEquals("Remove: check element 1 is correct ", null, list1.get(0));
		assertEquals("Remove: check size is correct ", 0, list1.size());
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{	//test 1 : add some element and see the element at the end of list
        list1.add(66);
        assertEquals("test 1 : add some element and see the element at the end of list",(Integer)66,list1.get(list1.size()-1));
        assertEquals(4,list1.size());
        
        //test2 : add null element
        try{
        	list1.add(null);
        }catch(NullPointerException e){
        	
        }
        
        
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{

	    //test1: add some element 
		list1.add(45);
		assertEquals("test1: add some element",4,list1.size() );
		//test2: call add method but pass invalid index
		try{
			list1.add(56,45);
		}catch(IndexOutOfBoundsException e){
			assertEquals("test2: call add method but pass invalid index",4,list1.size() );	
		}
		//test3: call add method but pass null value
		try{
			list1.add(null);
			
		}catch(NullPointerException e){
			assertEquals("test2: call add method but pass null value",4,list1.size() );
		}
		//test4: remove some element
		int a = list1.remove(1);
		assertEquals("test4: remove some element",3,list1.size());
		//test4: call remove index but pass invalid index
		try{
			list1.remove(56);
		}catch(IndexOutOfBoundsException e){
			assertEquals("test4: call remove index but pass invalid index",3,list1.size() );
		}
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		//test1 : insert at head
		list1.add(0,93);
		assertEquals("test1 : insert at start", (Integer)93, list1.get(0));
		assertEquals(4,list1.size);
		//test2 : insert at tail  ..
		
		list1.add(4,2234);
		assertEquals("test2 : insert at end", (Integer)2234, list1.get(4));
		assertEquals(5,list1.size);
		
		
		//test3 : insert in between
		list1.add(2,33);
		assertEquals("test3 : insert in between", (Integer)33, list1.get(2));
		assertEquals(6,list1.size);
		
		try{
			list1.add(-1,56);
		}catch(IndexOutOfBoundsException e){

		}
		
		
		try{
			list1.add(list1.size()+223,56);
		}catch(IndexOutOfBoundsException e){

		}

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		//test1: use set at start
		list1.add(633);
		list1.add(45436);
		
		list1.set(0, 22);
		assertEquals("test1: use set at start",(Integer)22,list1.get(0));
		
		//test2: use set at end
		list1.set(list1.size()-1, 33);
		assertEquals("test2: use set at end",(Integer)33,list1.get(list1.size()-1));
			
		//test3 : use set at some middle one
		list1.set(2, 456);
		assertEquals("test3 : use set at some middle one",(Integer)456,list1.get(2));
		
		try{
			list1.set(list1.size()+23,56);
		}catch(IndexOutOfBoundsException e){
	
		}
		try{
			list1.set(-34,56);
		}catch(IndexOutOfBoundsException e){

		}
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
