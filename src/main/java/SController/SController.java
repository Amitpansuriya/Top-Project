package SController;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Bean.Appointment;
import Bean.Stallion;
import Services.Services;
import StallionDao.AppointmentDao;
import StallionDao.DoctorDao;
import StallionDao.PatientDao;

@WebServlet("/SController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024
* 512)
public class SController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SController() {
		super();
	}

	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		System.out.println(cd);
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equalsIgnoreCase("register")) {
			Stallion DR = new Stallion();
			String savePath = "G:\\javaa\\StallionHospital\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("image");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "G:\\javaaa\\StallionHospital\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			DR.setImage(fileName);
			DR.setName(request.getParameter("name"));
			DR.setGender(request.getParameter("gender"));
			DR.setAge(Integer.parseInt(request.getParameter("age")));
			DR.setContact(Integer.parseInt(request.getParameter("contact")));
			DR.setAddress(request.getParameter("address"));
			DR.setSpeciality(request.getParameter("speciality"));
			DR.setExperience(Integer.parseInt(request.getParameter("experience")));
			DR.setEmail(request.getParameter("email"));
			DR.setPassword(request.getParameter("password"));
			System.out.println(DR);
			DoctorDao.insertDoctor(DR);
			response.sendRedirect("Doctor-Login.jsp");
		}
		else if(action.equalsIgnoreCase("login")) {
			Stallion DR = new Stallion();
			DR.setEmail(request.getParameter("email"));
			DR.setPassword(request.getParameter("password"));
			Stallion d = DoctorDao.logindoctor(DR);
			if(d==null) {
				request.setAttribute("msg","email and password is incorrect");
				request.getRequestDispatcher("Doctor-Login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("data",d);
				request.getRequestDispatcher("Doctor-Home.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("Change Password")) {
			String newpassword = request.getParameter("new-password");
			String confirmnewpassword = request.getParameter("confirm-new-password");
			String email = request.getParameter("email");
			int id = Integer.parseInt(request.getParameter("id"));
			if(newpassword.equals(confirmnewpassword)) {
				DoctorDao.doctorChangePasword(email, newpassword);
				Stallion s = DoctorDao.getbyId(id);
				HttpSession session = request.getSession();
				session.setAttribute("data",s);
				request.getRequestDispatcher("Doctor-Home.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "new password and confirm new password not matched");
				request.getRequestDispatcher("Doctor-change-passsword.jsp").forward(request, response);
			}
			
		}
	
		else if(action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Stallion d = DoctorDao.getbyId(id);
			request.setAttribute("data", d);
			request.getRequestDispatcher("Doctor-Update.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("update")) {
			Stallion d = new Stallion();
			String savePath = "G:\\javaa\\StallionHospital\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("image");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "G:\\javaaa\\StallionHospital\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			d.setId(Integer.parseInt(request.getParameter("id"))); 
			d.setImage(fileName);
			d.setName(request.getParameter("name"));
			d.setGender(request.getParameter("gender"));
			d.setAge(Integer.parseInt(request.getParameter("age")));
			d.setContact(Integer.parseInt(request.getParameter("contact")));
			d.setAddress(request.getParameter("address"));
			d.setSpeciality(request.getParameter("speciality"));
			d.setExperience(Integer.parseInt(request.getParameter("experience")));
			d.setEmail(request.getParameter("email"));
			d.setPassword(request.getParameter("password"));
			DoctorDao.UpdateDoctor(d);
			HttpSession session = request.getSession();
			session.setAttribute("data",d);
			System.out.println(d);
			response.sendRedirect("Doctor-Home.jsp");
		}
		else if(action.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DoctorDao.DeleteStallion(id);
			response.sendRedirect("Home.jsp");
		}
		else if(action.equalsIgnoreCase("Get OTP")) {
			String email = request.getParameter("email");
			boolean flag = DoctorDao.checkEmail(email);
			if(flag == true) {
				Services s=new Services();
				Random r=new Random();
				int num=r.nextInt(9999);
				System.out.println(num);
				s.sendMail(email,num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("Doctor-verify-otp.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "email id not registered");
				request.getRequestDispatcher("Doctor-forgot-password.jsp").forward(request, response);
			}
		}
		else if (action.equalsIgnoreCase("Verify")) {
			 String email= request.getParameter("email");
			 int otp1=Integer.parseInt(request.getParameter("otp1"));
			 int otp2=Integer.parseInt(request.getParameter("otp2"));
			 if(otp1==otp2) {
				 request.setAttribute("email", email);
				 request.getRequestDispatcher("SEmail-new-password.jsp").forward(request, response);
			 }
			 else {
				 request.setAttribute("email", email);
				 request.setAttribute("otp",otp1);
				 request.getRequestDispatcher("Doctor-Verify_Otp.jsp").forward(request, response);
			 }
		}
		else if(action.equalsIgnoreCase("New Password")) {
			String email= request.getParameter("email");
			String new_password =request.getParameter("new_password");
			String confirm_new_password= request.getParameter("confirm_new_password");
			if(new_password.equals(confirm_new_password)) {
				PatientDao.changepassword(email,new_password);
				response.sendRedirect("Doctor-Login.jsp");
			}
			else {
				request.setAttribute("msg"," new paswword and confirm_new_password not matched");
				request.getRequestDispatcher("SEmail-new-password.jsp").forward(request, response);
			}			
		}
	
	}
}
