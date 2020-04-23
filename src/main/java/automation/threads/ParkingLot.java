package automation.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class ParkingLot {
    final static int PARKING_LOT_CAPACITY = 10;
    private final Semaphore semaphore = new Semaphore(PARKING_LOT_CAPACITY, true);
    private final Queue<ParkingSpot> vacantParkingSpots = new LinkedList<>();

    ParkingLot(Queue<ParkingSpot> spots) {
        vacantParkingSpots.addAll(spots);
    }

    ParkingSpot getVacantParkingSpot(int maxWaitingPeriodInMilliseconds)
                                             throws WaitingPeriodExceededException {
        synchronized (vacantParkingSpots) {
            try {
                if (semaphore.tryAcquire(maxWaitingPeriodInMilliseconds, TimeUnit.MILLISECONDS)) {
                    return vacantParkingSpots.poll();
                }
            } catch (InterruptedException exception) {
                throw new WaitingPeriodExceededException(exception.getMessage());
            }
            throw new WaitingPeriodExceededException("Waiting time for a vacant parking spot has been exceeded.");
        }
    }

    void vacateSpot(ParkingSpot spotForVacation) {
        synchronized (vacantParkingSpots) {
            vacantParkingSpots.add(spotForVacation);
            semaphore.release();
        }
    }
}