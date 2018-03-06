import java.sql.*;
import java.util.Scanner;

public class Application {

    private static final String url = "jdbc:mysql://localhost:3306/edu_platform_db?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();

            readData();
//            updateData();
//            insertData();
//            deleteData();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null)
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if(statement != null)
                try { statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            if(connection != null)
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private static void readData() throws SQLException {
        /*
         * 1. Amount of Products
         */
        rs = statement.executeQuery("SELECT COUNT(*) FROM user");

        while (rs.next()) {
            int count = rs.getInt(1);
            System.out.format("\ncount: %d\n", count);
        }

        /*
         * 2. Products
         */
        rs = statement.executeQuery("SELECT * FROM lecture");
        System.out.format("\nTable Lecture -----------------------------\n");
        System.out.format("%3s %-15s %-30s\n", "ID", "Name",
                "Description");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            System.out.format("%3d %-15s %-30s\n", id, name, description);
        }

        /*
         * 3. Joining table
         */
        String query = "SELECT email, role FROM user U " +
                "INNER JOIN user_role UR ON user_id=U.id " +
                "INNER JOIN role R ON R.id=UR.role_id;";
        rs = statement.executeQuery(query);

        System.out.format("\n Joining Table UserRole ---------------\n");
        System.out.format("%-20s %s\n", "Email", "Role");
        while (rs.next()) {
            String email = rs.getString("email");
            String role = rs.getString("role");
            System.out.format("%-20s %s\n", email, role);
        }
    }

    private static void updateData() throws SQLException {
        /*
         * 1. Update Course table
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Input Course name you'd like to update: ");
        String oldCourseName = input.nextLine();
        System.out.println("Input new Course name for " + oldCourseName);
        String newCourseName = input.nextLine();

//        statement.execute("UPDATE course SET name='" + courseNew + "' WHERE name='"
//                + course + "';");

        int updatedCourseRows = statement.executeUpdate("UPDATE course SET name='"
                + newCourseName + "' WHERE name='" + oldCourseName + "';");
        System.out.println("Rows updated: " + updatedCourseRows);

        /*
         * 2. Update Lecture table
         */
        System.out.println("Input Lecture name you'd like to update: ");
        String oldLectureName = input.nextLine();
        System.out.println("Input new name for Lecture " + oldLectureName);
        String newLectureName = input.nextLine();
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE lecture SET name=? WHERE name=?;");
        preparedStatement.setString(1, newLectureName);
        preparedStatement.setString(2, oldLectureName);
        int updatedLectureRows = preparedStatement.executeUpdate();
        System.out.println("Rows updated: " + updatedLectureRows);
    }

    private static void insertData() throws SQLException {
        /*
         * Insert into Course
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Input the name of new Course: ");
        String newCourseName = input.nextLine();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO course (name, description) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, newCourseName);
        preparedStatement.setString(2, "Description");
        int insertedRowsAmount = preparedStatement.executeUpdate();
        System.out.println("Rows inserted: " + insertedRowsAmount);
    }

    private static void deleteData() throws SQLException {
        /*
         * Delete from Course
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Input the name of course to delete: ");
        String courseToDeleteName = input.nextLine();

        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM course WHERE name=?");
        preparedStatement.setString(1, courseToDeleteName);
        int rowsDeleted = preparedStatement.executeUpdate();
        System.out.println(rowsDeleted);
    }
}
