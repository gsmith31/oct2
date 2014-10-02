package headfirst.command.undo;

import java.util.*;

//
// This is the invoker
//
public class RemoteControlWithUndo {
	private Command[] onCommands;
	private Command[] offCommands;
	private Command undoCommand;
	private Stack<Command> undoCommands;
	private Command noCommand;

	public RemoteControlWithUndo() {
		onCommands = new Command[7];
		offCommands = new Command[7];

		noCommand = new NoCommand();
		for (int i = 0; i < 7; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		undoCommand = noCommand;
		undoCommands = new Stack<Command>();
		undoCommands.push(noCommand);
	}

	public void setCommand(int slot, Command onCommand, Command offCommand) {
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}

	public void onButtonWasPushed(int slot) {
		onCommands[slot].execute();
		// undoCommand = onCommands[slot];
		undoCommands.push(onCommands[slot]);
	}

	public void offButtonWasPushed(int slot) {
		offCommands[slot].execute();
		// undoCommand = offCommands[slot];
		undoCommands.push(offCommands[slot]);
	}

	public void undoButtonWasPushed() {
		if (undoCommands.peek().equals(noCommand)) {
			undoCommands.pop().execute();
			undoCommands.push(noCommand);
		} else {
			undoCommands.pop().execute();
		}
	}

	public String toString() {
		StringBuffer stringBuff = new StringBuffer();
		ListIterator i = undoCommands.listIterator(undoCommands.size());
		stringBuff.append("\n------ Remote Control -------\n");
		int counter = 0;
		while(i.hasPrevious()) {
			stringBuff.append("[slot " + counter + "] "
					+ i.previous().getClass().getName()+ "\n");
			counter++;
		}
		//stringBuff.append("[undo] " + undoCommand.getClass().getName() + "\n");
		return stringBuff.toString();
	}
}
