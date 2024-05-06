package fr.epita.assistants.jws.converter;

import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.domain.entity.GameInfoEntity;
import fr.epita.assistants.jws.domain.entity.GamesListEntity;

import java.util.ArrayList;
import java.util.List;

public class ModelToEntityConverter {
    public static GamesListEntity convertStart(GameModel model) {
        return new GamesListEntity(model.id, model.nbPlayers, model.state);
    }

    public static GameInfoEntity convert(GameModel game, List<PlayerModel> players) {
        return new GameInfoEntity(game.startTime, game.state, convert(players), new ArrayList<>(game.map), game.id);
    }

    public static List<GameInfoEntity.PlayerInfoEntity> convert(List<PlayerModel> players) {
        List<GameInfoEntity.PlayerInfoEntity> list = new ArrayList<>();
        for (PlayerModel player : players)
            list.add(convert(player));
        return list;
    }

    public static GameInfoEntity.PlayerInfoEntity convert(PlayerModel player) {
        return new GameInfoEntity.PlayerInfoEntity(player.id, player.name, player.lives, player.posx, player.posy);
    }

}
