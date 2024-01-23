
/** 
 *  Game of Life.
 *  Usage: "java GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the homework document.
 */

public class GameOfLife {

	public static void main(String[] args) {
		String fileName = /* "C:/Users/shale/Documents/repository/hw05-ShalevBashan/line.dat";*/args[0];
		//// Uncomment the test that you want to execute, and re-compile.
		//// (Run one test at a time).
		// test1(fileName);
		// test2(fileName);
		// test3(fileName, 3);
		// play(fileName);
	
	}
	
	
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				System.out.printf ("%3d", board[i][j]);
			}
		System.out.println();
		}
	}
		
	private static void test2(String fileName) {
		int[][] board = read(fileName);
		int[][] temp = new int[board.length][board[0].length];
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board.length - 1; j++) {
				temp[i][j] = cellValue(board, i, j);
			}
		}
		board = temp;
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				System.out.printf ("%3d", board[i][j]);
			}
		System.out.println();
		}
	}
		
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		
	// Reads the data file and plays the game, for ever.
	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	

	public static int[][] read(String fileName) {
		In in = new In(fileName); 
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows + 2][cols + 2];
		String line = "";
		for (int i = 1; i < board.length-1; i++) {
			line = in.readLine();
			for (int j = 1; j < board[i].length-1; j++) { 
					if (line.length() == 0 || line.length() < j) break;
					if (line.charAt(j-1) == 'x') board[i][j] = 1;
			}
		}
		return board;
	}
	
	public static int[][] evolve(int[][] board) {
		int[][] b = new int[board.length][board[0].length];
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board.length - 1; j++) {
				b[i][j] = cellValue(board, i, j);
			}
		}
		return b;
	}

	public static int cellValue(int[][] board, int i, int j) {
		int cnt = count(board, i, j);
		if (board[i][j] == 0 && cnt == 3) return 1;
		if (board[i][j] == 1) {
			if (cnt < 2) {
				return 0;
			} else if (cnt == 2 || cnt == 3) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
	
	public static int count(int[][] board, int i, int j) {
		int cnt = 0;
		for (int l = i - 1; l <= i + 1; l++) {
			for (int k = j - 1; k <= j + 1; k++) {
					if (board[l][k] == 1) {
						cnt++;
					}
			}
		}
		if (board[i][j] == 1) cnt--;
		return cnt;
	}
	
    public static void print(int[][] arr) {
		for (int i = 1; i < arr.length - 1; i++) {
			for (int j = 1; j < arr[i].length - 1; j++) {
				System.out.printf ("%3d", arr[i][j]);
			}
		System.out.println();
		}
	}
		
    // Displays the board. Living and dead cells are represented by black and white squares, respectively.
    // We use a fixed-size canvas of 900 pixels by 900 pixels for displaying game boards of different sizes.
    // In order to handle any given board size, we scale the X and Y dimensions according to the board size.
    // This results in the following visual effect: The smaller the board, the larger the squares
	// representing cells.
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		// Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
		
		// For each cell (i,j), draws a filled square of size 1 by 1 (remember that the canvas was 
		// already scaled to the dimensions rows by cols, which were read from the data file). 
		// Uses i and j to calculate the (x,y) location of the square's center, i.e. where it
		// will be drawn in the overall canvas. If the cell contains 1, sets the square's color
		// to black; otherwise, sets it to white. In the RGB (Red-Green-Blue) color scheme used by
		// StdDraw, the RGB codes of black and white are, respetively, (0,0,0) and (255,255,255).
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}
}
