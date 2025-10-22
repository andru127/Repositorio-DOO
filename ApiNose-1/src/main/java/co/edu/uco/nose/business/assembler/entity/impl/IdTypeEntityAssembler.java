package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.entity.IdTypeEntity;


public final class IdTypeEntityAssembler implements EntityAssembler<IdTypeEntity, IdentificationTypeDomain> {

	private static final EntityAssembler<IdTypeEntity, IdentificationTypeDomain> instance = new IdTypeEntityAssembler();
	private IdTypeEntityAssembler() {
		
	}
	
	public static EntityAssembler<IdTypeEntity, IdentificationTypeDomain> getIdentificationTypeEntityAssembler() {
		return instance;
	}
	
	@Override
	public IdTypeEntity toEntity(final IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdTypeEntity(domainTmp.getId(), domainTmp.getName());
	}

	@Override
	public IdentificationTypeDomain toDomain(final IdTypeEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new IdTypeEntity());
		return new IdentificationTypeDomain(entityTmp.getIdTypeId(), entityTmp.getName());
	}

}