package items;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ItemInitialize extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/items/itemsMaster.jsp";

		//現在登録済みの商品データを表示のために取得する
		try {
			//入力チェック

			ItemsDBAccess itemSet = new ItemsDBAccess();
			List<ItemsBean> itemList = itemSet.itemsDBAccessSelect("00000","all");

			//リストからリクエストにセット
			request.setAttribute("itemList", itemList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// パスをセット
		String path = NEXTPAGE_PATH;
		path = java.net.URLDecoder.decode(path, "UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
