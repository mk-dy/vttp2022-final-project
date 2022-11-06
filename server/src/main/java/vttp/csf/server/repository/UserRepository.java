package vttp.csf.server.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp.csf.server.repository.Queries.*;

import vttp.csf.server.models.User;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<User> getUserByEmail(String email) {
        SqlRowSet result = template.queryForRowSet(SQL_GET_USER_BY_EMAIL, email);

        if (!result.next()) {
            return Optional.empty();

        } else {
            User user = new User();
            user.setId(result.getString("user_id"));
            user.setFirstName(result.getString("user_first_name"));
            user.setEmail(email);
            user.setPassword(result.getString("user_password"));
            return Optional.of(user);
        }
    }

    public boolean createUser(User user) {
        int count = template.update(SQL_INSERT_USER,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword());
        
        return count == 1;
    }
}
