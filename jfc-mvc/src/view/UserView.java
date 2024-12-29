package view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class UserView extends JFrame {
    // Menambahkan 'final' pada variabel yang tidak akan diubah
    private final JTextField nameField;
    private final JTextField emailField;
    private final JButton addUserButton;
    private final JButton refreshButton;
    private final JList<String> userList;

    public UserView() {
        // Inisialisasi komponen UI
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        addUserButton = new JButton("Tambah Pengguna");
        refreshButton = new JButton("Segarkan Daftar");
        userList = new JList<>();

        // Menambahkan komponen ke frame (tampilan)
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(new JLabel("Nama:"));
        this.add(nameField);
        this.add(new JLabel("Email:"));
        this.add(emailField);
        this.add(addUserButton);
        this.add(refreshButton);
        this.add(new JScrollPane(userList));
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Getter untuk input nama dan email
    public String getNameInput() {
        return nameField.getText();
    }

    public String getEmailInput() {
        return emailField.getText();
    }

    // Menambahkan listener ke tombol
    public void addAddUserListener(ActionListener listener) {
        addUserButton.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    // Mengatur daftar pengguna di JList
    public void setUserList(String[] userArray) {
        userList.setListData(userArray);
    }
}
