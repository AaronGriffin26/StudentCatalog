package catalog.responders;

import catalog.BadRequestException;
import catalog.JDBCConnector;
import catalog.entities.FirstNameLastName;
import catalog.entities.StudentInfo;
import catalog.responses.NamesResponse;
import catalog.responses.StudentsResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetResponder {
	public static Object respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException, SQLException {
		String path = request.getPath();
		if(path.equals("/student") || path.equals("/student/"))
			return new StudentsResponse(getAllStudents(context));
		if(path.equals("/test") || path.equals("/test/"))
			return new StudentsResponse(getAllStudentsTest(context));
		if(path.equals("/name") || path.equals("/name/"))
			return new NamesResponse(getAllStudentNames(context));
		if(path.startsWith("/student/")) {
			try {
				int ssn = Integer.parseInt(path.substring(9));
				return getStudentWithSSN(ssn, context);
			}
			catch(NumberFormatException e) {
				throw new BadRequestException("SSN supplied was not a number: " + path.substring(9));
			}
		}
		throw new BadRequestException("Unknown GET path " + path);
	}

	private static StudentInfo[] getAllStudents(Context context) throws SQLException {
		Connection conn = null;
		try {
			context.getLogger().log("Attempting to connect");
			conn = JDBCConnector.getRemoteConnection(context);
			assert conn != null;
			Statement readStatement = conn.createStatement();
			ResultSet resultSet = readStatement.executeQuery("SELECT * FROM student_info;");
			resultSet.first();
			var list = new ArrayList<StudentInfo>();
			int count = resultSet.getFetchSize();
			context.getLogger().log("Retrieved " + count + " items");
			do {
				int ssn = resultSet.getInt("ssn");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				var si = new StudentInfo(ssn, firstName, lastName);
				list.add(si);
			} while(resultSet.next());
			resultSet.close();
			readStatement.close();
			return list.toArray(new StudentInfo[0]);
		}
		catch(NullPointerException e) {
			var list = new ArrayList<StudentInfo>();
			list.add(new StudentInfo(123456789, "Null", "Pointer"));
			list.add(new StudentInfo(333559999, "Exception", "PlsFix"));
			return list.toArray(new StudentInfo[0]);
		}
		finally {
			if(conn != null)
				try {
					conn.close();
				}
				catch(SQLException ignore) {
				}
			context.getLogger().log("Closed connection");
		}
	}

	private static StudentInfo[] getAllStudentsTest(Context context) throws SQLException {
		Connection conn = null;
		try {
			context.getLogger().log("Attempting to connect");
			conn = JDBCConnector.getRemoteConnectionTest(context);
			assert conn != null;
			Statement readStatement = conn.createStatement();
			ResultSet resultSet = readStatement.executeQuery("SELECT * FROM student_info;");
			var list = new ArrayList<StudentInfo>();
			int count = resultSet.getFetchSize();
			context.getLogger().log("Retrieved " + count + " items");
			do {
				int ssn = resultSet.getInt("ssn");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				var si = new StudentInfo(ssn, firstName, lastName);
				list.add(si);
			} while(resultSet.next());
			resultSet.close();
			readStatement.close();
			return list.toArray(new StudentInfo[0]);
		}
		catch(NullPointerException e) {
			var list = new ArrayList<StudentInfo>();
			list.add(new StudentInfo(123456789, "Null", "Pointer"));
			list.add(new StudentInfo(333559999, "Exception", "PlsFix"));
			return list.toArray(new StudentInfo[0]);
		}
		finally {
			if(conn != null)
				try {
					conn.close();
				}
				catch(SQLException ignore) {
				}
			context.getLogger().log("Closed connection");
		}
	}

	private static FirstNameLastName[] getAllStudentNames(Context context) throws SQLException {
		Connection conn = null;
		try {
			context.getLogger().log("Attempting to connect");
			conn = JDBCConnector.getRemoteConnection(context);
			assert conn != null;
			Statement readStatement = conn.createStatement();
			ResultSet resultSet = readStatement.executeQuery("SELECT first_name, last_name FROM student_info;");
			resultSet.first();
			var list = new ArrayList<FirstNameLastName>();
			int count = resultSet.getFetchSize();
			context.getLogger().log("Retrieved " + count + " items");
			do {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				var si = new FirstNameLastName(firstName, lastName);
				list.add(si);
			} while(resultSet.next());
			resultSet.close();
			readStatement.close();
			return list.toArray(new FirstNameLastName[0]);
		}
		catch(NullPointerException e) {
			var list = new ArrayList<FirstNameLastName>();
			list.add(new FirstNameLastName("Null", "Pointer"));
			list.add(new FirstNameLastName("Exception", "PlsFix"));
			return list.toArray(new FirstNameLastName[0]);
		}
		finally {
			if(conn != null)
				try {
					conn.close();
				}
				catch(SQLException ignore) {
				}
			context.getLogger().log("Closed connection");
		}
	}

	private static StudentInfo getStudentWithSSN(int ssn, Context context) throws SQLException {
		Connection conn = null;
		try {
			context.getLogger().log("Attempting to connect");
			conn = JDBCConnector.getRemoteConnection(context);
			assert conn != null;
			Statement readStatement = conn.createStatement();
			ResultSet resultSet = readStatement.executeQuery("SELECT * FROM student_info WHERE ssn = " + ssn + ";");
			resultSet.first();
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			var si = new StudentInfo(ssn, firstName, lastName);
			resultSet.close();
			readStatement.close();
			return si;
		}
		catch(NullPointerException e) {
			return new StudentInfo(123456789, "Null", "Pointer");
		}
		finally {
			if(conn != null)
				try {
					conn.close();
				}
				catch(SQLException ignore) {
				}
			context.getLogger().log("Closed connection");
		}
	}
}
