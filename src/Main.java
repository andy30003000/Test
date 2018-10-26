import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.*;
// one class needs to have a main() method
public class Main
{
    // arguments are passed using the text field below this editor
    public static void main(String[] args)
    {
        MyTestJava8.myTestJava8Method();

    }



}

 class MyTestJava8{

    public static void myTestJava8Method(){
        Supplier<StringBuilder> stringBuilderSupplier = StringBuilder::new;
        Consumer<String> myPersonPrintConsumer = System.out::println;
        Consumer<Person> myOtherPersonPrintConsumer = (Person) -> System.out.println(Person.getName());

        List<Person> testStringList = (Arrays.asList(new Person("Andy",36),new Person("Tammy",36),new Person("Richar",3)));

        //testStringList.forEach(myOtherPersonPrintConsumer);
        testStringList.stream().map(Person::getName).collect(StringBuilder::new,,)



    }
}

class MySingleton
{
    //Test
}


enum MyEnumSingleton{
    instance;
    private MyEnumSingleton(){
        System.out.println("I am currently in the private enum constructor");

    }
}

class Person
{
    private String name;
    private int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
