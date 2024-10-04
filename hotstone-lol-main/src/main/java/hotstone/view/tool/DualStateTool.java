package hotstone.view.tool;

import frds.broker.IPCException;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class DualStateTool extends NullTool{
    private final Tool theNullTool;
    private final Drawing model;
    private Tool state;
    private DrawingEditor editor;
    private Game game;
    private Player playerPlaying;

    public DualStateTool(DrawingEditor editor, Game game, Player who) {
        this.editor = editor;
        this.game = game;
        model = editor.drawing();
        state = theNullTool = new NullTool();
        playerPlaying = who;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());

        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (game.getPlayerInTurn() == playerPlaying) {
                // TODO: Complete this state selection
                if (hsf.getType() == HotStoneFigureType.CARD_FIGURE) {
                    state = new CardPlayTool(editor, game, game.getPlayerInTurn());
                } else if (hsf.getType() == HotStoneFigureType.TURN_BUTTON ||
                        hsf.getType() == HotStoneFigureType.SWAP_BUTTON) {
                    state = new EndTurnTool(editor, game);
                } else if (hsf.getType() == HotStoneFigureType.MINION_FIGURE) {
                    state = new MinionAttackTool(game, editor, game.getPlayerInTurn());
                } else if (hsf.getType() == HotStoneFigureType.HERO_FIGURE) {
                    state = new UsePowerTool(game, editor, game.getPlayerInTurn());

                } else if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
                    state = theNullTool; // Have to kill the window to restart.

                }
            } else if (hsf.getType() == HotStoneFigureType.OPPONENT_ACTION_BUTTON) {
                state = new ShowUpdateTool(editor);
            }

            state.mouseDown(e, x, y);
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        try {
            state.mouseUp(e, x, y);
            if (state != theNullTool) {
                model.requestUpdate();
            }
        } catch (IPCException exc) {
            System.out.println("bruh");
        }
        state = theNullTool;
    }
    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        state.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        state.mouseMove(e, x, y);
    }

}
