package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.StateDTOAssembler.getStateDTOAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.dto.CityDTO;

public final class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {

	
	private static final DTOAssembler<CityDTO, CityDomain> instance = new CityDTOAssembler();
	private CityDTOAssembler() {
		
	}
	
	public static DTOAssembler<CityDTO, CityDomain> getCityDTOAssembler() {
		return instance;
	}
	
	@Override
	public CityDTO toDTO(final CityDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CityDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var stateTmp = getStateDTOAssembler().toDTO(domainTmp.getState());
		return new CityDTO(domainTmp.getId(), domainTmp.getName(), stateTmp);
	}

	@Override
	public CityDomain toDomain(final CityDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new CityDTO());
		var stateDomainTmp = getStateDTOAssembler().toDomain(dtoTmp.getState());
		return new CityDomain(dtoTmp.getId(), dtoTmp.getName(), stateDomainTmp);
	}

	@Override
	public List<CityDTO> toDTO(final List<CityDomain> domainList) {
		var cityDTOList = new ArrayList<CityDTO>();
		
		for (var cityDomain : domainList) {
			
			cityDTOList.add(toDTO(cityDomain));
			
		}
		
		return cityDTOList;
	}

}