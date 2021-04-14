public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    /*Constructors*/
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    /**Method */
    public double calcDistance(Planet p){
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;
        double r = Math.pow(x * x + y * y,0.5);
        return r;
    }

    /**Get F */
    public static final double G = 6.67e-11;
    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        double F = G * (this.mass * p.mass) / (r * r);
        return F;
    }
    /**Calculate Single Fx and Fy */
    public double calcForceExertedByX(Planet p){
        double x = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        double Fx = F * x / r;
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double y = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        double Fy = F * y / r;
        return Fy;
    }

    /**Calculate net Fx and Fy */
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netx = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            netx = netx + this.calcForceExertedByX(p);
        }
        return netx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double nety = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            nety = nety + this.calcForceExertedByY(p);
        }
        return nety;
    }

    /**update the status */
    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw(){
        String name = "images/" + imgFileName;
        StdDraw.picture(xxPos,yyPos,name);
    }
}