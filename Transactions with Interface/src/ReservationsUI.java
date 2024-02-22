import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ReservationsUI extends JFrame {

    private JButton occupiedRoomsButton;
    private JButton freeRoomsButton;
    private JButton reserveARoomButton;
    private JButton cancelAResevationButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel ReservationsPanel;
    private JButton returnButton;
    private JLabel proyection;
    private JLabel results;
    private JScrollPane scrollPane;

    public ReservationsUI() throws Exception {
        ProyectoMySQL transaction = new ProyectoMySQL();

        setContentPane(scrollPane);
        setTitle("Room Reservations");
        setSize(675, 350);
        setVisible(true);
        occupiedRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("select * from reservation"));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: List of occupied rooms ");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        freeRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("SELECT ROOMID from room where ROOMID not in (select ROOMID FROM reservation WHERE DATE_TIME = '" + textField1.getText() + "')"));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: List of free rooms ");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        reserveARoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Statement = "INSERT INTO RESERVATION (ROOMID, RESERVED_BY, DATE_TIME, DURATION) VALUES ('" + textField2.getText() + "', '" + textField3.getText() + "', '" + textField4.getText() + "', " + textField5.getText() + ")";
                    transaction.stmt.executeUpdate(Statement);
                    proyection.setText("");
                    results.setText("The room has been reserved.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelAResevationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Statement = "delete from reservation where ROOMID = '" + textField6.getText() + "' and DATE_TIME = '" + textField7.getText() + "'";
                    transaction.stmt.executeUpdate(Statement);
                    proyection.setText("");
                    results.setText("The room has ben cleared.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseMainUI mainUI = new DatabaseMainUI();
                setVisible(false);
                mainUI.setVisible(true);
            }
        });
    }
}
