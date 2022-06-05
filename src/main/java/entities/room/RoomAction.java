package entities.room;

public class RoomAction {
    private final int id;
    private final String systemName;
    private final int cost;

    public RoomAction(int id, String systemName, int cost) {
        this.id = id;
        this.systemName = systemName;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public int getCost() {
        return cost;
    }
}
