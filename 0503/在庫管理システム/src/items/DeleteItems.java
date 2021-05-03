package items;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItems extends HttpServlet {
// 	削除ボタン押下時

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//削除は元の画面に戻る
		final String NEXTPAGE_PATH = "../servlet/items.ItemInitialize";


		request.setCharacterEncoding("UTF-8");

		//該当の商品IDの情報をDBから消す
		try {
			ItemsDBAccess deleteItem = new ItemsDBAccess();

			String item_id = request.getParameter("item_id");

			deleteItem.itemsDBAccessDelete(item_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// パスをセット
		String path = NEXTPAGE_PATH;
		path = java.net.URLDecoder.decode(path, "UTF-8");

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
