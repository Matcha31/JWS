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
import java.io.IOException;

@ApplicationScoped
@AllArgsConstructor
@Transactional
public class JoinGameService {
    @Inject
    public GameRepository gameRepository;
    @Inject
    public PlayerRepository playerRepository;
    public boolean gameExist(Long id){
        if (gameRepository.findById(id) == null)
            return false;
        return true;
    }
    public boolean gameCanBeStarted(Long id){
        if (gameRepository.findById(id).state != GameState.STARTING || gameRepository.findById(id).nbPlayers == 4)
            return false;
        return true;
    }

    public GameInfoEntity joinGame(String name, Long id) throws IOException {
        GameModel game = gameRepository.findById(id);
        gameRepository.update("nbPlayers = ?2 where id = ?1", game.id, game.nbPlayers + 1);
        int posx, posy;
        switch (game.nbPlayers){
            case 1:
                posx = 15;
                posy = 1;
                break;
            case 2:
                posx = 15;
                posy = 13;
                break;
            case 3:
                posx = 1;
                posy = 13;
                break;
            default:
                throw new IOException();
        }
        PlayerModel player = new PlayerModel(null, 3, name, posx, posy, null, null, game);
        playerRepository.persistAndFlush(player);
        return ModelToEntityConverter.convert(game, playerRepository.findByGame(game));
    }
}
