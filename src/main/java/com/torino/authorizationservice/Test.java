package com.torino.authorizationservice;

import java.io.*;

class Student implements Serializable {
    public int id;
}
public class Test {
    public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        Student s1 = new Student();
        //Class c1 = Class.forName("Student");
        //Student s2 = (Student) c1.newInstance();
        s1.id = 5;
        try {
            s1.id = 6/0;
        } catch (ArithmeticException e1) {

        } catch (Exception e) {
            System.out.println("Hello World!" + s1.id + e.getMessage() + ": " + e.getStackTrace().toString() + e);
        }

        try {
            testException(5);
            testException(15);
        } catch (FileNotFoundException e) {
            System.out.println("Error1" + e);
        } catch (IOException e1) {
            System.out.println("Error2" + e1);
        } catch (Exception e) {
            System.out.println("Error3" + e);
        }
        System.out.println("Hello World!" + s1.id);

        // Serialization
        String filename = "Student.ser";
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(file);
        oos.writeObject(s1);
        oos.close();

        FileInputStream file1 = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(file1);
        Student s3 = (Student) ois.readObject();
        System.out.println("Hello World!" + s3.id);

    }

    public static void testException(int i) throws FileNotFoundException, IOException, Exception {
        try {
            throw new Exception();
        }catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }finally {
            if (i < 0) {
                FileNotFoundException myException = new FileNotFoundException("Negative Integer " + i);
                throw myException;
            } else if (i > 10) {
                throw new IOException("Only supported for index 0 to 10");
            }
        }
    }
}
