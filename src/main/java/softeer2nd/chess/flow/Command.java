package softeer2nd.chess.flow;

public class Command {
    public enum CommandType {
        START, END, MOVE;
    }

    private final CommandType commandType;
    private final String[] args;

    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.args = null;
    }

    public Command(CommandType commandType, String... args) {
        this.commandType = commandType;
        this.args = args;
    }

    public String[] getArgs() {
        if (isStart() || isEnd() || args == null) {
            throw new RuntimeException();
        }
        return args;
    }

    public boolean isStart() {
        return commandType.equals(CommandType.START);
    }

    public boolean isEnd() {
        return commandType.equals(CommandType.END);
    }

    public boolean isMove() {
        return commandType.equals(CommandType.MOVE);
    }
}
