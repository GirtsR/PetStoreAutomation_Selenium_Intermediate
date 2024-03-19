package utils;

public class Locator {
    public LocatorType type;
    public String value;

    public Locator(LocatorType locatorType, String locatorValue) {
        this.type = locatorType;
        this.value = locatorValue;
    }

    public enum LocatorType {
        XPATH,
        ID,
        NAME
    }
}
