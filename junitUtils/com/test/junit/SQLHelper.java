package com.test.junit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SQLHelper {
	public static final String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名scutcs
	public static final String url = "jdbc:mysql://dfafeaf:3306/xjs_money_ssj?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true";
//	private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
//	private static Date now = new Date();
//	private static Calendar c = Calendar.getInstance();
	
	
	public SQLHelper() {
	}
	
	private static Connection getConnection(){
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,"root","短发呢啊发发");
			return conn;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void execute( String testCaseType, String methodName, String caseName ) {
//		PreparedStatement ps;
//		try {
//			String data = SyncServiceTestBase.loadFromFile( testCaseType, methodName, caseName );
//			String[] sqls = data.split( ";" );
//			for( String sql : sqls ) {
//				ps = getConnection().prepareStatement( sql );
//				ps.execute();
//			}
//		} catch( SQLException e ) {
//			e.printStackTrace();
//		}
//	}
	
	public static void execute( String...config ) {
		StringBuffer sb = new StringBuffer();
		for( String str : config ) {
			sb.append(File.separator).append( str );
		}
		String path = sb.toString().substring( 1 );
		PreparedStatement ps;
		try {
			String data = SyncServiceTestBase.loadFromFile( path );
			String[] sqls = data.split( ";" );
			for( String sql : sqls ) {
				ps = getConnection().prepareStatement( sql );
				ps.execute();
			}
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
}
