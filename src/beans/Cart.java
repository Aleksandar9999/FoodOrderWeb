package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import DAO.CartDAO;

public class Cart extends Entity{

	public List<ArticleInCart> articles;
	public Buyer buyer;
	public double price;
	public Cart(Buyer buyer) {
		price=0;
		articles=new ArrayList<ArticleInCart>();
		this.buyer=buyer;
	}


	public Cart(List<ArticleInCart> articles, Buyer buyer, double price) {
		super(UUID.randomUUID().toString());
		this.articles = articles;
		this.buyer = buyer;
		this.price = price;
	}
	public Cart(CartDAO dao) {
		super(dao.getId());
		this.articles = dao.getArticles();
		this.price = dao.getPrice();
	}

	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public double getPrice() {
		this.price=0.0;
		for (ArticleInCart articleInCart : articles) {
			this.price+=articleInCart.getQauantity()*articleInCart.getArticle().getPrice();
		}
		return this.price;
	}
	
	public List<ArticleInCart> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleInCart> articles) {
		this.articles = articles;
	}
	
	public void addArticle(ArticleInCart article) {
		if(articles==null)
			articles=new ArrayList<ArticleInCart>();
		articles.add(article);
	}
	
	public void updateArticle(ArticleInCart article) {
		articles.remove(articles.indexOf(article));
		articles.add(article);
	}
	public void deleteArticle(ArticleInCart article) {
		articles.remove(articles.indexOf(article));
	}
	
}
