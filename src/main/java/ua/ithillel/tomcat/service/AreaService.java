package ua.ithillel.tomcat.service;


import ua.ithillel.tomcat.model.vm.AreaItemVm;

import java.util.List;

public interface AreaService {
    List<AreaItemVm> getAreas();
}
