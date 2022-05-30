package io.github.andronikus.animation4jsample.animation;

import com.andronikus.animation4j.animation.Animation;
import com.andronikus.animation4j.animation.AnimationController;
import io.github.andronikus.animation4jsample.rig.RobotAnimationRig;
import io.github.andronikus.animation4jsample.RobotState;

public class RobotAnimationController extends AnimationController<Object, RobotState> {

    public RobotAnimationController(RobotState robot) {
        super(new RobotAnimationRig(robot));
    }

    @Override
    protected Animation<Object, RobotState> buildInitialStatesAndTransitions() {
        final Animation<Object, RobotState> idleAnimation = createAnimation()
            .withInterruptableFlag(false)
            .keyFrameBuilder()
                .withJoint((short)3)
                .withDuration(30L)
                .withJointRotation(0)
            .buildKeyFrame()
            .keyFrameBuilder()
                .withJoint((short)3)
                .withDuration(null)
                .withJointRotation(-Math.PI / 2)
            .buildKeyFrame();

        idleAnimation.createTransitionState((o, qwertyState) -> true)
            .withInterruptableFlag(true)
            .keyFrameBuilder()
                .withJoint((short)4)
                .withDuration(20L)
                .withJointRotation(0)
            .buildKeyFrame()
            .keyFrameBuilder()
                .withJoint((short)4)
                .withDuration(20L)
                .withJointRotation(Math.PI / -2)
            .buildKeyFrame()
            .finishAnimating();
        return idleAnimation.finishAnimating();
    }

    @Override
    public boolean checkIfObjectIsRoot(RobotState object) {
        return true;
    }
}
