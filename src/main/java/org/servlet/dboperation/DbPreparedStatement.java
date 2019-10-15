package org.servlet.dboperation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.servlet.constant.DBConstant;
import org.servlet.util.LogUtils;

public class DbPreparedStatement {
	private static LogUtils log=new LogUtils(DbPreparedStatement.class.getSimpleName());
	 
	public static PreparedStatement getPreparedStatement(String sqlQuery) {
		PreparedStatement psmt=null;
		try {
			Class.forName(DBConstant.DBCLASSNAME);
			Connection conn=DriverManager.getConnection(DBConstant.DBURL,DBConstant.DBUSER,DBConstant.DBPASSWORD);
			psmt=conn.prepareStatement(sqlQuery);
			
		} catch(SQLException e) {
			log.error("Sql Exception "+e.getMessage());;
		} catch(Exception e) {
			log.catching(e);
		}
		return psmt;
		
	}

}
