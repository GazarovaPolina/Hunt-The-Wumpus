package com.polina.huntthewumpus;

import java.util.List;

class Gamer {
    private final Cave cave;

    private int numberOfArrows = 5;
    boolean active = true;

    Gamer(Cave cave) {
        this.cave = cave;
    }

    int makeAMove(Room room, int numberOfRooms) {
        System.out.println();
        Scan getInf = new Scan();
        String gamerMove = getInf.inputSorM();

        if (gamerMove.equalsIgnoreCase("M")) {
            int roomSelection = getInf.inputRoomToGoTo(room);
            Room nextRoom = cave.get(roomSelection).enterRoomAndGetNext(cave);

            if (nextRoom == null) {
                active = false;
                return -1;
            }

            return nextRoom.id;
        } else {
            List<Integer> gamerListOfRooms = getInf.inputRoomNumbersToShoot(numberOfRooms);

            numberOfArrows--;

            if (numberOfArrows == 0) {
                System.out.println("ARROWS END! NOW WUMPUS WILL EAT YOU!");
                active = false;
                return -1;
            }

            System.out.println("YOU HAVE " + numberOfArrows + " ARROWS LEFT.");

            Room arrowRoom = room;
            for (int nextRoomId : gamerListOfRooms) {
                Room nextRoom = cave.get(nextRoomId);
                if (!arrowRoom.isConnectingWith(nextRoomId)) {
                    onArrowShotIncorrectly();
                    return active ? room.id : -1;
                }
                arrowRoom = nextRoom;
            }

            int wumpusRoom = gamerListOfRooms.get(gamerListOfRooms.size() - 1);
            if (cave.getWumpusLocation() == wumpusRoom) {
                System.out.println("HOORAY!!! YOU BEAT WUMPUS!!!");
                active = false;
            } else {
                System.out.println("YOU MADE A MISTAKE! WUMPUS IS NOT THERE!");
                cave.wakeAndPossiblyMoveWumpus();
            }

            return room.id;
        }
    }

    private void onArrowShotIncorrectly() {
        active = Cave.RANDOM.nextBoolean();
        if (!active) {
            System.out.println("YOU ARE KILLED BY YOUR OWN ARROW!");
        } else {
            System.out.println("YOU ARE LUCKY! THE ARROW FLEW BY AND WASN'T FELL IN YOU!");
        }
    }
}
