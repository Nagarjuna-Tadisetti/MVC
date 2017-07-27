package com.nag;
import java.util.List;
import java.util.ArrayList;
import com.nag.Employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	
	@RequestMapping(value="/add", method=RequestMethod.GET, headers="Accept=application/json")
	public List addEmp(@RequestParam String name, @RequestParam int id, @RequestParam String location ) throws Exception{
		
		Employee e = new Employee(name, id, location);
		EmployeeDAOImpl d = new EmployeeDAOImpl();
		d.addEmployee(e);
		
		List employee = new ArrayList();
		employee.add(e);
		return employee;
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET, headers="Accept=application/json")
	public List getEmp(@RequestParam int id) throws Exception{
		
		EmployeeDAOImpl d = new EmployeeDAOImpl();
		Employee e = d.getEmployee(id);
		
		List employee = new ArrayList();
		employee.add(e);
		return employee;
	}
	
}
