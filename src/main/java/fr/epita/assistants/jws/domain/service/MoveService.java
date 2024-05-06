package fr.epita.assistants.jws.domain.service;

import com.google.inject.Inject;
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

@ApplicationScoped
@AllArgsConstructor
@Transactional
public class MoveService {
    @Inject
    public GameRepository gameRepository;
    @Inject
    public PlayerRepository playerRepository;
    public boolean gameExist(Long id) {
        if (gameRepository.findById(id) == null)
            return false;
        return true;
    }
    public boolean playerExist(Long id) {
        if (playerRepository.findById(id) == null)
            return false;
        return true;
    }

    public boolean playerAlive(Long id) {
        if (playerRepository.findById(id).lives == 0)
            return false;
        return true;
    }

    public boolean gameRunning(Long id) {
        if (gameRepository.findById(id).state != GameState.RUNNING)
            return false;
        return true;
    }

    public GameInfoEntity move(int posx, int posy, Long gameId, Long playerId){
        GameModel game = gameRepository.findById(gameId);
        PlayerModel player = playerRepository.findById(playerId);
        playerRepository.update("posx = ?2 where id = ?1", playerId, posx);
        player.posx = posx;
        playerRepository.update("posy = ?2 where id = ?1", playerId, posy);
        player.posy = posy;
        return ModelToEntityConverter.convert(game, playerRepository.findByGame(game));
    }
}
