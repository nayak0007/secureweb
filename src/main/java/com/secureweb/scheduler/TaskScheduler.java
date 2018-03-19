package com.secureweb.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.secureweb.scheduler.JustOneLock;
//import com.aspose.cells.Cells;
//import com.aspose.cells.Workbook;
//import com.aspose.cells.Worksheet;
@Component
public class TaskScheduler {
	 private static final Logger LOGGER = LoggerFactory.getLogger(TaskScheduler.class);
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	 public static ServerSocket ss;

	 @Scheduled(cron = "*/20 * * * * *")
	    public void reportCurrentTime() throws SQLException {
		// LOGGER.info("scheduler>>>"+dateFormat.format(new Date()));
		// new TaskScheduler().test();
		 
		 try{
			// ss = new ServerSocket(1044);
			// System.out.println("Not Already active.");
			// jsonBuilder(ss);
		 }catch (Exception e){
			// System.out.println("Already active.");
		 }
		
	    }
	 public static Connection getDBConnection() {
			Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/recordbook",
							"root",
							"C1sc0123");
				} catch (Exception e) {
				}
			return connection;
		}
	static void test() {
	        JustOneLock ua = new JustOneLock("JustOneId");

	        if (ua.isAppActive()) {
	            System.out.println("Already active.");
	            System.exit(1);    
	        }
	        else {
	            System.out.println("NOT already active.");
	            try {
	                while(true) {
	                     try { System.out.print("................");
	                    // jsonBuilder(ss);//Thread.sleep(5 * 10);
	                     
	                     }
	                      catch(Exception e) { e.printStackTrace(); }
	                }
	             }
	            catch (Exception e) {  }
	        }
	    }
	public static void jsonBuilder(ServerSocket ss) throws Exception{
		/* System.out.println("START time is now {}"+dateFormat.format(new Date()));	
	       Connection con= getDBConnection();
	        JSONArray json = new JSONArray();
			Object b=new Object();
			String DATE_FORMAT = "ddMMyyyyhhmmss";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String timeStamp = sdf.format(
					Calendar.getInstance().getTime()).toString();
			ResultSet rs = null;
			Statement stmt=null;
			if (con != null) {
			try{
				Workbook wbk=new Workbook();
				Worksheet ws=wbk.getWorksheets().get(0);
				Cells cells=ws.getCells();
				//System.out.println(con.getSchema());
				stmt =  con.createStatement();
				rs=stmt.executeQuery("SELECT * FROM SPM_DATA");			
				ResultSetMetaData rsmd = rs.getMetaData();
				System.out.println(rsmd.getColumnCount());
				int startRow=0;
				int startClm=0;
				int mergeRow=0;
				String oldVal="";
				String val="";
				while(rs.next()) {
				  startClm=0;
				  int numColumns = rsmd.getColumnCount();
				  JSONObject obj = new JSONObject();
				  for (int i=1; i<=numColumns; i++) {
				    String column_name = rsmd.getColumnName(i);
				   // System.out.println(column_name+"<<<-->>>"+rs.getObject(column_name));
				    val=rs.getObject(column_name)==null?"":(String) rs.getObject(column_name);
				    cells.get(startRow, startClm).putValue(rs.getObject(column_name));
				    if(startClm==0){
				    	if(!oldVal.equalsIgnoreCase((String)rs.getObject(column_name)) && startRow>0){
				    		if((startRow-mergeRow)>0){
				    		cells.merge(startRow, startClm, startRow-mergeRow, 1);
				    		
				    		}
				    		oldVal=val;
				    		mergeRow=startRow;
				    	}
				    	
				    }
				    obj.put(column_name, rs.getObject(column_name));
				    startClm++;
				  }
				  json.put(obj);
				  startRow++;
				}
			wbk.save("D:\\spm-"+timeStamp+".xlsx");			
			FileWriter fileWriter = new FileWriter("D:\\demojson_"+timeStamp+".json");
			fileWriter.write(json.toString());
			fileWriter.flush();
			System.out.println("END time is now {}"+dateFormat.format(new Date()));
			fileWriter.close();
			}
			catch(Exception e){
			}
			finally {
				try {
					stmt.close();
					con.close();
					ss.close();
				} catch (SQLException ex) {
					throw ex;
				}
			}
			}*/
		
	}

}
