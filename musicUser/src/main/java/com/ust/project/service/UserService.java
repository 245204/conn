package com.ust.project.service;

import java.util.ArrayList;
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

	public Music fetchMusicByName(String musicName) {

		return musicRepository.findByMusicName(musicName);
	}

	public List<Music> fetchMusicByDate(String songReleaseDate) {

		return musicRepository.findAllBySongReleaseDate(songReleaseDate);
	}

	public List<Music> fetchMusicByArtistName(String artsitName) {

		return musicRepository.findAllByArtistName(artsitName);
	}

	public List<Music> fetchMusicByGenre(String musicGenre) {

		return musicRepository.findAllByMusicGenre(musicGenre);
	}
	public boolean addMusicRating(RatingDto ratingDto, Long musicId, Long userId) {
		Optional<Music> musicOptional = musicRepository.findById(musicId);

		if (musicOptional.isPresent()) {
			Optional<List<Rating>> ratingsOptional = ratingRepository.findByUserId(userId);
			List<Rating> ratingList = ratingsOptional.orElse(new ArrayList<>());

			boolean ratingFound = false;
			for (Rating rating : ratingList) {
				if (rating.getMusicId() == musicId) {
					rating.setRating(ratingDto.getRating());
					ratingRepository.save(rating);
					ratingFound = true;
					break;
				}
			}

			if (!ratingFound) {
				Rating newRating = new Rating();
				newRating.setMusicId(musicId);
				newRating.setUserId(userId);
				newRating.setRating(ratingDto.getRating());
				ratingRepository.save(newRating);
			}

			List<Rating> allRatings = ratingRepository.findAllByMusicId(musicId);
			double sumOfRatings = 0.0;
			int numberOfRatings = allRatings.size();
			for (Rating rating : allRatings) {
				sumOfRatings += rating.getRating();
			}

			double overallRate = sumOfRatings / numberOfRatings;

			Music music = musicOptional.get();
			music.setOverallRate(overallRate);
			musicRepository.save(music);
			return true;
		} else {
			return false;
		}
	}
	public double getMusicOverallRating(Long musicId) {
		Optional<Music> musicOptional = musicRepository.findById(musicId);
		if (musicOptional.isPresent()) {
			Music music = musicOptional.get();
			return music.getOverallRate();
		}
		else {
			return 0.0;
		}
	}

//	public boolean addMusicRating(RatingDto ratingDto, Long musicId, Long userId) {
//		Optional<Music> op = musicRepository.findById(musicId);
//
//		if (op.isPresent()) {
//
//			Optional<List<Rating>> op2 = ratingRepository.findByUserId(userId);
//			List<Rating> ratingObjList = op2.get();
//			int flag = 0;
//			for (Rating obj : ratingObjList) {
//				if (obj.getMusicId() == musicId) {
//					obj.setRating(ratingDto.getRating());
//					// obj.setMessage(ratingDto.getMessage());
//					flag = 1;
//					ratingRepository.save(obj);
//				}
//			}
//			if (flag == 0) {
//				Rating ratingObj = new Rating();
//				ratingObj.setMusicId(musicId);
//				ratingObj.setUserId(userId);
//				ratingObj.setRating(ratingDto.getRating());
//				// ratingObj.setMessage(ratingDto.getMessage());
//				ratingRepository.save(ratingObj);
//			}
//
//		} else {
//			return false;
//		}
//		Music music = op.get();
//		List<Rating> list = ratingRepository.findAllByMusicId(musicId);
//		AtomicReference<Double> overallRate = new AtomicReference<>(0.0);
//		list.forEach((e) -> overallRate.updateAndGet(currentRate -> currentRate + e.getRating()));
//		overallRate.set(overallRate.get() / list.size());
//		music.setOverallRate(overallRate.get());
//		musicRepository.save(music);
//		return true;
//	}

}

