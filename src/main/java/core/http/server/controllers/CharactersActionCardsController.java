package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import core.http.resources.CharacterActionCardsResource;
import core.http.resources.CharacterResource;
import entities.charcter.Character;
import entities.charcter.CharacterActionCard;
import services.dao.CharactersActionCardsDAO;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharactersActionCardsController extends Controller {
    @Override
    protected String makeResponse(HttpExchange exchange) {
        String response = "Nothing not found";

        if ("GET".equals(exchange.getRequestMethod())) {
            if (exchange.getRequestURI().getQuery() != null) {
                Map<String, String> params = super.queryToMap(exchange.getRequestURI().getQuery());
                if (params.containsKey("character")) {
                    String characterSystemName = super.queryToMap(exchange.getRequestURI().getQuery()).get("character");
                    response = index(characterSystemName);
                }
            }
        }
        return response;
    }

    private String index(String characterSystemName) {
        try {
            SortedSet<CharacterActionCard> characterActionCards = CharactersActionCardsDAO.getAll(characterSystemName);
            SortedSet<CharacterActionCardsResource> characterActionCardsResource = new TreeSet<>(Comparator.comparingInt(CharacterActionCardsResource::getId));

            for (CharacterActionCard characterActionCard: characterActionCards) {
                characterActionCardsResource.add(new CharacterActionCardsResource(characterActionCard));
            }

            return new Gson().toJson(characterActionCardsResource);
        } catch (Exception ignore) {}

        return null;
    }
}
