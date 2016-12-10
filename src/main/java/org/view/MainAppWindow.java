package org.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.controler.Controller;
import org.enums.Language;

public class MainAppWindow {

    private JFrame frame;
    private JTextField txtTrainFilePath;
    private JTextField txtTrainOptions;

    private JButton btnSelectTrainFile;
    private JButton btnSelectTestFile;
    private JLabel lblTrainFile;
    private JLabel lblTestFile;
    private JComboBox<String> cbBoxClassifier;
    private JScrollPane scrollPaneOptions;
    private JTextPane textOptions;
    private JButton btnStart;
    private JMenuBar menuBar;
    private JMenu mnLanguage;
    private JRadioButtonMenuItem mntmEnglish;
    private JRadioButtonMenuItem mntmSpanish;
    private JTabbedPane tabbedPaneResults;
    private JScrollPane scrollPaneTrainResults;
    private JScrollPane scrollPaneTestResults;
    private JTextArea textAreaTrainResults;
    private JTextArea textAreaTestResults;
    private JCheckBox chckbxUseFreeling;
    private JCheckBox chckbxTrainByPhases;
    private JCheckBox chckbxTrain;
    private JLabel lblCrossvalidationFolds;
    private JLabel lblNgramMin;
    private JLabel lblNgramMax;
    private JTextField txtNGramMin;
    private JTextField txtNGramMax;

    private Controller controller;

    private final int TAB_ORDER_TRAIN_RESULTS = 0;
    private final int TAB_ORDER_TEST_RESULTS = 1;
    private JTextField txtTestFilePath;
    private JTextField txtCrossValidationFolds;
    
    private JTabbedPane tabbedPanePhases;
    
    private JPanel panelDirectClassification;
    private JLabel lblClassifierDirect;
    private JLabel lblParmetersDirect;
    
    private JPanel panelPhase1;
    private JComboBox<String> cbBoxPhase1Classifier1;
    private JTextField txtPhase1Classifier1Options;
    private JLabel lblClassifierPhase1;
    private JLabel lblParmetersPhase1;
    
    private JPanel panelPhase2;
    private JComboBox<String> cbBoxPhase2Classifier1;
    private JComboBox<String> cbBoxPhase2Classifier2;
    private JTextField txtPhase2Classifier1Options;
    private JTextField txtPhase2Classifier2Options;
    private JLabel lblClassifier1Phase2;
    private JLabel lblParmeters1Phase2;
    private JLabel lblClassifier2Phase2;
    private JLabel lblParmeters2Phase2;
    
    private JPanel panelPhase3;
    private JComboBox<String> cbBoxPhase3Classifier1;
    private JComboBox<String> cbBoxPhase3Classifier2;
    private JComboBox<String> cbBoxPhase3Classifier3;
    private JComboBox<String> cbBoxPhase3Classifier4;
    private JTextField txtPhase3Classifier1Options;
    private JTextField txtPhase3Classifier2Options;
    private JTextField txtPhase3Classifier3Options;
    private JTextField txtPhase3Classifier4Options;  
    private JLabel lblClassifier1Phase3;
    private JLabel lblParmeters1Phase3;
    private JLabel lblClassifier2Phase3;
    private JLabel lblParmeters2Phase3;
    private JLabel lblClassifier3Phase3;
    private JLabel lblParmeters3Phase3;
    private JLabel lblClassifier4Phase3;
    private JLabel lblParmeters4Phase3;
    
    private JPanel panelOptions;
    private JPanel panelNGram;
    
    public void setControler(Controller controler) {
        
        this.controller = controler;
    }
    
    public void setVisible() {
        
        frame.setVisible(true);
    }
    
    /**
     * Create the application.
     */
    public MainAppWindow() {
        
        initialize();
        
        initializeMenuBar();
        
        initializeFilesSection();
        initializeClassificationSection();
        initializeOptionsSection();
        initializeResultsSection();
    }
    
