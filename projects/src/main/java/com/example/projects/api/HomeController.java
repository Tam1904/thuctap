package com.example.projects.api;

import com.example.projects.dto.PersonnelDTO;
import com.example.projects.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/",produces = "applications/json")
@CrossOrigin
public class HomeController {

    private final BaseServiceImpl baseServiceImpl;

    @Autowired
    public HomeController(BaseServiceImpl baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }

    @GetMapping(path = "/{id}")
    public PersonnelDTO(@PathVariable("id")Integer id){
        List<Per>
    }
}
