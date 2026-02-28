import java.util.Scanner;
import java.util.ArrayList;

public class Employee {
    private int ID;
    private String name;
    private double salary;

    Employee(int ID, String name, double salary) {
        this.ID = ID;
        this.name = name;
        this.salary = salary;
    }

    public int getID() { return ID; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }

    // Update employee record
    static void updateEmployee(int id, Scanner sc, ArrayList<Employee> employees) {
        for (Employee e : employees) {
            if (e.getID() == id) {
                System.out.print("Enter new name: ");
                String newName = sc.nextLine();
                e.setName(newName);

                System.out.print("Enter new salary: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid salary: ");
                    sc.nextLine();
                }
                double newSalary = sc.nextDouble();
                sc.nextLine();
                e.setSalary(newSalary);

                System.out.println("Employee record updated successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    // Delete employee record
    static void deleteEmployee(int id, ArrayList<Employee> employees) {
        for (Employee e : employees) {
            if (e.getID() == id) {
                employees.remove(e);
                System.out.println("Employee record deleted successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    // Menu method
    static void Menu() {
        System.out.println("\nEmployee Management System");
        System.out.println("1. Insert Employee Record");
        System.out.println("2. View Employee List");
        System.out.println("3. Update Employee Record");
        System.out.println("4. Delete Employee Record");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        while (true) {
            Menu();

            // Validate menu choice input
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5: ");
                sc.nextLine();
            }
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1: // Add Employee
                    System.out.print("Enter employee ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int id = sc.nextInt();
                    sc.nextLine();

                    // Check for duplicate ID
                    boolean exists = false;
                    for (Employee e : employees) {
                        if (e.getID() == id) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        System.out.println("An employee with ID " + id + " already exists.");
                        break;
                    }

                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter employee salary: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid salary: ");
                        sc.nextLine();
                    }
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    employees.add(new Employee(id, name, salary));
                    System.out.println(name + " added successfully.");
                    break;

                case 2: // View Employee List
                    if (employees.isEmpty()) {
                        System.out.println("No employee records found.");
                    } else {
                        System.out.println("\n--- Employee List ---");
                        for (Employee e : employees) {
                            System.out.println("ID: " + e.getID() +
                                    " | Name: " + e.getName() +
                                    " | Salary: " + e.getSalary());
                        }
                    }
                    break;

                case 3: // Update Employee
                    System.out.print("Enter employee ID to update: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int updateID = sc.nextInt();
                    sc.nextLine();
                    updateEmployee(updateID, sc, employees);
                    break;

                case 4: // Delete Employee
                    System.out.print("Enter employee ID to delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int deleteID = sc.nextInt();
                    sc.nextLine();
                    deleteEmployee(deleteID, employees);
                    break;

                case 5: // Exit
                    System.out.println("Exiting....");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}