    private void initializeMenuBar() {
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnLanguage = new JMenu("_language");
        menuBar.add(mnLanguage);

        mntmEnglish = new JRadioButtonMenuItem("_english");
        mntmEnglish.setSelected(false);
        mntmEnglish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.changeLanguageTo(Language.ENGLISH);
                mntmEnglish.setSelected(true);
            }
        });
        mnLanguage.add(mntmEnglish);

        mntmSpanish = new JRadioButtonMenuItem("_spanish");
        mntmSpanish.setSelected(false);
        mntmSpanish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.changeLanguageTo(Language.SPANISH);
                mntmSpanish.setSelected(true);
            }
        });
        mnLanguage.add(mntmSpanish);
    }

    private void initializeFilesSection() {
        
        initializeTrainFileSection();
        initializeTestFileSection();
        
        initializeFileSectionActionListeners();
    }
    
    private void initializeTrainFileSection() {
        
        lblTrainFile = new JLabel("_train file:");
        lblTrainFile.setBounds(6, 24, 167, 16);
        frame.getContentPane().add(lblTrainFile);
        
        txtTrainFilePath = new JTextField();
        txtTrainFilePath.setText("/Users/martinmineo/Desarrollo/Tesis/workspace/ChatClassifier/datasets/Archivo de entrenamiento-chico.arff");
        txtTrainFilePath.setBounds(185, 19, 630, 26);
        frame.getContentPane().add(txtTrainFilePath);
        txtTrainFilePath.setColumns(10);
        
        btnSelectTrainFile = new JButton("_select");
        btnSelectTrainFile.setBounds(827, 19, 117, 29);
        
        frame.getContentPane().add(btnSelectTrainFile);
    }
    
    private void initializeTestFileSection() {
     
        txtTestFilePath = new JTextField();
        txtTestFilePath.setText("/Users/martinmineo/Desarrollo/Tesis/workspace/ChatClassifier/datasets/Archivo de clasificacion-nombres.arff");
        txtTestFilePath.setBounds(185, 57, 630, 26);
        frame.getContentPane().add(txtTestFilePath);
        txtTestFilePath.setColumns(10);

        lblTestFile = new JLabel("_test file:");
        lblTestFile.setBounds(6, 62, 167, 16);
        frame.getContentPane().add(lblTestFile);

        btnSelectTestFile = new JButton("_selectTest");
        
        btnSelectTestFile.setBounds(827, 57, 117, 29);
        frame.getContentPane().add(btnSelectTestFile);
    }
    
    private void initializeFileSectionActionListeners() {
        
        btnSelectTrainFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                fileChooser.addChoosableFileFilter(
                        new FileNameExtensionFilter("WEKA dataset", "arff", "xls", "xlsx", "csv"));
                fileChooser.setAcceptAllFileFilterUsed(true);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    txtTrainFilePath.setText(file.getPath());
                }
            }
        });    

        btnSelectTestFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                fileChooser.addChoosableFileFilter(
                        new FileNameExtensionFilter("WEKA dataset", "arff", "xls", "xlsx", "csv"));
                fileChooser.setAcceptAllFileFilterUsed(true);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    txtTestFilePath.setText(file.getPath());
                }
            }
        });
    }
    
    private void initializeClassificationSection() {
        
        initializeClassificationTabbedPanels();
        
        initializeDirectClassificationPane();
        initializePhase1ClassificationPane();
        initializePhase2ClassificationPane();
        initializePhase3ClassificationPane();
        
        initializeScrollPaneOptions();
        
        initializeClassificationSectionActionListeners();
    }
    
    private void initializeClassificationTabbedPanels() {
     
        tabbedPanePhases = new JTabbedPane(JTabbedPane.TOP);
        tabbedPanePhases.setBounds(6, 119, 713, 176);
        frame.getContentPane().add(tabbedPanePhases);
    }
    
    private void initializeDirectClassificationPane() {
        
        panelDirectClassification = new JPanel();
        tabbedPanePhases.addTab("Directo", null, panelDirectClassification, null);
        panelDirectClassification.setLayout(null);

        
        cbBoxClassifier = new JComboBox<String>();
        cbBoxClassifier.setBounds(130, 6, 200, 27);
        panelDirectClassification.add(cbBoxClassifier);
        
        txtTrainOptions = new JTextField();
        txtTrainOptions.setBounds(509, 6, 177, 26);
        panelDirectClassification.add(txtTrainOptions);
        txtTrainOptions.setColumns(10);

        lblClassifierDirect = new JLabel("_classifier:");
        lblClassifierDirect.setBounds(6, 10, 112, 16);
        panelDirectClassification.add(lblClassifierDirect);
        
        lblParmetersDirect = new JLabel("_parameters:");
        lblParmetersDirect.setBounds(413, 10, 84, 16);
        panelDirectClassification.add(lblParmetersDirect);
    }
    
    private void initializePhase1ClassificationPane() {
        
        panelPhase1 = new JPanel();
        tabbedPanePhases.addTab("Fase 1", null, panelPhase1, null);
        panelPhase1.setLayout(null);
        
        cbBoxPhase1Classifier1 = new JComboBox<String>();
        cbBoxPhase1Classifier1.setBounds(130, 6, 200, 27);
        panelPhase1.add(cbBoxPhase1Classifier1);
        
        txtPhase1Classifier1Options = new JTextField();
        txtPhase1Classifier1Options.setBounds(509, 6, 177, 26);
        panelPhase1.add(txtPhase1Classifier1Options);
        txtPhase1Classifier1Options.setColumns(10);
        
        lblClassifierPhase1 = new JLabel("_classifier:");
        lblClassifierPhase1.setBounds(6, 10, 112, 16);
        panelPhase1.add(lblClassifierPhase1);
        
        lblParmetersPhase1 = new JLabel("_parameters:");
        lblParmetersPhase1.setBounds(413, 10, 84, 16);
        panelPhase1.add(lblParmetersPhase1);
    }
    
    private void initializePhase2ClassificationPane() {
     
        panelPhase2 = new JPanel();
        tabbedPanePhases.addTab("Fase 2", null, panelPhase2, null);
        panelPhase2.setLayout(null);
        
        cbBoxPhase2Classifier1 = new JComboBox<String>();
        cbBoxPhase2Classifier1.setBounds(130, 6, 200, 27);
        panelPhase2.add(cbBoxPhase2Classifier1);
        
        cbBoxPhase2Classifier2 = new JComboBox<String>();
        cbBoxPhase2Classifier2.setBounds(130, 36, 200, 27);
        panelPhase2.add(cbBoxPhase2Classifier2);
        
        txtPhase2Classifier1Options = new JTextField();
        txtPhase2Classifier1Options.setBounds(509, 6, 177, 26);
        panelPhase2.add(txtPhase2Classifier1Options);
        txtPhase2Classifier1Options.setColumns(10);
        
        txtPhase2Classifier2Options = new JTextField();
        txtPhase2Classifier2Options.setBounds(509, 36, 177, 26);
        panelPhase2.add(txtPhase2Classifier2Options);
        txtPhase2Classifier2Options.setColumns(10);
        
        lblClassifier1Phase2 = new JLabel("_classifier:");
        lblClassifier1Phase2.setBounds(6, 10, 112, 16);
        panelPhase2.add(lblClassifier1Phase2);
        
        lblParmeters1Phase2 = new JLabel("_parameters:");
        lblParmeters1Phase2.setBounds(413, 10, 84, 16);
        panelPhase2.add(lblParmeters1Phase2);
        
        lblClassifier2Phase2 = new JLabel("_classifier:");
        lblClassifier2Phase2.setBounds(6, 40, 112, 16);
        panelPhase2.add(lblClassifier2Phase2);
        
        lblParmeters2Phase2 = new JLabel("_parameters:");
        lblParmeters2Phase2.setBounds(413, 40, 84, 16);
        panelPhase2.add(lblParmeters2Phase2);
    }
    
    private void initializePhase3ClassificationPane() {
        
        panelPhase3 = new JPanel();
        tabbedPanePhases.addTab("Fase 3", null, panelPhase3, null);
        panelPhase3.setLayout(null);
        
        cbBoxPhase3Classifier1 = new JComboBox<String>();
        cbBoxPhase3Classifier1.setBounds(130, 6, 200, 27);
        panelPhase3.add(cbBoxPhase3Classifier1);
        
        cbBoxPhase3Classifier2 = new JComboBox<String>();
        cbBoxPhase3Classifier2.setBounds(130, 36, 200, 27);
        panelPhase3.add(cbBoxPhase3Classifier2);
        
        cbBoxPhase3Classifier3 = new JComboBox<String>();
        cbBoxPhase3Classifier3.setBounds(130, 66, 200, 27);
        panelPhase3.add(cbBoxPhase3Classifier3);
        
        cbBoxPhase3Classifier4 = new JComboBox<String>();
        cbBoxPhase3Classifier4.setBounds(130, 96, 200, 27);
        panelPhase3.add(cbBoxPhase3Classifier4);
        
        txtPhase3Classifier1Options = new JTextField();
        txtPhase3Classifier1Options.setBounds(509, 6, 177, 26);
        panelPhase3.add(txtPhase3Classifier1Options);
        txtPhase3Classifier1Options.setColumns(10);
        
        txtPhase3Classifier2Options = new JTextField();
        txtPhase3Classifier2Options.setBounds(509, 36, 177, 26);
        panelPhase3.add(txtPhase3Classifier2Options);
        txtPhase3Classifier2Options.setColumns(10);
        
        txtPhase3Classifier3Options = new JTextField();
        txtPhase3Classifier3Options.setBounds(509, 66, 177, 26);
        panelPhase3.add(txtPhase3Classifier3Options);
        txtPhase3Classifier3Options.setColumns(10);
        
        txtPhase3Classifier4Options = new JTextField();
        txtPhase3Classifier4Options.setBounds(509, 96, 177, 26);
        panelPhase3.add(txtPhase3Classifier4Options);
        txtPhase3Classifier4Options.setColumns(10);
        
        lblClassifier1Phase3 = new JLabel("_classifier:");
        lblClassifier1Phase3.setBounds(6, 10, 112, 16);
        panelPhase3.add(lblClassifier1Phase3);
        
        lblParmeters1Phase3 = new JLabel("_parameters:");
        lblParmeters1Phase3.setBounds(413, 10, 84, 16);
        panelPhase3.add(lblParmeters1Phase3);
        
        lblClassifier2Phase3 = new JLabel("_classifier:");
        lblClassifier2Phase3.setBounds(6, 40, 112, 16);
        panelPhase3.add(lblClassifier2Phase3);
        
        lblParmeters2Phase3 = new JLabel("_parameters:");
        lblParmeters2Phase3.setBounds(413, 40, 84, 16);
        panelPhase3.add(lblParmeters2Phase3);
        
        lblClassifier3Phase3 = new JLabel("_classifier:");
        lblClassifier3Phase3.setBounds(6, 70, 112, 16);
        panelPhase3.add(lblClassifier3Phase3);
        
        lblParmeters3Phase3 = new JLabel("_parameters:");
        lblParmeters3Phase3.setBounds(413, 70, 84, 16);
        panelPhase3.add(lblParmeters3Phase3);
        
        lblClassifier4Phase3 = new JLabel("_classifier:");
        lblClassifier4Phase3.setBounds(6, 100, 112, 16);
        panelPhase3.add(lblClassifier4Phase3);
        
        lblParmeters4Phase3 = new JLabel("_parameters:");
        lblParmeters4Phase3.setBounds(413, 100, 84, 16);
        panelPhase3.add(lblParmeters4Phase3);
    }

    private void initializeScrollPaneOptions() {
        
        scrollPaneOptions = new JScrollPane();
        scrollPaneOptions.setBounds(731, 134, 207, 149);
        frame.getContentPane().add(scrollPaneOptions);
        
        textOptions = new JTextPane();
        scrollPaneOptions.setRowHeaderView(textOptions);
        textOptions.setEditable(false);
    }
    
    private void initializeClassificationSectionActionListeners() {
        
        cbBoxClassifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (cbBoxClassifier.getSelectedIndex() != 0) {
                    controller.setSelectedClassifier();
                    
                    StringBuilder options = controller.getOptions();

                    txtTrainOptions.setText(options.toString());
                    textOptions.setText(controller.getClassifierOptionDescription());
                    textOptions.setCaretPosition(0);
                    
                } else
                    textOptions.setText("");
                ;
            }
        });
    }
    
    private void initializeOptionsSection() {
        
        panelOptions = new JPanel();
        panelOptions.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelOptions.setBounds(16, 295, 799, 110);
        frame.getContentPane().add(panelOptions);
        panelOptions.setLayout(null);

        chckbxUseFreeling = new JCheckBox("_useFreeling");
        chckbxUseFreeling.setBounds(6, 15, 128, 23);
        panelOptions.add(chckbxUseFreeling);
        chckbxUseFreeling.setSelected(true);
        
        chckbxTrainByPhases = new JCheckBox("_trainByPhases");
        chckbxTrainByPhases.setBounds(6, 45, 217, 23);
        panelOptions.add(chckbxTrainByPhases);
        chckbxTrainByPhases.setSelected(true);
        
        lblCrossvalidationFolds = new JLabel("Cross-validation folds");
        lblCrossvalidationFolds.setBounds(300, 75, 142, 16);
        panelOptions.add(lblCrossvalidationFolds);
        
        txtCrossValidationFolds = new JTextField();
        txtCrossValidationFolds.setBounds(454, 70, 86, 26);
        panelOptions.add(txtCrossValidationFolds);
        txtCrossValidationFolds.setText("10");
        txtCrossValidationFolds.setColumns(10);
        
        initializeNGramPanel();
        
        chckbxTrain = new JCheckBox("_train");
        chckbxTrain.setBounds(6, 75, 128, 23);
        panelOptions.add(chckbxTrain);
        chckbxTrain.setSelected(true);
    }
    
    private void initializeNGramPanel() {
     
        panelNGram = new JPanel();
        panelNGram.setBounds(300, 19, 323, 46);
        panelOptions.add(panelNGram);
        panelNGram.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "NGram", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelNGram.setLayout(null);
        
        lblNgramMin = new JLabel("NGram min");
        lblNgramMax = new JLabel("NGram max");
        
        txtNGramMax = new JTextField();
        txtNGramMax.setText("3");
        txtNGramMax.setBounds(251, 16, 64, 26);
        panelNGram.add(txtNGramMax);
        txtNGramMax.setColumns(10);
        panelNGram.add(lblNgramMax);
        
        txtNGramMin = new JTextField();
        txtNGramMin.setText("1");
        txtNGramMin.setBounds(89, 16, 64, 26);
        panelNGram.add(txtNGramMin);
        txtNGramMin.setColumns(10);
        panelNGram.add(lblNgramMin);
        
        lblNgramMax.setBounds(165, 21, 74, 16);
        lblNgramMin.setBounds(6, 21, 81, 16);
    }
    
    private void initializeResultsSection() {
        
        tabbedPaneResults = new JTabbedPane(JTabbedPane.TOP);
        tabbedPaneResults.setBounds(6, 417, 932, 253);
        frame.getContentPane().add(tabbedPaneResults);

        scrollPaneTrainResults = new JScrollPane();
        tabbedPaneResults.addTab("_train", scrollPaneTrainResults);

        textAreaTrainResults = new JTextArea();
        textAreaTrainResults.setFont(new Font("Courier New", Font.PLAIN, 13));
        scrollPaneTrainResults.setViewportView(textAreaTrainResults);
        textAreaTrainResults.setEditable(false);

        scrollPaneTestResults = new JScrollPane();
        tabbedPaneResults.addTab("_test", scrollPaneTestResults);

        textAreaTestResults = new JTextArea();
        textAreaTestResults.setFont(new Font("Courier New", Font.PLAIN, 13));
        scrollPaneTestResults.setViewportView(textAreaTestResults);
        textAreaTestResults.setEditable(false);
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(50, 30, 950, 749);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnStart = new JButton("_start");
        btnStart.setBounds(827, 376, 117, 29);
        frame.getContentPane().add(btnStart);
    }

    public void setTabTrainResultsText(String text) {

        tabbedPaneResults.setTitleAt(TAB_ORDER_TRAIN_RESULTS, text);
    }

    public void setTabTestResultsText(String text) {

        tabbedPaneResults.setTitleAt(TAB_ORDER_TEST_RESULTS, text);
    }

    public void setMntmEnglishSelected(boolean b) {
        mntmEnglish.setSelected(b);
    }

    public void setMntmSpanishSelected(boolean b) {
        mntmSpanish.setSelected(b);
    }

    public void setBtnSelectTrainText(String text) {
        btnSelectTrainFile.setText(text);
    }

    public void setBtnSelectTestText(String text) {
        btnSelectTestFile.setText(text);
    }

    public void setLblTrainFileText(String text) {
        lblTrainFile.setText(text);
    }

    public void setLblTestFileText(String text) {
        lblTestFile.setText(text);
    }

    public void setCbBoxClassifierModel(String[] text) {
        cbBoxClassifier.setModel(new DefaultComboBoxModel<String>(text));
        
        cbBoxPhase1Classifier1.setModel(new DefaultComboBoxModel<String>(text));
        
        cbBoxPhase2Classifier1.setModel(new DefaultComboBoxModel<String>(text));
        cbBoxPhase2Classifier2.setModel(new DefaultComboBoxModel<String>(text));
        
        cbBoxPhase3Classifier1.setModel(new DefaultComboBoxModel<String>(text));
        cbBoxPhase3Classifier2.setModel(new DefaultComboBoxModel<String>(text));
        cbBoxPhase3Classifier3.setModel(new DefaultComboBoxModel<String>(text));
        cbBoxPhase3Classifier4.setModel(new DefaultComboBoxModel<String>(text));
    }

    public void setLblClassifierText(String text) {
        lblClassifierDirect.setText(text);
    }

    public void setLblParmetersText(String text) {
        lblParmetersDirect.setText(text);
    }

    public void setBtnStartText(String text) {
        btnStart.setText(text);
    }

    public void setMnLanguageText(String text) {
        mnLanguage.setText(text);
    }

    public void setMntmEnglishText(String text) {
        mntmEnglish.setText(text);
    }

    public void setMntmSpanishText(String text) {
        mntmSpanish.setText(text);
    }

    public void setTextUseFreeling(String text) {
        chckbxUseFreeling.setText(text);
    }
    
    public void setTextUsePhases(String text) {
        chckbxTrainByPhases.setText(text);
    }

    public String getTxtTrainFilePathText() {

        return txtTrainFilePath.getText();
    }

    public String getTxtTrainOptionsText() {

        return txtTrainOptions.getText();
    }

    public String getTxtTestFilePathText() {

        return txtTestFilePath.getText();
    }

    public int getSelectedResultsTab() {

        return tabbedPaneResults.getSelectedIndex();
    }

    public void setProcessingTextTrainResults(String processingText) {

        textAreaTrainResults.setCaretPosition(0);
        textAreaTrainResults.setText(processingText);
        textAreaTrainResults.update(textAreaTrainResults.getGraphics());
        textAreaTrainResults.setCaretPosition(0);
    }

    public void setProcessingTextTestResults(String processingText) {

        textAreaTestResults.setCaretPosition(0);
        textAreaTestResults.setText(processingText);
        textAreaTestResults.update(textAreaTestResults.getGraphics());
        textAreaTestResults.setCaretPosition(0);
    }
    
    public String getTextAreaTestResults() {
        
        return textAreaTrainResults.getText();
    }

    public boolean getUseFreeling() {

        return chckbxUseFreeling.isSelected();
    }
    
    public boolean getTrainByPhases() {
        
        return chckbxTrainByPhases.isSelected();
    }
    
    public String getCrossValidationFolds() {
        
        return txtCrossValidationFolds.getText();
    }
    
    public String getSelectedClassifier() {
        
        return cbBoxClassifier.getSelectedItem().toString();
    }
    
    public String getNGramMin() {
        
        return txtNGramMin.getText();
    }
    
    public String getNGramMax() {
        
        return txtNGramMax.getText();
    }
    
    public boolean isTrainSelected() {
        
        return chckbxTrain.isSelected();
    }
}
