package services.dao;

import core.database.DatabaseConnection;
import entities.charcter.CharacterActionCard;
import entities.charcter.CharacterActionCardValue;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharactersActionCardsDAO {
    public static SortedSet<CharacterActionCard> getAll(String characterSystemName) {
        SortedSet<CharacterActionCard> characterActionCards = new TreeSet<>(Comparator.comparingInt(CharacterActionCard::getId));
        try {
            String query = "SELECT ac.id, ac.system_name, s.system_name as character_system_name, ac.cost, ac.available, ac.interruption, acv.id as action_id, acv.character_action_card_id, acv.system_name as action_system_name FROM character_action_cards ac LEFT JOIN character_action_card_values acv ON ac.id = acv.character_action_card_id LEFT JOIN characters s ON ac.character_id = s.id WHERE s.system_name='" + characterSystemName + "'";
            ResultSet rows = DatabaseConnection.getInstance().execute(query);
            while (rows.next()) {
                int characterActionCardId = rows.getInt("character_action_card_id");
                int id = rows.getInt("id");
                String systemName = rows.getString("system_name");
                String available = rows.getString("available");
                Integer cost = rows.getInt("cost");
                Boolean interruption = rows.getBoolean("interruption");
                CharacterActionCard characterActionCard = new CharacterActionCard(id, systemName, characterSystemName, interruption, available, cost);
                characterActionCards.add(characterActionCard);

                for (CharacterActionCard r : characterActionCards) {
                    if (r.getId() == characterActionCardId) {
                        int valueActionId = r.getValues().size() + 1;
                        String roomActionSystemName = rows.getString("action_system_name");
                        r.addCharacterActionCardValue(new CharacterActionCardValue(valueActionId, roomActionSystemName));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return characterActionCards;
    }
}
