package clinica.medica.documentos;

import clinica.medica.database.LaudosSQL;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Laudo implements Imprimivel {
    private Exame exame;
    private String clinica;
    private Medico medicoSolicitante;
    private Paciente paciente;
    private Date data;
    private String conteudo;

    public Laudo(int id) {
        SQLiteConnection connection = new SQLiteConnection();

        connection.conectar();

        ResultSet rs = LaudosSQL.selectAllFromLaudos(id, connection.getConn());

        try {
            this.setExame(new Exame(rs.getInt("exame")));
            this.setClinica("Clinica UNESP");
            this.setPaciente(new Paciente(rs.getString("paciente")));
            this.setMedicoSolicitante(new Medico(rs.getString("medicoSolicitante")));
            this.setData(rs.getDate("data"));
            this.setConteudo(rs.getString("conteudo"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();
    }

    // --------------------- Métodos da Interface ---------------------
    @Override
    public String imprimeTipo() {
        return "Laudo";
    }
    @Override
    public String imprimeInfo() {
        return "Laudo referente ao exame: " + exame.getTipo() + " - id: " + exame.getId();
    }
    @Override
    public String imprimeNomePaciente() {
        return "Nome: " + paciente.getNome();
    }
    @Override
    public String imprimeIdade() {
        return "Idade: " + paciente.getIdade();
    }
    @Override
    public String imprimeSexo() {
        return "Sexo: " + paciente.getSexo();
    }
    @Override
    public String imprimeData() {
        return "Data do laudo: " + data;
    }
    @Override
    public String imprimeComentarios() {
        return "Informações:\n" + conteudo;
    }
    @Override
    public String imprimeNomeMedico() {
        return "Dr. " + medicoSolicitante.getNome();
    }
    @Override
    public String imprimeCrmAtuacaoMedico() {
        return "CRM " + medicoSolicitante.getCRM() + " - " + medicoSolicitante.getAreaAtuacao();
    }

    // --------------------- Getters e Setters ---------------------
    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public Medico getMedicoSolicitante() {
        return medicoSolicitante;
    }

    public void setMedicoSolicitante(Medico medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
