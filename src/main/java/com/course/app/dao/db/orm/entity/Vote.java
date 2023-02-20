package com.course.app.dao.db.orm.entity;

import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "app.vote")
public class Vote implements Serializable {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;

	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.MERGE})
	@JoinTable(name = "app.vote_artist",
			joinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
	private Artist artist;

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(value = {org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
	@JoinTable(name = "app.vote_genre",
			joinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private Set<Genre> genres = new HashSet<>();

	private LocalDateTime dtCreate;
	private String about;

	public Vote(Artist artist, Set<Genre> genres, LocalDateTime dtCreate, String about) {
		this.artist = artist;
		this.genres = genres;
		this.dtCreate = dtCreate;
		this.about = about;
	}

	public Vote (VoteDTO voteDTO) {
		this.artist = new Artist(voteDTO.getArtist());
		for(GenreDTO item : voteDTO.getGenres()){
			this.genres.add(new Genre(item));
		}
		this.dtCreate = LocalDateTime.now();
		this.about = voteDTO.getAbout();
	}

	public Vote() {

	}

	public Long getId() {
		return id;
	}


	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vote vote = (Vote) o;
		return Objects.equals(id, vote.id) && Objects.equals(artist, vote.artist) &&
				Objects.equals(genres, vote.genres) &&
				Objects.equals(dtCreate, vote.dtCreate) && Objects.equals(about, vote.about);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, artist, genres, dtCreate, about);
	}

	@Override
	public String toString() {
		return "Vote{" +
				"id=" + id +
				", artist=" + artist +
				", genres=" + genres +
				", dtCreate=" + dtCreate +
				", about='" + about + '\'' +
				'}';
	}
}
