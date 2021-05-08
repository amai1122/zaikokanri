package stock;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import items.ItemsDBAccess;

public class InsertStock extends HttpServlet {
	//行先指定
	final static private String NEXTPAGE_PATH = "../servlet/stock.StockInitialize";
	final static private String ERROR_PATH = "../servlet/stock.NewStock";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// セッションオブジェクト生成
		HttpSession session = request.getSession();

		//リクエストから値をセット
		String move_id = request.getParameter("move_id");
		String reason_id = request.getParameter("reason_id");
		String move_date = request.getParameter("move_date");
		String item_id = request.getParameter("item_id");
		String item_name = request.getParameter("item_name");
		int item_price = Integer.valueOf(request.getParameter("item_price"));
		int cost_price = Integer.valueOf(request.getParameter("cost_price"));
		int stock_qty = Integer.valueOf(request.getParameter("stock_qty"));
		String in_or_out = request.getParameter("in_or_out");
		String move_qtyCheck = request.getParameter("move_qty");
		int move_qty = 0;

		// 取得したデータの入力値チェック
		List<String> errorList = new ArrayList<String>();
		StockDBAccess check = new StockDBAccess();
		String duplicateCheck=check.insertCheckDBAccess(move_id);
		if ("".equals(move_id) || move_id == null) {
			errorList.add("「入出庫ID」を入力してください");
		}else if (move_id.length() != 7) {
			errorList.add("「入出庫ID」は7桁で入力してください");
		}else if ("NG".equals(duplicateCheck)) {
			errorList.add("「入出庫ID」が重複しています");
		}
		if ("".equals(reason_id) || reason_id == null) {
			errorList.add("「理由」を選択してください");
		}
		if ("".equals(move_date) || move_date == null) {
			errorList.add("「入出庫日」を入力してください");
		}else {//日付チェック
			try {
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				df.setLenient(false);
				df.parse(move_date);
			}catch(ParseException e){
				errorList.add("「入出庫日」は日付をyyyy/mm/dd形式で入力してください");
			}
		}

		if ("".equals(in_or_out) || in_or_out == null) {
			errorList.add("「入庫/出庫」を選択してください");
		}
		if ("".equals(move_qtyCheck) || move_qtyCheck == null) {
			errorList.add("「入出庫数」を入力してください");
		}else {
			try {
				move_qty = Integer.parseInt(move_qtyCheck);
			} catch (NumberFormatException e) {
				errorList.add("「入出庫数」は整数値を入力してください");
			}
			if("－".equals(in_or_out) && move_qty > stock_qty) {
				errorList.add("「入出庫数」に対し「在庫数」が足りません");
			}
		}
		// errorListオブジェクトをrequestにセット
		request.setAttribute("errorList", errorList);

		String path = "";
		if (errorList.isEmpty()) {
			//在庫数の計算して入力する
			if("+".equals(in_or_out)) {
				stock_qty += move_qty;
			}else {
				stock_qty -= move_qty;
			}

			path = NEXTPAGE_PATH;
			StockDBAccess insertDB = new StockDBAccess();
			insertDB.stockDBAccessInsert(move_id, reason_id, move_date, item_id, item_name, item_price, cost_price,
					stock_qty, in_or_out, move_qty);
			//商品の在庫を更新
			ItemsDBAccess stockDB = new ItemsDBAccess();
			stockDB.itemsStockDBAccess(item_id, stock_qty);

		} else {
			path = ERROR_PATH;
			//セッションで入力値を戻す
            // InputBeanオブジェクト生成
            StockBean stock = new StockBean();
            stock.setMove_id(move_id);
            stock.setReason_id(reason_id);
            stock.setMove_date(move_date);
            stock.setItem_id(item_id);
            stock.setItem_name(item_name);
            stock.setItem_price(item_price);
            stock.setCost_price(cost_price);
            stock.setStock_qty(stock_qty);
            stock.setIn_or_out(in_or_out);
            stock.setMove_qty(move_qty);
			session.setAttribute("stock",stock);
			request.setAttribute("flag", "error");
		}

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
