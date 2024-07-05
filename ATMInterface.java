import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class ATMInterface {
    JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;
    private BankAccount account;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ATMInterface window = new ATMInterface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ATMInterface() {
        account = new BankAccount(1000.0); // Initial balance
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ATM Interface");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("ATM Machine");
        titleLabel.setBounds(150, 20, 100, 25);
        frame.getContentPane().add(titleLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 60, 80, 25);
        frame.getContentPane().add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 60, 150, 25);
        frame.getContentPane().add(amountField);
        amountField.setColumns(10);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 100, 100, 25);
        frame.getContentPane().add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(200, 100, 100, 25);
        frame.getContentPane().add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(120, 150, 150, 25);
        frame.getContentPane().add(balanceButton);

        balanceLabel = new JLabel("Balance: ₹ " + account.getBalance());
        balanceLabel.setBounds(50, 200, 300, 25);
        frame.getContentPane().add(balanceLabel);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(frame, "Withdrawal successful.");
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        updateBalance();
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            JOptionPane.showMessageDialog(frame, "Deposit successful.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        updateBalance();
    }

    private void checkBalance() {
        updateBalance();
        JOptionPane.showMessageDialog(frame, "Your current balance is: ₹ " + account.getBalance());
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹ " + account.getBalance());
        amountField.setText("");
    }
}
