package io.github.andronikus.animation4jsample.spritesheet;

import com.andronikus.animation4j.spritesheet.SpriteSheet;

import java.awt.image.BufferedImage;

public class HeadSpriteSheet extends SpriteSheet {

    public HeadSpriteSheet() {
        super("head.png", 48, 32);
    }

    public BufferedImage getIdleSprite(int animationState) {
        return getTile(0, animationState);
    }

    public BufferedImage getSadSprite(int animationState) {
        return getTile(1, animationState);
    }

    public BufferedImage getSadRecoverySprite(int animationState) {
        return getTile(1, animationState + 4);
    }

    public BufferedImage getHappySprite(int animationState) {
        return getTile(2, animationState);
    }

    public BufferedImage getHappyRecoverySprite(int animationState) {
        return getTile(2, animationState + 3);
    }
}
