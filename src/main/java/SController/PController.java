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

import Bean.Patient;
import Bean.Stallion;
import Services.Services;
import StallionDao.DoctorDao;
import StallionDao.PatientDao;


@WebServlet("/PController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024
* 512)

public class PController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PController() {
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actions = request.getParameter("actions");
		System.out.println(actions);
		if(actions.equalsIgnoreCase("register")) {
			Patient PR = new Patient();
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
			PR.setImage(fileName);
			PR.setName(request.getParameter("name"));
			PR.setGender(request.getParameter("gender"));
			PR.setAge(Integer.parseInt(request.getParameter("age")));
			PR.setDecisis(request.getParameter("decisis"));
			PR.setContact(Integer.parseInt(request.getParameter("contact")));
			PR.setAddress(request.getParameter("address"));
			PR.setEmail(request.getParameter("email"));
			PR.setPassword(request.getParameter("password"));
			System.out.println(PR);
			PatientDao.InsertPatient(PR);
			response.sendRedirect("Patient-Login.jsp");

		}
		else if(actions.equalsIgnoreCase("login")) {
			Patient PR = new Patient();
			PR.setEmail(request.getParameter("email"));
			PR.setPassword(request.getParameter("password"));
			Patient p = PatientDao.LoginPatient(PR);
			
			if(p==null) {
				request.setAttribute("msg","Email and password are invalid");
				request.getRequestDispatcher("Patient-Login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("data",p);
				request.getRequestDispatcher("Patient-Home.jsp").forward(request, response);
			}			
		}

		else if(actions.equalsIgnoreCase("update")) {
			Patient p = new Patient();
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

			p.setId(Integer.parseInt(request.getParameter("id"))); 
			p.setImage(fileName);
			p.setName(request.getParameter("name"));
			p.setGender(request.getParameter("gender"));
			p.setAge(Integer.parseInt(request.getParameter("age")));
			p.setDecisis(request.getParameter("decisis"));
			p.setContact(Integer.parseInt(request.getParameter("contact")));
			p.setAddress(request.getParameter("address"));
			p.setEmail(request.getParameter("email"));
			p.setPassword(request.getParameter("password"));
			PatientDao.updatepatient(p);
			HttpSession session = request.getSession();
			session.setAttribute("data", p);
			System.out.println(p);
			response.sendRedirect("Patient-Home.jsp");
		
		}
		else if(actions.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			PatientDao.deletepatient(id);
			response.sendRedirect("Home.jsp");
		}
		else if(actions.equalsIgnoreCase("Change Password")) {
			String newpassword = request.getParameter("new-password");
			String confirmnewpassword =request.getParameter("confirm-new-password");
			String email = request.getParameter("email");
			if(newpassword.equals(confirmnewpassword)) {
				PatientDao.changepassword(email,newpassword);
				request.getRequestDispatcher("Patient-Home.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg","new password and confirm new password not matched");
				request.getRequestDispatcher("Patient-change-password.jsp").forward(request, response);
			}
		}
		else if(actions.equalsIgnoreCase("Get OTP")) {
			String email = request.getParameter("email");
			System.out.println(email);
			boolean flag = PatientDao.checkEmail(email);
			if(flag == true) {
				Services s=new Services();
				Random r=new Random();
				int num=r.nextInt(9999);
				System.out.println(num);
				s.sendMail(email,num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("Patient-Verify-Otp.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "email id not registered");
				request.getRequestDispatcher("Patient-Forgot-Password.jsp").forward(request, response);
			}
		}
		else if (actions.equalsIgnoreCase("Verify")) {
			 String email= request.getParameter("email");
			 int otp1=Integer.parseInt(request.getParameter("otp1"));
			 int otp2=Integer.parseInt(request.getParameter("otp2"));
			 if(otp1==otp2) {
				 request.setAttribute("email", email);
				 request.getRequestDispatcher("PEmail-new-password.jsp").forward(request, response);
			 }
			 else {
				 request.setAttribute("email", email);
				 request.setAttribute("otp",otp1);
				 request.getRequestDispatcher("Patient-Verify_Otp.jsp").forward(request, response);
			 }
		}
		else if(actions.equalsIgnoreCase("New Password")) {
			String email= request.getParameter("email");
			String new_password =request.getParameter("new_password");
			String confirm_new_password= request.getParameter("confirm_new_password");
			if(new_password.equals(confirm_new_password)) {
				PatientDao.changepassword(email,new_password);
				response.sendRedirect("Patient-Login.jsp");
			}
			else {
				request.setAttribute("msg"," new paswword and confirm_new_password not matched");
				request.getRequestDispatcher("PEmail-new-password.jsp").forward(request, response);
			}
			
		}
	}
}
		
		
		
		
		
		
