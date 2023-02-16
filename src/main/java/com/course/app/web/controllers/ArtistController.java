package com.course.app.web.controllers;

import com.course.app.dto.ArtistDTO;
import com.course.app.services.api.IArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artists")
public class ArtistController{

    private final IArtistService artistService;

    public ArtistController(IArtistService service) {
        this.artistService = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ArtistDTO> getList() {
        return artistService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ArtistDTO getCard(@PathVariable(name = "id") Long id) {
        return artistService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody ArtistDTO dto) {
        artistService.addPosition(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/version/{version}")
    public void update(@RequestBody ArtistDTO dto,
                       @PathVariable(name = "id") Long id,
                       @PathVariable(name = "version") Long version) {
        artistService.updatePosition(dto, id, version);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/version/{version}")
    public void delete(@PathVariable(name = "id") Long id,
                          @PathVariable(name = "version") Long version) {
        artistService.deletePosition(id, version);
    }
}
