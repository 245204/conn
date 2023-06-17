package com.ust.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.project.dto.RatingDto;
import com.ust.project.exception.InvalidRatingException;
import com.ust.project.exception.MusicNotFoundException;
import com.ust.project.model.Music;
import com.ust.project.service.UserService;

@RestController
@Validated
@RequestMapping("/api/1.0/users")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/viewAllMusics")
	public ResponseEntity<List<Music>> getAllMusics()
	{

		return ResponseEntity.ok().body(userService.fetchAllMusics());
	}
	
	
	@GetMapping("search/music/name/{name}")
	public ResponseEntity<Music> getMusicByName(@PathVariable String name)
	{
		return ResponseEntity.ok().body(userService.fetchMusicByName(name));
		
	}
	
	
	@GetMapping("search/music/date/{date}")
	public ResponseEntity<List<Music>> getMusicByDate(@PathVariable String date)
	{
		return ResponseEntity.ok().body(userService.fetchMusicByDate(date));
	}
	
	
	@PostMapping("add/rating/music/{musicid}/{userid}")
	public ResponseEntity<String> addRatingMusic(@Valid @RequestBody RatingDto ratingdto, @PathVariable Long musicid,@PathVariable Long userid) throws InvalidRatingException, MusicNotFoundException {
		if (ratingdto.getRating() < 1 || ratingdto.getRating() > 5) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rating. Please provide a rating between 1 and 10");
			throw new InvalidRatingException("Invalid rating. Please provide a rating between 1 and 10");
		}
		boolean ratingAdded = userService.addMusicRating(ratingdto, musicid,userid);

		if (ratingAdded) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Rating added successfully.");
		} else {
			throw new MusicNotFoundException("Failed to add rating, Music not found");
		}

	}

}
