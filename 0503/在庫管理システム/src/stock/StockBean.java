package stock;

public class StockBean {

	private String move_id;
	private String reason_id;
	private String move_date;
	private String item_id;
	private String item_name;
	private int item_price;
	private int cost_price;
	private int stock_qty;
	private String in_or_out;
	private int move_qty;

	/**
	 * @return move_id
	 */
	public String getMove_id() {
		return move_id;
	}
	/**
	 * @param move_id セットする move_id
	 */
	public void setMove_id(String move_id) {
		this.move_id = move_id;
	}
	/**
	 * @return reason_id
	 */
	public String getReason_id() {
		return reason_id;
	}
	/**
	 * @param reason_id セットする reason_id
	 */
	public void setReason_id(String reason_id) {
		this.reason_id = reason_id;
	}
	/**
	 * @return move_date
	 */
	public String getMove_date() {
		return move_date;
	}
	/**
	 * @param move_date セットする move_date
	 */
	public void setMove_date(String move_date) {
		this.move_date = move_date;
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
	public int getItem_price() {
		return item_price;
	}
	/**
	 * @param item_price セットする item_price
	 */
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	/**
	 * @return cost_price
	 */
	public int getCost_price() {
		return cost_price;
	}
	/**
	 * @param cost_price セットする cost_price
	 */
	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}
	/**
	 * @return stock_qty
	 */
	public int getStock_qty() {
		return stock_qty;
	}
	/**
	 * @param stock_qty セットする stock_qty
	 */
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	/**
	 * @return in_or_out
	 */
	public String getIn_or_out() {
		return in_or_out;
	}
	/**
	 * @param in_or_out セットする in_or_out
	 */
	public void setIn_or_out(String in_or_out) {
		this.in_or_out = in_or_out;
	}
	/**
	 * @return move_qty
	 */
	public int getMove_qty() {
		return move_qty;
	}
	/**
	 * @param move_qty セットする move_qty
	 */
	public void setMove_qty(int move_qty) {
		this.move_qty = move_qty;
	}

}
