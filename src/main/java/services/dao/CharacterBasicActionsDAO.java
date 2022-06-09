package services.dao;

import core.database.DatabaseConnection;
import entities.charcter.Character;
import entities.charcter.CharacterBasicAction;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterBasicActionsDAO {
    public static SortedSet<CharacterBasicAction> getAll() {
        SortedSet<CharacterBasicAction> characterBasicActions = new TreeSet<>(Comparator.comparingInt(CharacterBasicAction::getId));
        try {
            ResultSet rows = DatabaseConnection.getInstance().execute("SELECT * FROM character_base_actions");
            while (rows.next()) {
                int id = rows.getInt("id");
                String systemName = rows.getString("system_name");
                String available = rows.getString("available");
                Integer cost = rows.getInt("cost");
                CharacterBasicAction characterBasicAction = new CharacterBasicAction(id, systemName, available, cost);
                characterBasicActions.add(characterBasicAction);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return characterBasicActions;
    }
}
