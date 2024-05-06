package fr.epita.assistants.jws.domain.service;

import com.google.inject.Inject;
import fr.epita.assistants.jws.converter.ModelToEntityConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.repository.GameRepository;
import fr.epita.assistants.jws.domain.entity.GamesListEntity;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Transactional
public class GamesListService {
    @Inject
    public GameRepository gameRepository;
    public List<GamesListEntity> getGames() {
        List<GameModel> games = gameRepository.listAll();
        return games.stream().map(ModelToEntityConverter::convertStart).toList();
    }
}
