package automation.threads;

class Client extends Thread {
    private ParkingLot parkingLot;
    private int idOfClient;

    Client(ParkingLot parkingLot, int id) {
        this.parkingLot = parkingLot;
        this.idOfClient = id;
    }

    public void run() {
        ParkingSpot spot = null;

        try {
            spot = parkingLot.getVacantParkingSpot(400);
            if (spot != null) {
                System.out.println("Client [id #" + idOfClient + "] has occupied the spot № "
                                   + spot.getSpotId() + ".");
                spot.occupySpot();
            }
        } catch (WaitingPeriodExceededException exception) {
            System.out.println("\n(!)\tClient [id #" + idOfClient + "] has been lost:\t"
                               + exception.getMessage());
        } finally {
            if (spot != null) {
                System.out.println("\n--> Client [id #" + idOfClient + "] has vacated the spot № "
                                   + spot.getSpotId() + ".");
                parkingLot.vacateSpot(spot);
            }
        }
    }
}