package com.airhacks;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySQLTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private MySQL mysql;
	
	public void connect() {
		mysql = new MySQL();
		mysql.load();
		mysql.connect();
	}
	
	@Test
	public void testACreate() {
		connect();
		PreparedStatement ps;
		try {
			mysql.create("Fabrizio", "Martin", "Martinez");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBRead() {
		connect();
		try {
			ResultSet rs = mysql.read();

			while(rs.next()) {
				System.out.println(rs.getString("nombre") + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCUpdate() {
		connect();
		mysql.update(1, "Nombre: " + Math.random(), "gomez", "ramirez");
	}
	
	@Test
	public void testDDelete() {
		connect();
		mysql.delete("Fabrizio");
	}
	
	@Test
	public void testERead() {
		connect();
		try {
			ResultSet rs = mysql.readById(1);

			while(rs.next()) {
				System.out.println("Obj: " + rs.getString("nombre") + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
