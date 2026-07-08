package br.com;

import br.com.configurations.ManagerConfig;
import br.com.view.windows.LoginWindow;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;

public class App {
    private static ManagerConfig configurations;

    public static void main(String[] args) {
        configurations = new ManagerConfig();

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            new LoginWindow(configurations.getController()).setVisible(true);
        });
    }
}
