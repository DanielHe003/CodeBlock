/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #3
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA:Ulfeen Ayevan & Wesley Mui  
 */

/*
*  PythonTracer contains a main method. The class also contains a static final int variable SPACE_COUNT = 4 
*  which will be used to determine the indentation of each statement. In addition, a static traceFile method
*  which will take as a parameter the name of a file containing a Python function. The traceFile method will 
*  open the indicated file, trace through the code of the Python function contained within the file, and 
*  output the details of the trace and the overall complexity to the console.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

import java.util.*;
import java.io.*;

public class PythonTracer {
	static final int SPACE_COUNT = 4;
/*
 * Prompts the user for the name of a file containing a single Python function, determines 
 * its order of complexity, and prints the result to the console.
 * 
 * @throws NullPointerException
 * 		It throws this error when it does not have a line with characters in it
 * 
 * @throws Exception 
 * 		There was a error in the code
 */
	public static void main(String[] args) {
		try {
		Scanner scan  = new Scanner(System.in);
		while(true) {
			System.out.println();
			System.out.print("Please enter a file name (or 'quit' to quit): ");
			String fileName = scan.nextLine();
			if(fileName.equals("quit")) {
				System.out.println();
				System.out.println("Program terminating successfully...");
				System.out.println();
				break;
			} else {
				System.out.println();
				System.out.println("Overall complexity of matrix_multiply: " + traceFile(fileName));
			}

		}
		System.out.println();
		scan.close();
		} catch(NullPointerException e) {
			System.out.println("The line is null");
		} catch(Exception e) {
			System.out.println("There was a error in the code");
		}
	}

	/*
	 * Opens the indicated file and traces through the code of the Python function contained within the file, 
	 * returning the Big-Oh order of complexity of the function. During operation, the stack trace should be 
	 * printed to the console as code blocks are pushed to/popped from the stack.
	 * 
	 * @param fileName
	 * 		The file that is looped through and finds the big O notation
	 * 
	 * <dt> Preconditions:
	 * 	<dd> filename is not null and the file it names contains a single Python function with valid syntax 
	 * 
	 * @returns
	 * 	A Complexity object representing the total order of complexity of the Python code contained within the file.
	 * 
	 * @throws FileNotFoundException
	 * 		File was not located so it was wrong
	 * 
	 * @throws IOException
	 * 		There was a input or output exception
	 */
	public static Complexity traceFile(String fileName) {
		BlockStack stack = new BlockStack();
		Complexity highestComplex = new Complexity();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(inStream);
			CodeBlock oldTop = new CodeBlock();
			Complexity oldTopComplexity = new Complexity();
			Complexity currentTop = new Complexity();
			String line = "";
			String trimmedLine;
			int indents;
			while(line != null) {
				line = reader.readLine();
				if(!line.trim().isEmpty() && (line.trim().startsWith("#") == false)) {
					indents = spaceCount(line) / SPACE_COUNT;
					while(indents < stack.size()-1) {
						if(indents == 0) {
							reader.close();
							if(stack.peek() == null) {
								return stack.peek().getBlockComplexity();
							}
						} else {
							oldTop = stack.pop();
							oldTopComplexity = oldTop.getBlockComplexity();
							currentTop = stack.peek().getHighestSubComplexity();
							if(oldTopComplexity.getNPower() > currentTop.getNPower()) {
								currentTop.setNPower(oldTopComplexity.getNPower());
							}
							else if(oldTopComplexity.getNPower() == currentTop.getNPower()) {
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()) {
									currentTop.setLogPower(oldTopComplexity.getLogPower());
								} 
							}
						}
					}
					if(line.contains("for") || line.contains("while") || line.contains("def")
							|| line.contains("if") || line.contains("elif") || line.contains("else")) {
						String keyWord = "";
						CodeBlock temp = new CodeBlock();
						String add = "";
						String block = "block complexity";
						String high = "highest sub-complexity";
						String lastName = "";
						int j = 0;
						int count = 0;
						int lastNameCounter = 0;
						if(line.contains("for")) {
							keyWord = "for";
						} else if(line.contains("while")) {
							keyWord = "while";
						} else if(line.contains("if")) {
							keyWord = "if";
						} else if(line.contains("def")) {
							keyWord = "def";
						} else if(line.contains("elif")) {
							keyWord = "elif";
						} else if(line.contains("else")) {
							keyWord = "else";
						}
						if(keyWord.equals("for")) {
							if(line.endsWith("N:") && !line.endsWith("log_N:")) {
								Complexity complex = new Complexity(1,0);
								temp.setBlockComplexity(complex);
							} else if(line.endsWith("log_N:")) {
								Complexity complex = new Complexity(0,1);
								temp.setBlockComplexity(complex);
							}
							while(j < lastName.length()) {
								if(temp.getName().split(".")[j] == "1") 
									count++;
								if(lastName.split(".")[j] == "1")
									lastNameCounter++;
							} if(lastNameCounter != count)
								temp.setName((temp.getName().substring(0, temp.getName().length()-1)) + "2");
						} else if (keyWord.equals("while")) {
							String str = line;
							String[] array = str.split(" ");
							boolean checkForWhile = true;
							int index = 0;
							while(!array[index].equals("while")) {
								index++;
							}	
							temp.setBlockType("while");
							temp.setLoopVariable("while");
							while(checkForWhile != false) {
								line = reader.readLine();
								if(line.contains("-=")) {
									temp.setBlockComplexity(new Complexity(1,0));
									checkForWhile = false;
								} else if(line.contains("/=")) {
									temp.setBlockComplexity(new Complexity(0,1));
									checkForWhile = false;
								}
							}
							
							while(j < lastName.length()) {
								temp.setBlockComplexity(new Complexity(0,0));
								if(temp.getName().split(".")[j] == "1") 
									count++;
								if(lastName.split(".")[j] == "1")
									lastNameCounter++;
							} if(lastNameCounter != count)
								temp.setName((temp.getName().substring(0, temp.getName().length()-1)) + "2");
						} else if(!keyWord.equals("while") && !keyWord.equals("for")){
							Complexity complex = new Complexity(0,0);
							temp.setBlockComplexity(complex);
							while(j < lastName.length()) {
								if(temp.getName().split(".")[j] == "1") 
									count++;
								if(lastName.split(".")[j] == "1")
									lastNameCounter++;
							} if(lastNameCounter != count)
								temp.setName((temp.getName().substring(0, temp.getName().length()-1)) + "2");
						}
						if(stack.size() == 0)
							temp.setName("1");
						else {
						temp.setName(stack.peek().getName());
						}
						for(int i = 0; i < stack.size(); i++) {
							add += ".1";
						}
						temp.setName(temp.getName()+add);
						temp.setBlockType(keyWord);
						temp.setLoopVariable("'"+keyWord+"'");
						
						//checks highest complexity and sets the highest sub-complexity as that
						if(highestComplex == null) {
							temp.setHighestSubComplexity(temp.getBlockComplexity());
						}
						if(stack.size() == 0) {
							temp.setHighestSubComplexity(temp.getBlockComplexity());
							highestComplex = temp.getHighestSubComplexity();
						} else if(temp.getBlockComplexity().getNPower() > highestComplex.getNPower()) {
							highestComplex.setLogPower(temp.getBlockComplexity().getLogPower());
							highestComplex.setNPower(temp.getBlockComplexity().getNPower());
						} else if(temp.getBlockComplexity().getNPower() == highestComplex.getNPower()) {
							if(temp.getBlockComplexity().getLogPower() > highestComplex.getLogPower()) {
								highestComplex.setLogPower(temp.getBlockComplexity().getLogPower());
							}
						} if(stack.peek() != null)
							stack.peek().setHighestSubComplexity(highestComplex);
						stack.push(temp);
						System.out.printf("\n    Entering block %s %s:\n", temp.getName(), temp.getLoopVariable());
						System.out.printf("        BLOCK %-7s%20s = %s %30s = %s\n", stack.peek().getName()+":", block, stack.peek().getBlockComplexity().toString(), high, highestComplex.toString());
						if(!(stack.peek() == null))
							lastName = stack.peek().getName();
					}
					if(stack.peek().getBlockType().equals("while")
							&& (line.split("\\s+")[0].equals(stack.peek().getLoopVariable()))) {
						Complexity temp = new Complexity();
						if(line.contains("-="))
							temp.setNPower(1);
						if(line.contains("/="))
							temp.setLogPower(1);
						if(!(stack.peek() == null))
							stack.peek().setBlockComplexity(temp);
					}
				} else {
					continue;
				}
			}
			while(stack.size()>1) {
				oldTop = stack.pop();
				oldTopComplexity = oldTop.getBlockComplexity();
				if(oldTopComplexity.getNPower() > oldTop.getHighestSubComplexity().getNPower()
						|| oldTopComplexity.getLogPower() > oldTop.getHighestSubComplexity().getLogPower()) {
					stack.peek().setHighestSubComplexity(oldTopComplexity);
				}
				System.out.printf("\n    Leaving block %s, updating block %s: \n", oldTop.getName(), stack.peek().getName());
				System.out.printf("        BLOCK %-7s%20s = %s %30s = %s\n", oldTop.getName(), "block complexity", oldTop.toString(), "highest sub-complexity", oldTop.getHighestSubComplexity().toString());
				if(stack.size() == 0)
					System.out.println("Leaving block 1.");
				reader.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not there");
		} catch (IOException e) {
			System.out.println("In/out exception");
		}
		return stack.peek().getBlockComplexity();
	}

	/*
	 * returns the count of spaces in a line
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This string has been instantiated.
	 * 
	 * @param line
	 * 		the string line that we need to find the spaces in
	 * 
	 * @return
	 * 	returns the count of spaces in a line
	 * 
	 */
	public static int spaceCount(String line) {
		int count = 0;
		for(char i : line.toCharArray()) {
			if(i == ' ')
				count++;
			if(i != ' ') {
				break;
			}
		}
		return count;
	}
}
