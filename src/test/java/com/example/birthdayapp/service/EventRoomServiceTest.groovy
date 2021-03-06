package com.example.birthdayapp.service

import com.example.birthdayapp.converter.EventRoomConverter
import com.example.birthdayapp.domain.EventRoomResource
import com.example.birthdayapp.entity.EventRoom
import com.example.birthdayapp.exception.ExistRoomException
import com.example.birthdayapp.exception.NotFoundRoomException
import com.example.birthdayapp.repository.EventRoomRepository
import spock.lang.Specification

class EventRoomServiceTest extends Specification {


    def "GetAllRoomEvents"() {
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)

        def eventRoomMock = new EventRoom()
        eventRoomMock.setRanking(2)
        eventRoomMock.setCapacity(2L)
        eventRoomMock.setName("lala")
        eventRoomMock.setId(2L)
        eventRoomMock.setScheduleAvailable("asda")


        List<EventRoom> eventRoomMockList = new ArrayList()
        eventRoomMockList.add(eventRoomMock)


        when:
        eventRoomService.getAll()

        then:
        1 * eventRoomRepository.findAll() >> eventRoomMockList
        1 * eventRoomConverter.convert(eventRoomMockList)
    }


    def "GetAllRoomEvenWithException"() {


        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)


        when:
        eventRoomService.getAll()

        then:
        thrown(NotFoundRoomException.class)
    }

    def "Create RoomEvent"(){
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)

        def eventRoomResourceMock = new EventRoomResource()
        eventRoomResourceMock.setRanking(2)
        eventRoomResourceMock.setCapacity(2L)
        eventRoomResourceMock.setName("lala")
        eventRoomResourceMock.setId(2L)
        eventRoomResourceMock.setScheduleAvailable("asda")


        when:
        eventRoomService.create(eventRoomResourceMock)

        then:
        1 * eventRoomRepository.existsByName(eventRoomResourceMock.getName()) >> false
        1 * eventRoomConverter.convert(eventRoomResourceMock)
        1 * eventRoomRepository.save(_)
    }


    def "Create RoomEventWithException"(){
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)



        def eventRoomResourceMock = new EventRoomResource()
        eventRoomResourceMock.setRanking(2)
        eventRoomResourceMock.setCapacity(2L)
        eventRoomResourceMock.setName("lala")
        eventRoomResourceMock.setId(2L)
        eventRoomResourceMock.setScheduleAvailable("asda")


        when:
        eventRoomService.create(eventRoomResourceMock)

        then:
        1 * eventRoomRepository.existsByName(eventRoomResourceMock.getName()) >> true
        thrown(ExistRoomException.class)
    }

    def "findOneEventRoom"(){
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)

        def eventRoomMock = new EventRoom()
        eventRoomMock.setRanking(2)
        eventRoomMock.setCapacity(2L)
        eventRoomMock.setName("lala")
        eventRoomMock.setId(2L)
        eventRoomMock.setScheduleAvailable("asda")

        when:
        eventRoomService.findOne(1L)

        then:
        1 * eventRoomRepository.findById(_) >> Optional.ofNullable(eventRoomMock)
        1 * eventRoomConverter.convert(eventRoomMock)
    }


    def "findOneEventRoomWithException"(){

        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)

        when:
        eventRoomService.findOne(1L)

        then:
        1 * eventRoomRepository.findById(_) >> Optional.ofNullable(null)
        thrown(ExistRoomException.class)
    }

    def "updateRoomEvent"(){
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)


        def eventRoomResourceMock = new EventRoomResource()
        eventRoomResourceMock.setRanking(2)
        eventRoomResourceMock.setCapacity(2L)
        eventRoomResourceMock.setName("lala")
        eventRoomResourceMock.setId(2L)
        eventRoomResourceMock.setScheduleAvailable("asda")

        def eventRoomMock = new EventRoom()
        eventRoomMock.setRanking(2)
        eventRoomMock.setCapacity(2L)
        eventRoomMock.setName("lala")
        eventRoomMock.setId(2L)
        eventRoomMock.setScheduleAvailable("asda")

        when:
        eventRoomService.update(eventRoomResourceMock)


        then:
        1 * eventRoomRepository.findById(eventRoomResourceMock.getId()) >> Optional.ofNullable(eventRoomMock)
        1 * eventRoomConverter.convert(eventRoomMock,eventRoomResourceMock)
        1 * eventRoomRepository.save(eventRoomMock)
    }

    def "updateRoomEventWithException"(){
        given:
        def eventRoomRepository = Mock(EventRoomRepository)
        def eventRoomConverter = Mock(EventRoomConverter)

        def eventRoomService = new EventRoomService(eventRoomRepository,eventRoomConverter)


        def eventRoomResourceMock = new EventRoomResource()
        eventRoomResourceMock.setRanking(2)
        eventRoomResourceMock.setCapacity(2L)
        eventRoomResourceMock.setName("lala")
        eventRoomResourceMock.setId(2L)
        eventRoomResourceMock.setScheduleAvailable("asda")

        when:
        eventRoomService.update(eventRoomResourceMock)


        then:
        1 * eventRoomRepository.findById(eventRoomResourceMock.getId()) >> Optional.ofNullable(null)
        thrown(ExistRoomException.class)

    }
}
