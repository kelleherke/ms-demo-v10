package ie.citadel.pupils.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.citadel.pupils.model.Pupil;


@Repository
public interface PupilRepository extends CrudRepository<Pupil,String>  {
    public Pupil findByPupilId(String pupilId);

}
