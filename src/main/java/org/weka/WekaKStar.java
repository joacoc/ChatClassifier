package org.weka;

import weka.classifiers.lazy.KStar;
import weka.filters.Filter;

public class WekaKStar extends Weka {

    private static final String KSTAR_PROPERTY_NAME = "KStar";
    
    public WekaKStar(int folds, int nGramMin, int nGramMax) {
        super(new KStar(), folds, nGramMin, nGramMax);
    }

    @Override
    public String getClassifierOptionDescription() {
        return properties.getProperty(KSTAR_PROPERTY_NAME);
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
