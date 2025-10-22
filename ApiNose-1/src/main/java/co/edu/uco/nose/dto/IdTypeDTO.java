package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class IdTypeDTO {

	private UUID id;
	private String name;
	
	public IdTypeDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id, final String name) {
		setId(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(name);
	}
	
	public static IdTypeDTO getDefaultValue() {
		return new IdTypeDTO();
	}
	
	public static IdTypeDTO getDefaultValue(final IdTypeDTO identificationType) {
		return ObjectHelper.getDefault(identificationType, getDefaultValue());
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
}
