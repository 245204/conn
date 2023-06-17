package com.ust.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDto {
	private String musicId;
	private String musicName;
	private String artistName;
	private String musicGenre;
	private String songReleaseDate;
	private String songLanguage;
	private String duration;
	private String country;
	private double overallRate;
}
