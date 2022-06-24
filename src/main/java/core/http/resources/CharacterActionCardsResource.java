package core.http.resources;

import models.characters.CharacterActionAvailable;
import models.characters.CharacterActionCard;
import models.characters.CharacterActionCardValue;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CharacterActionCardsResource {
    private final int id;
    private final String name;
    private final String systemName;
    private final CharacterActionAvailable available;
    private final int cost;
    private final boolean interruption;
    private final Object fullImageLink;
    private Set<CharacterActionCardValueResource> values = new TreeSet<>(Comparator.comparingInt(CharacterActionCardValueResource::getId));

    public CharacterActionCardsResource(CharacterActionCard characterActionCard)  {
        this.id = characterActionCard.getId();
        this.name = characterActionCard.getName();
        this.available = characterActionCard.getAvailable();
        this.cost = characterActionCard.getCost();
        this.interruption = characterActionCard.getInterruption();
        this.fullImageLink = characterActionCard.getFullImageLink();
        this.systemName = characterActionCard.getSystemName();

        for (CharacterActionCardValue characterActionCardValue: characterActionCard.getValues()) {
            this.values.add(new CharacterActionCardValueResource(characterActionCardValue));
        }
    }

    public int getId() {
        return id;
    }
}
