package com.course.app.dto;

public class VoteByIdDTO {
	private Long artistId;
	private Long[] genresId;
	private String about;

	public VoteByIdDTO(Long artistId, Long[] genresId, String about) {
		this.artistId = artistId;
		this.genresId = genresId;
		this.about = about;
	}

	public VoteByIdDTO(){

	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public Long[] getGenresId() {
		return genresId;
	}

	public void setGenresId(Long[] genresId) {
		this.genresId = genresId;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
