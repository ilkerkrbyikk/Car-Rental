package com.Ilker.service.maintenance;

import com.Ilker.entitiy.Maintenance;
import com.Ilker.request.AddMaintenanceRequest;
import com.Ilker.request.UpdateMaintenanceRequest;

import java.util.List;

public interface MaintenanceService {

    List<Maintenance> getAll();
    Maintenance add(AddMaintenanceRequest request);
    Maintenance update(UpdateMaintenanceRequest request, Long id);
    void delete(Long id);
}
