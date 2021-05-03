package items;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品ID選択時
 */
public class UpdateItems extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/items/itemsInsert.jsp";

		request.setCharacterEncoding("UTF-8");

		// DBの引数を取得
		String item_id = request.getParameter("item_id");

		final String dbcom ="update";//

		//該当IDの商品情報をDBから取得
		try {
			ItemsDBAccess up = new ItemsDBAccess();
			List<ItemsBean> itemsList = up.itemsDBAccessSelect(item_id, dbcom);
			System.out.println("IDからデータを取得");

			//リストから商品ID、商品名、商品単価をリクエストにセット
			for (ItemsBean item : itemsList) {
				request.setAttribute("item_id", item.getItem_id());
				request.setAttribute("item_name",item.getItem_name());
				request.setAttribute("item_price", item.getItem_price());
				request.setAttribute("cost_price", item.getCost_price());
				request.setAttribute("stock_qty", item.getStock_qty());
			}
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
