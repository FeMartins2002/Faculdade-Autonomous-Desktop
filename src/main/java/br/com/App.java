package br.com;

import br.com.configurations.ManagerConfig;
import br.com.view.windows.LoginWindow;

public class App {
    private static ManagerConfig configurations;

    public static void main(String[] args) {
        configurations = new ManagerConfig();

        LoginWindow loginWindow = new LoginWindow(configurations.getController());
        loginWindow.setVisible(true);
    }
}
