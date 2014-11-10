package ImportarBasicosYdatClientes;

import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;

public class MysqlToXls {

  private Connection connection = null;

  public MysqlToXls(String database, String user, String password)
  	throws ClassNotFoundException, SQLException {

		// Create MySQL database connection
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://localhost:3306/" + database;
    connection = DriverManager.getConnection(url, user, password);
  }

  public void generateXls(String tablename, String filename)
  	throws SQLException, FileNotFoundException, IOException {

    // Create new Excel workbook and sheet
    HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
    HSSFSheet xlsSheet = xlsWorkbook.createSheet();
    short rowIndex = 0;

    // Execute SQL query
    PreparedStatement stmt =
    connection.prepareStatement("select * from " + tablename);
    ResultSet rs = stmt.executeQuery();

    // Get the list of column names and store them as the first
    // row of the spreadsheet.
    ResultSetMetaData colInfo = rs.getMetaData();
    List<String> colNames = new ArrayList<String>();
    HSSFRow titleRow = xlsSheet.createRow(rowIndex++);

    for (int i = 1; i <= colInfo.getColumnCount(); i++) {
      colNames.add(colInfo.getColumnName(i));
      titleRow.createCell((short) (i-1)).setCellValue(
        new HSSFRichTextString(colInfo.getColumnName(i)));
      xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
    }

    // Save all the data from the database table rows
    while (rs.next()) {
      HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
      short colIndex = 0;
      for (String colName : colNames) {
        dataRow.createCell(colIndex++).setCellValue(
          new HSSFRichTextString(rs.getString(colName)));
      }
    }

    // Write to disk
    xlsWorkbook.write(new FileOutputStream(filename));
  }

  // Close database connection
  public void close() throws SQLException {
    connection.close();
  }

  public static void main(String[] args) {
    try {
      MysqlToXls mysqlToXls = new MysqlToXls("acme", "root", "root");
      mysqlToXls.generateXls("empresa", "empresa1.xls");
      System.out.println("TODO OK");
      mysqlToXls.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}