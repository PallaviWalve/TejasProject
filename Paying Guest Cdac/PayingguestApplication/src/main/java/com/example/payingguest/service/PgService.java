package com.example.payingguest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.payingguest.entities.PG;
import com.example.payingguest.exception.CustomException;
import com.example.payingguest.repository.PgRepo;

@Service
public class PgService {

	@Autowired
	private PgRepo pgRepoRef;

	public ResponseEntity<?> registerPg(PG pgRef) {
		try {
			PG pg = pgRepoRef.save(pgRef);
			return new ResponseEntity<>(pg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("unable to Register try again", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public ResponseEntity<?> getAllPg() {
		try {
			List<PG> pgList = pgRepoRef.findAll();
			if (pgList.size() != 0) {
				return new ResponseEntity<>(pgList, HttpStatus.OK);
			} else {
				throw new CustomException("no pg found");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	public PG  getOnePg(Long id) {
			return pgRepoRef.findById(id).orElseThrow(()->new CustomException("pg not Found"));
	}

	public ResponseEntity<?> getPgByLocation(String address) {
		try {
			List<PG> pgList = pgRepoRef.findByAddress(address);
			if (pgList.size() != 0) {
				return new ResponseEntity<>(pgList, HttpStatus.OK);
			} else {
				throw new CustomException("no pg found");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> update(PG newpg) {
		try {
			PG pg = pgRepoRef.findById(newpg.getPgId()).orElseThrow(() -> new CustomException("PG not found"));
			if (pg != null) {
				pg.setAddress(newpg.getAddress());
				pg.setPgName(newpg.getPgName());
				pg.setOwnerName(newpg.getOwnerName());
				pg.setContactNo(newpg.getContactNo());
				pgRepoRef.save(pg);
				return new ResponseEntity<>("info updated Successful", HttpStatus.OK);
			} else {
				throw new CustomException("Unable to update");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> delete(Long id) {
		try {
			PG pg = pgRepoRef.findById(id).orElseThrow(() -> new CustomException("PG not found"));
			if (pg != null) {
				pgRepoRef.deleteById(id);
				return new ResponseEntity<>("pg deleted Successful", HttpStatus.OK);
			} else {
				throw new CustomException("Unable to delete");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
