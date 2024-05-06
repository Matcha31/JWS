package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.converter.EntityToResponseConverter;
import fr.epita.assistants.jws.domain.service.GameInfoService;
import fr.epita.assistants.jws.domain.service.JoinGameService;
import fr.epita.assistants.jws.domain.service.StartGameService;
import fr.epita.assistants.jws.presentation.rest.request.GameRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameInfoResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/games/{gameId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InfoJoinStartGameEndpoint {
    @Inject
    GameInfoService infoService;

    @GET
    @Path("/")
    public Response getGameInfo(@PathParam("gameId") Long id){
        // Game not found
        if (!infoService.gameExist(id))
            return Response.ok().status(404).build();
        GameInfoResponse response = EntityToResponseConverter.convert(infoService.getGameInfo(id));
        return Response.ok(response).status(200).build();
    }

    @Inject
    JoinGameService joinService;

    @POST
    @Path("/")
    public Response joinGame(GameRequest request, @PathParam("gameId") Long id) throws IOException {
        // Game does not exist
        if (!joinService.gameExist(id))
            return Response.ok().status(404).build();
        // Bad request or game started/finished
        if (request == null || request.name == null || !joinService.gameCanBeStarted(id))
            return Response.ok().status(400).build();
        GameInfoResponse response = EntityToResponseConverter.convert(joinService.joinGame(request.name, id));
        return Response.ok(response).status(200).build();
    }

    @Inject
    StartGameService startService;

    @PATCH
    @Path("/start/")
    public Response startGame(@PathParam("gameId") Long id){
        if (!startService.gameExist(id) || !startService.gameCanBeStarted(id))
            return Response.ok().status(404).build();
        GameInfoResponse response = EntityToResponseConverter.convert(startService.startGame(id));
        return Response.ok(response).status(200).build();
    }
}