package com.example.birthdayapp.service

import com.example.birthdayapp.converter.AnimatorConverter
import com.example.birthdayapp.domain.AnimatorResource
import com.example.birthdayapp.entity.Animator
import com.example.birthdayapp.exception.ExistAnimatorException
import com.example.birthdayapp.exception.NotFoundAnimatorException
import com.example.birthdayapp.repository.AnimatorRepository
import spock.lang.Specification

class AnimatorServiceTest extends Specification {


    def "GetAllAnimators"() {
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)

        def animatorMock = new Animator()
        animatorMock.setRanking(2)
        animatorMock.setName("lala")
        animatorMock.setId(2L)
        animatorMock.setScheduleAvailable("asda")


        List<Animator> list = new ArrayList()
        list.add(animatorMock)

        when:
        animatorService.getAll()

        then:
        1 * animatorRepository.findAll() >> list
        1 * animatorConverter.convert(list)
    }

    def "GetAllAnimatorWithException"() {


        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)


        when:
        animatorService.getAll()

        then:
        thrown(NotFoundAnimatorException.class)
    }

    def "Create RoomEvent"(){
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)


        def animatorResourceMock = new AnimatorResource()
        animatorResourceMock.setRanking(2)
        animatorResourceMock.setName("lala")
        animatorResourceMock.setId(2L)
        animatorResourceMock.setScheduleAvailable("asda")


        when:
        animatorService.create(animatorResourceMock)

        then:
        1 * animatorConverter.convert(animatorResourceMock)
        1 * animatorRepository.save(_)
    }



    def "findOneAnimator"(){
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)

        def animatorMock = new Animator()
        animatorMock.setRanking(2)
        animatorMock.setName("lala")
        animatorMock.setId(2L)
        animatorMock.setScheduleAvailable("asda")

        when:
        animatorService.findOne(1L)

        then:
        1 * animatorRepository.findById(_) >> Optional.ofNullable(animatorMock)
        1 * animatorConverter.convert(animatorMock)
    }


    def "findOneAnimatorWithException"(){

        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)


        when:
        animatorService.findOne(1L)

        then:
        1 * animatorRepository.findById(_) >> Optional.ofNullable(null)
        thrown(ExistAnimatorException.class)
    }

    def "updateAnimator"(){
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)



        def animatorResourceMock = new AnimatorResource()
        animatorResourceMock.setRanking(2)
        animatorResourceMock.setName("lala")
        animatorResourceMock.setId(2L)
        animatorResourceMock.setScheduleAvailable("asda")

        def animatorMock = new Animator()
        animatorMock.setRanking(2)
        animatorMock.setName("lala")
        animatorMock.setId(2L)
        animatorMock.setScheduleAvailable("asda")

        when:
        animatorService.update(animatorResourceMock)


        then:
        1 * animatorRepository.findById(animatorResourceMock.getId()) >> Optional.ofNullable(animatorMock)
        1 * animatorConverter.convert(animatorMock,animatorResourceMock)
        1 * animatorRepository.save(animatorMock)
    }

    def "updateRoomEventWithException"(){
        given:
        def animatorRepository = Mock(AnimatorRepository)
        def animatorConverter = Mock(AnimatorConverter)
        def animatorService = new AnimatorService(animatorRepository,animatorConverter)



        def animatorResourceMock = new AnimatorResource()
        animatorResourceMock.setRanking(2)
        animatorResourceMock.setName("lala")
        animatorResourceMock.setId(2L)
        animatorResourceMock.setScheduleAvailable("asda")

        when:
        animatorService.update(animatorResourceMock)


        then:
        1 * animatorRepository.findById(_) >> Optional.ofNullable(null)
        thrown(ExistAnimatorException.class)

    }

}
