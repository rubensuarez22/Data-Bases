import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class ProyectoMySQL {

    Connection conn = null;
    Statement stmt = null;
    BufferedReader in = null;

    static final String URL = "jdbc:mysql://localhost/";
    static final String BD = "proyecto_final";        // specify: the name of th DB,
    static final String USER = "root";        // The username
    static final String PASSWD = "Barbas#77736";// The user's password

    public ProyectoMySQL() throws SQLException, Exception {

        // this will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.print("Connecting to the database... ");

        // setup the connection with the DB
        conn = DriverManager.getConnection(URL + BD, USER, PASSWD);
        System.out.println("connected\n\n");

        conn.setAutoCommit(false);         // beginning of the transaction
        stmt = conn.createStatement();
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int columnCount = rsetmd.getColumnCount();

        StringBuilder sb = new StringBuilder();

        while (rset.next()) {
            for (int j = 1; j <= columnCount; j++) {
                sb.append(rset.getString(j)).append("\t");
            }
            sb.append("<br>");
        }

        return sb.toString();
    }

    ResultSet query(String statement) throws SQLException {

        ResultSet rset = stmt.executeQuery(statement);
        System.out.println("Results:");

        System.out.println();
        return rset;
    }

    void close() throws SQLException {

        stmt.close();
        conn.close();
    }

    boolean menu() throws SQLException, IOException {

        String statement;

        System.out.println("\nLevel of Isolation = " + conn.getTransactionIsolation() + "\n");
        System.out.println("Section 1 (ROOMS)\n");
        System.out.println("(1) The complete list of rooms\n");
        System.out.println("(2) Restricted list of room\n");
        System.out.println("Section 2 (RESERVATIONS)\n");
        System.out.println("(3) Check occupied rooms\n");
        System.out.println("(4) Free rooms on a given day\n");
        System.out.println("(5) Reserve a room on a given day\n");
        System.out.println("(6) Cancel a reservation on a given day\n");
        System.out.println("Section 2 (SCHEDULES)\n");
        System.out.println("(7) The complete schedule\n");
        System.out.println("(8) Partial schedules based on your criteria\n");
        System.out.println("(9) Add an entry in the schedule\n");
        System.out.println("(10) Modify a schedule's value\n");
        System.out.println("(11) Delete a course from the schedule\n");
        System.out.println("Section 3 (Validations)\n");
        System.out.println("(12) Validate all operations\n");
        System.out.println("(13) Abort all operations\n");
        System.out.println("(14) Change level of isolation\n");
        System.out.println("(15) Sign out\n\n");
        System.out.print("Option: ");

        switch (Integer.parseInt("0" + in.readLine())) {

            case 1:
                query("select * from room");
                break;

            case 2:
                System.out.println("\nPredicate?");
                query("select * from room where " + in.readLine());
                break;

            case 3:
                query(statement = "select * from reservation");
                break;
            case 4:
                System.out.print("Date and time? (YYYY-MM-DD HH:MM:SS)\n");
                String dateTime = in.readLine();
                query("SELECT ROOMID from room where ROOMID not in (select ROOMID FROM reservation WHERE DATE_TIME = '" + dateTime + "')");
                break;

            case 5:
                System.out.print("What classroom will be used? \n");
                String roomId = in.readLine();

                System.out.print("Who is reserving it? \n");
                String reservedBy = in.readLine();

                System.out.print("Date and time? (YYYY-MM-DD HH:MM:SS)\n");
                dateTime = in.readLine();

                System.out.print("Duration for the reservation (in minutes)?\n");
                String duration = in.readLine();

                statement = "INSERT INTO RESERVATION (ROOMID, RESERVED_BY, DATE_TIME, DURATION) VALUES ('" + roomId + "', '" + reservedBy + "', '" + dateTime + "', " + duration + ")";
                stmt.executeUpdate(statement);
                break;

            case 6:
                System.out.print("Cancel reservation in what classroom?\n");
                roomId = in.readLine();
                System.out.print("What day and time? (YYYY-MM-DD HH:MM:SS)\n");
                stmt.executeUpdate("delete from reservation where ROOMID = '" + roomId + "' and DATE_TIME = '" + in.readLine() + "'");
                break;

            case 7:
                query("select * from schedules");
                break;

            case 8:
                System.out.println("\nPredicate?");
                query("select * from schedules where " + in.readLine());
                break;

            case 9:
                System.out.print("Course ID? \n");
                String courseId = in.readLine();
                System.out.print("Section ? \n");
                String section = in.readLine();
                System.out.print("Week day? (S-M-T-W-TH-F-SA)\n");
                String weekd = in.readLine();
                int dayNumber = 0;
                switch (weekd.toUpperCase()) {
                    case "S":
                        dayNumber = 1;
                        break;
                    case "M":
                        dayNumber = 2;
                        break;
                    case "T":
                        dayNumber = 3;
                        break;
                    case "W":
                        dayNumber = 4;
                        break;
                    case "TH":
                        dayNumber = 5;
                        break;
                    case "F":
                        dayNumber = 6;
                        break;
                    case "SA":
                        dayNumber = 7;
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid day abbreviation (L-M-X-J-V-S-D).");
                        break;
                }

                System.out.print("Hour? \n");
                String hour = in.readLine();


                System.out.print("Minute? \n");
                String minute = in.readLine();

                System.out.print("Duration (in minutes)?\n");
                duration = in.readLine();

                System.out.print("Period? (SPRING-(21-24) or WINTER-(21-24))\n");
                String period = in.readLine();

                System.out.print("Semester?\n");
                String semester = in.readLine();

                System.out.print("Room ID? \n");
                roomId = in.readLine();

                statement = "INSERT INTO SCHEDULES (COURSEID, SECC, WEEKD, COURSE_HOUR, COURSE_MINUTE, DURATION, PERIOD, SEMESTER, ROOMID) VALUES ('" + courseId + "', '" + section + "','" + dayNumber + "','" + hour + "','" + minute + "', '" + duration + "', '" + period + "', " + semester + ", '" + roomId + "')";
                stmt.executeUpdate(statement);
                break;
            case 10:
                statement = "update schedules set ";

                System.out.println("\n<Attribute> = <Value>?");
                statement += in.readLine();

                System.out.println("What course ID?");
                courseId = in.readLine();
                statement += " where courseid = '" + courseId + "'";

                System.out.println("What section?");
                section = in.readLine();
                statement += " and secc = '" + section + "'";

                System.out.println("What period?");
                period = in.readLine();
                statement += " and period = '" + period + "'";

                System.out.println("Want to change both days (2) or just one (1)?");
                query("SELECT * from schedules where courseid = '" + courseId + "' and secc = '" + section + "' and period = '" + period + "'");
                Scanner scanner = new Scanner(System.in);
                Integer a = scanner.nextInt();
                if (a == 1) {
                    System.out.println("Select which day from the schedules above? (1: Sunday and 7: Monday)");
                    dayNumber = scanner.nextInt();
                    ;
                    statement += " and weekd = '" + dayNumber + "'";
                    stmt.executeUpdate(statement);
                } else {
                    stmt.executeUpdate(statement);
                }
                break;
            case 11:
                System.out.println("Course ID to delete?");
                courseId = in.readLine();

                stmt.executeUpdate("DELETE FROM schedules WHERE courseid = '" + courseId + "'");
                query("SELECT COURSEID from COURSE");
                break;
            case 12:
                conn.commit();      // fin de la transacción e inicio de la siguiente
                break;

            case 13:
                conn.rollback();    // fin de la transacción e inicio de la siguiente
                break;

            case 14:
                System.out.println();

                System.out.println(conn.TRANSACTION_NONE + " = TRANSACTION_NONE");
                System.out.println(conn.TRANSACTION_READ_UNCOMMITTED + " = TRANSACTION_READ_UNCOMMITTED");
                System.out.println(conn.TRANSACTION_READ_COMMITTED + " = TRANSACTION_READ_COMMITTED");
                System.out.println(conn.TRANSACTION_REPEATABLE_READ + " = TRANSACTION_REPEATABLE_READ");
                System.out.println(conn.TRANSACTION_SERIALIZABLE + " = TRANSACTION_SERIALIZABLE\n\n");

                System.out.println("Nivel?");
                conn.setTransactionIsolation(Integer.parseInt(in.readLine()));
                break;

            case 15:
                return false;
        }

        return true;
    }
}