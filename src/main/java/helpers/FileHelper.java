package helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper implements AutoCloseable {
    public static File streamToFile(InputStream in) {
        if (in == null) {
            return null;
        }

        try {
            File f = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            f.deleteOnExit();

            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            return f;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
