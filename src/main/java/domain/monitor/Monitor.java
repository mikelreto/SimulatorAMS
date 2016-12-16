package domain.monitor;

import domain.model.Flight;
import domain.model.Lane;


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
    public synchronized void enterPista(final Lane currentLine,
                                        final Lane nextLine,
                                        final Flight vuelo)
                                        throws InterruptedException {
         while (!nextLine.getTaken().isEmpty()) {
          // if () { Dentro de la zona de espera
               vuelo.getPlane().setPosX(vuelo.getPlane().getPosX()
                                           /*+ currentLine.getPosX()*/);
               vuelo.getPlane().setPosY(vuelo.getPlane().getPosY()
                                           /*+ currentLine.getMoveY()*/);
          //  } else {
               wait();
         //}
         }
         // while () { Fuera de la zona de espera
               move(currentLine, vuelo);
               vuelo.getPlane().setPosX(vuelo.getPlane().getPosX()
                 /*+ currentLine.getMoveX()*/);
               vuelo.getPlane().setPosY(vuelo.getPlane().getPosY()
                 /*+ currentLine.getMoveY()*/);
   try {
      Thread.sleep(ONESECOND);
    } catch (InterruptedException ex) {
           Thread.currentThread().interrupt();
    }
         //}
   nextLine.setTaken("Y");

   //vuelo.getAirplane().setPosX(nextLine.getInitialPosX());
   //cuelo.getAirplane().setPosY(nextLine.getInitialPosY());
   currentLine.setTaken("N");
   notifyAll();
    }

    /**
     * Function to move the airplane to the next position.
     * @param currentLine is the current line of the airplane
     * @param vuelo is the flight of the airplane
     */
    private void move(final Lane currentLine,
                      final Flight vuelo) {
        vuelo.getPlane().setPosX(vuelo.getPlane().getPosX()
                                    /*+ currentLine.getMoveX()*/);
        vuelo.getPlane().setPosY(vuelo.getPlane().getPosY()
                                    /*+ currentLine.getMoveY()*/);
    }

}
