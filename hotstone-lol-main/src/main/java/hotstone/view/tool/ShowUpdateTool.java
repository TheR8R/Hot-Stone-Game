package hotstone.view.tool;

import frds.broker.IPCException;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ShowUpdateTool extends NullTool {
    protected final HotStoneDrawing model;

    public ShowUpdateTool(DrawingEditor editor) {
        model = (HotStoneDrawing) editor.drawing();
    }
    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // Find the button below
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (hsf.getType() == HotStoneFigureType.OPPONENT_ACTION_BUTTON) {
                model.requestUpdate();
            }
        }
    }
}