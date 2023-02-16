package com.course.app.web.controllers;

import com.course.app.core.Message;
import com.course.app.core.Result;
import com.course.app.dto.ArtistWithPointsDTO;
import com.course.app.dto.GenreWithPointsDTO;
import com.course.app.dto.ResultToOutputDTO;
import com.course.app.services.api.IStatisticService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "/result")
public class ResultPrinterController {

	private final IStatisticService stat;

	public ResultPrinterController(IStatisticService service){
		this.stat = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResultToOutputDTO result(){

		//подсчёт результатов голосования и создание объекта Result
		Result res = stat.calculate();
		Map<String, Integer> artistsMap = res.getArtistsMap();
		Map<String, Integer> genresMap = res.getGenresMap();
		List<Message> messagesList = res.getMessages();

		List<ArtistWithPointsDTO> artistWithPointsDTOList = new ArrayList<>();
		List<GenreWithPointsDTO> genreWithPointsDTOList = new ArrayList<>();

		Set<Map.Entry<String, Integer>> artistsEntries = artistsMap.entrySet();
		Set<Map.Entry<String, Integer>> genresEntries = genresMap.entrySet();

		for(Map.Entry<String, Integer> entry : artistsEntries) {
			ArtistWithPointsDTO dto = new ArtistWithPointsDTO(entry.getKey(), entry.getValue());
			artistWithPointsDTOList.add(dto);
		}

		for(Map.Entry<String, Integer> entry : genresEntries) {
			GenreWithPointsDTO dto = new GenreWithPointsDTO(entry.getKey(), entry.getValue());
			genreWithPointsDTOList.add(dto);
		}

		return new ResultToOutputDTO(artistWithPointsDTOList, genreWithPointsDTOList, messagesList);
	}
}
