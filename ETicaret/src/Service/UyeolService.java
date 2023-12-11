package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnet.DatabaseManager;
import dto.UyeolRequest;

public class UyeolService {
public void uyeol (UyeolRequest request) throws SQLException{
	execute(request);
}
private void execute (UyeolRequest request ) throws SQLException{
	//database bağlantısı.
	try (Connection connection = DatabaseManager.connect()) {
        // Email kontrolü yap
        checkEmailUsed(request.getEmail(), connection);

        // SQL sorgusuyla müşteriyi ekleme.
        String insertQuery = "INSERT INTO tbl_kullanici (name, surname, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getSurname());
            preparedStatement.setString(3, request.getEmail());
            preparedStatement.setString(4, request.getPassword());

            preparedStatement.executeUpdate();
        }
    }
}private void checkEmailUsed(String email, Connection connection) throws SQLException {
    // Veritabanında email kontrolü yap
    String query = "SELECT email FROM tbl_kullanici WHERE email = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, email);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                throw new IllegalArgumentException("This email is already used");
            }
        }
    }
}
}
