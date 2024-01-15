import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Student studentA = new Student("Michael", 15, 96.5);

        Serializer.addStudent(Serializer.FILE_JSON, studentA);
        Serializer.addStudent(Serializer.FILE_XML, studentA);
        Serializer.addStudent(Serializer.FILE_BIN,studentA);

        Serializer.readFromFile(Serializer.FILE_BIN);
    }
}