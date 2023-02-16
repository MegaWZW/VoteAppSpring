package com.course.app.web.controllers;

import com.course.app.dto.GenreDTO;
import com.course.app.services.api.IGenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/genres")
public class GenreController {
    private final IGenreService genreService;

    public GenreController(IGenreService service) {
        this.genreService = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GenreDTO> getList() {
        return genreService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public GenreDTO getCard(@PathVariable(name = "id") Long id) {
        return genreService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody GenreDTO dto) {
        genreService.addPosition(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/version/{version}")
    public void update(@RequestBody GenreDTO dto,
                       @PathVariable(name = "id") Long id,
                       @PathVariable(name = "version") Long version) {
        genreService.updatePosition(dto, id, version);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/version/{version}")
    public void delete(@PathVariable(name = "id") Long id,
                       @PathVariable(name = "version") Long version) {
        genreService.deletePosition(id, version);
    }
}
