package StudentServlet;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import data_access.ConnectionParameters;
import data_access.DbUtils;



public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);

	}

	public List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<Student> studentList = new ArrayList<Student>();
		

		try {
			
			connection = openConnection();
			String txtSQL = "SELECT * FROM Student ORDER BY lastname";
			pre = connection.prepareStatement(txtSQL);
			result = pre.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String firstname = result.getString("firstname");
				String surname = result.getString("lastname");
				String streetaddress = result.getString("streetaddress");
				String postcode = result.getString("postcode");
				String postoffice = result.getString("postoffice");
				

				studentList.add(new Student(id, firstname, surname, streetaddress, postcode, postoffice));
			}
		}

		catch (SQLException sqle) {
			System.out.println("Database connection is fail");
			studentList = null;
		} finally {
			DbUtils.closeQuietly(result, pre, connection);
		}

		return studentList;

	}

	public String getAllStudentJSON() {
		
			Connection connection = null;
			PreparedStatement pre = null;
			ResultSet result = null;
			List<Student> studentList = new ArrayList<Student>();
			String jsonString = null;

			try {
				connection = openConnection();
				String txtSQL = "SELECT * FROM Student ORDER BY lastname";
				pre = connection.prepareStatement(txtSQL);
				result = pre.executeQuery();

				while (result.next()) {
					int id = result.getInt("id");
					String firstname = result.getString("firstname");
					String surname = result.getString("lastname");
					String streetaddress = result.getString("streetaddress");
					String postcode = result.getString("postcode");
					String postoffice = result.getString("postoffice");
					studentList.add(new Student(id, firstname, surname, streetaddress, postcode, postoffice));

					
				}
				Gson gson = new Gson();
				 jsonString = gson.toJson(studentList);
			}

			catch (SQLException sqle) {
				System.out.println("Database connection is fail");
				studentList = null;
			} finally {
				DbUtils.closeQuietly(result, pre, connection);
			}

			return jsonString;
		
	}

	public Student getStudentByID(int studentId) {
		Connection connection = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		Student foundStudent = null;
		boolean found = false;

		try {
			connection = openConnection();
			String txtSQL = "SELECT * FROM Student WHERE id = ?";
			pre = connection.prepareStatement(txtSQL);
			pre.setInt(1, studentId);
			result = pre.executeQuery();

			while (result.next()) {
				found = true;
				int id = result.getInt("id");
				String firstname = result.getString("firstname");
				String surname = result.getString("lastname");
				String streetaddress = result.getString("streetaddress");
				String postcode = result.getString("postcode");
				String postoffice = result.getString("postoffice");

				foundStudent = new Student(id, firstname, surname, streetaddress, postcode, postoffice);

			}
			if (found == false) {
				foundStudent = null;

			}
		}

		catch (SQLException sqle) {
			System.out.println("Database connection is fail");
			foundStudent = null;
		} finally {
			DbUtils.closeQuietly(result, pre, connection);
		}

		return foundStudent;

	}

	public int insertStudent(Student student) {

		Connection connection = null;
		PreparedStatement pre = null;
		int result = -5;

		try {
			connection = openConnection();
			String txt = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?,?,?,?,?,?)";
			pre = connection.prepareStatement(txt);
			pre.setInt(1, student.getId());
			pre.setString(2, student.getName());
			pre.setString(3, student.getLastname());
			pre.setString(4, student.getStreetAddress());
			pre.setString(5, student.getPostCode());
			pre.setString(6, student.getPostOffice());

			pre.executeUpdate();
			result = 0;
		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == 19) {
				result = 1;
			} else {
				result = -1;
				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}
		}

		finally {
			DbUtils.closeQuietly(pre, connection);
		}

		return result;
	}

	public int deleteStudent(int studentId) {

		Connection connection = null;
		PreparedStatement pre = null;

		int found = -5;
		int resultOfE = -5;

		try {
			connection = openConnection();
			String sql = "DELETE FROM Student WHERE id = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, studentId);

			found = pre.executeUpdate();

			if (found == 1) {
				resultOfE = 0;
			} else if (found == 0) {
				resultOfE = 1;
			} else {
				resultOfE = -1;
			}

		}

		catch (SQLException sqle) {
			if (sqle.getErrorCode() == 19) {
				System.out.println("Error happens with id number");
			} else {

				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}
		} finally {
			DbUtils.closeQuietly(pre, connection);
		}

		return resultOfE;
	}

	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement pre = null;
		int found = -5;
		int result = -5;

		try {
			connection = openConnection();
			String sql = "UPDATE Student SET firstname = ?, lastname = ?, streetaddress = ?, postcode = ?, postoffice = ? WHERE id = ?";
			pre = connection.prepareStatement(sql);
			pre.setString(1, student.getName());
			pre.setString(2, student.getLastname());
			pre.setString(3, student.getStreetAddress());
			pre.setString(4, student.getPostCode());
			pre.setString(5, student.getPostOffice());
			pre.setInt(6, student.getId());

			found = pre.executeUpdate();

			if (found == 0) {
				result = 1;
			} else if (found == 1) {
				result = 0;
			} else {
				result = -1;
			}

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == 19) {
				System.out.println("Error happens with id number");
			} else {

				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}
		} finally {
			DbUtils.closeQuietly(pre, connection);
		}
		return result;

	}

}