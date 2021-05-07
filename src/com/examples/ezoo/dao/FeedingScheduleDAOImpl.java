package com.examples.ezoo.dao;
import java.util.ArrayList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.resource.cci.ResultSet;

import com.examples.ezoo.model.FeedingSchedule;
import com.examples.ezoo.model.Animal;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
	
	@Override
	public void addFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO feeding_schedules VALUES(?,?,?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, feedingSchedule.getScheduleID());
			stmt.setString(2,feedingSchedule.getFeedingTime());
			stmt.setString(3,feedingSchedule.getRecurrence());
			stmt.setString(4,feedingSchedule.getFood());
			stmt.setString(5,feedingSchedule.getNotes());
			
			success= stmt.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success ==0) {
			throw new Exception("Insert feedingSchedule failed: "+feedingSchedule);
		}
	}
	
	@Override
	public void deleteFeedingSchedule(Long feedingScheduleID) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM feeding_schedules WHERE schedule_id =?";
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, feedingScheduleID);
			success = stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(connection!=null)
					connection.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			if(success ==0)
				throw new Exception ("Deleting feedingSchedule failed");
		}
	}
	
	
	
	@Override
	public List<FeedingSchedule> getAllFeedingSchedules(){
		List<FeedingSchedule> feedingSchedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM feeding_schedules";
			
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				FeedingSchedule a = new FeedingSchedule();
				
				a.setScheduleID(rs.getLong("schedule_id"));
				a.setFeedingTime(rs.getString("feeding_time"));
				a.setRecurrence(rs.getString("recurrence"));
				a.setFood(rs.getString("food"));
				a.setNotes(rs.getString("notes"));
				
				feedingSchedules.add(a);
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!= null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return feedingSchedules;
	}
	
	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		Connection connection = null;
		PreparedStatement stmt = null;
		FeedingSchedule a = new FeedingSchedule();

		try {
			connection = DAOUtilities.getConnection();
			String sql="SELECT * FROM feeding_schedules WHERE feedingScheduleID=?";
			stmt= connection.prepareStatement(sql);
			stmt.setLong(1, animal.getFeedingScheduleID());
			java.sql.ResultSet rs= stmt.executeQuery();
			
			a.setScheduleID(rs.getLong("schedule_id"));
			a.setFeedingTime(rs.getString("feeding_time"));
			a.setRecurrence(rs.getString("recurrence"));
			a.setFood(rs.getString("food"));
			a.setNotes(rs.getString("notes"));
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!= null)
					stmt.close();
				if(connection!=null)
					connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
	@Override
	public void assignFeedingSchedule(Long feedingScheduleID,Long animalID) throws Exception{
		Connection connection = null;
		PreparedStatement stmt= null;
		int success =0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=? WHERE animalid=?";
			stmt=connection.prepareStatement(sql);
			stmt.setLong(1, feedingScheduleID);
			stmt.setLong(2, animalID);
			success = stmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(connection!=null)
					connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(success==0)
				throw new Exception("FeedingSchedule was not assigned to animal");
		}
	}
//	@Override
//	public void assignFeedingSchedule(FeedingSchedule feedingSchedule,
//			Animal animal) throws Exception{
//		Connection connection = null;
//		PreparedStatement stmt= null;
//		int success =0;
//		try {
//			connection = DAOUtilities.getConnection();
//			String sql = "UPDATE animals SET feeding_schedule=? WHERE animalid=?";
//			stmt=connection.prepareStatement(sql);
//			stmt.setLong(1, feedingSchedule.getScheduleID());
//			stmt.setLong(2, animal.getFeedingScheduleID());
//			success = stmt.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(stmt!=null)
//					stmt.close();
//				if(connection!=null)
//					connection.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
//			if(success==0)
//				throw new Exception("FeedingSchedule was not assigned to animal: "+animal);
//		}
//	}
	
	@Override
	public void removeFeedingSchedule(Long animalID) throws Exception{
		Connection connection = null;
		PreparedStatement stmt= null;
		int success= 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql="UPDATE animals SET feeding_schedule=null WHERE animalid=?";
			stmt= connection.prepareStatement(sql);
			stmt.setLong(1, animalID);
			success= stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(connection!=null)
					connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			if(success==0)
				throw new Exception("FeedingSchedule was not removed from the animal");
			
			
		}
		
	}
	@Override
	public void updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception{
		Connection connection = null;
		PreparedStatement stmt= null;
		int success= 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql="UPDATE feeding_schedules SET feeding_time=?, recurrence=?,food=?,notes=? WHERE schedule_id=?";
			stmt= connection.prepareStatement(sql);
			stmt.setLong(5, feedingSchedule.getScheduleID());
			stmt.setString(1,feedingSchedule.getFeedingTime());
			stmt.setString(2,feedingSchedule.getRecurrence());
			stmt.setString(3,feedingSchedule.getFood());
			stmt.setString(4,feedingSchedule.getNotes());
			
			
			success= stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(connection!=null)
					connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			if(success==0)
				throw new Exception("FeedingSchedule was not updated"+feedingSchedule);
			
			
		}
		
	}
	
	
}
