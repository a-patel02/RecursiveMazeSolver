
import BasicIO.*; 
import Media.*;
import java.lang.Math.*;

/**
 * This class finds a path for a maze text file
 *
 * @author     Abhi Patel
 * @version    1.0
 * @since      March 29, 2021
 */

public class solvingmaze {
	private ASCIIDataFile maze; //maze text file 
	private ASCIIOutputFile output; //output file 
	
	
	private char[][] mazearray;//2D char array to store the maze 
	private int rowstartrandom /* random row number for start position*/, colstartrandom/* random col number for start position*/, rowendrandom/* random row number for end position*/, 
				colendrandom/* random col number for end position*/, startposrow/* start position row */, startposcol /* start position col */, endposrow/* end position row*/, endposcol/*end positon col*/; 
	
	
	
	private int row, col; //row and col to read the maze size from text file
			
	public solvingmaze() {
		maze = new ASCIIDataFile(); //the maze text file to read
		output = new ASCIIOutputFile();//outpute file to write the solved maze
		
		
		//reading maze size 
		row = maze.readInt(); //read the first int as the row value for maze size
		col = maze.readInt(); //read the second int as the col value for maze size 
		
		//making the maze read size 
		mazearray = new char[row][col]; //set the maze to the size read from the first two int's
		
		//generating all four points, 2 for start, 2 for end
		 rowstartrandom = (int) ((row-2) * Math.random()+1); //generate a random number for rowstartrandom that is in the range of the maze size
		 colstartrandom = (int) ((col-2) * Math.random()+1); //generate a random number for colstartrandom that is in the range of the maze size
		 rowendrandom = (int) ((row-2) * Math.random()+1); //generate a random number for rowendrandom that is in the range of the maze size
		 colendrandom = (int) ((col-2) * Math.random()+1); //generate a random number for colendrandom that is in the range of the maze size
		 
		 
		 //checks if start and end are same, if so then re generates all the random numbers
		 while(rowstartrandom != rowendrandom && colstartrandom != colendrandom) {
			 if(rowstartrandom == rowendrandom && colstartrandom == colendrandom) {
				 rowstartrandom = (int) ((row-2) * Math.random()+1);
				 colstartrandom = (int) ((col-2) * Math.random()+1);
				 rowendrandom = (int) ((row-2) * Math.random()+1);
				 colendrandom = (int) ((col-2) * Math.random()+1);
			 }
			 else {
				 break;
			 }
		 }

		readMaze(); //call the readMaze method that reads the text file maze into the maze char array 
		printMaze(); //print the original 

		//check if findPath method is true and that it found a path to solve maze, if so then print solved maze, else print unsolvable 
		
		if(findPath(rowstartrandom, colstartrandom)){
			output.newLine();
			output.writeLine("Start position of Gretel: (" + startposrow + ", " + startposcol + ")" );
			output.newLine();
			mazearray[startposrow][startposcol] = 'G'; 
			printMaze();
			output.newLine();
			output.writeLine("Found Hansel at: (" + endposrow + ", " + endposcol + ")" );

		}
		else {
			output.writeLine("Unsolvable");
		}

		maze.close();
		
	}

	
	/*
	 * Method that reads the text file and sets the characters read into the char maze array 
	 */
	public void readMaze() {
		for(int i =0; i < mazearray.length; i++) {
			//we read entire first line as a string
			String t = maze.readLine();
			for(int j = 0; j < mazearray[i].length; j++) {
				mazearray[i][j] = t.charAt(j);//break down the string into characters and store them in the maze array
				
				//method checking if G (start position) can be placed where the random start numbers were generated, if not then regenerate random number and place G there
				findStart(i,j);
				//method checking if H (end position) can be placed where the random end numbers were generated, if not then regenerate random number and place H there
				findEnd(i, j);
		}
	}
	}
	
	
	/*
	 * Method that prints the entire maze array
	 */
	public void printMaze() {
		for(int i = 0; i< mazearray.length; i++) {
			for(int j =0; j< mazearray[i].length; j++) {
				output.writeC(mazearray[i][j]);

			}
			output.newLine();
		}
	}
	
	
	/*
	 * Method that prints the Start position where the random start position was generated and where there are no walls, if there are walls then regenerate random start numbers
	 * @param i, row number to check currently if it matches with random row start position 
	 * @param j, column number to check currently if it matches with random column start position 
	 */
	public void findStart(int i, int j) {
		//if there is a wall where the random start position was generated then re generate random numbers, if not then set random start position in mazearray as 'G'
		if(mazearray[rowstartrandom][colstartrandom] == '#' ) {
			 rowstartrandom = (int) ((row-2) * Math.random()+1);
			 colstartrandom = (int) ((col-2) * Math.random()+1);
			 
			 //if the regenerated random start position matches the random end position, recurse through the entire method to get a random start position that does not match, by regenerating numbers
			 if(rowendrandom == rowstartrandom && colendrandom == colstartrandom) {
				 findStart(i, j);
			 }
			 //if at the regenerated random start position there is a wall, recurse through the entire method to get a random start position that does not match, by regenerating numbers
			 if(mazearray[rowstartrandom][colstartrandom] == '#' ) {
				 findStart(i, j);
			 }
		}
		//set random start position in mazearray as 'G'
		else {
			 mazearray[rowstartrandom][colstartrandom] = 'G';
			 startposrow = rowstartrandom; //set the random row start as the start position row to be used later
			startposcol = colstartrandom; //set the random column start as the start position column to be used later 
		}
	}
	
