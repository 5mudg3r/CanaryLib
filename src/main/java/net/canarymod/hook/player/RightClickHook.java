package net.canarymod.hook.player;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Multi use hook for right click. Contains information about a player right clicking.
 * @author Jason Jones
 *
 */
public final class RightClickHook extends CancelableHook{

    private Player player;
    private Block placed, clicked;
    private Item item;
    private EntityLiving entity;

    public RightClickHook(Player player, Block clicked, Block placed, Item item, Entity entity){
        if(!(entity instanceof EntityLiving)) {
            this.entity = null;
        }
        else {
            this.entity = (EntityLiving) entity;
        }
        this.player = player;
        this.clicked = clicked;
        this.placed = placed;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Gets the {@link Block} clicked (If there was a bloc clicked)
     * @return
     */
    public Block getBlockClicked(){
        return clicked;
    }

    /**
     * Get the {@link Block} placed (if there was a placed block)
     * @return
     */
    public Block getBlockPlaced(){
        return placed;
    }

    /**
     * Gets the {@link Item} used
     * @return item
     */
    public Item getItem(){
        return item;
    }

    /**
     * Return the {@link EntityLiving} that was right clicked
     * @return entity clicked else null if none clicked
     */
    public EntityLiving getEntityClicked(){
        return entity;
    }

    /**
     * Return the set of Data in this order: PLAYER CLICKEDBLOCK PLACEDBLOCK ITEM ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, clicked, placed, item, entity, isCanceled };
    }
}
