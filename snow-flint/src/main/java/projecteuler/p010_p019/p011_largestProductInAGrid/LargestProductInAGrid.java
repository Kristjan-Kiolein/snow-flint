package projecteuler.p010_p019.p011_largestProductInAGrid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * <b>Largest product in a grid</b><br>
 * <br>
 * In the 20�20 grid below, four numbers along a diagonal line have been marked
 * in red.<br>
 * <br>
 * 08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08<br>
 * 49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00<br>
 * 81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65<br>
 * 52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91<br>
 * 22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80<br>
 * 24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50<br>
 * 32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70<br>
 * 67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21<br>
 * 24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72<br>
 * 21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95<br>
 * 78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92<br>
 * 16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57<br>
 * 86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58<br>
 * 19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40<br>
 * 04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66<br>
 * 88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69<br>
 * 04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36<br>
 * 20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16<br>
 * 20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54<br>
 * 01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48<br>
 * <br>
 * The product of these numbers is 26 � 63 � 78 � 14 = 1788696.<br>
 * <br>
 * <i>What is the greatest product of four adjacent numbers in the same
 * direction (up, down, left, right, or diagonally) in the 20�20 grid?</i>
 *
 */
public class LargestProductInAGrid {

	public static void main(String[] args) {
		int nrOfMultiples = 4;
		System.out.println("Largest product is " +
				NumberFormat.getNumberInstance(Locale.US).format(
						solve(Paths.get("src/main/java/projecteuler/p11_largestProductInAGrid", "grid.txt"), nrOfMultiples)));
	}

	public static int solve(Path file, int nrOfMultiples) {
		int[][] grid = readGrid(file);
		return findLargestProduct(grid, nrOfMultiples);
	}

	private static int findLargestProduct(int[][] grid, int nrOfMultiples) {

		int largestHorizontal = findLargestHorizontal(grid, nrOfMultiples);
		int largestVertical = findLargestVertical(grid, nrOfMultiples);
		int largestMajorDiagonal = findLargestMajorDiagonal(grid, nrOfMultiples);
		int largestMinorDiagonal = findLargestMinorDiagonal(grid, nrOfMultiples);

		System.out.println("largestHorizontal : " + NumberFormat.getNumberInstance(Locale.US).format(largestHorizontal));
		System.out.println("largestVertical : " + NumberFormat.getNumberInstance(Locale.US).format(largestVertical));
		System.out.println("largestMajorDiagonal : " + NumberFormat.getNumberInstance(Locale.US).format(largestMajorDiagonal));
		System.out.println("largestMinorDiagonal : " + NumberFormat.getNumberInstance(Locale.US).format(largestMinorDiagonal));
		
		
		int l1 = largestHorizontal > largestVertical ? largestHorizontal : largestVertical;
		int l2 = l1 > largestMajorDiagonal ? l1 : largestMajorDiagonal;
		return l2 > largestMinorDiagonal ? l2 : largestMinorDiagonal;
//		return largestHorizontal;
	}

	private static int findLargestMajorDiagonal(int[][] grid, int nrOfMultiples) {
		
		int largestProduct = 0;
		int[] largestMembers = null;
		
		for (int k = 0; k < grid.length*2; k++) {
			int[] firstMember = new int[] {0, Math.abs(grid.length - k)};
			int product = 1;
			for (int i = 0; i < k; i++) {
				int j = grid.length - k + i;
				if (j < grid.length && i < grid.length && j >= 0 && i >= 0) {
					int addedNumber = grid[j][i];
					
					if (addedNumber == 0) {
						product = 1;
						firstMember[0] = i + 1;
						firstMember[1] = j + 1;
					} else {
						if (i - firstMember[0] >= nrOfMultiples && j - firstMember[1] >= nrOfMultiples
								|| j - firstMember[0] >= nrOfMultiples && i - firstMember[1] >= nrOfMultiples) {
							int excludedNumber = grid[j - nrOfMultiples][i - nrOfMultiples];
							product = product * addedNumber / excludedNumber;
							if (product > largestProduct) {
								largestProduct = product;
								largestMembers = new int[] {grid[j - 3][i - 3], grid[j - 2][i - 2], grid[j- 1][i - 1], grid[j][i]};
							}
								
						} else {
							product *= addedNumber;
							if ((i - firstMember[0] == nrOfMultiples - 1 && j - firstMember[1] == nrOfMultiples - 1 
									|| j - firstMember[0] == nrOfMultiples - 1 && i - firstMember[1] == nrOfMultiples - 1 ) && product > largestProduct){
								largestProduct = product;
								largestMembers = new int[] {grid[j - 3][i - 3], grid[j - 2][i - 2], grid[j- 1][i - 1], grid[j][i]};
							}
						}
					}

					System.out.printf("%02d ", grid[j][i]);
				}
			}
			System.out.print("Largest largestProduct: " + NumberFormat.getNumberInstance(Locale.US).format(largestProduct) + " members : " + Arrays.toString(largestMembers));
			System.out.println("");
		}
		System.out.println();
		return largestProduct;
	}

