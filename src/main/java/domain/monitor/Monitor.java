package domain.monitor;

import domain.model.Flight;
import domain.model.Line;

/**
 * The monitor class.
 * @author PBL5
 *
 */
public class Monitor {

    /**
     * Number of milisencods to wait.
     */
    private static final int ONESECOND = 1000;

    /**
     * Function to enter to a new Line.
     * @param currentLine is the actual line.
     * @param nextLine is the next line.
     * @param vuelo is the flight that needs to see the lines.
     * @throws InterruptedException if there is any problem.
     */
    public synchronized void enterPista(final Line currentLine,
                                        final Line nextLine,
                                        final Flight vuelo)
                                        throws InterruptedException {
         while (nextLine.getTaken()) {
          // if () { Dentro de la zona de espera
               vuelo.getAirplane().setPosX(vuelo.getAirplane().getPosX()
                                           + currentLine.getMoveX());
               vuelo.getAirplane().setPosY(vuelo.getAirplane().getPosY()
                                           + currentLine.getMoveY());
          //  } else {
               wait();
         //}
         }
         // while () { Fuera de la zona de espera
               move(currentLine, vuelo);
               vuelo.getAirplane().setPosX(vuelo.getAirplane().getPosX()
                 + currentLine.getMoveX());
               vuelo.getAirplane().setPosY(vuelo.getAirplane().getPosY()
                 + currentLine.getMoveY());
   try {
      Thread.sleep(ONESECOND);
    } catch (InterruptedException ex) {
           Thread.currentThread().interrupt();
    }
         //}
   nextLine.setTaken(true);

   //vuelo.getAirplane().setPosX(nextLine.getInitialPosX());
   //cuelo.getAirplane().setPosY(nextLine.getInitialPosY());
   currentLine.setTaken(false);
   notifyAll();
    }

    /**
     * Function to move the airplane to the next position.
     * @param currentLine is the current line of the airplane
     * @param vuelo is the flight of the airplane
     */
    private void move(final Line currentLine,
                      final Flight vuelo) {
        vuelo.getAirplane().setPosX(vuelo.getAirplane().getPosX()
                                    + currentLine.getMoveX());
        vuelo.getAirplane().setPosY(vuelo.getAirplane().getPosY()
                                    + currentLine.getMoveY());
    }

}
