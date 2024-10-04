package hotstone.view.tool;

import hotstone.framework.*;
import hotstone.view.GfxConstants;
import hotstone.view.figure.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class MinionAttackTool extends NullTool {
    private Player player;
    private DrawingEditor editor;
    private Game game;
    private HotStoneActorFigure draggedActor;
    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;


    public MinionAttackTool(Game game, DrawingEditor editor, Player who) {
        this.game = game;
        this.editor = editor;
        this.player = who;
    }

        @Override
        public void mouseDown(MouseEvent e, int x, int y) {
            Drawing model = editor.drawing();
            Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
            draggedActor = (HotStoneActorFigure) figureAtPosition;
            model.zOrder(draggedActor, ZOrder.TO_TOP);
            lastX = x; lastY = y;
            orgX = x; orgY = y;
        }

        @Override
        public void mouseDrag(MouseEvent e, int x, int y) {
            // compute relative movement
            draggedActor.moveBy(x - lastX, y - lastY);
            // update last position
            lastX = x; lastY = y;
        }

        @Override
        public void mouseUp(MouseEvent e, int x, int y) {
            // Invoke related facade method, if figure is a card;
            draggedActor.moveBy(orgX - x, orgY - y);
            Drawing model = editor.drawing();
            Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
            Card c = draggedActor.getAssociatedCard();
            HotStoneFigure target = (HotStoneFigure) figureAtPosition;
            if(target.getType() == HotStoneFigureType.MINION_FIGURE) {
                game.attackCard(player, c, ((HotStoneActorFigure)target).getAssociatedCard());
            } else if(target.getType() == HotStoneFigureType.HERO_FIGURE) {
                boolean check = (y > GfxConstants.MY_HERO_POSITION.y) || (x > GfxConstants.MY_HERO_POSITION.x);
                if(!check) {
                    game.attackHero(player, c);
                }
            }



            draggedActor = null;
        }
    }

