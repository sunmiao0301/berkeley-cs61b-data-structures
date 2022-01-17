import java.nio.file.attribute.UserPrincipal;

import javax.activation.UnsupportedDataTypeException;

class NBody{
    public static double readRadius(String a){
        In in = new In(a);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] ret = new Body[N];
        for(int i = 0; i < N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ret[i] = new Body(xP, yP, vX, vY, m, img);
        }
        return ret;
    }
    public static void main(String[] args){
        //Hint: the arguments come in as Strings
        //Double.parseDouble();
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        //filename使用本作业前面的方法从描述的文件中读取物体和宇宙半径
        //物体
        //Body.readBodies(filename);
        //宇宙半径
        //Body.readRadius(filename);

            /**
             * 双缓冲最重要的用途是制作计算机动画，通过快速显示静态绘图来创造运动的错觉。
             * 要制作动画，请重复以下四个步骤：
             * 
             * 清除屏幕外画布。
             * 在离屏画布上绘制对象。
             * 将离屏画布复制到屏幕画布。
             * 稍等片刻。
             */
        
        StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-NBody.readRadius(filename), NBody.readRadius(filename));

        Body[] bodies = readBodies(filename);
        int N = bodies.length;

        double time = 0;

        double[] xForces = new double[N];
        double[] yForces = new double[N];

        while(time <= T){

            StdDraw.clear();

            StdDraw.picture(-NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
		    StdDraw.picture(NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");
            StdDraw.picture(NBody.readRadius(filename), NBody.readRadius(filename), "images/starfield.jpg");
            StdDraw.picture(-NBody.readRadius(filename), -NBody.readRadius(filename), "images/starfield.jpg");

            for(int i = 0; i < N; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //注意这里update的第一个参数是dt不是time（否则就飞出去了）
            for(int j = 0; j < N; j++){
                bodies[j].update(dt, xForces[j], yForces[j]);
                bodies[j].draw();
            }
            //show
            StdDraw.show();
		    StdDraw.pause(10);

            time = time + dt;
        }
    }
}