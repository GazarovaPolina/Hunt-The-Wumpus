package com.polina.huntthewumpus;

enum ConnectionMap {
    CORRIDORS("cor") {
        int findConnectingRoom(int currentRoom, int numberOfRooms) {
            int connectingRoom = currentRoom - 2;
            if (currentRoom == 0) {
                connectingRoom = numberOfRooms - 2;
            } else if (currentRoom == 1) {
                connectingRoom = numberOfRooms - 1;
            } else if (currentRoom == numberOfRooms - 2) {
                connectingRoom = 0;
            } else if (currentRoom == numberOfRooms - 1) {
                connectingRoom = 1;
            }
            return connectingRoom;
        }

        @Override
        public int[] getConnections(int currentRoom, int numberOfRooms) {
            int firstConnectingRoom = currentRoom - 2;
            int secondConnectingRoom;
            int thirdConnectingRoom = currentRoom + 2;

            if (currentRoom < 2) firstConnectingRoom = findConnectingRoom(currentRoom, numberOfRooms);

            if (currentRoom > numberOfRooms - 3) thirdConnectingRoom = findConnectingRoom(currentRoom, numberOfRooms);

            if (currentRoom % 2 == 0) {
                secondConnectingRoom = currentRoom + 1;
            } else {
                secondConnectingRoom = currentRoom - 1;
            }
            return new int[]{firstConnectingRoom, secondConnectingRoom, thirdConnectingRoom};
        }
    },
    DODECAHEDRON("dod") {
        @Override
        public int[] getConnections(int currentRoom, int numberOfRooms) {
            return switch (currentRoom) {
                case 0 -> new int[]{5, 8, 17};
                case 1 -> new int[]{6, 8, 15};
                case 2 -> new int[]{9, 10, 18};
                case 3 -> new int[]{10, 11, 15};
                case 4 -> new int[]{12, 17, 18};
                case 5 -> new int[]{0, 12, 14};
                case 6 -> new int[]{1, 11, 17};
                case 7 -> new int[]{13, 15, 16};
                case 8 -> new int[]{0, 1, 13};
                case 9 -> new int[]{2, 12, 19};
                case 10 -> new int[]{2, 3, 16};
                case 11 -> new int[]{3, 6, 18};
                case 12 -> new int[]{4, 5, 9};
                case 13 -> new int[]{7, 8, 14};
                case 14 -> new int[]{5, 13, 19};
                case 15 -> new int[]{1, 3, 7};
                case 16 -> new int[]{7, 10, 19};
                case 17 -> new int[]{0, 4, 6};
                case 18 -> new int[]{2, 4, 11};
                case 19 -> new int[]{9, 14, 16};
                default -> throw new IllegalArgumentException("Current room " + currentRoom + " doesn't exist");
            };
        }
    };

    private final String id;

    ConnectionMap(String id) {
        this.id = id;
    }

    static ConnectionMap findById(String id) {
        for (ConnectionMap generator : ConnectionMap.values()) {
            if (generator.id.equalsIgnoreCase(id)) return generator;
        }
        throw new IllegalArgumentException("No generators with id " + id);
    }

    abstract int[] getConnections(int currentRoom, int numberOfRooms);
}
