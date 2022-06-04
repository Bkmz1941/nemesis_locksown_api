package entities.room;

enum RoomType {
    BASIC,
    ADDITIONAL,
    SPECIAL
}

public class Room {
    private final String systemName;
    private final RoomType type;
    private final Boolean computer;

    public Room(String systemName, String type, Boolean computer) {
        this.systemName = systemName;
        this.type = detectRoomType(type);
        this.computer = computer;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public RoomType getType() {
        return this.type;
    }

    public Boolean getComputer() {
        return this.computer;
    }

    private RoomType detectRoomType(String type) {
        RoomType result = null;
        switch (type) {
            case "basic":
                result = RoomType.BASIC;
                break;
            case "additional":
                result = RoomType.ADDITIONAL;
                break;
            case "special":
                result = RoomType.SPECIAL;
                break;
        }
        return result;
    }
}
