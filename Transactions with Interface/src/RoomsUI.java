import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RoomsUI extends JFrame {
    private JButton completeListOfRoomsButton;
    private JButton restrictedListOfRoomsButton;
    private JPanel RoomsPanel;
    private JButton returnButton;
    private JTextField textField1;
    private JLabel proyection;
    private JLabel results;
    private JScrollPane ScrollPane;

    public RoomsUI () throws Exception {
        ProyectoMySQL transaction = new ProyectoMySQL();

        setContentPane(ScrollPane);
        setTitle("Room information");
        setSize(475, 250);
        setVisible(true);
        completeListOfRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("select * from room"));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: Complete list of rooms");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        restrictedListOfRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("select * from room where " + textField1.getText()));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: Restricted list of rooms");
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
