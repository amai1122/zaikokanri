package items;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemSearch extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/items/itemsMaster.jsp";

		request.setCharacterEncoding("UTF-8");

		String item_name =request.getParameter("itemSearch");
		//現在登録済みの商品データを表示のために取得する

		ItemsDBAccess itemSet = new ItemsDBAccess();
		List<ItemsBean> itemList = itemSet.itemsDBAccessSelect(item_name,"search");

		//リストからリクエストにセット
		request.setAttribute("itemList", itemList);
		System.out.println("検索結果をリクエストにセット");

		// パスをセット
		String path = NEXTPAGE_PATH;
		path = java.net.URLDecoder.decode(path, "UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
