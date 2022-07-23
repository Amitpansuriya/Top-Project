package StallionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import Bean.Patient;
import Bean.Stallion;
import SDBConnection.StallionDBC;

public class PatientDao {
	public static void InsertPatient(Patient p) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "insert into patient_data(image,name,gender,age,decisis,contact,address,email,password) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getImage());
			pst.setString(2, p.getName());
			pst.setString(3, p.getGender());
			pst.setInt(4, p.getAge());
			pst.setString(5, p.getDecisis());
			pst.setInt(6, p.getContact());
			pst.setString(7, p.getAddress());
			pst.setString(8, p.getEmail());
			pst.setString(9, p.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Patient LoginPatient(Patient PR) {
		Patient p = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, PR.getEmail());
			pst.setString(2, PR.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p = new Patient();
				p.setId(rs.getInt(1));
				p.setImage(rs.getString(2));
				p.setName(rs.getString(3));
				p.setGender(rs.getString("gender"));
				p.setAge(rs.getInt(5));
				p.setDecisis(rs.getString(6));
				p.setContact(rs.getInt(7));
				p.setAddress(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPassword(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
		
	}
	public static List<Patient> AllPatientList(){
		List<Patient> list= new ArrayList<Patient>();
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				p.setId(rs.getInt(1));
				p.setImage(rs.getString(2));
				p.setName(rs.getString(3));
				p.setGender(rs.getString(4));
				p.setAge(rs.getInt(5));
				p.setDecisis(rs.getString(6));
				p.setContact(rs.getInt(7));
				p.setAddress(rs.getString(8));
		
				p.setEmail(rs.getString(9));
				p.setPassword(rs.getString(10));
				list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static Patient ProfilePatient(Patient PR) {
		Patient p = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, PR.getEmail());
			pst.setString(2, PR.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p = new Patient();
				p.setId(rs.getInt(1));
				p.setImage(rs.getString(2));
				p.setName(rs.getString(3));
				p.setGender(rs.getString(4));
				p.setAge(rs.getInt(5));
				p.setDecisis(rs.getString(6));
				p.setContact(rs.getInt(7));
				p.setAddress(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPassword(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static Patient getbyiddata(int id) {
		Patient p = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				p = new Patient();
				p.setId(rs.getInt(1));
				p.setImage(rs.getString(2));
				p.setName(rs.getString(3));
				p.setGender(rs.getString(4));
				p.setAge(rs.getInt(5));
				p.setDecisis(rs.getString(6));
				p.setContact(rs.getInt(7));
				p.setAddress(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPassword(rs.getString(10));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public static Patient getbyid(int id) {
		Patient p = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p = new Patient();
				p.setId(rs.getInt(1));
				p.setImage(rs.getString(2));
				p.setName(rs.getString(3));
				p.setGender(rs.getString(4));
				p.setAge(rs.getInt(5));
				p.setDecisis(rs.getString(6));
				p.setContact(rs.getInt(7));
				p.setAddress(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPassword(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public static void updatepatient(Patient p) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "update patient_data set image=?,name=?,gender=?,age=?,decisis=?,contact=?,address=?,email=?,password=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getImage());
			pst.setString(2, p.getName());
			pst.setString(3, p.getGender());
			pst.setInt(4, p.getAge());
			pst.setString(5, p.getDecisis());
			pst.setInt(6, p.getContact());
			pst.setString(7, p.getAddress());
			pst.setString(8, p.getEmail());
			pst.setString(9, p.getPassword());
			pst.setInt(10, p.getId());
			pst.executeUpdate();
			System.out.println("data update done");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletepatient(int id) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "delete from patient_data where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Account deleted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changepassword(String email, String new_password) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "update patient_data set password=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, new_password);
			pst.setString(2, email);
			pst.executeUpdate();
			System.out.println("new password set successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from patient_data where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
