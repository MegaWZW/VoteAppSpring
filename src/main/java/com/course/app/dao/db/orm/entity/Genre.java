package com.course.app.dao.db.orm.entity;

import com.course.app.dto.GenreDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "app.genre")
public class Genre implements Serializable {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;

	@Version
	@Column(name = "version")
	private Long version;

	@Column(name = "name")
	private String name;

	public Genre(String name) {
		this.id = 0L;
		this.version = 0L;
		this.name = name;
	}

	public Genre(GenreDTO dto) {
		this.id = dto.getId();
		this.version = dto.getVersion();
		this.name = dto.getName();
	}

	public Genre() {

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
		Genre genre = (Genre) o;
		return Objects.equals(id, genre.id) && Objects.equals(name, genre.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Genre{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
