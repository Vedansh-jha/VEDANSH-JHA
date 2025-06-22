// Exercise 1: Inventory Management System
import java.util.*;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}

class InventorySystem {
    HashMap<Integer, Product> inventory = new HashMap<>();

    void addProduct(Product p) {
        inventory.put(p.productId, p);
    }

    void updateProduct(int id, Product p) {
        inventory.put(id, p);
    }

    void deleteProduct(int id) {
        inventory.remove(id);
    }
}

// Exercise 2: E-commerce Platform Search Function
class SearchProduct {
    int productId;
    String productName;
    String category;

    SearchProduct(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

class SearchSystem {
    SearchProduct[] products;

    SearchSystem(SearchProduct[] products) {
        this.products = products;
    }

    SearchProduct linearSearch(String name) {
        for (SearchProduct p : products) {
            if (p.productName.equals(name)) return p;
        }
        return null;
    }

    SearchProduct binarySearch(String name) {
        int low = 0, high = products.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = products[mid].productName.compareTo(name);
            if (cmp == 0) return products[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}

// Exercise 3: Sorting Customer Orders
class Order {
    int orderId;
    String customerName;
    double totalPrice;

    Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
}

class SortOrders {
    static void bubbleSort(Order[] orders) {
        for (int i = 0; i < orders.length - 1; i++) {
            for (int j = 0; j < orders.length - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}

// Exercise 4: Employee Management System
class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}

class EmployeeSystem {
    Employee[] employees = new Employee[100];
    int count = 0;

    void addEmployee(Employee e) {
        employees[count++] = e;
    }

    Employee searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) return employees[i];
        }
        return null;
    }

    void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i].name);
        }
    }

    void deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                employees[i] = employees[count - 1];
                count--;
                break;
            }
        }
    }
}

// Exercise 5: Task Management System
class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
}

class TaskList {
    Task head;

    void addTask(Task t) {
        t.next = head;
        head = t;
    }

    Task searchTask(int id) {
        Task curr = head;
        while (curr != null) {
            if (curr.taskId == id) return curr;
            curr = curr.next;
        }
        return null;
    }

    void traverseTasks() {
        Task curr = head;
        while (curr != null) {
            System.out.println(curr.taskName);
            curr = curr.next;
        }
    }

    void deleteTask(int id) {
        Task curr = head, prev = null;
        while (curr != null) {
            if (curr.taskId == id) {
                if (prev == null) head = curr.next;
                else prev.next = curr.next;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }
}

// Exercise 6: Library Management System
class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}

class Library {
    Book[] books;

    Library(Book[] books) {
        this.books = books;
    }

    Book linearSearch(String title) {
        for (Book b : books) {
            if (b.title.equals(title)) return b;
        }
        return null;
    }

    Book binarySearch(String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareTo(title);
            if (cmp == 0) return books[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}

// Exercise 7: Financial Forecasting
class FinancialForecast {
    double predictFutureValue(double currentValue, double rate, int years) {
        if (years == 0) return currentValue;
        return predictFutureValue(currentValue * (1 + rate), rate, years - 1);
    }

    double predictIteratively(double currentValue, double rate, int years) {
        for (int i = 0; i < years; i++) {
            currentValue *= (1 + rate);
        }
        return currentValue;
    }
}
