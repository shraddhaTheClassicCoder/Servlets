package in.ineuron.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import in.ineuron.utility.JdbcUtil;
import in.ineuron.dto.Student;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstmt = null;

	@Override
	public String addStudent(Student student) {

		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlInsertQuery = "insert into student(sname,sage,saddress) values(?,?,?)";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());

				// Execute Query
				int count = pstmt.executeUpdate();

				if (count == 1) {
					return "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}

	@Override
	public Student searchStudent(Integer sid) {

		Student student = new Student();
		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlSelectQuery = "select sid,sname,sage,saddress from student where sid=?";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if (pstmt != null) {

				pstmt.setInt(1, sid);

				// Execute Query
				ResultSet res = pstmt.executeQuery();

				if (res != null) {
					if (res.next()) {

						student.setSid(res.getInt(1));
						student.setSname(res.getString(2));
						student.setSage(res.getInt(3));
						student.setSaddress(res.getString(4));
					}
				}
				return student;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;

	}

	public String updateStudent(Student student) {

		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlUpdateQuery = "update student set sname=?,sage=?,saddress=? where sid=?";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setInt(4, student.getSid());

				// Execute Query
				int count = pstmt.executeUpdate();

				if (count == 1) {
					return "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}

	@Override
	public String deleteStudent(Integer sid) {

		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlDeleteQuery = "delete from student where sid = ?";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			}
			if (pstmt != null) {

				pstmt.setInt(1, sid);

				// Execute Query
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return "success";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";

	}
}
