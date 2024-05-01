package com.keshaw.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienReporsitory {
	
	List<Alien> aliens = new ArrayList<Alien>();
	
	Connection con = null;
	public AlienReporsitory() {	
		String url = "jdbc:postgresql://localhost:5432/RestAPI";
		String username = "postgres";
		String password = "0000";
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Alien> getAliens() throws SQLException{	
		
		List<Alien> alien = new ArrayList<Alien>();
		Statement stmt = con.createStatement();

		String query = "select * from RestAPIDemo";
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			Alien a = new Alien();
			a.setName(rs.getString(1));
			a.setId(rs.getInt(2));
			a.setPoints(rs.getInt(3));
			
			alien.add(a);
		}
		
		return alien;
	}
	
	public Alien getAlienById(int id) throws SQLException {
		Statement stmt = con.createStatement();
		Alien a = new Alien();
		
		String query = "select * from RestAPIDemo where id = '"+id+"'";
		
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			a.setName(rs.getString(1));
			a.setId(rs.getInt(2));
			a.setPoints(rs.getInt(3));
		}
		//System.out.println(Table.read().db(rs).print());
		return a;
	}
	
	public String addAlien(Alien alien) throws SQLException {
		String sql = "insert into RestAPIDemo values (?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, alien.getName());
		st.setInt(2, alien.getId());
		st.setInt(3, alien.getPoints());
		
		int status = st.executeUpdate();
		
		return (status >= 1 ? "Added " + status + " Rows": "Not Added");
	}
	
	public String updateAlien(Alien alien) throws SQLException {
		String sql = "update RestAPIDemo set name = ?, points = ? where id =?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, alien.getName());
		st.setInt(2, alien.getPoints());
		st.setInt(3, alien.getId());
		
		int status = st.executeUpdate();
		
		return (status >= 1 ? "Updated "+ status + " Rows": "Not Updated");
	}

	public String deleteAlien(int id) throws SQLException {
		String sql = "delete from RestAPIDemo where id =?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int status = st.executeUpdate();
		
		return (status >=1 ? "Deleted "+status+" Rows" : "Not deleted");
	}

}
