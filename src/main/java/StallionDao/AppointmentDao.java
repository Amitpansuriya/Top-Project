package StallionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Bean.Appointment;
import SDBConnection.StallionDBC;

public class AppointmentDao {
		public static void insertdata(Appointment a) {
			try {
				Connection conn=StallionDBC.DataConnection();
				String sql = "insert into appointment_data (did,Dname,Dcontact,Dspeciality,Demail,pid,Pname,Pcontact,Pdecisis,Pemail,Date,Status ) values (?,?,?,?,?,?,?,?,?,?,?,? )";
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setInt(1,a.getDid());
				pst.setString(2,a.getDname());
				pst.setInt(3,a.getDcontact());
				pst.setString(4,a.getDspeciality());
				pst.setString(5,a.getDemail());
				pst.setInt(6,a.getPid());
				pst.setString(7,a.getPname());
				pst.setInt(8,a.getPcontact());
				pst.setString(9,a.getPdecisis());
				pst.setString(10,a.getPemail());
				pst.setString(11,a.getDate());	
				pst.setString(12,a.getStatus());
				pst.executeUpdate();
				System.out.println("Your Appointment Booked");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		public static List<Appointment>  AllPendingappointmentlist(int aid){
			   List<Appointment> list = new ArrayList<Appointment>();
			   try {
				   Connection conn=StallionDBC.DataConnection();
					String sql = "select * from appointment_data where Status='Pending' and did=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setInt(1, aid);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Appointment a = new Appointment();
						a.setAid(rs.getInt("aid"));
						a.setPid(rs.getInt("pid"));
						a.setPname(rs.getString("Pname"));
						a.setPdecisis(rs.getString("Pdecisis"));
						a.setPcontact(rs.getInt("Pcontact"));
						a.setPemail(rs.getString("Pemail"));
						a.setDate(rs.getString("Date"));
						a.setStatus(rs.getString("Status"));
						a.setDid(rs.getInt("Did"));
						a.setDname(rs.getString("Dname")); 
						a.setDcontact(rs.getInt("Dcontact"));
						a.setDspeciality(rs.getString("Dspeciality")); 
						a.setDemail(rs.getString("Demail"));
						 
						list.add(a);
						
					}
				   	
			} catch (Exception e) {
				  e.printStackTrace();
			}
			return list;
			   
		}
		public static List<Appointment>  AllPendingappointmentlistPatient(int id){
			   List<Appointment> list = new ArrayList<Appointment>();
			   try {
				   Connection conn=StallionDBC.DataConnection();
					String sql = "select * from appointment_data where Status ='Pending' and pid=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setInt(1, id);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Appointment a = new Appointment();
						a.setAid(rs.getInt("aid"));
						a.setPid(rs.getInt("pid"));
						a.setPname(rs.getString("Pname"));
						a.setPdecisis(rs.getString("Pdecisis"));
						a.setPcontact(rs.getInt("Pcontact"));
						a.setPemail(rs.getString("Pemail"));
						a.setDate(rs.getString("Date"));
						a.setStatus(rs.getString("Status"));
						a.setDid(rs.getInt("Did"));
						a.setDname(rs.getString("Dname")); 
						a.setDcontact(rs.getInt("Dcontact"));
						a.setDspeciality(rs.getString("Dspeciality")); 
						a.setDemail(rs.getString("Demail"));
						 
						list.add(a);
						
					}
				   	
			} catch (Exception e) {
				  e.printStackTrace();
			}
			return list;
			   
		}
		public static List<Appointment>  AllConfirmappointmentlist(int id){
				List<Appointment> list = new ArrayList<Appointment>();
				try {
					Connection conn=StallionDBC.DataConnection();
					String sql = "select * from appointment_data where Status ='Confirm' and did=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setInt(1, id);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Appointment a = new Appointment();
						a.setAid(rs.getInt("aid"));
						a.setPid(rs.getInt("pid"));
						a.setPname(rs.getString("Pname"));
						a.setPdecisis(rs.getString("Pdecisis"));
						a.setPcontact(rs.getInt("Pcontact"));
						a.setPemail(rs.getString("Pemail"));
						a.setDate(rs.getString("Date"));
						a.setStatus(rs.getString("Status"));
						a.setDid(rs.getInt("Did"));
						a.setDname(rs.getString("Dname")); 
						a.setDcontact(rs.getInt("Dcontact"));
						a.setDspeciality(rs.getString("Dspeciality")); 
						a.setDemail(rs.getString("Demail"));						 
						list.add(a);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return list;
		}
		public static List<Appointment>  AllConfirmappointmentlistPatient(int id){
			List<Appointment> list = new ArrayList<Appointment>();
			try {
				Connection conn=StallionDBC.DataConnection();
				String sql = "select * from appointment_data where Status ='Confirm' and pid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					Appointment a = new Appointment();
					a.setAid(rs.getInt("aid"));
					a.setPid(rs.getInt("pid"));
					a.setPname(rs.getString("Pname"));
					a.setPdecisis(rs.getString("Pdecisis"));
					a.setPcontact(rs.getInt("Pcontact"));
					a.setPemail(rs.getString("Pemail"));
					a.setDate(rs.getString("Date"));
					a.setStatus(rs.getString("Status"));
					a.setDid(rs.getInt("Did"));
					a.setDname(rs.getString("Dname")); 
					a.setDcontact(rs.getInt("Dcontact"));
					a.setDspeciality(rs.getString("Dspeciality")); 
					a.setDemail(rs.getString("Demail"));						 
					list.add(a);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
	}
		public static void AppointmentStatus(Appointment a){
			try {
				Connection conn = StallionDBC.DataConnection();
				String sql="update appointment_data set Status='Confirm' where aid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1,a.getAid());
				pst.executeUpdate();
				System.out.println("Appointment Confirm");
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		public static void AppointmentReject(Appointment a) {
			try {
				Connection conn = StallionDBC.DataConnection();
				String sql = "update Appointment_data set status='Rejected' where aid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1,a.getAid());
				pst.executeUpdate();
				System.out.println("Appointment Rejected");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void AppointmentCancel(Appointment a) {
			try {
				Connection conn = StallionDBC.DataConnection();
				String sql = "update Appointment_data set status='Cancel' where aid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1,a.getAid());
				pst.executeUpdate();
				System.out.println("Appointment Cancel By You");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}












