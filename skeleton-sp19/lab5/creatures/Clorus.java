package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    private int r;// = 34;

    private int g;// = 0;

    private int b;// = 231;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus(){
        this(1);// this (1) 是什么意思
    }

    public Color color() {
        return color(r, g, b);
    }


    /**
     * Called when this creature moves.
     */
    public void move(){
        energy -= 0.03;
        if(energy < 0)
            energy = 0;
    }

    /**
     * Called when this creature attacks C.
     */
    public void attack(Creature c){
        energy += c.energy();
    }

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    public Creature replicate(){
        energy /= 2;
        return new Clorus(energy);
    }

    /**
     * Called when this creature chooses stay.
     */
    public void stay(){
        energy -= 0.01;
        if(energy < 0)
            energy = 0;
    }

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors){
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        //boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()){
            if(entry.getValue().name().equals("empty"))
                emptyNeighbors.add(entry.getKey());
            if(entry.getValue().name().equals("plip"))
                plipNeighbors.add(entry.getKey());
        }
/**
 if (false) { // FIXME
 // TODO
 }
 */
        if(emptyNeighbors.size() == 0)
            return new Action(Action.ActionType.STAY);


        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if(plipNeighbors.size() > 0)
            return new Action(Action.ActionType.ATTACK, plipNeighbors.getFirst());

        // Rule 3
        if(energy >= 1.0)
            return new Action(Action.ActionType.REPLICATE, emptyNeighbors.getFirst());

        // Rule 4
        return new Action(Action.ActionType.MOVE, emptyNeighbors.getFirst());
    }
    /**
     * Returns the current energy.
     */
    public double energy() {
        return energy;
    }

}
