package ie.citadel.pupils.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pupils")
public class Pupil{

  @Id
  @Column(name = "pupil_id", nullable = false)
  private String pupilId;

  @Column(name = "eircode", nullable = false)
  private String eircode;
  
  @Column(name = "forename", nullable = false)
  private String forename;
  
  @Column(name = "surname", nullable = false)
  private String surname;

  @Transient
  private String address1 ="";

  @Transient
  private String address2 ="";

  @Transient
  private String address3 ="";

  @Transient
  private String address4 ="";
  
  public String getPupilId() {
	return pupilId;
  }

  public void setPupilId(String pupilId) {
	this.pupilId = pupilId;
  }

	public String getEircode() {
		return eircode;
	}
	
	public void setEircode(String eircode) {
		this.eircode = eircode;
	}
	
	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getAddress3() {
		return address3;
	}
	
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	public String getAddress4() {
		return address4;
	}
	
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	
	public Pupil withPupilId(String pupilId){
	    this.pupilId = pupilId;
	    return this;
	}
	
	public Pupil withEircode(String eircode){
	    this.eircode = eircode;
	    return this;
	}
	
	public Pupil withForename(String forename){
	    this.forename = forename;
	    return this;
	}
	
	public Pupil withSurname(String surname){
	    this.surname = surname;
	    return this;
	}
	
	
	public Pupil withAddress1(String address1){
        this.address1 = address1;
        return this;
    }
	
	public Pupil withAddress2(String address2){
        this.address2 = address2;
        return this;
    }

	public Pupil withAddress3(String address3){
        this.address3 = address3;
        return this;
    }

	public Pupil withAddress4(String address4){
        this.address4 = address4;
        return this;
    }
	


}
