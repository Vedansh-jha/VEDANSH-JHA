// Exercise 1: Singleton Pattern
// Logger.java
public class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger initialized.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        System.out.println("Same instance? " + (logger1 == logger2));
    }
}


// Exercise 2: Factory Method Pattern
// Document.java
interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word Document...");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF Document...");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel Document...");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// FactoryTest.java
public class FactoryTest {
    public static void main(String[] args) {
        DocumentFactory factory;

        factory = new WordFactory();
        Document doc1 = factory.createDocument();
        doc1.open();

        factory = new PdfFactory();
        Document doc2 = factory.createDocument();
        doc2.open();

        factory = new ExcelFactory();
        Document doc3 = factory.createDocument();
        doc3.open();
    }
}


// Exercise 3: Builder Pattern
class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public void specs() {
        System.out.println("CPU: " + CPU + ", RAM: " + RAM + ", Storage: " + storage);
    }
}

public class BuilderTest {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .build();

        Computer officePC = new Computer.Builder()
            .setCPU("Intel i5")
            .setRAM("16GB")
            .setStorage("512GB SSD")
            .build();

        gamingPC.specs();
        officePC.specs();
    }
}


// Exercise 4: Adapter Pattern
interface PaymentProcessor {
    void processPayment();
}

class ThirdPartyGateway {
    public void makeTransaction() {
        System.out.println("Payment made using third-party gateway.");
    }
}

class GatewayAdapter implements PaymentProcessor {
    private ThirdPartyGateway gateway;

    public GatewayAdapter(ThirdPartyGateway gateway) {
        this.gateway = gateway;
    }

    public void processPayment() {
        gateway.makeTransaction();
    }
}

public class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor processor = new GatewayAdapter(new ThirdPartyGateway());
        processor.processPayment();
    }
}


// Exercise 5: Decorator Pattern
interface Notifier {
    void send();
}

class EmailNotifier implements Notifier {
    public void send() {
        System.out.println("Sending Email Notification");
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        notifier.send();
        System.out.println("Sending SMS Notification");
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        notifier.send();
        System.out.println("Sending Slack Notification");
    }
}

public class DecoratorTest {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier = new SMSNotifierDecorator(notifier);
        notifier = new SlackNotifierDecorator(notifier);

        notifier.send();
    }
}


// Exercise 6: Proxy Pattern
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");
        image.display();
        image.display();
    }
}


// Exercise 7: Observer Pattern
import java.util.*;

interface Observer {
    void update(float price);
}

interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private float price;

    public void setPrice(float price) {
        this.price = price;
        notifyObservers();
    }

    public void register(Observer o) {
        observers.add(o);
    }

    public void deregister(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(price);
        }
    }
}

class MobileApp implements Observer {
    public void update(float price) {
        System.out.println("MobileApp: Stock price updated to " + price);
    }
}

class WebApp implements Observer {
    public void update(float price) {
        System.out.println("WebApp: Stock price updated to " + price);
    }
}

public class ObserverTest {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        market.register(mobile);
        market.register(web);

        market.setPrice(100);
        market.setPrice(105);
    }
}


// Exercise 8: Strategy Pattern
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void payAmount(int amount) {
        strategy.pay(amount);
    }
}

public class StrategyTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext(new CreditCardPayment());
        context.payAmount(250);

        context = new PaymentContext(new PayPalPayment());
        context.payAmount(400);
    }
}


// Exercise 9: Command Pattern
interface Command {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void pressButton() {
        command.execute();
    }
}

public class CommandTest {
    public static void main(String[] args) {
        Light light = new Light();
        Command on = new LightOnCommand(light);
        Command off = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(on);
        remote.pressButton();

        remote.setCommand(off);
        remote.pressButton();
    }
}


// Exercise 10: MVC Pattern
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}

class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("Student: " + name + ", ID: " + id + ", Grade: " + grade);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
}

public class MVCTest {
    public static void main(String[] args) {
        Student student = new Student("Alice", "S101", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        controller.updateView();
        controller.setStudentName("Bob");
        controller.setStudentGrade("A+");
        controller.updateView();
    }
}


// Exercise 11: Dependency Injection
interface CustomerRepository {
    String findCustomerById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public String findCustomerById(String id) {
        return "Customer Name for ID: " + id;
    }
}

class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void displayCustomer(String id) {
        System.out.println(repository.findCustomerById(id));
    }
}

public class DIExample {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);
        service.displayCustomer("123");
    }
}
