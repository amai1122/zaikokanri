package stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.ItemsDBAccess;

public class InsertHistory extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "../servlet/stock.StockInitialize";
		final String ERROR_PATH = "/jsp/stock/stockInsert.jsp";

		request.setCharacterEncoding("UTF-8");

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
		if ("".equals(move_id) || move_id == null) {
			errorList.add("「入出庫ID」を入力してください");
		}else if (move_id.length() != 7) {
			errorList.add("「入出庫ID」は7桁で入力してください");
		}
		StockDBAccess check = new StockDBAccess();
		String duplicateCheck=check.insertCheckDBAccess(move_id);
		if ("NG".equals(duplicateCheck)) {
			errorList.add("「入出庫ID」が重複しています");
		}
		if ("".equals(reason_id) || reason_id == null) {
			errorList.add("「理由」を選択してください");
		}
		if ("".equals(move_date) || move_date == null) {
			errorList.add("「入出庫日」を入力してください");
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
			request.setAttribute("move_id", "");
			request.setAttribute("reason_id", "");
			request.setAttribute("move_date", "");
			request.setAttribute("item_id", item_id);
			request.setAttribute("item_name", item_name);
			request.setAttribute("item_price", item_price);
			request.setAttribute("cost_price", cost_price);
			request.setAttribute("stock_qty", stock_qty);
			request.setAttribute("in_or_out", "");
			request.setAttribute("move_qty", "0");
		}

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
