package items;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewItems extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/items/itemsInsert.jsp";


		request.setCharacterEncoding("UTF-8");

		//新規なので空欄で登録画面に遷移
		request.setAttribute("item_id", "");
		request.setAttribute("item_name","");
		request.setAttribute("item_price", "");
		request.setAttribute("cost_price", "");
		request.setAttribute("stock_qty", "");

		// パスをセット
        String path = NEXTPAGE_PATH;

        // itemsInsert.jspに遷移
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
	}

}
