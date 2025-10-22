package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class CityDTO {
	
	private UUID id;
	private String name;
	private StateDTO state;
	
	public CityDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setState(StateDTO.getDefaultValue());
	}
	
	public CityDTO(final UUID id) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefault());
		setState(StateDTO.getDefaultValue());
	}
	
	public CityDTO(final UUID id, final String name, final StateDTO state) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefaultWithTrim(name));
		setState(ObjectHelper.getDefault(state, StateDTO.getDefaultValue()));
	}
	
	
	
	public static CityDTO getDefaultValue() {
		return new CityDTO();
	}
	
	public static CityDTO getDefaultValue(final CityDTO city) {
		return ObjectHelper.getDefault(city, getDefaultValue());
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
	
	public StateDTO getState() {
		return state;
	}
	
	public void setState(final StateDTO state) {
		this.state = ObjectHelper.getDefault(state, StateDTO.getDefaultValue());
	}
}
