package memcache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private  Connection con = null;
	private  PreparedStatement pstmt = null;
	private  ResultSet rs = null;
	private  String url = "jdbc:mysql://localhost:3306/test";
	private  String user = "root";
	private  String password = "mysql"; 
	private User u = null;
	
	public  Object queryInfoByName(String name){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement("select * from user where name = ?");
			if(!"".equals(name)){
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				if(rs.next()){
					u  = new User();
					u.setId(rs.getString("id"));
					u.setName(rs.getString("name"));
					u.setAge(rs.getString("age"));
					u.setAddress(rs.getString("address"));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return u != null ? u : null;
	}
}	
