package com.nacu.petclinic.controllers;

import com.nacu.petclinic.model.Pet;
import com.nacu.petclinic.model.Visit;
import com.nacu.petclinic.services.PetService;
import com.nacu.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VisitController {

    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    @Autowired
    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("petId") Long petId) {
        return petService.findById(petId);
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(Pet pet, Model model) {
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        model.addAttribute("visit", visit);
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(Pet pet, @Valid Visit visit, BindingResult result, Model model) {
        pet.getVisits().add(visit);
        if(result.hasErrors()) {
            model.addAttribute("visit", visit);
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + pet.getOwner().getId();
        }
    }

}


































