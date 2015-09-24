package uk.ac.shef.dcs.jate.v2.algorithm;

import uk.ac.shef.dcs.jate.v2.JATEException;
import uk.ac.shef.dcs.jate.v2.feature.AbstractFeature;
import uk.ac.shef.dcs.jate.v2.feature.FrequencyTermBased;
import uk.ac.shef.dcs.jate.v2.model.JATETerm;
import uk.ac.shef.dcs.jate.v2.model.TermInfo;

import java.util.*;

/**
 * Created by zqz on 19/09/2015.
 */
public class TTF extends Algorithm {

    @Override
    public List<JATETerm> execute(Set<String> candidates) throws JATEException{
        candidates.remove("");

        AbstractFeature feature = features.get(FrequencyTermBased.class.getName());
        validateFeature(feature, FrequencyTermBased.class);
        FrequencyTermBased fFeature = (FrequencyTermBased) feature;
        boolean collectInfo=termInfoCollector!=null;
        List<JATETerm> result = new ArrayList<>();
        for(String tString: candidates){
            JATETerm term = new JATETerm(tString, (double)fFeature.getTTF(tString));

            if(collectInfo){
                TermInfo termInfo =termInfoCollector.collect(tString);
                term.setTermInfo(termInfo);
            }
            result.add(term);
        }
        Collections.sort(result);
        return result;
    }
}
