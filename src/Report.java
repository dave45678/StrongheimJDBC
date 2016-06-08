

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SqlHelper;

@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		
		String studentId = null;
		if (request.getParameter("studentId")==null ||request.getParameter("studentId")=="%25" ){
			studentId = "%";
		}else{
			studentId = request.getParameter("studentId");
		}
		 
		String assignmentType = null;
		if (request.getParameter("assignmentType")==null ||request.getParameter("assignmentType")=="%25" ){
			assignmentType = "%";
		}else{
			assignmentType = request.getParameter("assignmentType");
		}
		
		
		
		String ConnectionString = "jdbc:oracle:thin:testuserdb/password@localhost";
		Connection con = null;
		
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String assignmenttype = "%";
		String studentid = "%";
		String heading = "";
		String table = "";
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(ConnectionString);
		if(query.equals("assignmentsByStudent")){
			heading = "Assignments By Student";
			sql = "select * from STRONGHEIM_GRADEBOOK where STUDENTID=?";
			pstmt = con.prepareStatement(sql);	
		}else if (query.equals("assignmentsByAssignmentType")){
			sql = "select * from STRONGHEIM_GRADEBOOK where ASSIGNMENTTYPE=?";
			heading = "Assignments By Assignment Type";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,assignmenttype);
		}else if (query.equals("assignmentTypeByStudentId")){
			heading = "Assignment Type By Student";
			sql = "select * from STRONGHEIM_GRADEBOOK where ASSIGNMENTTYPE =? and STUDENTID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,assignmenttype);
			pstmt.setString(2,studentid);
		}else if (query.equals("averageByStudentId")){
			heading = "Average By Student";
			sql = "select AVG(assignmentgrade) from STRONGHEIM_GRADEBOOK where STUDENTID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,studentid);
		}else if (query.equals("averageByStudentAndAssignmentType")){
			heading = "Assignments By Student and Assignment Type";
			sql = "select AVG(assignmentgrade) from STRONGHEIM_GRADEBOOK where ASSIGNMENTTYPE LIKE ? and STUDENTID LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,assignmenttype);
			pstmt.setString(2,studentid);
			System.out.println(">>>>" + assignmenttype + " " + studentid);
		}else if (query.equals("highLowByAssignmentType")){
			heading = "Hi/Low Grade By Assignment Type";
			sql = "select max(assignmentgrade) as \"Max Grade\", min(assignmentgrade) as \"Min Grade\" FROM STRONGHEIM_GRADEBOOK where  ASSIGNMENTTYPE=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,assignmenttype);
		}else{
			//error occurred... don't know which query to run
			heading = "Gradebook Data";
			sql = "select * from STRONGHEIM_GRADEBOOK"; 
			pstmt = con.prepareStatement(sql);
		}
		
		//execute the query and return a resultset object
		rs = pstmt.executeQuery();
		table = SqlHelper.generateHTMLTable(rs);


		//catch the errors from most specific to most general
		}catch (SQLTimeoutException e){
			e.printStackTrace();
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
			
			
			
		//set the table attribute in the jsp page
		request.setAttribute("heading",heading);
		request.setAttribute("table", table);
		//redirect to next page
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	
			
	}



}
