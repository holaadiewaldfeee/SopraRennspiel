package model;


import javafx.scene.image.Image;

/**
 * Class car represents the race-car in the race game.
 */
public class Car {
    double heading = 0.0f;  // angle car is pointed at (radians)
    Vector2 position = new Vector2();  // metres in world coords
    private Vector2<Double, Double> velocity = new Vector2<Double, Double>();  // m/s in world coords
    private Vector2<Double, Double> velocity_c = new Vector2<Double, Double>();  // m/s in local car coords (x is forward y is sideways)
    private Vector2<Double, Double> accel = new Vector2<Double, Double>();  // acceleration in world coords
    private Vector2<Double, Double> accel_c = new Vector2<Double, Double>();   // accleration in local car coords
    public double absVel = 0.0f;  // absolute velocity m/s
    double yawRate = 0.0f;   // angular velocity in radians
    double steer = 0.0f;    // amount of steering input (-1.0..1.0)
    double steerAngle = 0.0f; // actual front wheel steer angle (-maxSteer..maxSteer)
    double throttle = 0.0;
    double breakForce = 0.0;

    //  Other static values to be computed from config
    double inertia = 0.0;  // will be = mass
    double wheelBase = 0.0;  // set from axle to CG lengths
    double axleWeightRatioFront = 0.0;  // % car weight on the front axle
    double axleWeightRatioRear = 0.0; // % car weight on the rear axle

    double gravity = 9.81;  // m/s^2
    double mass = 1200.0;  // kg
    double inertiaScale = 1.0;  // Multiply by mass for inertia
    double halfWidth = 0.8; // Centre to side of chassis (metres)
    double cgToFront = 2.0; // Centre of gravity to front of chassis (metres)
    double cgToRear = 2.0;   // Centre of gravity to rear of chassis
    double cgToFrontAxle = 1.25;  // Centre gravity to front axle
    double cgToRearAxle = 1.25;  // Centre gravity to rear axle
    double cgHeight = 0.55;  // Centre gravity height
    double wheelRadius = 0.3;  // Includes tire (also represents height of axle)
    double wheelWidth = 0.2;  // Used for render only
    double tireGrip = 2.0;  // How much grip tires have
    double lockGrip = 0.7;  // % of grip available when wheel is locked
    double engineForce = 8000.0;
    double brakeForce = 12000.0;
    double eBrakeForce = this.brakeForce / 2.5;
    double weightTransfer = 0.2;  // How much weight is transferred during acceleration/braking
    double maxSteer = 0.6;  // Maximum steering angle in radians
    double cornerStiffnessFront = 5.0;
    double cornerStiffnessRear = 5.2;
    double airResist = 2.5;    // air resistance (* vel)
    double rollResist = 8.0;    // rolling resistance force (* vel)

    private Image look;

    public Car() {
    }

    public void update(float dt) {
        // Pre-calc heading vector
        double sn = Math.sin(this.heading);
        double cs = Math.cos(this.heading);

        // Get velocity in local car coordinates
        this.velocity_c.x = cs * this.velocity.x + sn * this.velocity.y;
        this.velocity_c.y = cs * this.velocity.y - sn * this.velocity.x;

        // Weight on axles based on centre of gravity and weight shift due to forward/reverse acceleration
        double axleWeightFront = mass * (this.axleWeightRatioFront * gravity - weightTransfer * this.accel_c.x * cgHeight / this.wheelBase);
        double axleWeightRear = mass * (this.axleWeightRatioRear * gravity + weightTransfer * this.accel_c.x * cgHeight / this.wheelBase);

        // Resulting velocity of the wheels as result of the yaw rate of the car body.
        // v = yawrate * r where r is distance from axle to CG and yawRate (angular velocity) in rad/s.
        double yawSpeedFront = cgToFrontAxle * this.yawRate;
        double yawSpeedRear = -cgToRearAxle * this.yawRate;

        // Calculate slip angles for front and rear wheels (a.k.a. alpha)
        double slipAngleFront = Math.atan2(this.velocity_c.y + yawSpeedFront, Math.abs(this.velocity_c.x)) - Math.signum(this.velocity_c.x) * this.steerAngle;
        double slipAngleRear = Math.atan2(this.velocity_c.y + yawSpeedRear, Math.abs(this.velocity_c.x));

        double tireGripFront = tireGrip;
        //double tireGripRear = tireGrip * (1.0 - this.inputs.ebrake * (1.0 - lockGrip)); // reduce rear grip when ebrake is on

        double frictionForceFront_cy = clamp(-cornerStiffnessFront * slipAngleFront, -tireGripFront, tireGripFront) * axleWeightFront;
        //double frictionForceRear_cy = clamp(-cornerStiffnessRear * slipAngleRear, -tireGripRear, tireGripRear) * axleWeightRear;

        //  Get amount of brake/throttle from our inputs
        //double brake = Math.min(this.inputs.brake * brakeForce + this.inputs.ebrake * eBrakeForce, brakeForce);
        //double throttle = this.inputs.throttle * engineForce;

        //  Resulting force in local car coordinates.
        //  This is implemented as a RWD car only.
        double tractionForce_cx = throttle - brakeForce * Math.signum(this.velocity_c.x);
        double tractionForce_cy = 0;

        double dragForce_cx = -rollResist * this.velocity_c.x - airResist * this.velocity_c.x * Math.abs(this.velocity_c.x);
        double dragForce_cy = -rollResist * this.velocity_c.y - airResist * this.velocity_c.y * Math.abs(this.velocity_c.y);

        // total force in car coordinates
        double totalForce_cx = dragForce_cx + tractionForce_cx;
        //double totalForce_cy = dragForce_cy + tractionForce_cy + Math.cos(this.steerAngle) * frictionForceFront_cy + frictionForceRear_cy;

        // acceleration along car axes
        this.accel_c.x = totalForce_cx / mass;  // forward/reverse accel
       //this.accel_c.y = totalForce_cy / mass;  // sideways accel

        // acceleration in world coordinates
        this.accel.x = cs * this.accel_c.x - sn * this.accel_c.y;
        this.accel.y = sn * this.accel_c.x + cs * this.accel_c.y;

        // update velocity
        this.velocity.x += this.accel.x * dt;
        this.velocity.y += this.accel.y * dt;

        this.absVel = this.velocity.length();

        // calculate rotational forces
        //double angularTorque = (frictionForceFront_cy + tractionForce_cy) * cgToFrontAxle - frictionForceRear_cy * cgToRearAxle;

        //  Sim gets unstable at very slow speeds, so just stop the car
        if (Math.abs(this.absVel) < 0.5 && throttle != 0.0d) {
            this.velocity.x = this.velocity.y = this.absVel = 0;
            //angularTorque = this.yawRate = 0;
        }

        //double angularAccel = angularTorque / this.inertia;

        //this.yawRate += angularAccel * dt;
        this.heading += this.yawRate * dt;

        //  finally we can update position
        this.position.x += this.velocity.x * dt;
        this.position.y += this.velocity.y * dt;
    }

    public void setSpeed(float x) {
        this.throttle = x * engineForce;
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
    }

    public Image getLook() {
        return look;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
