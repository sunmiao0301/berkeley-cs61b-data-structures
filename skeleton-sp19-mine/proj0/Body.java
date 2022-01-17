class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double mass, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        this.mass = mass;//用一下this 就是玩
        imgFileName = img;
    }
    public Body(Body b){//直接用对象实例作为参数的构造函数
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    //计算两个body之间的距离 实例方法 this指代调用这个方法的对象实例
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
    }

    //计算两个body之间的力
    public double calcForceExertedBy(Body b){
        double G = 6.67 * Math.pow(10, -11);
        return G * (b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }
    
    //注意以下两个函数中，不要使用 Math.abs 来修复这些方法的符号问题。这将在以后绘制行星时引起问题。
    public double calcForceExertedByX(Body b){
        return (this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        return (this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByX(allBodys[i]);
        }
        return ret;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        int len = allBodys.length;
        double ret = 0;
        for(int i = 0; i < len; i++){
            if(!this.equals(allBodys[i]))
                ret += this.calcForceExertedByY(allBodys[i]);
        }
        return ret;
    }

    public void update(double seconds, double xForce, double yForce){
        double xAccelerate = xForce / this.mass;
        double yAccelerate = yForce / this.mass;
        this.xxVel += xAccelerate * seconds;
        this.yyVel += yAccelerate * seconds;
        this.xxPos += this.xxVel * seconds;
        this.yyPos += this.yyVel * seconds;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
        StdDraw.show();
    }
}