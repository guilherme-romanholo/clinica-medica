package clinica.medica.usuarios;

import clinica.medica.consultas.Consulta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Medico extends Usuario {
    private String areaAtuacao;
    private LinkedList<Consulta> consultas;
    
    public Medico(ResultSet rs){
        super(rs);
        try{
        this.areaAtuacao = rs.getString("areaAtuacao");
       // this.consultas = rs.getObject("consultas", Consulta);
        }catch(SQLException e){
            System.out.println("Medico não encontrado, tente novamente!");
        }
    }

}
