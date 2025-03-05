//Progarm 1
import java.io.*;

class Student implements Serializable {
    private int id;
    private String name;
    private String gpa;

    
    public Student(int id, String name, String gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa='" + gpa + "'}";
    }
}

public class A {
    public static void main(String[] args) {
        Student student = new Student(1, "Mohit Singh", "8.5");

        // Serialize 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Student object has been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Student object has been deserialized.");
            System.out.println("Deserialized Student: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


// Progaram 2

import java.io.*;
import java.util.*;

public class DeserializeAndSum {
    public static void main(String[] args) {
        String numbers = "10,20,30,40,50"; // Example input string
        List<Integer> integerList = convertToIntegerList(numbers);
        
        // Serialize and Deserialize the list
        String filename = "integerList.ser";
        serializeList(integerList, filename);
        List<Integer> deserializedList = deserializeList(filename);
        
        // Calculate sum using autoboxing and unboxing
        int sum = calculateSum(deserializedList);
        System.out.println("Sum of integers: " + sum);
    }

    // Method to convert a comma-separated string to a list of Integer
    public static List<Integer> convertToIntegerList(String numbers) {
        List<Integer> list = new ArrayList<>();
        String[] splitNumbers = numbers.split(",");
        for (String num : splitNumbers) {
            list.add(Integer.parseInt(num.trim())); // Autoboxing
        }
        return list;
    }

    // Method to serialize a list of Integers
    public static void serializeList(List<Integer> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to deserialize a list of Integers
    @SuppressWarnings("unchecked")
    public static List<Integer> deserializeList(String filename) {
        List<Integer> list = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to calculate sum using autoboxing and unboxing
    public static int calculateSum(List<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            sum += num; // Unboxing happens here
        }
        return sum;
    }
}
