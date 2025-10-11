package co.edu.uco.nose.entity;

import co.edu.uco.nose.entity.Entity;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class CountryEntity extends Entity {

    private String name;


    public CountryEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public CountryEntity(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }

    public CountryEntity(final UUID id, final String name) {
        super(id);
        setName(name);
    }

    static CountryEntity getDefaultValue() {
        return new CountryEntity();
    }

    static CountryEntity getDefaultValue(final CountryEntity country) {
        return ObjectHelper.getDefault(country, getDefaultValue());
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}