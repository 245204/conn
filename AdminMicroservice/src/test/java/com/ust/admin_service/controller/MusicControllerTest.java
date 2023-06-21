package com.ust.admin_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.service.MusicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicController.class)
class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MusicService mockMusicService;

    @Test
    void testAddAMusic() throws Exception {
        // Setup
        // Configure MusicService.add(...).
        final MusicDto dto = new MusicDto();
        dto.setMusicName("musicName");
        dto.setArtistName("artistName");
        dto.setMusicGenre("musicGenre");
        dto.setSongReleaseDate("songReleaseDate");
        dto.setSongLanguage("songLanguage");
        when(mockMusicService.add(dto)).thenReturn(new Music());

        // Convert the DTO object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDto = objectMapper.writeValueAsString(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/1.0/admin/addmusic")
                        .content(jsonDto) // Pass the JSON payload
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo(""); // Set expected response accordingly
    }


    @Test
    void testViewAllMusics() throws Exception {
        // Setup
        List<Music> musicList = new ArrayList<>();
        musicList.add(new Music());

        when(mockMusicService.view()).thenReturn(musicList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/allmusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // Convert the musicList to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResponse = objectMapper.writeValueAsString(musicList);

        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }


    @Test
    void testViewAllMusics_MusicServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMusicService.view()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/allmusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetById() throws Exception {
        // Setup
        long musicId = 0L;
        Music music = new Music();
        when(mockMusicService.fetchById(musicId)).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/get/{musicId}", musicId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // Convert the music object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResponse = objectMapper.writeValueAsString(music);

        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetById_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        long musicId = 0L;
        when(mockMusicService.fetchById(musicId)).thenThrow(MusicNotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/get/{musicId}", musicId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }



    @Test
    void testUpdateMusic() throws Exception {
        // Setup
        long musicId = 0L;

        // Create a mock MusicDto object with the updated values
        MusicDto updatedDto = new MusicDto();
        updatedDto.setMusicName("updatedMusicName");
        updatedDto.setArtistName("updatedArtistName");
        updatedDto.setMusicGenre("updatedMusicGenre");
        updatedDto.setSongReleaseDate("updatedSongReleaseDate");
        updatedDto.setSongLanguage("updatedSongLanguage");

        // Configure MusicService.update(...)
        when(mockMusicService.update(eq(updatedDto), eq(musicId))).thenReturn(new Music());

        // Convert the updatedDto object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDto = objectMapper.writeValueAsString(updatedDto);

        // Run the test
        MvcResult result = mockMvc.perform(put("/1.0/admin/update/{musicId}", musicId)
                        .content(jsonDto) // Pass the JSON payload
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andReturn();

        // Verify the results
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getContentAsString()).isEqualTo(""); // Set expected response accordingly
    }



    @Test
    void testUpdateMusic_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        // Configure MusicService.update(...)
        final MusicDto dto = new MusicDto();
        dto.setMusicName("musicName");
        dto.setArtistName("artistName");
        dto.setMusicGenre("musicGenre");
        dto.setSongReleaseDate("songReleaseDate");
        dto.setSongLanguage("songLanguage");
        when(mockMusicService.update(dto, 0L)).thenThrow(new MusicNotFoundException());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/1.0/admin/update/{musicId}", 0)
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteMusic() throws Exception {
        // Setup
        when(mockMusicService.delete(0L)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/1.0/admin/delete/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("result");
    }


    @Test
    void testDeleteMusic_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        when(mockMusicService.delete(0L)).thenThrow(MusicNotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/1.0/admin/delete/{musicId}", 0L)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    public class MusicNotFoundException extends Exception {
        // Custom implementation for MusicNotFoundException
        // ...
    }

}
