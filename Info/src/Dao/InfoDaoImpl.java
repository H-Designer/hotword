package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Info;
import Util.DBUtil;

public class InfoDaoImpl {
	public static void add(Info info) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "insert into Info values(?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, info.getName());
			preparedStatement.setString(2, info.getType());
			preparedStatement.setString(3, info.getInformation());
			preparedStatement.setString(4, info.getTitle());
			preparedStatement.setString(5, info.getWebadd());
			preparedStatement.setString(6, info.getNews());
			preparedStatement.setString(7, info.getNewsadd());
			preparedStatement.setInt(8, 1);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	public static Info load(String name) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Info info = null;
		String sql = "select * from Info where name='"+name+"'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				info = new Info();
				info.setName(resultSet.getString("name"));
				info.setType(resultSet.getString("type"));
				info.setInformation(resultSet.getString("information"));
				info.setTitle(resultSet.getString("title"));
				info.setWebadd(resultSet.getString("webadd"));
				info.setNews(resultSet.getString("news"));
				info.setNewsadd(resultSet.getString("newsadd"));
				info.setSum(resultSet.getInt("tsum"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return info;
	}
	public static void update(Info info) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "update Info set type=?,information=?,title=?,news=?,newsadd=? where name=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, info.getType());
			preparedStatement.setString(2, info.getInformation());
			preparedStatement.setString(3, info.getTitle());
			preparedStatement.setString(4, info.getNews());
			preparedStatement.setString(5, info.getNewsadd());
			preparedStatement.setString(6, info.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	public static List<Info> load() {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Info> infos = new ArrayList<>();
		Info info = null;
		String sql = "select * from Info order by tsum desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				info = new Info();
				info.setName(resultSet.getString("name"));
				info.setType(resultSet.getString("type"));
				info.setInformation(resultSet.getString("information"));
				info.setTitle(resultSet.getString("title"));
				info.setWebadd(resultSet.getString("webadd"));
				info.setNews(resultSet.getString("news"));
				info.setNewsadd(resultSet.getString("newsadd"));
				info.setSum(resultSet.getInt("tsum"));
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return infos;
	}
	//查询有多少种类
	public static int getTypeNum() {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int num = 0;
		String sql = "SELECT count(DISTINCT type) num from Info";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				num = resultSet.getInt("num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return num;
	}
	//查询相同种类的数据
	public static List<Info> loadByType(String type) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Info> infos = new ArrayList<>();
		Info info = null;
		String sql = "select * from Info where type='"+type+"'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				info = new Info();
				info.setName(resultSet.getString("name"));
				info.setType(resultSet.getString("type"));
				info.setInformation(resultSet.getString("information"));
				info.setTitle(resultSet.getString("title"));
				info.setWebadd(resultSet.getString("webadd"));
				info.setNews(resultSet.getString("news"));
				info.setNewsadd(resultSet.getString("newsadd"));
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return infos;
	}
	//获取种类
	public static List<String> getAllType() {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> types = new ArrayList<>();
		String sql = "select distinct(type) from Info";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				types.add(resultSet.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return types;
	}
}
