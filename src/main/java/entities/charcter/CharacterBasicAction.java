package entities.charcter;

import helpers.FileWithTranslate;

public class CharacterBasicAction {
    private final int id;
    private final String systemName;
    private final CharacterBasicActionAvailable available;
    private final Integer cost;

    public CharacterBasicAction(int id, String systemName, String available, Integer cost) {
        this.id = id;
        this.systemName = systemName;
        this.available = detectActionAvailable(available);
        this.cost = cost;
    }

    private CharacterBasicActionAvailable detectActionAvailable(String available) {
        return switch (available) {
            case "in_combat" -> CharacterBasicActionAvailable.IN_COMBAT;
            case "out_of_combat" -> CharacterBasicActionAvailable.OUT_OF_COMBAT;
            case "always" -> CharacterBasicActionAvailable.ALWAYS;
            default -> throw new IllegalStateException("Unexpected value: " + available);
        };
    }

    public Integer getCost() {
        return cost;
    }

    public String getName() {
        return FileWithTranslate.getKey("ru", "character_basic_actions", this.systemName, "name");
    }

    public CharacterBasicActionAvailable getAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }
}
