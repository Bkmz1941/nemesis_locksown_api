package models.characters;

import core.http.server.HttpServer;
import helpers.FileWithTranslate;

import java.io.IOException;

public class Character {
    private final int id;
    private final String systemName;
    private final CharacterColor color;

    public Character(int id, String systemName, String color) {
        this.id = id;
        this.systemName = systemName;
        this.color = detectCharacterColor(color);
    }

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public CharacterColor getColor() {
        return color;
    }

    public String getName() {
        return FileWithTranslate.getKey("ru", "characters", this.systemName, "name");
    }

    public String getImageFullImageLink() throws IOException {
        return HttpServer.port + ":/" + HttpServer.getInstance().getAddress() + "/resources/images/characters/" + this.systemName + ".png";
    }
    public String getImageMiniImageLink() throws IOException {
        return HttpServer.port + ":/" + HttpServer.getInstance().getAddress() + "/resources/images/characters/" + this.systemName + "_mini.png";
    }

    private CharacterColor detectCharacterColor(String type) {
        CharacterColor result = null;
        switch (type) {
            case "blue":
                result = CharacterColor.BLUE;
                break;
            case "green":
                result = CharacterColor.GREEN;
                break;
            case "orange":
                result = CharacterColor.ORANGE;
                break;
            case "red":
                result = CharacterColor.RED;
                break;
            case "violet":
                result = CharacterColor.VIOLET;
                break;
            case "white":
                result = CharacterColor.WHITE;
                break;
        }
        return result;
    }
}
