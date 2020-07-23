package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
  @Autowired
  PetRepository petRepository;
  CustomerService customerService;

  public List<Pet> getAllPets() {
    return petRepository.findAll();
  }

  public Pet savePet(Pet pet) {
    System.out.println("Pet: "+ pet);
    System.out.println("Pet.getCust: "+ pet.getCustomer());
    customerService.addPetToCustomer(pet,pet.getCustomer());
    return petRepository.save(pet);
  }

  public Pet getPetById(Long petId) {
    return petRepository.getOne(petId);
  }

  public List<Pet> getPetsByOwner(Long ownerId) {
    return petRepository.findByCustomer_Id(ownerId);
  }
}
