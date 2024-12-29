package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.UserView;

public class UserController {

    private UserView view;
    private UserMapper mapper;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = view.getNameInput();
            String email = view.getEmailInput();

            if (!nama.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(nama);
                user.setEmail(email);
                
                mapper.insertUser(session, user);

                JOptionPane.showMessageDialog(view, "Pengguna berhasil ditambahkan!");
            } else {
                JOptionPane.showMessageDialog(view, "Harap isi semua kolom.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers(session);
            
            String[] userArray = users.stream()
                .map(u -> u.getName() + " (" + u.getEmail() + ")")
                .toArray(String[]::new);

            view.setUserList(userArray);
        }
    }
}
