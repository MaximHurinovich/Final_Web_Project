package by.gurinovich.webproject.command;

public enum CommandEnum {
    ABOUT {
        {
            this.command = new AboutCommand();
        }
    }, ACCOUNT {
        {
            this.command = new AccountCommand();
        }
    }, ADDMONEY {
        {
            this.command = new AddMoneyCommand();
        }
    }, EDIT {
        {
            this.command = new EditCommand();
        }
    }, ACCEPTADD {
        {
            this.command = new AcceptAddMoneyCommand();
        }
    }, ACCEPTEDIT {
        {
            this.command = new AcceptEditCommand();
        }
    },LOGIN {
        {
            this.command = new LoginCommand();
        }
    }, LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },HOME {
        {
            this.command = new HomeCommand();
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