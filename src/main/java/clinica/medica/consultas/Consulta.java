package clinica.medica.consultas;

import clinica.medica.database.ConsultaSQL;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consulta {
    private int id;
    private String data;
    private Medico medico;
    private Paciente paciente;
    private boolean realizada;
    private String descricao;
    private String horario;

    public Consulta(int id) {
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = ConsultaSQL.selectAllFromConsulta(id, connection.getConn());

        try {
            this.medico = new Medico(rs.getString("medico"));
            this.setPaciente(new Paciente(rs.getString("paciente")));
            this.setData(rs.getString("data"));
            this.setRealizada(rs.getBoolean("realizada"));
            this.setDescricao(rs.getString("descricao"));
            this.setHorario(rs.getString("horario"));
            this.setId(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possível criar a consulta.");
        }

        connection.desconectar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
