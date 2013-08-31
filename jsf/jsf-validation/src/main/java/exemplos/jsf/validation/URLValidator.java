package exemplos.jsf.validation;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("urlValidator") 
public class URLValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		System.out.println("Validando a url");
		
	      String urlValue = value.toString();

	    if(urlValue.startsWith("http://")==false){
			FacesMessage msg = new FacesMessage("URL validation failed", "Invalid URL format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
	    }

	}

}
