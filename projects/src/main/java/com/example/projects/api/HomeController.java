package com.example.projects.api;

import com.example.projects.dto.PersonnelDTO;
import com.example.projects.entity.Employee;
import com.example.projects.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/",produces = "application/json")
@CrossOrigin("*")
public class HomeController {

    private BaseServiceImpl baseServiceImpl;

    @Autowired
    public HomeController(BaseServiceImpl baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }

    public PersonnelDTO [] convert(List<Employee> employeeList){
        PersonnelDTO [] personnelDTOS = new PersonnelDTO[employeeList.size()];
        for(int i=0;i< employeeList.size();i++){
            personnelDTOS[i]=  PersonnelDTO.convert(employeeList.get(i));
        }
        return personnelDTOS;
    }

    @GetMapping(path = "/city/{id}")
    public PersonnelDTO[] getEmployeeByCity(@PathVariable("id") Integer id){
        List<Employee> employeeList = baseServiceImpl.getByCity(id);
        return convert(employeeList);
    }

    @GetMapping(path = "/department/{id}")
    public PersonnelDTO[] getEmployeeByDepartment(@PathVariable("id")Integer id){
        List<Employee> employeeList = baseServiceImpl.getByDepartment(id);
        return convert(employeeList);
    }

    @GetMapping(path="/page/{index}")
    public List<Employee> getPageList(@PathVariable("index")Integer index){
        List<Employee> employees = baseServiceImpl.getPage(index,3);
        return employees;
    }
}
