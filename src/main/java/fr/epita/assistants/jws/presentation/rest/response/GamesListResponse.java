package fr.epita.assistants.jws.presentation.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.epita.assistants.jws.utils.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
public class GamesListResponse {
    public List<GameResponse> list;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class GameResponse {
        public Long id;
        public int players;
        public GameState state;
    }
}