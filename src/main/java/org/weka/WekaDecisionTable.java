package org.weka;

import weka.classifiers.rules.DecisionTable;
import weka.filters.Filter;

public class WekaDecisionTable extends Weka {
    
    private static final String DECISION_TABLE_PROPERTY_NAME = "DecisionTable";

    public WekaDecisionTable(int folds, int nGramMin, int nGramMax) {
        super(new DecisionTable(), folds, nGramMin, nGramMax);
    }
    
    @Override
    public String getClassifierOptionDescription() {
        return properties.getProperty(DECISION_TABLE_PROPERTY_NAME);
    }
    
    @Override
    protected boolean hasSpecialFilter() {
        return false;
    }
    
    @Override
    protected Filter getSpecialFilter() {
         return null;
    }

}