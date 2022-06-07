package app;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import core.http.server.HandlerResources;
import entities.room.Room;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        Room room = new Room(1, "archive_room", "basic", "basic",false);
        System.out.println(room.getName());

        assertTrue( true );
    }
}