	private static int findLargestMinorDiagonal(int[][] grid, int nrOfMultiples) {
		
		int largestProduct = 0;
		int[] largestMembers = null;

		for (int k = 0; k < grid.length*2; k++) {
			int[] firstMember = new int[] { 0, k };
			int product = 1;
			for (int i = 0; i <= k; i++) {
				int j = k - i;
				if (j < grid.length && i < grid.length) {
					int addedNumber = grid[j][i];
					
					if (addedNumber == 0) {
						product = 1;
						firstMember[0] = i + 1;
						firstMember[1] = j - 1;
					} else {
						if (i - firstMember[0] >= nrOfMultiples && nrOfMultiples + j < grid.length) {
							int excludedNumber = grid[j + nrOfMultiples][i - nrOfMultiples];
							product = product * addedNumber / excludedNumber;
							if (product > largestProduct) {
								largestProduct = product;
								largestMembers = new int[] {grid[j + 3][i - 3], grid[j + 2][i - 2], grid[j + 1][i - 1], grid[j][i]};
							}
						} else {
							product *= addedNumber;
							if (i - firstMember[0] == nrOfMultiples - 1 && nrOfMultiples + j < grid.length  && product > largestProduct) {
								largestProduct = product;
								largestMembers = new int[] {grid[j + 3][i - 3], grid[j + 2][i - 2], grid[j + 1][i - 1], grid[j][i]};
							}
						}
					}
					System.out.printf("%02d ", grid[j][i]);
				}
			}
			System.out.print("Largest largestProduct: " + NumberFormat.getNumberInstance(Locale.US).format(largestProduct) + " members : " + Arrays.toString(largestMembers));
			System.out.println("");
		}
		System.out.println();

		return largestProduct;
	}

	private static int findLargestVertical(int[][] grid, int nrOfMultiples) {

		int largestProduct = 0;
		int[] largestMembers = null;

		for (int i = 0; i < grid.length; i++) {
			int firstMember = 0;
			int product = 1;
			for (int j = 0; j < grid.length; j++) {
				int addedNumber = grid[j][i];
				if (addedNumber == 0) {
					product = 1;
					firstMember = j + 1;
				} else {
					if (j - firstMember >= nrOfMultiples) {
						int excludedNumber = grid[j - nrOfMultiples][i];
						product = product*addedNumber / excludedNumber;
						if (product > largestProduct) {
							largestProduct = product;
							largestMembers = new int[] {grid[j - 3][i], grid[j - 2][i], grid[j - 1][i], grid[j][i]};
						}
					} else {
						product *= addedNumber;
						if (j - firstMember == nrOfMultiples - 1 && product > largestProduct){
							largestProduct = product;
							largestMembers = new int[] {grid[j - 3][i], grid[j - 2][i], grid[j - 1][i], grid[j][i]};
						}
					}
				}
				System.out.printf("%02d ", grid[j][i]);
			}
			System.out.print("Largest largestProduct: " + NumberFormat.getNumberInstance(Locale.US).format(largestProduct) + " members : " + Arrays.toString(largestMembers));
			System.out.println();
		}
		System.out.println();
		return largestProduct;
	}

	private static int findLargestHorizontal(int[][] grid, int nrOfMultiples) {

		int largestProduct = 0;
		int[] largestMembers = null;

		for (int i = 0; i < grid.length; i++) {
			int firstMember = 0;
			int product = 1;
			for (int j = 0; j < grid.length; j++) {
				int addedNumber = grid[i][j];
				if (addedNumber == 0) {
					product = 1;
					firstMember = j + 1;
				} else {
					if (j - firstMember >= nrOfMultiples) {
						int excludedNumber = grid[i][j - nrOfMultiples];
						product = product * addedNumber / excludedNumber;
						if (product > largestProduct) {
							largestProduct = product;
							largestMembers = new int[] {grid[i][j - 3], grid[i][j - 2], grid[i][j - 1], grid[i][j]};
						}
					} else {
						product *= addedNumber;
						if (j - firstMember == nrOfMultiples - 1 && product > largestProduct) {
							largestProduct = product;
							largestMembers = new int[] {grid[i][j - 3], grid[i][j - 2], grid[i][j - 1], grid[i][j]};
						}
							
					}
				}
				System.out.printf("%02d ", grid[i][j]);
			}
			System.out.print("Largest largestProduct: " + NumberFormat.getNumberInstance(Locale.US).format(largestProduct) + " members : " + Arrays.toString(largestMembers));
			System.out.println("");
		}
		System.out.println();
		return largestProduct;
	}

	private static int[][] readGrid(Path file) {
		int[][] grid = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {

			int lineNr = 0;
			for (String line; (line = br.readLine()) != null; lineNr++) {
				String[] numbers = line.split("\\s+");
				if (grid == null) {
					grid = new int[numbers.length][numbers.length];
				}
				for (int i = 0; i < numbers.length; i++) {
					grid[lineNr][i] = Integer.valueOf(numbers[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return grid;
	}
}
