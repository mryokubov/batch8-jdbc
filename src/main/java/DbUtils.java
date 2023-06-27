import java.sql.*;

public class DbUtils {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public DbUtils() {
        try {
            this.connection = DriverManager.getConnection(ConfigReader.getProperty("db_url"),
                    ConfigReader.getProperty("db_username"), ConfigReader.getProperty("db_password"));
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectQuery(String query){
        try {
            this.resultSet =  this.statement.executeQuery(query);
            return this.resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertQuery(String insertQuery){
        boolean execute = false;
        try {
            execute = this.statement.execute(insertQuery);
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printResultSet(ResultSet resultSet){

        try {
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
        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }

    public void deleteRow(String query, long employeeId){
        try {
            query += employeeId +";";
            int i = statement.executeUpdate(query);
        }
        catch (Exception e){

        }
    }

    public long getLargestEmpId(){
        try {
            String query = "select employee_id from employees\n" +
                    "order by employee_id desc\n" +
                    "limit 1;";
            resultSet = selectQuery(query);
            while (resultSet.next()){
                long employeeId = resultSet.getLong("employee_id");
                return employeeId;
            }
        }
       catch (Exception e){
           e.printStackTrace();
       }
        return 0;
    }

    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
