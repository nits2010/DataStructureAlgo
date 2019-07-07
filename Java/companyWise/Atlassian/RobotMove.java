package Java.companyWise.Atlassian;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/04/19
 * Description:https://codereview.stackexchange.com/questions/209155/robot-block-command?rq=1
 *
 * Problem
 * -----------
 * We have a robot that can pickup blocks from a stash, move them horizontally and lower them in place.
 * There are 10 positions available to lower blocks number 0 to 9. Each position can hold up to 15 blocks.
 *
 * The robot understands the commands 'P', 'M' and 'L':
 *
 * P: Pickup from the stash and move to position 0
 * M: Move to the Next Position
 * L: Lower the block
 * The robot is safe to operate and very forgiving:
 *
 * There are always blocks in the stash (Pickup always gets a block)
 * If the robot already holds a block, Pickup will reset position to 0
 * The robot will not go beyond position 9, Trying to move further does nothing
 * Lowering the block on a pile of 15 does nothing
 * Lowering without a block does nothing
 * Robot ignores any command that is not 'P', 'M','L'
 *
 * Implement a function that takes a String of commands for the robot.
 * The function should output String representing the number of blocks (in hexadecimal) at each position after running all the commands
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
