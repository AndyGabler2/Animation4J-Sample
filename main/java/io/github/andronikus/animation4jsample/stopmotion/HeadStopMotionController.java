package io.github.andronikus.animation4jsample.stopmotion;

import com.andronikus.animation4j.stopmotion.StopMotionController;
import com.andronikus.animation4j.stopmotion.StopMotionState;
import io.github.andronikus.animation4jsample.RobotState;
import io.github.andronikus.animation4jsample.spritesheet.HeadSpriteSheet;

public class HeadStopMotionController extends StopMotionController<Object, RobotState, HeadSpriteSheet> {

    public HeadStopMotionController() {
        super(new HeadSpriteSheet());
    }

    @Override
    public boolean checkIfObjectIsRoot(RobotState object) {
        return true;
    }

    @Override
    protected StopMotionState<Object, RobotState, HeadSpriteSheet> buildInitialStatesAndTransitions() {
        final StopMotionState<Object, RobotState, HeadSpriteSheet> idleState = new StopMotionState<>(this)
            .addFrame(40L, HeadSpriteSheet::getIdleSprite)
            .addFrame(3L, HeadSpriteSheet::getIdleSprite)
            .addFrame(2L, HeadSpriteSheet::getIdleSprite)
            .addFrame(1L, HeadSpriteSheet::getIdleSprite)
            .addFrame(2L, HeadSpriteSheet::getIdleSprite)
            .addFrame(3L, HeadSpriteSheet::getIdleSprite);

        final StopMotionState<Object, RobotState, HeadSpriteSheet> sadState = idleState.createTransitionState((o, robotState) -> robotState.isRobotSad())
            .addFrame(2L, HeadSpriteSheet::getSadSprite)
            .addFrame(3L, HeadSpriteSheet::getSadSprite)
            .addFrame(5L, HeadSpriteSheet::getSadSprite)
            .addFrame(null, HeadSpriteSheet::getSadSprite);

        final StopMotionState<Object, RobotState, HeadSpriteSheet> sadRecoveryState = sadState.createTransitionState((o, robotState) -> !robotState.isRobotSad())
            .addFrame(3L, HeadSpriteSheet::getSadRecoverySprite)
            .addFrame(6L, HeadSpriteSheet::getSadRecoverySprite)
            .addFrame(9L, HeadSpriteSheet::getSadRecoverySprite)
            .addFrame(null, HeadSpriteSheet::getSadRecoverySprite)
            .withInterruptableFlag(false);

        sadRecoveryState.createTransition((o, qwertyState) -> true, idleState);

        final StopMotionState<Object, RobotState, HeadSpriteSheet> happyState = idleState.createTransitionState((o, robotState) -> robotState.isRobotHappy())
            .addFrame(2L, HeadSpriteSheet::getHappySprite)
            .addFrame(3L, HeadSpriteSheet::getHappySprite)
            .addFrame(null, HeadSpriteSheet::getHappySprite);

        final StopMotionState<Object, RobotState, HeadSpriteSheet> happyRecoveryState = happyState.createTransitionState((o, robotState) -> !robotState.isRobotHappy())
            .addFrame(2L, HeadSpriteSheet::getHappyRecoverySprite)
            .addFrame(9L, HeadSpriteSheet::getHappyRecoverySprite)
            .addFrame(null, HeadSpriteSheet::getHappyRecoverySprite)
            .withInterruptableFlag(false);

        happyRecoveryState.createTransition((o, qwertyState) -> true, idleState);

        return idleState;
    }
}
