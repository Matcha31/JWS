package fr.epita.assistants.jws.domain.entity;

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
public class GameInfoEntity {
    public Instant startTime;
    public GameState state;
    public List<PlayerInfoEntity> players;
    public List<String> map;
    public Long id;
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlayerInfoEntity {
        public Long id;
        public String name;
        public int lives;
        public int posX;
        public int posY;
    }
}
