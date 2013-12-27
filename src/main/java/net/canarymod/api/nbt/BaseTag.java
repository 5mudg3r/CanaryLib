package net.canarymod.api.nbt;

/**
 * Base for all NBT storage tags.
 *
 * @author gregthegeek
 */
public interface BaseTag {

    /**
     * Returns the ID number of the type of tag this is.
     *
     * @return the tag id
     */
    public byte getTypeId();

    /**
     * Copies the Tag
     *
     * @return a new instance of the tag being copied
     */
    public <T> BaseTag copy();

}
