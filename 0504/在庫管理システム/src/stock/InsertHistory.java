package stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertHistory extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/servlet/sotck.StockInitialize";
		final String ERROR_PATH = "/servlet/stock.NewStockMove";

		request.setCharacterEncoding("UTF-8");

		//リクエストから値をセット
		String move_id = request.getParameter("move_id");
		String reason_id = request.getParameter("reason_id");
		String move_date = request.getParameter("move_date");
		String item_id = request.getParameter("item_id");
		String item_name = request.getParameter("item_name");
		int item_price = Integer.valueOf(request.getParameter("item_price"));
		int cost_price =Integer.valueOf(request.getParameter("cost_price"));
		int stock_qty =Integer.valueOf(request.getParameter("stock_qty"));
		String in_or_out =request.getParameter("in_or_out");
		int move_qty =Integer.valueOf(request.getParameter("move_qty"));


		 // 取得したデータの入力値チェック
        List<String> errorList = new ArrayList<String>();
        if (move_id.equals("") || move_id == null) {
            errorList.add("「入出庫ID」を入力してください");
        }
        if (reason_id.equals("") || reason_id == null) {
            errorList.add("「理由」を選択してください");
        }
        if (move_date.equals("") || move_date == null) {
            errorList.add("「入出庫日」を入力してください");
        }
        if (in_or_out.equals("") || in_or_out == null) {
            errorList.add("「入庫/出庫」を選択してください");
        }
        if (move_qty== 0) {
            errorList.add("「入出庫数」を入力してください");
        }
     // errorListオブジェクトをrequestにセット
        request.setAttribute("errorList", errorList);

        String path="";
        if (errorList.isEmpty()) {
			path = NEXTPAGE_PATH;
			StockDBAccess insertDB = new StockDBAccess();
			insertDB.stockDBAccessInsert(move_id,reason_id,move_date,item_id, item_name, item_price,cost_price,stock_qty,in_or_out,move_qty);
        } else {
            path = ERROR_PATH;
        }

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
