//STEP 1. Import required packages
import java.sql.*;

public class Lab1Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/world1";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Raketa4";

    private Connection connect() {
        Connection conn = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();


        }
        return conn;
    }

    private void doSelect(Connection conn){
        Statement stmt = null;
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT city.idCity, city.NameCity, people.NamePeople FROM world1.people JOIN world1.city ON people.idCity = city.idCity";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("idCity");
                String title = rs.getString("NameCity");
                String name = rs.getString("NamePeople");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Title of City: " + title);
                System.out.println(", Name: " + name);
            }
            rs.close();
            stmt.close();

        } catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }


    private void close(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void doInsert(Connection conn){
        Statement stmt = null;
        try {
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO world1.people(idPeople, NamePeople, idCity) " +
                    "VALUES ( 5, 'Evgen', 3)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO world1.people(idPeople, NamePeople, idCity) " +
                    "VALUES ( 6, 'Sonya', 3)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

            stmt.close();
        } catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }

    private void doUpdate(Connection conn){
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE people " +
                    "SET NamePeople = 'Dima' WHERE idPeople = 5";
            stmt.executeUpdate(sql);

            // Now you can extract all the records
            // to see the updated records
            sql = "SELECT idPeople, NamePeople, idCity FROM people";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("idPeople");
                String Name = rs.getString("NamePeople");
                int idC  = rs.getInt("idCity");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + Name);
                System.out.println(", idCity: " + idC);
            }
        rs.close();
    }catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }

    private void doDelete(Connection conn){
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM people " +
                    "WHERE idPeople = 5";
            stmt.executeUpdate(sql);

            // Now you can extract all the records
            // to see the remaining records
            sql = "SELECT idPeople, NamePeople, idCity FROM people";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("idPeople");
                String Name = rs.getString("NamePeople");
                int idC  = rs.getInt("idCity");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + Name);
                System.out.println(", idCity: " + idC);
            }
            rs.close();
        }catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }

    private void CreateTable(Connection conn){
        Statement stmt = null;
        try {
        System.out.println("Creating table in given database...");
        stmt = conn.createStatement();

        String sql = "CREATE TABLE PATIENTS " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
    } catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }

    private void AlterTable(Connection conn){
        Statement stmt = null;
        try {
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "ALTER TABLE PATIENTS ADD City VARCHAR(255)" ;

            stmt.executeUpdate(sql);
            System.out.println("Alter table in given database...");
        } catch(SQLException se2){
            // nothing we can do
            se2.printStackTrace();
            close(conn);
        }
    }

    public static void main(String[] args) {
      Lab1Main mc = new Lab1Main();
        Connection c = mc.connect();
      mc.connect();
      //mc.doInsert(c);
      //mc.doUpdate(c);
      //mc.doDelete(c);
      //mc.doSelect(c);
        // mc.CreateTable(c);
        mc.AlterTable(c);
      mc.close(c);
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample

