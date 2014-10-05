import java.io.*;
import java.util.*;

public class MazeBuilder {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println(" Incorrect number of arguments");
			System.err.println(" Usage: ");
			System.err.println("\tjava MazeBuilder <height> <width> <output file>");
			System.exit(1);
		} else if (!args[2].endsWith(".txt")) {
			System.err.println(" Please specify a proper filename ending in .txt");
			System.exit(1);
		}

		try {
			int HEIGHT = Integer.parseInt(args[0]);
			int WIDTH = Integer.parseInt(args[1]);

			if (HEIGHT <= 0 || WIDTH <= 0) {
				System.err.println("Input height and width must be greater than 0");
				System.exit(1);
			}

			PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter(args[2])));

			Random rand = new Random();

			MyDisjSets UFSet = new MyDisjSets(WIDTH * HEIGHT); 
			boolean[] hWall = new boolean[WIDTH * HEIGHT];
			boolean[] vWall = new boolean[WIDTH * HEIGHT];

			// Create maze using union find according to WIDTH & HEIGHT passed
			// in above
			int a = 0;
			int b = 0;
			while (UFSet.numSets() > 1) {
				if (a + 1 == b && (a + 1) % WIDTH != 0) {
					vWall[a] = true;
					UFSet.union(UFSet.find(a), UFSet.find(b));
				} else if (a + WIDTH == b && a < WIDTH * HEIGHT - WIDTH) {
					hWall[a] = true;
					UFSet.union(UFSet.find(a), UFSet.find(b));
				} else if (a - 1 == b && a % WIDTH != 0) {
					vWall[b] = true;
					UFSet.union(UFSet.find(a), UFSet.find(b));
				} else if (a - WIDTH == b && a >= WIDTH) {
					hWall[b] = true;
					UFSet.union(UFSet.find(a), UFSet.find(b));
				} else {
					a = 0;
					b = 0;
				}
				while (((b >= WIDTH * HEIGHT || b < 0) || UFSet.find(a) == UFSet.find(b)) 
						&& UFSet.numSets() > 1) {
					a = rand.nextInt(WIDTH * HEIGHT);
					b = rand.nextInt(4);
					if (b == 0) {
						b = a - WIDTH;
					} else if (b == 1) {
						b = a + WIDTH;
					} else if (b == 2) {
						b = a + 1;
					} else {
						b = a - 1;
					}
				}
			}
			vWall[HEIGHT * WIDTH - 1] = true;


			// Print maze using matrices previously created above
			for (int j = 0; j < WIDTH; j++) {
				fileOut.print("+-");
			}
			fileOut.println("+");
			for (int i = 0; i < HEIGHT * 2; i++) {
				if (i % 2 == 0) {
					if (i == 0) {
						fileOut.print(" ");
					} else {
						fileOut.print("|");
					}
					for (int j = 0; j < WIDTH; j++) {
						if (vWall[WIDTH * (i / 2) + j] == false) {
							fileOut.print(" |");
						} else {
							fileOut.print("  ");
						}
					}
					fileOut.println();
				} else {
					for (int j = 0; j < WIDTH; j++) {
						if (hWall[WIDTH * (i / 2) + j] == true) {
							fileOut.print("+ ");
						} else {
							fileOut.print("+-");
						}
					}
					fileOut.println("+");
				}
			}

			fileOut.close();
			System.out.println("Operation complete");

		} catch (IOException ioe) {
			System.err.println("Error writing output file");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err.println("Height and width must be integers");
			System.exit(1);
		}
	}
}
