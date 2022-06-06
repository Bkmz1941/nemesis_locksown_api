package helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

public class FileWithTranslate {
    public static String getKey(String lang, String filename, String entity, String key) {
        try {
            File file = FileHelper.streamToFile(FileWithTranslate.class.getResourceAsStream("/translate/" + lang + "/" + filename + ".json"));
            if (file != null) {
                String jsonString = new String(Files.readAllBytes(file.toPath()));
                TypeToken<HashMap<String, HashMap<String, String>>> type = new TypeToken<>() {
                };
                HashMap<String, HashMap<String, String>> string = new Gson().fromJson(jsonString, type.getType());
                return string.get(entity).get(key);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
