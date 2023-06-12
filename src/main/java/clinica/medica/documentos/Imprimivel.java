package clinica.medica.documentos;

import java.sql.Date;

public interface Imprimivel {

    String imprimeTipo();
    String imprimeInfo();
    String imprimeNomePaciente();
    String imprimeIdade();
    String imprimeSexo();
    String imprimeData();
    String imprimeComentarios();
    String imprimeNomeMedico();
    String imprimeCrmAtuacaoMedico();
}
