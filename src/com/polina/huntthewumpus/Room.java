package com.polina.huntthewumpus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class Room implements Comparable<Room> {
    final int id;
    final int[] connectingRooms;

    Room(int id, ConnectionMap map, int numberOfRooms) {
        this.id = id;
        this.connectingRooms = map.getConnections(id, numberOfRooms);
    }

    void printInformation(Cave cave) {
        System.out.println("HUNT THE WUMPUS\n");
        System.out.println("YOU ARE IN ROOM " + id + ".");

        String adjacentRooms = Arrays.stream(connectingRooms)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        System.out.println("TUNNELS LEAD TO " + adjacentRooms + ".");

        Set<String> messageSet = new HashSet<>();
        for (int num : connectingRooms) {
            if (cave.getWumpusLocation() == num) System.out.println("UGH! IT SMELLS SO BAD!");
            messageSet.add(cave.get(num).getRoomMessage());
        }
        messageSet.remove("");
        for (String num : messageSet) System.out.println(num);
    }

    String getRoomMessage() {
        return "";
    }

    Room enterRoomAndGetNext(Cave cave) {
        return cave.checkWumpusPresenceAndPrint(id) ? null : this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Room o) {
        return Integer.compare(id, o.id);
    }

    public boolean isConnectingWith(int nextRoomId) {
        for (int roomId : connectingRooms) {
            if (nextRoomId == roomId) return true;
        }
        return false;
    }
}


