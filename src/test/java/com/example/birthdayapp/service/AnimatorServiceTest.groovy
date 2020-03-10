package com.example.birthdayapp.service

import com.example.birthdayapp.converter.AnimatorConverter
import com.example.birthdayapp.repository.AnimatorRepository
import spock.lang.Specification

class AnimatorServiceTest extends Specification {


    def "GetAllAnimators"() {
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)

        when:
        animatorService.getAll()

        then:
        1 * animatorRepository.findAll()
        1 * animatorConverter.convert(_)
    }
}
