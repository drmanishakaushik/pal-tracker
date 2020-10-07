package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository repo;


    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
     }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry time = repo.create(timeEntryToCreate);
        return new ResponseEntity(time, HttpStatus.CREATED);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") long timeEntryId) {
        TimeEntry time = repo.find(timeEntryId);
        if(time== null){
            return new ResponseEntity(time, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(time, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = repo.list();
            return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable("timeEntryId")long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry time = repo.update(timeEntryId, expected);
        if(time== null){
            return new ResponseEntity(time, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(time, HttpStatus.OK);
    }
    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity delete(@PathVariable("timeEntryId")long timeEntryId) {
//        TimeEntry time = repo.find(timeEntryId);
//        if(time != null) {
            repo.delete(timeEntryId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }else{
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
    }
}
