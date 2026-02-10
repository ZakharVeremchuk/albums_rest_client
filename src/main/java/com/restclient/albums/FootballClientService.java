package com.restclient.albums;

import com.restclient.albums.exception.ResponseServerException;
import org.apache.coyote.BadRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class FootballClientService {
    private RestClient restClient;

    public FootballClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Player> getPlayers() {
        return restClient.get().uri("/players").retrieve()
                .body(new ParameterizedTypeReference<List<Player>>() {
                });
    }

    public Optional<Player> getPlayer(String id) {
        return restClient.get().uri("/players/{id}", id)
                .exchange((request, response) -> {
                    if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Optional.empty();
                    }
                    return Optional.of(response.bodyTo(Player.class));
                });
    }

    public Player savePlayer(Player player) {
        Player result = restClient.post().uri("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .body(player)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    String message = this.getMessageFromResponse(response.getBody());
                    throw new ResponseServerException(response.getStatusCode(), response.getHeaders(), message);
                }))
                .toEntity(Player.class).getBody();
        return result;
    }

    public String getMessageFromResponse(InputStream body) throws IOException {
        return new String(body.readAllBytes(), StandardCharsets.UTF_8);
    }
}
