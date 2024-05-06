package fr.epita.assistants.jws.domain.service;

import com.google.inject.Inject;
import fr.epita.assistants.jws.converter.ModelToEntityConverter;
import fr.epita.assistants.jws.data.model.GameModel;
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
public class StartGameService {
    @Inject
    public GameRepository gameRepository;
    @Inject
    public PlayerRepository playerRepository;
    public boolean gameExist(Long id) {
        if (gameRepository.findById(id) == null)
            return false;
        return true;
    }

    public boolean gameCanBeStarted(Long id) {
        if (gameRepository.findById(id).state != GameState.STARTING || gameRepository.findById(id).nbPlayers == 4)
            return false;
        return true;
    }

    public GameInfoEntity startGame(Long id) {
        GameModel game = gameRepository.findById(id);
        if (game.nbPlayers == 1){
            gameRepository.update("state = ?2 where id = ?1", id, GameState.FINISHED);
            game.state = GameState.FINISHED;
        }
        else
        {
            gameRepository.update("state = ?2 where id = ?1", id, GameState.RUNNING);
            game.state = GameState.RUNNING;
        }
        return ModelToEntityConverter.convert(game, playerRepository.findByGame(game));
    }

}
