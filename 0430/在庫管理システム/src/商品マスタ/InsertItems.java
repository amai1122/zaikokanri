package 商品マスタ;
//商品の登録または編集をDBに行う
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertItems extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/JSP/itemsMaster.jsp";

		request.setCharacterEncoding("UTF-8");

		// 入力データを取得

		try {
			//リクエストから値をセット
			String item_id = request.getParameter("item_id");
			String item_name = request.getParameter("item_name");
			String item_price = request.getParameter("item_price");
			String cost_price =request.getParameter("cost_price");
			String stock_qty =request.getParameter("stock_qty");



			ItemsDBAccess insertDB = new ItemsDBAccess();
			insertDB.itemsDBAccessInsert(item_id, item_name, item_price,cost_price,stock_qty);

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
