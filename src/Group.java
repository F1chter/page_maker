import java.util.ArrayList;

/**
 * Created by Alex on 17.07.15.
 */
public class Group {
    String type;
    String name;
    ArrayList<Country>countries;

    public Group(String name) {
        this.name = name;
        countries = new ArrayList<Country>();

    }
    public String getLinkJSON(String excludeCountry){
        String  ret="";
        for (Country country:countries){
            if(!excludeCountry.equals(country.getName())) {
                ret += country.getLinkJSON();
            }
        }
        return "";
    }

}
