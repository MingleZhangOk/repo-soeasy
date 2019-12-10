package com.mingzhang.table.util;

import com.mingzhang.table.parameter.CommonParameter;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtils {

	private static Properties properties = new Properties();
	private static DataSource dataSource;
	
	public static void initDbcp() throws Exception{
		try {
			 FileInputStream is = new FileInputStream(CommonParameter.confFilePath + "dbcp.properties");
//			 FileInputStream is = new FileInputStream(System.getProperty("user.dir") + File.separator + "conf" + File.separator + "dbcp.properties");
			 properties.load(is);
			 dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new Exception("初始化连接失败", e);
		}
	}
	
	/**
	 * 从连接池中获取连接
	 * @param
	 */
	public synchronized static Connection getConnection() throws Exception{
		Connection connection=null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			throw new Exception("获取连接失败", e);
		}
		return connection;
	}
	/**
	 * 执行Sql语句
	 * @param connection
	 * @param sql
	 */
	public static void executeSql(Connection connection,String sql) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1));
				break;
			}
		} catch (Exception e) {
			throw new Exception("执行sql出错",e);
		}finally {
			
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				throw new Exception("关闭连接出错",e);
			}
		}
	}
	
}
