package ie.citadel.pupils.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.citadel.pupils.model.Pupil;
import ie.citadel.pupils.services.PupilService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value="v1/pupils")
public class PupilServiceController {
    @Autowired
    private PupilService pupilService;

  
    @RequestMapping(value="/{pupilId}",method = RequestMethod.GET)
    public Pupil getPupil( @PathVariable("pupilId") String pupilId) {

        return pupilService.getPupil( pupilId);
        
    }
    
  }
