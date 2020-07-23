package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertCustomerToCustomerDTO(customerService.saveCustomer(convertCustomerDTOToCustomer(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        customers.forEach(customer -> {
            customerDTOs.add(convertCustomerToCustomerDTO(customer));
        });
        return customerDTOs;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToCustomerDTO(customerService.getOwnerByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEmployeeToEmployeeDTO(employeeService.saveEmployee(convertEmployeeDTOToEmployee(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List <Employee> employees = employeeService.findEmployeesForService(employeeRequestDTO);
        List <EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> {
            employeeDTOS.add(convertEmployeeToEmployeeDTO(employee));
        });

        return employeeDTOS;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        if(employee.getSkills() != null && employee.getSkills().size() > 0) {
            Set<EmployeeSkill> skills = new HashSet<>(employee.getSkills());
            employeeDTO.setSkills(skills);
        }

        if(employee.getDaysAvailable() != null && employee.getDaysAvailable().size() > 0) {
            Set<DayOfWeek> availability = new HashSet<>(employee.getDaysAvailable());
            employeeDTO.setDaysAvailable(availability);
        }

        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        if(employeeDTO.getSkills() != null && employeeDTO.getSkills().size() > 0) {
            Set<EmployeeSkill> skills = new HashSet<>(employeeDTO.getSkills());
            employee.setSkills(skills);
        }

        if(employeeDTO.getDaysAvailable() != null && employeeDTO.getDaysAvailable().size() > 0) {
            Set<DayOfWeek> availability = new HashSet<>(employeeDTO.getDaysAvailable());
            employee.setDaysAvailable(availability);
        }

        return employee;
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        List<Long> petIds = new ArrayList<>();
        BeanUtils.copyProperties(customer, customerDTO);
        if(customer.getPets() != null) {
            customer.getPets().forEach(pet -> {
                petIds.add(pet.getId());
            });
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setPets(new ArrayList<>());
        if(customerDTO.getPetIds() != null && customerDTO.getPetIds().size() > 0) {
            customerDTO.getPetIds().forEach(id -> {
                Pet pet = new Pet();
                pet.setId(id);
                customer.getPets().add(pet);
            });
        }
        return customer;
    }
}
