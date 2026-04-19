package br.com.view.panels;

import br.com.view.builders.ButtonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private JButton scales, freelancers, stores;
    private MainPanel panel;

    public MenuPanel(MainPanel panel) {
        this.panel = panel;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buildButtons();
    }

    private void buildButtons() {
        buildButtonScales();
        buildButtonFreelancers();
        buildButtonStores();
    }

    private void buildButtonScales() {
        scales = new ButtonBuilder("Escalas")
                .build();
        scales.addActionListener(this);
        add(scales);
    }

    private void buildButtonFreelancers() {
        freelancers = new ButtonBuilder("Freelancers")
                .build();
        freelancers.addActionListener(this);
        add(freelancers);
    }

    private void buildButtonStores() {
        stores = new ButtonBuilder("Stores")
                .build();
        stores.addActionListener(this);
        add(stores);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == scales) {
            panel.showScales();
        }

        if(click.getSource() == freelancers) {
            panel.showFreelancers();
        }

        if(click.getSource() == stores) {
            panel.showStores();
        }
    }
}
