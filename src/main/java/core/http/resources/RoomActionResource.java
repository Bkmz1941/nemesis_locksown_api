package core.http.resources;

import models.room.RoomAction;

public class RoomActionResource {
    private final int id;
    private final String name;
    private final String description;
    private final String conditions;
    private final String hints;
    private final int cost;

    public RoomActionResource(RoomAction roomAction) {
        this.id = roomAction.getId();
        this.name = roomAction.getName();
        this.description = roomAction.getDescription();
        this.conditions = roomAction.getConditions();
        this.hints = roomAction.getHints();
        this.cost = roomAction.getCost();
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getConditions() {
        return conditions;
    }

    public String getHints() {
        return hints;
    }
}
