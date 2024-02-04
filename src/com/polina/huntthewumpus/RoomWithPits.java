package com.polina.huntthewumpus;

class RoomWithPits extends Room {
    public RoomWithPits(int id, ConnectionMap map, int numberOfRooms) {
        super(id, map, numberOfRooms);
    }

    String getRoomMessage() {
        return "WHAT A STRONG BREEZE!";
    }

    @Override
    Room enterRoomAndGetNext(Cave cave) {
        Room nextRoom = super.enterRoomAndGetNext(cave);
        if (nextRoom != null) System.out.println("YYYYAAAA... FELL IN PIT.");
        return null;
    }
}
