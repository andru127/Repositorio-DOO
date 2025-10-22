package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.BooleanHelper;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class UserDTO {
	
	private UUID id;
	private IdTypeDTO identificationType;
	private String identificationNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String secondLastName;
	private CityDTO residenceCity;
	private String email;
	private String cellPhoneNumber;
	private boolean emailConfirmed;
	private boolean cellPhoneNumberConfirmed;
	
	public UserDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationType(IdTypeDTO.getDefaultValue());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setMiddleName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setResidenceCity(CityDTO.getDefaultValue());
		setEmail(TextHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(BooleanHelper.getDefault());
		setEmailConfirmed(BooleanHelper.getDefault());
	}
	
	public UserDTO(final UUID id) {
		this();
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
	}
	
	public UserDTO(final UUID id, final IdTypeDTO identificationType, final String identificationNumber,
			final String firstName, final String middleName, final String lastName, final String secondLastName,
			final CityDTO residenceCity, final String email, final String cellPhoneNumber,
			final boolean emailConfirmed, final boolean cellPhoneNumberConfirmed) {
		
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setIdentificationType(identificationType);
		setIdentificationNumber(identificationNumber);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setSecondLastName(secondLastName);
		setResidenceCity(residenceCity);
		setEmail(email);
		setCellPhoneNumber(cellPhoneNumber);
		setEmailConfirmed(emailConfirmed);
		setCellPhoneNumberConfirmed(cellPhoneNumberConfirmed);
	}
	
	public static UserDTO getDefaultValue() {
		return new UserDTO();
	}
	
	public static UserDTO getDefaultValue(final UserDTO user) {
		return ObjectHelper.getDefault(user, getDefaultValue());
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}

	public IdTypeDTO getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(final IdTypeDTO identificationType) {
		this.identificationType = ObjectHelper.getDefault(identificationType, IdTypeDTO.getDefaultValue());
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = TextHelper.getDefaultWithTrim(firstName);
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(final String middleName) {
		this.middleName = TextHelper.getDefaultWithTrim(middleName);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = TextHelper.getDefaultWithTrim(lastName);
	}
	
	public String getSecondLastName() {
		return secondLastName;
	}
	
	public void setSecondLastName(final String secondLastName) {
		this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
	}
	
	public CityDTO getResidenceCity() {
		return residenceCity;
	}
	
	public void setResidenceCity(final CityDTO residenceCity) {
		this.residenceCity = ObjectHelper.getDefault(residenceCity, CityDTO.getDefaultValue());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}
	
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	
	public void setCellPhoneNumber(final String cellPhoneNumber) {
		this.cellPhoneNumber = TextHelper.getDefaultWithTrim(cellPhoneNumber);
	}
	
	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}
	
	public void setEmailConfirmed(final boolean emailConfirmed) {
		this.emailConfirmed = BooleanHelper.getDefault(emailConfirmed);
	}
	
	public boolean isCellPhoneNumberConfirmed() {
		return cellPhoneNumberConfirmed;
	}
	
	public void setCellPhoneNumberConfirmed(final boolean cellPhoneNumberConfirmed) {
		this.cellPhoneNumberConfirmed = BooleanHelper.getDefault(cellPhoneNumberConfirmed);
	}
}
