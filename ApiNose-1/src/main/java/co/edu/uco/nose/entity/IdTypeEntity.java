package co.edu.uco.nose.entity;

import co.edu.uco.nose.entity.Entity;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;

import java.util.UUID;

public class IdTypeEntity extends Entity {

    private String name;

    public IdTypeEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public IdTypeEntity(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }


    public IdTypeEntity(final UUID id, final String name) {
        super(id);
        this.name = name;
    }

    static IdentificationTypeEntity getDefaultValue() {
        return new IdentificationTypeEntity();
    }

    static IdentificationTypeEntity getDefaultValue(final IdentificationTypeEntity identificationType) {
        return ObjectHelper.getDefault(identificationType, getDefaultValue());
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }



}