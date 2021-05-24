public class Planet{
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;

    /**
     * @param xP the x-position of a planet
     * @param yP the y-position of a planet
     * @param xV current velocity in x-direction
     * @param yV current velocity in y-direction
     * @param mass the mass of planet
     * @param imgFileName he name of the file that corresponds to the image that depicts the body
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String imgfilename ){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = imgfilename;
}
     public Planet(Planet b){
         xxPos = b.xxPos;
         yyPos = b.yyPos;
         xxVel = b.xxVel;
         yyVel = b.yyVel;
         mass = b.mass;
         imgFileName = b.imgFileName;
     }

    /**
     * p_dx the distance in x-axis
     * p_dy the distance in y-axis
     */

     public double calcDistance(Planet b){
         double dx = b.xxPos - this.xxPos;
         double dy = b.yyPos - this.yyPos;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
     }

     public double calcForceExertedBy(Planet b){
        double G = 6.67e-11;
        double Force = this.mass * b.mass * G/(this.calcDistance(b) * this.calcDistance(b));
        return Force;
     }

     public double calcForceExertedByX(Planet b){
        double ForceExertedByX = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos)/this.calcDistance(b);
        return ForceExertedByX;
     }
    public double calcForceExertedByY(Planet b){
        double ForceExertedByY = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos)/this.calcDistance(b);
        return ForceExertedByY;
    }
    /**
        calculate the net force exerted on a specific planet
     */
    public double calcNetForceExertedByX(Planet[] allBodys){
        double NetForceExertedByX = 0;
         for (int i = 0; i < allBodys.length; i+=1) {
             if (this.xxPos == allBodys[i].xxPos && this.yyPos == allBodys[i].yyPos) {
                 continue;
             }
             double NetForceExertedByXByBody = this.calcForceExertedBy(allBodys[i]) * (allBodys[i].xxPos - this.xxPos)/this.calcDistance(allBodys[i]);
             NetForceExertedByX += NetForceExertedByXByBody;
         }
        return NetForceExertedByX;
    }
    public double calcNetForceExertedByY(Planet[] allBodys){
        double NetForceExertedByY = 0;
        for (int i = 0; i < allBodys.length; i+=1) {
            if (this.xxPos == allBodys[i].xxPos && this.yyPos == allBodys[i].yyPos) {
                continue;
            }
            double NetForceExertedByYByBody = this.calcForceExertedBy(allBodys[i]) * (allBodys[i].yyPos - this.yyPos)/this.calcDistance(allBodys[i]);
            NetForceExertedByY += NetForceExertedByYByBody;
        }
        return NetForceExertedByY;
    }
    /**
     calculate the acceleration resulting the position change
     */
    public void update(double dt,double fX,double fY){
        double a_x = fX / this.mass;
        double a_y = fY /this.mass;
        this.xxVel += a_x * dt;
        this.xxPos += this.xxVel * dt;
        this.yyVel += a_y * dt;
        this.yyPos += this.yyVel * dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}