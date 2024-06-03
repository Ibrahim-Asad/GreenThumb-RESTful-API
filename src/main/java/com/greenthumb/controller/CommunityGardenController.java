package com.greenthumb.controller;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.service.CommunityGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Community-Garden")
public class CommunityGardenController {

    @Autowired
    private CommunityGardenService communityGardenService;

    @GetMapping("/get-all")
    public List<CommunityGardenDTO> getall(){
        return communityGardenService.getAllCommuntyGarden();
    }

}
