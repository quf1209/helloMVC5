package service;

import java.util.HashMap;
import java.util.Map;

import model.Customer;

public class CustomerService {

	private Map<String, Customer> customers;
	
	private static final CustomerService instance = new CustomerService();
	
	private CustomerService()
	{
		customers = new HashMap<String, Customer>();
	}
	
	public static CustomerService getInstance()
	{
		return instance;
	}
	
	
	public void addCustomer(Customer customer)
	{
		customers.put(customer.getId(), customer);
	}
	
	public Customer findCustomer(String id)
	{
		if(id!=null)
			return (customers.get(id.toLowerCase()));
		else
			return null;
	}
	
	public boolean login(String id, String password)
	{
		Customer s = customers.get(id.toLowerCase());
		if(id.equals(s.getId()))
		{
			if(password.equals(s.getPassword()))
				return true;
			else 
				return false;
			
		}
		else
			return false;
	}
}
