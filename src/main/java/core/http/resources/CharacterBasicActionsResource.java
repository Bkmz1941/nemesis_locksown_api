package core.http.resources;

import models.characters.CharacterBasicAction;
import models.characters.CharacterActionAvailable;

public class CharacterBasicActionsResource {
    private final int id;
    private final String name;
    private final CharacterActionAvailable available;
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
