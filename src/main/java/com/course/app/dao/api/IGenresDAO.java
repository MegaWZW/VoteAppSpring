package com.course.app.dao.api;

import com.course.app.dto.GenreDTO;

import java.util.List;

public interface IGenresDAO {
	List<GenreDTO> getAll();
	GenreDTO getOne(Long id);
	void addPosition(GenreDTO genre);
	void deletePosition(Long id, Long version);
	void updatePosition(GenreDTO genre, Long id, Long version);
	boolean isExist(Long id);
}
