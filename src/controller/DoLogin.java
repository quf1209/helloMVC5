package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		CustomerService service = (CustomerService) CustomerService.getInstance();
		
		Customer customer = service.findCustomer(id);
		boolean bool = service.login(id, password);//아이디와 패스워드를 가진 사용자가 있는지를 확인한다.
		
		String page;
		
		if(customer==null)//id에 해당되는 customer정보를 가져오기
		{	
			page="/view/loginFail.jsp";	
		}
		
		else if(customer!=null && bool==false)
		{
			page="/view/loginMistake.jsp";
			request.setAttribute("customer", customer);
		}
		else
		{
			page="/view/loginSuccess.jsp";
			request.setAttribute("customer", customer);//커스토머 정보 출력
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
