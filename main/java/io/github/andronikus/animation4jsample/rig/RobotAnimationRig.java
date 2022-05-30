package io.github.andronikus.animation4jsample.rig;

import com.andronikus.animation4j.rig.AnimationLimb;
import com.andronikus.animation4j.rig.AnimationRig;
import com.andronikus.animation4j.util.ImagesUtil;
import io.github.andronikus.animation4jsample.stopmotion.HeadStopMotionController;
import io.github.andronikus.animation4jsample.stopmotion.ArmTopStopMotionController;
import io.github.andronikus.animation4jsample.RobotState;

import java.util.Collections;
import java.util.List;

public class RobotAnimationRig extends AnimationRig<Object, RobotState> {

    public RobotAnimationRig(RobotState state) {
        super(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfObjectIsAnimatedEntity(RobotState object) {
        return true;
    }

    @Override
    protected List<AnimationLimb<Object, RobotState>> buildLimbs(RobotState state) {
        final AnimationLimb<Object, RobotState> torso = new AnimationLimb<Object, RobotState>()
            .setWidth(32)
            .setHeight(64)
            .setImage(ImagesUtil.getImage("torso.png"))
            .finishRigging();

        final AnimationLimb<Object, RobotState> neck = torso.registerJoint((short) 1, Math.PI / 2, 34, true)
            .getLimb()
            .setWidth(16)
            .setHeight(16)
            .setImage(ImagesUtil.getImage("neck.png"))
            .finishRigging();

        neck.registerJoint((short)2, Math.PI / 2, 18, false)
            .getLimb()
            .setHeight(32)
            .setWidth(48)
            .setStopMotionController(new HeadStopMotionController())
            .finishRigging();

        final AnimationLimb<Object, RobotState> leftArmTop = torso.registerJoint((short)3, Math.PI * 19 / 32, 32, false)
            .getLimb()
            .setWidth(32)
            .setHeight(64)
            .setStopMotionController(new ArmTopStopMotionController())
            .finishRigging();

        leftArmTop.registerJoint((short)4, -Math.PI / 32 * 19, 35, false)
            .getLimb()
            .setWidth(16)
            .setHeight(52)
            .setImage(ImagesUtil.getImage("armbottom.png"))
            .finishRigging();

        final AnimationLimb<Object, RobotState> rightArmTop = torso.registerJoint((short)5, Math.PI * 13 / 32, 32, false)
            .getLimb()
            .setWidth(32)
            .setHeight(64)
            .setStopMotionController(new ArmTopStopMotionController())
            .setReflectX(true)
            .finishRigging();

        rightArmTop.registerJoint((short)6, -Math.PI / 32 * 13, 35, false)
            .getLimb()
            .setWidth(16)
            .setHeight(52)
            .setImage(ImagesUtil.getImage("armbottom.png"))
            .finishRigging();

        final AnimationLimb<Object, RobotState> leftLegTop = torso.registerJoint((short)7, Math.PI * 23 / 16, 32, true)
            .getLimb()
            .setWidth(16)
            .setHeight(64)
            .setImage(ImagesUtil.getImage("legtop.png"))
            .setReflectX(true)
            .finishRigging();

        leftLegTop.registerJoint((short)8, Math.PI / -2, 32, false)
            .getLimb()
            .setReflectX(true)
            .setWidth(16)
            .setHeight(52)
            .setImage(ImagesUtil.getImage("legbottom.png"))
            .finishRigging();

        final AnimationLimb<Object, RobotState> rightLegTop = torso.registerJoint((short)9, Math.PI * 25 / 16, 32, true)
            .getLimb()
            .setWidth(16)
            .setHeight(64)
            .setImage(ImagesUtil.getImage("legtop.png"))
            .setReflectX(true)
            .finishRigging();

        rightLegTop.registerJoint((short)10, Math.PI / -2, 32, false)
            .getLimb()
            .setWidth(16)
            .setHeight(52)
            .setImage(ImagesUtil.getImage("legbottom.png"))
            .finishRigging();

        return Collections.singletonList(torso);
    }
}
