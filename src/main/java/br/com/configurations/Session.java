package br.com.configurations;

import br.com.entities.Manager;

public class Session {
    private static Manager managerLogged;

    public static void setManager(Manager manager) {
        managerLogged = manager;
    }

    public static Manager getManagerLogged() {
        return managerLogged;
    }

    public static void logout() {
        managerLogged = null;
    }
}
