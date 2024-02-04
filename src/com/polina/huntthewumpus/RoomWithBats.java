package com.polina.huntthewumpus;

class RoomWithBats extends Room {
    public RoomWithBats(int id, ConnectionMap map, int numberOfRooms) {
        super(id, map, numberOfRooms);
    }

    String getRoomMessage() {
        return "DO YOU HEAR A NOISE? PROBABLY BATS NEARBY!";
    }

    @Override
    Room enterRoomAndGetNext(Cave cave) {
        Room nextRoom = super.enterRoomAndGetNext(cave);
        if (nextRoom == null) return null;

        System.out.println("OH, SHIT! THERE ARE SUPER BATS HEAR!");
        System.out.println("ANOTHER ROOM FOR YOU!");

        int roomChosenByBats = Cave.RANDOM.nextInt(cave.roomCount());
        while (cave.get(roomChosenByBats) instanceof RoomWithBats) {
            roomChosenByBats = Cave.RANDOM.nextInt(cave.roomCount());
        }

        return cave.get(roomChosenByBats).enterRoomAndGetNext(cave);
    }
}
