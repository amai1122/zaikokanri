package items;

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
				sqlStr = "SELECT * From items WHERE item_id = ?";
			}else if ("search".equals(dbCom)) {
				//商品名で検索(item_idに商品名が入る)
				sqlStr = "SELECT * From items WHERE item_name LIKE ? ";
			}
			// プリペアドステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			if ("all".equals(dbCom)) {
				//バインド変数なし
			} else if ("update".equals(dbCom)) {
				pstmt.setString(index, item_id);
			} else if ("search".equals(dbCom)) {
				pstmt.setString(index, "%" + item_id + "%");
			}

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
		String sqlStr = null;

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			psID = con.prepareStatement("SELECT items FROM item_id WHERE item_name = ? ");
			psID.setString(1, item_id);
			// SQL文実行
			rsID = psID.executeQuery();

			if (rsID != null) {
				//IDがあれば更新
				sqlStr = "UPDATE items SET item_id= ? ,item_name= ? ,item_price = ? ,cost_price= ? WHERE itemid = ?";
			} else {
				//IDが無ければ新規登録
				sqlStr = "INSERT INTO items(item_id,item_name,item_price,cost_price)VALUES( ?, ?, ?, ?)";
			}

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			if (rsID != null) {
				pstmt.setString(index++, item_id);
				pstmt.setString(index++, item_name);
				pstmt.setString(index++, item_price);
				pstmt.setString(index++, cost_price);
				pstmt.setString(index++, item_id);
			} else {
				pstmt.setString(index, item_id);
				pstmt.setString(index++, item_name);
				pstmt.setString(index++, item_price);
				pstmt.setString(index++, cost_price);
			}

			//SQL実行
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (rsID != null) {
				rsID.close();
			}
			if (psID != null) {
				psID.close();
			}

			if (con != null) {
				con.close();
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
			sqlStr = "DELETE FROM item_id WHERE itemid = ?";

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			pstmt.setString(index, item_id);

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
