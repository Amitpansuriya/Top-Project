package StallionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import Bean.Stallion;
import SDBConnection.StallionDBC;

public class DoctorDao {
	public static void insertDoctor(Stallion d) {
		try {
			
			Connection conn = StallionDBC.DataConnection();
			String sql="insert into doctor_data(image,name,gender,age,contact,address,speciality,experience,email,password) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,d.getImage());
			pst.setString(2,d.getName());
			pst.setString(3,d.getGender());
			pst.setInt(4,d.getAge());
			pst.setInt(5,d.getContact());
			pst.setString(6,d.getAddress());
			pst.setString(7,d.getSpeciality());
			pst.setInt(8,d.getExperience());
			pst.setString(9,d.getEmail());
			pst.setString(10,d.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Stallion logindoctor(Stallion DR) {
		Stallion d = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from doctor_data where email=? and password=?";
			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1,DR.getEmail() );
			pst.setString(2,DR.getPassword());
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				d = new Stallion();
				d.setId(rs.getInt(1));
				d.setImage(rs.getString(2));
				d.setName(rs.getString(3));
				d.setGender(rs.getString(4));
				d.setAge(rs.getInt(5));
				d.setContact(rs.getInt(6));
				d.setAddress(rs.getString(7));
				d.setSpeciality(rs.getString(8));
				d.setExperience(rs.getInt(9));
				d.setEmail(rs.getString(10));
				d.setPassword(rs.getString(11));
				System.out.println(d);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public static List<Stallion> AllDoctorList(){
		List<Stallion> list= new ArrayList<Stallion>();
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from doctor_data";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Stallion d = new Stallion();
				d.setId(rs.getInt(1));
				d.setImage(rs.getString(2));
				d.setName(rs.getString(3));
				d.setGender(rs.getString(4));
				d.setAge(rs.getInt(5));
				d.setContact(rs.getInt(6));
				d.setAddress(rs.getString(7));
				d.setSpeciality(rs.getString(8));
				d.setExperience(rs.getInt(9));
				d.setEmail(rs.getString(10));
				d.setPassword(rs.getString(11));
				list.add(d);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Stallion DoctorProfile(Stallion DR) {
		Stallion d = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from doctor_data where email=? and password=?";
			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1,DR.getEmail() );
			pst.setString(2,DR.getPassword());
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				d = new Stallion();
				d.setId(rs.getInt(1));
				d.setImage(rs.getString(2));
				d.setName(rs.getString(3));
				d.setGender(rs.getString(4));
				d.setAge(rs.getInt(5));
				d.setContact(rs.getInt(6));
				d.setAddress(rs.getString(7));
				d.setSpeciality(rs.getString(8));
				d.setExperience(rs.getInt(9));
				d.setEmail(rs.getString(10));
				d.setPassword(rs.getString(11));
				System.out.println(d);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public static void doctorChangePasword(String email,String new_password) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql="update doctor_data set password=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, new_password);
			pst.setString(2, email);
			pst.executeUpdate();
			System.out.println("passsword updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Stallion getbyId(int id) {
		Stallion d = null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from doctor_data where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				d = new Stallion();
				d.setId(rs.getInt(1));
				d.setImage(rs.getString(2));
				d.setName(rs.getString(3));
				d.setGender(rs.getString(4));
				d.setAge(rs.getInt(5));
				d.setContact(rs.getInt(6));
				d.setAddress(rs.getString(7));
				d.setSpeciality(rs.getString(8));
				d.setExperience(rs.getInt(9));
				d.setEmail(rs.getString(10));
				d.setPassword(rs.getString(11));
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public static void UpdateDoctor(Stallion d) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "update doctor_data set image=?,name=?,gender=?,age=?,contact=?,address=?,speciality=?,experience=?,email=?,password=?,hobbies=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, d.getImage());
			pst.setString(2,d.getName());
			pst.setString(3,d.getGender());
			pst.setInt(4,d.getAge());
			pst.setInt(5, d.getContact());
			pst.setString(6, d.getAddress());
			pst.setString(7,d.getSpeciality());
			pst.setInt(8,d.getExperience());
			pst.setString(9, d.getEmail());
			pst.setString(10, d.getPassword());
			pst.setInt(11, d.getId());
			pst.executeUpdate();
			System.out.println("data updated");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void DeleteStallion(int id) {
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "delete from doctor_data where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.executeUpdate();
			System.out.println("account deleted");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql="select * from doctor_data where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static Stallion GetByID(int id) {
		Stallion d= null;
		try {
			Connection conn = StallionDBC.DataConnection();
			String sql = "select * from doctor_data where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				d=new Stallion();
				d.setId(rs.getInt(1));
				d.setImage(rs.getString(2));
				d.setName(rs.getString(3));
				d.setContact(rs.getInt(6));
				d.setSpeciality(rs.getString(8));
				d.setExperience(rs.getInt(9));
				d.setEmail(rs.getNString(10));
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
			return d;
	}

}
