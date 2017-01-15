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
		boolean bool = service.login(id, password);//���̵�� �н����带 ���� ����ڰ� �ִ����� Ȯ���Ѵ�.
		
		String page;
		
		if(customer==null)//id�� �ش�Ǵ� customer������ ��������
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
			request.setAttribute("customer", customer);//Ŀ����� ���� ���
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
