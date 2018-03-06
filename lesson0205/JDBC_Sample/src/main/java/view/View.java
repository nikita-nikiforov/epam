package view;

import model.Course;
import model.metadata.TableMetaData;
import persistant.ConnectionManager;
import service.CourseService;
import service.MetaDataService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() {
        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        menu.put("A", "    A - Select all table");
        menu.put("B", "    B - Select structure of DB");

        menu.put("1", "    1 - Table: Course");
        menu.put("11", "    11 - Create new Course");
        menu.put("12", "    12 - Update Course");
        menu.put("13", "    13 - Delete from Course");
        menu.put("14", "    14 - Select Course");
        menu.put("15", "    15 - Find Course by ID");

        menu.put("Q", "    Q - exit");

        methodsMenu.put("A", this::selectAllTable);
        methodsMenu.put("B", this::takeStructureOfDB);

        methodsMenu.put("11", this::createForCourse);
        methodsMenu.put("12", this::updateCourse);
        methodsMenu.put("13", this::deleteFromCourse);
        methodsMenu.put("14", this::selectCourse);
        methodsMenu.put("15", this::findCourseByID);
    }

    private void selectAllTable() throws SQLException {
        selectCourse();
    }

    private void takeStructureOfDB() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        MetaDataService metaDataService = new MetaDataService();
        List<TableMetaData> tables = metaDataService.getTablesStructure();
        System.out.println("TABLES OF DATABASE: " + connection.getCatalog());

        for (TableMetaData table : tables) {
            System.out.println(table);
        }
    }

    private void createForCourse() throws SQLException {
        System.out.println("Input ID for Course: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Course: ");
        String name = input.nextLine();
        System.out.println("Input description for Course: ");
        String description = input.nextLine();
        Course course = new Course(id, name, description);

        CourseService courseService = new CourseService();
        int count = courseService.create(course);
        System.out.println("Rows have been created: " + count);
    }

    private void updateCourse() throws SQLException {
        System.out.println("Input ID of Course: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Course: ");
        String name = input.nextLine();
        System.out.println("Input description for Course: ");
        String description = input.nextLine();
        Course course = new Course(id, name, description);

        CourseService courseService = new CourseService();
        int count = courseService.update(course);
        System.out.println("Rows have been updated: " + count);
    }

    private void deleteFromCourse() throws SQLException {
        System.out.println("Input ID of Course: ");
        int id = Integer.parseInt(input.nextLine());
        CourseService courseService = new CourseService();
        int count = courseService.delete(id);
        System.out.println("Rows have been deleted: " + count);

    }

    private void selectCourse() throws SQLException {
        System.out.println("\nTable: Course");
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.findAll();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void findCourseByID() throws SQLException {
        System.out.println("Input ID of Course: ");
        int id = Integer.parseInt(input.nextLine());
        CourseService courseService = new CourseService();
        Course course = courseService.findById(id);
        System.out.println(course);
    }

    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet()) {
            if(key.length() == 1) System.out.println(menu.get(key));
        }
    }

    private void outputSubmenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet()) {
            if (key.length()!=1 && key.substring(0, 1).equals(fig)){
                System.out.println(menu.get(key));
            }
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point: ");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubmenu(keyMenu);
                System.out.println("Please, select menu point: ");
                keyMenu = input.nextLine().toUpperCase();
            }

            try{
                methodsMenu.get(keyMenu).print();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (!keyMenu.equalsIgnoreCase("Q"));
    }
}