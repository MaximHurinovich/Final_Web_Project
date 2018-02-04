package by.gurinovich.webproject.command;

public enum CommandEnum {

    ADDMONEY {
        {
            this.command = new AddMoneyCommand();
        }
    }, RETURNMONEY {
        {
            this.command = new ReturnMoneyCommand();
        }
    }, MYBETS {
        {
            this.command = new MyBetsCommand();
        }
    }, BANUSER {
        {
            this.command = new AdminBanCommand();
        }
    }, MAKEADMIN {
        {
            this.command = new MakeAdminCommand();
        }
    }, MAKEBOOKMAKER {
        {
            this.command = new MakeBookmakerCommand();
        }
    }, BOOKRACE {
        {
            this.command = new BookRaceCommand();
        }
    }, SETBOOKING {
        {
            this.command = new AcceptBookRaceCommand();
        }
    },BOOKRACES {
        {
            this.command = new BookmakerRacesCommand();
        }
    }, BET {
        {
            this.command = new BetCommand();
        }
    }, ACCEPTBET {
        {
            this.command = new AcceptBetCommand();
        }
    }, ADMINBETS {
        {
            this.command = new AdminBetsCommand();
        }
    }, EDIT {
        {
            this.command = new EditCommand();
        }
    }, ADDRACE {
        {
            this.command = new AdminAddRaceCommand();
        }
    }, RUNRACE {
        {
            this.command = new AdminRunRaceCommand();
        }
    }, ADDHORSE {
        {
            this.command = new AdminAddHorseCommand();
        }
    }, DELETERACE {
        {
            this.command = new AdminDeleteRaceCommand();
        }
    }, ACCEPTADD {
        {
            this.command = new AcceptAddMoneyCommand();
        }
    }, ACCEPTEDIT {
        {
            this.command = new AcceptEditCommand();
        }
    }, ADMIN_EDIT {
        {
            this.command = new AdminEditCommand();
        }
    }, BOOKMAKER_EDIT {
        {
            this.command = new BookmakerEditCommand();
        }
    },LOGIN {
        {
            this.command = new LoginCommand();
        }
    }, LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    }, RESULTS {
        {
            this.command = new ResultsCommand();
        }
    }, SIGNUP {
        {
            this.command = new RegisterCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}