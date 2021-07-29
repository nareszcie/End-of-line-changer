package validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;
import java.util.List;

public class EOLValidator implements IParameterValidator {

    @Override
    public void validate(String s, String s1) throws ParameterException {
        List<String> correctValues = Arrays.asList("cl","rf","clrf");
        for(String value : correctValues){
            if(s1.equals(value))
                return;
        }
        throw new ParameterException("Invalid eol symbol. Allowed values: "+correctValues);
    }
}
