import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class  IOHelper {
    private static JFileChooser chooser = new JFileChooser();

    public static String openFile(ArrayList<String> list) throws IOException {
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String rec = "";
                System.out.println("File Path: " + file);

                while (reader.ready()) {
                    rec = reader.readLine();
                    list.add(rec);
                }

                reader.close();
                System.out.println("\n\nData file read!");
                return file.getFileName().toString();
            } else {
                System.out.println("File not selected. Please restart program.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(ArrayList<String> list, String name) throws IOException {

        ArrayList<String> recs = list;
        String fileName = name;

        File workingDirectory = new File(System.getProperty("user.dir"));

        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + name);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String r : recs) {
                writer.write(r, 0, r.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}