package app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();

                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (i % 2 == 1) {
                        System.out.print(String.valueOf(chars[i]).toUpperCase());
                    } else {
                        System.out.print(String.valueOf(chars[i]).toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e);
        }

        assertTrue( true );
    }
}
