package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class V1_0_1__Insert_Users_With_Hashed_Passwords extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Connection connection = context.getConnection();

        insertUser(connection,1L,"user1", "user1@example.com", "password1",  passwordEncoder);
        insertUser(connection,2L ,"user2", "user2@example.com", "password2",  passwordEncoder);
        insertUser(connection, 3L,"user3", "user3@example.com", "password3",  passwordEncoder);
        insertUser(connection, 4L,"user4", "user4@example.com", "password4",  passwordEncoder);
        insertUser(connection, 5L,"user5", "user5@example.com", "password5",  passwordEncoder);
        insertUser(connection, 6L,"user6", "user6@example.com", "password6",  passwordEncoder);
        insertUser(connection, 7L,"user7", "user7@example.com", "password7",  passwordEncoder);
    }

    private void insertUser(Connection connection, Long id, String username, String email, String rawPassword, PasswordEncoder passwordEncoder) throws SQLException {
        String hashedPassword = passwordEncoder.encode(rawPassword);

        try (PreparedStatement insertUser = connection.prepareStatement(
                "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)")) {
            insertUser.setLong(1, id);
            insertUser.setString(2, username);
            insertUser.setString(3, email);
            insertUser.setString(4, hashedPassword);
            insertUser.executeUpdate();
        }
    }

}
