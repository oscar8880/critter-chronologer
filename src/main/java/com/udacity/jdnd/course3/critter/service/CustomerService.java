package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
  @Autowired
  CustomerRepository customerRepository;

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer getOwnerByPet(Long petId) {
    return customerRepository.findByPets_Id(petId);
  }

  public void addPetToCustomer(Pet pet, Customer customer) {
    List<Pet> pets = customer.getPets();
    if(pets == null){
      pets = new ArrayList<Pet>();
    }
    pets.add(pet);
    customer.setPets(pets);
    customerRepository.save(customer);
  }
}