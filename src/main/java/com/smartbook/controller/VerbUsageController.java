package com.smartbook.controller;

import com.smartbook.entity.IrrVerbArrange;
import com.smartbook.service.IrrVerbArrangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/verbs")
@AllArgsConstructor
@Tag(name = "Verb Usage Controller", description = "designed to learn words")
public class VerbUsageController {
    private final IrrVerbArrangeService irrVerbArrangeService;

    @GetMapping(value = "/artAll")
    public ResponseEntity<?> getAll() {
        List<IrrVerbArrange> verbs = irrVerbArrangeService.findAll();
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ///compose
    @GetMapping(value = "/caseStudyAll")
    public ResponseEntity<List<IrrVerbArrange>> listAllVerbs() {
        final List<IrrVerbArrange> verbs = irrVerbArrangeService.findAll();
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //compose by caseNumber 50 100 200
    @GetMapping(value = "/caseStudy/{caseNumber}")
    public ResponseEntity<List<IrrVerbArrange>> listVerbsByCaseNumber(@PathVariable Integer caseNumber) {
        System.out.println("Number");
        final List<IrrVerbArrange> verbs = irrVerbArrangeService.findByCaseNumber(caseNumber);
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //compose by caseNumber Pageable
    @GetMapping(value = "/studyByNumber/{caseNumber}")
    public ResponseEntity<List<IrrVerbArrange>> listVerbsByCaseNumberPageable(
            @PathVariable Integer caseNumber,
            @PageableDefault(size = 10, page = 0, sort = {"word"}, direction = Sort.Direction.ASC)
                    Pageable pageable) {
        System.out.println("Pageable");
        final List<IrrVerbArrange> verbs = irrVerbArrangeService.findByCaseNumberPageable(caseNumber, pageable);
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //compose by caseNumber Pageable studyByGroup
    @GetMapping(value = "/studyByGroup/{caseNumber}/{groupNumber}")
    public ResponseEntity<List<IrrVerbArrange>> listVerbsByCaseNumberGroupNumberPageable(
            @PathVariable Integer caseNumber,
            @PathVariable Integer groupNumber,
            @PageableDefault(size = 10, page = 0, sort = {"word"}, direction = Sort.Direction.ASC)
                    Pageable pageable) {
        System.out.println("Pageable by Group");
        if (groupNumber >= 14) {
            System.out.println("groupNumber not equals = " + groupNumber);
            final List<IrrVerbArrange> verbs = irrVerbArrangeService.findByCaseNumberPageable(caseNumber, pageable);
            return verbs != null && !verbs.isEmpty()
                    ? new ResponseEntity<>(verbs, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("groupNumber " + groupNumber);
        final List<IrrVerbArrange> verbs = irrVerbArrangeService.findByCaseNumberAndGroupNumberPageable(caseNumber, groupNumber, pageable);
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ///Search by first Char
    @GetMapping(value = "/firstChar/{firstChar}")
    public ResponseEntity<List<IrrVerbArrange>> searchByFirstChar(@PathVariable("firstChar") String firstChar) {
        System.out.println("firstChar = " + firstChar);

        final List<IrrVerbArrange> verbs = irrVerbArrangeService.searchByFirstChar(firstChar);
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ///Search by containing Chars
    @GetMapping(value = "/containChars/{containChars}")
    public ResponseEntity<List<IrrVerbArrange>> searchByContainChars(@PathVariable("containChars") String containChars) {
        System.out.println("containingChar = " + containChars);

        final List<IrrVerbArrange> verbs = irrVerbArrangeService.searchByContainChars(containChars);
        return verbs != null && !verbs.isEmpty()
                ? new ResponseEntity<>(verbs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
