/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #3
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA:Ulfeen Ayevan & Wesley Mui  
 */

/*
*  Since the complexity of each CodeBlock depends on the complexity of any CodeBlocks nested inside of it 
*  the tracer will need to use a Stack to determine the total complexity of a function. 
*  I wrote my own Stack class to act as the stack for the assignment.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

import java.util.Stack;

public class BlockStack {
	private Stack<CodeBlock> stack;
	private int top = 0;
	
	/*
	 * Constructs an BlockStack stack.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The BlockStack has been initialized.
	 * 
	 */
	public BlockStack() {
		stack = new Stack<CodeBlock>();
	}
	
	/*
	 * returns the size of the stack.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This BlockStack has been instantiated.
	 * 
	 * @return
	 * 	The int size of the stack.
	 * 
	 */
	public int size() {
		int count = 0;
		for(CodeBlock i: stack) {
			count++;
		}
		return count;
	}
	
	/*
	 * returns true if the stack is empty false if not.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This BlockStack has been instantiated.
	 * 
	 * @return
	 * 	returns true if the stack is empty false if not.
	 * 
	 */
	public boolean isEmpty() {
		return stack.empty();
	}
	
	/*
	 * Pushes a new block into the stack
	 * 
	 * @param block
	 * 		New CodeBlock block for the stack.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This BlockStack has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> This BlockStack has another block at the top with the parameter block.
	 * 
	 * @throws Exception
	 * 	catches empty stack exceptions
	 * 
	 */
	public void push(CodeBlock block) {
		try {
			stack.push(block);
		}catch (Exception e){
			System.out.println("Empty block");
		}
	}
	
	/*
	 * returns the top block at the stack.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This BlockStack has been instantiated.
	 * 
	 * @return
	 * 	returns the top block at the stack.
	 * 
	 */
	public CodeBlock peek() {
		if(stack.isEmpty())
			return null;
		return stack.peek();
	}
	
	/*
	 * removes and returns the top block at the stack.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This BlockStack has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> This top Block at the Stack has been removed.
	 * 
	 * @return
	 * 	returns the top block at the stack.
	 * 
	 */
	public CodeBlock pop() {
		CodeBlock temp = peek();
		if(temp == null)
			return null;
		stack.pop();
		return temp;
	}
}
