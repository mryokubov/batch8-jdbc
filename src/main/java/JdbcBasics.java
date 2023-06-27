import java.sql.*;

public class JdbcBasics {

    public static void main(String[] args) {


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr", "root", "admin114");
            Statement statement = connection.createStatement();

            String query = "select * from employees;";

            ResultSet resultSet = statement.executeQuery(query);

            System.out.printf("%-15s %-15s %-15s %-30s %-10s %-15s %-10s%n", "Employee ID", "First Name", "Last Name", "Job Title", "Salary", "Reports To", "Office ID");

            while (resultSet.next()){
                long employeeId = resultSet.getLong("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String jobTitle = resultSet.getString("job_title");
                double salary = resultSet.getDouble("salary");
                long reportsTo = resultSet.getLong("reports_to");
                int officeId = resultSet.getInt("office_id");
                System.out.printf("%-15d %-15s %-15s %-30s %-10.2f %-15d %-10d%n", employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
