package com.example.birthdayapp.service

import com.example.birthdayapp.converter.GuestConverter
import com.example.birthdayapp.domain.GuestResource
import com.example.birthdayapp.entity.Guest
import com.example.birthdayapp.exception.NotFoundGuestException
import com.example.birthdayapp.repository.GuestRepository
import spock.lang.Specification

class GuestServiceTest extends Specification {


    def "GetAllGuest"() {
        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)

        def guestMock = new Guest()
        guestMock.setFirstName("Pedro")
        guestMock.setLastName("sarasa")
        guestMock.setPhone("lala")


        List<Guest> list = new ArrayList()
        list.add(guestMock)

        when:
        guestService.getAll()

        then:
        1 * guestRepository.findAll() >> list
        1 * guestConverter.convert(list)
    }

    def "GetAllguestWithException"() {


        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)


        when:
        guestService.getAll()

        then:
        thrown(NotFoundGuestException.class)
    }





    def "findOneguest"(){
        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)

        def guestMock = new Guest()
        guestMock.setFirstName("Pedro")
        guestMock.setLastName("sarasa")
        guestMock.setPhone("lala")

        when:
        guestService.findOne(1L)

        then:
        1 * guestRepository.findById(_) >> Optional.ofNullable(guestMock)
        1 * guestConverter.convert(guestMock)
    }


    def "findOneguestWithException"(){

        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)


        when:
        guestService.findOne(1L)

        then:
        1 * guestRepository.findById(_) >> Optional.ofNullable(null)
        thrown(NotFoundGuestException.class)
    }

    def "updateguest"(){
        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)



        def guestResourceMock = new GuestResource()
        guestResourceMock.setFirstName("Pedro")
        guestResourceMock.setLastName("sarasa")
        guestResourceMock.setPhone("lala")

        def guestMock = new Guest()
        guestMock.setFirstName("Pedro")
        guestMock.setLastName("sarasa")
        guestMock.setPhone("lala")

        when:
        guestService.update(guestResourceMock)


        then:
        1 * guestRepository.findById(guestResourceMock.getId()) >> Optional.ofNullable(guestMock)
        1 * guestConverter.convert(guestMock,guestResourceMock)
        1 * guestRepository.save(guestMock)
    }

    def "updateRoomEventWithException"(){
        given:
        def guestRepository = Mock(GuestRepository)
        def guestConverter = Mock(GuestConverter)
        def guestService = new GuestService(guestRepository,guestConverter)



        def guestResourceMock = new GuestResource()
        guestResourceMock.setFirstName("Pedro")
        guestResourceMock.setLastName("sarasa")
        guestResourceMock.setPhone("lala")

        when:
        guestService.update(guestResourceMock)


        then:
        1 * guestRepository.findById(_) >> Optional.ofNullable(null)
        thrown(NotFoundGuestException.class)

    }

}
