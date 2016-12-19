package cn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiban on 2016/11/2.
 */
public class AutocrementColumnsUtils {

	public static void main(String[] args) {
		try {
			// 驱动
			String driver = "com.mysql.jdbc.Driver";
			// 数据库连接
			String url = "jdbc:mysql://10.201.3.31:3232/test_money_new";
			// 用户名
			String user = "SSJ_feidee";
			// 数据库密码
			String password = "Hf#df_6c#b7,d8d#2ee6_fe85H3d";
			// 加载驱动
			Class.forName(driver);
			// 获取链接
			Connection connection = DriverManager.getConnection(url, user, password);
			// 创建查询声明

			List<String> tableList = getTableNames(connection);

			for (String tableName : tableList) {
                    dealTable(connection,tableName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

    private static void dealTable(Connection connection, String tableName) throws SQLException {
        String sql = " SELECT  * FROM  "+ tableName +" limit 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        // 获取各个列的信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        System.out.println();
        System.out.print(tableName+"  ");
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            if (metaData.isAutoIncrement(i)){
                System.out.print(metaData.getColumnName(i)+" ");
            }
        }
    }

    /**
	 * 获取所有表
	 * @return
	 * @param connection
	 */
	private static List<String> getTableNames(Connection connection) throws SQLException {
		List<String> tableList = new ArrayList<String>();
		String sql = " show tables ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			tableList.add(resultSet.getString(1));
		}
		return tableList;
	}

}
