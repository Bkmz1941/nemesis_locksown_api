package core.http.resources;

import entities.charcter.Character;
import entities.charcter.CharacterBasicAction;
import entities.charcter.CharacterBasicActionAvailable;
import entities.charcter.CharacterColor;

import java.io.IOException;

public class CharacterBasicActionsResource {
    private final int id;
    private final String name;
    private final CharacterBasicActionAvailable available;
    private final int cost;

    public CharacterBasicActionsResource(CharacterBasicAction characterBasicAction)  {
        this.id = characterBasicAction.getId();
        this.name = characterBasicAction.getName();
        this.available = characterBasicAction.getAvailable();
        this.cost = characterBasicAction.getCost();
    }

    public int getId() {
        return id;
    }
}
