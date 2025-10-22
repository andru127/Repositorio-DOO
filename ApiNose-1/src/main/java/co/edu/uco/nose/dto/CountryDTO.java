package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public class CountryDTO {
    
    private UUID id;
    private String name;

    public CountryDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public CountryDTO(final UUID id) {
        setId(UUIDHelper.getUUIDHelper().getDefault(id));
        setName(TextHelper.getDefault());
    }

    public CountryDTO(final UUID id, final String name) {
        setId(UUIDHelper.getUUIDHelper().getDefault(id));
        setName(TextHelper.getDefaultWithTrim(name));
    }

    public static CountryDTO getDefaultValue() {
        return new CountryDTO();
    }

    public static CountryDTO getDefaultValue(final CountryDTO country) {
        return ObjectHelper.getDefault(country, getDefaultValue());
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
