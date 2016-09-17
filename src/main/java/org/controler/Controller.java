package org.controler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.commons.Constants;
import org.commons.PropertiesManager;
import org.enums.Classifier;
import org.enums.Language;
import org.processDataset.DirectProcessing;
import org.processDataset.PhasesProcessing;
import org.processDataset.ProcessDataset;
import org.view.MainAppWindow;
//import org.weka.MachineLearningClassifierTechnique;
import org.weka.Weka;
import org.weka.WekaJ48;
import org.weka.WekaNaiveBayes;
import org.weka.WekaSMO;


public class Controller {
    
    private Weka weka;
    private MainAppWindow mainWindowView;

    // Properties
    private PropertiesManager languageProp;

    // Language
    private Language selectedLanguage;

    // Language items
    private static final String MAIN_VIEW_MENU_LANGUAGE = "mainViewMenuLanguage";
    private static final String MAIN_VIEW_MENU_LANGUAGE_ENGLISH = "mainViewMenuLanguageEnglish";
    private static final String MAIN_VIEW_MENU_LANGUAGE_SPANISH = "mainViewMenuLanguageSpanish";
    private static final String MAIN_VIEW_TRAIN_FILE = "mainViewTrainFile";
    private static final String MAIN_VIEW_TEST_FILE = "mainViewTestFile";
    private static final String MAIN_VIEW_CLASSIFIER = "mainViewClassifier";
    private static final String MAIN_VIEW_PARAMETER = "mainViewParameter";
    private static final String MAIN_VIEW_CBBOX_OPTION = "mainViewCbBoxOption";
    private static final String MAIN_VIEW_CLASSIFIER_ERROR_MESSAGE = "mainViewClassifierErrorMessage";
    private static final String MAIN_VIEW_BTN_SELECT = "mainViewBtnSelect";
    private static final String MAIN_VIEW_BTN_START = "mainViewBtnStart";
    private static final String MAIN_VIEW_TAB_TRAIN_RESULTS = "mainViewTabTrainResults";
    private static final String MAIN_VIEW_TAB_TEST_RESULTS = "mainViewTabTestResults";
    private static final String MAIN_VIEW_PROCESSING = "mainViewProcessing";
    private static final String MAIN_VIEW_USE_FREELING = "mainViewUseFreeling";
    private static final String MAIN_VIEW_USE_PHASES = "mainViewUsePhases";

    public Controller(MainAppWindow mainWindowView) {

        this.mainWindowView = mainWindowView;

        selectedLanguage = Language.getSelectedLanguage();

        languageProp = new PropertiesManager(Constants.RESOURCES + "/" + selectedLanguage.getFilename());
    }

    public void initializeView() {

        mainWindowView.setMnLanguageText(languageProp.getProperty(MAIN_VIEW_MENU_LANGUAGE));
        mainWindowView.setMntmEnglishText(languageProp.getProperty(MAIN_VIEW_MENU_LANGUAGE_ENGLISH));
        mainWindowView.setMntmSpanishText(languageProp.getProperty(MAIN_VIEW_MENU_LANGUAGE_SPANISH));
        mainWindowView.setLblTrainFileText(languageProp.getProperty(MAIN_VIEW_TRAIN_FILE));
        mainWindowView.setLblTestFileText(languageProp.getProperty(MAIN_VIEW_TEST_FILE));
        mainWindowView.setLblClassifierText(languageProp.getProperty(MAIN_VIEW_CLASSIFIER));
        mainWindowView.setLblParmetersText(languageProp.getProperty(MAIN_VIEW_PARAMETER));
        mainWindowView.setCbBoxClassifier(getCbBoxClassifierContent());
        mainWindowView.setLblClassifierErrorMessageText(languageProp.getProperty(MAIN_VIEW_CLASSIFIER_ERROR_MESSAGE));
        mainWindowView.setBtnSelectTrainText(languageProp.getProperty(MAIN_VIEW_BTN_SELECT));
        mainWindowView.setBtnSelectTestText(languageProp.getProperty(MAIN_VIEW_BTN_SELECT));
        mainWindowView.setBtnStartText(languageProp.getProperty(MAIN_VIEW_BTN_START));
        mainWindowView.setTabTrainResultsText(languageProp.getProperty(MAIN_VIEW_TAB_TRAIN_RESULTS));
        mainWindowView.setTabTestResultsText(languageProp.getProperty(MAIN_VIEW_TAB_TEST_RESULTS));
        mainWindowView.setTextUseFreeling(languageProp.getProperty(MAIN_VIEW_USE_FREELING));
        mainWindowView.setTextUsePhases(languageProp.getProperty(MAIN_VIEW_USE_PHASES));

        switch (selectedLanguage) {
        case ENGLISH:
            mainWindowView.setMntmEnglishSelected(true);
            break;
        case SPANISH:
            mainWindowView.setMntmSpanishSelected(true);
            break;
        default:
            mainWindowView.setMntmSpanishSelected(true);
            break;
        }

        mainWindowView.setVisible();
    }

