package by.gurinovich.webproject.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    }, LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    }, SIGNUP{
        {
            this.command = new RegisterCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}