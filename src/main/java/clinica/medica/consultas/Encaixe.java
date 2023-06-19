package clinica.medica.consultas;

import clinica.medica.database.ConsultaSQL;
import clinica.medica.database.SQLiteConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Encaixe extends Consulta {
   private String motivoEmergencia;

    public Encaixe(int id) {
        super(id);
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = ConsultaSQL.selectAllFromConsulta(id, connection.getConn());

        try {
            this.setMotivoEmergencia(rs.getString("motivoEmergencia"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possível criar o encaixe.");
        }

        connection.desconectar();
    }

    public String getMotivoEmergencia() {
        return motivoEmergencia;
    }

    public void setMotivoEmergencia(String motivoEmergencia) {
        this.motivoEmergencia = motivoEmergencia;
    }
}
