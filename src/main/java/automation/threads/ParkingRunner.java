package automation.threads;

import java.util.LinkedList;
import java.util.Random;

public class ParkingRunner {
    public static void main(String[] args) {
        int numberOfClients = ParkingLot.PARKING_LOT_CAPACITY + new Random().nextInt(20);

        LinkedList<ParkingSpot> listOfVacantParkingSpots = new LinkedList<>();
        for (int i = 1; i <= ParkingLot.PARKING_LOT_CAPACITY; i++) {
            listOfVacantParkingSpots.add(new ParkingSpot(i));
        }

        ParkingLot parkingLot = new ParkingLot(listOfVacantParkingSpots);
        for (int i = 1; i <= numberOfClients; i++) {
            new Client(parkingLot, i).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}