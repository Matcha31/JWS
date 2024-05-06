package fr.epita.assistants.jws.domain.service;

import fr.epita.assistants.jws.converter.ModelToEntityConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.repository.GameRepository;
import fr.epita.assistants.jws.data.repository.PlayerRepository;
import fr.epita.assistants.jws.domain.entity.GameInfoEntity;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@AllArgsConstructor
@Transactional
public class GameInfoService {
    @Inject
    public GameRepository gameRepository;
    @Inject
    public PlayerRepository playerRepository;

    public boolean gameExist(Long id){
        if (gameRepository.findById(id) == null)
            return false;
        return true;
    }
    public GameInfoEntity getGameInfo(Long id){
        GameModel game = gameRepository.findById(id);
        return ModelToEntityConverter.convert(game, playerRepository.findByGame(game));
    }
}
