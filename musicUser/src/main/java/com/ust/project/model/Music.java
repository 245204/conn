package com.ust.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MUSICS_TABLE")
public class Music {
	
	@Column(name="music_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long musicId;
	@Column(name="music_name")
	private String musicName;
	@Column(name="artist_name")
	private String artistName;
	@Column(name="music_genre")
	private String musicGenre;
	@Column(name="song_release_date")
	private String songReleaseDate;
	@Column(name="song_language")
	private String songLanguage;
	@Column(name="duration")
	private String duration;
	@Column(name="country")
	private String country;
	@Column(name="overall_rate")
	private double overallRate;

}
