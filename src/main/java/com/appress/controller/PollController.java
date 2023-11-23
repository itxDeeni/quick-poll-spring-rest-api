package com.appress.controller;

import com.appress.domain.Poll;
import com.appress.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {
    @Inject
    private PollRepository pollRepository;

    @GetMapping(path = "/hello")
    public String test(){
        return "hello world";
    }

    @GetMapping(path = "/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @PostMapping(path = "/polls")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }

    @GetMapping(path ="polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) throws Exception{
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(!poll.isPresent()){
            throw new Exception("Poll not found");
        }
        return new ResponseEntity<>(poll.get(),HttpStatus.OK);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
    //save the entity
        Poll newPoll = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
