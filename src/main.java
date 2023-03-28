import javax.swing.*;

public class main {

    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RentCalculator calculatorApp = new RentCalculator();
                calculatorApp.setVisible(true);

            }
        });


    }
}
