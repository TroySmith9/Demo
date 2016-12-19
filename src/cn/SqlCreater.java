package cn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by yiban on 2016/10/28.
 */
public class SqlCreater {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context-util.xml");
	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

	public static void main(String[] args) {
		try {
			init();
//			String sql=" show tables ";
			String sql="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
			SqlRowSet rowset=jdbcTemplate.queryForRowSet(sql);
			System.out.println("*******");
			List<String> tableList=new ArrayList<String>();
			while (rowset.next()){
				tableList.add(rowset.getString(1));
			}
			for (String tableName:tableList){
				String querySql="select * from "+tableName+" limit 1";
				SqlRowSet sqlRowSet= jdbcTemplate.queryForRowSet(querySql);
				sqlRowSet.getMetaData();
			}
//			String tableName = "t_user";
//			String nameSpace = "user";
//			String[] columns = getColumns(tableName);
//			StringBuilder stringBuilder = new StringBuilder(getSqlMapHeader(nameSpace));
//			stringBuilder.append(createInsertSql(columns,tableName)).toString();
//			stringBuilder.append("\n</sqlMap>");

//			outPutFile(tableName, stringBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void outPutFile(String tableName, StringBuilder string) throws IOException {
		String path = new File("").getAbsolutePath() + File.separator + tableName+".xml";
		FileWriter fw=new FileWriter(new File(path));
		fw.write(string.toString());
		fw.close();
	}

	private static void init() {
		applicationContext = new ClassPathXmlApplicationContext("spring-context-util.xml");
		jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
	}

	private static String getSqlMapHeader(String nameSpace) {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
		stringBuilder.append("<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\" >\n");
		stringBuilder.append("<sqlMap namespace=\"").append(nameSpace).append("\">\n");
		return stringBuilder.toString();
	}

	private static String[] getColumns(String tableName) {
		String sql = " SELECT  * FROM  " + tableName + " limit 1 ";
		// ResultSet result = statement.executeQuery(sql);
		// ResultSetMetaData metadata = (ResultSetMetaData)
		// result.getMetaData();
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		jdbcTemplate.query(sql, rcch);
		return rcch.getColumnNames();
	}

	private static String createInsertSql(String[] columns, String tableName) {

		StringBuilder sb = new StringBuilder("<sql id=\"insert\">\n");
		sb.append(" insert into "+tableName+" (");
		sb.append(StringUtils.join(columns, ",")).append(" ) \n values (#");
		sb.append(StringUtils.join(columns, "#, #")).append("#)\n").append("</sql>");
		return sb.toString();
	}

}
