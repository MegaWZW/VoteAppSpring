package com.course.app.dto;

import com.course.app.dao.db.orm.entity.Artist;

public class ArtistDTO {
	private Long id;
	private Long version;
	private String name;

	public ArtistDTO(Long id, Long version, String name) {
		this.id = id;
		this.version = version;
		this.name = name;
	}

	public ArtistDTO(Long id, String name){
		this.id = id;
		this.name= name;
	}

	public ArtistDTO(String name) {
		this.name = name;
	}

	public ArtistDTO(Artist artistEntity) {
		this.id = artistEntity.getId();
		this.version = artistEntity.getVersion();
		this.name = artistEntity.getName();
	}

	public ArtistDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ArtistDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
