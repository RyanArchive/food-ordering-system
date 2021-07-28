// Ordering System

import java.util.Scanner;

public class OrderingSystem {
	public static void main(String[] args) {
		String[] menu = {"Adobo", "Ampalaya", "Menudo", "Sisig", "Kare-kare"};
		double[] prices = {50, 25, 45, 40, 46.5};
		Scanner sc = new Scanner(System.in);
		int[] quantity = {0, 0, 0, 0, 0};
		double[] totalPerMenu = {0, 0, 0, 0, 0};
		int orderIndex = 1;										// Arrangement of order
		int[] orderArrangement = {0, 0, 0, 0, 0};				// Container for arrangement of order
		double totalPrice = 0;

		showMenu(menu, prices);

		do {
			orderFood(sc, quantity, prices, totalPerMenu, orderArrangement, orderIndex);
			orderIndex++;
		} while (orderAgain(sc));

		showReceipt(orderArrangement, menu, quantity, totalPerMenu, totalPrice, sc);
	}

	public static void showMenu(String[] menu, double[] prices) {
		System.out.println();

		for (int x = 1; x <= menu.length; x++)
			System.out.println(x + ". " + menu[x - 1] + "\tPhp. " + prices[x - 1]);

		System.out.println();
	}

	public static void orderFood(Scanner sc, int[] quantity, double[] prices, double[] totalPerMenu, int[] orderArrangement, int orderIndex) {
		int order;

		do {
			System.out.print("Select your order: ");
			order = sc.nextInt();
		} while (!(order == 1 || order == 2 || order == 3 || order == 4 || order == 5));

		System.out.print("Quantity: ");
		int qty = sc.nextInt();

		// Punching the order
		for (int x = 1; x <= order; x++) {
			if (order == x) {
				quantity[x - 1] += qty;
				totalPerMenu[x - 1] += (prices[x - 1] * qty);

				// What has been bought first will be in the first in the receipt
				if (orderArrangement[x - 1] == 0)
					orderArrangement[x - 1] = orderIndex;
			}
		}
	}

	public static Boolean orderAgain(Scanner sc) {
		String again = "";

		do {
			System.out.print("Do you want to order again[y/n]: ");
			again = sc.next();
		} while (!(again.equalsIgnoreCase("y") || again.equalsIgnoreCase("n")));

		return again.equalsIgnoreCase("y");
	}

	public static void showReceipt(int[] orderArrangement, String[] menu, int[] quantity, double[] totalPerMenu, double totalPrice, Scanner sc) {
		double payment, change;
		totalPrice = totalPerMenu[0] + totalPerMenu[1] + totalPerMenu[2] + totalPerMenu[3] + totalPerMenu[4];

		System.out.println("\nItem Name\tQTY\tPrice");

		for (int x = 0; x <= 4; x++)
			for (int y = 0; y <= 4; y++)
				if (orderArrangement[y] == (x + 1))
					System.out.println(menu[y] + "   \t" + quantity[y] + "\tPhp. " + totalPerMenu[y]);

		System.out.println("\nTotal\t\t\tPhp. " + totalPrice + "\n");	
			
		do {
			System.out.print("Enter Payment: ");
			payment = sc.nextDouble();

			if (payment >= totalPrice) {
				change = payment - totalPrice;
				System.out.println("Change: Php. " + change);
			}
		} while (payment < totalPrice);
	}
}