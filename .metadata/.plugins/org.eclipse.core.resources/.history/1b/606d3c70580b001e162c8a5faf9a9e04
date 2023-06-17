package com.ust.admin_service.service;

import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MusicService {


    @Autowired
    MusicRepository musicrepo;

    public Music add(MusicDto dto) {
//		Music music=Music.build(0,dto.getMusicName(),dto.getArtistName(),
//				dto.getMusicGenre(),dto.getSongReleaseDate(),dto.getSongLanguage(),
//				dto.getDuration(),dto.getCountry(),dto.getOverallRate());
        Music music=new Music();
        music.setMusicName(dto.getMusicName());
        music.setArtistName(dto.getArtistName());
        music.setMusicGenre(dto.getMusicGenre());
        music.setSongReleaseDate(dto.getSongReleaseDate());
        music.setSongLanguage(dto.getSongLanguage());
        music.setDuration(dto.getDuration());
        music.setCountry(dto.getCountry());
        music.setOverallRate(dto.getOverallRate());
        return musicrepo.save(music);
    }

    public List<Music> view() {
        // TODO Auto-generated method stub
        return musicrepo.findAll();
    }

    public Music fetchById(long musicId) throws MusicNotFoundException {
        Optional<Music> op=musicrepo.findById(musicId);
        if(op.isPresent()) {
            return op.get();
        }
        throw new MusicNotFoundException("music not found with id : "+ musicId);
    }

    public Music update(MusicDto dto, long musicId) throws MusicNotFoundException {
        Optional<Music> op = musicrepo.findById(musicId);
        Music temp = null;
        if (op.isPresent()) {
            temp = op.get();
            temp.setMusicName(dto.getMusicName());
            temp.setArtistName(dto.getArtistName());
            temp.setMusicGenre(dto.getMusicGenre());
            temp.setSongReleaseDate(dto.getSongReleaseDate());
            temp.setSongLanguage(dto.getSongLanguage());
            temp.setDuration(dto.getDuration());
            temp.setCountry(dto.getCountry());
            temp.setOverallRate(dto.getOverallRate());
            return musicrepo.save(temp);
        }
        else
            throw new MusicNotFoundException("music not found with id : "+ musicId);
    }

    public String delete(long musicId) throws MusicNotFoundException {
        Optional<Music> opt= musicrepo.findById(musicId);
        if(opt.isPresent()) {
            musicrepo.deleteById(musicId);
            return "Music Deleted Successfully";

        }
        else {
            throw new MusicNotFoundException("music not found with id : "+ musicId);
        }
    }



}


