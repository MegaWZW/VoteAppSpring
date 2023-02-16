package com.course.app.services;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistDTO;
import com.course.app.services.api.IArtistService;

import java.util.List;

public class ArtistService implements IArtistService {

	private final IArtistsDAO dao;

	public ArtistService(IArtistsDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<ArtistDTO> getAll() {
		return dao.getAll();
	}

	@Override
	public ArtistDTO getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public void addPosition(ArtistDTO artist) {
		dao.addPosition(artist);
	}

	@Override
	public void deletePosition(Long id, Long version) {
		dao.deletePosition(id, version);
	}

	@Override
	public void updatePosition(ArtistDTO artist, Long id, Long version) {
		dao.updatePosition(artist, id, version);
	}

	@Override
	public boolean isExist(Long id) {
		return dao.isExist(id);
	}
}
