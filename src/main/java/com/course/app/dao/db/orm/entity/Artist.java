package com.course.app.dao.db.orm.entity;

import com.course.app.dto.ArtistDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "app.artist")
public class Artist implements Serializable {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;

	@Version
	@Column(name = "version")
	private Long version;

	@Column(name = "name")
	private String name;

	public Artist(String name) {
		this.id = 0L;
		this.version = 0L;
		this.name = name;
	}

	public Artist(ArtistDTO dto) {
		this.id = dto.getId();
		this.id = dto.getVersion();
		this.name = dto.getName();
	}

	public Artist(){

	}

	public Long getId() {
		return id;
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
		Artist artist = (Artist) o;
		return Objects.equals(id, artist.id) && Objects.equals(name, artist.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Artist{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
