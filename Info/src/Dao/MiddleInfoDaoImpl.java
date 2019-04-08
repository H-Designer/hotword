package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.MiddleInfo;
import Util.DBUtil;

public class MiddleInfoDaoImpl {
	public static void add(MiddleInfo middleInfo) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "insert into mInfo values(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, middleInfo.getTitle());
			preparedStatement.setString(2, middleInfo.getContent());
			preparedStatement.setString(3, middleInfo.getWebadd());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	public static List<MiddleInfo> load() {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<MiddleInfo> middleInfos = new ArrayList<>();
		MiddleInfo middleInfo = null;
		String sql = "select * from mInfo";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				middleInfo = new MiddleInfo();
				middleInfo.setTitle(resultSet.getString("title"));
				middleInfo.setContent(resultSet.getString("content"));
				middleInfo.setWebadd(resultSet.getString("webadd"));
				middleInfos.add(middleInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return middleInfos;
	}
}
