package clinica.medica.documentos;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Date;

public class Exame {
    private String tipo;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Date data;
    private String comentario;
}
