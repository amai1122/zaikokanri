package stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.ItemsBean;
import items.ItemsDBAccess;

public class NewHistory extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/stock/stockInsert.jsp";

		request.setCharacterEncoding("UTF-8");

		// DBの引数を取得
		String item_id = request.getParameter("item_id");

		final String dbcom = "update";//

		//商品テーブルからデータを取得
		try {
			ItemsDBAccess up = new ItemsDBAccess();
			List<ItemsBean> itemsList = up.itemsDBAccessSelect(item_id, dbcom);
			System.out.println("IDからデータを取得");

			//リストから商品ID、商品名、商品単価をリクエストにセット
			String item_name = "";
			int item_price = 0;
			int cost_price = 0;
			int stock_qty = 0;

			for (ItemsBean stock : itemsList) {
				item_id = stock.getItem_id();
				item_name = stock.getItem_name();
				item_price = stock.getItem_price();
				cost_price = stock.getCost_price();
				stock_qty = stock.getStock_qty();
			}

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
