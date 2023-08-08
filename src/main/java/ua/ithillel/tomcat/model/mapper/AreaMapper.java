package ua.ithillel.tomcat.model.mapper;


import ua.ithillel.tomcat.model.dto.AreaItemDto;
import ua.ithillel.tomcat.model.vm.AreaItemVm;

public interface AreaMapper {
    AreaItemVm areaDtToAreaVm(AreaItemDto areaItemDto);
}
