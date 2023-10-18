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

public class EliminarMySQL {

     static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresa"; // 3. URL de conexión a la base de datos.
     static final String USUARIO = "root"; // 4. Nombre de usuario de la base de datos.
     static final String PASSWORD = "user"; // 5. Contraseña de la base de datos.
     
     public static void main(String[] args) throws SQLException {
        
         try(Connection conexion = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD);) {
             // 6. Establecer una conexión a la base de datos utilizando la información de URL, usuario y contraseña.

             System.out.println("Conexión establecida con éxito"); // 7. Imprimir mensaje de éxito en la conexión.
             
             String ConsultaSQL = "DELETE FROM empleados WHERE idEmpleados = ?"; // 8. Consulta SQL para eliminar registros en la tabla empleados.
             
             PreparedStatement preparedStatement = conexion.prepareStatement(ConsultaSQL); // 9. Crear un objeto PreparedStatement para ejecutar la consulta SQL.
                
             int empleadoEliminado = 5; // 10. Definir el ID del empleado que se eliminará.

             // Ejecutar la consulta
             preparedStatement.setDouble(1, empleadoEliminado); // 11. Asignar el valor del ID del empleado al parámetro de la consulta SQL.

             // Ejecutar la eliminación
             int filasAfectadas = preparedStatement.executeUpdate(); // 12. Ejecutar la eliminación y obtener el número de filas afectadas.

             if (filasAfectadas > 0) {
                 System.out.println("Registros eliminados con éxito "); // 13. Verificar si se eliminaron registros con éxito y mostrar un mensaje.
             } else {
                 System.out.println("No se pudieron eliminar los registros "); // 14. En caso de fallo, mostrar un mensaje de error.
             }

             // Cerrar recurso
             preparedStatement.close(); // 15. Cerrar el recurso PreparedStatement.
         } catch (SQLException e) {
             System.out.println("Error al eliminar en la DB: " + e.getMessage()); // 16. Manejo de excepciones en caso de error de SQL.
         }
    }
}


