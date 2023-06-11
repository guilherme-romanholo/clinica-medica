package clinica.medica.documentos;

import clinica.medica.database.ReceitasSQL;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Receita {
    Medico medico;
    Paciente paciente;
    String nomeDoRemedio;
    Date dataReceita;
    String detalhes;
    int id;

    public Receita(String medico, String paciente, String nomeDoRemedio, Date dataReceita, String detalhes) {
        this.medico = new Medico(medico);
        this.paciente = new Paciente(paciente);
        this.nomeDoRemedio = nomeDoRemedio;
        this.dataReceita = dataReceita;
        this.detalhes = detalhes;
    }
    public Receita(int id) {
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = ReceitasSQL.selectAllFromReceitas(id, connection.getConn());

        try {
            this.medico = new Medico(rs.getString("medico"));
            this.paciente = new Paciente(rs.getString("paciente"));
            this.nomeDoRemedio = rs.getString("nomeDoRemedio");
            this.dataReceita = rs.getDate("data");
            this.detalhes = rs.getString("detalhes");
            this.id = id;
        } catch (SQLException e) {
            System.out.println("Não foi possível criar a receita.");
        }

        connection.desconectar();
    }

    public String getDetalhes() {
        return detalhes;
    }

    public Date getDataReceita() {
        return dataReceita;
    }

    public String getNomeDoRemedio() {
        return nomeDoRemedio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public int getId() {
        return id;
    }
}
