package com.course.app.dto;

import com.course.app.core.Message;

import java.util.List;

public class ResultToOutputDTO {
	private List<ArtistWithPointsDTO> topArtists;
	private List<GenreWithPointsDTO> topGenres;
	private List<Message> messages;

	public ResultToOutputDTO(List<ArtistWithPointsDTO> topArtists,
	                         List<GenreWithPointsDTO> topGenres,
	                         List<Message> messages) {
		this.topArtists = topArtists;
		this.topGenres = topGenres;
		this.messages = messages;
	}

	public ResultToOutputDTO(){

	}

	public List<ArtistWithPointsDTO> getTopArtists() {
		return topArtists;
	}

	public void setTopArtists(List<ArtistWithPointsDTO> topArtists) {
		this.topArtists = topArtists;
	}

	public List<GenreWithPointsDTO> getTopGenres() {
		return topGenres;
	}

	public void setTopGenres(List<GenreWithPointsDTO> topGenres) {
		this.topGenres = topGenres;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
