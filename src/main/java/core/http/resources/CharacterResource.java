package core.http.resources;

import helpers.FileWithTranslate;
import entities.charcter.Character;
import entities.charcter.CharacterColor;

import java.io.IOException;

public class CharacterResource {
    private final int id;
    private final String fullImageLink;
    private final String miniImageLink;
    private final String name;
    private final String systemName;
    private final CharacterColor color;

    // HttpServer
    public CharacterResource(Character character) throws IOException {
        this.id = character.getId();
        this.name = character.getName();
        this.fullImageLink = character.getImageFullImageLink();
        this.miniImageLink = character.getImageMiniImageLink();
        this.color = character.getColor();
        this.systemName = character.getSystemName();
    }

    public int getId() {
        return id;
    }
}
