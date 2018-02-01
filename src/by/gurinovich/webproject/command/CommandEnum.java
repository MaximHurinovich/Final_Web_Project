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
    }, RETURNMONEY{
        {
            this.command = new ReturnMoneyCommand();
        }
    },MYBETS{
        {
            this.command = new MyBetsCommand();
        }
    },BET{
        {
            this.command = new BetCommand();
        }
    }, ACCEPTBET{
        {
            this.command = new AcceptBetCommand();
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
    },RESULTS {
        {
            this.command = new ResultsCommand();
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