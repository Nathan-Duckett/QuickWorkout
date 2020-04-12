package net.ddns.ndap.quickworkout.model;

/**
 * Workout represents a possible workout action. This contains a time you should be
 * expected to work on this exercise, a name, a picture to reference the workout and
 * a description on how to perform the workout.
 *
 * @author Nathan Duckett
 */
public class Workout {
    private String name;
    private long time;
    private String description;
    private String imagePath;

    /**
     * Create a new Workout with the following contents.
     *
     * @param name Name of this workout.
     * @param expectedTime Expected time this workout should take.
     * @param description String description of how to perform the workout.
     * @param imagePath String path to an image which describes this workout.
     */
    public Workout(String name, long expectedTime, String description, String imagePath) {
        this.name = name;
        this.time = expectedTime;
        this.description = description;
        this.imagePath = imagePath;
    }

    /**
     * Get the name of this workout.
     *
     * @return String name of this workout.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this Workout.
     *
     * @param name String value to set the workout name as.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the expected time this workout should take.
     *
     * @return The long value this workout should take.
     */
    public long getTime() {
        return time;
    }

    /**
     * Set the expected time of this workout.
     *
     * @param time The long value this workout should take.
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Get the description of how to perform this workout.
     *
     * @return String value of the description on how to perform this workout.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of how to perform this workout.
     *
     * @param description String value of the description on how to perform this workout.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the image path to the image that describes this workout.
     *
     * @return String value of the path to an image for this workout.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Set the image path of this workout.
     *
     * @param imagePath String value of the image path for this workout.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
