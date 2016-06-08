package util;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SqlHelper {

	public static String generateHTMLTable(ResultSet rs)
			throws SQLException {
	StringBuilder table = new StringBuilder();
	ResultSetMetaData md = rs.getMetaData();
	int colCount = md.getColumnCount();
	
	table.append("<table>");
	table.append("<tr>");
	for (int i=1; i <=colCount; i++){
		table.append("<th>");
		table.append(md.getColumnName(i));
		table.append("</th>");
	}
	table.append("</tr>");
	
	while (rs.next()){
		table.append("<tr>");
		
		for (int i=1; i<=colCount; i++){
		table.append("<td>");
		table.append(rs.getString(i));
		table.append("</td>");
		}
		
		table.append("</tr>");
	}
	table.append("</table>");
		
		return table.toString();
	}
}
