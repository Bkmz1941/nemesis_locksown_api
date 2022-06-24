package models.characters;

import core.http.server.HttpServer;
import helpers.FileWithTranslate;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CharacterActionCard {
    private final int id;
    private final String systemName;
    private final String ownerSystemName;
    private final Integer cost;
    private final Boolean interruption;
    private final CharacterActionAvailable available;
    private final Set<CharacterActionCardValue> values = new TreeSet<>(Comparator.comparingInt(CharacterActionCardValue::getId));

    public CharacterActionCard(int id, String systemName, String ownerSystemName, Boolean interruption, String available, Integer cost) {
        this.id = id;
        this.systemName = systemName;
        this.ownerSystemName = ownerSystemName;
        this.interruption = interruption;
        this.available = detectActionAvailable(available);
        this.cost = cost;
    }

    private CharacterActionAvailable detectActionAvailable(String available) {
        return switch (available) {
            case "in_combat" -> CharacterActionAvailable.IN_COMBAT;
            case "out_of_combat" -> CharacterActionAvailable.OUT_OF_COMBAT;
            case "always" -> CharacterActionAvailable.ALWAYS;
            default -> throw new IllegalStateException("Unexpected value: " + available);
        };
    }

    public void addCharacterActionCardValue(CharacterActionCardValue characterActionCardValue) {
        values.add(characterActionCardValue);
    }

    public String getName() {
        return FileWithTranslate.getKey("ru", "characters_action_cards", this.systemName, "name");
    }

    public String getFullImageLink() {
        try {
            return HttpServer.port + ":/" + HttpServer.getInstance().getAddress() + "/resources/images/characters/action_cards/" + this.ownerSystemName + "_" + this.systemName + ".png";
        } catch (Exception ignore) {
            return "";
        }
    }

    public int getId() {
        return id;
    }

    public Set<CharacterActionCardValue> getValues() {
        return values;
    }

    public String getSystemName() {
        return systemName;
    }

    public CharacterActionAvailable getAvailable() {
        return available;
    }

    public Boolean getInterruption() {
        return interruption;
    }

    public Integer getCost() {
        return cost;
    }
}