    public void changeLanguageTo(Language language) {

        if (selectedLanguage != language) {
            switch (language) {
            case ENGLISH:
                selectedLanguage = Language.ENGLISH;
                mainWindowView.setMntmEnglishSelected(true);
                mainWindowView.setMntmSpanishSelected(false);
                break;
            case SPANISH:
                selectedLanguage = Language.SPANISH;
                mainWindowView.setMntmEnglishSelected(false);
                mainWindowView.setMntmSpanishSelected(true);
                break;
            default:
                selectedLanguage = Language.SPANISH;
                mainWindowView.setMntmEnglishSelected(true);
                mainWindowView.setMntmSpanishSelected(false);
                break;
            }

            languageProp = new PropertiesManager(Constants.RESOURCES + "/" + selectedLanguage.getFilename());
            initializeView();
        }
    }

    public String[] getCbBoxClassifierContent() {

        List<String> content = new ArrayList<String>();
        content.add(languageProp.getProperty(MAIN_VIEW_CBBOX_OPTION));

        Classifier classifiers[] = Classifier.values();

        for (Classifier classifier : classifiers) {
            content.add(classifier.getDescription());
        }

        String[] simpleArray = new String[content.size()];
        content.toArray(simpleArray);
        return simpleArray;
    }

    public void setSelectedClassifier() {
       
        int folds = new Integer(mainWindowView.getCrossValidationFolds()).intValue();
        int nGramMin = new Integer(mainWindowView.getNGramMin()).intValue();
        int nGramMax = new Integer (mainWindowView.getNGramMax()).intValue();
        
        switch (Classifier.getClassifier(mainWindowView.getSelectedClassifier())) {
        case J48:
            weka = new WekaJ48(folds, nGramMin, nGramMax);
            break;
        case NAIVE_BAYES:
            weka = new WekaNaiveBayes(folds, nGramMin, nGramMax);
            break;
        case SMO:
            weka = new WekaSMO(folds, nGramMin, nGramMax);
            break;
        default:
            weka = new WekaSMO(folds, nGramMin, nGramMax);
        }
    }

    public StringBuilder getOptions() {

        String[] options = weka.getClassifierOptions();

        StringBuilder builder = new StringBuilder();
        for (String s : options) {
            if (s.contains("weka."))
                s = "\"" + s + "\"";
            builder.append(s + " ");
        }
        return builder;
    }

    public String getClassifierOptionDescription() {

        return weka.getClassifierOptionDescription();//model.getValidOptions();
    }

    public void btnStartPresed() {
        
        boolean useFreeling = mainWindowView.getUseFreeling();
        
        ProcessDataset process;
        String wekaClassifierOptions = mainWindowView.getTxtTrainOptionsText();
        setSelectedClassifier();
        weka.setClassifierOptions(wekaClassifierOptions);
        
        if (mainWindowView.getTrainByPhases()) {
            process =  new PhasesProcessing(weka, useFreeling);
        }
        else
            process = new DirectProcessing(weka, useFreeling);
        
        String trainFileName = mainWindowView.getTxtTrainFilePathText(); 
        String testFileName = mainWindowView.getTxtTestFilePathText();

        mainWindowView.setProcessingTextTrainResults(languageProp.getProperty(MAIN_VIEW_PROCESSING));
        process.train(mainWindowView.getTxtTrainFilePathText());
        
        mainWindowView.setProcessingTextTestResults(languageProp.getProperty(MAIN_VIEW_PROCESSING));
        process.classify(testFileName, trainFileName);
        
        String classificationResults = process.getClassificationResults();
        
        String options = "Opciones seleccionadas\n======================\n" + "Clasificador: " + mainWindowView.getSelectedClassifier()
        + '\n' + "Parámetros: " + mainWindowView.getTxtTrainOptionsText() + '\n' + "Cross-validation folds: "
        + mainWindowView.getCrossValidationFolds() + '\n' + "Entrenar en fases: " + mainWindowView.getTrainByPhases() + '\n'
        + "Usar FreeLing: " + mainWindowView.getUseFreeling()  + '\n'
        + "NGramMin: " + mainWindowView.getNGramMin() + ", NGramMax: " + mainWindowView.getNGramMax()
        + "\n===============================================================\n\n";
        
        mainWindowView.setProcessingTextTrainResults(options + process.getTrainingResults());
        mainWindowView.setProcessingTextTestResults(classificationResults);
        
        try (PrintWriter out = new PrintWriter("results/(" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ") resultado-"
                + mainWindowView.getSelectedClassifier() + "-folds_" + mainWindowView.getCrossValidationFolds() + "-fases_"
                + mainWindowView.getTrainByPhases() + "-freeling_" + mainWindowView.getUseFreeling() + "-NGram(" + mainWindowView.getNGramMin()
                + ", " + mainWindowView.getNGramMax() + ").txt")) {
            out.println(mainWindowView.getTextAreaTestResults());
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}