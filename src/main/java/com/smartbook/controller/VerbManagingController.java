package com.smartbook.controller;

import com.smartbook.entity.IrrVerbAllForm;
import com.smartbook.entity.IrrVerbPhonetic;
import com.smartbook.entity.IrrVerbWord;
import com.smartbook.entity.VerbGroupMark;
import com.smartbook.entity.enums.Tenses;
import com.smartbook.service.IrrVerbAdminService;
import com.smartbook.service.VerbGroupMarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adm_verbs")
@AllArgsConstructor
@Tag(name = "Verb Manage Controller", description = "designed for content management")
public class VerbManagingController {
    private final IrrVerbAdminService irrVerbAdminService;
    private final VerbGroupMarkService verbGroupMarkService;

    @GetMapping("/grMark")
    public ResponseEntity<?> getAllVerbGroups() {
        List<VerbGroupMark> verbGroupMarks = verbGroupMarkService.findAllGroup();
        return new ResponseEntity<>(verbGroupMarks, HttpStatus.OK);
    }

    @GetMapping(value = "/allVerbs/{id}")
    public ResponseEntity<Optional<IrrVerbAllForm>> getSingleVerb(@PathVariable Long id) {
        final Optional<IrrVerbAllForm> verb = irrVerbAdminService.findById(id);
        return verb.isPresent()
                ? new ResponseEntity<>(verb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // add new verbs by VordWerb
    @PostMapping("/addNewVerbs")
    public ResponseEntity<?> addNewVerbs(@RequestBody IrrVerbAllForm irrVerbAllForm) {
        Optional<IrrVerbAllForm> currentVerb = irrVerbAdminService.findByWord(irrVerbAllForm.getPresentSimple());
//        System.out.println("isEmpty() = " + currentVerb.isEmpty());
        if (currentVerb.isEmpty()) {
            irrVerbAdminService.saveNewWords(irrVerbAllForm);
            return new ResponseEntity<>(irrVerbAllForm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/deleteVerbs/{id}")
    public ResponseEntity<?> deleteVerb(@PathVariable Long id) {
        Optional<IrrVerbAllForm> currentVerb = irrVerbAdminService.findById(id);
        System.out.println("isPresent() = " + currentVerb.isPresent());
        if (currentVerb.isPresent()) {
            irrVerbAdminService.deleteVerbsById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/searchWord")
    public ResponseEntity<IrrVerbAllForm> searchByWordPs(@RequestParam String word) {
        Optional<IrrVerbAllForm> currentVerb = irrVerbAdminService.findByWordPresentSimple(word);
        if (currentVerb.isPresent()) {
            return new ResponseEntity<>(currentVerb.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/wordPhonetic/{idAllForm}")
    public ResponseEntity<?> getPhoneticByWord(@PathVariable Long idAllForm) {
        Optional<IrrVerbAllForm> currentVerb = irrVerbAdminService.findById(idAllForm);
//        System.out.println("isPresent() = " + currentVerb.isPresent());
//        System.out.println("isEmpty() = " + currentVerb.isEmpty());
        if (currentVerb.isPresent()) {
            List<IrrVerbWord> wordList = irrVerbAdminService.findByVerbAllForm(currentVerb.get());
            return new ResponseEntity<>(wordList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/updateVerbs/{idAllForm}")
    public ResponseEntity<?> updateVerbAllForm(@PathVariable Long idAllForm, @RequestBody IrrVerbAllForm irrVerbAllForm) {
        Optional<IrrVerbAllForm> currentVerb = irrVerbAdminService.findById(idAllForm);
        System.out.println("isPresent() = " + currentVerb.isPresent());
        System.out.println("isEmpty() = " + currentVerb.isEmpty());
        if (currentVerb.isPresent()) {
            irrVerbAdminService.updateVerbAllForm(idAllForm, irrVerbAllForm);
            return new ResponseEntity<>(currentVerb.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteBeforeUpdate")
    public ResponseEntity<?> deleteByWordByTense(
            @RequestParam Long idAllForm, @RequestParam Tenses tenses, @RequestParam String word) {
        Optional<IrrVerbAllForm> currentAllForm = irrVerbAdminService.findById(idAllForm);
        Optional<IrrVerbWord> currentWord = irrVerbAdminService.findByWordAndTenses(tenses, word, currentAllForm.get());
        System.out.println("currentAllForm.isPresent() = " + currentAllForm.isPresent());
        System.out.println("currentWord.isPresent() + " + currentWord.isPresent());
        if (currentWord.isPresent()) {
            System.out.println(currentWord.get().toString());
            irrVerbAdminService.deleteVerbsAndPhonetics(currentWord.get().getId());
            return new ResponseEntity<>(currentAllForm, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //,
//            @RequestBody IrrVerbWord irrVerbWord
    @PutMapping(value = "/newAfterUpdate") //add new word after update word in IrrVerbAllForm table
    public ResponseEntity<?> addNewWordInVerbWord(
            @RequestParam Long idAllForm, @RequestParam Tenses tenses, @RequestParam String word) {
        Optional<IrrVerbAllForm> currentAllForm = irrVerbAdminService.findById(idAllForm);
        Optional<IrrVerbWord> currentWord = irrVerbAdminService.findByWordAndTenses(tenses, word, currentAllForm.get());
        System.out.println("currentAllForm.isPresent() = " + currentAllForm.isPresent());
        System.out.println("currentWord.isEmpty() + " + currentWord.isEmpty());
        if (currentAllForm.isPresent() && currentWord.isEmpty()) {
            IrrVerbWord ivw = irrVerbAdminService.newWordAfterUpdate(currentAllForm.get(), tenses, word);
            Optional<IrrVerbWord> ww = irrVerbAdminService.findByWordAndTenses(tenses, word, currentAllForm.get());
            List<IrrVerbPhonetic> wph = irrVerbAdminService.findPhoneticByWord(ww.get());
            return new ResponseEntity<>(wph, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //generate new ID from new IrrVerbAllForm
    @GetMapping("/gen")
    public ResponseEntity<?> generateId() {
        irrVerbAdminService.filUpVerbWAndPhonetic();
        return new ResponseEntity<>(HttpStatus.OK);
    }

// change to Optional !!! move to verbController
    @GetMapping("/binaryGet/{id}") //http://localhost:8080/adm_verbs/binaryGet/5
    public ResponseEntity<?> getMediaFileFromDB(@PathVariable Long id) {
        IrrVerbPhonetic phonetic = irrVerbAdminService.findByPhoneticId(id);

        String fileName = phonetic.getIrrVerbWord().getWord() + ".mp3";
        System.out.println(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(phonetic.getFileAudioDb());
    }


//    ///to Excel
//    @GetMapping("/download")
//    public ResponseEntity<Resource> getFile() {
//        String filename = "tutorials.xlsx";
//        InputStreamResource file = new InputStreamResource(verbService.loadToExcel());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(file);
//    }
//
//    //from Excel
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        if (ExcelVerbConverter.hasExcelFormat(file)) {
//            try {
//                System.out.println(file.getOriginalFilename());
////                verbService.saveFromExcel(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//        message = "Please upload an excel file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }



}
