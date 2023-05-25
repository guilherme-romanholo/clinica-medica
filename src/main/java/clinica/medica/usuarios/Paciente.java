package clinica.medica.usuarios;

import clinica.medica.documentos.Exame;

import java.util.ArrayList;

public class Paciente extends Usuario{
    private String endereco;
    private ArrayList<Exame> exames;
    private String[] doencas;
}
