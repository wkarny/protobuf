// See README.txt for information and build instructions.

import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

class AddPerson {

  static Person PromptForAddress(BufferedReader stdin,
                                  PrintStream stdout) throws IOException {
    Person.Builder person = Person.newBuilder();

    person.setId(123);
    person.setName("Alice");
    person.setEmail("alice@example.com");

    Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber("555-1212");   
    phoneNumber.setType(Person.PhoneType.MOBILE);
    person.addPhones(phoneNumber);

    return person.build();
  }

  
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
      System.exit(-1);
    }

    long t = System.currentTimeMillis();

    AddressBook.Builder addressBook = AddressBook.newBuilder();

    // try {
    //   FileInputStream input = new FileInputStream(args[0]);
    //   try {
    //     addressBook.mergeFrom(input);
    //   } finally {
    //     try { input.close(); } catch (Throwable ignore) {}
    //   }
    // } catch (FileNotFoundException e) {
    //   System.out.println(args[0] + ": File not found.  Creating a new file.");
    // }

    addressBook.addPeople(
      PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                       System.out));

    FileOutputStream output = new FileOutputStream(args[0]);
    try {
      addressBook.build().writeTo(output);
    } finally {
      output.close();
    }

    System.out.println("Time required to encode : " + (System.currentTimeMillis() - t));
  }
}
