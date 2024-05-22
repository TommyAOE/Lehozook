package App;

import javax.swing.*;

import App.Model.Model;
import App.View.GameView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu extends JFrame {

    //attributes for textfields
    JTextField PlayerNumber;
    JTextField ProfNumber;
    JTextField RoundNumber;

    public Menu() {

        // Setting a title for the window
        setTitle("SlideRule");
        //1600*1000 es kell majd legyen de nem fert ki a laptopomra
        setSize(1600, 1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting a background (source:https://stackoverflow.com/questions/1064977/setting-background-images-in-jframe)
        try {//itt majd be kell allitani a kep helyet,csak at kell irni a fajl helyet
            this.setContentPane(new JLabel(new ImageIcon(("SlideRule/resources/menulogo.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);//mennyi hely legyen az egyes "dolgok kozott"

        // Adding Title
        //gridbagconstraints(https://www.youtube.com/watch?v=g2vDARb7gx8)
        JLabel titleLabel = new JLabel("SlideRule");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;//mennyire legyen szetnyujtva a tobbihez kepest szelessegben
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // PLayer Count Textblock
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel playerCountLabel=new JLabel("Player Count: ");
        playerCountLabel.setForeground(Color.WHITE);
        add(playerCountLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        PlayerNumber = new JTextField(10);
        add(PlayerNumber, gbc);

        // Professor Count textBlock
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel professorCountLabel=new JLabel("Professor Count: ");
        professorCountLabel.setForeground(Color.WHITE);
        add(professorCountLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        ProfNumber = new JTextField(10);
        add(ProfNumber, gbc);

        // Max Rounds Textblock
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel maxRoundsLabel=new JLabel("Max Rounds: ");
        maxRoundsLabel.setForeground(Color.WHITE);
        add(maxRoundsLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        RoundNumber = new JTextField(10);
        add(RoundNumber, gbc);

        // Play, Help and Exit buttons
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton playButton = new JButton("Play");
        playButton.setBackground(Color.WHITE);
        add(playButton, gbc);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxPlayers = 5, maxProfessors = 5, maxRounds = 10;

                if(!PlayerNumber.getText().equals(""))
                    maxPlayers=Integer.parseInt(PlayerNumber.getText());
                
                if(!ProfNumber.getText().equals(""))
                    maxProfessors=Integer.parseInt(ProfNumber.getText()); 

                if(!RoundNumber.getText().equals(""))
                    maxRounds=Integer.parseInt(RoundNumber.getText());

                Model model = new Model(maxPlayers, maxProfessors, maxRounds);

                GameView gv = new GameView(model);
                gv.setVisible(true);
                
                
                Menu.this.setVisible(false);
            }
        });

        gbc.gridy = 5;
        JButton helpButton = new JButton("Help");
        helpButton.setBackground(Color.WHITE);
        add(helpButton, gbc);
        helpButton.addActionListener(new helpButtonFunc());

        gbc.gridy = 6;
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.WHITE);
        add(exitButton, gbc);
        exitButton.addActionListener(new ExitButtonFunc());
       

        setVisible(true);
    }


     //Help Button 
     class helpButtonFunc implements ActionListener
     {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> data=new ArrayList<>();
            JTextArea rulesArea = new JTextArea(30, 90);
            JScrollPane rulesJScrollPane;

            //reading the rules from a text file 
                try {//itt majd csak be kell allitani a txt uj helyet
                    rulesArea.read(new BufferedReader(new FileReader("SlideRule/resources/jatekleiras.txt")), null);

              } catch (Exception a){

                   a.printStackTrace();
                }

                rulesArea.setEditable(false);
                rulesJScrollPane = new JScrollPane(rulesArea);

                JFrame frame=new JFrame("Rules");
                frame.setSize(1600, 1000);

                //setting a background the same way as in the menu frame
                try {//itt majd be kell allitani a kep helyet,csak at kell irni a fajl helyet
                    frame.setContentPane(new JLabel(new ImageIcon(("SlideRule/resources/menulogo.png"))));
                } catch (Exception k) {
                    k.printStackTrace();
                }

                frame.setLayout(new GridBagLayout());
                GridBagConstraints gbc =new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);

                //title for this one too
                JLabel titleLabel = new JLabel("SlideRule");
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //alignment
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;//mennyire legyen szetnyujtva a tobbihez kepest szelessegben
                gbc.anchor = GridBagConstraints.CENTER;
                frame.add(titleLabel, gbc);

                //alignment
                gbc.gridx=2;
                gbc.gridy=0;
                gbc.anchor=GridBagConstraints.CENTER;
                
                //Adding scrollpane for the frame
                frame.add(rulesJScrollPane,gbc);
                frame.setResizable(false);
                frame.setVisible(true);  
            }
    }


     // Exit Button
    class ExitButtonFunc implements ActionListener
     {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

     }
        

}

