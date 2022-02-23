package com.pms.schedule.repository;

import com.pms.schedule.dto.Medicines;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/* Collects the data from medical stock microservice*/

//@FeignClient(url = "${feign.stock-service-url}", name = "stock-service")
@FeignClient("medical-stock-service")
public interface MedicineRepository {

    @GetMapping("/stock/MedicineStockInformation")
    List<Medicines> getAllMedicines();
}
