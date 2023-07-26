package com.hit.entities;

/*
 * Product entity.
 */
public class Product extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private String title;
	private String image;
	private Integer price;

	/*
	 * Initializes product object instaince using given parameters.
	 */
	public Product(String title, String image, Integer price) {
		this.title = title;
		this.image = image;
		this.price = price;
	}

	/*
	 * Gets product's price.
	 */
	public Integer getPrice() {
		return price;
	}

	/*
	 * sets product's price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/*
	 * Gets title's price.
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * sets title's price
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * Gets image's price.
	 */
	public String getImage() {
		return image;
	}

	/*
	 * sets image's price
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/*
	 * @inheritdoc
	 */
	@Override
	public Integer defaultValue() {
		return 1;
	}

	/*
	 * @inheritdoc
	 */
	@Override
	public Integer nextValue(Integer value) {
		return value + 1;
	}
}