package Actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class CompareTheProduct {
	public static void main(String[] args) {
		// this is the path for collection website file.
		String collectionfile = "saveonfoods.txt";
		// Pattern for collection file.[1]
		String patternForCollection = ".+?\\|\\s(.+?)\\s\\|\\s(\\$[\\d.]+)";

		// this is the path for yupik website file.
		String yupikfile = "yupik.txt";
		// Pattern for yupik website file.[1]
		String patternForYupik = ".+?\\|\\s(.+?)\\s\\|\\s(\\$[\\d.]+)";
		Scanner scanner = new Scanner(System.in);
		System.out.println("==========Compare the price==========" + "\n");
		System.out.print("Enter product name: ");
		String productInfo = scanner.nextLine();

		// Select the website either collection or yupik
		System.out.println("==========Comparison Between Collection & Yupik==========");
		System.out.println("Press 1 for Yes");
//		System.out.println("2. Yupik");
		// which website you want to enter either 1(collection) or 2(yupik)
		int productType = scanner.nextInt();
		scanner.nextLine();

		// store information about product name in collection website[8]
		String collectionProductInfo = "";
		// store information about product price in collection website[8]
		String collectionPriceInfo = "";

		// store information about product name in yupik website[8]
		String yupikProductInfo = "";
		// store information about product price in yupik website[8]
		String yupikPriceInfo = "";

		// I used bufferreader because it is faster, It reads data from collection
		// website every line(inside the folder(file)),[4].
		try (BufferedReader collection = new BufferedReader(new FileReader(collectionfile))) {
			String inputOfCollection;
			// while loop continues to reading the file(using matches), if there in no lines
			// left, it
			// returns null
			// and each line stored in input.
			while ((inputOfCollection = collection.readLine()) != null) {
				// pattern is searches for specific text patterns within the line, that defined
				// in the collection website
				Pattern eachLinePatternCollection = Pattern.compile(patternForCollection);
				// it matches the operation with the help of pattern (that was stored in
				// inputOfCollection)
				Matcher eachLineMatchesCollection = eachLinePatternCollection.matcher(inputOfCollection);

				// In collections website, if we find input string & if the first group contains
				// the product information(in case sensitive(I used .lowercase))
				// if it matches with the product info then we have to grasp 1. Info(group(1))
				// and 2. price(group(2)).[Lecture-9]
				if (eachLineMatchesCollection.find()&& eachLineMatchesCollection.group(1).toLowerCase().contains(productInfo.toLowerCase())) {
					// add it in the Info and Price(collection website).
					collectionProductInfo = eachLineMatchesCollection.group(1);
					collectionPriceInfo = eachLineMatchesCollection.group(2);
					break;
				}
			}
			// Check lists are not empty, if products found, it will print product found in
			// collection
			// and then product information and price will print,
			if (!collectionProductInfo.isEmpty() && !collectionPriceInfo.isEmpty()) {
				System.out.println("Product found in Collections:");
				System.out.println(collectionProductInfo + " : " + collectionPriceInfo);
				// if product is list is empty then print product not found
			} else {
				System.out.println("Sorry, we don't have product in Collections");
			}
		} catch (IOException e) { // IO Exception
			System.out.println("Error reading file: " + e.getMessage());
		}
		// I used bufferreader because it is faster, It reads data from yupik
		// website every line(inside the folder(file)),[4].
		try (BufferedReader br2 = new BufferedReader(new FileReader(yupikfile))) {
			String inputOfYupik;
			// while loop continues to reading the file(using matches), if there in no lines
			// left, it
			// returns null
			// and each line stored in input.
			while ((inputOfYupik = br2.readLine()) != null) {
				// pattern is searches for specific text patterns within the line, that defined
				// in the yupik website
				Pattern eachLinePatternCollection = Pattern.compile(patternForYupik);
				// it matches the operation with the help of pattern (that was stored in
				// inputOfCollection)
				Matcher eachLineMatchesYupik = eachLinePatternCollection.matcher(inputOfYupik);
				
				// In yupik website, if we find input string & if the first group contains
				// the product information(in case sensitive(I used .lowercase))
				// Also, if it matches with the product info then we have to extract 1. Info(group(1))
				// and 2. price(group(2)).[Lecture-9]
				if (eachLineMatchesYupik.find() && eachLineMatchesYupik.group(1).toLowerCase().contains(productInfo.toLowerCase())) {
					yupikProductInfo = eachLineMatchesYupik.group(1);
					yupikPriceInfo = eachLineMatchesYupik.group(2);
					break;
				}
			}
			
			if (!yupikProductInfo.isEmpty() && !yupikPriceInfo.isEmpty()) {
				System.out.println("Product found in Yupik");
				System.out.println(collectionProductInfo + " : " + yupikPriceInfo);
			} else {
				System.out.println("Sorry, we don't have product in Yupik");
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		// It Compare the prices, for example if price of collection is less then print
		// collection if not then print yupik.[5][6][7]
		if (!collectionPriceInfo.isEmpty() && !yupikPriceInfo.isEmpty()) {
			double priceOfCollection = Double.parseDouble(collectionPriceInfo.replace("$", ""));
			double priceOfYupik = Double.parseDouble(yupikPriceInfo.replace("$", ""));
			String cheaper = (priceOfCollection < priceOfYupik) ? "Collections" : "Yupik";
			System.out.println("The cheaper price is in " + cheaper);
		}

		scanner.close();
	}
}