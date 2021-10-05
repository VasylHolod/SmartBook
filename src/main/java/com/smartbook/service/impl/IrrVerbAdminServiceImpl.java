package com.smartbook.service.impl;

import com.smartbook.entity.IrrVerbAllForm;
import com.smartbook.entity.IrrVerbPhonetic;
import com.smartbook.entity.IrrVerbWord;
import com.smartbook.entity.enums.Dialect;
import com.smartbook.entity.enums.Tenses;
import com.smartbook.repository.IrrVerbAllFormRepo;
import com.smartbook.repository.IrrVerbPhoneticRepo;
import com.smartbook.repository.IrrVerbWordRepo;
import com.smartbook.service.IrrVerbAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IrrVerbAdminServiceImpl implements IrrVerbAdminService {
    private final IrrVerbAllFormRepo irrVerbAllFormRepo;
    private final IrrVerbWordRepo irrVerbWordRepo;
    private final IrrVerbPhoneticRepo irrVerbPhoneticRepo;

    @Override
    public Optional<IrrVerbAllForm> findByWord(String presentSimple) {
        return irrVerbAllFormRepo.findByPresentSimpleLike(presentSimple);
    }

    @Override
    public void deleteVerbsById(Long id) {
        irrVerbAllFormRepo.deleteById(id);
    }

    @Override
    public Optional<IrrVerbAllForm> findByWordPresentSimple(String word) {
        return irrVerbAllFormRepo.findByPresentSimpleLike(word);
    }

    @Override
    public void updateVerbAllForm(Long id, IrrVerbAllForm irrVerbAllForm) {
//        IrrVerbAllForm irrVerbAllForm = irrVerbAllFormRepo.getOne(id);
        irrVerbAllFormRepo.save(irrVerbAllForm);
    }

    @Override
    public List<IrrVerbWord> findByVerbAllForm(IrrVerbAllForm irrVerbAllForm) {
        return irrVerbWordRepo.findAllByIrrVerbAllForm(irrVerbAllForm);
    }


    @Override
    public List<IrrVerbPhonetic> findPhoneticByWord(IrrVerbWord irrVerbWord) {
        return irrVerbPhoneticRepo.findByIrrVerbWord(irrVerbWord);
    }

    @Override
    public Optional<IrrVerbWord> findByWordAndTenses(Tenses tenses, String word, IrrVerbAllForm irrVerbAllForm) {
        return irrVerbWordRepo.findByWordAndAndTensesAndAndIrrVerbAllForm(word, tenses, irrVerbAllForm);
    }

    @Override
    public void deleteVerbsAndPhonetics(long id) {
        irrVerbWordRepo.deleteById(id);
//        irrVerbWordRepo.deleteById(id);
    }

    @Override
    public IrrVerbWord newWordAfterUpdate(IrrVerbAllForm irrVerbAllForm, Tenses tenses, String word) {
        System.out.println(irrVerbAllForm.getId());
        System.out.println(tenses.getName());
        System.out.println(word);
        System.out.println();
        IrrVerbWord irrVerbWord = new IrrVerbWord(word, tenses, irrVerbAllForm);
        irrVerbWordRepo.save(irrVerbWord);
        irrVerbPhoneticRepo.save(new IrrVerbPhonetic(Dialect.GB, (irrVerbWord)));
        irrVerbPhoneticRepo.save(new IrrVerbPhonetic(Dialect.US, (irrVerbWord)));
        return irrVerbWord;
    }

    @Override
    public void saveNewWords(IrrVerbAllForm irrVerbAllForm) {
        irrVerbAllFormRepo.save(irrVerbAllForm);
        createNewWordAndPhonetic(irrVerbAllForm);
    }

    @Override
    public void filUpVerbWAndPhonetic() {
//        List<Long> idd = new ArrayList<>();
//        idd.add(416L);
//        idd.add(417L);
//        idd.add(418L);
//        List<IrrVerbAllForm> irrVerbAllForms = irrVerbAllFormRepo.findAllById(idd);
        List<IrrVerbAllForm> irrVerbAllForms = irrVerbAllFormRepo.findAll();
        for (IrrVerbAllForm irrVerbAllForm : irrVerbAllForms) {
            createNewWordAndPhonetic(irrVerbAllForm);

        }
    }

    private void createNewWordAndPhonetic(IrrVerbAllForm irrVerbAllForm) {
        List<IrrVerbWord> wordList = new ArrayList<>();
        wordList.add(new IrrVerbWord(irrVerbAllForm.getPresentSimple(), Tenses.PRESENT_SIMPLE, irrVerbAllForm));
        wordList.add(new IrrVerbWord(irrVerbAllForm.getPastSimple(), Tenses.PAST_SIMPLE, irrVerbAllForm));
        wordList.add(new IrrVerbWord(irrVerbAllForm.getPastParticiple(), Tenses.PAST_PARTICIPLE, irrVerbAllForm));
        wordList.add(new IrrVerbWord(irrVerbAllForm.getIngForm(), Tenses.ING_FORM, irrVerbAllForm));
        wordList.add(new IrrVerbWord(irrVerbAllForm.getThirdPerson(), Tenses.THIRD_PERSON_SINGULAR, irrVerbAllForm));
        for (IrrVerbWord irrVerbWord : wordList) {
            irrVerbWordRepo.save(irrVerbWord);
            irrVerbPhoneticRepo.save(new IrrVerbPhonetic(Dialect.GB, (irrVerbWord)));
            irrVerbPhoneticRepo.save(new IrrVerbPhonetic(Dialect.US, (irrVerbWord)));
        }
    }


    @Override
    public Optional<IrrVerbAllForm> findById(Long id) {
        return irrVerbAllFormRepo.findById(id);
    }

    @Override
    public IrrVerbPhonetic findByPhoneticId(Long id){
        return irrVerbPhoneticRepo.findById(id).get();
    }
}
