package credits.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ResourceFileReader {

    private ClassLoader classLoader = getClass().getClassLoader();

    public List<String> receiveFileStrings(String path) {

        List<String> fileResult = new ArrayList<>();

        try {
            fileResult = Files.readAllLines(new File(classLoader.getResource(path).getFile()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileResult;
    }

    public List<String> parseFileStrings(List<String> fileResult, String separator) {

        List<String> strings = new ArrayList<>();
        boolean previousLastCharacterIsSeparator = true;

        for (int s = 0; s <= fileResult.size() - 1; s++) {

            String currentLine = fileResult.get(s);

            if (s == 0) {
                previousLastCharacterIsSeparator = lastCharacterIsSeparator(separator, currentLine);
                if (!previousLastCharacterIsSeparator) {
                    strings.add("");
                }
            }

            String[] splits = currentLine.split(separator);

            int splitLength = splits.length - 1;
            for (int i = 0; i <= splitLength; i++) {

                if (!previousLastCharacterIsSeparator && splitLength == 0) {
                    strings.set(strings.size() - 1, strings.get(strings.size() - 1) + splits[i]);
                } else {
                    strings.add(splits[i]);
                }
            }
            previousLastCharacterIsSeparator = lastCharacterIsSeparator(separator, currentLine);
        }

        return strings;
    }

    private boolean lastCharacterIsSeparator(String separator, String currentLine) {
        return currentLine.substring(currentLine.length() - 1).equals(separator);
    }


}
