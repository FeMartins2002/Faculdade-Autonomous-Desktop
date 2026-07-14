package br.com.view.windows;

import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PolicyWindow extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton acceptButton;
    private JPanel titlePanel, messagePanel, buttonPanel;
    private JScrollPane scrollPane;
    private JTextArea policyArea;

    public PolicyWindow() {
        setTitle("Politica de Privacidade");
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
        titleLabel = new LabelBuilder("Politica de Privacidade")
                .size(250, 40)
                .textColor(Color.WHITE)
                .fontSize(20)
                .opaque(true)
                .build();
    }

    private void buildMessage() {
        String policyText = getPrivacyPolicy();
        policyArea = new JTextArea(policyText);

        policyArea.setEditable(false);
        policyArea.setLineWrap(true);
        policyArea.setWrapStyleWord(true);
        policyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        policyArea.setMargin(new Insets(10, 10, 10, 10));

        scrollPane = new JScrollPane(policyArea);
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

    private String getPrivacyPolicy() {
        return "POLÍTICA DE PRIVACIDADE - AUTONOMOUS\n\n"

                + "O Autonomous valoriza a privacidade e a proteção "
                + "dos dados utilizados em sua plataforma. Esta Política "
                + "de Privacidade descreve como as informações são "
                + "coletadas, armazenadas e utilizadas dentro do sistema.\n\n"

                + "1. SOBRE O AUTONOMOUS\n\n"
                + "O Autonomous é um sistema desenvolvido para auxiliar "
                + "na gestão de freelancers, escalas, horários, pagamentos "
                + "e organização operacional de um Sorveteria.\n\n"

                + "2. DADOS COLETADOS\n\n"
                + "O sistema poderá armazenar informações fornecidas "
                + "pelos usuários, como:\n"
                + "- Nome;\n"
                + "- Telefone;\n"
                + "- E-mail;\n"
                + "- Horários de trabalho;\n"
                + "- Registros de escalas;\n"
                + "- Informações administrativas relacionadas aos freelancers.\n\n"

                + "3. FINALIDADE DAS INFORMAÇÕES\n\n"
                + "Os dados cadastrados no Autonomous são utilizados "
                + "exclusivamente para fins administrativos e operacionais, "
                + "incluindo controle de escalas, organização de equipes, "
                + "acompanhamento de horários e gestão interna.\n\n"

                + "4. COMPARTILHAMENTO DE DADOS\n\n"
                + "As informações registradas no Autonomous não devem "
                + "ser compartilhadas com terceiros sem autorização, "
                + "exceto quando necessário para cumprimento de obrigações "
                + "legais ou administrativas.\n\n"

                + "5. SEGURANÇA DAS INFORMAÇÕES\n\n"
                + "O Autonomous adota medidas para proteger os dados "
                + "armazenados contra acessos não autorizados, alterações "
                + "indevidas ou perda de informações. Ainda assim, nenhum "
                + "sistema é totalmente livre de riscos.\n\n"

                + "6. RESPONSABILIDADE DO USUÁRIO\n\n"
                + "Cada usuário é responsável pela segurança de sua "
                + "conta e senha de acesso. O compartilhamento de "
                + "credenciais não é recomendado.\n\n"

                + "7. ARMAZENAMENTO DOS DADOS\n\n"
                + "As informações poderão permanecer armazenadas pelo "
                + "tempo necessário para funcionamento e organização "
                + "das atividades internas relacionadas ao sistema.\n\n"

                + "8. DIREITOS DOS USUÁRIOS\n\n"
                + "Os usuários poderão solicitar atualização ou correção "
                + "de informações cadastradas sempre que necessário.\n\n"

                + "9. ALTERAÇÕES NA POLÍTICA\n\n"
                + "Esta Política de Privacidade poderá ser atualizada "
                + "futuramente para refletir melhorias, alterações "
                + "funcionais ou adequações administrativas.\n\n"

                + "10. CONSIDERAÇÕES FINAIS\n\n"
                + "O Autonomous possui finalidade acadêmica e educacional, "
                + "sendo desenvolvido como Projeto Integrador voltado "
                + "à gestão de freelancers de uma Sorveteria.\n\n"

                + "Última atualização: Julho de 2026.";
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == acceptButton) {
            dispose();
        }
    }
}
