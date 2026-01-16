package com.IanMuggli.wobbles.controlStateMachine;

public class ControlContext {

    private ControlStates state;

    public ControlContext()
    {
        //Initial State
        state = ControlStates.InventoryClosed;
    }

    public ControlStates doAction(ControlActions action)
    {
        switch (action)
        {
            case OpenInventory:
                this.state = ControlStates.InventoryOpen;
                break;
            case CloseInventory:
                this.state = ControlStates.InventoryClosed;
                break;
        }

        //Send back the new state
        return this.state;
    }

    //Get Methods
    public ControlStates getState() {return state;}
}
