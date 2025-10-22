package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class CountryEntity  {

    private UUID countryId;
	private String name;

	public CountryEntity() {
		setCountryId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public CountryEntity(final UUID countryId) {
		setCountryId(countryId);
		setName(TextHelper.getDefault());
	}
	
	public CountryEntity(UUID countryId, String name) {
		setCountryId(countryId);
		setName(name);
	}
	


	
	static CountryEntity getDefault() {
		return new CountryEntity();
	}
	
	static CountryEntity getDefault(final CountryEntity country) {
		return ObjectHelper.getDefault(country, getDefault());
	}

	public UUID getCountryId() {
		return countryId;
	}

	public void setCountryId(UUID countryId) {
		this.countryId = UUIDHelper.getUUIDHelper().getDefault(countryId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
}