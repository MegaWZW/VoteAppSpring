package com.course.app.web.controllers;

import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteByIdDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.api.IGenreService;
import com.course.app.services.api.IVoteService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(path = "/vote")
public class VoiceHandlerController {

	private final IVoteService voteService;
	private final IArtistService artistService;
	private final IGenreService genreService;

	public VoiceHandlerController(IVoteService voteService,
	                              IArtistService artistService,
	                              IGenreService genreService){
		this.voteService = voteService;
		this.artistService = artistService;
		this.genreService = genreService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void vote (@RequestBody VoteByIdDTO dto) {

		Long artistId = dto.getArtistId();
		Long[] genresId = dto.getGenresId();
		String text = dto.getAbout();

		ArtistDTO artistDTO = artistService.getOne(artistId);

		Set<GenreDTO> setGenresDTO = new HashSet<>();
		for(Long id : genresId) {
			GenreDTO genreDTO = genreService.getOne(id);
			setGenresDTO.add(genreDTO);
		}

		VoteDTO voteDTO = new VoteDTO(artistDTO, setGenresDTO, text);

		voteService.save(voteDTO);
	}
}
