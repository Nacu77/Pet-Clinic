package com.nacu.petclinic.bootstrap;

import com.nacu.petclinic.model.Owner;
import com.nacu.petclinic.model.Vet;
import com.nacu.petclinic.services.OwnerService;
import com.nacu.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading owners...");
        Owner owner1 = new Owner("Florin", "Nacu");
        Owner owner2 = new Owner("Gigi", "Becali");
        Owner owner3 = new Owner("Cristiano", "Ronaldo");
        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);
        System.out.println("Loaded owners successfully");

        System.out.println("Loading vets...");
        Vet vet1 = new Vet("Andreea", "Cristea");
        Vet vet2 = new Vet("Bianca", "Andrei");
        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("Loaded vets successfully");
    }
}
