package core.http.resources;

import models.characters.CharacterActionCardValue;

public class CharacterActionCardValueResource {
    private final int id;
    private final String name;
    private final String description;

    public CharacterActionCardValueResource(CharacterActionCardValue characterActionCardValue)  {
        this.id = characterActionCardValue.getId();
        this.name = characterActionCardValue.getName();
        this.description = characterActionCardValue.getDescription();
    }

    public int getId() {
        return id;
    }
}
