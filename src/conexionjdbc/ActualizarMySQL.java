package conexionjdbc; // 1. Declaración del paquete del programa.

import java.sql.Connection; // 2. Importar las clases necesarias de JDBC.
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;

public class ActualizarMySQL {

     static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresa"; // 3. URL de conexión a la base de datos.
     static final String USUARIO = "root"; // 4. Nombre de usuario de la base de datos.
     static final String PASSWORD = "user"; // 5. Contraseña de la base de datos.
     
     public static void main(String[] args) throws SQLException {
        
         try (Connection conexion = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD)) {
             // 6. Establecer una conexión a la base de datos utilizando la información de URL, usuario y contraseña.

             System.out.println("Conexión establecida con éxito"); // 7. Imprimir mensaje de éxito en la conexión.
             
             // Crear la Query que queremos ejecutar
             String ConsultaSQL = "UPDATE empleados SET salario=?, cargo =? WHERE idEmpleados = 1"; // 8. Consulta SQL para actualizar registros en la tabla empleados.
             
             // Crear un objeto PreparedStatement para ejecutar consultas
             PreparedStatement preparedStatement = conexion.prepareStatement(ConsultaSQL); // 9. Crear un objeto PreparedStatement para ejecutar la consulta SQL.

             // Ejecutar la consulta
             preparedStatement.setDouble(1, 123437.345); // 10. Asignar nuevos valores para el salario y el cargo.
             preparedStatement.setString(2, "Desarrollador Senior");

             // Ejecutar la actualización
             int filasAfectadas = preparedStatement.executeUpdate(); // 11. Ejecutar la actualización y obtener el número de filas afectadas.

             if (filasAfectadas > 0) {
                 System.out.println("Registros actualizados con éxito "); // 12. Verificar si se actualizaron registros con éxito y mostrar un mensaje.
             } else {
                 System.out.println("No se pudieron actualizar los registros "); // 13. En caso de fallo, mostrar un mensaje de error.
             }

             // Cerrar recurso
             preparedStatement.close(); // 14. Cerrar el recurso PreparedStatement.
         } catch (SQLException e) {
             System.out.println("Error al actualizar en la DB: " + e.getMessage()); // 15. Manejo de excepciones en caso de error de SQL.
         }
    }
}


