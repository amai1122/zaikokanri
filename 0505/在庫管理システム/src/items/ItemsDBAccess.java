package items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDBAccess {
	//------------------------------------------------

	public List<ItemsBean> itemsDBAccessSelect(String item_id, String dbCom){
		//DBからitemsテーブルの商品マスタ一覧の表示または編集時の表示

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;
		List<ItemsBean> itemList = new ArrayList<ItemsBean>();

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//SQL文字列(all= update= )
			if ("all".equals(dbCom)) {
				//商品マスタの表示なら全部持ってくる
				sqlStr = "SELECT * FROM items;";
			} else if ("update".equals(dbCom)) {
				//編集画面なら指定IDのデータだけ持ってくる
				sqlStr = "SELECT * From items WHERE item_id =?";
			} else if ("search".equals(dbCom)) {
				//商品名で検索(item_idに商品名が入る)
				sqlStr = "SELECT * From items WHERE item_name LIKE ?";
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
				bn.setItem_id(rs.getString("item_id"));
				bn.setItem_name(rs.getString("item_name"));
				bn.setItem_price(rs.getInt("item_price"));
				bn.setCost_price(rs.getInt("cost_price"));
				bn.setStock_qty(rs.getInt("stock_qty"));

				itemList.add(bn);
			}

		}catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return itemList;
	}

	//------------------------------------------------
	//DBへ保存（新規または更新）

	public void itemsDBAccessInsert(String item_id, String item_name, String item_price, String cost_price,
			String stock_qty) {
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
			sqlStr = "SELECT item_id FROM items WHERE item_id =?";
			psID = con.prepareStatement(sqlStr);
			psID.setString(1, item_id);
			// SQL文実行
			rsID = psID.executeQuery();

			String str="";
			while(rsID.next()){
				str=rsID.getString("item_id");
			}
			if (item_id.equals(str)) {
				//IDがあれば更新
				sqlStr = "UPDATE items SET item_id= ?,item_name= ?,item_price =? ,cost_price= ? WHERE item_id = ?";
			} else {
				//IDが無ければ新規登録
				sqlStr = "INSERT INTO items(item_id,item_name,item_price,cost_price)VALUES(?, ?, ?, ?)";
			}

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			//バインド変数パラメータ
			int index = 1;
			if (item_id.equals(str)) {
				pstmt.setString(index++, item_id);
				pstmt.setString(index++, item_name);
				pstmt.setInt(index++, Integer.parseInt(item_price));
				pstmt.setInt(index++, Integer.parseInt(cost_price));
				pstmt.setString(index++, item_id);
			} else {
				pstmt.setString(index++, item_id);
				pstmt.setString(index++, item_name);
				pstmt.setInt(index++, Integer.parseInt(item_price));
				pstmt.setInt(index++, Integer.parseInt(cost_price));
			}
			//SQL実行
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
			}catch (SQLException e) {
                e.printStackTrace();
            }

		}
	}

	//------------------------------------------------
	//情報の削除
	public void itemsDBAccessDelete(String item_id) throws Exception {
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
			sqlStr = "DELETE FROM items WHERE item_id =?";

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			pstmt.setString(index, item_id);

			//SQL実行
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
	//------------------------------------------------
	//在庫数の更新
	public void itemsStockDBAccess(String item_id,int stock_qty) {

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//在庫数更新
			sqlStr = "UPDATE items SET stock_qty=? WHERE item_id = ?";

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			pstmt.setInt(index++, stock_qty);
			pstmt.setString(index++, item_id);

			//SQL実行
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}

}
