package com.nacu.petclinic.bootstrap;

import com.nacu.petclinic.model.Owner;
import com.nacu.petclinic.model.Pet;
import com.nacu.petclinic.model.PetType;
import com.nacu.petclinic.model.Vet;
import com.nacu.petclinic.services.OwnerService;
import com.nacu.petclinic.services.PetTypeService;
import com.nacu.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType("Dog");
        PetType cat = new PetType("Cat");
        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner("Florin", "Nacu", "Strada Stefan Cel Mare", "Corod", "0321321412");
        Owner owner2 = new Owner("Gigi", "Becali", "O Strada din Pipera", "Bucales", "321321412");
        Owner owner3 = new Owner("Cristiano", "Ronaldo", "Strada lui Juve", "Torino", "321421");

        Pet pet1 = new Pet("Turi", savedDogPetType, owner1, LocalDate.of(2020, 7, 15));
        Pet pet2 = new Pet("Bianco", savedDogPetType, owner1, LocalDate.of(2020, 10, 21));
        Pet pet3 = new Pet("Becalinho", savedCatPetType, owner2, LocalDate.of(1999, 12, 1));

        owner1.getPets().add(pet1);
        owner1.getPets().add(pet2);
        owner2.getPets().add(pet3);

        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);

        Vet vet1 = new Vet("Andreea", "Cristea");
        Vet vet2 = new Vet("Bianca", "Andrei");
        vetService.save(vet1);
        vetService.save(vet2);
    }
}
