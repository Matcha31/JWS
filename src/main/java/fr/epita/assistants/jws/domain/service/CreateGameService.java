package fr.epita.assistants.jws.domain.service;

import com.google.inject.Inject;
import fr.epita.assistants.jws.converter.MapConverter;
import fr.epita.assistants.jws.converter.ModelToEntityConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.data.repository.GameRepository;
import fr.epita.assistants.jws.data.repository.PlayerRepository;
import fr.epita.assistants.jws.domain.entity.GameInfoEntity;
import fr.epita.assistants.jws.utils.GameState;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Transactional
public class CreateGameService {
    @Inject
    public GameRepository gameRepository;
    @Inject
    public PlayerRepository playerRepository;

    public GameInfoEntity createGame(String name) throws IOException {
        // Create the first player
        String pathMap = System.getenv("JWS_MAP_PATH");
        if (pathMap == null)
            pathMap = "src/test/resources/map1.rle";
        List<String> map = MapConverter.RleToStringList(pathMap);
        GameModel newGame = new GameModel(Instant.now(), GameState.STARTING, 1, map, null);
        gameRepository.persistAndFlush(newGame);
        PlayerModel player = new PlayerModel(null, 3, name, 1, 1, null, null, newGame);
        playerRepository.persistAndFlush(player);
        return ModelToEntityConverter.convert(newGame, playerRepository.findByGame(newGame));
    }
}
