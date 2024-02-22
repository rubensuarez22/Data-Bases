import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.Delayed;

public class ScheduleUI extends JFrame {
    private JButton scheduleButton;
    private JButton filteredScheduleButton;
    private JButton addAnEntryButton;
    private JButton deleteCourseButton;
    private JButton modifyScheduleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JCheckBox specificDayCheckBox;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JButton returnButton;
    private JLabel results;
    private JLabel proyection;
    private JPanel SchedulePanel;
    private JTextField textField8;
    private JScrollPane scrollPane;

    public ScheduleUI() throws Exception {
        ProyectoMySQL transaction = new ProyectoMySQL();

        setContentPane(scrollPane);
        setTitle("Room Schedule");
        setSize(675, 550);
        setVisible(true);
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("select * from schedules"));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: Schedule ");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        filteredScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = transaction.dumpResultSet(transaction.query("select * from schedules where " + textField1.getText()));
                    proyection.setText("<html>" + text + "</html>");
                    results.setText("Showing: Filtered Schedule ");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addAnEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Statement = "INSERT INTO SCHEDULES (COURSEID, SECC, WEEKD, COURSE_HOUR, COURSE_MINUTE, DURATION, PERIOD, SEMESTER, ROOMID) VALUES ('" + textField2.getText() + "','" + textField3.getText() + "','" + textField4.getText() + "','" + textField5.getText() + "','" + textField6.getText() + "','" + textField7.getText() + "','" + textField8.getText() + "','" + textField9.getText() + "','" + textField10.getText() + "')";
                    transaction.stmt.executeUpdate(Statement);
                    proyection.setText("");
                    results.setText("The entry has been made.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        modifyScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Statement = "update schedules set" + textField11.getText() + " where courseid = " + textField12.getText() + " and secc = '" + textField13 + "and period = " + textField14 + "'";
                    if(specificDayCheckBox.isSelected())
                    {
                        Statement += " and weekd = '" + textField15.getText() + "'";
                    }
                    transaction.stmt.executeUpdate(Statement);
                    proyection.setText("");
                    results.setText("The Schedule has been modified.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Statement = "DELETE FROM schedules WHERE courseid = '" + textField16.getText() + "'";
                    transaction.stmt.executeUpdate(Statement);
                    proyection.setText("");
                    results.setText("The course has ben deleted.");
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
