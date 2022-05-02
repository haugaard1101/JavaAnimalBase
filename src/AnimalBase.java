import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalBase {

  private ArrayList<Animal> animals;

  public AnimalBase() {
    animals = new ArrayList<>();
  }

  public void start() throws FileNotFoundException {
    UserInterface ui = new UserInterface(this);
    ui.start();
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimalBase app = new AnimalBase();
    app.start();
  }

  public Iterable<Animal> getAllAnimals() {
    return animals;
  }

  public int getAnimalCount() {
    return animals.size();
  }

  public void sortBy(String sortBy, SortDirection sortDirection) {
    // TODO: Implement sorting!
    System.out.println("TODO: Sort the list of animals by: " + sortBy);
  }

  public void createNewAnimal(String name, String description, String type, int age, double weight) {
    Animal animal = new Animal(name, description, type, age, weight);
    animals.add(animal);
  }

  public boolean deleteAnimal(String name) {
    // find animal with this name
    Animal animal = findAnimalByName(name);
    if (animal == null) {
      return false;
    } else {
      animals.remove(animal);
      return true;
    }
  }

  private Animal findAnimalByName(String name) {
    for (Animal animal : animals) {
      if (animal.getName().equalsIgnoreCase(name)) {
        return animal;
      }
    }
    return null;
  }


  public void loadDatabase() {

    Scanner loader = new Scanner(System).useDelimiter(";");

    while (loader.hasNextLine()) {
      String line = loader.findInLine();

      String name = loader.next();
      String description = loader.next();
      String type = loader.next();
      // loader.next(); // Scanner bug
      int age = loader.nextInt();
      // loader.next(); // Scanner bug
      double weight = loader.nextDouble();
    }
    System.out.println("Done");
  }

  public void saveDatabase() throws FileNotFoundException {
    PrintStream ps = new PrintStream("animals.csv");
    for (Animal animal : getAllAnimals()) {
      writeAnimal(ps, animal);
    }
  }

  public void writeAnimal(PrintStream ps, Animal animal) {
    ps.print(animal.getName());
    ps.print(";");
    ps.print(animal.getDesc());
    ps.print(";");
    ps.print(animal.getType());
    ps.print(";");
    ps.print(animal.getAge());
    ps.print(";");
    ps.print(animal.getWeight());
    ps.print("\n");
  }

}
