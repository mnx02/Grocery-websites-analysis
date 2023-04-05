package Group5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.interactions.Actions;

import Actions.SpellChecking;
import Actions.WordCompletion;
import Actions.BestDeals;
import Actions.DataValidation;
import Actions.FrequencyCount;
import Actions.InvertedIndexing;
import Actions.PageRanking;
import Actions.ProductCategoryList;
import Actions.SearchFrequency;
import HtmlParsing.HtmlParsingSaveOnFoods;
import HtmlParsing.HtmlParsingShopFoodEx;
import HtmlParsing.HtmlParsingYupik;
import Webcrawling.WebCrawlerSaveOnFoods;
import Webcrawling.WebCrawlerShopFoodEx;
import Webcrawling.WebCrawlerYupik;

public class IndexFile {

	public static void main(String[] args) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		// Websites ;links to Crawl
		String url_Yupik = "https://yupik.com/en/";
		String url_ShopFoodEx = "https://www.shopfoodex.com/";
		String url_SaveOnFoods = "https://www.saveonfoods.com/";
		// Create a HashTable of all the links crawled
		Hashtable<String, String> url_Map = new Hashtable<String, String>();
		url_Map.putAll(WebCrawlerYupik.startWebCrawling(url_Yupik));
		url_Map.putAll(WebCrawlerShopFoodEx.startWebCrawling(url_ShopFoodEx));
		url_Map.putAll(WebCrawlerSaveOnFoods.startWebCrawling(url_SaveOnFoods));

		Scanner inp = new Scanner(System.in);

		System.out.println("Do you wish to perform data validation. If Yes then press Y");
		String dataValidate = inp.nextLine();
//		if (dataValidate.equals("Y") || dataValidate.equals("y")) {
//			// Data validation with regular expression
//			DataValidation.dataValidation();
//		}
		System.out.println("Parsing the website data. Please wait...");
//		HtmlParsingShopFoodEx.parse();
//		HtmlParsingYupik.parse();
//		HtmlParsingSaveOnFoods.parse();
		System.out.println("Done with parsing");
		String ch = "N";
		String[] listOfCategory = ProductCategoryList.getCategories();
		Map<String, Double> products = new HashMap<String, Double>();
//		PageRanking.
		do {
			do {
				System.out.println("\nChoose the option\n1. Get of Products by Category\n2. Search product");
				switch (inp.nextLine()) {
				case "1":
					System.out.println("List of common categories");
					for (int i = 0; i < listOfCategory.length; i++) {
						System.out.println(i + 1 + ". " + listOfCategory[i]);
					}
					System.out.println("Select the category");
					ch = inp.nextLine();

					System.out.println("List of products:");
					products = ProductCategoryList.getListOfProductByCategory(listOfCategory[Integer.parseInt(ch) - 1]);
					System.out.println("\nTop 5 products:");
					BestDeals.TopDeals(products);
					System.out.println("\nFrequency Count:");
					InvertedIndexing.Indexing(url_Map, listOfCategory[Integer.parseInt(ch) - 1]);
					SearchFrequency.SearchCount(listOfCategory[Integer.parseInt(ch) - 1]);
					break;
				case "2":
					boolean repeat = true;
					while (repeat) {
						System.out.println("Enter product name");
						String product = inp.nextLine();
						boolean spellCheck = SpellChecking.SpellChecker(product);
						
						if (!spellCheck) {
							System.out.println("Please check the spelling");
							List<String> suggestedWords = WordCompletion.findSimilarWords(product);
							System.out.println("\nSome suggested words are:");
							for (String word : suggestedWords) {
								System.out.println(word);
							}
						} else {
							System.out.println("List of products: (Frequency: "+FrequencyCount.Frequency(product)+")");
							products = ProductCategoryList.getSimilarProducts(product);
							System.out.println("\nTop 5 products:");
							BestDeals.TopDeals(products);
							System.out.println("\nFrequency Count:");
							InvertedIndexing.Indexing(url_Map, product);
							SearchFrequency.SearchCount(product);
							repeat = false;
						}
					}
					break;
				default:
					System.out.println("Choose correct option\nPress y to continue");
					ch = inp.nextLine();
					break;
				}
			} while (ch.equals("Y") || ch.equals("y"));

			System.out.println("\nPress Y to continue");
			ch = inp.nextLine();
		} while (ch.equals("Y") || ch.equals("y"));
		System.out.println("end");

	}

}
