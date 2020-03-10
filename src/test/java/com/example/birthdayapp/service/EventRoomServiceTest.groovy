package com.example.birthdayapp.service

import com.example.birthdayapp.converter.EventRoomConverter
import com.example.birthdayapp.domain.EventRoomResource
import com.example.birthdayapp.entity.EventRoom
import com.example.birthdayapp.exception.ExistRoomException
import com.example.birthdayapp.exception.NotFoundRoomException
import com.example.birthdayapp.repository.EventRoomRepository
import spock.lang.Specification

class EventRoomServiceTest extends Specification {


    def "GetAll"() {


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


    def "GetAllWithException"() {


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
}
