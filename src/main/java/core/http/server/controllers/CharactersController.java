package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import core.http.resources.CharacterResource;
import models.characters.Character;
import services.dao.CharacterDAO;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharactersController extends Controller {
    @Override
    protected String makeResponse(HttpExchange exchange) {
        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = index();
        }
        return response;
    }

    private String index() {
        try {
            SortedSet<Character> characters = CharacterDAO.getAll();
            SortedSet<CharacterResource> characterResource = new TreeSet<>(Comparator.comparingInt(CharacterResource::getId));

            for (Character character: characters) {
                characterResource.add(new CharacterResource(character));
            }

            return new Gson().toJson(characterResource);
        } catch (Exception ignore) {}

        return null;
    }
}
