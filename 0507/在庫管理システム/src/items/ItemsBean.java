package items;

public class ItemsBean {

	// serialVersionUIDを指定
    private static final long serialVersionUID = 1L;

	private String item_id;//商品ID
	private String item_name;//商品名
	private String item_price;//販売単価
	private String cost_price;//商品原価
	private String stock_qty;//在庫数

    public ItemsBean() {
    	item_id = "";
    	item_name = "";
    	item_price = "0";
    	cost_price = "0";
    	stock_qty = "0";
    }

	/**
	 * @return item_id
	 */
	public String getItem_id() {
		return item_id;
	}

	/**
	 * @param item_id セットする item_id
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	/**
	 * @return item_name
	 */
	public String getItem_name() {
		return item_name;
	}

	/**
	 * @param item_name セットする item_name
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	/**
	 * @return item_price
	 */
	public String getItem_price() {
		return item_price;
	}

	/**
	 * @param item_price セットする item_price
	 */
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	/**
	 * @return cost_price
	 */
	public String getCost_price() {
		return cost_price;
	}

	/**
	 * @param cost_price セットする cost_price
	 */
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}

	/**
	 * @return stock_qty
	 */
	public String getStock_qty() {
		return stock_qty;
	}

	/**
	 * @param stock_qty セットする stock_qty
	 */
	public void setStock_qty(String stock_qty) {
		this.stock_qty = stock_qty;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
