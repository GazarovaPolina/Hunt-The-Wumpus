package com.polina.huntthewumpus;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Scan {
    private final Scanner scan = new Scanner(System.in);

    String nextLine() {
        return scan.nextLine();
    }

    int nextInt() {
        return scan.nextInt();
    }

    String inputCorOrDod() {
        String roomsLayout;
        while (true) {
            System.out.println("Enter 'cor' if you want a 'corridor' or 'dod' if you want 'dodecahedron':");
            try {
                roomsLayout = nextLine();
                if (roomsLayout.equals("cor") || roomsLayout.equals("dod")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error. Try again.");
            }
        }
        return roomsLayout;
    }

    int inputNumberOfRoomsInCaseCor() {
        int numberOfRooms;
        while (true) {
            System.out.println("Enter the number of rooms (number must be > 5) :");
            try {
                numberOfRooms = nextInt();
                if (numberOfRooms > 5) {
                    break;
                }
            } catch (InputMismatchException e) {
                nextLine();
            }
        }
        return numberOfRooms;
    }

    String inputSorM() {
        String gamerMove;
        while (true) {
            System.out.println("SHOOT OR MOVE? (S / M)?");
            try {
                gamerMove = nextLine();
                if (gamerMove.equalsIgnoreCase("s") || gamerMove.equalsIgnoreCase("m")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error. Try again.");
            }
        }
        return gamerMove;
    }

    int inputRoomToGoTo(Room room) {
        int roomSelection;
        input:
        while (true) {
            System.out.println("WHERE TO?");
            try {
                roomSelection = nextInt();
                for (int currRoom : room.connectingRooms) {
                    if (currRoom == roomSelection) {
                        break input;
                    }
                }
            } catch (InputMismatchException e) {
                nextLine();
            }
        }
        return roomSelection;
    }

    List<Integer> inputRoomNumbersToShoot(int numberOfRooms) {
        List<Integer> gamerListOfRooms = new ArrayList<>();

        input:
        while (true) {
            gamerListOfRooms.clear();

            System.out.println("ENTER ROOM NUMBER(S):");
            String nextMoveInput = nextLine();

            String[] parts = nextMoveInput.trim().split(" ");
            if (parts.length > 5) {
                System.out.println("Too many rooms.");
                continue;
            }

            try {
                for (String part : parts) {
                    int roomIndex = Integer.parseInt(part);
                    if (roomIndex >= numberOfRooms) {
                        System.out.println("The number is too large: " + roomIndex + ".");
                        continue input;
                    }
                    if (roomIndex < 0) {
                        System.out.println("The number is too small: " + roomIndex + ".");
                        continue input;
                    }
                    gamerListOfRooms.add(roomIndex);
                }
                break;
            } catch (NumberFormatException ex) {
                for (String part : parts) {
                    if (part.equals(",")) {
                        System.out.println("Unnecessary symbol ','. Use the space bar.");
                        continue input;
                    }
                }
                System.out.println("Only numbers allowed.");
            }
        }
        return gamerListOfRooms;
    }

}
