public class SQL_QUERIES {

    public static final String SELECT_ALL_EMPLOYEES = "select * from employees;";
    public static final String INSERT_QUERY = "insert into employees \n" +
            "(employee_id, first_name, last_name, job_title, salary, reports_to, office_id)\n" +
            "values\n";

    public static final String DELETE_QUERY = "delete from employees\n" +
            "where employee_id = ";

}

