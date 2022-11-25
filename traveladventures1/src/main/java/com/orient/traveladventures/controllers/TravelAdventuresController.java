package com.orient.traveladventures.controllers;

import com.orient.traveladventures.entities.Adventure;
import com.orient.traveladventures.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    @GetMapping()
    public Iterable<Adventure> getAdventures(){
        return this.adventureRepository.findAll();
    }

    @GetMapping("/bycountry/{country}")
    public List<Adventure> getByCountry(@PathVariable String country){
        return this.adventureRepository.findByCountry(country);
    }


    @GetMapping(path = "/bystate")
    public List<Adventure> getAdventuresFromLisboa(@RequestParam String state){
        return this.adventureRepository.findByState(state);
    }

    @PostMapping("/addnew")
    public Adventure addNewAdventure(@RequestBody Adventure adventure){
        return adventureRepository.save(adventure);
    }

    @PutMapping("/{id}")
    public Adventure upadateAdventure(@PathVariable Integer id, @RequestBody Adventure adventure){

        Optional<Adventure> adventureUpdateOptional = this.adventureRepository.findById(id);

        if(!adventureUpdateOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Adventure adventureToUpdate = adventureUpdateOptional.get();

        if(!adventure.getBlogCompleted()){
            adventureToUpdate.setBlogCompleted(true);
        } else {
            adventureToUpdate.setBlogCompleted(false);
        }

        Adventure updatedAdventure = this.adventureRepository.save(adventureToUpdate);
        return updatedAdventure;
    }

    @DeleteMapping("/{id}")
    public String deleteAdventure(@PathVariable Integer id){

        Optional<Adventure> adventureToDeleteOptional = this.adventureRepository.findById(id);
        if(adventureToDeleteOptional.isPresent()){
            Adventure adventureToDelete = adventureToDeleteOptional.get();
            this.adventureRepository.delete(adventureToDelete);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "Data has been deleted successfully!";
    }









}
