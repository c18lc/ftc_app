package org.firstinspires.ftc.teamcode.Autonomous;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous(name = "Blue 1", group = "Sensor")
public class PTBlue1 extends LinearOpMode {

    /**
     * Note that the REV Robotics Color-Distance incorporates two sensors into one device.
     * It has a light/distance (range) sensor.  It also has an RGB color sensor.
     * The light/distance sensor saturates at around 2" (5cm).  This means that targets that are 2"
     * or closer will display the same value for distance/light detected.
     * <p>
     * Although you configure a single REV Robotics Color-Distance sensor in your configuration file,
     * you can treat the sensor as two separate sensors that share the same name in your op mode.
     * <p>
     * In this example, we represent the detected color by a hue, saturation, and value color
     * model (see https://en.wikipedia.org/wiki/HSL_and_HSV).  We change the background
     * color of the screen to match the detected color.
     * <p>
     * In this example, we  also use the distance sensor to display the distance
     * to the target object.  Note that the distance sensor saturates at around 2" (5 cm).
     */

    ColorSensor colorSensor;


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeft = null;
    private DcMotor FrontRight = null;
    private DcMotor BackLeft = null;
    private DcMotor BackRight = null;
    private DcMotor Pulley = null;
    private DcMotor NomLeft = null;
    private DcMotor NomRight = null;
    //private DcMotor ThiccBoiPlacer = null;
    private Servo Servo1 = null;
    //private Servo Servo2 = null;
    //private Servo ServoRelicRelease = null;
    //private Servo ServoHand = null;
    //private Servo ServoElbow = null;
    //private DcMotor ThiccBoiRetriever= null;
    private Servo colorServo = null;
    double startTime = runtime.milliseconds();




    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        Pulley = hardwareMap.get(DcMotor.class, "Pulley");
        Servo1 = hardwareMap.get(Servo.class, "Servo1");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        colorServo = hardwareMap.get(Servo.class, "colorServo");
        NomLeft = hardwareMap.get(DcMotor.class, "NomLeft");
        NomRight = hardwareMap.get(DcMotor.class, "NomRight");

        //WheelOne.setDirection(DcMotor.Direction.FORWARD);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.FORWARD);
        Pulley.setDirection(DcMotor.Direction.FORWARD);
        NomLeft.setDirection(DcMotor.Direction.FORWARD);
        NomRight.setDirection(DcMotor.Direction.REVERSE);

        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;
        colorServo.setPosition(.95);
        //do stuff here!
        waitForStart();

