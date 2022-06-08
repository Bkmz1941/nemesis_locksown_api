package core.http.resources;

import entities.charcter.Character;
import entities.charcter.CharacterColor;

import java.io.IOException;

public class CharacterResource {
    private final int id;
    private final String systemName;
    private final String fullImageLink;
    private final CharacterColor color;

    // HttpServer
    public CharacterResource(Character character) throws IOException {
        this.id = character.getId();
        this.systemName = character.getSystemName();
        this.fullImageLink = character.getImageFullImageLink();
        this.color = character.getColor();
    }

    public int getId() {
        return id;
    }
}
