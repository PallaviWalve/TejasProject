package com.example.payingguest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payingguest.entities.Complaint;
import com.example.payingguest.service.ComplaintService;

@RestController
@RequestMapping("pg_api/complaint")
@CrossOrigin("*")
public class ComplaintController {
	@Autowired
	private ComplaintService complaintServiceRef;

	@PostMapping("/create/{userid}")
	public ResponseEntity<?> writeComplaint(@RequestBody Complaint complaintRef, @PathVariable Long userid) {
		return complaintServiceRef.writeComplaint(complaintRef, userid);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getComplaint(@PathVariable Long id) {
		return complaintServiceRef.getComplaint(id);
	}

//	@GetMapping("/getComplaintByUser/{id}")
//	public ResponseEntity<?> getComplaintByUserId(@PathVariable Long id){
//		try {
//		return  new ResponseEntity<>(complaintServiceRef.getComplaintByUserId(id),HttpStatus.OK);
//		}catch (ComplaintNotFoundException e) {
//		 return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//		}
//	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		return complaintServiceRef.deleteComplaint(id);
	}
}
