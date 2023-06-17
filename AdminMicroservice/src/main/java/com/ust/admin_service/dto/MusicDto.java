package com.ust.admin_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDto {

    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Music name should not be null")
    private String musicName;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^[a-zA-Z0-9\\-.,]+$", message = "Artist name should not be null")
    private String artistName;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Genre should  contain alphabetic characters only")
    private String musicGenre;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Release date  should be in the format :'YYYY-MM-DD")
    private String songReleaseDate;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "song Language should  contain alphabetic characters only")
    private String songLanguage;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^\\d+$", message = "Duration should  only contain numeric digits (0-9)")
    private String duration;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Country name  should only contain alphabetic characters")
    private String country;
    @NotNull
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must not exceed 10")
    private double overallRate;
}

