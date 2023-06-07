package clinica.medica.documentos;

import clinica.medica.database.ExamesSQL;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exame {
    private int id;
    private String tipo;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Date data;
    private String comentario;

    public Exame(int id) {
        SQLiteConnection connection = new SQLiteConnection();

        connection.conectar();

        ResultSet rs = ExamesSQL.selectAllFromExames(id, connection.getConn());

        try {
            this.id = rs.getInt("id");
            this.tipo = rs.getString("tipo");
            this.paciente = new Paciente(rs.getString("paciente"));
            this.medicoSolicitante = new Medico(rs.getString("medicoSolicitante"));
            this.data = rs.getDate("data");
            this.comentario = rs.getString("comentario");
        } catch (SQLException e) {

        }

        connection.desconectar();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the medicoSolicitante
     */
    public Medico getMedicoSolicitante() {
        return medicoSolicitante;
    }

    /**
     * @param medicoSolicitante the medicoSolicitante to set
     */
    public void setMedicoSolicitante(Medico medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
