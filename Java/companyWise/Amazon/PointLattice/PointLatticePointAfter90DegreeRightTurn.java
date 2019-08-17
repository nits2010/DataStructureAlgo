package Java.companyWise.Amazon.PointLattice;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-07
 * Description: https://stackoverflow.com/questions/55482917/points-on-a-lattice
 * <p>
 * Hanna moves in a lattice where every point can be represented by a pair of integers.
 * She moves from point A to point B and then takes a turn 90 degrees right and starts moving till she reaches
 * the first point on the lattice. Find what's the point she would reach?
 */
public class PointLatticePointAfter90DegreeRightTurn {

    public static void main(String args[]) {
        SolutionPointLatticePointAfter90DegreeRightTurn sol = new SolutionPointLatticePointAfter90DegreeRightTurn();
        System.out.println(sol.solution(2, 2, 2, -3));
        System.out.println(sol.solution(-1, 3, 3, 1));
    }
}

class SolutionPointLatticePointAfter90DegreeRightTurn {

    /**
     * I'm assuming you mean that she moves in a straight line from A to B and then turns 90 degrees, and that the lattice is a Cartesian grid with the y axis pointing up and the x axis pointing right.
     * <p>
     * Let (dx,dy) = (Bx,By)-(Ax,Ay), the vector from point A to point B.
     * <p>
     * We can rotate this by 90 degrees to give (dy,-dx).
     * <p>
     * After hanna turns right at B, she will head out along that rotated vector toward (Bx+dy,By-dx)
     * <p>
     * Since she is moving in a straight line, her vector from B will follow (t.dy,-t.dx), and will hit another lattice point when both of those components are integers, i.e...
     * <p>
     * She will hit another lattice point at: (Bx + dy/GCD(|dx|,|dy|), By - dx/GCD(|dx|,|dy|) )
     *
     * @param AX
     * @param AY
     * @param BX
     * @param BY
     * @return
     */
    public String solution(int AX, int AY, int BX, int BY) {

        int dx = BX - AX;
        int dy = BY - AY;

        int gcd = getGCD(Math.abs(dx), Math.abs(dy));

        int x = BX + dy / gcd;
        int y = BY - dx / gcd;

        return "" + x + "," + y;

    }

    //GCD O(log(ab))
    private int getGCD(int a, int b) {
        if (b == 0)
            return a;
        return getGCD(b, a % b);

    }
}