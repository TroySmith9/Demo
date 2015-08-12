package com.cn.ssj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/*******************************************
 * SqliteHelper.java
 * @author  xiong.xing
 * @version 0.1
 * @date     2010-11-1
 *******************************************/
public class SqliteHelper {

	private Connection con;

	private Map<String, Statement> batchsStates = new LinkedHashMap<String, Statement>();

	private List<Statement> batchsStates1 = new ArrayList<Statement>();

	/**
	 * sql语句对应的参数,用于生成sql脚本
	 */
	private Map<String, List<Object[]>> batchsSqls = new LinkedHashMap<String, List<Object[]>>();

	/**
	 * 是否需要写入sql日志
	 */
	private boolean writeSqlLog = true;

	public void setWriteSqlLog( boolean writeSqlLog ) {
		this.writeSqlLog = writeSqlLog;
	}

	public SqliteHelper( String fileName ) throws SQLException, ClassNotFoundException {
		Class.forName( "org.sqlite.JDBC" );

		con = DriverManager.getConnection( "jdbc:sqlite:" + fileName );

		con.setAutoCommit( false );
	}

	public List<Map<String, String>> queryList( String sqlId, Object... params ) throws SQLException
	{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ResultSet rs = executeQuery( sqlId, params );

		ResultSetMetaData rsmd = rs.getMetaData();
		/** 取得字段总数 */
		int len = rsmd.getColumnCount();
		while( rs.next() )
		{
			Map<String, String> map = new HashMap<String, String>( len );
			for( int i = 1; i <= len; i++ )
			{
				String strColumnName = removeDBPrefix( rsmd.getColumnName( i ).toString().toLowerCase() );
				Object objValue = rs.getObject( i );
				map.put( strColumnName, getString( objValue ) );
			}
			/** 加入列表 */
			list.add( map );
		}
		rs.close();
		return list;
	}

	public Map<String, String> queryMap( String sqlId, Object... params ) throws SQLException
	{
		Map<String, String> map = new HashMap<String, String>( 1 );
		ResultSet rs = executeQuery( sqlId, params );
		ResultSetMetaData rsmd = rs.getMetaData();
		/** 取得字段总数 */
		int len = rsmd.getColumnCount();
		while( rs.next() )
		{
			for( int i = 1; i <= len; i++ )
			{
				String strColumnName = removeDBPrefix( rsmd.getColumnName( i ).toString().toLowerCase() );
				Object objValue = rs.getObject( i );
				map.put( strColumnName, getString( objValue ) );
			}
		}
		rs.close();
		return map;
	}

	/**
	 * 查询
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public SqliteResultSet executeQuery( String sqlId, Object... params ) throws SQLException {
		PreparedStatement ps = null;
		ps = con.prepareStatement( sqlId );
		prepareParams( ps, params );
		ResultSet rs = ps.executeQuery();

		return new SqliteResultSet( rs );
	}

	public Object getObject( String sqlId, Object... params ) throws SQLException {
		ResultSet rs = executeQuery( sqlId, params );
		rs.next();
		Object obj = rs.getObject( 1 );
		rs.close();

		return obj;
	}

	/**
	 * 添加批处理sql语句
	 * @param sqlId
	 * @param params
	 * @throws SQLException 
	 */
	public void addBatchSql( String[] sqlIds, List<Object[]> params ) throws SQLException {
		if( sqlIds == null )
			return;

		for( int i = 0; i < sqlIds.length; i++ ) {
			String sql = sqlIds[ i ];
			if( StringUtils.isEmpty( sql ) )
				continue;

			Object[] objs = null;
			if( params != null )
				objs = params.get( i );

			if( objs == null ) {
				this.addBatch( sql );
			} else {
//				this.addBatch( sql, objs );
			}

		}
	}

	/**
	 * 添加原始SQL批处理
	 * @param sql
	 * @throws SQLException
	 */
	public void addOriginalSql( String sql ) throws SQLException {
		// Log.info("OriginalSql : " + sql);
		/*
		 * Statement state = batchsStates.get(sql); if(state == null){ state =
		 * con.createStatement(); batchsStates.put(sql, state); }
		 * state.addBatch(sql);
		 */
		Statement state = con.createStatement();
		state.addBatch( sql );
		batchsStates1.add( state );
	}

	/**
	 * 立即执行的sql
	 * @param sql
	 * @throws SQLException 
	 */
	public void executeImmediately( String sql, Object... params ) throws SQLException {
		con.setAutoCommit( true );
		PreparedStatement ps = con.prepareStatement( sql );
		prepareParams( ps, params );
		ps.execute();
		ps.close();
		ps = null;

		con.setAutoCommit( false );
	}

