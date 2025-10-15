package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

public final class CityEntity extends Entity  {

    private UUID cityId;
    private String name;
    private StateEntity state;

    public CityEntity() {
        setCityId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setState(StateEntity.getDefault());
    }

    public CityEntity(final UUID cityId) {
        setCityId(cityId);
        setName(TextHelper.getDefault());
        setState(StateEntity.getDefault());
    }

    public CityEntity(final UUID cityId, final String name, final StateEntity state) {
        setCityId(cityId);
        setName(name);
        setState(state);
    }

    public static CityEntity getDefault() {
        return new CityEntity();
    }

    public static CityEntity getDefault(final CityEntity city) {
        return ObjectHelper.getDefault(city, getDefault());
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(final UUID cityId) {
        this.cityId = UUIDHelper.getUUIDHelper().getDefault(cityId);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(final StateEntity state) {
        this.state = ObjectHelper.getDefault(state, StateEntity.getDefault());
    }
}
