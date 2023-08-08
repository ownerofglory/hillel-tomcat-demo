package ua.ithillel.tomcat.model.mapper;

import ua.ithillel.tomcat.model.dto.AreaItemDto;
import ua.ithillel.tomcat.model.vm.AreaItemVm;

public class AreaMapperDefault implements AreaMapper {
    @Override
    public AreaItemVm areaDtToAreaVm(AreaItemDto areaItemDto) {
        AreaItemVm areaItemVm = new AreaItemVm();
        areaItemVm.setArea(areaItemDto.getStrArea());
        return areaItemVm;
    }
}
