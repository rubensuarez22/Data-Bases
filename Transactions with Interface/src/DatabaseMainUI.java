import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DatabaseMainUI extends JFrame {
    private JPanel Panel;
    private JButton roomsButton;
    private JButton reservationsButton;
    private JButton schedulesButton;
    private JButton validationsButton;
    private JButton logOutButton;
    private JLabel Isolation;
    public static int isol = 2;

    public DatabaseMainUI()
    {
        setContentPane(Panel);
        setTitle("Classroom Schedule");
        setSize(450, 200);
        Isolation.setText("Isolation Level: " + isol);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        roomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    RoomsUI roomsUI = new RoomsUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        reservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    ReservationsUI reservationsUI = new ReservationsUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        schedulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    ScheduleUI scheduleUI = new ScheduleUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        validationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    ValidationsUI validationsUI = new ValidationsUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main( String arg[] ) throws SQLException, Exception {

        if( arg.length != 0 ) {

            System.err.println( "Use: java TransactionMySQL" );
            System.exit( 1 );
        }

        ProyectoMySQL transaction = new ProyectoMySQL();

        DatabaseMainUI mainUI = new DatabaseMainUI();

        while( true )

            try {
                if( ! transaction.menu() )
                    break;

            } catch( Exception e ) {

                System.err.println( "failed" );
                e.printStackTrace( System.err );
            }

        transaction.close();
    }
}

