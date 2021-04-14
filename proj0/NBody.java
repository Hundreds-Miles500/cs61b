public class NBody{
    public static double readRadius(String s){
        In in = new In(s);
        int n = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Planet [] readPlanets(String s){
        In in = new In(s);
        int n = in.readInt();
        double r = in.readDouble();

        //It only malloc memory of pointer but not alloc value to it
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n ; i += 1){
            double a = in.readDouble();
            double b = in.readDouble();
            double c = in.readDouble();
            double d = in.readDouble();
            double e = in.readDouble();
            String f = in.readString();
            //Then the object is truly created
            planets[i] = new Planet(a, b, c, d, e, f);
        }
        return planets;
    }
    public static void main (String[] args) {
        /**Collecting All Needed Input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /**Drawing the Backfround */
        StdDraw.setScale(-r,r);
        StdDraw.picture(0,0,"images/starfield.jpg");

        /**Draw Planets */
        for (Planet p : planets) {
            p.draw();
        }
        /**Creating an Animation */
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i <= T; i += dt){
            int n = planets.length;
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int j = 0; j < n; j += 1){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < n; j += 1){
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}