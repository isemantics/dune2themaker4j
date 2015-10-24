package com.fundynamic.d2tm.game.entities;

import com.fundynamic.d2tm.Game;
import com.fundynamic.d2tm.game.behaviors.*;
import com.fundynamic.d2tm.game.rendering.RenderQueue;
import com.fundynamic.d2tm.math.Vector2D;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Renderable, Updateable {

    // Final properties of unit
    protected final EntityData entityData;
    protected final SpriteSheet spriteSheet;
    protected final Player player;
    protected final EntityRepository entityRepository;

    protected Vector2D absoluteCoordinates;

    public Entity(Vector2D absoluteCoordinates, SpriteSheet spriteSheet, EntityData entityData, Player player, EntityRepository entityRepository) {
        this.absoluteCoordinates = absoluteCoordinates;
        this.spriteSheet = spriteSheet;
        this.entityData = entityData;
        this.player = player;
        this.entityRepository = entityRepository;
        if (player != null) {
            // temporarily, because 'particle' does not belong to a player
            player.addEntity(this);
        }
    }

    /**
     * Returns the upper-left coordinate of this entity
     *
     * @return
     */
    public Vector2D getAbsoluteCoordinates() {
        return absoluteCoordinates;
    }

    /**
     * Returns center of this entity
     *
     * @return
     */
    public Vector2D getCenteredCoordinates() {
        return absoluteCoordinates.add(getHalfSize());
    }

    /**
     * Returns distance from this entity to other
     *
     * @param other
     * @return
     */
    public float distance(Entity other) {
        return getCenteredCoordinates().distance(other.getCenteredCoordinates());
    }

    public int getX() {
        return absoluteCoordinates.getXAsInt();
    }

    public int getY() {
        return absoluteCoordinates.getYAsInt();
    }

    public int getSight() {
        return entityData.sight;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean belongsToPlayer(Player other) {
        return other.equals(player);
    }

    public boolean isMovable() {
        return this instanceof Moveable;
    }

    public boolean isSelectable() {
        return this instanceof Selectable;
    }

    public boolean isUpdatable() {
        return this instanceof Updateable;
    }

    public boolean isDestructible() {
        return this instanceof Destructible;
    }

    public boolean isDestroyer() {
        return this instanceof Destroyer;
    }

    public abstract EntityType getEntityType();

    public boolean removeFromPlayerSet(Entity entity) {
        if (player != null) {
            return player.removeEntity(entity);
        }
        return false;
    }

    public Vector2D getRandomPositionWithin() {
        return Vector2D.random(getX(), getX() + entityData.getWidth(), getY(), getY() + entityData.getHeight());
    }

    public Vector2D getDimensions() {
        return Vector2D.create(entityData.getWidth(), entityData.getHeight());
    }

    @Override
    public void enrichRenderQueue(RenderQueue renderQueue) {
        // by default do nothing
    }

    public Vector2D getHalfSize() {
        return Vector2D.create(entityData.getWidth() / 2, entityData.getHeight() / 2);
    }

    /**
     * Return the metadata about this entity.
     * @return
     */
    public EntityData getEntityData() {
        return this.entityData;
    }

    public int getWidthInCells() {
        return entityData.getWidthInCells();
    }

    public int getHeightInCells() {
        return entityData.getHeightInCells();
    }

    public List<Vector2D> getAllCellsAsVectors() {
        return entityData.getAllCellsAsVectors(absoluteCoordinates);
    }
}