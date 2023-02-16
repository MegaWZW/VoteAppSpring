package com.course.app.dto;

import com.course.app.dao.db.orm.entity.Genre;

import java.util.Objects;

public class GenreDTO {
	private Long id;
	private Long version;
	private String name;

	public GenreDTO(Long id, Long version, String name) {
		this.id = id;
		this.version = version;
		this.name = name;
	}

	public GenreDTO(Long id, String name){
		this.id = id;
		this.name = name;
	}

	public GenreDTO(String name){
		this.name = name;
	}

	public GenreDTO(Genre genreEntity) {
		this.id = genreEntity.getId();
		this.version = genreEntity.getVersion();
		this.name = genreEntity.getName();
	}

	public GenreDTO(){

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GenreDTO genreDTO = (GenreDTO) o;
		return Objects.equals(id, genreDTO.id) && Objects.equals(name, genreDTO.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "GenreDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
