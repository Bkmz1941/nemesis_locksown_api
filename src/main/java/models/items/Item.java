package models.items;

import java.util.Set;

public class Item {
    private final int id;
    private final String systemName;
    private final ItemType type;
    private final int weight;
    private final boolean onceUse;
    private final int uses;
    private final ItemCraftingType itemCraftingType;
    private final boolean isCrafting;
    private final int cost;
    private final String mark;
    private Set<ItemValue> values;

    public Item(
            int id,
            String systemName,
            ItemType type,
            int weight,
            boolean onceUse,
            int uses,
            ItemCraftingType itemCraftingType,
            boolean isCrafting,
            int cost,
            String mark
    ) {
        this.id = id;
        this.systemName = systemName;
        this.type = type;
        this.weight = weight;
        this.onceUse = onceUse;
        this.uses = uses;
        this.itemCraftingType = itemCraftingType;
        this.isCrafting = isCrafting;
        this.cost = cost;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public ItemType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isOnceUse() {
        return onceUse;
    }

    public int getUses() {
        return uses;
    }

    public ItemCraftingType getItemCraftingType() {
        return itemCraftingType;
    }

    public boolean isCrafting() {
        return isCrafting;
    }

    public int getCost() {
        return cost;
    }

    public String getMark() {
        return mark;
    }

    public Set<ItemValue> getValues() {
        return values;
    }

    public void setValues(Set<ItemValue> values) {
        this.values = values;
    }
}