	/*
	 * Method that prints the End position where the random end position was generated where there are no walls, if there are walls then regenerate random end numbers
	 * @param i, row number to check currently if it matches with random row end position
	 * @param j, column number to check currently if it matches with random column end position
	 * 
	 * 
	 * Everything works exactly as the findStart(i, j) method but this time works for the end positions and sets the end positions and as 'H'
	 */
	public void findEnd(int i, int j) {
		//find end 
		if(mazearray[rowendrandom][colendrandom] == '#') {
			 rowendrandom = (int) ((row-2) * Math.random()+1);
			 colendrandom = (int) ((col-2) * Math.random()+1);

			 if(rowstartrandom == rowendrandom && colstartrandom == colendrandom) {
				 findEnd(i, j);
			 }
			 if(mazearray[rowendrandom][colendrandom] == '#') {
				 findEnd(i, j);
			 }
		}
		//set random end position in mazearray as 'H'
		else {
			 mazearray[rowendrandom][colendrandom] = 'H';
			 endposrow = rowendrandom;
			endposcol = colendrandom;
		}
	}
	
	
	/*
	 * Method that finds the path from the start position 'G' to the end position 'H'
	 * @param x, row of the start position
	 * @param y, column of the start position
	 */
	public boolean findPath(int x, int y) {
		//if we found Hansel then return true and we found path
		if(mazearray[x][y] == 'H') 
			return true;
		
		//if we are on a wall and we are not on G(start position) or if we are on '.' then return false, this is because we have across barriers
		if((mazearray[x][y] != ' ') && (mazearray[x][y]!='G')|| mazearray[x][y] == '.') 
			return false;
		
		//after we came across a barrier we want to make sure we don't go that position again so we leave a . there
		mazearray[x][y] = '.'; //we have already visited spot and there are no further solutions/paths on that spot
		
		/*
		 * Recursion 
		 *
		 * Check each direction by recursing the entire method and checking if there is a solution there if there is then method will return true and we will place an arrow stating the direction we are going
		 * 
		 */
		
		//check up, if recursive method says there is a solution at top, then set current pos to ^, return true and continue path
		if(findPath(x-1, y)) {
				mazearray[x][y] = '^';
				return true;
			}

		//check right, if recursive method says there is a solution at right, then set current pos to >, return true and continue path
		if(findPath(x, y+1)) {
			mazearray[x][y] = '>';
			return true;
		}

		//check down, if recursive method says there is a solution at down, then set current pos to v, return true and continue path
		if(findPath(x+1, y)) {
				mazearray[x][y] = 'v';
				return true;
				
			}

		//check left, if recursive method says there is a solution at left, then set current pos to <, return true and continue path
		if(findPath(x, y-1)) {
				mazearray[x][y] = '<';
				return true;
			}

		//if none of the methods return a statement then return false, because there is no solution to be found
		return false;
	}
	
	public static void main(String[] args) {
		solvingmaze c = new solvingmaze();
		

	}

}
