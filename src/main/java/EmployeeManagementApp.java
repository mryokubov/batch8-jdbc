import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeManagementApp {


    public static void main(String[] args) {

        DbUtils utils = new DbUtils();
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO EMPLOYEE MANAGEMENT APP");

        boolean start = true;
        while (start)
        {
            System.out.println("SELECT FROM THE FOLLOWING: ");
            System.out.println("1. SEE EMPLOYEES  2. ADD EMPLOYEE  3. DELETE EMPLOYEE   4. QUIT");
            System.out.print("ANSWER: ");
            int answer = Integer.parseInt(scanner.nextLine());

            switch (answer){
                case 1:
                    ResultSet resultSet = utils.selectQuery(SQL_QUERIES.SELECT_ALL_EMPLOYEES);
                    utils.printResultSet(resultSet);
                    break;
                case 4:
                    System.out.println("GOOD BYE");
                    utils.closeConnection();
                    start = false;
                    break;
                case 2:
                    System.out.print("firstname: ");
                    String firstName = scanner.nextLine();
                    System.out.print("lastname: ");
                    String lastName = scanner.nextLine();
                    System.out.print("job title: ");
                    String job_title = scanner.nextLine();
                    System.out.print("salary: ");
                    long salary = Long.parseLong(scanner.nextLine());
                    long reportsTo = 37270;
                    long officeId = 2;
                    String insertQuery = SQL_QUERIES.INSERT_QUERY + "(" +
                            (utils.getLargestEmpId()+1) + ", \'" + firstName + "\',\'" + lastName +"\',\'" + job_title + "\'," + salary + "," + reportsTo + "," + officeId +");";
                    utils.insertQuery(insertQuery);
                    break;
                case 3:
                    System.out.print("ENTER EMPLOYEE ID: ");
                    long empId = Long.parseLong(scanner.nextLine());
                    utils.deleteRow(SQL_QUERIES.DELETE_QUERY, empId);
                    break;
            }

        }





    }
}
