package com.polina.huntthewumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class AllRoomsGenerator {

    int numberOfRooms;
    List<Integer> specialRoomNumbers;

    AllRoomsGenerator(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;

        List<Integer> list = new ArrayList<>();
        for (int room = 0; room < numberOfRooms; room++) {
            list.add(room);
        }
        Collections.shuffle(list);
        specialRoomNumbers = list.subList(0, 4);
    }

    List<Room> generateRooms(String roomsLayout) {

        ConnectionMap connectionMap = ConnectionMap.findById(roomsLayout);

        List<Room> listOfRooms = new ArrayList<>();

        for (int counter = 0; counter < numberOfRooms; counter++) {
            if (!specialRoomNumbers.contains(counter)) {
                Room room = new Room(counter, connectionMap, numberOfRooms);
                listOfRooms.add(room);
            }
        }

        RoomWithBats firstRoomWithBats = new RoomWithBats(specialRoomNumbers.get(0), connectionMap, numberOfRooms);
        RoomWithBats secondRoomWithBats = new RoomWithBats(specialRoomNumbers.get(1), connectionMap, numberOfRooms);
        RoomWithPits firstRoomWithPits = new RoomWithPits(specialRoomNumbers.get(2), connectionMap, numberOfRooms);
        RoomWithPits secondRoomWithPits = new RoomWithPits(specialRoomNumbers.get(3), connectionMap, numberOfRooms);
        listOfRooms.addAll(Arrays.asList(firstRoomWithBats, secondRoomWithBats, firstRoomWithPits, secondRoomWithPits));

        Collections.sort(listOfRooms);
        return listOfRooms;
    }

    int createFirstRoomNumber() {
        int firstRoom = Cave.RANDOM.nextInt(numberOfRooms);
        while (specialRoomNumbers.contains(firstRoom)) {
            firstRoom = Cave.RANDOM.nextInt(numberOfRooms);
        }
        return firstRoom;
    }


}
