package beans;

public class ArticleInCart {
	private Article article;
	private int qauantity;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQauantity() {
		return qauantity;
	}

	public void setQauantity(int qauantity) {
		this.qauantity = qauantity;
	}

	public ArticleInCart(Article article, int qauantity) {
		super();
		this.article = article;
		this.qauantity = qauantity;
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
