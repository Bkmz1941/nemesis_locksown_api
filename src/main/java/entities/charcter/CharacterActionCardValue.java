package entities.charcter;

import core.http.server.HttpServer;
import helpers.FileWithTranslate;

import java.io.IOException;

public class CharacterActionCardValue {
    private final int id;
    private final String systemName;

    public CharacterActionCardValue(int id, String systemName) {
        this.id = id;
        this.systemName = systemName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return FileWithTranslate.getKey("ru", "characters_action_card_values", this.systemName, "name");
    }

    public String getDescription() {
        return FileWithTranslate.getKey("ru", "characters_action_card_values", this.systemName, "description");
    }
}
