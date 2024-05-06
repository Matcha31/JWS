package fr.epita.assistants.jws.presentation.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.epita.assistants.jws.utils.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
public class GameInfoResponse {
    public Instant startTime;
    public GameState state;
    public List<PlayerInfoResponse> players;
    public List<String> map;
    public Long id;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PlayerInfoResponse {
        public Long id;
        public String name;
        public int lives;
        public int posX;
        public int posY;
    }
}
