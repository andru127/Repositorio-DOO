package co.edu.uco.nose.entity;

import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscutting.helpers.BooleanHelper;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class UserEntity  {

	private UUID userId;
    private IdTypeEntity identificationType;
    private String identificationNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private CityEntity residenceCity;
    private String email;
    private String cellPhoneNumber;
    private boolean emailConfirmed;
    private boolean emailConfirmedIsDefaultValue;
    private boolean cellPhoneNumberConfirmed;
    private boolean cellPhoneNumberConfirmedIsDefaultValue;

    
    public UserEntity() {
        setUserId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationType(IdTypeEntity.getDefault());
        setIdentificationNumber(TextHelper.getDefault());;
        setFirstName(TextHelper.getDefault());
        setMiddleName(TextHelper.getDefault());
        setLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(CityEntity.getDefault());
        setEmail(TextHelper.getDefault());
        setCellPhoneNumber(TextHelper.getDefault());
        setCellPhoneNumberConfirmed(false);
        setCellPhoneNumberConfirmedIsDefaultValue(false);
        setEmailConfirmed(false);
        setEmailConfirmedIsDefaultValue(false);
    }

    public UserEntity(final UUID id) {
        setUserId(userId);
        setIdentificationType(IdTypeEntity.getDefault());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setMiddleName(TextHelper.getDefault());
        setLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(CityEntity.getDefault());
        setEmail(TextHelper.getDefault());
        setCellPhoneNumber(TextHelper.getDefault());
        setCellPhoneNumberConfirmed(false);
        setCellPhoneNumberConfirmedIsDefaultValue(false);
        setEmailConfirmed(false);
        setEmailConfirmedIsDefaultValue(false);

    }


    public UserEntity(final UUID id, final IdTypeEntity identificationType, final String identificationNumber, final String firstName,
                      final String middleName, final String lastName, final String secondLastName, final CityEntity residenceCity, final String email,
                      final String cellPhoneNumber, final boolean emailConfirmed, final boolean cellPhoneNumberConfirmed) {
        setUserId(userId);
        setIdentificationType(identificationType);
        setIdentificationNumber(identificationNumber);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setSecondLastName(secondLastName);
        setResidenceCity(residenceCity);
        setEmail(email);
        setCellPhoneNumber(cellPhoneNumber);
        setCellPhoneNumberConfirmed(cellPhoneNumberConfirmed);
        setEmailConfirmed(emailConfirmed);
        setCellPhoneNumberConfirmed(cellPhoneNumberConfirmed);
    }

    static UserEntity getDefaultValue() {
        return new UserEntity();
    }

    static UserEntity getDefaultValue(final UserEntity user) {
        return ObjectHelper.getDefault(user, getDefaultValue());
    }

    
    
    public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = UUIDHelper.getUUIDHelper().getDefault(userId);
	}

	public IdTypeEntity getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(final IdTypeEntity identificationType) {
        this.identificationType = IdTypeEntity.getDefault(identificationType);
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

    public CityEntity getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(final CityEntity residenceCity) {
        this.residenceCity = CityEntity.getDefault(residenceCity);
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
    
    public boolean isEmailConfirmedIsDefaultValue() {
        return emailConfirmedIsDefaultValue;
    }

    public void setEmailConfirmedIsDefaultValue(final boolean emailConfirmedIsDefaultValue) {
        this.emailConfirmedIsDefaultValue = false;
    }

    public boolean CellPhoneNumberConfirmed() {
        return cellPhoneNumberConfirmed;
    }

    public void setCellPhoneNumberConfirmed(final boolean cellPhoneNumberConfirmed) {
        this.cellPhoneNumberConfirmed = BooleanHelper.getDefault(cellPhoneNumberConfirmed);
    }
    
    public boolean CellPhoneNumberConfirmedIsDefaultValue() {
        return cellPhoneNumberConfirmedIsDefaultValue;
    }

    public void setCellPhoneNumberConfirmedIsDefaultValue(final boolean cellPhoneNumberConfirmedIsDefaultValue) {
        this.cellPhoneNumberConfirmedIsDefaultValue = false;
    }


}