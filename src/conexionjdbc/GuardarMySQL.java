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

public class GuardarMySQL {

     static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresa"; // 3. URL de conexión a la base de datos.
     static final String USUARIO = "root"; // 4. Nombre de usuario de la base de datos.
     static final String PASSWORD = "user"; // 5. Contraseña de la base de datos.
     
     public static void main(String[] args) throws SQLException {
        
         try(Connection conexion = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD);) {
             // 6. Establecer una conexión a la base de datos utilizando la información de URL, usuario y contraseña.

             System.out.println("Conexión establecida con éxito"); // 7. Imprimir mensaje de éxito en la conexión.
             
             String ConsultaSQL = "INSERT INTO empleados(idEmpleados,nombres,apellidos,fechaNacimiento,salario,cargo) VALUES (?,?,?,?,?,?)"; // 8. Consulta SQL para insertar datos en la tabla empleados.
             
             PreparedStatement preparedStatement = conexion.prepareStatement(ConsultaSQL); // 9. Crear un objeto PreparedStatement para ejecutar la consulta SQL.
             
             LocalDate fechaNacimiento = LocalDate.of(1988, Month.JULY, 15); // 10. Crear una fecha de nacimiento utilizando la API de fecha y hora de Java.
             Date fechaDate = Date.valueOf(fechaNacimiento); // 11. Convertir la fecha en un objeto Date compatible con JDBC.
             
             preparedStatement.setInt(1, 5); // 12. Asignar valores a los parámetros de la consulta SQL.
             preparedStatement.setString(2, "Sofia Valeria");
             preparedStatement.setString(3, "Morales Fuentes");
             preparedStatement.setDate(4, fechaDate);
             preparedStatement.setDouble(5, 437.345);
             preparedStatement.setString(6, "Community Manager");
             
             int filasAfectadas = preparedStatement.executeUpdate(); // 13. Ejecutar la inserción y obtener el número de filas afectadas.
             
             if(filasAfectadas > 0){
                 System.out.println("Registros insertados con éxito "); // 14. Verificar si se insertaron registros con éxito y mostrar un mensaje.
             }else{
                 System.out.println("No se pudieron insertar los registros "); // 15. En caso de fallo, mostrar un mensaje de error.
             }

             preparedStatement.close(); // 16. Cerrar el recurso PreparedStatement.
         } catch(SQLException e){
             System.out.println("Error al insertar en la DB: " + e.getMessage()); // 17. Manejo de excepciones en caso de error de SQL.
         }
    }
}

