package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.dto.IdTypeDTO;


public final class IdTypeDTOAssembler implements DTOAssembler<IdTypeDTO, IdentificationTypeDomain> {

	
	private static final DTOAssembler<IdTypeDTO, IdentificationTypeDomain> instance = new IdTypeDTOAssembler();
	private IdTypeDTOAssembler() {
		
	}
	
	public static DTOAssembler<IdTypeDTO, IdentificationTypeDomain> getIdTypeDTOAssembler() {
		return instance;
	}
	
	@Override
	public IdTypeDTO toDTO(final IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdTypeDTO(domainTmp.getId(), domainTmp.getName());
	}

	@Override
	public IdentificationTypeDomain toDomain(final IdTypeDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new IdTypeDTO());
		return new IdentificationTypeDomain(dtoTmp.getId(), dtoTmp.getName());
	}

	@Override
	public List<IdTypeDTO> toDTO(final List<IdentificationTypeDomain> domainList) {
		var identificationTypeDTOList = new ArrayList<IdTypeDTO>();
		
		for (var identificationTypeDomain : domainList) {
			
			identificationTypeDTOList.add(toDTO(identificationTypeDomain));
		}
		
		return identificationTypeDTOList;
	}
	

}