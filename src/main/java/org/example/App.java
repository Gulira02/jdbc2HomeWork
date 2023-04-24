package org.example;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.JobService;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.service.impl.JobServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
        EmployeeService employeeService=new EmployeeServiceImpl();
        JobService jobService=new JobServiceImpl();
      // employeeService.createEmployee();
      // employeeService.addEmployee(new Employee("Gulira","Merlin",20,"g@gmail.com",1));
       // employeeService.updateEmployee(1l,new Employee("Dina","Fena",13,"D@gmail.com",2));
       // employeeService.dropTable();
      //  System.out.println(employeeService.findByEmail("D@gmail.com"));
      //  employeeService.cleanTable();
       // System.out.println(employeeService.getEmployeeById(2l));
        System.out.println(employeeService.getEmployeeById(1l));


        //   jobService.createJobTable();
        //("Mentor","Management","Instructor")
        //("Java","JavaScript")
        //("Backend developer","Fronted developer")
       // jobService.addJob(new Job("Testirovshik","Python","Fullstack",5));
       // System.out.println(jobService.getJobById(3l));
       // System.out.println(jobService.getJobByEmployeeId(1l));
       // jobService.deleteDescriptionColumn();
        System.out.println(jobService.sortByExperience("ascOrDesc"));


    }
}
