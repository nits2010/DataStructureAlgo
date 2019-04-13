package Atlassian;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/04/19
 * Description:
 */
public class RobotMove {

    private static Map<Integer, String> map = new HashMap<>();


    static class Piles {

        public Piles(int width, int height) {
            this.width = width - 1;
            this.height = height;
            piles = new int[width];
        }

        public boolean drop(int position) {
            boolean positionValid = position <= (this.width);
            boolean pileAvailable = this.piles[position] < this.height;

            if (positionValid && pileAvailable) {
                piles[position]++;
            }

            return positionValid && pileAvailable;
        }

        @Override
        public String toString() {
            StringBuffer res = new StringBuffer();

            for (int i = 0; i < piles.length; i++) {


                res.append(getHexadecimalEquivalent(piles[i]));
            }
            return res.toString();
        }

        private String getHexadecimalEquivalent(int num) {
            if (num <= 15) {
                return map.getOrDefault(num, String.valueOf(num));
            } else {
                String va = String.format("%02x", num);
                return "0x"+va;
            }
        }


        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        private final int width;
        private final int height;

        private final int[] piles;

    }


    static class Robot {

        public Robot(Piles piles) {
            this.maxWidth = piles.getWidth();
            this.piles = piles;

        }

        public String execute(String command) {
            for (char cmd : command.toCharArray()) {
                switch (cmd) {
                    case 'P':
                        pickup();
                        break;
                    case 'M':
                        move();
                        break;
                    case 'L':
                        lower();
                        break;
                    default:
                        break;
                }
            }
            return (piles.toString());
        }


        private final Piles piles;
        private final int maxWidth;
        private int position = 0;
        private boolean hasBlock = false;


        private void pickup() {
            hasBlock = true;
            position = 0;
        }


        private void move() {
            position = Math.min(++position, maxWidth);
        }


        private void lower() {
            if (piles.drop(position)) {
                hasBlock = false;
            }
        }
    }

    public static void main(String[] args) {

        Robot robot = new Robot(new Piles(10, 15));
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");

        System.out.println(robot.execute("PLPLPLPLPLPLPLPLPLPL"));
//        System.out.println(robot.execute("PMLPMMMLPMLPMML"));
//        System.out.println( robot.execute("PMLPMLPMLPMLPMLPMLPMLPMLPMLPMLPMLPMLPMLPMLPML"));
//        System.out.println( robot.execute("PMMMMMMMMMMMMMMMMLPMMMMMMMMMMMMMMMMLPMMMMMMMMMMMMMMMML"));


    }

}
