package com.example.demo.api;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/",produces = "application/json")
@CrossOrigin("*")
public class HomeController {

    private BaseServiceImpl baseServiceImpl;

    @Autowired
    public HomeController(BaseServiceImpl baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }

    public EmployeeDTO[] convert(List<Employee> employeeList){
        EmployeeDTO [] personnelDTOS = new EmployeeDTO[employeeList.size()];
        for(int i=0;i< employeeList.size();i++){
            personnelDTOS[i]=  EmployeeDTO.convert(employeeList.get(i));
        }
        return personnelDTOS;
    }

    @GetMapping(path = "/city/{id}")
    public EmployeeDTO[] getEmployeeByCity(@PathVariable("id") Integer id){
        List<Employee> employeeList = baseServiceImpl.getByCity(id);
        return convert(employeeList);
    }

    @GetMapping(path = "/department/{id}")
    public EmployeeDTO[] getEmployeeByDepartment(@PathVariable("id")Integer id){
        List<Employee> employeeList = baseServiceImpl.getByDepartment(id);
        return convert(employeeList);
    }

    @GetMapping(path="/page/{index}")
    public EmployeeDTO [] getPageList(@PathVariable("index")Integer index){
        List<Employee> employees = baseServiceImpl.getPage(index);
        return convert(employees);
    }
}
