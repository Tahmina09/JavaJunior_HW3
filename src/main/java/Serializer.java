import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;


public class Serializer {
    public static final String FILE_JSON = "classbook.json";
    public static final String FILE_XML = "classbook.xml";
    public static final String FILE_BIN = "classbook.bin";
    private static final ObjectMapper objectmapper = new ObjectMapper();
    private static final XmlMapper xmlmapper = new XmlMapper();


    public static void addStudent(String fileName, Student student) {
        try{
            if (fileName.endsWith(".json")) {
                objectmapper.writeValue(new File(FILE_JSON), student);
                System.out.println("Объект сериализован!");
            } else if (fileName.endsWith(".xml")) {
                xmlmapper.writeValue(new File(FILE_XML), student);
                System.out.println("Объект сериализован!");
            } else if (fileName.endsWith(".bin")) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_BIN)) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(student);
                    System.out.println("Объект сериализован!");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //ToDo
    public static void readFromFile(String fileName) {
        File file = new File(fileName);
        Student student = new Student();
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectmapper.readValue(file, objectmapper.getTypeFactory().constructType(Student.class));
                    System.out.println("Объект десериализован!");
                } else if (fileName.endsWith(".xml")) {
                    student = xmlmapper.readValue(file, xmlmapper.getTypeFactory().constructType(Student.class));
                    System.out.println("Объект десериализован!");
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_BIN))) {
                        student = (Student) objectInputStream.readObject();
                        System.out.println("Объект десериализован!");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
