package io.github.andronikus.animation4jsample.spritesheet;

import com.andronikus.animation4j.spritesheet.SpriteSheet;

import java.awt.image.BufferedImage;

public class ArmTopSpriteSheet extends SpriteSheet {

    public ArmTopSpriteSheet() {
        super("armtop.png", 32, 64);
    }

    public BufferedImage getNeutralSprite(int animationState) {
        return getTile(0, animationState);
    }

    public BufferedImage getRedSprite(int animationState) {
        return getTile(1, animationState);
    }
}
