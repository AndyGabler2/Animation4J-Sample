package io.github.andronikus.animation4jsample.stopmotion;

import com.andronikus.animation4j.stopmotion.StopMotionController;
import com.andronikus.animation4j.stopmotion.StopMotionState;
import io.github.andronikus.animation4jsample.RobotState;
import io.github.andronikus.animation4jsample.spritesheet.ArmTopSpriteSheet;

public class ArmTopStopMotionController extends StopMotionController<Object, RobotState, ArmTopSpriteSheet> {

    public ArmTopStopMotionController() {
        super(new ArmTopSpriteSheet());
    }

    @Override
    protected StopMotionState<Object, RobotState, ArmTopSpriteSheet> buildInitialStatesAndTransitions() {
        final StopMotionState<Object, RobotState, ArmTopSpriteSheet> neutralState = new StopMotionState<>(this)
            .addFrame(7L, ArmTopSpriteSheet::getNeutralSprite)
            .addFrame(7L, ArmTopSpriteSheet::getNeutralSprite)
            .addFrame(7L, ArmTopSpriteSheet::getNeutralSprite)
            .addFrame(7L, ArmTopSpriteSheet::getNeutralSprite);

        neutralState.createTransitionState((object, state) -> state.isRobotSad() && !state.isRobotHappy())
            .addFrame(4L, ArmTopSpriteSheet::getRedSprite)
            .addFrame(4L, ArmTopSpriteSheet::getRedSprite)
            .addFrame(4L, ArmTopSpriteSheet::getRedSprite)
            .addFrame(4L, ArmTopSpriteSheet::getRedSprite)
            .createTransition((o, qwertyState) -> qwertyState.isRobotHappy() || !qwertyState.isRobotSad(), neutralState);

        return neutralState;
    }

    @Override
    public boolean checkIfObjectIsRoot(RobotState object) {
        return true;
    }
}
