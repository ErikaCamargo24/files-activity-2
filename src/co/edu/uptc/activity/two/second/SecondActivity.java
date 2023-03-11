package co.edu.uptc.activity.two.second;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity {
	
	private static Person readPerson (String path){
        Person person = new Person();
        File file = new File(path);
        try(DataInputStream stream = new DataInputStream(new FileInputStream(path))){
            person.setId(stream.readLong());
            person.setFullName(stream.readUTF());
            person.setPhone(stream.readUTF());
            person.setSalary(stream.readDouble());
            person.setAge(stream.readInt());

        }catch (Exception e){
            System.err.println(e.getStackTrace());
        }
        return person;
    }
	
	
	private static void writeNewPerson(String path, Person  person){
        try{
            DataOutputStream stream = new DataOutputStream(new FileOutputStream(path));

            stream.writeUTF(person.getFullName());
            stream.writeDouble(person.getSalary());
            stream.writeInt(person.getAge());
            stream.writeUTF(person.getPhone());
            stream.writeLong(person.getId());
            stream.close();

        }catch (IOException e){
            System.err.println(e.getStackTrace());
        }
    }
	
	private static String revertFullName(String fullName, String useDecode){
        StringBuilder builder = new StringBuilder();
        String[] names = fullName.split(useDecode);

        for (int i = names.length-1; i >= 0; i--) {
            builder.append(names[i]).append(" ");
        }

        return builder.toString();
    }

    private static int totalAge(int age){
    	return (age -10);
    }

    private static long valueId(long id){
    	return (id * 2);
    }
    
    private static double valueTotalSalary(double salary){
    	return (salary / 2);
    }
	

	   public static void main(String[] args) {
	        Person oldPerson = readPerson("src/resources/input/person.bin");
	        oldPerson.setFullName(revertFullName(oldPerson.getFullName(), " "));
	        oldPerson.setAge(totalAge(oldPerson.getAge()));
	        oldPerson.setId(valueId(oldPerson.getId()));
	        oldPerson.setSalary(valueTotalSalary(oldPerson.getSalary()));
	        System.out.println("Edad " + valueId(oldPerson.getId()));
	        System.out.println("number " + oldPerson.getPhone());
	        writeNewPerson("src/resources/output/new-person.ddr", oldPerson);
	    }

}
