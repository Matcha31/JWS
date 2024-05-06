package fr.epita.assistants.jws.converter;

import fr.epita.assistants.jws.domain.entity.GameInfoEntity;
import fr.epita.assistants.jws.domain.entity.GamesListEntity;
import fr.epita.assistants.jws.presentation.rest.response.GameInfoResponse;
import fr.epita.assistants.jws.presentation.rest.response.GamesListResponse;

import java.util.ArrayList;
import java.util.List;

public class EntityToResponseConverter {
    public static GamesListResponse convert(List<GamesListEntity> entities) {
        List<GamesListResponse.GameResponse> list = new ArrayList<>();
        for (GamesListEntity entity : entities)
            list.add(convert(entity));
        return new GamesListResponse(list);
    }

    public static GamesListResponse.GameResponse convert(GamesListEntity entity) {
        return new GamesListResponse.GameResponse(entity.id, entity.players, entity.state);
    }
    public static GameInfoResponse convert(GameInfoEntity entity) {
        return new GameInfoResponse(entity.startTime, entity.state, convertI(entity.players), new ArrayList<>(entity.map), entity.id);
    }

    public static List<GameInfoResponse.PlayerInfoResponse> convertI(List<GameInfoEntity.PlayerInfoEntity> entities) {
        List<GameInfoResponse.PlayerInfoResponse> list = new ArrayList<>();
        for (GameInfoEntity.PlayerInfoEntity entity : entities)
            list.add(convert(entity));
        return list;
    }

    public static GameInfoResponse.PlayerInfoResponse convert(GameInfoEntity.PlayerInfoEntity entity) {
        return new GameInfoResponse.PlayerInfoResponse(entity.id, entity.name, entity.lives, entity.posX, entity.posY);
    }
}
