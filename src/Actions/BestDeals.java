package Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BestDeals {

	public static void TopDeals(Map<String, Double> products) {
		PriorityQueue<Product> queue = new PriorityQueue<>();
		for (Map.Entry<String, Double> entry : products.entrySet()) {
			queue.add(new Product(entry.getKey(), entry.getValue()));
		}
		for (int i = 0; i < 5; i++) {
			Product bestDeal = queue.poll();
			System.out.println(bestDeal.getName() );
		}
	}

}

class Product implements Comparable<Product> {

	private String name;
	private double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public int compareTo(Product other) {
		return Double.compare(this.price, other.price);
	}
}
