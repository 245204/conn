package com.ust.project.service;

import com.ust.project.dto.RatingDto;
import com.ust.project.model.Music;
import com.ust.project.model.Rating;
import com.ust.project.repository.MusicRepository;
import com.ust.project.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService();
        userServiceUnderTest.musicRepository = mock(MusicRepository.class);
        userServiceUnderTest.ratingRepository = mock(RatingRepository.class);
    }

    @Test
    void testFetchAllMusics() {
        // Setup
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setOverallRate(0.0);
        final List<Music> expectedResult = List.of(music);

        // Configure MusicRepository.findAll(...).
        final Music music2 = new Music();
        music2.setMusicId(0L);
        music2.setMusicName("musicName");
        music2.setArtistName("artistName");
        music2.setMusicGenre("musicGenre");
        music2.setOverallRate(0.0);
        final List<Music> music1 = List.of(music2);
        when(userServiceUnderTest.musicRepository.findAll()).thenReturn(music1);

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchAllMusics();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchAllMusics_MusicRepositoryReturnsNoItems() {
        // Setup
        when(userServiceUnderTest.musicRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchAllMusics();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFetchMusicByName() {
        // Setup
        final Music expectedResult = new Music();
        expectedResult.setMusicId(0L);
        expectedResult.setMusicName("musicName");
        expectedResult.setArtistName("artistName");
        expectedResult.setMusicGenre("musicGenre");
        expectedResult.setOverallRate(0.0);

        // Configure MusicRepository.findByMusicName(...).
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setOverallRate(0.0);
        when(userServiceUnderTest.musicRepository.findByMusicName("musicName")).thenReturn(music);

        // Run the test
        final Music result = userServiceUnderTest.fetchMusicByName("musicName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchMusicByDate() {
        // Setup
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setOverallRate(0.0);
        final List<Music> expectedResult = List.of(music);

        // Configure MusicRepository.findAllBySongReleaseDate(...).
        final Music music2 = new Music();
        music2.setMusicId(0L);
        music2.setMusicName("musicName");
        music2.setArtistName("artistName");
        music2.setMusicGenre("musicGenre");
        music2.setOverallRate(0.0);
        final List<Music> music1 = List.of(music2);
        when(userServiceUnderTest.musicRepository.findAllBySongReleaseDate("songReleaseDate")).thenReturn(music1);

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByDate("songReleaseDate");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchMusicByDate_MusicRepositoryReturnsNoItems() {
        // Setup
        when(userServiceUnderTest.musicRepository.findAllBySongReleaseDate("songReleaseDate"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByDate("songReleaseDate");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFetchMusicByArtistName() {
        // Setup
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setOverallRate(0.0);
        final List<Music> expectedResult = List.of(music);

        // Configure MusicRepository.findAllByArtistName(...).
        final Music music2 = new Music();
        music2.setMusicId(0L);
        music2.setMusicName("musicName");
        music2.setArtistName("artistName");
        music2.setMusicGenre("musicGenre");
        music2.setOverallRate(0.0);
        final List<Music> music1 = List.of(music2);
        when(userServiceUnderTest.musicRepository.findAllByArtistName("artsitName")).thenReturn(music1);

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByArtistName("artsitName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchMusicByArtistName_MusicRepositoryReturnsNoItems() {
        // Setup
        when(userServiceUnderTest.musicRepository.findAllByArtistName("artsitName"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByArtistName("artsitName");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFetchMusicByGenre() {
        // Setup
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setOverallRate(0.0);
        final List<Music> expectedResult = List.of(music);

        // Configure MusicRepository.findAllByMusicGenre(...).
        final Music music2 = new Music();
        music2.setMusicId(0L);
        music2.setMusicName("musicName");
        music2.setArtistName("artistName");
        music2.setMusicGenre("musicGenre");
        music2.setOverallRate(0.0);
        final List<Music> music1 = List.of(music2);
        when(userServiceUnderTest.musicRepository.findAllByMusicGenre("musicGenre")).thenReturn(music1);

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByGenre("musicGenre");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchMusicByGenre_MusicRepositoryReturnsNoItems() {
        // Setup
        when(userServiceUnderTest.musicRepository.findAllByMusicGenre("musicGenre"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Music> result = userServiceUnderTest.fetchMusicByGenre("musicGenre");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testAddMusicRating() {
        // Setup
        final RatingDto ratingDto = new RatingDto(0.0);

        // Configure MusicRepository.findById(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setOverallRate(0.0);
        final Optional<Music> music = Optional.of(music1);
        when(userServiceUnderTest.musicRepository.findById(0L)).thenReturn(music);

        // Configure RatingRepository.findByUserId(...).
        final Optional<List<Rating>> ratings = Optional.of(List.of(new Rating(0L, 0L, 0L, 0.0)));
        when(userServiceUnderTest.ratingRepository.findByUserId(0L)).thenReturn(ratings);

        when(userServiceUnderTest.ratingRepository.findAllByMusicId(0L))
                .thenReturn(List.of(new Rating(0L, 0L, 0L, 0.0)));

        // Run the test
        final boolean result = userServiceUnderTest.addMusicRating(ratingDto, 0L, 0L);

        // Verify the results
        assertThat(result).isTrue();
        verify(userServiceUnderTest.ratingRepository).save(new Rating(0L, 0L, 0L, 0.0));

        // Confirm MusicRepository.save(...).
        final Music entity = new Music();
        entity.setMusicId(0L);
        entity.setMusicName("musicName");
        entity.setArtistName("artistName");
        entity.setMusicGenre("musicGenre");
        entity.setOverallRate(0.0);
        verify(userServiceUnderTest.musicRepository).save(entity);
    }

    @Test
    void testAddMusicRating_MusicRepositoryFindByIdReturnsAbsent() {
        // Setup
        final RatingDto ratingDto = new RatingDto(0.0);
        when(userServiceUnderTest.musicRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final boolean result = userServiceUnderTest.addMusicRating(ratingDto, 0L, 0L);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testAddMusicRating_RatingRepositoryFindByUserIdReturnsAbsent() {
        // Setup
        final RatingDto ratingDto = new RatingDto(0.0);

        // Configure MusicRepository.findById(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setOverallRate(0.0);
        final Optional<Music> music = Optional.of(music1);
        when(userServiceUnderTest.musicRepository.findById(0L)).thenReturn(music);

        when(userServiceUnderTest.ratingRepository.findByUserId(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceUnderTest.addMusicRating(ratingDto, 0L, 0L))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testAddMusicRating_RatingRepositoryFindByUserIdReturnsNoItems() {
        // Setup
        final RatingDto ratingDto = new RatingDto(0.0);

        // Configure MusicRepository.findById(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setOverallRate(0.0);
        final Optional<Music> music = Optional.of(music1);
        when(userServiceUnderTest.musicRepository.findById(0L)).thenReturn(music);

        when(userServiceUnderTest.ratingRepository.findByUserId(0L)).thenReturn(Optional.of(Collections.emptyList()));
        when(userServiceUnderTest.ratingRepository.findAllByMusicId(0L))
                .thenReturn(List.of(new Rating(0L, 0L, 0L, 0.0)));

        // Run the test
        final boolean result = userServiceUnderTest.addMusicRating(ratingDto, 0L, 0L);

        // Verify the results
        assertThat(result).isTrue();
        verify(userServiceUnderTest.ratingRepository).save(new Rating(0L, 0L, 0L, 0.0));

        // Confirm MusicRepository.save(...).
        final Music entity = new Music();
        entity.setMusicId(0L);
        entity.setMusicName("musicName");
        entity.setArtistName("artistName");
        entity.setMusicGenre("musicGenre");
        entity.setOverallRate(0.0);
        verify(userServiceUnderTest.musicRepository).save(entity);
    }

    @Test
    void testAddMusicRating_RatingRepositoryFindAllByMusicIdReturnsNoItems() {
        // Setup
        final RatingDto ratingDto = new RatingDto(0.0);

        // Configure MusicRepository.findById(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setOverallRate(0.0);
        final Optional<Music> music = Optional.of(music1);
        when(userServiceUnderTest.musicRepository.findById(0L)).thenReturn(music);

        // Configure RatingRepository.findByUserId(...).
        final Optional<List<Rating>> ratings = Optional.of(List.of(new Rating(0L, 0L, 0L, 0.0)));
        when(userServiceUnderTest.ratingRepository.findByUserId(0L)).thenReturn(ratings);

        when(userServiceUnderTest.ratingRepository.findAllByMusicId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = userServiceUnderTest.addMusicRating(ratingDto, 0L, 0L);

        // Verify the results
        assertThat(result).isTrue();
        verify(userServiceUnderTest.ratingRepository).save(new Rating(0L, 0L, 0L, 0.0));

        // Confirm MusicRepository.save(...).
        final Music entity = new Music();
        entity.setMusicId(0L);
        entity.setMusicName("musicName");
        entity.setArtistName("artistName");
        entity.setMusicGenre("musicGenre");
        entity.setOverallRate(0.0);
        verify(userServiceUnderTest.musicRepository).save(entity);
    }
}
