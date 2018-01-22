package coffeepoint;

import coffeepoint.entity.employee.Employee;
import coffeepoint.entity.employee.EmployeeSchedule;
import coffeepoint.entity.equipment.Cashbox;
import coffeepoint.entity.equipment.PaymentTerminal;
import coffeepoint.entity.equipment.coffeemachine.CoffeeMachine;
import coffeepoint.entity.equipment.grill.Grill;
import coffeepoint.entity.product.Menu;
import coffeepoint.service.bonuscard.BonusCardService;
import coffeepoint.service.CustomerService;
import coffeepoint.service.ManagementService;
import coffeepoint.service.order.OrderService;
import coffeepoint.service.ProductService;
import java.util.HashSet;
import java.util.Set;

/* Represents a single CoffeePoint*/
public class CoffeePoint {
    private String name;
    private String address;
    private boolean isOpened;
    private Set<Employee> employees = new HashSet<>();
    private Employee todayEmployee;
    private EmployeeSchedule employeeSchedule; // TODO

    // Services for different domains of program
    private CustomerService customerService;
    private ManagementService management;
    private OrderService orderService;
    private ProductService productService;
    private BonusCardService bonusCardService;

    // Equipment
    private CoffeeMachine coffeeMachine;
    private Grill grill;
    private Cashbox cashbox;
    private PaymentTerminal paymentTerminal;

    // Menu of drinks and food
    private Menu menu;

    public CoffeePoint(){
        customerService = new CustomerService(this);
        management = new ManagementService(this);
        orderService = new OrderService(this);
        productService = new ProductService(this);
        menu = new Menu(this);
        bonusCardService = new BonusCardService(this);
        cashbox = new Cashbox();
        paymentTerminal = new PaymentTerminal();;
    }

    public void open(){
        isOpened = true;
        todayEmployee = employees.iterator().next();
    }

    public void close(){
        isOpened = false;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine){
        this.coffeeMachine = coffeeMachine;
    }

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public Employee getTodayEmployee() {
        return todayEmployee;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public ManagementService getManagement() {
        return management;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setTodayEmployee(Employee todayEmployee) {
        this.todayEmployee = todayEmployee;
    }

    public PaymentTerminal getPaymentTerminal() {
        return paymentTerminal;
    }

    public BonusCardService getBonusCardService() {
        return bonusCardService;
    }

    public Grill getGrill() {
        return grill;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public Cashbox getCashbox() {
        return cashbox;
    }
}