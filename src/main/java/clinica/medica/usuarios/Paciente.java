package clinica.medica.usuarios;

import clinica.medica.documentos.Exame;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Paciente extends Usuario{
    private String endereco;
    private ArrayList<Exame> exames;
    private String[] doencas;
    private String sexo;
    private int idade;
    private double altura;
    private double peso;
    
    public Paciente(ResultSet rs){
        super(rs);
        try{
        this.endereco = rs.getString("endereco");
        //this.exames = rs.getArray("exames");
        //this.doencas = rs.getString("doenças");
        this.sexo = rs.getString("sexo");
        this.idade = rs.getInt("idade");
        this.altura = rs.getDouble("altura");
        this.peso = rs.getDouble("peso");
        }catch(SQLException e){
            System.out.println("Usuários não encontrado, tente novamente!");
        }
    }
    
}
