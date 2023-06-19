package clinica.medica.gui.laudo;

import clinica.medica.database.PacientesSQL;
import clinica.medica.documentos.Laudo;
import clinica.medica.gui.recursos.ImpressaoUI;
import clinica.medica.gui.recursos.RecursosUI;
import clinica.medica.gui.telas.TelaLogadaUI;
import clinica.medica.usuarios.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * Classe que possui os métodos usados na criação da interface da parte de laudos do paciente
 */
public class LaudosPacienteUI {
    /**
     * Método que montará o painel contendo todos os laudos de um paciente
     * @param pacienteLogado paciente logado no sistema
     * @param telaLogada tela principal que conterá o painel das consultas marcadas
     * @return retorna o painel das consultas marcadas para adicioná-lo na tela principal
     */
    public static JPanel telaVerificarLaudo(Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Laudos");
        ArrayList<Laudo> laudos = PacientesSQL.verificarLaudos(pacienteLogado.getCpf());
        int i = 0;
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);


        JButton imprimirLaudoButton = new JButton("Visualizar laudo");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaLaudo = new String[laudos.size()];

        for (Laudo ex : laudos) {
            listaLaudo[i] = "Laudo - " + ex.getPaciente().getNome() + " - " + ex.getExame().getTipo() + " - " + ex.getExame().getId();
            i++;
        }


        JList<String> list = new JList<>(listaLaudo);
        JScrollPane scrollPanel = new JScrollPane(list);
        scrollPanel.setPreferredSize(new Dimension(800, 600));

        constraints.gridy = 1;
        painelLaudo.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelLaudo.add(imprimirLaudoButton, constraints);

        /**
         * criação de uma nova classe para tratar os eventos do botão Imprimir laudo
         */
        imprimirLaudoButton.addMouseListener(new MouseAdapter() {
            /**
             * Método que pega os dados do laudo selecionado e imprime este
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String laudoSelecionado = list.getSelectedValue();
                String[] elementos = laudoSelecionado.split("-");
                int id = Integer.parseInt(elementos[3].strip());
                Laudo laudo = new Laudo(id);
                ImpressaoUI.imprimirDocumento(laudo);
            }
        });
        painelPrincipal.add(infoPanel,BorderLayout.NORTH);
        painelPrincipal.add(painelLaudo,BorderLayout.CENTER);
        return painelPrincipal;
    }
}
