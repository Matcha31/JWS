package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.converter.EntityToResponseConverter;
import fr.epita.assistants.jws.domain.service.CreateGameService;
import fr.epita.assistants.jws.domain.service.GamesListService;
import fr.epita.assistants.jws.presentation.rest.request.GameRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameInfoResponse;
import fr.epita.assistants.jws.presentation.rest.response.GamesListResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateListGameEndpoint {
    @Inject
    CreateGameService createService;

    @POST
    @Path("/")
    public Response createGame(GameRequest request) throws IOException {
        // Bad request
        if (request == null || request.name == null)
            return Response.ok().status(400).build();
        GameInfoResponse response = EntityToResponseConverter.convert(createService.createGame(request.name));
        return Response.ok(response).status(200).build();
    }
    @Inject
    GamesListService listService;
    @GET
    @Path("/")
    public Response getGame(){
        GamesListResponse response = EntityToResponseConverter.convert(listService.getGames());
        return Response.ok(response.list).status(200).build();
    }
}
