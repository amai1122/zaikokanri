package items;
//商品の登録または編集をDBに行う
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert_Items extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/servlet/items.ItemInitialize";
		final String ERROR_PATH = "/servlet/items.NewItems";

		request.setCharacterEncoding("UTF-8");

		//リクエストから値をセット
		String item_id = request.getParameter("item_id");
		String item_name = request.getParameter("item_name");
		String item_price = request.getParameter("item_price");
		String cost_price =request.getParameter("cost_price");
		String stock_qty =request.getParameter("stock_qty");


		 // 取得したデータの入力値チェック
        List<String> errorList = new ArrayList<String>();
        if (item_id.equals("") || item_id == null) {
            errorList.add("「商品ID」を入力してください");
        }else if(item_id.length() != 5) {
        	errorList.add("「商品ID」は5桁で入力してください");
        }
        if (item_name.equals("") || item_name == null) {
            errorList.add("「商品名」を入力してください");
        }else if(item_name.length() > 255) {
        	errorList.add("「商品名」が長すぎます");
        }
        if (item_price.equals("") || item_price == null) {
            errorList.add("「販売単価」を入力してください");
        }else {
			try {
				Integer.parseInt(item_price);
			} catch (NumberFormatException e) {
				errorList.add("「販売単価」は整数値を入力してください");
			}
        }
        if (cost_price.equals("") || cost_price == null) {
            errorList.add("「商品原価」を入力してください");
        }else {
			try {
				Integer.parseInt(cost_price);
			} catch (NumberFormatException e) {
				errorList.add("「商品原価」は整数値を入力してください");
			}
        }
     // errorListオブジェクトをrequestにセット
        request.setAttribute("errorList", errorList);

        String path="";
        if (errorList.isEmpty()) {
			path = NEXTPAGE_PATH;
			ItemsDBAccess insertDB = new ItemsDBAccess();
			insertDB.itemsDBAccessInsert(item_id, item_name, item_price,cost_price,stock_qty);
        } else {
            path = ERROR_PATH;
        }

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
