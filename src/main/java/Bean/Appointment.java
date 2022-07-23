package Bean;

import java.sql.Date;

public class Appointment {
		int aid,pid,did,Pcontact,Dcontact;
		String Dname,Pname,Dspeciality,Demail,Pemail,Pdecisis,Date,Status;
		public int getAid() {
			return aid;
		}
		public void setAid(int aid) {
			this.aid = aid;
		}
		public int getPid() {
			return pid;
		}
		public void setPid(int pid) {
			this.pid = pid;
		}
		public int getDid() {
			return did;
		}
		public void setDid(int did) {
			this.did = did;
		}
		public int getPcontact() {
			return Pcontact;
		}
		public void setPcontact(int pcontact) {
			Pcontact = pcontact;
		}
		public int getDcontact() {
			return Dcontact;
		}
		public void setDcontact(int dcontact) {
			Dcontact = dcontact;
		}
		public String getDname() {
			return Dname;
		}
		public void setDname(String dname) {
			Dname = dname;
		}
		public String getPname() {
			return Pname;
		}
		public void setPname(String pname) {
			Pname = pname;
		}
		public String getDspeciality() {
			return Dspeciality;
		}
		public void setDspeciality(String dspeciality) {
			Dspeciality = dspeciality;
		}
		public String getDemail() {
			return Demail;
		}
		public void setDemail(String demail) {
			Demail = demail;
		}
		public String getPemail() {
			return Pemail;
		}
		public void setPemail(String pemail) {
			Pemail = pemail;
		}
		public String getPdecisis() {
			return Pdecisis;
		}
		public void setPdecisis(String pdecisis) {
			Pdecisis = pdecisis;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		@Override
		public String toString() {
			return "Appointment [aid=" + aid + ", pid=" + pid + ", did=" + did + ", Pcontact=" + Pcontact
					+ ", Dcontact=" + Dcontact + ", Dname=" + Dname + ", Pname=" + Pname + ", Dspeciality="
					+ Dspeciality + ", Demail=" + Demail + ", Pemail=" + Pemail + ", Pdecisis=" + Pdecisis + ", Date="
					+ Date + ", Status=" + Status + "]";
		}
		
}
