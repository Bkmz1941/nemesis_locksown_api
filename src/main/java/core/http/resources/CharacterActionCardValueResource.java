package core.http.resources;

import entities.charcter.CharacterActionAvailable;
import entities.charcter.CharacterActionCard;
import entities.charcter.CharacterActionCardValue;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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
