package clinica.medica.documentos;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Date;

public class Laudo {
    private Exame exame;
    private String clinica;
    private Medico medicoSolicitante;
    private Paciente paciente;
    private Date data;
    private String conteudo;
    
}
