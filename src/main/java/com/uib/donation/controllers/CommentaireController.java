package com.uib.donation.controllers;

import com.uib.donation.dao.CommentDao;
import com.uib.donation.services.CommentaireServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/commentaires/")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200"
})
public class CommentaireController {

    private  final CommentaireServiceImplementation commentaireService;

    @GetMapping("findAllByCampagne/{id}")
    public ResponseEntity<?> findAllCampagne(@PathVariable("id")Long id ){
        return  commentaireService.getCommentairesByCampaign(id);
    }

    @GetMapping("findLatestByUser/{id}")
    public ResponseEntity<?> findLatestByUser(@PathVariable("id")Long id){
        return commentaireService.findLatestByUser(id);
    }

    @GetMapping("findLatestByCampagneOwner/{id}")
    public ResponseEntity<?> findLatestByCampagneOwner(@PathVariable("id")Long id){
        return commentaireService.findLatestByCampagneOwner(id);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return commentaireService.getCommentaireById(id);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CommentDao commentaire){
        return commentaireService.createCommentaire(commentaire);
    }

    @GetMapping("latest")
    public ResponseEntity<?> getLatestComments(){
        return  commentaireService.getLatestComments();
    }

}
