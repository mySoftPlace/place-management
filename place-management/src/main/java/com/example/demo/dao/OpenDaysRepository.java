package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.OpenDays;

@RepositoryRestResource  //Exposing the repository as a webservice
public interface OpenDaysRepository extends JpaRepository<OpenDays, Long> {

}
