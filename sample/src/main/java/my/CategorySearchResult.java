package my;

public class CategorySearchResult {

	private String largeCategoryId;

	private String middleCategoryId;

	private Category[] result;

	public String getLargeCategoryId() {
		return largeCategoryId;
	}

	public void setLargeCategoryId(String largeCategoryId) {
		this.largeCategoryId = largeCategoryId;
	}

	public String getMiddleCategoryId() {
		return middleCategoryId;
	}

	public void setMiddleCategoryId(String middleCategoryId) {
		this.middleCategoryId = middleCategoryId;
	}

	public Category[] getResult() {
		return result;
	}

	public void setResult(Category[] result) {
		this.result = result;
	}
}
