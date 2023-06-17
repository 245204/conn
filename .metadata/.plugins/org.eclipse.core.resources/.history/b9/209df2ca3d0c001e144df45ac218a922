package com.ust.admin_service.controller;

import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) // to connect from one port to another port
@RestController
@RequestMapping("/1.0/admin")
public class MusicController {

        @Autowired
        MusicService musicService;

        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/addmusic")
        public ResponseEntity<Music> addAMusic(@RequestBody MusicDto dto) {
	//return ResponseEntity.ok(musicerepo.save(dto));
            return ResponseEntity.ok(musicService.add(dto));
        }
        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping("/allmusics")
        public List<Music> viewAllMusics() {
            return musicService.view();
        }

        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping("/get/{musicId}")
        public ResponseEntity<Music> getById(@PathVariable long musicId) throws MusicNotFoundException {
            return ResponseEntity.ok(musicService.fetchById(musicId));
        }

        @CrossOrigin(origins = "http://localhost:4200")
        @PutMapping("/update/{musicId}")
        public ResponseEntity<Music> updateMusic(@RequestBody MusicDto dto,@PathVariable @Valid long musicId ) throws MusicNotFoundException {
            return ResponseEntity.ok(musicService.update(dto,musicId));
        }

        @CrossOrigin(origins = "http://localhost:4200")
        @DeleteMapping("/delete/{musicId}")
        public String deleteMusic(@PathVariable long musicId)  throws MusicNotFoundException {

            return	musicService.delete(musicId);

        }
    }




