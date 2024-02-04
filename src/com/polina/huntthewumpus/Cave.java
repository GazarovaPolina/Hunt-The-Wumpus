package com.polina.huntthewumpus;

import java.util.List;
import java.util.Random;

class Cave {
    private final List<Room> listOfRooms;
    private int wumpusLocation;

    static final Random RANDOM = new Random();

    public Cave(List<Room> listOfRooms) {
        this.listOfRooms = listOfRooms;
        this.wumpusLocation = RANDOM.nextInt(listOfRooms.size());
    }

    void wakeAndPossiblyMoveWumpus() {
        System.out.println("WUMPUS WOKE UP!!");

        boolean wakeUp = RANDOM.nextInt(4) == 0;
        if (!wakeUp) return;

        int[] adjacentRooms = listOfRooms.get(wumpusLocation).connectingRooms;
        int randomIndex = RANDOM.nextInt(adjacentRooms.length);
        wumpusLocation = adjacentRooms[randomIndex];
    }

    Room get(int id) {
        return listOfRooms.get(id);
    }

    public int roomCount() {
        return listOfRooms.size();
    }

    public int getWumpusLocation() {
        return wumpusLocation;
    }

    boolean checkWumpusPresenceAndPrint(int room) {
        if (getWumpusLocation() != room) return false;

        System.out.println("GRRRR!");
        System.out.println("AAA!");
        System.out.println("YOU ARE THE WUMPUS'S SNACK.");

        return true;
    }
}
