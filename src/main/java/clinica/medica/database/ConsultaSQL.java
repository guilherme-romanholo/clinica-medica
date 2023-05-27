
package clinica.medica.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaSQL {
    public static ResultSet consultarTudo(String tabela){
        String query = "SELECT * FROM ?";
        ResultSet rs = null;
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar("/<default package>/clinica.db");
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, tabela);
            rs = pstmt.executeQuery();
        }catch(SQLException e){
            System.out.println("bruh");
        }
        connection.desconectar();
        return rs;
    }
}
