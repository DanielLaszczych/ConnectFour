import java.util.Scanner;
import java.util.InputMismatchException;

public class ConnectFour {

	static String[][] board = new String[6][7];
	static int track1, track2, track3, track4, track5, track6, track7;
	static Scanner stdin = new Scanner(System.in);
	static boolean tie, yellowWin, redWin;
	
	public static void setBoard() {
		track1 = track2 = track3 = track4 = track5 = track6 = track7 = 6;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = "   ";
			}
		}
	}

	public static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("|");
				System.out.print(board[i][j]);
				if(j == board[i].length - 1)
					System.out.print("|");
			}
			System.out.println();
		}
		System.out.println(".............................");
		System.out.println();
	}
	
	public static void redPlay() {
		while(true) {
			try {
				System.out.print("Player 1 drop a red disk at column (0 - 6): ");
				int column = stdin.nextInt();
				if((column >= 0 && column <= 6)) {
					try {
						board[getRow(column)][column] = " R ";
						break;
						} catch (ArrayIndexOutOfBoundsException ex) {
							System.out.println("Column " + column + " is filled up. Try again");
							stdin.nextLine();
						}
				}
				else {
					System.out.println("Input was not in the specified range. Try again");
					stdin.nextLine();
				}
			} catch (InputMismatchException ex) {
				System.out.println("Incorrect Input");
				stdin.nextLine();
			}
		}
	}
	
	public static void yellowPlay() {
		while(true) {
			try {
				System.out.print("Player 2 drop a yellow disk at column (0 - 6): ");
				int column = stdin.nextInt();
				if((column >= 0 && column <= 6)) {
					try {
					board[getRow(column)][column] = " Y ";
					break;
					} catch (ArrayIndexOutOfBoundsException ex) {
						System.out.println("Column " + column + " is filled up. Try again");
						stdin.nextLine();
					}
				}
				else {
					System.out.println("Input was not in the specified range. Try again");
					stdin.nextLine();
				}
			} catch (InputMismatchException ex) {
				System.out.println("Incorrect Input. Try again");
				stdin.nextLine();
			}
		}
	}
	
	public static char trackWin(String[][] arr) {
		
		for(int i = 0; i < arr.length; i++) {
			
			//Checking Horizontal
			for(int j = 0; j < 4; j++) {
				
				if ((arr[i][j].charAt(1) != ' ') 
				&& (arr[i][j+1].charAt(1) != ' ')
				&& (arr[i][j+2].charAt(1) != ' ')
				&& (arr[i][j+3].charAt(1) != ' ')		
				&& (arr[i][j].charAt(1) == arr[i][j+1].charAt(1))
				&& (arr[i][j+1].charAt(1) == arr[i][j+2].charAt(1))
				&& (arr[i][j+2].charAt(1) == arr[i][j+3].charAt(1))) {
					
					return arr[i][j].charAt(1);
				}
			}	
		}
		for(int i = 0; i < arr[0].length; i++) {
			
			//Checking Vertical
			for(int j = 0; j < arr.length/2; j++) {
				
				if ((arr[j][i].charAt(1) != ' ') 
				&& (arr[j+1][i].charAt(1) != ' ')
				&& (arr[j+2][i].charAt(1) != ' ')
				&& (arr[j+3][i].charAt(1) != ' ')		
				&& (arr[j][i].charAt(1) == arr[j+1][i].charAt(1))
				&& (arr[j+1][i].charAt(1) == arr[j+2][i].charAt(1))
				&& (arr[j+2][i].charAt(1) == arr[j+3][i].charAt(1))) {
					
					return arr[j][i].charAt(1);
				}
			}
		}
		for(int i = 0; i < 3; i++) {
			
			//Checking Diagonals
			for(int j = 1; j < 4; j++) {
				
				if ((arr[i][j].charAt(1) != ' ') 
				&& (arr[i+1][j+1].charAt(1) != ' ')
				&& (arr[i+2][j+2].charAt(1) != ' ')
				&& (arr[i+3][j+3].charAt(1) != ' ')		
				&& (arr[i][j].charAt(1) == arr[i+1][j+1].charAt(1))
				&& (arr[i+1][j+1].charAt(1) == arr[i+2][j+2].charAt(1))
				&& (arr[i+2][j+2].charAt(1) == arr[i+3][j+3].charAt(1))) {
					
					return arr[i][j].charAt(1);
				}
			}		
		}
		for(int i = 0; i < 3; i++) {
			
			//Checking Diagonals
			for(int j = 6; j > 2; j--) {
				
				if ((arr[i][j].charAt(1) != ' ') 
				&& (arr[i+1][j-1].charAt(1) != ' ')
				&& (arr[i+2][j-2].charAt(1) != ' ')
				&& (arr[i+3][j-3].charAt(1) != ' ')		
				&& (arr[i][j].charAt(1) == arr[i+1][j-1].charAt(1))
				&& (arr[i+1][j-1].charAt(1) == arr[i+2][j-2].charAt(1))
				&& (arr[i+2][j-2].charAt(1) == arr[i+3][j-3].charAt(1))) {
					
				return arr[i][j].charAt(1);
				}
			}	
		}
		return 'N';	
	}
	
	public static int getRow(int row) {
		switch(row) {
		case 0 : track1--; return track1;
		case 1 : track2--; return track2;
		case 2 : track3--; return track3;
		case 3 : track4--; return track4;
		case 4 : track5--; return track5;
		case 5 : track6--; return track6;
		case 6 : track7--; return track7;
		}
		return 0;
	}

	public static void main(String[] args) {
		int turn = 0;
		setBoard();
		printBoard();
		while(turn != 42) {
			redPlay();
			printBoard();
			turn++;
			if (trackWin(board) == 'R') {
				System.out.print("Red Wins");
				break;
			}
			if(trackWin(board) == 'Y') {
				System.out.print("Yellow Wins");
				break;
			}
			yellowPlay();
			printBoard();
			turn++;
			if (trackWin(board) == 'R') {
				System.out.print("Red Wins");
				break;
			}
			if(trackWin(board) == 'Y') {
				System.out.print("Yellow Wins");
				break;
			}
		}
		if(turn == 42 && trackWin(board) == 'N') {
			System.out.print("Tie");
		}
	}

}
