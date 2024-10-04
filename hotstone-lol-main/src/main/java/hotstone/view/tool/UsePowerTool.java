package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.GfxConstants;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UsePowerTool extends NullTool {
    private final DrawingEditor editor;
    private Game game;

    private Player player;


    public UsePowerTool(Game game, DrawingEditor editor, Player who) {
        this.game = game;
        this.editor = editor;
        this.player = who;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        boolean check = (y > GfxConstants.MY_HERO_POSITION.y) || (x > GfxConstants.MY_HERO_POSITION.x);
        if(check) {
            game.usePower(player);
            editor.showStatus("used hero power");
        }
    }
}
