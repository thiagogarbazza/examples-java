package exemplos.jsf.converter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("moedaConverter")
public class MoedaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("Convertendo a moeda para valor bigdecimal.");

		return new BigDecimal("0");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("Convertendo o valor para moeda.");

		BigDecimal moeda = (BigDecimal) value;
		moeda.setScale(2);

		NumberFormat nf2 = NumberFormat.getInstance(new Locale("pt", "br"));
		return nf2.format(moeda);
	}

}
