import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<Student> groupList = new ArrayList<Student>();
    public static ArrayList<Student> studentsWithMark = new ArrayList<Student>();

    public static void ShowCommands() {
        System.out.println("""
                /h - help, lists commands and how to use them
                /r – selects random student, asks if present
                /l – list of students who received a grade
                /a - add student to group
                /e - shut down the program""");
    }

    public static void AddStudent() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        groupList.add(new Student(name));
        try (FileWriter writer = new FileWriter("group.txt", true)) {
            groupList.add(new Student(name));
            writer.write(name + "\n");
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static void ShowStudentsWithMark() {
        for (Student student : studentsWithMark) {
            System.out.println(student.name + " - " + student.mark);
        }
    }

    public static void RateStudent() {
        if (groupList.size() == studentsWithMark.size()) {
            System.out.println("All students was rated");
            return;
        }
        Random rnd = new Random();
        int newMark = rnd.nextInt(11);
        int posOfStudent = rnd.nextInt(groupList.size());
        while (groupList.get(posOfStudent).isPresent) {
            posOfStudent = rnd.nextInt(groupList.size());
        }
        groupList.get(posOfStudent).isPresent = true;
        groupList.get(posOfStudent).mark = newMark;
        studentsWithMark.add(groupList.get(posOfStudent));
    }

    public static void FillArray() {
        try {
            FileReader fr = new FileReader("group.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                groupList.add(new Student(line));
                line = reader.readLine();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        FillArray();
        label:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "/h":
                    ShowCommands();
                    break;
                case "/r":
                    RateStudent();
                    break;
                case "/a":
                    System.out.println("Input name of student");
                    AddStudent();
                    break;
                case "/l":
                    ShowStudentsWithMark();
                    break;
                case "/e":
                    break label;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
