package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

class Entity {

    private UUID id;

    protected Entity(final UUID id) {
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

}