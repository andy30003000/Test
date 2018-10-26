import javax.naming.Name;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.*;
import java.util.stream.Collectors;

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

        List<Person> personList = (Arrays.asList(new Person("Andy",36),new Person("Tammy",36),new Person("Richard",3)));
        //personList.forEach(myOtherPersonPrintConsumer);
        StringBuilder  Simpleprint = personList.parallelStream().map(Person::getName).collect(stringBuilderSupplier,StringBuilder::append
                    ,(StringBuilder a, StringBuilder b)->a.append(b));

        //BiConsumer<StringBuilder,StringBuilder> test =  (StringBuilder a, StringBuilder b)->a.append(b); //same as StringBuilder::append;

        System.out.println("Simple print: " + Simpleprint);

        long NumberOlderThan3 = personList.stream().map((person)->person.getAge()).filter((age)->age>3).count();

        System.out.println("Number Older than 3: " + NumberOlderThan3);

        Map<String,Integer> personMap = new HashMap<String, Integer>();
        personList.forEach((person)->personMap.put(person.getName(),person.getAge()));

        System.out.println("Print only Daddy: " + personMap.entrySet().stream().map((mapEntry)->mapEntry.getKey()).filter((name)->name.equalsIgnoreCase("andy")).count());

        personMap.entrySet().stream().map((mapEntry)->mapEntry.getKey())
                .sorted(Comparator.naturalOrder())
                .map((name)->name.toLowerCase())
                .forEach(myPersonPrintConsumer);


    }

}

class MySingleton
{
    //Test
}


enum MyEnumSingleton{
    instance;
    private MyEnumSingleton(){
        System.out.println("I am currently in the private enum constructor!!");

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Person) {
            return ((Person) o).getAge() == this.getAge() &&
                    ((Person) o).getName().equals(this.getName());
        }
        return false;

    }

    @Override
    public int hashCode(){
        return this.getAge() + Objects.hash(this.getName());
    }

}
