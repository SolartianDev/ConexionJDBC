package conexionjdbc; // 1. Declaración del paquete del programa.

import java.sql.Connection; // 2. Importar las clases necesarias de JDBC.
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {

     static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresa"; // 3. URL de conexión a la base de datos.
     static final String USUARIO = "root"; // 4. Nombre de usuario de la base de datos.
     static final String PASSWORD = "user"; // 5. Contraseña de la base de datos.
     
     public static void main(String[] args) throws SQLException {
        
         try (Connection conexion = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD)) {
             // 6. Establecer una conexión a la base de datos utilizando la información de URL, usuario y contraseña.

             System.out.println("Conexión establecida con éxito"); // 7. Imprimir un mensaje de éxito en la conexión.
         } catch (SQLException e) {
             System.out.println("Error en la conexión a la DB: " + e.getMessage()); // 8. Manejo de excepciones en caso de error de SQL.
         }
    }
}

