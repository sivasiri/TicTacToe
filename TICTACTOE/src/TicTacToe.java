import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static List<Integer> playerPositions= new ArrayList<Integer>();
	static List<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		// The main board
		char[][] board = new char[][] {{' ','|',' ','|',' '},
										{'-','+','-','+','-'},
										{' ','|',' ','|',' '},
										{'-','+','-','+','-'},
										{' ','|',' ','|',' '}};
		printBoard(board);
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter number from 1-9");
			int playerPos = sc.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Enter the correct pos");
				playerPos = sc.nextInt();
			}
			playBoard(board, playerPos, "player");
			String result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9)+1;
			while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9)+1;
			}
			playBoard(board, cpuPos, "cpu");
			result = checkWinner();
			printBoard(board);
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			
		}
		printBoard(board);
										
	}
	// Funtion to print the board
	public static void printBoard(char[][] board) {
		for(char[] c : board) {
			for(char d :c) {
				System.out.print(d);
			}
			System.out.println();
		}
	}
	//Func to make a move
	public static char[][] playBoard(char[][] board , int pos, String user){
		char symbol = ' ';
		if(user.equals("player"))
		{
			symbol='X';
			playerPositions.add(pos);
		}
		else if(user.equals("cpu")) {
			symbol ='O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
		case 1:
			board[0][0]= symbol;
			break;
		case 2:
			board[0][2]= symbol;
			break;
		case 3:
			board[0][4]= symbol;
			break;
		case 4:
			board[2][0]= symbol;
			break;
		case 5:
			board[2][2]= symbol;
			break;
		case 6:
			board[2][4]= symbol;
			break;
		case 7:
			board[4][0]= symbol;
			break;
		case 8:
			board[4][2]= symbol;
			break;
		case 9:
			board[4][4]= symbol;
			break;
		default:
			break;
		}
		return board;
	}
	// func checks the winner after the CPU move
	public static String checkWinner() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List topCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,6);
		List botCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(topCol);
		winning.add(midCol);
		winning.add(botCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l:winning) {
			if(playerPositions.containsAll(l)) {
				return "Congrats you won";
			}
			else if(cpuPositions.containsAll(l)) {
				return "oops! cpu won";
			}
			else if(playerPositions.size()+ cpuPositions.size()==9) {
				return "cat";
			}
		}
		return "";
	}

}

