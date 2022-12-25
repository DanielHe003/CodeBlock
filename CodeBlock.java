/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #3
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA:Ulfeen Ayevan & Wesley Mui  
 */

/*
*  CodeBlock describes a nested block of code. There are different types of code blocks, so I created a static 
*  final array of six String variables named BLOCK_TYPES to enumerate the types of blocks available for nesting. 
*  "def", "for", "while", "if", "elif", "else". I also declared six static final int variables corresponding to 
*  the indices of the BLOCK_TYPE array: DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELIF = 4, ELSE = 5.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

import java.util.Stack;

public class CodeBlock {
	public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "elif", "else"};
	public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELIF = 4, ELSE = 5;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	private String name;
	private String loopVariable;
	private String blockType;
	
	/*
	 * Constructs an CodeBlock objects with null or 0 fields.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The CodeBlock has been initialized with empty fields.
	 * 
	 */
	public CodeBlock() {
	}
	
	/*
	 * Does the same thing as the CodeBlock() method but uses user inputted fields to set the fields.
	 * 
	 * @param loopVariable
	 * 		New String loopVariable for the CodeBlock
	 * 
	 * @throws Exception
	 * 	catches empty string variable
	 */
	public CodeBlock(String loopVariable) {
		try {
		this.loopVariable = loopVariable;
		} catch(Exception e) {
			System.out.println("Loop Variable is null");
		}
	}
	
	/*
	 * Does the same thing as the CodeBlock() method but uses user inputted fields to set the fields.
	 * 
	 * @param loopVariable
	 * 		New String loopVariable for the CodeBlock
	 * @param highestSubComplexity
	 * 		New Complexity highestSubComplexity for the CodeBlock
	 * @param blockComplexity
	 * 		New Complexity blockComplexity for the CodeBlock
	 * @param name
	 * 		New String name for the CodeBlock
	 * 
	 * @throws Exception
	 * 		catches any empty fields
	 */
	public CodeBlock(String loopVariable, Complexity highestSubComplexity, Complexity blockComplexity, String name) {
		try {
			this.loopVariable = loopVariable;
			this.blockComplexity = blockComplexity;
			this.name = name;
			this.highestSubComplexity = highestSubComplexity;
		} catch(Exception e) {
			System.out.println("Fields for code block is null");
		}
	}
	
	/*
	 * returns the Complexity blockComplexity
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock has been instantiated.
	 * 
	 * @return
	 * 	The Complexity blockComplexity
	 * 
	 */
    public Complexity getBlockComplexity(){
        return blockComplexity;
    }
    
	/*
	 * returns the Complexity highestSubComplexity
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock has been instantiated.
	 * 
	 * @return
	 * 	The Complexity highestSubComplexity
	 * 
	 */
    public Complexity getHighestSubComplexity(){
        return highestSubComplexity;
    }
	/*
	 * returns the String name
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock has been instantiated.
	 * 
	 * @return
	 * 	The String name
	 * 
	 */
    public String getName(){
        return name;
    }
    
	/*
	 * returns the String blockType
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock has been instantiated.
	 * 
	 * @return
	 * 	The String blockType
	 * 
	 */
    public String getBlockType(){
    	String s = " ";
    	if(blockType == null)
    		return s;
        return blockType;
    }
    
	/*
	 * returns the String loopVariable
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock has been instantiated.
	 * 
	 * @return
	 * 	The String loopVariable
	 * 
	 */
    public String getLoopVariable(){
    	return loopVariable;
    }
    
	/*
	 * Replaces the String blockType with the parameter blockType
	 * 
	 * @param blockType
	 * 		New blockType for the CodeBlock
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The new CodeBlock now has a new blockType field with the parameter blockType.
	 * 
	 * @throws Exception
	 * 		empty field for blocktype
	 * 
	 */
    public void setBlockType(String blockType) {
    	try {
    		this.blockType = blockType;
    	} catch(Exception e) {
			System.out.println("Empty blockType");
		}
    }
    
	/*
	 * Replaces the Complexity blockComplexity with the parameter blockComplexity
	 * 
	 * @param blockComplexity
	 * 		New blockComplexity for the CodeBlock
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The new CodeBlock now has a new blockComplexity field with the parameter blockComplexity.
	 * 
	 * @throws
	 * 		block Complexity is null or invalid
	 */
    public void setBlockComplexity(Complexity blockComplexity){
    	try {
    		this.blockComplexity = blockComplexity;
    	} catch(Exception e) {
			System.out.println("blockComplexity is null");
		}
    }
    
	/*
	 * Replaces the Complexity highestSubComplexity with the parameter highestSubComplexity
	 * 
	 * @param blockComplexity
	 * 		New highestSubComplexity for the CodeBlock
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The new highestSubComplexity now has a new blockComplexity field with the parameter highestSubComplexity.
	 * 
	 * @throws
	 * 		Highest Sub Complexity is null
	 * 
	 */
    public void setHighestSubComplexity(Complexity highestSubComplexity){
    	try {
        this.highestSubComplexity = highestSubComplexity;
    	} catch(Exception e) {
			System.out.println("highestSubComplexity is null");
		}
    }
    
	/*
	 * Replaces the String name with the parameter name
	 * 
	 * @param blockType
	 * 		New name for the CodeBlock
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The new CodeBlock now has a new name field with the parameter name.
	 * 
	 * 
	 * @throws Exception 
	 * 		Throws a exception when the name is null
	 */
    public void setName(String name){
    	try {
    		this.name = name;
    	} catch(Exception e) {
			System.out.println("name is null");
		}
    }
    
	/*
	 * Replaces the String loopVariable with the parameter loopVariable
	 * 
	 * @param blockType
	 * 		New loopVariable for the CodeBlock
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This CodeBlock object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The new CodeBlock now has a new loopVariable field with the parameter loopVariable.
	 * 
	 * @throws Exception
	 * 		throws a exception when loopVariable is null
	 */
    public void setLoopVariable(String loopVariable){
    	try {
        this.loopVariable = loopVariable;
    	} catch(Exception e) {
			System.out.println("loopVariable is null");
		}
    }
}

