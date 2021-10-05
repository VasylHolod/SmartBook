package com.smartbook.service;

import com.smartbook.entity.IrrVerbAllForm;
import com.smartbook.entity.IrrVerbPhonetic;
import com.smartbook.entity.IrrVerbWord;
import com.smartbook.entity.enums.Tenses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IrrVerbAdminService {

    Optional<IrrVerbAllForm> findByWord(String presentSimple);

    void saveNewWords(IrrVerbAllForm irrVerbAllForm);

    Optional<IrrVerbAllForm> findById(Long id);

    void deleteVerbsById(Long id);

    Optional<IrrVerbAllForm> findByWordPresentSimple(String word);

    List<IrrVerbWord> findByVerbAllForm(IrrVerbAllForm irrVerbAllForm);

    void updateVerbAllForm(Long id, IrrVerbAllForm irrVerbAllForm);

    IrrVerbWord newWordAfterUpdate(IrrVerbAllForm irrVerbAllForm, Tenses tenses, String word);

    Optional<IrrVerbWord> findByWordAndTenses(Tenses tenses, String word, IrrVerbAllForm irrVerbAllForm);

    List<IrrVerbPhonetic> findPhoneticByWord(IrrVerbWord irrVerbWord);

    void deleteVerbsAndPhonetics(long id);

    void filUpVerbWAndPhonetic();

    IrrVerbPhonetic findByPhoneticId(Long id);
}
