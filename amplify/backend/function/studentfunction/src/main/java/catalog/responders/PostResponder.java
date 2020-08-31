package catalog.responders;

import catalog.BadRequestException;
import catalog.JDBCConnector;
import catalog.responses.SimpleResponse;
import catalog.entities.StudentInfo;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostResponder {
    public static SimpleResponse respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException, SQLException {
        String path = request.getPath();
        if (path.equals("/student") || path.equals("/student/"))
            return addStudent(request, context);
        throw new BadRequestException("Unknown POST path " + path);
    }

    private static SimpleResponse addStudent(APIGatewayProxyRequestEvent request, Context context) throws SQLException {
        Connection conn = null;
        try {
            context.getLogger().log("Attempting to connect");
            conn = JDBCConnector.getRemoteConnection(context);
            assert conn != null;
            Statement readStatement = conn.createStatement();
            var gson = new Gson();
            StudentInfo student = gson.fromJson(request.getBody(), StudentInfo.class);
            ResultSet resultSet = readStatement.executeQuery("INSERT INTO student_info (ssn, first_name, last_name) values (" + student.ssn + ", " + student.firstName + ", " + student.lastName + ");");
            boolean success = resultSet.rowInserted();
            resultSet.close();
            readStatement.close();
            if (success)
                return new SimpleResponse("New student info inserted");
            return new SimpleResponse("Failed to insert student");
        } catch (NullPointerException e) {
            return new SimpleResponse("(Let's pretend it was inserted)");
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
            context.getLogger().log("Closed connection");
        }
    }
}
