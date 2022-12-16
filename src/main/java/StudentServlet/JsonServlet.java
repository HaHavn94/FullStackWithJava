package StudentServlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



@WebServlet("/jsontest")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public JsonServlet() {
        super();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Student> studentL = new ArrayList <Student>() ;
		Student ha = new Student (1,"ha","kjds","kjds","kjds","sas");
	    Student la = new Student (2,"la","kjds","kjds","kjds","sas");
	studentL.add(la) ;
	studentL.add(ha) ;
	
	PrintWriter out = response.getWriter();
	
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8") ;
	
	Gson gson = new Gson() ;
	String json = gson.toJson(studentL) ;
	
	out.print(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}
}