package com.fundynamic.d2tm.game.map.renderer;

import com.fundynamic.d2tm.game.AbstractD2TMTest;
import com.fundynamic.d2tm.game.map.Cell;
import com.fundynamic.d2tm.game.rendering.gui.battlefield.CellShroudRenderer;
import org.junit.Assert;
import org.junit.Test;

import static com.fundynamic.d2tm.game.rendering.gui.battlefield.CellShroudRenderer.ShroudFacing.*;
import static com.fundynamic.d2tm.game.rendering.gui.battlefield.CellShroudRenderer.getFacing;
import static org.junit.Assert.assertEquals;

public class CellShroudRendererTest extends AbstractD2TMTest {

    @Test
    public void middle() throws Exception {
        assertEquals(MIDDLE, getFacing(true, true, true, true));
    }

    @Test
    public void determineShroudFacingReturnsFULLwhenCellIsShrouded() {
        CellShroudRenderer cellShroudRenderer = new CellShroudRenderer(player, shroud);

        Cell cell = map.getCell(60, 60);

        // shroud the cell
        player.shroud(cell.getMapCoordinate());

        // we expect this to be a FULL cell when shrouded
        assertEquals(FULL, cellShroudRenderer.determineShroudFacing(cell));
    }


    @Test
    public void determineShroudFacingReturnsNonFULLWhenCellIsNotShrouded() {
        CellShroudRenderer cellShroudRenderer = new CellShroudRenderer(player, shroud);

        Cell cell = map.getCell(60, 60);

        // remove shroud
        player.revealShroudFor(cell.getMapCoordinate());

        // we expect this to be a MIDDLE cell when this cell is revealed, but around it is shrouded
        assertEquals(MIDDLE, cellShroudRenderer.determineShroudFacing(cell));
    }

    @Test
    public void topLeft() throws Exception {
        assertEquals(TOP_LEFT, getFacing(true, false, false, true));
    }

    @Test
    public void top() throws Exception {
        assertEquals(TOP, getFacing(true, false, false, false));
    }

    @Test
    public void topRight() throws Exception {
        assertEquals(TOP_RIGHT, getFacing(true, true, false, false));
    }

    @Test
    public void right() throws Exception {
        assertEquals(RIGHT, getFacing(false, true, false, false));
    }

    @Test
    public void rightBottom() throws Exception {
        assertEquals(RIGHT_BOTTOM, getFacing(false, true, true, false));
    }

    @Test
    public void bottom() throws Exception {
        assertEquals(BOTTOM, getFacing(false, false, true, false));
    }

    @Test
    public void bottomLeft() throws Exception {
        assertEquals(BOTTOM_LEFT, getFacing(false, false, true, true));
    }

    @Test
    public void left() throws Exception {
        assertEquals(LEFT, getFacing(false, false, false, true));
    }

    @Test
    public void topBottomLeft() throws Exception {
        assertEquals(TOP_BOTTOM_LEFT, getFacing(true, false, true, true));
    }

    @Test
    public void topRightLeft() throws Exception {
        assertEquals(TOP_RIGHT_LEFT, getFacing(true, true, false, true));
    }

    @Test
    public void topRightBottom() throws Exception {
        assertEquals(TOP_RIGHT_BOTTOM, getFacing(true, true, true, false));
    }

    @Test
    public void rightBottomLeft() throws Exception {
        assertEquals(RIGHT_BOTTOM_LEFT, getFacing(false, true, true, true));
    }

    @Test
    public void topBottom() throws Exception {
        assertEquals(TOP_BOTTOM, getFacing(true, false, true, false));
    }

    @Test
    public void rightLeft() throws Exception {
        assertEquals(RIGHT_LEFT, getFacing(false, true, false, true));
    }

    @Test
    public void returnsNullWhenNoShroudAround() throws Exception {
        Assert.assertNull(getFacing(false, false, false, false));
    }
}