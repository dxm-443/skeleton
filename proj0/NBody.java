public class NBody{
    public static double readRadius(String FileName){
        /* Start reading in data/planets.txt */
        In  in = new In(FileName);
        int number = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets(String FileName){
        In  in = new In(FileName);
        int number = in.readInt();
        Planet[] planets = new Planet[number];
        in.readDouble();
        for (int i = 0; i < number; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String imgfilename = in.readString();
            Planet planet = new Planet(xP, yP, xV, yV, m, imgfilename);
            planets[i] = planet;
        }
        return planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        /**
         * draw the background
         */
        int time = 0;
        while(time <= T) {
            int waitTimeMilliseconds = 10;
            StdDraw.enableDoubleBuffering();
            String imageToDraw = "images/starfield.jpg";
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTimeMilliseconds);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}