        while (opModeIsActive()) {

            //clamp on block
            //Lift lift
            // Pulley.setPower(.9);
            // delay(500);
            // Pulley.setPower(0);

            colorServo.setPosition(0);
            //nom(.9);

            int red = 0;
            int blue = 0;
            int count = 0;
            for (int i = 0; i < 50; i++) {
                if (colorSensor.red() > colorSensor.blue()) {
                    red++;
                }
                if (colorSensor.red() < colorSensor.blue()) {
                    blue++;
                }
                telemetry.update();
            }
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("RED", red);
            telemetry.addData("BLUE", blue);
            telemetry.update();



            double jewelturntime = getRuntime();
            if (red > blue) {
                telemetry.addData("Red Wins!", colorSensor.red());
                telemetry.update();
                turnRight(.4, 150);
                delay(100);
                colorServo.setPosition(0.95);
                delay(100);
                turnLeft(.4, 150);
                delay(500);
                moveForward(.4,1000);
                delay(500);
                turnRight(.4, 1000);
                delay(500);
                //strafeLeft(.95, 250);
                Servo1.setPosition(0.9);
                delay(500);
                moveBackward(.4,750);
                delay(250);
                moveForward(.4,250);
                delay(250);
                moveBackward(.4,750);
                delay(250);
                moveForward(.4,250);
                delay(250);
                Servo1.setPosition(0.3);

//                strafeLeft(.95,1200);
//                delay(500);
//                moveForward(.4, 1750);
//                delay(500);
//                moveBackward(.4, 200);
//                nom(-.9);
//                delay(500);
//                moveForward(.4, 700);
//                nom(.9);
//                delay(500);
//                moveBackward(.4, 1700);
//                delay(1700);
//                strafeLeft(.6, 200);
//                delay(500);
//                Servo1.setPosition(0.5);
//                delay(500);
//                Servo1.setPosition(0.9);
//                delay(500);
//                moveBackward(.4, 750);
//                delay(500);
//                moveForward(.4, 250);
//                Servo1.setPosition(.3);
//                delay(500);



                //delay(250);
                //moveForward(.4, 350);
                //delay(500);
                //moveBackward(.4,250);
            } else {
                telemetry.addData("Blue Wins!", colorSensor.red());
                telemetry.update();
                turnLeft(.4, 150);
                delay(100);
                colorServo.setPosition(0.95);
                delay(100);
                turnRight(.4, 150);
                delay(500);
                moveForward(.4,1000);
                delay(500);
                turnRight(.4, 1000);
                delay(500);
                //strafeLeft(.95, 250);
                Servo1.setPosition(0.9);
                delay(500);
                moveBackward(.4,750);
                delay(500);
                moveForward(.4,250);
                delay(250);
                moveBackward(.4,750);
                delay(250);
                moveForward(.4,250);
                Servo1.setPosition(.3);
                delay(500);

//                strafeLeft(.6,1200);
//                delay(500);
//                moveForward(.4, 1750);
//                delay(500);
//                moveBackward(.4, 200);
//                nom(-.9);
//                delay(500);
//                moveForward(.4, 700);
//                nom(.9);
//                delay(500);
//                moveBackward(.4, 1700);
//                delay(1700);
//                strafeLeft(.95, 200);
//                delay(500);
//                Servo1.setPosition(0.5);
//                delay(500);
//                Servo1.setPosition(0.9);
//                delay(500);
//                moveBackward(.4, 750);
//                delay(500);
//                moveForward(.4,250);
//                Servo1.setPosition(.3);
//                delay(500);

            }

            break;




        }

        colorSensorCode(SCALE_FACTOR, hsvValues);
        strafeRight(0,0);


    }



    void colorSensorCode(double SCALE_FACTOR, float[] hsvValues) {
        Color.RGBToHSV((int) (colorSensor.red() * SCALE_FACTOR),
                (int) (colorSensor.green() * SCALE_FACTOR),
                (int) (colorSensor.blue() * SCALE_FACTOR),
                hsvValues);
    }




    void colorServoDown(){
        colorServo.setPosition(0.8);
        delay(1000);
    }


    //Put any functions that you want here
    public void delay(int time) {
        double startTime = runtime.milliseconds();
        while (runtime.milliseconds() - startTime < time) {
        }
    }
    public void moveForward(double power, int time) {
        FrontLeft.setPower(power);
        FrontRight.setPower(power);
        BackLeft.setPower(power);
        BackRight.setPower(power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    public void moveBackward(double power, int time) {
        FrontLeft.setPower(-power);
        FrontRight.setPower(-power);
        BackLeft.setPower(-power);
        BackRight.setPower(-power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    public void strafeRight(double power, int time){
        FrontLeft.setPower(-power);
        FrontRight.setPower(-power);
        BackLeft.setPower(power);
        BackRight.setPower(power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    public void strafeLeft(double power, int time){
        FrontLeft.setPower(power);
        FrontRight.setPower(power);
        BackLeft.setPower(-power);
        BackRight.setPower(-power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    public void turnRight(double power, int time){
        FrontLeft.setPower(power);
        FrontRight.setPower(-power);
        BackLeft.setPower(power);
        BackRight.setPower(-power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    public void turnLeft(double power, int time){
        FrontLeft.setPower(-power);
        FrontRight.setPower(power);
        BackLeft.setPower(-power);
        BackRight.setPower(power);
        delay(time);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }

    public void nom(double power){
        NomRight.setPower(power);
        NomLeft.setPower(power);
    }
}