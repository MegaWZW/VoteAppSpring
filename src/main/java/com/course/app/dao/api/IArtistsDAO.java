package com.course.app.dao.api;

import com.course.app.dto.ArtistDTO;

import java.util.List;


public interface IArtistsDAO {
	List<ArtistDTO> getAll();
	ArtistDTO getOne(Long id);
	void addPosition(ArtistDTO artist);
	void deletePosition(Long id, Long version);
	void updatePosition(ArtistDTO artist, Long id, Long version);
	boolean isExist(Long id);
}
