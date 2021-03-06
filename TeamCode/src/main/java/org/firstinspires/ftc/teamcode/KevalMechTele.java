package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static android.os.SystemClock.sleep;

@TeleOp(name = "KevalMechTele", group = "e")
public class KevalMechTele extends OpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    Servo FoundationServo1;
    Servo FoundationServo2;
    DcMotor RotateSlide;
    Servo grableft;
    Servo grabright;

    double flPower, frPower, blPower, brPower;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        FoundationServo1 = hardwareMap.servo.get("servo1");
        FoundationServo2 = hardwareMap.servo.get("servo2");
        RotateSlide = hardwareMap.dcMotor.get("RotateSlide");
        grableft = hardwareMap.servo.get("grableft");
        grabright = hardwareMap.servo.get("grabright");
    }

    @Override
    public void loop() {
        // The left joystick to move forward/backward/left/right, right joystick to move turn

        //Slow mode


        //gamepad 1 controls movement and foundation servos
        //gamepad 2 controls rotating the linear slides and grabbing blocks
        //gamepad 2 left stick for controlling slides
        //gamepad 2 right stick for controlling grabbing/releasing
        //both gamepads a = grab, y = release
        fl.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x));
        fr.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x));
        bl.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x));
        br.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x));

        if (gamepad2.dpad_up) {
            RotateSlide.setPower(0.5);
        } else if (gamepad2.dpad_down) {
            RotateSlide.setPower(-0.5);
        } else {
            RotateSlide.setPower(0);
        }


        while(gamepad2.a) {
            //Release block
            grableft.setPosition(0.6);
            grabright.setPosition(0.4);
            sleep(500);
            break;
        }
        while(gamepad2.y){
            //Grab block
            grableft.setPosition(0.25);
            grabright.setPosition(0.75);
            sleep(500);
            break;
        }


        // run until the end of the match (driver presses STOP)
        while(gamepad1.y) {
            // move to 0 degrees.
            //Release foundation
            FoundationServo1.setPosition(0.7);
            FoundationServo2.setPosition(0.3);
            sleep(500);
            break;
        }

        while(gamepad1.a) {
            // move to 180 degrees.
            //Grab foundation
            FoundationServo1.setPosition(0.1);
            FoundationServo2.setPosition(0.75);
            sleep(500);
            break;
        }
        telemetry.addData("Servo 1 Position", FoundationServo1.getPosition());
        telemetry.addData("Servo 2 Position", FoundationServo2.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();

        telemetry.addData("fl", fl.getCurrentPosition());
        telemetry.addData("fr", fr.getCurrentPosition());
        telemetry.addData("bl", bl.getCurrentPosition());
        telemetry.addData("br", br.getCurrentPosition());
        telemetry.update();
    }
}