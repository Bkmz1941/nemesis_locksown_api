package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import core.http.resources.CharacterBasicActionsResource;
import models.characters.CharacterBasicAction;
import services.dao.CharacterBasicActionsDAO;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CharactersBasicActionsController extends Controller {
    @Override
    protected String makeResponse(HttpExchange exchange) {
        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = index();
        }
        return response;
    }

    private String index() {
        Set<CharacterBasicAction> characterBasicActions = CharacterBasicActionsDAO.getAll();
        Set<CharacterBasicActionsResource> characterBasicActionsResource = new TreeSet<>(Comparator.comparingInt(CharacterBasicActionsResource::getId));

        characterBasicActions.forEach(el -> characterBasicActionsResource.add(new CharacterBasicActionsResource(el)));

        return new Gson().toJson(characterBasicActionsResource);
    }
}