	/**
	 * 此方法用于添加动态组装的SQL(如果参数为null时SQL执行无效)，因数据迁移需要而产生  WJH20120326
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public void addBatchSql( String sql, Object[] params ) throws SQLException {
		PreparedStatement ps = ( PreparedStatement )batchsStates.get( sql );
		if( ps == null ) {
			ps = con.prepareStatement( sql );
			batchsStates.put( sql, ps );
		}
	
		prepareParams( ps, params );
		ps.addBatch();
	
		// addSqls(sql, params);
		// 使用不上，如果要使用需要修改SqlLogGenerateSynchronizer.formatSql(..)方法
	}

	/**
	 * 此方法用于添加带参数的原始SQL(如果参数为null时SQL执行无效)
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public void addBatchSql1( String sql, Object[] params ) throws SQLException {
		PreparedStatement ps = con.prepareStatement( sql );
		prepareParams( ps, params );
		ps.addBatch();
		batchsStates1.add( ps );
	
		// addSqls(sql, params);
		// 使用不上，如果要使用需要修改SqlLogGenerateSynchronizer.formatSql(..)方法
	}

	/**
	 * 不需要参数的操作
	 * @param sql
	 * @throws SQLException
	 */
	private void addBatch( String sqlId ) throws SQLException {
		// Log.info("add sql:" + sqlMapper.getSql());
		/*
		 * Statement state = batchsStates.get(sqlId); if(state == null){ state =
		 * con.createStatement(); batchsStates.put(sqlId, state); }
		 * state.addBatch(sqlMapper.getSql());
		 */
		Statement state = con.createStatement();
		state.addBatch(sqlId);
		batchsStates1.add( state );

		addSqls( sqlId, null );
	}

//	private void addBatch( String sqlId, Object[] params ) throws SQLException {
//		// Log.info("add sql:" + sqlMapper.getSql());
//		/*
//		 * PreparedStatement ps = (PreparedStatement) batchsStates.get(sqlId);
//		 * if(ps == null){ ps = con.prepareStatement(sqlMapper.getSql());
//		 * batchsStates.put(sqlId, ps); } Object[] realParams =
//		 * sqlMapper.prepareParams(params); prepareParams(ps, realParams);
//		 * ps.addBatch();
//		 */
//		PreparedStatement ps = con.prepareStatement(sqlId);
//		Object[] realParams = sqlMapper.prepareParams( params );
//		prepareParams( ps, realParams );
//		ps.addBatch();
//		batchsStates1.add( ps );
//
//		addSqls( sqlId, realParams );
//	}

	/**
	 * 添加sql - param用于生成sql
	 * @param sqlId
	 * @param params
	 */
	private void addSqls( String sqlId, Object[] params ) {
		if( !writeSqlLog )
			return;

		List<Object[]> objs = batchsSqls.get( sqlId );
		if( objs == null ) {
			objs = new ArrayList<Object[]>();
		}

		objs.add( params );

		batchsSqls.put( sqlId, objs );
	}

	private void prepareParams( Statement state, Object[] params ) throws SQLException {
		if( params != null ) {
			PreparedStatement ps = ( PreparedStatement )state;
			for( int i = 0; i < params.length; i++ ) {
				ps.setObject( i + 1, params[ i ] );
			}
			// printParams(params);
		}
	}


	/**
	 * 提交修改
	 * @throws SQLException 
	 */
	public void commit() throws SQLException {
		for( Statement ps : batchsStates1 ) {
			ps.executeBatch();
		}
		batchsStates1.clear();

		for( Statement ps : batchsStates.values() ) {
			ps.executeBatch();
		}

		batchsStates.clear();

		con.commit();
	}

	public void close() {
		try {
			if( batchsStates1 != null ) {
				for( Statement ps : batchsStates1 ) {
					ps.close();
				}
				batchsStates1 = null;
			}

			if( batchsStates != null ) {
				for( Statement ps : batchsStates.values() ) {
					ps.close();
				}
				batchsStates = null;
			}
			if( con != null ) {
				con.close();
				con = null;
			}
		} catch( SQLException e ) {
		}
	}

	public Map<String, List<Object[]>> getBatchsSqls() {
		return batchsSqls;
	}

	private static final boolean isEmpty( String str ) {
		if( str == null || str.trim().length() == 0 ) {
			return true;
		}

		return false;
	}

	private static final String removeDBPrefix( String columnName ) {
		int len = columnName.indexOf( "." );
		if( len > 0 )
		{
			return columnName.substring( len + 1 );
		}
		return columnName;
	}

	private static final String getString( Object obj ) {
		String str = null;
		if( obj instanceof String ) {
			str = ( String )obj;
		}
		else {
			str = String.valueOf( obj );
		}

		if( isEmpty( str ) ) {
			return "";
		}
		return str.trim();
	}

}
