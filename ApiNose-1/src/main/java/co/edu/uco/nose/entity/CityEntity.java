package co.edu.uco.nose.entity;

import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class CityEntity extends Entity {

    private UUID cityId;
	private String name;
    private StateEntity state;

    public CityEntity() {
        setCityId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setState(StateEntity.getDefault());
    }

    public CityEntity(final UUID cityId) {
        setCityId(id);
        setName(TextHelper.getDefault());
        setState(StateEntity.getDefault());
    }

    public CityEntity(final UUID cityId, final String name, final StateEntity state) {
        setCityId(id);
        setName(name);
        setState(state);
    }

    static CityEntity getDefaultValue() {
        return new CityEntity();
    }

    static CityEntity getDefaultValue(final CityEntity city) {
        return ObjectHelper.getDefault(city, getDefaultValue());
    }

    public UUID getCityId() {
		return cityId;
	}

	public void setCityId(UUID cityId) {
		this.cityId = UUIDHelper.getDefault(cityId);
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