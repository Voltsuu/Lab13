import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileChecker {
    public static ArrayList<String> list = new ArrayList<String>();
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        boolean loaded = false;
        boolean edited = false;
        boolean saved = false;
        String fileName = null;
        String option = "";

        boolean done = false;
        while (!done) {
            option = InputHelper.getRegExString(scan, "Options: \nA – Add an item to the list \nD – Delete an item from the list \n" +
                    "V – View the list \nO - Open \nS – Save \nC – Clear \nQ – Quit the program", "[AaDdVvQqOoSsCcQq]");
            if (option.equalsIgnoreCase("A")) {
                add();
                if (loaded) {
                    edited = true;
                    saved = false;
                }
            } else if (option.equalsIgnoreCase("D")) {
                delete();
                if (loaded) {
                    edited = true;
                    saved = false;
                }
            } else if (option.equalsIgnoreCase("V")) {
                view();
            } else if (option.equalsIgnoreCase("O")) {
                if (edited) {
                    if (loaded) {
                        IOHelper.writeFile(list, fileName);
                    } else {
                        if (InputHelper.getYNConfirm(scan, "Do you want to save the current list before opening?")) {
                        }
                    }
                }
                list.clear();
                fileName = IOHelper.openFile(list);
                loaded = true;
                edited = false;
                saved = false;
            } else if (option.equalsIgnoreCase("S")) {
                if (loaded) {
                    IOHelper.writeFile(list, fileName);
                } else {
                    IOHelper.writeFile(list, InputHelper.getNonZeroLenString(scan, "Choose your file"));
                }
                loaded = false;
                edited = false;
                saved = true;
            } else if (option.equalsIgnoreCase("C")) {
                list.clear();
                loaded = true;
                edited = false;
                saved = false;
            }
            else if (option.equalsIgnoreCase("Q")) {
                quit();
            }
        }
    }
    public static void add() {
        String bruh = InputHelper.getNonZeroLenString(scan, "Add something bum");
        list.add(bruh);
    }
    public static void delete(){
        int index = InputHelper.getRangedInt(scan, "Enter an index now!!!!", 0, list.size());
        list.remove(index);
    }
    public static void view(){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " - " + list.get(i));
        }
    }
    public static void quit(){
        boolean done = false;
        done = InputHelper.getYNConfirm(scan, "Are you sure you want to quit? [Y/N]");
    }
}