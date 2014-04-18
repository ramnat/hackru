package model;

import java.sql.Connection;
import org.postgresql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import play.api.db.DB;
//import play.db.DB;
import views.html.main;

public class VideoNote {

	/**
	 * @param args
	 */
	
	
	@Column(name="url")
	public String url;
	
	@Column(name="note")
	public String  note;
	
	@Column(name="timestamp")
	public long  timestamp;
	
	@Column(name="user")
	public String userId;
	
	public VideoNote()
	{}
			
	public VideoNote(String note,long  timestamp,String userId)
	{
			this.note=note;
			this.timestamp=timestamp;
			this.userId=userId;
			
	}
		public List<List<String>>  getDataRows(String sql,int numcols) throws SQLException, ClassNotFoundException 
	{
		List<List<String>> result = new ArrayList<>();
		try {

			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Add driver to your class path");
			ex.printStackTrace();
						throw ex;
		
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres","postgres123");
			Statement stmt = conn.createStatement();
		    ResultSet rs= stmt.executeQuery(sql);
		      // List of list, one per row
		    
		     
		    while (rs.next()) {
		       int i = 1;
		    	List<String> row = new ArrayList<>(numcols);
		        while (i <= numcols) {  // don't skip the last column, use <=
		            
		        	System.out.println("getstringo/p  "+rs.getString(i));
		        	row.add(rs.getString(i));
		        	i=i+1;
		        }
		        result.add(row); // add it to the result
		    }
		    conn.close();
		    stmt.close();
		    rs.close();
		} 
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
		}
		
		public ArrayList<VideoNote> getNoteDatafromDB() throws ClassNotFoundException, SQLException
		{
			String sql="Select url,note,timestamp,username from note_details ";
			List<List<String>> result =getDataRows(sql,4);
			ArrayList<VideoNote> noteList = new ArrayList<VideoNote>();
			
			System.out.println("Data from DB is "+result);
			
			for (int i=0;i<result.size();i++)
			{
				VideoNote vid=new VideoNote();
				
				System.out.println("i is "+i);
				System.out.println("Single one is "+result.get(i).get(1));
				
				
				
				vid.note= result.get(i).get(1);
				vid.url=result.get(i).get(0);
				vid.userId=result.get(i).get(3);
                 vid.timestamp=Integer.parseInt(result.get(i).get(2));
                 noteList.add(vid);
			}
			
			return  noteList;
			
		}

		public static void main(String[] args) {
			String sql="select * from test";
			String sql1="Select url,note,timestamp,username from note_details";
			VideoNote not=new VideoNote();
			try {
				try {
					
//					System.out.println(not.getDataRows(sql, 2));
//					System.out.println(not.getDataRows(sql1, 4));
					System.out.println(not.getNoteDatafromDB().get(0).timestamp );
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
}


	
	