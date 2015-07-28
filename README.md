# page_maker
Create country pages for World Data Centre


Instruction
1.  Create page for Albania

2.  Export project to empty.json

3.  Cut page Albania (instead paste tag //TODO ALBANIA ) from JSON and paste to input.json

4.  In input.json put a tags on which place you need to change to data


A piece of code where tags change to data

ret=ret.replaceAll("Albania",name);
        ret=ret.replaceAll("ALB",code);
        ret=ret.replaceAll("//TODO GROUPS",groupJSON());
        ret=ret.replaceAll("//TODO Link",linkJSON());
        ret=ret.replaceAll("//TODO POPM",
        String.format("%7.3f",Math.round(Float.parseFloat(indicatorValue.get("Population"))) / 1000000.0));
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

