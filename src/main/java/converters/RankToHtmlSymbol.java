package converters;

import cards.Rank;
import cards.Suit;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by pndl on 3/21/15.
 */
@FacesConverter("converters.RankToHtmlSymbol")
public class RankToHtmlSymbol implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o.equals(Rank.ACE)) return "A";
        if (o.equals(Rank.TWO)) return "2";
        if (o.equals(Rank.THREE)) return "3";
        if (o.equals(Rank.FOUR)) return "4";
        if (o.equals(Rank.FIVE)) return "5";
        if (o.equals(Rank.SIX)) return "6";
        if (o.equals(Rank.SEVEN)) return "7";
        if (o.equals(Rank.EIGHT)) return "8";
        if (o.equals(Rank.NINE)) return "9";
        if (o.equals(Rank.TEN)) return "10";
        if (o.equals(Rank.JACK)) return "J";
        if (o.equals(Rank.QUEEN)) return "Q";
        if (o.equals(Rank.KING)) return "K";
        return null;
    }
}
