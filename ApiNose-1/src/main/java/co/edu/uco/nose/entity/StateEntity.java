package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class StateEntity  {
	
	private UUID stateId;
	private String name;
	private CountryEntity country;
	
	public StateEntity() {
		setStateId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(CountryEntity.getDefault());
	}
	
	public StateEntity(final UUID StateId) {
		setStateId(StateId);
		setName(name);
		setCountry(country);
	}
	
	private StateEntity(UUID stateId, String name, CountryEntity country) {
		setStateId(stateId);
		setName(name);
		setCountry(country);
	}
	static StateEntity getDefault() {
		return new StateEntity();
	}
	
	static StateEntity getDefault(final StateEntity State) {
		return ObjectHelper.getDefault(State, getDefault());
	}

	public UUID getStateId() {
		return stateId;
	}

	public void setStateId(UUID stateId) {
		this.stateId = UUIDHelper.getUUIDHelper().getDefault(stateId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity countryEntity) {
		this.country = CountryEntity.getDefault(countryEntity);
	}

}
