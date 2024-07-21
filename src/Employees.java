import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employees {
	Connection conn = SQLiteConnection.getConnection();
	private int id;
	private String full_name;
	private String address;
	private String class_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public Employees() {

	}

	public Employees(String full_name) {
		this.full_name = full_name;
	}

	public Employees(int id, String full_name, String address) {
		this.id = id;
		this.full_name = full_name;
		this.address = address;
	}

	public Employees(int id, String full_name, String address, String class_name) {
		this.id = id;
		this.full_name = full_name;
		this.address = address;
		this.class_name = class_name;
	}

	public boolean insert() {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO employees (id, full_name, address, class_name) " + "VALUES " + "(" + id + ", '"
					+ full_name + "'" + ", '" + address + "'" + ", '" + class_name + "'" + ")";
			stmt.executeUpdate(sql);

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			SQLiteConnection.Close();
		}
		return true;
	}

	public ArrayList<Employees> getListEmployees() {
		ArrayList<Employees> listEmp = new ArrayList<>();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "Select * from employees order by id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Employees(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"),
						rs.getString("class_name")));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			SQLiteConnection.Close();
		}
		return listEmp;
	}

	public boolean update() {

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE employees SET full_name='" + full_name + "', address ='" + address
					+ "', class_name = '" + class_name + "' WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			SQLiteConnection.Close();
		}
		return true;
	}

	public boolean delete() {

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM employees WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			SQLiteConnection.Close();
		}

		return true;
	}

	public ArrayList<Employees> findStudent() {
		ArrayList<Employees> listEmp = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employees WHERE id = " + this.id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Employees(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"),
						rs.getString("class_name")));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			SQLiteConnection.Close();
		}

		return listEmp;
	}

	public ArrayList<Employees> findStudentWithName() {
		ArrayList<Employees> listEmp = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employees WHERE LOWER(full_name) like '%" + full_name.toLowerCase() + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Employees(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"),
						rs.getString("class_name")));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			SQLiteConnection.Close();
		}

		return listEmp;
	}

//	public ArrayList<Classes> findClass() {
//		ArrayList<Classes> listEmp = new ArrayList<>();
//		Statement stmt;
//		try {
//			stmt = conn.createStatement();
//			String sql = "SELECT * FROM employees WHERE id = " + this.id;
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				listEmp.add(new Employees(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"), rs.getString("class_name")));
//			}
//			rs.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			
//		} 
//		finally {
//			SQLiteConnection.Close();
//		}
//		
//		return listEmp;
//	}

}
