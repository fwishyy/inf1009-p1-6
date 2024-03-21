package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class AnimatedEntity extends Entity {

    // Animation settings
    protected boolean isAnimation;
    protected int frameCountColumn; // Number of frames in a column
    protected int frameCountRow; // Number of frames in a row
    protected float stateTime; // time elapsed since the last frame update
    protected float frameDuration; // Duration between each frame (in seconds)
    protected TextureRegion[][] frames; // to hold sliced frames
    protected int currentFrame;
    protected String currentAnimation;
    protected float rotation;

    // Multiple Animations
    protected ObjectMap<String, TextureRegion[][]> animations;
    // Stores TextureRegions with a corresponding user given string eg. "attack"
    // The same string is used to refer to the animation when setting an animation

    protected ObjectMap<String, Array<Integer>> animation_frames;
    // Stores corresponding info unique to each set of animations
    // Also uses the same string as "animations" to perform the lookup
    // [0] = number of frames animations has (aka. frameCountRow)
    // [1] = width
    // [2] = height

    // Specifc Playback (eg once, twice, n-times)
    protected int playcount;    // User provided int of how many times animation should loop before reseting to default animation
    protected int framecounter; // Tracks number of frames drawn
    protected int framelimit;    // equals playcount * number of frames in the sprite, used to know when animation should be reset
    protected float lapsed;    // Tracks the lapsed time, used to determine when to increment the index of the frame
    protected boolean loop;        // Used to determine if additional logic is needed to process the animation. Looping animation = easier

    // Sprite Flipping
    protected boolean flip;        // true means sprite should be drawn flipped. Most textures face right, so true = face left

    // Flag to know when to trigger something else (Untested)
    // Eg. when firing a bow, only launch the projectile on frame 7 of 13 where the character releases the string
    // Others can keep checking if flag == true before running corresponding game action
    protected boolean flag;
    protected int trigger_frame;

    protected AnimatedEntity(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {

        this.texture = new TextureRegion();
        this.vector2 = new Vector2(x, y);
        this.type = type;
        this.frameCountRow = frameCountRow;
        this.frameCountColumn = frameCountColumn;
        this.frameDuration = frameDuration;
        this.isAnimation = true;
        this.stateTime = 0;
        this.currentFrame = 0;

        // For Specific Playback
        this.playcount = 0;
        this.framelimit = 0;
        this.lapsed = 0;
        this.loop = true;

        // For fliiping
        this.flip = false;

        // For trigger
        this.flag = false;
        trigger_frame = 0;

        // Create new object maps for each animated entity
        this.animations = new ObjectMap<>();
        this.animation_frames = new ObjectMap<>();

        // Stores the initial set of textures as the "default"
        loadAnimation(texture, "default", frameCountColumn);

        // Sets the animation to default
        setAnimation("default");
    }

    // This method loads the texture into the objectmaps for future use
    // This is a private method, accessible by addAnimations
    private void loadAnimation(String filepath, String name, int number_of_frames) {

        Texture tex = new Texture(Gdx.files.internal(filepath));
        int width = tex.getWidth() / number_of_frames;
        int height = tex.getHeight() / frameCountRow;

        //System.out.println("frame len: " + this.frames[0].length);

        // From Ivan's code, I'm not sure when this might be used so I left it in for now
        int cols = this.frameCountColumn;
        int rows = this.frameCountRow;


        // Split texture into frames
        TextureRegion[][] temp = TextureRegion.split(tex, width, height);

        // Store animations into the object map using the provided name
        this.animations.put(name, temp);

        // Create a new array to store a sequence of integers (each set of animations has one array)
        Array<Integer> frameData = new Array<>();

        // Add values to array
        frameData.add(number_of_frames); // i = 0
        frameData.add(width);             // i = 1
        frameData.add(height);             // i = 2

        // Stores the array into another object map
        this.animation_frames.put(name, frameData);

        // It looks like this:
        // ["walk_animation", ["number_of_frames", "width", "height"]]

    }

    // This method sets the animation based on what was loaded. So addAnimation first, then setAnimation
    public void setAnimation(String name) {

        // Check if wanted animation is cached
        if (animations.containsKey(name)) {

            // Get the animation from object map
            this.frames = animations.get(name);

            // Retrieve the corresponding frame information
            Array<Integer> frameData = animation_frames.get(name);
            this.frameCountColumn = frameData.get(0);
            this.width = frameData.get(1);
            this.height = frameData.get(2);


            Texture firstFrame = frames[0][0].getTexture();

            this.texture = new TextureRegion(firstFrame, (int) this.vector2.x, (int) this.vector2.y, width, height);
            this.sprite = new Sprite(this.texture);

            // Set animation to play from first frame
            this.currentFrame = 0;

            System.out.println("AnimatedEntity: Animation '" + name + "' set");
            currentAnimation = name;

        } else {
            System.out.println("AnimatedEntity: requested animation does not exsist");
        }
    }

    // Same as setAnimation, but user can specific how many times to playback
    public void setAnimation(String name, int playcount) {

        // Calls the same method previously
        setAnimation(name);

        // Initialise the extra parameters for a non-looping animation
        this.loop = false;
        this.playcount = playcount;
        this.framelimit = playcount * this.frameCountColumn;
    }


    // Small helper method to assist when adding animation
    public void addAnimation(String filepath, String name, int number_of_frames) {

        if (animations.containsKey(name)) {
            System.out.println("AnimatedEntity: animation already exsists!");
        } else {

            loadAnimation(filepath, name, number_of_frames);
            System.out.println("AnimatedEntity: Animation '" + name + "' added");
        }
    }


    // Removes an animation
    public void removeAnimation(String name) {

        if (animations.containsKey(name)) {

            animations.remove(name);
            animation_frames.remove(name);

            System.out.println("AnimatedEntity: Animation '" + name + "' removed");
        } else {

            System.out.println("AnimatedEntity: animation to remove not found!");

        }
    }


    @Override
    public void draw(SpriteBatch batch) {


    	/* 	How it works:
    	 *
    	 	When adding an animation the parameter frameDuration is specified. If it set to 0.1f
    	 	then a new frame shold be drawn every 100ms. this.lapsed keeps track of the lapsed time given by
    	 	getDeltatime. Every 100ms (or every frameDuration) it is reset to 0,
    	 	and the index of the current frame is updated

    	 	If the animation is a loop, it increments framecounter. If an animation should loop 3 times, then we
    	 	multiply the number of frames (eg. 20) by 3, getting 60. Once framecounter hits 60, then 3 cycles of
    	 	animation should have been drawn, then setAnimation("default") will reset the animation back, as well
    	 	as reset all corresponding variables (framecounter, framelimit, playcount) */

        batch.begin();

        int cols = this.getFrameCountColumn();
        int rows = this.getFrameCountRow();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();


        this.lapsed += Gdx.graphics.getDeltaTime();

        //System.out.println("lapsedtime: " + this.lapsed);
        if (this.lapsed >= this.frameDuration) {

            // Reset time
            this.lapsed = 0f;

            // Increment currentFrame
            currentFrame += 1;

            // Increment framecounter if the animation is to be played n-times
            if (!this.loop) {
                framecounter += 1;
            }

            // Check if currentFrame exceeds the total number of frames
            if (currentFrame >= this.getFrameCountColumn()) {

                // If so, reset currentFrame to 0
                currentFrame = 0;
            }
        }


        // Once the animation has been played n-times, reset it back to the default animation
        if (!this.loop) {
            if (this.framecounter >= this.framelimit) {

                setAnimation("default");
                this.framecounter = 0;
                this.framelimit = 0;
                this.loop = true;
                System.out.println("Animation Reset");
            }
        }


        // When a specific frame is reached, trigger the flag for other methods to know
        // Eg. frame of character releasing of a bow to fire arrow
        if (currentFrame == this.trigger_frame) {
            this.flag = true;
        } else {
            this.flag = false;
        }

        TextureRegion currentFrameRegion = this.getFrames()[currentFrame / cols][currentFrame % cols];

        if (flip) {
            batch.draw(currentFrameRegion, this.getX() + width, this.getY(), width / 2, height / 2, width * -1, height, 1, 1, rotation);
        } else {
            batch.draw(currentFrameRegion, this.getX(), this.getY(), width / 2, height / 2, width, height, 1, 1, rotation);
        }

        batch.end();
    }

    public boolean getIsAnimation() {
        return this.isAnimation;
    }

    public void setIsAnimation(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }

    public int getFrameCountColumn() {
        return this.frameCountColumn;
    }

    public void setFrameCountColumn(int frameCount) {
        this.frameCountColumn = frameCount;
    }

    public int getFrameCountRow() {
        return this.frameCountRow;
    }

    public void setFrameCountRow(int frameCount) {
        this.frameCountRow = frameCount;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getStateTime() {
        return this.stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public float getFrameDuration() {
        return this.frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public TextureRegion[][] getFrames() {
        return this.frames;
    }

    public void setFrames(TextureRegion[][] frames) {
        this.frames = frames;
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public boolean getLoop() {
        return this.loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public boolean getFlip() {
        return this.flip;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setTrigger(int frame) {
        this.trigger_frame = frame;
    }


    @Override
    public void dispose() {
        for (int row = 0; row < frames.length; row++) {
            for (int col = 0; col < frames[row].length; col++)
                frames[row][col].getTexture().dispose();
        }
        super.dispose();
    }

}