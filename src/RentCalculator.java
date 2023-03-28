import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class RentCalculator extends JFrame {

    private static final int DEFAULT_PROPERTY_AMOUNT = 1300;
    private static final int DEFAULT_RENT_AMOUNT = 250000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 1000000000;
    private static final int STEP_SIZE = 1;

    private JPanel mainPanel;
    private JButton calculateButton;
    private JLabel monthlyRentLabel;
    private JLabel propertyLabel;
    private JSpinner propertyAmount;
    private JSpinner rentAmount;
    private JFormattedTextField result;
    private JLabel rentalAmountPerYearText;


    public RentCalculator() {
        add(mainPanel);
        setUI();


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateRent();
            }
        });
    }
    public void setUI(){
        setSize(687, 100);
        setTitle("Annual Rent Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("assets/rentrate-icon.png");
        Image scaledImage = icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        setIconImage(scaledImage);

        JLabel headerLabel = new JLabel(icon);

        getContentPane().add(headerLabel,BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setLayout();

        setupPropertyAmountSpinner();
        setupRentAmountSpinner();


        setupResultTextField();
    }
    private void calculateRent() {
        int propertyAmountValue = (int) propertyAmount.getValue();
        int rentAmountValue = (int) rentAmount.getValue();
        double resultValue = ((propertyAmountValue * 12) / (double) rentAmountValue) * 100;
        rentalAmountPerYearText.setVisible(true);
        result.setVisible(true);
        result.setValue(resultValue);

    }
    public void setLayout(){
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridwidth = 1;
        mainPanel.add(propertyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(propertyAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(monthlyRentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(rentAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(rentalAmountPerYearText, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(result, gbc);

        SpinnerModel propertyModel = new SpinnerNumberModel(DEFAULT_PROPERTY_AMOUNT, MIN_VALUE, MAX_VALUE, STEP_SIZE);
        propertyAmount.setModel(propertyModel);

        SpinnerModel amountModel = new SpinnerNumberModel(DEFAULT_RENT_AMOUNT, MIN_VALUE, MAX_VALUE, STEP_SIZE);
        rentAmount.setModel(amountModel);

        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setGroupingUsed(false);
        result.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(format)));
        result.setBorder(BorderFactory.createEmptyBorder());

        add(mainPanel);
        setTitle("Annual rent calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupPropertyAmountSpinner() {
        SpinnerModel propertyModel = new SpinnerNumberModel(1300, 0, 1000000000, 1);
        propertyAmount.setModel(propertyModel);
    }

    private void setupRentAmountSpinner() {
        SpinnerModel amountModel = new SpinnerNumberModel(250000, 0, 1000000000, 1);
        rentAmount.setModel(amountModel);
    }

    private void setupResultTextField() {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setGroupingUsed(false);
        result.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(format)));
        result.setBorder(BorderFactory.createEmptyBorder());
        result.setVisible(false);

        rentalAmountPerYearText.setVisible(false);
    }
}
