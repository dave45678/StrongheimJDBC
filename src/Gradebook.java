

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Gradebook")
public class Gradebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Gradebook() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		String assignmentName = request.getParameter("assignmentName");
		String assignmentType = request.getParameter("assignmentType");
		int assignmentGrade = Integer.parseInt(request.getParameter("assignmentGrade"));
		
		//converting the assignment date from the parameter (string) to a sql date is a multi-step process
		java.util.Date assignmentDate=null;
	
		try {
			assignmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("assignmentDate"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// because PreparedStatement#setDate(..) expects a java.sql.Date argument
		java.sql.Date sqlDate = new java.sql.Date(assignmentDate.getTime()); 
		
		
		//connect to database
		String ConnectionString = "jdbc:oracle:thin:testuserdb/password@localhost";
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into Strongheim_Gradebook values (?,?,?,?,?)";
		//save to database
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(ConnectionString);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,studentId);
				pstmt.setString(2,assignmentName);	
				pstmt.setString(3, assignmentType);
				pstmt.setDate(4, sqlDate);
				pstmt.setInt(5, assignmentGrade);
				pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		//redirect to next page
		getServletContext().getRequestDispatcher("/dataentry.jsp").forward(request, response);
	}

}
