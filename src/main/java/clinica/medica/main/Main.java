package clinica.medica.main;

import clinica.medica.gui.LoginUI;
import clinica.medica.usuarios.Medico;

public class Main {
    public static void main(String[] args) {
        Medico medico = new Medico("42421342123");
        System.out.println(medico.getNome());
        System.out.println(medico.getCpf());
        System.out.println(medico.getAreaAtuacao());
        LoginUI.chamarTela();
    }
}
