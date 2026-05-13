package br.com.view.panels;

import br.com.configurations.FreelancerConfig;
import br.com.configurations.ScaleConfig;
import br.com.configurations.StoreConfig;
import br.com.view.cards.FreelancersCard;
import br.com.view.cards.ScalesCard;
import br.com.view.cards.StoresCard;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JPanel scalesPanel, freelancersPanel, storesPanel;
    private static FreelancerConfig freelancerConfig;
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
    }

    private void buildPanels() {
        buildFreelancersPanel();
        buildScalesPanel();
        buildStoresPanel();
    }

    private void buildFreelancersPanel() {
        freelancersPanel = new FreelancersCard(freelancerConfig.getController());
        add(freelancersPanel, "freelancersCard");
    }

    private void buildScalesPanel() {
        scalesPanel = new ScalesCard(scaleConfig.getController(), freelancerConfig.getController(), storeConfig.getController());
        add(scalesPanel, "scalesCard");
    }

    private void buildStoresPanel() {
        storesPanel = new StoresCard(storeConfig.getController());
        add(storesPanel, "storesCard");
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
}
