package automation.threads;

import java.util.Random;

class ParkingSpot {
    private int spotId;

    ParkingSpot(int spotId) {
        this.spotId = spotId;
    }

    int getSpotId() {
        return spotId;
    }

    void isOccupied() {
        try {
            Thread.sleep(new Random().nextInt(300));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}