package ie.citadel.pupil.events.models;

public class AddressChangeModel{
    private String type;
    private String action;
    private String eircode;

    public AddressChangeModel() {
		super();
	}

    
    public  AddressChangeModel(String type, String action, String eircode) {
        super();
        this.type   = type;
        this.action = action;
        this.eircode = eircode;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getEircode() {
        return eircode;
    }

    public void seEircode(String eircode) {
        this.eircode = eircode;
    }

}