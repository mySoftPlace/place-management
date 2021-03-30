package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Place;

@RepositoryRestResource  //Exposing the repository as a webservice
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
