package com.ust.admin_service.service;

import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.repository.MusicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class MusicServiceTest {

    private MusicService musicServiceUnderTest;

    @BeforeEach
    void setUp() {
        musicServiceUnderTest = new MusicService();
        musicServiceUnderTest.musicrepo = mock(MusicRepository.class);
    }

    @Test
    void testAdd() {
        // Setup
        final MusicDto dto = new MusicDto();
        dto.setMusicName("musicName");
        dto.setArtistName("artistName");
        dto.setMusicGenre("musicGenre");
        dto.setSongReleaseDate("songReleaseDate");
        dto.setSongLanguage("songLanguage");
        dto.setDuration("duration");
        dto.setCountry("country");
        dto.setOverallRate(0.0);

        final Music expectedResult = new Music();

        // Mock the save method
        when(musicServiceUnderTest.musicrepo.save(any(Music.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Run the test
        final Music result = musicServiceUnderTest.add(dto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testView() {
        // Setup
        final List<Music> expectedResult = List.of(new Music());
        when(musicServiceUnderTest.musicrepo.findAll()).thenReturn(List.of(new Music()));

        // Run the test
        final List<Music> result = musicServiceUnderTest.view();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testView_MusicRepositoryReturnsNoItems() {
        // Setup
        when(musicServiceUnderTest.musicrepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Music> result = musicServiceUnderTest.view();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFetchById() throws Exception {
        // Setup
        final Music expectedResult = new Music();
        when(musicServiceUnderTest.musicrepo.findById(0L)).thenReturn(Optional.of(new Music()));

        // Run the test
        final Music result = musicServiceUnderTest.fetchById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchById_MusicRepositoryReturnsAbsent() {
        // Setup
        when(musicServiceUnderTest.musicrepo.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> musicServiceUnderTest.fetchById(0L)).isInstanceOf(MusicNotFoundException.class);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final MusicDto dto = new MusicDto();
        dto.setMusicName("musicName");
        dto.setArtistName("artistName");
        dto.setMusicGenre("musicGenre");
        dto.setSongReleaseDate("songReleaseDate");
        dto.setSongLanguage("songLanguage");
        dto.setDuration("duration");
        dto.setCountry("country");
        dto.setOverallRate(0.0);

        final Music expectedResult = new Music();

        // Mock the findById method
        when(musicServiceUnderTest.musicrepo.findById(any(Long.class))).thenReturn(Optional.of(expectedResult));

        // Mock the save method
        when(musicServiceUnderTest.musicrepo.save(any(Music.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Run the test
        final Music result = musicServiceUnderTest.update(dto, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testUpdate_MusicRepositoryFindByIdReturnsAbsent() {
        // Setup
        final MusicDto dto = new MusicDto();
        dto.setMusicName("musicName");
        dto.setArtistName("artistName");
        dto.setMusicGenre("musicGenre");
        dto.setSongReleaseDate("songReleaseDate");
        dto.setSongLanguage("songLanguage");
        dto.setDuration("duration");
        dto.setCountry("country");
        dto.setOverallRate(0.0);

        when(musicServiceUnderTest.musicrepo.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> musicServiceUnderTest.update(dto, 0L)).isInstanceOf(MusicNotFoundException.class);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        when(musicServiceUnderTest.musicrepo.findById(0L)).thenReturn(Optional.of(new Music()));

        // Run the test
        final String result = musicServiceUnderTest.delete(0L);

        // Verify the results
        assertThat(result).isEqualTo("Music Deleted Successfully");
        verify(musicServiceUnderTest.musicrepo).deleteById(0L);
    }

    @Test
    void testDelete_MusicRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(musicServiceUnderTest.musicrepo.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> musicServiceUnderTest.delete(0L)).isInstanceOf(MusicNotFoundException.class);
    }
}
