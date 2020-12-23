package com.nacu.petclinic.bootstrap;

import com.nacu.petclinic.model.*;
import com.nacu.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
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

        Specialty specialty1 = new Specialty("Ginecolog");
        Specialty specialty2 = new Specialty("Radiolog");
        Specialty specialty3 = new Specialty("Dentist");

        Vet vet1 = new Vet("Andreea", "Cristea");
        vet1.getSpecialties().add(specialty1);
        vet1.getSpecialties().add(specialty2);

        Vet vet2 = new Vet("Bianca", "Andrei");
        vet2.getSpecialties().add(specialty3);

        vetService.save(vet1);
        vetService.save(vet2);

        Visit visit1 = new Visit(LocalDate.now(), "Are dintii pe afara", pet1);
        Visit visit2 = new Visit(LocalDate.of(2021, 10,12), "Cauta joaca mereu", pet1);
        Visit visit3 = new Visit(LocalDate.now(), "E cam gras", pet2);

        visitService.save(visit1);
        visitService.save(visit2);
        visitService.save(visit3);
    }
}
