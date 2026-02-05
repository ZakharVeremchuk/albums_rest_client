package com.restclient.albums;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class AlbumController {

    private FootballClientService footballClientService;

    public AlbumController(FootballClientService footballClientService) {
        this.footballClientService = footballClientService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return this.footballClientService.getPlayers();
    }
}
