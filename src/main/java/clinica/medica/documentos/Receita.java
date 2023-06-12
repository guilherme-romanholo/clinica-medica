package clinica.medica.documentos;

import clinica.medica.database.ReceitasSQL;
import clinica.medica.database.SQLiteConnection;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Receita implements Imprimivel {
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

    // --------------------- Métodos da Interface ---------------------
    @Override
    public String imprimeTipo() {
        return "Receita";
    }
    @Override
    public String imprimeInfo() {
        return "Remédio: " + nomeDoRemedio;
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
        return "Data da receita: " + dataReceita;
    }
    @Override
    public String imprimeComentarios() {
        return "Informações:\n" + detalhes;
    }
    @Override
    public String imprimeNomeMedico() {
        return "Dr. " + medico.getNome();
    }
    @Override
    public String imprimeCrmAtuacaoMedico() {
        return "CRM " + medico.getCRM() + " - " + medico.getAreaAtuacao();
    }

    // --------------------- Getters e Setters ---------------------
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
