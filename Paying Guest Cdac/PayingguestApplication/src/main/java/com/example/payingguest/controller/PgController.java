package com.example.payingguest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payingguest.entities.PG;
import com.example.payingguest.service.PgService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pg_api/pg")
public class PgController {
	  @Autowired 
	  private PgService pgServiceRef;
	  
	  
	  
	  @PostMapping("/register")
	  public ResponseEntity<?> registerPg(@RequestBody PG pgRef){
		  return pgServiceRef.registerPg(pgRef);
	  }
	  
	  
	  @GetMapping("/getAllPg")
	  public ResponseEntity<?> getAllPg(){
		  return pgServiceRef.getAllPg();
	  }
	  
	  @GetMapping("/getAllPgByLocation/{location}")
	  public ResponseEntity<?> getAllPgByLocation(@PathVariable String location){
		  return pgServiceRef.getPgByLocation(location);
	  }
	  
	  @PutMapping("/update")
	  public ResponseEntity<?> update(@RequestBody PG pgRef){
		  return pgServiceRef.update(pgRef);
	  }
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> delete(@PathVariable  Long id){
		  return pgServiceRef.delete(id);
	  }
}
