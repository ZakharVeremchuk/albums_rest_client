package com.restclient.albums;

import org.springframework.web.bind.annotation.*;

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
        return footballClientService.getPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") String id) {
        return footballClientService.getPlayer(id).orElse(null);
    }

    @PostMapping
    public Player savePlayer(@RequestBody Player player) {
        return footballClientService.savePlayer(player);
    }

}
