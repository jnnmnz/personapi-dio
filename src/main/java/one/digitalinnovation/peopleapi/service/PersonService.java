package one.digitalinnovation.peopleapi.service;

import one.digitalinnovation.peopleapi.dto.request.PersonDTO;
import one.digitalinnovation.peopleapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.peopleapi.entity.Person;
import one.digitalinnovation.peopleapi.exception.PersonNotFoundException;
import one.digitalinnovation.peopleapi.mapper.PersonMapper;
import one.digitalinnovation.peopleapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("CREATED Person ID "+savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
        return personMapper.toDTO(person);
    }
}
