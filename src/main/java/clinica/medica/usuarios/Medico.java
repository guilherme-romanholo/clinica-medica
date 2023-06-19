package clinica.medica.usuarios;

import clinica.medica.consultas.Consulta;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.database.UsuariosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Medico extends Usuario {
    private String areaAtuacao;
    private String CRM;
    private LinkedList<Consulta> consultas;

    public Medico(String medicoCpf) {
        super(medicoCpf);

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = UsuariosSQL.selectAllFromMedico(medicoCpf, connection.getConn());

        try {
            this.areaAtuacao = rs.getString("areaAtuacao");
            this.CRM = rs.getString("CRM");
            this.consultas = null;
        } catch (SQLException e) {
            System.out.println("Não foi possível instanciar o medico.");
        }

        connection.desconectar();
    }

    public String getAreaAtuacao() {
        return this.areaAtuacao;
    }
    
    public String getCRM(){
        return this.CRM;
    }
}
