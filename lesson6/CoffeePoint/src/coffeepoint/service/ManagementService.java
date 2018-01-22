package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.employee.Employee;

/* Operations with management of personal */
public class ManagementService {
    private CoffeePoint coffeePoint;

    public ManagementService(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }

    public void hireEmployee(Employee employee){
        employee.setCoffeePoint(coffeePoint);
        coffeePoint.getEmployees().add(employee);
    }

    public void setTodayEmployee(){
        Employee employee = coffeePoint.getEmployees().iterator().next();
        coffeePoint.setTodayEmployee(employee);
    }
}
