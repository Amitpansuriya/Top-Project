package SController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Bean.Appointment;
import Bean.Patient;
import Bean.Stallion;
import SDBConnection.StallionDBC;
import Services.Services;
import Services.Servicess;
import StallionDao.AppointmentDao;
import StallionDao.DoctorDao;
import StallionDao.PatientDao;



@WebServlet("/AController")
public class AController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		System.out.println(action);
		if(action.equalsIgnoreCase("Make appointment")) {
			Appointment a= new Appointment();
			a.setDid(Integer.parseInt(request.getParameter("did")));
			a.setDname(request.getParameter("Dname"));
			a.setDcontact(Integer.parseInt(request.getParameter("Dcontact")));
			a.setDspeciality(request.getParameter("Dspeciality"));
			a.setDemail(request.getParameter("Demail"));
			a.setPid(Integer.parseInt(request.getParameter("pid")));
			a.setPname(request.getParameter("Pname"));
			a.setPcontact(Integer.parseInt(request.getParameter("Pcontact")));
			a.setPdecisis(request.getParameter("Pdecisis"));
			a.setPemail(request.getParameter("Pemail"));
			a.setDate(request.getParameter("Date"));
			a.setStatus("Pending");
			/*
			 * String Date=request.getParameter("Date"); try { Date D=new
			 * SimpleDateFormat("dd/mm/yy").parse(Date); } catch (ParseException e) {
			 * 
			 * e.printStackTrace(); } a.setDate(Date);
			 */
					
			System.out.println(a);
			AppointmentDao.insertdata(a);
			response.sendRedirect("Patient-Home.jsp");
		}
		else if(action.equalsIgnoreCase("Pending Appointment")) {
			int aid= Integer.parseInt(request.getParameter("aid"));
			AppointmentDao.AllPendingappointmentlist(aid);
			HttpSession session = request.getSession();
			session.setAttribute("Adata", aid);
			response.sendRedirect("Doctor-Pending.jsp");
			
		}
		else if (action.equalsIgnoreCase("Confirm")){
			Appointment a = new Appointment();
			a.setAid(Integer.parseInt(request.getParameter("aid")));
			a.setPid(Integer.parseInt(request.getParameter("pid")));
			System.out.print(a.getPid());
			Patient s1 = PatientDao.getbyiddata(Integer.parseInt(request.getParameter("pid")));
			String Pemail = s1.getEmail();
			System.out.print(Pemail);
			Servicess s=new Servicess();
			s.sendMail(Pemail);
			AppointmentDao.AppointmentStatus(a);
			response.sendRedirect("Doctor-Pending.jsp");	
			}
		else if(action.equalsIgnoreCase("Reject")){
			Appointment a = new Appointment();
			a.setAid(Integer.parseInt(request.getParameter("aid")));
			a.setPid(Integer.parseInt(request.getParameter("pid")));
			System.out.print(a.getPid());
			Patient s1 = PatientDao.getbyiddata(Integer.parseInt(request.getParameter("pid")));
			String Pemail = s1.getEmail();
			System.out.print(Pemail);
			Servicess s=new Servicess();
			s.sendRejectMail(Pemail);
			AppointmentDao.AppointmentReject(a);
			response.sendRedirect("Doctor-Pending.jsp");
		}
		else if(action.equalsIgnoreCase("Cancel")) {
			Appointment a = new Appointment();
			a.setAid(Integer.parseInt(request.getParameter("aid")));
			a.setPid(Integer.parseInt(request.getParameter("pid")));
			a.setDid(Integer.parseInt(request.getParameter("did")));
			System.out.print(a.getPid());
			Stallion s1 = DoctorDao.getbyId(Integer.parseInt(request.getParameter("did")));
			String Demail = s1.getEmail();
			System.out.print(Demail);
			Servicess s=new Servicess();
			s.sendCancelMail(Demail);
			AppointmentDao.AppointmentCancel(a);
			response.sendRedirect("Patient-Pending.jsp");
		}
			
		}
}
