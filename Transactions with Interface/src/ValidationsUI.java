import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ValidationsUI extends JFrame {
    private JLabel results;
    private JComboBox comboBox1;
    private JButton validateAllOperationsButton;
    private JButton abortAllOperationsButton;
    private JScrollPane scrollPane;
    private JButton returnButton;

    public ValidationsUI() throws Exception {
        ProyectoMySQL transaction = new ProyectoMySQL();

        setContentPane(scrollPane);
        setTitle("Validations");
        setSize(475, 250);
        setVisible(true);
        validateAllOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transaction.conn.commit();    // fin de la transacción e inicio de la siguiente
                    results.setText("All operations validated");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        abortAllOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transaction.conn.rollback();    // fin de la transacción e inicio de la siguiente
                    results.setText("All operations aborted");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(comboBox1.getSelectedIndex())
                {
                    case (0):
                        DatabaseMainUI.isol = 2;
                        break;
                    case (1):
                        DatabaseMainUI.isol = 0;
                        break;
                    case (2):
                        DatabaseMainUI.isol = 1;
                        break;
                    case (3):
                        DatabaseMainUI.isol = 4;
                        break;
                    case (4):
                        DatabaseMainUI.isol = 8;
                        break;
                }
                try {
                    transaction.conn.setTransactionIsolation(DatabaseMainUI.isol);
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