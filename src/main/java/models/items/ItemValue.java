package models.items;

public class ItemValue {
    private final int id;
    private final String systemName;

    public ItemValue(int id, String systemName) {
        this.id = id;
        this.systemName = systemName;
    }

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }
}
