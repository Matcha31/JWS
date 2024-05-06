package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.converter.EntityToResponseConverter;
import fr.epita.assistants.jws.domain.service.BombService;
import fr.epita.assistants.jws.domain.service.MoveService;
import fr.epita.assistants.jws.presentation.rest.request.BombMoveRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameInfoResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games/{gameId}/players/{playerId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BombMoveEndpoint {
    @Inject
    MoveService moveService;

    @POST
    @Path("/move/")
    public Response move(BombMoveRequest request, @PathParam("gameId") Long gameId, @PathParam("playerId") Long playerId){
        if (!moveService.gameExist(gameId) || !moveService.playerExist(playerId))
            return Response.ok().status(404).build();
        if (!moveService.gameRunning(gameId) || !moveService.playerAlive(playerId))
            return Response.ok().status(400).build();
        GameInfoResponse response = EntityToResponseConverter.convert(moveService.move(request.posx, request.posy, gameId, playerId));
        return Response.ok(response).status(200).build();
    }

    @Inject
    BombService bombService;
    @POST
    @Path("/bomb/")
    public Response bomb(BombMoveRequest request, @PathParam("gameId") Long gameId, @PathParam("playerId") Long playerId){
        if (!bombService.gameExist(gameId) || !bombService.playerExist(playerId))
            return Response.ok().status(404).build();
        if (!bombService.gameRunning(gameId) || !bombService.playerAlive(playerId))
            return Response.ok().status(400).build();
        GameInfoResponse response = EntityToResponseConverter.convert(bombService.bomb(request.posx, request.posy, gameId, playerId));
        return Response.ok(response).status(200).build();
    }
}
