package fr.epita.assistants.jws.domain.entity;

import fr.epita.assistants.jws.utils.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GamesListEntity {
    public Long id;
    public int players;
    public GameState state;
}
