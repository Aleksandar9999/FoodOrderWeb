package beans;

public class ArticleInCart {
	private Article article;
	private int quantity;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQauantity() {
		return quantity;
	}

	public void setQauantity(int qauantity) {
		this.quantity = qauantity;
	}

	public ArticleInCart(Article article, int qauantity) {
		super();
		this.article = article;
		this.quantity = qauantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleInCart other = (ArticleInCart) obj;
		if (other.getArticle().getId().equals(article.getId()))
			return true;
		return false;
	}

}
