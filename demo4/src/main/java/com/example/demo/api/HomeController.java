package com.example.demo.api;

import com.example.demo.entities.Employee;
import com.example.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/",produces = "application/json")
@CrossOrigin("*")
public class HomeController {

    private BaseServiceImpl baseServiceImpl;

    @Autowired
    public HomeController(BaseServiceImpl baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }

    @GetMapping(path = "/add")
    public String addEmployee(){
        Employee employee = new Employee(6,"Nguyen Van F", (long) 841223456,new Date(),1,1,1);
        baseServiceImpl.saveEmployee(employee);
        return employee.toString();
    }

    @GetMapping(path = "/getAll")
    public String getAll(){
        return baseServiceImpl.findAll().toString();
    }

    @GetMapping(path = "/city/{id}")
    public String getByCityId(@PathVariable("id") Integer id){
        return  baseServiceImpl.getEmployeesByCityId(id).toString();
    }

    @GetMapping(path = "/{id}")
    public String getById(@PathVariable("id")Integer id){
        return baseServiceImpl.findById(id).toString();
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteByID(@PathVariable("id")Integer id){
        baseServiceImpl.deleteEmployee(id);
        return "OK";
    }
}
