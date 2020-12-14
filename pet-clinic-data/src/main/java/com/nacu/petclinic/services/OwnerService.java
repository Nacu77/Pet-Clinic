package com.nacu.petclinic.services;

import com.nacu.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
