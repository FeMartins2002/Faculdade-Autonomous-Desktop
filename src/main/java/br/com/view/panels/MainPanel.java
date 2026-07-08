package br.com.view.panels;

import br.com.configurations.DashboardConfig;
import br.com.configurations.FreelancerConfig;
import br.com.configurations.ScaleConfig;
import br.com.configurations.StoreConfig;
import br.com.view.cards.DashboardCard;
import br.com.view.cards.FreelancersCard;
import br.com.view.cards.HistoryCard;
import br.com.view.cards.ScalesCard;
import br.com.view.cards.StoresCard;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private JPanel scalesPanel, freelancersPanel, storesPanel, dashboardPanel, historyCard;
    private static FreelancerConfig freelancerConfig;
    private static DashboardConfig dashboardConfig;
    private static ScaleConfig scaleConfig;
    private static StoreConfig storeConfig;
    private CardLayout cardLayout;

    public MainPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        initializeControllers();
        buildPanels();
    }

    private void initializeControllers() {
        freelancerConfig = new FreelancerConfig();
        scaleConfig = new ScaleConfig();
        storeConfig = new StoreConfig();
        dashboardConfig = new DashboardConfig();
    }

    private void buildPanels() {
        buildScalesPanel();
        buildStoresPanel();
        buildFreelancersPanel();
        buildDashboardPanel();
        buildHistoryPanel();
    }

    private void buildFreelancersPanel() {
        freelancersPanel = new FreelancersCard(freelancerConfig.getController());
        add(freelancersPanel, "freelancersCard");
    }

    private void buildScalesPanel() {
        scalesPanel = new ScalesCard(scaleConfig.getController());
        add(scalesPanel, "scalesCard");
    }

    private void buildStoresPanel() {
        storesPanel = new StoresCard(storeConfig.getController());
        add(storesPanel, "storesCard");
    }

    private void buildDashboardPanel() {
        dashboardPanel = new DashboardCard(dashboardConfig.getController());
        add(dashboardPanel, "dashboardCard");
    }

    private void buildHistoryPanel() {
        historyCard =  new HistoryCard(scaleConfig.getController());
        add(historyCard, "historyCard");
    }

    public void showFreelancers() {
        cardLayout.show(this, "freelancersCard");
    }

    public void showScales() {
        cardLayout.show(this, "scalesCard");
    }

    public void showStores() {
        cardLayout.show(this, "storesCard");
    }

    public void showDashboard() {
        cardLayout.show(this, "dashboardCard");
    }

    public void showHistory() {
        cardLayout.show(this, "historyCard");
    }
}
