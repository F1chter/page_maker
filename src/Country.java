import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 11.07.15.
 */
public class Country {
    private String name;
    private String code;
    private HashMap<String,String> group;
    private HashMap<String,String> indicatorValue;
    private static HashMap<String, Set<String>> regionCountries;
    private static HashMap<String, String> countryENCO;
    static {
        regionCountries = new HashMap<String, Set<String>>();
        countryENCO = new HashMap<String, String>();
    }
    public Country(String name, String code){
        this.name=name;
        this.code=code;
        group = new HashMap<String, String>();
        indicatorValue = new HashMap<String, String>();
    }
    public void addGroup(String groupName,String groupCode)throws Exception{
        if (group.containsKey(groupName)) {
            throw new Exception("More one value per one indicator");
        }else{
            group.put(groupName,groupCode);
        }
    }
    public void addValue(String indicator,String value) throws Exception {
        if (indicatorValue.containsKey(indicator)) {
            throw new Exception("More one value per one indicator");
        }else{
            indicatorValue.put(indicator,value);
        }
    }
    public String toPage(String template){
        String ret = template;
        ret=ret.replaceAll("Albania",name);
        ret=ret.replaceAll("ALB",code);
        ret=ret.replaceAll("//TODO GROUPS",groupJSON());
        ret=ret.replaceAll("//TODO Link",linkJSON());
        ret=ret.replaceAll("//TODO POPM",String.format("%7.3f",Math.round(Float.parseFloat(indicatorValue.get("Population"))) / 1000000.0));
        ret=ret.replaceAll("//TODO GDPDOL",round(indicatorValue.get("GDP per capita")));
        ret=ret.replaceAll("//TODO SDIRANK",indicatorValue.get("Index of sustainable development rank"));
        ret=ret.replaceAll("//TODO SDI",round(indicatorValue.get("Index of sustainable development")));
        ret=ret.replaceAll("//TODO CONFLICT",indicatorValue.get("Conflicts intencity"));
        ret=ret.replaceAll("//TODO Quality",round(indicatorValue.get("Quality of Life Component")));
        ret=ret.replaceAll("//TODO Economic",round(indicatorValue.get("Index of economic dimension")));
        ret=ret.replaceAll("//TODO Enviroment",round(indicatorValue.get("Index of environmental dimension")));
        ret=ret.replaceAll("//TODO Social",round(indicatorValue.get("Index of social and institutional dimension")));
        ret=ret.replaceAll("//TODO Harmonization",round(indicatorValue.get("Harmonization degree")));
        ret=ret.replaceAll("//TODO Security",round(indicatorValue.get("Security of Life Component")));
        ret=ret.replaceAll("//TODO Energy",round(indicatorValue.get("Energy security")));
        ret=ret.replaceAll("//TODO Biodiversity",round(indicatorValue.get("Biological balance")));
        ret=ret.replaceAll("//TODO GINI",round(indicatorValue.get("Gini coefficient")));
        ret=ret.replaceAll("//TODO Corruption",round(indicatorValue.get("Corruption perception")));
        ret=ret.replaceAll("//TODO Water",round(indicatorValue.get("Access to potable water")));
        ret=ret.replaceAll("//TODO CO2",round(indicatorValue.get("CO2 emissions ")));
        ret=ret.replaceAll("//TODO Child",round(indicatorValue.get("Mortality rate, under-5 ")));
        ret=ret.replaceAll("//TODO Diseases",round(indicatorValue.get("Global diseases")));
        ret=ret.replaceAll("//TODO State",round(indicatorValue.get("State fragility")));
        ret=ret.replaceAll("//TODO Natural",round(indicatorValue.get("Natural disasters")));
        ret=ret.replaceAll("//TODO Proliferation",round(indicatorValue.get("Proliferation Index")));


        return ret;
    }
    private String groupJSON(){
        String ret="";
        for (String key:group.keySet()) {
           ret+= "{"
                   +"\"id\": \""+group.get(key)+"\","
                   +"\"index\": \"24\","
                   +"\"label\": \""+key+"\""
                   +"},";
        }
        return ret;
    }
    private String linkJSON(){
        String ret="";
        for (String key:group.keySet()) {
        //ret+="<div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/"+group.get(key)+"\"></a></div>";
            ret+="<div style=\\\\\\\"text-align: center;\\\\\\\"><a href=\\\\\\\"http://wdc-app.cloudapp.net/app/SDI_2014/"+group.get(key)+"\\\\\\\"><b>"+key+"</b></a></div>";
            ret+=getLinkByGroup(key,name);
        }
        return ret;
        //return ret;
        //return "\"<div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/OSCE\"><b>Organization for Security and Co-operation in Europe</b></a>&nbsp;</div><div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">Austria<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Azerbaijan<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Albania<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Belgium<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Bulgaria<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Bosnia and Herzegovina<b style=\"color: rgb(17, 17, 17);\">,</b></a></div><div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">United&nbsp;Kingdom<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Armenia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Georgia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Greece<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Denmark<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Estonia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Ireland<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Iceland<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Spain<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Italy<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Kazakhstan<b style=\"color: rgb(17, 17, 17);\">,</b>Canada<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Kyrgyzstan<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b></a></div><div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">Cyprus<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Latvia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Lithuania<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Luxembourg<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Moldova, Republic of<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Netherlands<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Germany<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Norway<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Poland<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Portugal<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b></a></div><div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">Russian Federation<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Romania<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Slovakia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Slovenia<b style=\"color: rgb(17, 17, 17);\">,</b>United States<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b></a><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">Tajikistan<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b></a></div><div style=\"text-align: center;\"><a href=\"http://wdc-app.cloudapp.net/app/SDI_2014/AUT\">Turkey<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Hungary<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Uzbekistan<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Ukraine<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Finland<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>France<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Croatia<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Czech Republic<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Switzerland<b style=\"color: rgb(17, 17, 17);\">,&nbsp;</b>Sweden<span style=\"color: rgb(17, 17, 17);\">&nbsp;</span><br></a></div>\"";
    }
    private String round(String s){
        if(s.isEmpty()){
            return "";
        }
        return String.format("%.3f", Float.parseFloat(s));
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", group=" + group +
                ", indicatorValue=" + indicatorValue +
                '}';
    }

    public static void addAllGroups(HashMap<String, Set<String>> regionCountries, HashMap<String, String> countryENCO) {
        Country.regionCountries = regionCountries;
        Country.countryENCO = countryENCO;
    }

    private static String getLinkByGroup(String groupName,String excludeCountry){
        String ret="<div style=\\\\\\\"text-align: center;\\\\\\\">";
        Set<String> countriesNames=regionCountries.get(groupName);
        for(String name:countriesNames) {
            if(!excludeCountry.equals(name))
                ret += "<a href=\\\\\\\"http://wdc-app.cloudapp.net/app/SDI_2014/" + countryENCO.get(name) + "\\\\\\\">" + name + "</a>&nbsp;";
        }
        ret += "</div>";
        return ret;
    }

    public String getName() {
        return name;
    }

    public String getLinkJSON() {
        return "";
    }
}
