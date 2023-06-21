package com.ust.project.controller;

import com.ust.project.dto.RatingDto;
import com.ust.project.model.Music;
import com.ust.project.service.UserService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testGetAllMusics() throws Exception {
        // Setup
        // Configure UserService.fetchAllMusics(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setSongReleaseDate("songReleaseDate");
        final List<Music> music = List.of(music1);
        when(mockUserService.fetchAllMusics()).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/1.0/users/allmusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo("[{\"musicId\":0,\"musicName\":\"musicName\",\"artistName\":\"artistName\",\"musicGenre\":\"musicGenre\",\"songReleaseDate\":\"songReleaseDate\"}]");
    }


    @Test
    void testGetAllMusics_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchAllMusics()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/1.0/users/allmusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetMusicByName() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByName(...).
        final Music music = new Music();
        music.setMusicId(0L);
        music.setMusicName("musicName");
        music.setArtistName("artistName");
        music.setMusicGenre("musicGenre");
        music.setSongReleaseDate("songReleaseDate");
        when(mockUserService.fetchMusicByName("musicName")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/name/{musicName}", "musicName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("{\"musicId\":0,\"musicName\":\"musicName\",\"artistName\":\"artistName\",\"musicGenre\":\"musicGenre\",\"songReleaseDate\":\"songReleaseDate\"}");
    }


    @Test
    void testGetMusicByDate() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByDate(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setSongReleaseDate("songReleaseDate");
        final List<Music> music = List.of(music1);
        when(mockUserService.fetchMusicByDate("songReleaseDate")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/date/{songReleaseDate}", "songReleaseDate")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo("[{\"musicId\":0,\"musicName}]");
    }

        @Test
    void testGetMusicByDate_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchMusicByDate("songReleaseDate")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/date/{songReleaseDate}", "songReleaseDate")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetMusicByGenre() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByGenre(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setSongReleaseDate("songReleaseDate");
        final List<Music> music = List.of(music1);
        when(mockUserService.fetchMusicByGenre("musicGenre")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/genre/{musicGenre}", "musicGenre")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetMusicByGenre_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchMusicByGenre("musicGenre")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/genre/{musicGenre}", "musicGenre")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetMusicByArtistName() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByArtistName(...).
        final Music music1 = new Music();
        music1.setMusicId(0L);
        music1.setMusicName("musicName");
        music1.setArtistName("artistName");
        music1.setMusicGenre("musicGenre");
        music1.setSongReleaseDate("songReleaseDate");
        final List<Music> music = List.of(music1);
        when(mockUserService.fetchMusicByArtistName("artistName")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/artist/{artistName}", "artistName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("[{\"musicId\":0,\"musicName\":\"musicName\",\"artistName\":\"artistName\",\"musicGenre\":\"musicGenre\",\"songReleaseDate\":\"songReleaseDate\"}]");
    }


    @Test
    void testGetMusicByArtistName_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchMusicByArtistName("artistName")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/artist/{artistName}", "artistName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testAddRatingMusic() throws Exception {
        // Setup
        when(mockUserService.addMusicRating(any(RatingDto.class), anyLong(), anyLong()))
                .thenReturn(true);

        // Run the test
        final MvcResult result = mockMvc.perform(
                        post("/api/1.0/users/add/rating/music/{musicid}/{userid}", 0, 0)
                                .content("{\"rating\": 0.0}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Verify the results
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("true");
    }


    @Test
    void testAddRatingMusic_UserServiceReturnsFalse() throws Exception {
        // Setup
        when(mockUserService.addMusicRating(any(RatingDto.class), anyLong(), anyLong()))
                .thenReturn(false);

        // Run the test
        final MvcResult result = mockMvc.perform(
                        post("/api/1.0/users/add/rating/music/{musicid}/{userid}", 0, 0)
                                .content("{\"rating\": 0.0}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Verify the results
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

}
