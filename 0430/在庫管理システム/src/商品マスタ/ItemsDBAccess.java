package 商品マスタ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemsDBAccess {
	//------------------------------------------------

	protected List<ItemsBean> itemsDBAccessSelect(String item_id, String dbCom)
			throws Exception {
		//DBからitemsテーブルの商品マスタ一覧の表示または編集時の表示

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;
		List<ItemsBean> list = new ArrayList<ItemsBean>();

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "foot", "");

			//SQL文字列(all= update= )
			if ("all".equals(dbCom)) {
				//商品マスタの表示なら全部持ってくる
				sqlStr = "SELECT * From items;";
			} else if ("update".equals(dbCom)) {
				//編集画面なら指定IDのデータだけ持ってくる
				sqlStr = "SELECT * From items WHERE item_id = '" + item_id + "';";
			}
			// プリペアドステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			// SQL文実行
			rs = pstmt.executeQuery();

			//戻り値のlistにセット
			while (rs.next()) {
				ItemsBean bn = new ItemsBean();
				bn.setItem_id(rs.getString(0));
				bn.setItem_name(rs.getString(1));
				bn.setItem_price(rs.getInt(2));
				bn.setCost_price(rs.getInt(3));
				bn.setStock_qty(rs.getInt(4));

				list.add(bn);
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	//------------------------------------------------
	//DBへ保存（新規または更新）
	protected void itemsDBAccessInsert(String item_id, String item_name, String item_price, String cost_price,
			String stock_qty) throws Exception {
		//データベースへの新規登録または編集

		//データベースの検索
		Connection con = null;
		PreparedStatement psID = null;
		PreparedStatement pstmt = null;
		ResultSet rsID = null;
		ResultSet rs = null;
		String sqlStr = null;

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			psID = con.prepareStatement("SELECT items FROM item_id WHERE item_name ='" + item_id + "';");
			// SQL文実行
			rsID = psID.executeQuery();

			if (rsID != null) {
				//IDがあれば更新
				sqlStr = "UPDATE items SET item_id='" + item_id + "',item_name='" + item_name
						+ "',item_price="+ item_price + ",cost_price="+ cost_price
						+ "WHERE itemid ='" + item_id + "';";
			} else {
				//IDが無ければ新規登録
				sqlStr = "INSERT INTO items(item_id,item_name,item_price,cost_price)VALUES('" + item_id + "','"
						+ item_name + "'," + item_price + ","+ cost_price + ");";
			}

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			//SQL実行
			pstmt.executeUpdate();

		} finally {
			if (rsID != null) {
				rsID.close();
			}
			if (psID != null) {
				psID.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	//------------------------------------------------
	//情報の削除
	protected void itemsDBAccessDelete(String item_id) throws Exception {
		//商品データの削除

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//削除
			sqlStr = "DELETE FROM item_id WHERE itemid ='" + item_id + "';";

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			//SQL実行
			pstmt.executeUpdate();

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

}
