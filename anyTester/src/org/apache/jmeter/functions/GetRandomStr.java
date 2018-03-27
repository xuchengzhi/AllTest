 package org.apache.jmeter.functions;  
  
  
import org.apache.jmeter.engine.util.CompoundVariable;  
import org.apache.jmeter.samplers.SampleResult;  
import org.apache.jmeter.samplers.Sampler;  
import org.apache.jmeter.util.JMeterUtils;  
  
import java.util.*;  
import java.util.Random;  
  
/** 
 * Function to return a random string you given 
 * Pass parameters split by comma 
 *  
 * @since 2.10 
 */  
public class GetRandomStr extends AbstractFunction {  
	private static final List<String> desc = new LinkedList();  
	  
    private static final String KEY = "__getrandomstr"; //$NON-NLS-1$  
  
    static {  
        desc.add(JMeterUtils.getResString("getrandomstr_string")); //$NON-NLS-1$  
    }  
  
    private Object[] values;  
    Random ra;  
    int rannum;  
  
    public GetRandomStr() {  
    }  
  
    /** {@inheritDoc} */  
    @Override  
    public String execute(SampleResult previousResult, Sampler currentSampler)  
            throws InvalidVariableException {  
        String randomString = ""; //$NON-NLS-1$  
        ra =new Random();  
        rannum = ra.nextInt(values.length);  
        try {  
            randomString = ((CompoundVariable) values[rannum]).execute();  
        } catch (Exception uee) {  
            return null;  
        }  
        return randomString;  
    }  
  
    /** {@inheritDoc} */  
    @Override  
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {  
        //checkParameterCount(parameters, 1);  
        values = parameters.toArray();  
    }  
  
    /** {@inheritDoc} */  
    @Override  
    public String getReferenceKey() {  
        return KEY;  
    }  
  
    /** {@inheritDoc} */  
    @Override  
    public List<String> getArgumentDesc() {  
        return desc;  
    }  
    public static void main(String[] args){
    	GetRandomStr();
    }
}  
