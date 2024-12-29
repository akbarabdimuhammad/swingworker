package model;

import org.apache.ibatis.session.SqlSession;
import java.util.List;
import model.User;

public interface UserMapper {
    void insertUser(SqlSession session, User user);
    List<User> getAllUsers(SqlSession session);
}
