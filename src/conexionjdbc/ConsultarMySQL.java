package conexionjdbc; // 1. Declaración del paquete del programa.

import java.sql.Connection; // 2. Importar las clases necesarias de JDBC.
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultarMySQL {

     static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresa"; // 3. URL de conexión a la base de datos.
     static final String USUARIO = "root"; // 4. Nombre de usuario de la base de datos.
     static final String PASSWORD = "user"; // 5. Contraseña de la base de datos.
     
     public static void main(String[] args) throws SQLException {
        
         try(Connection conexion = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD);) {
             // 6. Establecer una conexión a la base de datos utilizando la información de URL, usuario y contraseña.

             System.out.println("Conexión establecida con éxito"); // 7. Imprimir mensaje de éxito en la conexión.
             
             // Crear un objeto Statement para ejecutar consultas SQL
             Statement statement = conexion.createStatement(); // 8. Crear un objeto Statement para ejecutar consultas.

             // Crear la Query que queremos ejecutar
             String ConsultaSQL = "SELECT * FROM empresa.empleados"; // 9. Consulta SQL para seleccionar todos los registros de la tabla empleados.

             // Ejecutar la consulta y obtener los resultados
             ResultSet resultSet = statement.executeQuery(ConsultaSQL); // 10. Ejecutar la consulta SQL y obtener un conjunto de resultados (ResultSet).

             // Recorrer los resultados y mostrar la información
             while (resultSet.next()) { // 11. Recorrer cada fila del conjunto de resultados.
                 int id = resultSet.getInt("idEmpleados"); // 12. Obtener valores de las columnas de la fila.
                 String nombres = resultSet.getString("nombres");
                 String apellidos = resultSet.getString("apellidos");
                 Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                 double salario = resultSet.getDouble("salario");
                 String cargo = resultSet.getString("cargo");

                 System.out.println("ID de Empleado: " + id
                         + ", Nombres: " + nombres
                         + ", Apellidos: " + apellidos
                         + ", Fecha de Nacimiento: " + fechaNacimiento
                         + ", Salario: " + salario
                         + ", Cargo: " + cargo);
             }
             
             // Cerrar los recursos
             resultSet.close(); // 13. Cerrar el ResultSet.
             statement.close(); // 14. Cerrar el Statement.
         } catch (SQLException e) {
             System.out.println("Error en la conexión a la DB: " + e.getMessage()); // 15. Manejo de excepciones en caso de error de SQL.
         }
    }
}
