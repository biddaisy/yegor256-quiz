import java.io.*;
import java.nio.charset.Charset;

/**
 * This class is thread safe.
 */
public class Parser {

    public String getContent(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int bite;
            while ((bite = fileInputStream.read()) != -1) {
                output.append((char) bite);
            }
            return output.toString();
        }
    }

    public String getContent(File file, Charset charset) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return new String(fileInputStream.readAllBytes(), charset);
        }
    }

    public String getContentWithoutUnicode(File file) throws IOException {
        try (FileInputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int bite;
            while ((bite = i.read()) != -1) {
                if (bite < 0x80) {
                    output.append((char) bite);
                }
            }
            return output.toString().trim();
        }
    }

    public void saveContent(String content, File file) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content.getBytes());
        }
    }

    public void saveContent(String content, File file, Charset charset) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset)) {
            outputStreamWriter.write(content);
        }
    }

}
