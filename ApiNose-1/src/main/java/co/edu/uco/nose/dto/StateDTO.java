package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class StateDTO {

	private UUID id;
	private String name;
	private CountryDTO country;

	public StateDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(CountryDTO.getDefaultValue());
	}

	public StateDTO(final UUID id) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefault());
		setCountry(CountryDTO.getDefaultValue());
	}

	public StateDTO(final UUID id, final String name, final CountryDTO country) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefaultWithTrim(name));
		setCountry(ObjectHelper.getDefault(country, CountryDTO.getDefaultValue()));
	}

	public static StateDTO getDefaultValue() {
		return new StateDTO();
	}

	public static StateDTO getDefaultValue(final StateDTO state) {
		return ObjectHelper.getDefault(state, getDefaultValue());
	}


	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(final CountryDTO country) {
		this.country = ObjectHelper.getDefault(country, CountryDTO.getDefaultValue());
	}
}
