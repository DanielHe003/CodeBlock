/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #3
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA:Ulfeen Ayevan & Wesley Mui  
 */

/*
*  Complexity represents the Big-Oh complexity of some block of code. Since Big-Oh notation can get quite messy 
*  (e.g. log(n^(1/2) / n!)), it will restrict the possible orders to powers of two base types: n, and log_n.
*  Following this practice, two member variables in the Complexity class: nPower (int) and logPower (int). 
*  These two variables will keep track of what power each of the base types is present in the complexity object.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

public class Complexity {
    private int nPower;
    private int logPower;
    
	/*
	 * Constructs an Complexity objects with null or 0 fields.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The ProductLoad has been initialized with empty fields.
	 * 
	 */
	public Complexity() {
	}
	
	/*
	 * Does the same thing as the Complexity() method but uses user inputted fields to set the fields.
	 * 
	 * @param nPower
	 * 		New nPower for the Complexity
	 * @param logPower 
	 * 		New logPower for the Complexity
	 */
	public Complexity(int nPower, int logPower){
        this.nPower = nPower;
        this.logPower = logPower;
    }
	
	/*
	 * returns the int nPower
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Complexity object has been instantiated.
	 * 
	 * @return
	 * 	The Complexity nPower
	 * 
	 * 
	 */
    public int getNPower(){
    	if(nPower == 0)
    		return 0;
        return nPower;
    }
    
	/*
	 * returns the int logPower
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Complexity object has been instantiated.
	 * 
	 * @return
	 * 	The Complexity logPower
	 * 
	 */
    public int getLogPower(){
        return logPower;
    }
    
	/*
	 * Replaces the int logPower with the parameter logPower
	 * 
	 * @param logPower
	 * 		New logPower for the Complexity
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Complexity object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Complexity now has a new logPower field with the parameter int.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the logPower is null
	 * 
	 */
    public void setLogPower(int logPower){
    	try {
    		this.logPower = logPower;
    	} catch(Exception e) {
			System.out.println("logPower is null");
		}
    }

	/*
	 * Replaces the int weight with the parameter int
	 * 
	 * @param nPower
	 * 		New nPower for the Complexity
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Complexity object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Complexity now has a new nPower field with the parameter nPower.
	 * 
	 * @throws Exception
	 * 		throws a exception when the n power is null
	 */
    public void setNPower(int nPower){
    	try {
    		this.nPower = nPower;
    	} catch(Exception e) {
			System.out.println("nPower is null");
		}
    }
    
	/*
	 * Returns a neatly formatted String representation of the Complexity object.
	 * 
	 * @returns
	 * 		A neatly formatted string representing the big O notation of the complexity object.
	 */
    public String toString(){
    	String temp = "O(";
    	if(nPower == 0 && logPower == 0)
    		temp += "1";
    	if(nPower == 1)
    		temp += "n";
    	if(nPower > 1)
    		temp += "^"+nPower;
    	if(logPower == 1)
    		temp += "log(n)";
    	if(logPower > 1)
    		temp += " * log(n)^"+logPower;
    	temp += ")";
    	return temp;
    	}
}
