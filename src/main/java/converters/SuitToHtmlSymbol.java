package converters;

import java.io.Serializable;

import cards.Suit;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by pndl on 3/21/15.
 */
@FacesConverter("converters.SuitToHtmlSymbol")
public class SuitToHtmlSymbol implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o.equals(Suit.CLUBS)) return "&clubs;";
        if (o.equals(Suit.SPADES)) return "&spades;";
        if (o.equals(Suit.HEARTS)) return "&hearts;";
        if (o.equals(Suit.DIAMONDS)) return "&diams;";
        return null;
    }
}
