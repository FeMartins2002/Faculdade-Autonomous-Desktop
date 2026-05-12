package br.com.view.windows;

import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TermsWindow extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton acceptButton;
    private JPanel titlePanel, messagePanel, buttonPanel;
    private JScrollPane scrollPane;
    private JTextArea termsArea;

    public TermsWindow() {
        setTitle("Termos de Uso");
        setSize(500, 500);
        getContentPane().setBackground(new Color(21, 32, 43));
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setResizable(false);

        buildTitle();
        buildMessage();
        buildButton();

        buildTitlePanel();
        buildMessagePanel();
        buildButtonPanel();

        organizeComponents();
        setVisible(true);
    }

    private void buildTitle() {
        titleLabel = new LabelBuilder("Termos de Uso do Sistema")
                .size(250, 40)
                .textColor(Color.WHITE)
                .fontSize(20)
                .opaque(true)
                .build();
    }

    private void buildMessage() {
        String termsText = getMessage();
        termsArea = new JTextArea(termsText);

        termsArea.setEditable(false);
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        termsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        termsArea.setMargin(new Insets(10, 10, 10, 10));

        scrollPane = new JScrollPane(termsArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void buildButton() {
        acceptButton = new ButtonBuilder("Ok")
                .background(new Color(59, 111, 146))
                .size(100, 30)
                .textColor(Color.WHITE)
                .tooltip("Ok")
                .fontSize(15)
                .opaque(true)
                .build();
        acceptButton.addActionListener(this);
    }

    private void buildTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(21, 32, 43));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.add(titleLabel);
    }

    private void buildMessagePanel() {
        messagePanel = new JPanel(new GridLayout(1, 1));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        messagePanel.setBackground(new Color(21, 32, 43));
        messagePanel.add(scrollPane);
    }

    private void buildButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.setBackground(new Color(21, 32, 43));
        buttonPanel.add(acceptButton);
    }

    private void organizeComponents() {
        add(titlePanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String getMessage() {
        return  "Bem-vindo ao sistema de gerenciamento de "
                + "de freelancers para sorveteria bachir. Ao acessar ou utilizar este "
                + "software, o usuário declara estar ciente e de acordo com os "
                + "termos descritos abaixo.\n\n"

                + "1. FINALIDADE DO SISTEMA\n\n"
                + "Este sistema foi desenvolvido exclusivamente para "
                + "auxiliar gestores no controle "
                + "de funcionários freelancers, escalas de trabalho, horários, "
                + "turnos e organização da sorveteria.\n\n"

                + "2. RESPONSABILIDADE DO USUÁRIO\n\n"
                + "O usuário é totalmente responsável pelas informações "
                + "cadastradas no sistema, incluindo nomes, horários, valores "
                + "de pagamento, dados de contato e registros de escalas.\n\n"

                + "3. VERACIDADE DAS INFORMAÇÕES\n\n"
                + "Todos os dados inseridos devem ser verdadeiros, atualizados "
                + "e utilizados apenas para fins relacionados a sorveteria.\n\n"

                + "4. USO ADEQUADO DA PLATAFORMA\n\n"
                + "É proibida a utilização do sistema para práticas ilegais, "
                + "fraudulentas ou que possam comprometer a integridade dos "
                + "dados armazenados.\n\n"

                + "5. CONTROLE DE ACESSO\n\n"
                + "Cada usuário é responsável pela segurança de sua conta e "
                + "de sua senha de acesso. O compartilhamento de credenciais "
                + "não é recomendado.\n\n"

                + "6. DISPONIBILIDADE DO SISTEMA\n\n"
                + "O sistema pode sofrer interrupções temporárias para "
                + "manutenção, atualização ou correção de falhas sem aviso "
                + "prévio.\n\n"

                + "7. LIMITAÇÃO DE RESPONSABILIDADE\n\n"
                + "Os desenvolvedores não se responsabilizam por perdas de "
                + "dados causadas por mau uso, falhas externas, desligamentos "
                + "inesperados ou problemas de hardware.\n\n"

                + "8. PRIVACIDADE DOS DADOS\n\n"
                + "As informações registradas no sistema devem ser utilizadas "
                + "somente para controle administrativo interno, "
                + "não sendo recomendada a inserção de dados sensíveis.\n\n"

                + "9. RESTRIÇÕES DE USO\n\n"
                + "O usuário não poderá tentar modificar, copiar, desmontar "
                + "ou explorar vulnerabilidades do sistema.\n\n"

                + "10. CADASTRO DE FREELANCERS\n\n"
                + "Os registros de freelancers devem conter apenas informações "
                + "necessárias para organização das escalas, pagamentos e "
                + "controle de presença.\n\n"

                + "11. ORGANIZAÇÃO DAS ESCALAS\n\n"
                + "O sistema permite criar, editar e remover escalas de "
                + "trabalho conforme a necessidade operacional da sorveteria.\n\n"

                + "12. PAGAMENTOS E VALORES\n\n"
                + "Os valores cadastrados para diárias, horas extras ou "
                + "pagamentos possuem caráter apenas demonstrativo no contexto "
                + "acadêmico do projeto.\n\n"

                + "13. ATUALIZAÇÕES DO SISTEMA\n\n"
                + "Novas funcionalidades poderão ser adicionadas futuramente, "
                + "assim como correções de bugs e melhorias de desempenho.\n\n"

                + "14. PENALIDADES\n\n"
                + "O uso inadequado do sistema poderá resultar em suspensão "
                + "temporária ou permanente do acesso.\n\n"

                + "15. ACEITE DOS TERMOS\n\n"
                + "Ao clicar em 'Ok' ou continuar utilizando o sistema, "
                + "o usuário confirma que leu, compreendeu e concorda com "
                + "todos os termos apresentados.\n\n"

                + "16. CONSIDERAÇÕES FINAIS\n\n"
                + "Este projeto possui finalidade educacional e foi desenvolvido "
                + "como projeto integrador, 5º semestre pelo grupo Senacos.\n\n"

                + "Última atualização: Maio de 2026.";
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == acceptButton) {
            dispose();
        }
    }
}
