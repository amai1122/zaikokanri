package items;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品ID選択時
 */
public class UpdateItems extends HttpServlet {
	//行先指定
	final static private String NEXTPAGE_PATH = "/jsp/items/itemsInsert.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// DBの引数を取得
		String item_id = request.getParameter("item_id");

		//該当IDの商品情報をDBから取得
		try {
			ItemsDBAccess up = new ItemsDBAccess();
			ItemsBean items = up.itemsDBAccessSelectByID(item_id);

			//リストから商品ID、商品名、商品単価をセッションにセット
			request.setAttribute("item_id", items.getItem_id());
			request.setAttribute("item_name",items.getItem_name());
			request.setAttribute("item_price", items.getItem_price());
			request.setAttribute("cost_price", items.getCost_price());
			request.setAttribute("stock_qty", items.getStock_qty());

			request.setAttribute("flag", "update");

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
