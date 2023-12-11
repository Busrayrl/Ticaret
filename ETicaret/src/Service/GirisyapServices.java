package Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnet.DatabaseManager;
import dto.UyeolRequest;

public final class GirisyapServices {

    public void girisyap(String email, String password) throws Exception {
    	try (Connection connection = DatabaseManager.connect()) {
            String sql = "SELECT * FROM tbl_kullanici WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                       
                        System.out.println("Giriş başarılı");
                    } else {
                        throw new Exception("Kullanıcı adı veya şifre hatalı");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata yönetimi 
    }}

    public static List<UyeolRequest> getAllUsersFromDatabase() {
        List<UyeolRequest> userList = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            String sql = "SELECT * FROM tbl_kullanici";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                    	UyeolRequest user =new UyeolRequest.Builder()
                                .name(resultSet.getString("name"))
                                .surname(resultSet.getString("surname"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                .build();
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata yönetimi
        }
        return userList;
   
    
    }
        } 
   
