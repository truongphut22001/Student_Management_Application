import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Classes {
	Connection conn = SQLiteConnection.getConnection();
	private int class_id;
	private String class_name;

	public int getClassId() {
		return class_id;
	}

	public void setClassId(int class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public Classes() {

	}

	public Classes(String class_name) {
		this.class_name = class_name;
	}

	public Classes(int class_id, String class_name) {
		this.class_id = class_id;
		this.class_name = class_name;
	}

	public boolean insert() {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO classes (class_id, class_name) " + "VALUES " + "(" + class_id + ", '" + class_name
					+ "'" + ")";
			stmt.executeUpdate(sql);

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			SQLiteConnection.Close();
		}
		return true;
	}

	public ArrayList<Classes> getListEmployees() {
		ArrayList<Classes> listEmp = new ArrayList<>();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "Select * from classes order by class_id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Classes(rs.getInt("class_id"), rs.getString("class_name")));
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
			String sql = "UPDATE classes SET class_name = '" + class_name + "' WHERE class_id = " + class_id;
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
			String sql = "DELETE FROM classes WHERE class_id = " + class_id;
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			SQLiteConnection.Close();
		}

		return true;
	}

	public ArrayList<Classes> findClass() {
		ArrayList<Classes> listEmp = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM classes WHERE class_id = " + this.class_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Classes(rs.getInt("class_id"), rs.getString("class_name")));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			SQLiteConnection.Close();
		}

		return listEmp;

	}

	public ArrayList<Classes> findClassWithName() {
		ArrayList<Classes> listEmp = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM classes WHERE LOWER(class_name) like '%" + class_name.toLowerCase() + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listEmp.add(new Classes(rs.getInt("class_id"), rs.getString("class_name")));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			SQLiteConnection.Close();
		}

		return listEmp;
	}
}
