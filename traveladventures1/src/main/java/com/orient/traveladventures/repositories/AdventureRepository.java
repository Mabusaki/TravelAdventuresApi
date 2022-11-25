package com.orient.traveladventures.repositories;

import java.util.List;
import com.orient.traveladventures.entities.Adventure;
import org.springframework.data.repository.CrudRepository;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {

    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);

}
