package stock;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.ItemsBean;
import items.ItemsDBAccess;

public class NewStock extends HttpServlet {
	//行先指定
	final static private String NEXTPAGE_PATH = "/jsp/stock/stockInsert.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// DBの引数を取得
		String item_id = request.getParameter("item_id");


		//商品テーブルからデータを取得
		try {
			ItemsDBAccess up = new ItemsDBAccess();
			ItemsBean stock = up.itemsDBAccessSelectByID(item_id);

			//リストから商品ID、商品名、商品単価をリクエストにセット
			String item_name = "";
			String item_price = "0";
			String cost_price = "0";
			String stock_qty = "0";


			item_id = stock.getItem_id();
			item_name = stock.getItem_name();
			item_price = stock.getItem_price();
			cost_price = stock.getCost_price();
			stock_qty = stock.getStock_qty();


			request.setAttribute("move_id", "");
			request.setAttribute("reason_id", "");
			request.setAttribute("move_date", "");
			request.setAttribute("item_id", item_id);
			request.setAttribute("item_name", item_name);
			request.setAttribute("item_price", item_price);
			request.setAttribute("cost_price", cost_price);
			request.setAttribute("stock_qty", stock_qty);
			request.setAttribute("in_or_out", "");
			request.setAttribute("move_qty", "0");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// パスをセット
		String path = NEXTPAGE_PATH;

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
