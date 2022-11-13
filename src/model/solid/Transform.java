package model.solid;

import transforms.*;

public class Transform {
    private Mat4 translation;

    /**
     * Rotation (in degrees)
     * X - roll
     * Y - pitch
     * Z - yaw
     */
    private Point3D rotation;
    private Point3D scale;

    public Transform() {
        translation = new Mat4Identity();
        rotation = new Point3D();
        scale = new Point3D(1,1,1);
    }

    /**
     * Combine translation, rotation and scale
     * @return final matrix
     */
    public Mat4 getMatrix() {
        Mat4 rot = new Mat4RotXYZ(
                Math.toRadians(rotation.getX()),
                Math.toRadians(rotation.getY()),
                Math.toRadians(rotation.getZ())
        );

        return (new Mat4Identity())
                .mul(new Mat4Scale(scale.ignoreW()))
                .mul(rot)
                .mul(translation);
    }

    /**
     * Set rotation
     * @param roll rotation on X axis
     * @param pitch rotation on Y axis
     * @param yaw rotation on Z axis
     */
    public void setRotation(float roll, float pitch, float yaw) {
        rotation = new Point3D(roll % 360, pitch % 360, yaw % 360);
    }

    /**
     * Add rotation to current rotation
     * @param roll rotation over axis X
     * @param pitch rotation over axis Y
     * @param yaw rotation over axis Z
     */
    public void addRotation(float roll, float pitch, float yaw) {
        rotation = rotation.add(new Point3D(roll, pitch, yaw));
    }

    /**
     * rotation
     */
    public Mat4 getRotation() {
        return new Mat4RotXYZ(rotation.getX(), rotation.getY(), rotation.getY());
    }

    /**
     * Set translation
     * @param x X location
     * @param y Y location
     * @param z Z location
     */
    public void setTranslation(float x, float y, float z) {
        translation = new Mat4Transl(x, y, z);
    }


    public void addTranslation(float x, float y, float z) {
        Point3D c = getTranslation();
        setTranslation((float)c.getX() + x, (float)c.getY() + y, (float)c.getZ() + z);
    }


    /**
     * @return object translation
     */
    public Point3D getTranslation() {
        return new Point3D().mul(translation);
    }

    /**
     * set new scale
     * @param x X scale
     * @param y Y scale
     * @param z Z scale
     */
    public void setScale(float x, float y, float z) {
        scale = new Point3D(x, y, z);
    }

    /**
     * scale
     */
    public Point3D getScale() {
        return scale;
    }

    public void addScale(float x, float y, float z) {
        scale = new Point3D(scale.getX() + x, scale.getY() + y, scale.getZ() + z);
    }

    public void addScale(float a) {
        addScale(a, a, a);
    }


    @Override
    public String toString() {
        return "{" +
                " translation= " + getTranslation().toString() +
                " rotation=" + rotation.toString() +
                " scale=" + scale +
                '}';
    }

    public void reset() {
        setTranslation(0,0,0);
        setScale(1,1,1);
        setRotation(0,0,0);
    }
}
