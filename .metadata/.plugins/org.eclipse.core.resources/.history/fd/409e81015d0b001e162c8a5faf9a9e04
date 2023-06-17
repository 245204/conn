package com.ust.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.concurrent.atomic.AtomicDouble;
import java.util.concurrent.atomic.AtomicReference;

import com.ust.project.dto.RatingDto;
import com.ust.project.model.Music;
import com.ust.project.model.Rating;
import com.ust.project.repository.MusicRepository;
import com.ust.project.repository.RatingRepository;

@Service
public class UserService {
	@Autowired
	MusicRepository musicRepository;
	@Autowired
	RatingRepository ratingRepository;

	public List<Music> fetchAllMusics() {
		return musicRepository.findAll();
	}

	public Music fetchMusicByName(String name) {

		return musicRepository.findByMusicName(name);
	}

	public List<Music> fetchMusicByDate(String date) {

		return musicRepository.findAllBySongReleaseDate(date);
	}
	

	public boolean addMusicRating(RatingDto ratingDto, Long musicId,Long userId) {
	    Optional<Music> op = musicRepository.findById(musicId);
	    
	    if(op.isPresent())
	    {
	    
	    	Optional<List<Rating>> op2 =  ratingRepository.findByUserId(userId);
	    	List<Rating> ratingObjList = op2.get();
	    	int flag=0;
	    	for(Rating obj:ratingObjList)
	    	{
	    		if(obj.getMusicId()==musicId)
			    	{
				    obj.setRating(ratingDto.getRating());
				//    obj.setMessage(ratingDto.getMessage());
				    flag=1;
				    ratingRepository.save(obj);
			    	}
	    	}
	    	if(flag==0)
	    	{
	    		 Rating ratingObj = new Rating();
	    		    ratingObj.setMusicId(musicId);
	    		    ratingObj.setUserId(userId);
	    		    ratingObj.setRating(ratingDto.getRating());
	    		   // ratingObj.setMessage(ratingDto.getMessage());
	    		    ratingRepository.save(ratingObj);
	    	}
	    	
	    }
	    else
	    {
	    return false;
	    }
	    Music music = op.get();
	    List<Rating> list = ratingRepository.findAllByMusicId(musicId);
	    AtomicReference<Double> overallRate = new AtomicReference<>(0.0);
	    list.forEach((e) -> overallRate.updateAndGet(currentRate -> currentRate + e.getRating()));
	    overallRate.set(overallRate.get() / list.size());
	    music.setOverallRate(overallRate.get());
	    musicRepository.save(music);
	    return true;
	    }
	    
	}


