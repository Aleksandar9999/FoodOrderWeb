package beans;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

	public List<Article> articles;
	public Buyer buyer;
	public double price;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public Cart(List<Article> articles, Buyer buyer, double price) {
		super();
		this.articles = articles;
		this.buyer = buyer;
		this.price = price;
	}
	
}
